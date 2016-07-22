package com.is.moo.mvp.interactor;

import android.content.Context;

import com.is.moo.entities.MoviesWrapper;
import com.is.moo.base.Retrofit2Movie;
import com.is.moo.mvp.listeners.BaseSingleLoadedListener;
import com.is.moo.mvp.listeners.CommonSingleInteractor;
import com.is.moo.utils.TLog;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by George on 2016/7/20.
 */
public class MoviesWrapperInteractor  extends Retrofit2Movie implements CommonSingleInteractor {

    private BaseSingleLoadedListener<MoviesWrapper> loadedListener;

    public MoviesWrapperInteractor(Context context, BaseSingleLoadedListener<MoviesWrapper> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonSingleData(JSONObject json) {
        Call<MoviesWrapper> call = movieAPI.getPopularMoviesByPage(json.optInt("page"),json.optInt("rows"));
        call.enqueue(new Callback<MoviesWrapper>() {
            @Override
            public void onResponse(Call<MoviesWrapper> call, Response<MoviesWrapper> response) {
                loadedListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MoviesWrapper> call, Throwable t) {
                loadedListener.onFailure(t.getMessage());
            }
        });
    }
}
