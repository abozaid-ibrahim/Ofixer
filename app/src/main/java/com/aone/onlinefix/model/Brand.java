package com.aone.onlinefix.model;

/**
 * Created by abuzeid on 10/24/17.
 */

public class Brand {
    private String id;
    private String name;

    public Brand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
