package com.is.moo.mvp.interactor;

import android.content.Context;

import com.is.moo.entities.ReviewsWrapper;
import com.is.moo.base.Retrofit2Movie;
import com.is.moo.mvp.listeners.BaseSingleLoadedListener;
import com.is.moo.mvp.listeners.CommonSingleInteractor;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by George on 2016/7/21.
 */
public class ReviewsWrapperInteractor extends Retrofit2Movie implements CommonSingleInteractor {
    private BaseSingleLoadedListener<ReviewsWrapper> loadedListener;

    public ReviewsWrapperInteractor(Context context, BaseSingleLoadedListener<ReviewsWrapper> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonSingleData(JSONObject json) {
        Call<ReviewsWrapper> call=movieAPI.getReviews(json.optString("id"));
        call.enqueue(new Callback<ReviewsWrapper>() {
            @Override
            public void onResponse(Call<ReviewsWrapper> call, Response<ReviewsWrapper> response) {
                loadedListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ReviewsWrapper> call, Throwable t) {
                loadedListener.onFailure(t.getMessage());
            }
        });
    }
}
