package com.aone.onlinefix.utils;


import com.aone.onlinefix.R;

/**
 * Created by abuzeid-ibrahim on 10/28/17.
 */

public enum ProblemState {
    Wait_Stores_response,//user sent the request
    Choose_Store,//user sent the request and there are stores response
    Store_Going_To_Fix, //users accept response
    Problem_Fixed,
    Problem_Not_Fixed; //user fix his phone




    public static int getText(String state){
        if (state.equals(Wait_Stores_response)){
                return R.string.wait_stores_response;
        }else if (state.equals(Choose_Store)){
            return R.string.choose_store_to_fix;

        }else if (state.equals(Store_Going_To_Fix)){
            return R.string.store_fixing_your_mobile;

        }else if (state.equals(Problem_Fixed)){
            return R.string.problem_fixed;

        }else if (state.equals(Problem_Not_Fixed)){
            return R.string.problem_not_fixed;

        }else {
            return R.string.app_name;

        }

    }

}
