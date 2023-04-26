package com.JNF.stream_functional;


public class UserDTO {
    private String name;
    private String group;
    private String contact;

    public UserDTO(User user) {
        this.setName(user.getLastName() + "." + user.getFirstName());
        this.setGroup(Utils.getGroup(user.getAge()));
        this.setContact(user.getEmail());
    }

    public UserDTO(String name, String group, String contact) {
        this.name = name;
        this.group = group;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "UserDTO [contact=" + contact + ", group=" + group + ", name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
