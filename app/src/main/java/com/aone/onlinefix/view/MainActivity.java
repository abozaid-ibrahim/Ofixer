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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.aone.onlinefix.R;
import com.aone.onlinefix.dummy.DummyContent;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.FixRequestResponse;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.app;

import java.sql.Time;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements FixRequestsFragment.OnFixRequestInteractionListener {

//    @BindView(R.id.fragments_container)
//    private View contianer;


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

    private void setNavigationMenu() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    /*listener*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return onItemSelected(item.getItemId());
        }

    };

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

    private void showFixingDialog(final FixRequest uri) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setMessage("Fix Phone")

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fix_request_response, null);
        final EditText costField = (EditText) view.findViewById(R.id.dialog_fix_cost_tf);
        final EditText hoursField = (EditText) view.findViewById(R.id.dialog_fix_hours_tf);
        final EditText minutesField = (EditText) view.findViewById(R.id.dialog_fix_min_tf);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.dialog_radio_group);
        final Spinner guarntee =  view.findViewById(R.id.guarntee_spinner);



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

                        sendRequest(uri,costField.getText().toString(),
                                hoursField.getText().toString() + ":" + minutesField.getText().toString(),
                                guarntee.getSelectedItem().toString(),
                                radioGroup.getCheckedRadioButtonId());
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

    private void sendRequest(FixRequest uri, String cost, String duration, String gar, int checkedRadioButtonId) {
String place = "";

switch (checkedRadioButtonId){
    case R.id.radioHome:
        place = "Home";
        break;
    case R.id.radioStore:
        place = "Store";
        break;
    case R.id.radioBoth:
        place = "Both";
        break;
    default:
        break;
}
        FixRequestResponse response = new FixRequestResponse();

        response.setUser_id(uri.getUser_id());
        response.setRequest_id(uri.getRequest_id());
        response.setCost(cost);
        response.setDate(new Time(System.currentTimeMillis()).toString());
        response.setDevicetype("android");
        response.setGuarntee(gar);
        response.setPlace(place);
        DataSourceManager.instance.addResponse(response);
    }


}
