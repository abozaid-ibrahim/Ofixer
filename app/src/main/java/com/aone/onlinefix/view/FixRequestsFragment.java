package com.aone.onlinefix.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aone.onlinefix.R;
import com.aone.onlinefix.adapters.FixRequestsRecyclerAdapter;
import com.aone.onlinefix.callbacks.BaseCallback;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.EvBus;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixRequestsFragment extends BaseFragment {


     @BindView(R.id.list_fix_requests)
    RecyclerView recyclerView;

    private OnFixRequestInteractionListener mListener;

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
        DataSourceManager.instance.getAllProblems();
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
        recyclerView.setAdapter(new FixRequestsRecyclerAdapter(event.data, mListener));

    }



    public void onButtonPressed(FixRequest uri) {
        if (mListener != null) {
            mListener.onFixRequestAcceptClicked(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFixRequestInteractionListener) {
            mListener = (OnFixRequestInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFixRequestInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFixRequestInteractionListener {
        void onFixRequestAcceptClicked(FixRequest uri);
    }
}
