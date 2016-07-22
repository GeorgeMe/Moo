package com.is.moo.views.activities;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.is.moo.R;
import com.is.moo.entities.Movie;
import com.is.moo.entities.MoviesWrapper;
import com.is.moo.mvp.presenters.MoviesPresenter;
import com.is.moo.mvp.views.MoviesView;
import com.is.moo.utils.RecyclerInsetsDecoration;
import com.is.moo.utils.RecyclerViewClickListener;
import com.is.moo.views.adapters.MoviesAdapter;
import com.is.moo.views.fragments.NavigationDrawerFragment;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by George on 2015/8/13.
 */
public class MoviesActivity extends ActionBarActivity implements MoviesView, RecyclerViewClickListener, View.OnClickListener {

    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(1);

    private final static String BUNDLE_MOVIES_WRAPPER       = "movies_wrapper";
    private final static String BUNDLE_BACK_TRANSLATION     = "background_translation";
    public final static String EXTRA_MOVIE_ID               = "movie_id";
    public final static String EXTRA_MOVIE_LOCATION         = "view_location";
    public final static String EXTRA_MOVIE_POSITION         = "movie_position";
    public final static String SHARED_ELEMENT_COVER         = "cover";
    private int page=1;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private MoviesAdapter mMoviesAdapter;

    public float mBackgroundTranslation;

    @Bind(R.id.activity_movies_toolbar)                   Toolbar mToolbar;
    @Bind(R.id.activity_movies_progress)                  ProgressBar mProgressBar;
    @Bind(R.id.activity_movies_recycler)                  RecyclerView mRecycler;
    private MoviesPresenter mMoviesPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeToolbar();
        initializeRecycler();
        initializeDrawer();

        if (savedInstanceState == null){
            if (mMoviesPresenter==null)
            mMoviesPresenter=new MoviesPresenter(getContext(),this);
            mMoviesPresenter.getPopularMoviesByPage(page);
        }else{
            if (mMoviesPresenter==null)
                mMoviesPresenter=new MoviesPresenter(getContext(),this);
            initializeFromParams(savedInstanceState);
        }
    }


    private void initializeFromParams(Bundle savedInstanceState) {

        MoviesWrapper moviesWrapper = (MoviesWrapper) savedInstanceState .getSerializable(BUNDLE_MOVIES_WRAPPER);

        mMoviesPresenter.onPopularMoviesReceived(moviesWrapper);
    }

    private void initializeRecycler() {

        mRecycler.addItemDecoration(new RecyclerInsetsDecoration(this));
        mRecycler.setOnScrollListener(recyclerScrollListener);
    }

    private void initializeDrawer() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
    }

    private void initializeToolbar() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        mToolbar.setNavigationOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMoviesAdapter != null) {
            outState.putSerializable(BUNDLE_MOVIES_WRAPPER, new MoviesWrapper(mMoviesAdapter.getMovieList()));
            outState.putFloat(BUNDLE_BACK_TRANSLATION, mBackgroundTranslation);
        }
    }

    @Override
    public Context getContext() {

        return this;
    }

    @Override
    public void showMovies(List<Movie> movieList) {

        mMoviesAdapter = new MoviesAdapter(movieList);
        mMoviesAdapter.setRecyclerListListener(this);
        mRecycler.setAdapter(mMoviesAdapter);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingLabel() {

        Snackbar loadingSnackBar = Snackbar.with(this)
                .text(getString(R.string.activity_movies_message_more_films))
                .actionLabel(getString(R.string.action_cancel))
                .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                .color(getResources().getColor(R.color.theme_primary))
                .actionColor(getResources().getColor(R.color.theme_accent));

        SnackbarManager.show(loadingSnackBar);
    }

    @Override
    public void hideActionLabel() {
        SnackbarManager.dismiss();
    }

    @Override
    public boolean isTheListEmpty() {
        return (mMoviesAdapter == null) || mMoviesAdapter.getMovieList().isEmpty();
    }

    @Override
    public void appendMovies(List<Movie> movieList) {
        mMoviesAdapter.appendMovies(movieList);
    }

    @Override
    public void onClick(View touchedView, int moviePosition, float touchedX, float touchedY) {

        Intent movieDetailActivityIntent = new Intent (MoviesActivity.this, MovieDetailActivity.class);

        int movieID = mMoviesAdapter.getMovieList().get(moviePosition).getId();
        movieDetailActivityIntent.putExtra(EXTRA_MOVIE_ID, movieID);
        //movieDetailActivityIntent.putExtra(EXTRA_MOVIE_POSITION, moviePosition);
        startActivity(movieDetailActivityIntent);
/*        ImageView mCoverImage = (ImageView) touchedView.findViewById(R.id.item_movie_cover);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) mCoverImage.getDrawable();

        if (mMoviesAdapter.isMovieReady(moviePosition) || bitmapDrawable != null) {

            sPhotoCache.put(0, bitmapDrawable.getBitmap());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                startDetailActivityBySharedElements(touchedView, moviePosition,movieDetailActivityIntent);
            else
                startDetailActivityByAnimation(touchedView, (int) touchedX,(int) touchedY, movieDetailActivityIntent);

        } else {
            Toast.makeText(this, getString(R.string.activity_movies_message_loading_film),Toast.LENGTH_SHORT).show();
        }*/
    }

    private void startDetailActivityByAnimation(View touchedView,
                                                int touchedX, int touchedY, Intent movieDetailActivityIntent) {

        int[] touchedLocation = {touchedX, touchedY};
        int[] locationAtScreen = new int [2];
        touchedView.getLocationOnScreen(locationAtScreen);

        int finalLocationX = locationAtScreen[0] + touchedLocation[0];
        int finalLocationY = locationAtScreen[1] + touchedLocation[1];

        int [] finalLocation = {finalLocationX, finalLocationY};
        movieDetailActivityIntent.putExtra(EXTRA_MOVIE_LOCATION,
                finalLocation);

        startActivity(movieDetailActivityIntent);
    }

    @SuppressWarnings("unchecked")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startDetailActivityBySharedElements(View touchedView,int moviePosition, Intent movieDetailActivityIntent) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<>(touchedView, SHARED_ELEMENT_COVER + moviePosition));
        startActivity(movieDetailActivityIntent, options.toBundle());
    }

    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener() {
        public boolean flag;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);

            int totalItemCount      = mRecycler.getLayoutManager().getItemCount();
            int visibleItemCount    = mRecycler.getLayoutManager().getChildCount();
            int pastVisibleItems    = ((GridLayoutManager) mRecycler.getLayoutManager()).findFirstVisibleItemPosition();

            if((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                mMoviesPresenter.getPopularMoviesByPage(page++);
            }

            // Is scrolling up
            if (dy > 10) {
                if (!flag) {
                    showToolbar();
                    flag = true;
                }
                // Is scrolling down
            } else if (dy < -10) {
                if (flag) {
                    hideToolbar();
                    flag = false;
                }
            }
        }
    };


    private void showToolbar() {
        mToolbar.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_up_off));
    }

    private void hideToolbar() {
        mToolbar.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_up_on));
    }

    @Override
    public void onClick(View v) {
        mNavigationDrawerFragment.openFragment();
    }

}
