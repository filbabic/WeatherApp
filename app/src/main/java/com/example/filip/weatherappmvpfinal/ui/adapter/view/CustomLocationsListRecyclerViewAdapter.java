package com.example.filip.weatherappmvpfinal.ui.adapter.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.filip.weatherappmvpfinal.R;
import com.example.filip.weatherappmvpfinal.pojo.LocationWrapper;
import com.example.filip.weatherappmvpfinal.ui.location.view.browse.LocationItemListener;

import java.util.ArrayList;

/**
 * Created by Filip on 10/02/2016.
 */
public class CustomLocationsListRecyclerViewAdapter extends RecyclerView.Adapter<CustomLocationsListRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<LocationWrapper> mCitiesList = new ArrayList<>();
    private final LocationItemListener listener;

    public CustomLocationsListRecyclerViewAdapter(LocationItemListener listener) {
        this.listener = listener;
    }

    public void fillData(ArrayList<LocationWrapper> data) {
        mCitiesList.clear();
        mCitiesList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocationWrapper locationWrapper = mCitiesList.get(position);
        holder.mLocationName.setText(locationWrapper.getLocation());
        holder.itemView.setTag(locationWrapper);
    }

    @Override
    public int getItemCount() {
        return mCitiesList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        private TextView mLocationName;

        public ViewHolder(View itemView) {
            super(itemView);
            mLocationName = (TextView) itemView.findViewById(R.id.location_list_item_text_view);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onLongClick(mCitiesList.get(getAdapterPosition()));
            return true;
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(mCitiesList.get(getAdapterPosition()));
        }
    }
}
