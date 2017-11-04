package com.aone.onlinefix.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.aone.onlinefix.R;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.model.UserModel;
import com.aone.onlinefix.utils.FirDB;
import com.aone.onlinefix.utils.app;
import com.aone.onlinefix.utils.ui;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.login_mobile)
    EditText mobileField;

    @BindView(R.id.login_pw)
    EditText passField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.login_submit)
    public void login() {

        if (mobileField.getText().toString().isEmpty()) {
            ui.show(this, getString(R.string.enter_mobile));
            return;
        }
        if (passField.getText().toString().isEmpty()) {
            ui.show(this, getString(R.string.enter_pass));
            return;
        }


        ui.load(LoginActivity.this);
        final String id = app.md5(mobileField.getText().toString() + "_" + passField.getText().toString());
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(FirDB.stores_table);
        Query query = myRef.child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ui.dismissLoading();

                if (dataSnapshot.exists()) {


                    // dataSnapshot is the "issue" node with all children with id 0
//                    for (DataSnapshot stores : dataSnapshot.getChildren()) {


                    Store store = dataSnapshot.getValue(Store.class);
                    new UserModel().save(LoginActivity.this, store);

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    ui.show(LoginActivity.this, R.string.store_not_registered);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ui.show(LoginActivity.this, "DataBase Error");

            }
        });


    }


    @OnClick(R.id.login_create_user)
    public void newUser() {
        startActivity(new Intent(this, SignupActivity.class));

    }

}
