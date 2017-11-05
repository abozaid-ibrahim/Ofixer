package com.aone.onlinefix.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aone.onlinefix.R;
import com.aone.onlinefix.callbacks.BaseCallback;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.FixRequestResponse;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.view.LoginActivity;
import com.aone.onlinefix.view.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


import static java.security.AccessController.getContext;

/**
 * Created by abuzeid on 10/19/17.
 */

public class DataSourceManager {
    public static final String TAG = "FirBase DATABAse";


    private DataSourceManager() {

    }

    public static final DataSourceManager instance = new DataSourceManager();


    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public void getAllProblems() {
        DatabaseReference myRef = database.getReference(FirDB.problems_table);

        final List<FixRequest> result = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> snapshotIterator = dataSnapshot.getChildren().iterator();
                while (snapshotIterator.hasNext()) {
                    DataSnapshot problem = snapshotIterator.next();
                    Object test = problem.getValue();
                    Iterator<DataSnapshot> test1 = problem.getChildren().iterator();
                    DataSnapshot test2 = test1.next();

                    //FixRequest fixRequest = problem.getValue(FixRequest.class);

                    FixRequest fixRequest = test2.getValue(FixRequest.class);


                    if (fixRequest.getState().equals(ProblemState.Wait_Stores_response.toString())) {

                        fixRequest.setUser_id(problem.getKey());
                        result.add(fixRequest);
                    }

                }
                EvBus.bus.post(new BaseCallback.FixRequestsCallback(result));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                error(error);
            }
        });
    }

    public void getMyProblems(final String storeid) {
        DatabaseReference myRef = database.getReference(FirDB.problems_table);

        final List<FixRequest> result = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> snapshotIterator = dataSnapshot.getChildren().iterator();
                while (snapshotIterator.hasNext()) {
                    DataSnapshot problem = snapshotIterator.next();
                    String userid = problem.getKey();

                    Iterator<DataSnapshot> childrensIterator = problem.getChildren().iterator();
                    DataSnapshot currentObject = childrensIterator.next();


                    FixRequest fixRequest = currentObject.getValue(FixRequest.class);
                    if (fixRequest.getState().equals(ProblemState.Store_Going_To_Fix.toString()) &&
                            fixRequest.getStore_id().equals(storeid)) {

                        fixRequest.setUser_id(userid);
                        result.add(fixRequest);
                    }
                }
                EvBus.bus.post(new BaseCallback.FixRequestsCallback(result));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                error(error);
            }
        });
    }

    public void getUser(final FixRequest request) {
        DatabaseReference myRef = database.getReference(FirDB.users_table);
        Query query = myRef.child(request.getUser_id());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ui.dismissLoading();

                if (dataSnapshot.exists()) {

                    Store store = dataSnapshot.getValue(Store.class);
                    EvBus.bus.post(store);
                } else {
                    System.err.println("???USER NOT FOUND");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                error(databaseError);
            }


        });


    }

    private void error(DatabaseError databaseError) {
        ui.show(ui.getContext(), "DataBase Error");

        System.err.println("???DATABASE ERROR " + databaseError.getMessage());

    }

    public void addResponse(FixRequestResponse response) {
        DatabaseReference myRef = database.getReference(FirDB.response_table);
        int randomInt = new Random().nextInt();
        String randomKey = String.valueOf(randomInt + System.currentTimeMillis());
        myRef.child(response.getUser_id()).child(response.getRequest_id()).child(randomKey).setValue(response);
    }

    public void addStore(final Context context, final Store store) {
        DatabaseReference myRef = database.getReference(FirDB.stores_table);


        myRef.child(store.getStore_id()).setValue(store,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        ui.dismissLoading();
                        if (databaseError != null) {
                            System.err.println("eror.........");
                            ui.show(ui.getContext(), R.string.db_error);

                        } else {

                            store.save();
                            EvBus.bus.post(store);

                        }
                    }
                });


    }


}

