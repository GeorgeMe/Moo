package com.is.moo.api;

import com.is.moo.entities.MovieDetail;
import com.is.moo.entities.MoviesWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by George on 2016/7/20.
    */
    public interface MovieAPI {

    @GET("/tnfs/api/list")
    Call<MoviesWrapper> getPopularMoviesByPage(@Query("page") int page,@Query("rows") int rows);

    @GET("/tnfs/api/show")
    Call<MovieDetail> getMovieDetail(@Query("id") int id);

}
