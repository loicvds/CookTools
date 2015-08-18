package com.cooktools.lo.cooktools.activities;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooktools.lo.cooktools.R;
import com.cooktools.lo.cooktools.modules.SlidingTabLayout;
import com.cooktools.lo.cooktools.services.ViewPagerAdapter;

/**
 * Activité principale de l'application avec des Sliding Tab contenant les deux calculatrices
 */

public class Calculatrice extends Fragment {
    public static final String TAG = "calc";

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Proportions","Conversions", "Chronos"};
    int Numboftabs = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_calculatrice, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter =  new ViewPagerAdapter(getActivity().getSupportFragmentManager(),Titles,Numboftabs);

        pager = (ViewPager) getActivity().findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) getActivity().findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);
    }
}
