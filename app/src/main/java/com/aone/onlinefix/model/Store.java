package com.aone.onlinefix.model;

import java.util.List;

/**
 * Created by abuzeid on 10/23/17.
 */

public class Store {



    private String store_name;
    private String store_id;

    private String place;
    private String latitude;
    private String longitude;
    private String address;
    private String mobile;
    private String phone;
    private String email;
    private String password;
    private String username;
    private List<DeviceModel> modelCanFix;

    public Store() {
    }

    public Store(String store_name, String store_id, String place, String latitude, String longitude, String address, String mobile,
                 String phone, String email, String password, String username, List<DeviceModel> modelCanFix) {
        this.store_name = store_name;
        this.store_id = store_id;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.username = username;
        this.modelCanFix = modelCanFix;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<DeviceModel> getModelCanFix() {
        return modelCanFix;
    }

    public void setModelCanFix(List<DeviceModel> modelCanFix) {
        this.modelCanFix = modelCanFix;
    }
}
