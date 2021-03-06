package com.projects.pedro.dummywireframe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Class adapted from : https://www.androidhive.info/2016/01/android-working-with-recycler-view/
 * Encapsulates the content of the Second Activity layout that will receive rows, i.e. objects of SecondListRow.
 */
public class SecondListAdapter extends RecyclerView.Adapter<SecondListAdapter.MyViewHolder> {

    private List<SecondListRow> listItems;

    /**
     * Inner class that holds the title of the view.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.description);
        }
    }

    /**
     *  Constructor with one parameter. It initiates our list.
     * @param list List of SecondListRow objects.
     */
    public SecondListAdapter(List<SecondListRow> list){
        this.listItems = list;
    }

    /**
     * Allows inflation of the specific layout on the activity for each row as views are created.
     * @param parent ViewGroup parent to get context.
     * @param viewType
     * @return Inner class MyViewHolder object.
     */
    @NonNull
    @Override
    public SecondListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_second_row,parent,false);
        return new MyViewHolder(itemView);
    }

    /**
     * sets the title in the view given by the text view in the SecondListRow.
     * @param holder  Object of the inner class.
     * @param position Position to serve as index to get in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull SecondListAdapter.MyViewHolder holder, int position) {
        SecondListRow listRow = listItems.get(position);
        holder.title.setText(listRow.getTitle());
    }

    /**
     * Getter for the list items size.
     * @return
     */
    @Override
    public int getItemCount() {
        return listItems.size();
    }
}