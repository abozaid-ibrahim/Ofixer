package com.aone.onlinefix.presenter;

/**
 * Created by abuzeid on 10/19/17.
 */

public interface DataSource {
    public  <T>  boolean commit(Class<T> model);

}
