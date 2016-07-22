package com.is.moo.mvp.views;
import com.is.moo.entities.MovieDetail;
/**
 * Created by George on 2015/8/13.
 */
public interface DetailView extends MVPView {

    public void showMovieDetail(MovieDetail result);

}
