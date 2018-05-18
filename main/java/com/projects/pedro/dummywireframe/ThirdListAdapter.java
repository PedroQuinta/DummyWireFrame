package com.projects.pedro.dummywireframe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ThirdListAdapter extends RecyclerView.Adapter<ThirdListAdapter.ViewHolder>{

    private List<ThirdListRow> items;
    private Context context;

    public ThirdListAdapter(List<ThirdListRow> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ThirdListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_third_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdListAdapter.ViewHolder holder, int position) {
        ThirdListRow item = items.get(position);
        holder.descriptionA.setText(item.getDescriptionA());
        holder.descriptionB.setText(item.getDescriptionB());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView descriptionA, descriptionB;

        public ViewHolder(View itemView) {
            super(itemView);
            descriptionA = itemView.findViewById(R.id.desc_titleA);
            descriptionB = itemView.findViewById(R.id.desc_titleB);
        }
    }
}
