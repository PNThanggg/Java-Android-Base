package com.example.hit.pnt.listrealtimedatabase;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Integer id;
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("name", name);

        return mapResult;
    }
}
