package com.aone.onlinefix.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by abuzeid on 10/19/17.
 */


@IgnoreExtraProperties
public class FixRequestResponse   {

    private String store_name;
    private String response_id;
    private String store_id;
    private String request_id;
    private String user_id;

    private String home_cost;
    private String store_cost;

    private String duration;
    private String guarntee;
    private String place;
    private String date;
    private String latitude;
    private String longitude;
    private String devicetype;
    private String address;
    private String mobile;


    public FixRequestResponse() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public FixRequestResponse(String store_name, String response_id, String store_id, String request_id,
                              String cost, String duration, String guarntee, String place,
                              String date, String latitude, String longitude, String devicetype,
                              String address, String mobile, String uid, String home_cost, String store_cost) {
        this.user_id = uid;
        this.store_name = store_name;
        this.response_id = response_id;
        this.store_id = store_id;
        this.request_id = request_id;
        this.duration = duration;
        this.guarntee = guarntee;
        this.place = place;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.devicetype = devicetype;
        this.address = address;
        this.mobile = mobile;
        this.home_cost = home_cost;
        this.store_cost = store_cost;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }



    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGuarntee() {
        return guarntee;
    }

    public void setGuarntee(String guarntee) {
        this.guarntee = guarntee;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Object toMap() {
        return null;
    }

    public String getStore_cost() {
        return store_cost;
    }

    public void setStore_cost(String store_cost) {
        this.store_cost = store_cost;
    }

    public String getHome_cost() {
        return home_cost;
    }

    public void setHome_cost(String home_cost) {
        this.home_cost = home_cost;
    }
}