package com.example.hit.pnt.realtimedatabasefirebase;

public class User {
    private int id;
    private String name;
    private Job job;
    private String address;

    public User() {
    }

    public User(int id, String name, Job job, String address) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job=" + job +
                ", address='" + address + '\'' +
                '}';
    }
}
