package com.example.hit.pnt.retrofit;

public class Job {
    private int id;
    private String job;

    public Job() {
    }

    public Job(int id, String job) {
        this.id = id;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
