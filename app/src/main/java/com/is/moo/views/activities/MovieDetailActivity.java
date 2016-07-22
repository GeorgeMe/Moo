package com.is.moo.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.is.moo.Constants;
import com.is.moo.R;
import com.is.moo.custom.views.PinchImageView;
import com.is.moo.custom.views.PinchImageViewPager;
import com.is.moo.entities.MovieDetail;
import com.is.moo.mvp.presenters.MovieDetailPresenter;
import com.is.moo.mvp.views.DetailView;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by George on 2015/8/13.
 */
public class MovieDetailActivity extends AppCompatActivity implements DetailView {


    @Bind(R.id.pager)
    PinchImageViewPager pager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MovieDetailPresenter mDetailPresenter;

    LinkedList<PinchImageView> viewCache = new LinkedList<PinchImageView>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        int id = getIntent().getIntExtra(MoviesActivity.EXTRA_MOVIE_ID, 1);
        mDetailPresenter = new MovieDetailPresenter(this, getContext());
        mDetailPresenter.getMovieDetail(id);
    }

    @Override
    public void showMovieDetail(final MovieDetail result) {
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return result.getList().size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PinchImageView piv;
                if (viewCache.size() > 0) {
                    piv = viewCache.remove();
                    piv.reset();
                } else {
                    piv = new PinchImageView(getContext());
                }
                Picasso.with(getContext()).load(Constants.BASIC_STATIC_URL + result.getList().get(position).getSrc()).centerCrop().resize(500, 500).into(piv);
                container.addView(piv);
                return piv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                container.removeView(piv);
                viewCache.add(piv);
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                PinchImageView piv = (PinchImageView) object;
                Picasso.with(getContext()).load(Constants.BASIC_STATIC_URL + result.getList().get(position).getSrc()).centerCrop().resize(500, 500).into(piv);
                pager.setMainPinchImageView(piv);
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

}
