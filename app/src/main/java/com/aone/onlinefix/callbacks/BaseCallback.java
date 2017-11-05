package com.aone.onlinefix.callbacks;

import com.aone.onlinefix.model.FixRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abuzeid-ibrahim on 11/5/17.
 */

public class BaseCallback {
    public static class FixRequestsCallback{
        public  List<FixRequest> data = new ArrayList<>();

        public FixRequestsCallback(List<FixRequest> result) {
            this.data = result;
        }
    }
}
