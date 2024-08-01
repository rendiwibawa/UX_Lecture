package com.example.gpt.PembayaranPilihJam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpt.R;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.WatchViewHolder> {

    private List<Hour> hourList;
    private List<Hour> selectedHours = new ArrayList<>();

    public HourAdapter(List<Hour> hourList) {
        this.hourList = hourList;
    }

    @NonNull
    @Override
    public WatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_watch, parent, false);
        return new WatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchViewHolder holder, int position) {
        Hour hour = hourList.get(position);
        holder.bind(hour);
    }

    @Override
    public int getItemCount() {
        return hourList.size();
    }

    public List<Hour> getSelectedWatches() {
        return selectedHours;
    }

    public class WatchViewHolder extends RecyclerView.ViewHolder {

        private TextView watchName;
        private TextView watchPrice;

        public WatchViewHolder(View itemView) {
            super(itemView);
            watchName = itemView.findViewById(R.id.watch_name);
            watchPrice = itemView.findViewById(R.id.watch_price);

            itemView.setOnClickListener(v -> {
                Hour hour = hourList.get(getAdapterPosition());
                if (selectedHours.contains(hour)) {
                    selectedHours.remove(hour);
                    itemView.setSelected(false);
                } else {
                    selectedHours.add(hour);
                    itemView.setSelected(true);
                }
            });
        }

        public void bind(Hour hour) {
            watchName.setText(hour.getName());
            watchPrice.setText(String.format("$%.2f", hour.getPrice()));
            itemView.setSelected(selectedHours.contains(hour));
        }
    }
}




