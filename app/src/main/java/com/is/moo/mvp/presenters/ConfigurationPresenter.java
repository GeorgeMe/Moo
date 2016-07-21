package com.is.moo.mvp.presenters;

import android.content.Context;

import com.is.moo.Constants;
import com.is.moo.entities.ConfigurationResponse;
import com.is.moo.mvp.interactor.ConfigurationInteractor;
import com.is.moo.mvp.listeners.BaseSingleLoadedListener;
import com.is.moo.mvp.views.ConfigurationView;

/**
 * Created by George on 2016/7/21.
 */
public class ConfigurationPresenter implements BaseSingleLoadedListener<ConfigurationResponse> {
    private Context context;
    private ConfigurationView configurationView;
    private ConfigurationInteractor configurationInteractor;

    public ConfigurationPresenter(Context context, ConfigurationView configurationView) {
        this.context = context;
        this.configurationView = configurationView;
        configurationInteractor=new ConfigurationInteractor(context,this);
    }

    public void getConfiguration(){
        configurationInteractor.getCommonSingleData(null);
    }
    @Override
    public void onSuccess(ConfigurationResponse data) {
       Constants.BASIC_STATIC_URL= data.getImages().getBase_url();
    }

    @Override
    public void onFailure(String msg) {

    }
}
