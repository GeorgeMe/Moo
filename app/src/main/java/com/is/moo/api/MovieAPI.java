package com.is.moo.api;

import com.is.moo.entities.ConfigurationResponse;
import com.is.moo.entities.ImagesWrapper;
import com.is.moo.entities.MovieDetail;
import com.is.moo.entities.MoviesWrapper;
import com.is.moo.entities.ReviewsWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by George on 2016/7/20.
    */
    public interface MovieAPI {
    //图片基本信息
    @GET("/3/configuration")
    Call<ConfigurationResponse> getConfiguration();

    @GET("/3/movie/popular")
    Call<MoviesWrapper> getPopularMoviesByPage(@Query("apikey") String apikey,@Query("page") String page);

    @GET("/3/movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") String id);

    @GET("/3/movie/{id}/reviews")
    Call<ReviewsWrapper> getReviews(@Path("id") String id);

    @GET("/3/movie/{id}/images")
    Call<ImagesWrapper> getImages(@Path("id") String movieId);
}
