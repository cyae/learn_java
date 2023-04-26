package com.Ex.DiscountSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

class Activity {

    int actId;
    int priceLimit;
    int discount;
    int number;
    int userLimit;

    public Activity(int actId, int priceLimit, int discount, int number, int userLimit) {
        this.actId = actId;
        this.priceLimit = priceLimit;
        this.discount = discount;
        this.number = number;
        this.userLimit = userLimit;
    }

    @Override
    public String toString() {
        return "Activity [actId=" + actId + ", discount=" + discount + ", number=" +
                number + ", priceLimit="
                + priceLimit + ", userLimit=" + userLimit + "]";
    }

}

class DiscountSystem {

    ConcurrentSkipListMap<Activity, HashMap<Integer, Integer>> coreMap;
    HashMap<Integer, Activity> eventMap;

    public DiscountSystem() {
        coreMap = new ConcurrentSkipListMap<>((Activity a1, Activity a2) -> {
            return a2.discount != a1.discount ? a2.discount - a1.discount : a1.actId - a2.actId;
        });

        eventMap = new HashMap<>();
    }

    public void addActivity(int actId, int priceLimit, int discount, int number, int userLimit) {
        Activity activity = new Activity(actId, priceLimit, discount, number, userLimit);
        HashMap<Integer, Integer> log = new HashMap<>();
        coreMap.put(activity, log);

        eventMap.put(actId, activity);
    }

    public void removeActivity(int actId) {
        coreMap.remove(eventMap.get(actId));
    }

    public int consume(int userId, int cost) {
        System.out.println("应付" + cost + "元");
        for (Map.Entry<Activity, HashMap<Integer, Integer>> entry : coreMap.entrySet()) {
            Activity activity = entry.getKey();
            HashMap<Integer, Integer> log = entry.getValue();
            if (verify(activity, log, userId, cost)) {
                int discount = activity.discount;
                System.out.print("按照规则:" + activity);
                System.out.println(", 减免" + discount + "元");
                cost -= discount;
                updateCore(activity, log, userId);
                System.out.println("更新规则:" + activity);
                System.out.println("现有记录:" + log);
                break;
            }
        }

        System.out.println("实付" + cost + "元");
        System.out.println("* * * * * * * * * * * * *");
        return cost;
    }

    private void updateCore(Activity activity, HashMap<Integer, Integer> log, int userId) {
        activity.number--;
        if (activity.number == 0) {
            coreMap.remove(activity);
        }
        log.put(userId, log.getOrDefault(userId, 0) + 1);
    }

    private boolean verify(Activity activity, HashMap<Integer, Integer> log, int userId, int cost) {
        return (cost >= activity.priceLimit &&
                activity.number > 0 &&
                (log.get(userId) == null || log.get(userId) < activity.userLimit));
    }
}