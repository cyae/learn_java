package com.learnjava.spring.demo_jdbc.model;

public class Book {

    private String id;
    private String username;
    private String password;
    private String age;
    private String sex;
    private String email;

    public Book() {
    }

    public Book(String id, String username, String password, String age, String sex, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getAge() {
        return age;
    }



    public void setAge(String age) {
        this.age = age;
    }



    public String getSex() {
        return sex;
    }



    public void setSex(String sex) {
        this.sex = sex;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "Book [age=" + age + ", email=" + email + ", id=" + id + ", password=" + password + ", sex=" + sex
                + ", username=" + username + "]";
    }

    
}
