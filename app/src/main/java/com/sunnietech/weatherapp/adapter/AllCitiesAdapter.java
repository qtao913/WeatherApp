package com.sunnietech.weatherapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sunnietech.weatherapp.R;
import com.sunnietech.weatherapp.model.Location;

import java.util.List;


public class AllCitiesAdapter extends RecyclerView.Adapter<AllCitiesAdapter.ViewHolder> {
    private final Activity mActivity;
    private final LayoutInflater mLayoutInflater;
    private List<Location> mLocations;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public AllCitiesAdapter(Activity activity, List<Location> locations) {
        mActivity = activity;
        mLayoutInflater = LayoutInflater.from(activity.getApplicationContext());
        mLocations = locations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater
                .inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Location city = mLocations.get(position);
        holder.icon.setImageResource(city.getImage());
        holder.title.setText(city.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(v, position);
            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public Location getItem(int position) {
        return mLocations.get(position);
    }
    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView icon;
        final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.city_icon);
            title = (TextView) itemView.findViewById(R.id.city_name);
        }
    }
}
