package com.is.moo.views.fragments;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.is.moo.R;
/**
 * Created by George on 2015/8/13.
 */
public class NavigationDrawerFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        LinearLayout drawerContent = (LinearLayout) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);

        return drawerContent;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {

        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }

    public void openFragment() {

        mDrawerLayout.openDrawer(mFragmentContainerView);
    }
}
