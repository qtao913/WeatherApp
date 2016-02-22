package com.sunnietech.weatherapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunnietech.weatherapp.R;

public class DetailedCityWeather extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_city_weather);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_city_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private Interpolator mInterpolator;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detailed_city_weather, container, false);
            mInterpolator = new FastOutSlowInInterpolator();
            Intent intent = getActivity().getIntent();
            String cityName = intent.getStringExtra(getString(R.string.cityNameTag));
            int imageId = intent.getIntExtra(getString(R.string.cityImageTag), -1);
            TextView cityNameToolBar = (TextView)rootView.findViewById(R.id.city_name);
            ImageView cityImage = (ImageView)rootView.findViewById(R.id.city_icon);
            cityNameToolBar.setText(cityName);
            cityImage.setImageResource(imageId);
            ViewCompat.animate(cityImage)
                    .scaleX(1)
                    .scaleY(1)
                    .alpha(1)
                    .setInterpolator(mInterpolator)
                    .setStartDelay(300)
                    .start();
            return rootView;

        }
    }
}