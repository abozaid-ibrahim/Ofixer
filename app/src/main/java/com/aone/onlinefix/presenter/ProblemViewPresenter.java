package com.aone.onlinefix.presenter;

import android.view.View;
import android.widget.TextView;

import com.aone.onlinefix.R;
import com.aone.onlinefix.model.FixRequest;

/**
 * Created by abuzeid on 10/19/17.
 */
//get data from firebase , update ui
public class ProblemViewPresenter {

    TextView nameTv, brandTv, problemTv, dateTv, descTv;


    public ProblemViewPresenter() {
    }

    public void bind(View view) {
        nameTv = view.findViewById(R.id.fix_request_username);
        brandTv = view.findViewById(R.id.fix_request_brand);
        problemTv = view.findViewById(R.id.fix_request_problem);
        descTv = view.findViewById(R.id.fix_request_desc);
        dateTv = view.findViewById(R.id.fix_request_date);

    }

    public void updateUI(FixRequest request) {
        nameTv.setText(request.getUsername());
        brandTv.setText(request.getBrand()+" - "+request.getModel());
        problemTv.setText(request.getProblem());
        descTv.setText(request.getDesc());
        dateTv.setText(request.getDate());
    }

}
