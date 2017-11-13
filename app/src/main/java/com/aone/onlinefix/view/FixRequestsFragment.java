package com.aone.onlinefix.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.aone.onlinefix.R;
import com.aone.onlinefix.adapters.FixRequestsRecyclerAdapter;
import com.aone.onlinefix.callbacks.BaseCallback;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.presenter.FixRequestPresenter;
import com.aone.onlinefix.presenter.FixRequestViewPresenter;
import com.aone.onlinefix.utils.EvBus;
import com.aone.onlinefix.utils.FixPlace;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixRequestsFragment extends BaseFragment implements FixRequestViewPresenter {


    @BindView(R.id.list_fix_requests)
    RecyclerView recyclerView;


    FixRequestPresenter presenter;

    public FixRequestsFragment() {
    }

    public static FixRequestsFragment newInstance() {
        FixRequestsFragment fragment = new FixRequestsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fix_requests, container, false);
        ButterKnife.bind(this, view);
        presenter = new FixRequestPresenter(this);
        presenter.getRequests();
        return view;


    }

    @Override
    public void onStart() {
        super.onStart();
        EvBus.bus.register(this);
    }

    @Override
    public void onStop() {
        EvBus.bus.unregister(this);
        super.onStop();
    }


    @Subscribe
    public void onEvent(BaseCallback.FixRequestsCallback event) {
        recyclerView.setAdapter(new FixRequestsRecyclerAdapter(event.data, new OnFixRequestInteractionListener() {
            @Override
            public void onFixRequestAcceptClicked(FixRequest uri) {
                showFixingDialog(uri);

            }
        }));

    }

    private void showFixingDialog(final FixRequest fixRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //builder.setMessage("Fix Phone")

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fix_request_response, null);
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
                (getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.guarntee_array));
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

                        presenter.sendResponse(fixRequest, homeCostEt.getText().toString(),
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

    public interface OnFixRequestInteractionListener {
        void onFixRequestAcceptClicked(FixRequest uri);
    }
}
