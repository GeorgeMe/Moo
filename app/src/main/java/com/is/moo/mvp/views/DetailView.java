package com.is.moo.mvp.views;
import android.graphics.Bitmap;
import com.is.moo.entities.Review;
import java.util.List;
/**
 * Created by George on 2015/8/13.
 */
public interface DetailView extends MVPView {

    void showFilmCover(Bitmap bitmap);

    public void setName(String title);

    public void setDescription(String description);

    public void setHomepage(String homepage);

    public void setCompanies(String companies);

    public void setTagline(String tagline);

    public void finish(String cause);

    public void showConfirmationView();

    public void animateConfirmationView();

    public void startClosingConfirmationView();

    public void showReviews(List<Review> results);

    void showMovieImage(String url);
}
