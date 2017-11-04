package com.aone.onlinefix.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aone.onlinefix.R;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.presenter.ProblemViewPresenter;
import com.aone.onlinefix.view.FixRequestsFragment;

import java.util.List;


public class FixRequestsRecyclerAdapter extends RecyclerView.Adapter<FixRequestsRecyclerAdapter.ViewHolder> {

    private final List<FixRequest> mValues;
    private final FixRequestsFragment.OnFixRequestInteractionListener mListener;

    public FixRequestsRecyclerAdapter(List<FixRequest> items, FixRequestsFragment.OnFixRequestInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fix_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.presenter.updateUI(holder.mItem);
        holder.fixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onFixRequestAcceptClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        FixRequest mItem;
        Button fixBtn;
        ProblemViewPresenter presenter = new ProblemViewPresenter();

        ViewHolder(View view) {
            super(view);
            mView = view;

            fixBtn = view.findViewById(R.id.fix_request_submit);
            presenter.bind(view);
        }

    }
}
