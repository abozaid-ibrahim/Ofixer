package com.aone.onlinefix.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by abuzeid on 10/19/17.
 */


@IgnoreExtraProperties
public class FixRequest {

    private String username;
    private String id;
    private String user_id;
    private String request_id;
    private String email;
    private String store_id;

    private String brand_id;
    private String brand;
    private String model;
    private String date;
    private String state;
    private String problem;
    private String desc;
    private String devicetype;
    private String notes;
    private String place;



    public FixRequest() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public FixRequest(String username, String id, String user_id, String request_id, String email, String store_id, String brand_id, String brand,
                      String model, String date, String state, String problem, String desc, String devicetype, String notes, String place) {
        this.username = username;
        this.id = id;
        this.user_id = user_id;
        this.request_id = request_id;
        this.email = email;
        this.store_id = store_id;
        this.brand_id = brand_id;
        this.brand = brand;
        this.model = model;
        this.date = date;
        this.state = state;
        this.problem = problem;
        this.desc = desc;
        this.devicetype = devicetype;
        this.notes = notes;
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}