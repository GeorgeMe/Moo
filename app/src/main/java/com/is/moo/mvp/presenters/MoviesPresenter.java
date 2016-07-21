package com.is.moo.mvp.presenters;
import android.content.Context;

import com.is.moo.mvp.interactor.MoviesWrapperInteractor;
import com.is.moo.mvp.listeners.BaseSingleLoadedListener;
import com.is.moo.mvp.views.MoviesView;
import com.is.moo.Constants;
import com.is.moo.entities.MoviesWrapper;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by George on 2015/8/13.
 */
public class MoviesPresenter implements BaseSingleLoadedListener<MoviesWrapper> {

    private MoviesWrapperInteractor moviesWrapperInteractor;
    private Context context;
    private MoviesView mMoviesView;

    public MoviesPresenter(Context context,MoviesView mMoviesView) {
        this.context=context;
        this.mMoviesView = mMoviesView;
        moviesWrapperInteractor=new MoviesWrapperInteractor(context,this);
    }

    public void onPopularMoviesReceived(MoviesWrapper moviesWrapper) {
        mMoviesView.hideLoading();
        mMoviesView.hideActionLabel();
        if (mMoviesView.isTheListEmpty()) {
            mMoviesView.showMovies(moviesWrapper.getResults());
        } else {
            mMoviesView.appendMovies(moviesWrapper.getResults());
        }
    }

    public void getPopularMoviesByPage(String page){
        mMoviesView.showLoading();
        mMoviesView.showLoadingLabel();
        JSONObject json=new JSONObject();
        try {
            json.put("apikey",Constants.API_KEY);
            json.put("page",page);
        }catch (JSONException j){
            mMoviesView.hideLoading();
            mMoviesView.hideActionLabel();
            //抛异常处理
        }
        moviesWrapperInteractor.getCommonSingleData(json);
    }

    @Subscribe
    public void onConfigurationFinished (String baseImageUrl) {
        Constants.BASIC_STATIC_URL = baseImageUrl;
    }

    @Override
    public void onSuccess(MoviesWrapper moviesWrapper) {
        mMoviesView.hideLoading();
        mMoviesView.hideActionLabel();
        if (mMoviesView.isTheListEmpty()) {
            mMoviesView.showMovies(moviesWrapper.getResults());
        } else {
            mMoviesView.appendMovies(moviesWrapper.getResults());
        }
    }

    @Override
    public void onFailure(String msg) {

    }
}