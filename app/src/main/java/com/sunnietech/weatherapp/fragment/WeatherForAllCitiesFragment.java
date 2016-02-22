package com.sunnietech.weatherapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunnietech.weatherapp.R;
import com.sunnietech.weatherapp.activity.DetailedCityWeather;
import com.sunnietech.weatherapp.adapter.AllCitiesAdapter;
import com.sunnietech.weatherapp.helper.TransitionHelper;
import com.sunnietech.weatherapp.model.Location;

import java.util.Arrays;

public class WeatherForAllCitiesFragment extends Fragment {
    int[] imagePaths = new int[] {
            R.drawable.sanfrancisco,
            R.drawable.sydney,
            R.drawable.singapore,
            R.drawable.suzhou
    };
    String[] cityNames = new String[] {
            "San Francisco",
            "Sydney",
            "Singapore",
            "Suzhou"
    };
    private static final int REQUEST_CATEGORY = 0x2300;
    private AllCitiesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final int SPAN_COUNT = 2;

    public WeatherForAllCitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_for_all_cities, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.weather_all_cities);
        setUpCityGrid(recyclerView);
        return rootView;
    }

    private void setUpCityGrid(RecyclerView citiesView) {
        citiesView.setHasFixedSize(true);
        mAdapter = new AllCitiesAdapter(getActivity(), Arrays.asList(setDummyDataForDisplay()));
        mAdapter.setOnItemClickListener(
                new AllCitiesAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Activity activity = getActivity();
                        startCityDetailActivityWithTransition(activity,
                                view.findViewById(R.id.city_name),
                                mAdapter.getItem(position));
                    }
                }
        );
        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        citiesView.setLayoutManager(mLayoutManager);
        citiesView.setAdapter(mAdapter);
    }

    private Location[] setDummyDataForDisplay() {
        int len = imagePaths.length;
        Location[] data = new Location[len];
        for (int i = 0; i < len; i++) {
            Location item = new Location(imagePaths[i],cityNames[i]);
            data[i] = item;
        }
        return data;
    }

    private void startCityDetailActivityWithTransition(Activity activity, View toolbar,
                                                       Location location) {

        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, activity.getString(R.string.transition_toolbar)));
        @SuppressWarnings("unchecked")
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);

        // Start the activity with the participants, animating from one to the other.
        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        Intent startIntent = new Intent (getActivity(), DetailedCityWeather.class);
        startIntent.putExtra(getString(R.string.cityNameTag), location.getName());
        startIntent.putExtra(getString(R.string.cityImageTag), location.getImage());
        ActivityCompat.startActivityForResult(activity,
                startIntent,
                REQUEST_CATEGORY,
                transitionBundle);
    }
}
