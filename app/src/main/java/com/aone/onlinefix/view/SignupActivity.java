package com.aone.onlinefix.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.aone.onlinefix.R;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.EvBus;
import com.aone.onlinefix.utils.FirDB;
import com.aone.onlinefix.utils.app;
import com.aone.onlinefix.utils.ui;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.signup_mobile)
    EditText mobileField;

    @BindView(R.id.signup_pw)
    EditText passField;

    @BindView(R.id.signup_cpw)
    EditText cpassField;

    @BindView(R.id.signup_name)
    EditText nameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);


    }


    @Override
    protected void onStart() {
        super.onStart();
        EvBus.bus.register(this);
    }

    @Override
    protected void onStop() {
        EvBus.bus.unregister(this);
        super.onStop();
    }

    @OnClick(R.id.signup_submit)
    public void singup() {
        if (mobileField.getText().toString().isEmpty()) {
            ui.show(this, getString(R.string.enter_mobile));
            return;
        }
        if (passField.getText().toString().isEmpty()) {
            ui.show(this, getString(R.string.enter_pass));
            return;
        }


        ui.load(SignupActivity.this);
        final String id = app.md5(mobileField.getText().toString() + "_" + passField.getText().toString());


        Store store = new Store();
        store.setStore_id(id);
        store.setMobile(mobileField.getText().toString());
        store.setStore_name(nameField.getText().toString());
        store.setPassword(passField.getText().toString());
        DataSourceManager.instance.addStore(this,store);

    }
public void onEvent(Store store){
    ui.show(this,"Welcome to Ofix Community");
    startActivity(new Intent(this,MainActivity.class));
    finish();
}

}
