package com.aone.onlinefix.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abuzeid on 10/26/17.
 */

public class UserModel {

    private String name, id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void save(Context context,Store store) {
        SharedPreferences.Editor edit = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE).edit();
        edit.putString("id", store.getStore_id());
        edit.putString("name", store.getStore_name());
        edit.commit();
    }

    public static UserModel getCurrentUser(Context context) {
        SharedPreferences edit = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        if (!edit.contains("id")) {
            return null;
        }
        UserModel user = new UserModel();
        user.id = edit.getString("id", "");
        user.name = edit.getString("name", "");
        return user;

    }

}
