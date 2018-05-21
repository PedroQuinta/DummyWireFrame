package com.projects.pedro.dummywireframe;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * This activity represents the the list of activities of the given day.
 */
public class SecondActivity extends AppCompatActivity{

    /**
     * Class attributes
     */
    Toolbar toolbar;
    String title;
    List<ListRow> listItems = new ArrayList<>();
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    String toolbar_title;

    /**
     * Method run in the beginning of the lifecycle of the acitivity where the layout and
     * components are initialized.
     * @param savedInstanceState Bundle to save important information for when onCreate is destroyed.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        toolbar = findViewById(R.id.toolbar_second);
        setSupportActionBar(toolbar);

        // The use of sharedpreferences is to ensure the transmission of the correct title for the next activity toolbar.
        Bundle aux = getIntent().getExtras();
        if(aux != null){
            toolbar_title = aux.getString("Key");
            PreferenceManager.getDefaultSharedPreferences(SecondActivity.this).edit().putString("Title",toolbar_title).apply();
        }else{
            toolbar_title = PreferenceManager.getDefaultSharedPreferences(SecondActivity.this).getString("Title", "");
        }
        if(getSupportActionBar() != null){
            try{
                getSupportActionBar().setTitle(toolbar_title);
            }catch (Exception e){e.printStackTrace();}
        }

        // make sure it has a back to previous activity icon in the toolbar.
        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch (NullPointerException e){
            e.getLocalizedMessage();
        }

        recyclerView = findViewById(R.id.recycler_view);
        listAdapter = new ListAdapter(listItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listAdapter);

        // generates the number of textviews needed in the recycler view.
        prepareListRows();

        // if a recycler view item is clicked then that item title is sent to the next activity
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ListRow listRow = listItems.get(position);
                Bundle temp = new Bundle();
                temp.putString("Key", listRow.getTitle());
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtras(temp);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Generates rows layout items to insert in the recycler view.
     */
    private void prepareListRows(){
        ListRow list;
        for(int i=1; i<10; i++){
            list = new ListRow("List 0"+i);
            listItems.add(list);
        }
        listAdapter.notifyDataSetChanged();
    }

    /**
     * It guarantees that when we click in the top left back arrow, we go to the previous activity.
     * @param item Menu item that represents the arrow.
     * @return true;
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
