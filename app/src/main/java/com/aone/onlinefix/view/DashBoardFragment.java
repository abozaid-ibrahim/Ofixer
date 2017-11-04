package com.aone.onlinefix.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aone.onlinefix.R;
import com.aone.onlinefix.adapters.DashboardRecyclerAdapter;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.UserModel;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.EvBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashBoardFragment extends BaseFragment {

    @BindView(R.id.list_dashboard)
    RecyclerView recyclerView ;

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
        DataSourceManager.instance.getMyProblems(UserModel.getCurrentUser(getContext()).getId());
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


    public void onEvent(List<FixRequest> event) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new DashboardRecyclerAdapter(event,null));

    }






}
