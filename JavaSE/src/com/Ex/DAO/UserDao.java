package com.Ex.DAO;

import java.util.Iterator;
import java.util.List;

public class UserDao extends DAO<User> {
    // 可以UserDao继承DAO，并且指定泛型<User>直接测试
    public static void main(String[] args) {
        UserDao userDAO = new UserDao();
        userDAO.save("001", new User(001, 13, "jack1"));
        userDAO.save("002", new User(002, 15, "jack2"));
        userDAO.save("003", new User(003, 16, "jack3"));
        userDAO.save("004", new User(004, 18, "jack4"));

        List<User> list = userDAO.list();
        Iterator<User> it = list.iterator();
        while (it.hasNext()) {
            User user = it.next();
            System.out.println(user);
        }

        User user = userDAO.get("002");
        System.out.println(user);
        userDAO.get("005");
    }
}
