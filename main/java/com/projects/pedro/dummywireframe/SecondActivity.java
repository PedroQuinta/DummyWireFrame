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
    List<SecondListRow> listItems = new ArrayList<>();
    RecyclerView recyclerView;
    SecondListAdapter secondListAdapter;
    String toolbar_title;

    /**
     * Method run in the beginning of the lifecycle of the acitivity where the layout and
     * components are initialized.
     * @param savedInstanceState Bundle to save important information for when onCreate is destroyed.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        toolbar = findViewById(R.id.toolbar_second);
        setSupportActionBar(toolbar);

        setToolbarTitle();

        // generates the number of textviews needed in the recycler view.
        prepareListRows();

        setUpRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Generates rows layout items to insert in the recycler view.
     */
    private void prepareListRows(){
        String listDescription = getString(R.string.listTextContent);
        SecondListRow list;
        for(int i=1; i<10; i++){
            list = new SecondListRow(listDescription+i);
            listItems.add(list);
        }
        secondListAdapter.notifyDataSetChanged();
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

    /**
     * Set up of the recycler view and its on touch implementation.
     */
    private void setUpRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        secondListAdapter = new SecondListAdapter(listItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(secondListAdapter);

        // if a recycler view item is clicked then that item title is sent to the next activity
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SecondListRow listRow = listItems.get(position);
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Key",listRow.getTitle());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    /**
     * Setting up toolbar title depending on which buttons' text we get from the previous activity.
     */
    private void setToolbarTitle(){
        // The use of sharedpreferences is to ensure the transmission of the correct title for the next activity toolbar.
        Intent mIntent = getIntent();
        if(mIntent != null){
            toolbar_title = mIntent.getStringExtra("Key");
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
    }
}
