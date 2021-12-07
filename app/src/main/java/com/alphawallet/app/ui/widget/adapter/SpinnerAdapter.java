package com.alphawallet.app.ui.widget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphawallet.app.R;
import com.alphawallet.app.ui.widget.entity.SpinnerItem;

import java.util.List;

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.ViewHolder> {
    private final List<SpinnerItem> data;
    private final ItemClickListener listener;
    private final int spinnerNo;

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView icon;
        final TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }
    }

    public SpinnerAdapter(int spinnerNo,List<SpinnerItem> data, ItemClickListener listener) {
        this.listener = listener;
        this.data = data;
        this.spinnerNo = spinnerNo;
    }


    @NonNull
    @Override
    public SpinnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.spinner_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpinnerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(data.get(i).getName());
        viewHolder.icon.setImageResource(data.get(i).getIcon());

        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(data.get(i), spinnerNo));
    }

    public interface ItemClickListener{
        void onItemClick(SpinnerItem item, int spinnerNo);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
