package com.is.moo.mvp.listeners;

import org.json.JSONObject;

/**
 * Created by George on 2016/5/21.
 */
public interface CommonListInteractor {
    void getCommonListData(int event, JSONObject json);
}