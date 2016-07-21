package com.is.moo.mvp.presenters;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.is.moo.mvp.interactor.ReviewsWrapperInteractor;
import com.is.moo.mvp.listeners.BaseSingleLoadedListener;
import com.is.moo.mvp.views.DetailView;
import com.is.moo.views.activities.MoviesActivity;
import com.is.moo.Constants;
import com.is.moo.entities.ImagesWrapper;
import com.is.moo.entities.MovieDetail;
import com.is.moo.entities.Production_companies;
import com.is.moo.entities.ReviewsWrapper;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

/**
 * Created by George on 2015/8/13.
 */
public class MovieDetailPresenter implements BaseSingleLoadedListener<ReviewsWrapper> {

    private DetailView mMovieDetailView;
    private Context context;
    private ReviewsWrapperInteractor reviewsWrapperInteractor;
    public MovieDetailPresenter(DetailView mMovieDetailView, Context context) {
        this.mMovieDetailView = mMovieDetailView;
        this.context = context;
        reviewsWrapperInteractor=new ReviewsWrapperInteractor(context,this);
        mMovieDetailView.showFilmCover(MoviesActivity.sPhotoCache.get(0));
    }

    public void getReviews(String id){
        JSONObject json=new JSONObject();
        try {
            json.put("id",id);
        }catch (JSONException j){

        }
        reviewsWrapperInteractor.getCommonSingleData(json);
    }

    public void showDescription(String description) {

        mMovieDetailView.setDescription(description);
    }

    public void showTagline(String tagLine) {

        mMovieDetailView.setTagline(tagLine);
    }

    public void showTitle(String title) {

        mMovieDetailView.setName(title);
    }

    public void showCompanies(List<Production_companies> companies) {

        String companiesString = "";

        for (int i = 0; i < companies.size(); i++) {

            Production_companies company = companies.get(i);
            companiesString += company.getName();

            if (i != companies.size() -1)
                companiesString += ", ";
        }

        if (!companies.isEmpty())
            mMovieDetailView.setCompanies(companiesString);
    }

    @Subscribe
    public void onDetailInformationReceived(MovieDetail response) {

        showDescription(response.getOverview());
        showTitle(response.getTitle());
        showTagline(response.getTagline());
        showCompanies(response.getProduction_companies());
        showHomepage(response.getHomepage());
        showFilmImage(response.getMovieImagesList());
    }

    private void showFilmImage(List<ImagesWrapper.MovieImage> movieImagesList) {

        if (movieImagesList != null && movieImagesList.size() > 0) {

            int randomIndex = new Random().nextInt(movieImagesList.size());
            Log.d("[DEBUG]", "MovieDetailPresenter showFilmImage - Random index: "+randomIndex);

            mMovieDetailView.showMovieImage (Constants.BASIC_STATIC_URL +movieImagesList.get(randomIndex).getFile_path());
        }
    }

    @Subscribe
    public void onReviewsReceived (final ReviewsWrapper reviewsWrapper) {

        // Wait 300 milliseconds to ensure that Palette generates the colors
        // before show the reviews
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (reviewsWrapper.getResults().size() > 0)
                    mMovieDetailView.showReviews(reviewsWrapper.getResults());
            }
        }, 300);

    }

    public void showHomepage(String homepage) {

        if (!TextUtils.isEmpty(homepage))
            mMovieDetailView.setHomepage(homepage);
    }

    @Override
    public void onSuccess(ReviewsWrapper data) {
        mMovieDetailView.showReviews(data.getResults());
    }

    @Override
    public void onFailure(String msg) {

    }
}
