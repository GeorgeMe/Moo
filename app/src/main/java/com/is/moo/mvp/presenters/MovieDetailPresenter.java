package com.is.moo.mvp.presenters;
import android.content.Context;

import com.is.moo.entities.MovieDetail;
import com.is.moo.mvp.interactor.MovieDetailInteractor;
import com.is.moo.mvp.listeners.BaseSingleLoadedListener;
import com.is.moo.mvp.views.DetailView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by George on 2015/8/13.
 */
public class MovieDetailPresenter implements BaseSingleLoadedListener<MovieDetail> {

    private DetailView mMovieDetailView;
    private Context context;
    private MovieDetailInteractor reviewsWrapperInteractor;
    public MovieDetailPresenter(DetailView mMovieDetailView, Context context) {
        this.mMovieDetailView = mMovieDetailView;
        this.context = context;
        reviewsWrapperInteractor=new MovieDetailInteractor(context,this);
    }

    public void getMovieDetail(int id){
        JSONObject json=new JSONObject();
        try {
            json.put("id",id);
        }catch (JSONException j){

        }
        reviewsWrapperInteractor.getCommonSingleData(json);
    }


    @Override
    public void onSuccess(MovieDetail data) {
        mMovieDetailView.showMovieDetail(data);
    }

    @Override
    public void onFailure(String msg) {

    }
}
