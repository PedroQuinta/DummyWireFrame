package com.example.pedro.finalwireframe;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyMainAdapter extends RecyclerView.Adapter<MyMainAdapter.ViewHolder> {

    private List<ListItemMain> itemsList;
    private Context context;



    public MyMainAdapter(List<ListItemMain> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItemMain item = itemsList.get(position);
        holder.title.setText(item.getTitle());
        holder.btn1.setText(item.getBtn1());
        holder.btn2.setText(item.getBtn2());
        holder.btn3.setText(item.getBtn3());

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public Button btn1, btn2, btn3;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            btn1 = itemView.findViewById(R.id.btnA);
            btn2 = itemView.findViewById(R.id.btnB);
            btn3 = itemView.findViewById(R.id.btnC);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.i("", "onClick: click of button "+btn1.getText());
        }
    }

}
