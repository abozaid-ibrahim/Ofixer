package com.aone.onlinefix.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.aone.onlinefix.R;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.FixRequestResponse;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.FixPlace;
import com.aone.onlinefix.utils.app;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements FixRequestsFragment.OnFixRequestInteractionListener {

//    @BindView(R.id.fragments_container)
//    private View contianer;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return onItemSelected(item.getItemId());
        }

    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setNavigationMenu();
        if (savedInstanceState == null) {
            onItemSelected(R.id.navigation_home);
        }


    }



    /*listener*/

    private void setNavigationMenu() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean onItemSelected(int item) {
        Fragment fragment = null;

        switch (item) {
            case R.id.navigation_home:
                fragment = FixRequestsFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, fragment).commit();
                setTitle(R.string.fix_requests);

                return true;
            case R.id.navigation_dashboard:
                fragment = DashBoardFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, fragment).commit();
                setTitle(R.string.title_dashboard);

                return true;

        }


        return false;
    }



/*Fix requests Fragment*/

    @Override
    public void onFixRequestAcceptClicked(FixRequest uri) {
        this.showFixingDialog(uri);


    }

    private void showFixingDialog(final FixRequest fixRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setMessage("Fix Phone")

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fix_request_response, null);
        final EditText hoursField = (EditText) view.findViewById(R.id.dialog_fix_hours_tf);
        final EditText minutesField = (EditText) view.findViewById(R.id.dialog_fix_min_tf);
        final Spinner guarntee = view.findViewById(R.id.guarntee_spinner);
        final View homeCostLayout = view.findViewById(R.id.dialog_fix_home_cost_layout);
        final View storeCostLayout = view.findViewById(R.id.dialog_fix_store_cost_layout);
        final EditText storeCostEt = view.findViewById(R.id.dialog_fix_store_cost);
        final EditText homeCostEt = view.findViewById(R.id.dialog_fix_home_cost);
        if (fixRequest.getPlace().equals(FixPlace.home.toString())) {
            storeCostLayout.setVisibility(View.GONE);
        } else if (fixRequest.getPlace().equals(FixPlace.store.toString())) {
            homeCostLayout.setVisibility(View.GONE);
        } else if (fixRequest.getPlace().equals(FixPlace.all.toString())) {
            homeCostLayout.setVisibility(View.VISIBLE);
            storeCostLayout.setVisibility(View.VISIBLE);
        }


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.guarntee_array));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        guarntee.setAdapter(spinnerArrayAdapter);
        guarntee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(view)

                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        sendResponse(fixRequest, homeCostEt.getText().toString(),
                                hoursField.getText().toString() + ":" + minutesField.getText().toString(),
                                guarntee.getSelectedItem().toString(),
                                storeCostEt.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });


        // Create the AlertDialog object and return it
        builder.create().show();
    }

    private void sendResponse(FixRequest uri, String hcost, String duration, String gar, String scost) {


        FixRequestResponse response = new FixRequestResponse();

        response.setUser_id(uri.getUser_id());
        response.setRequest_id(uri.getRequest_id());
        response.setHome_cost(hcost);
        response.setStore_cost(scost);
        response.setDuration(duration);
        response.setDate(app.timeNow());
        response.setDevicetype("android");
        response.setGuarntee(gar);
        response.setStore_id(Store.getCurrent().getStore_id());
        response.setStore_name(Store.getCurrent().getStore_name());
        response.setAddress(Store.getCurrent().getAddress());

        DataSourceManager.instance.addResponse(response);
    }


}
