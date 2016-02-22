package com.sunnietech.weatherapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunnietech.weatherapp.R;
import com.sunnietech.weatherapp.adapter.AllCitiesAdapter;
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

    private AllCitiesAdapter mAllCitiesAdapter;
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
        mAllCitiesAdapter = new AllCitiesAdapter(getActivity(), Arrays.asList(setDummyDataForDisplay()));
        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        citiesView.setLayoutManager(mLayoutManager);
        citiesView.setAdapter(mAllCitiesAdapter);
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
}
