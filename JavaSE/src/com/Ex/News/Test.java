package com.Ex.News;

import java.util.ArrayList;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        News news1 = new News("news1kjasdklblskdjlaiwebrlkbadslcbilwueb");
        News news2 = new News("news2coaheu");

        TreeSet<News> set = new TreeSet<>();

        // 报错，因为News类既没实现comparable接口，set也没传入比较器
        // 无法比较
        set.add(new News("123"));

        ArrayList<News> list = new ArrayList<>();
        list.add(news1);
        list.add(news2);

        for (int i = list.size() - 1; i >= 0; i--) {
            News news = list.get(i);
            if (news.headline.length() > 15) {
                news.headline = news.headline.substring(0, 15);
                news.headline.concat("...");
            }

            System.out.println(news.headline);
        }

    }
}
