package com.aone.onlinefix.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aone.onlinefix.R;
import com.aone.onlinefix.adapters.DashboardRecyclerAdapter;
import com.aone.onlinefix.callbacks.BaseCallback;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.EvBus;
import com.aone.onlinefix.utils.ui;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashBoardFragment extends BaseFragment {

    private static final int CALL_PERMISSION = 98;
    @BindView(R.id.list_dashboard)
    RecyclerView recyclerView;
    String mobile = null;

    public DashBoardFragment() {
    }

    public static DashBoardFragment newInstance() {
        DashBoardFragment fragment = new DashBoardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        if (Store.getCurrent() != null) {
            DataSourceManager.instance.getMyProblems(Store.getCurrent().getStore_id());
        } else {

        }

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
        recyclerView.setAdapter(new DashboardRecyclerAdapter(event.data, new FixRequestsFragment.OnFixRequestInteractionListener() {

            @Override
            public void onFixRequestAcceptClicked(FixRequest uri) {

            }
        }));

    }

    private void callAction(String mobile) {
//DataSourceManager.instance.getUser(mItem);

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mobile));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ui.show(getActivity(), "Grant Permission to make a call");
            this.mobile = mobile;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION);

            return;
        } else {
            ui.getContext().startActivity(intent);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION) {
            if (mobile != null)
                callAction(mobile);

        }
    }
}
