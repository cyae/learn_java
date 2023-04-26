package com.Ex.DAO;

import org.junit.Test;

public class DAOTest {
    DAO<User> dao = new DAO<>();

    @Test
    public void testDelete() {
        dao.delete("123");
    }

    @Test
    public void testGet() {
        dao.get("123");
    }

    @Test
    public void testList() {
        dao.list();
    }

    @Test
    public void testSave() {
        User user = new User(1, 14, "wick");
        dao.save("123", user);
    }

    @Test
    public void testUpdate() {
        User user = new User(2, 12, "hack");
        dao.update("123", user);
    }
}
