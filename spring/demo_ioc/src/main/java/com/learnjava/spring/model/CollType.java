package com.learnjava.spring.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollType {

    private String[] array;
    private List<String> list;
    private Map<String, String> map;
    private Set<String> set;

    private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "CollType{" +
                "array=" + Arrays.toString(array) +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", userList=" + userList +
                '}';
    }
}
