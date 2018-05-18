package com.projects.pedro.dummywireframe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Adapter to the RecyclerView of the vacancies tab.
 */
public class VacanciesAdapter extends RecyclerView.Adapter<VacanciesAdapter.ViewHolder>{

    private List<VacanciesListRow> itemLista;
    private Context context;

    public VacanciesAdapter(List<VacanciesListRow> itemLista, Context context) {
        this.itemLista = itemLista;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacancies_item_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VacanciesListRow item = itemLista.get(position);

        holder.btn1.setText(item.getBtn1());
        holder.btn2.setText(item.getBtn2());
    }

    @Override
    public int getItemCount() {
        return itemLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Button btn1, btn2;

        public ViewHolder(View itemView) {
            super(itemView);
            btn1 = itemView.findViewById(R.id.btnVacancieA);
            btn2 = itemView.findViewById(R.id.btnVacancieB);
        }
    }
}
