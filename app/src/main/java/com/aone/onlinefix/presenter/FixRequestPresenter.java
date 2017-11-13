package com.aone.onlinefix.presenter;

import com.aone.onlinefix.model.FixRequest;
import com.aone.onlinefix.model.FixRequestResponse;
import com.aone.onlinefix.model.Store;
import com.aone.onlinefix.utils.DataSourceManager;
import com.aone.onlinefix.utils.app;

/**
 * Created by abuzeid-ibrahim on 11/13/17.
 */

public class FixRequestPresenter {


    private FixRequestViewPresenter viewPresenter;

    public FixRequestPresenter(FixRequestViewPresenter viewPresenter) {
        this.viewPresenter = viewPresenter;
    }


    public void sendResponse(FixRequest uri, String hcost, String duration, String gar, String scost) {


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


    public void getRequests() {
        DataSourceManager.instance.getAllProblems();
    }
}
