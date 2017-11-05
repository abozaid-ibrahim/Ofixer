package com.aone.onlinefix.adapters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.aone.onlinefix.R;
import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.FixRequestResponse;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.presenter.ProblemViewPresenter;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.app;
import com.aone.onlinefix.view.FixRequestsFragment;

import java.sql.Time;
import java.util.List;


public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.ViewHolder> {

    private final List<FixRequest> mValues;
    private final FixRequestsFragment.OnFixRequestInteractionListener mListener;

    public DashboardRecyclerAdapter(List<FixRequest> items, FixRequestsFragment.OnFixRequestInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.presenter.updateUI(holder.mItem);
//holder.clientPhoneTv.setText(holder.mItem.ge);
        holder.clientPhoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    callAction(holder.mItem);


            }
        });
        holder.fixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onFixRequestAcceptClicked(holder.mItem);
                }
            }
        });
    }

    public  void onEvent(Store user){
//        app.makeCall(user.getName());

    }
    private void callAction(FixRequest mItem) {
DataSourceManager.instance.getUser(mItem);

    }



//
//    private void showFixingDialog(final FixRequest uri) {
//        AlertDialog.Builder builder = new AlertDialog.Builder();
//        //builder.setMessage("Fix Phone")
//
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fix_request_response, null);
//        final EditText costField = (EditText) view.findViewById(R.id.dialog_fix_cost_tf);
//        final EditText durationField = (EditText) view.findViewById(R.id.dialog_fix_duration_tf);
//        final EditText guarnteeField = (EditText) view.findViewById(R.id.dialog_guarantee_duration_tf);
//        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.dialog_radio_group);
//        final CheckBox acceptBox = (CheckBox) view.findViewById(R.id.dialog_accept_checkBox);
//
//        builder.setView(view)
//
//                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                })
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//
//
//        // Create the AlertDialog object and return it
//        builder.create().show();
//    }
//




    @Override
    public int getItemCount() {
        return mValues.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
          View mView;
          Button clientPhoneTv;
         FixRequest mItem;
         Button fixBtn;
         ProblemViewPresenter presenter = new ProblemViewPresenter();

         ViewHolder(View view) {
            super(view);
            mView = view;

            fixBtn = (Button) view.findViewById(R.id.fix_request_submit);
             clientPhoneTv = view.findViewById(R.id.item_db_call);

             presenter.bind(view);

         }

    }
}
