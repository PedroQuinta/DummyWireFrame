package com.projects.pedro.dummywireframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Class that represents the components with the descriptions of the list previously chosen.
 */
public class ThirdActivity extends AppCompatActivity {

    Toolbar toolbar;

    /**
     * Method run in the beginning of the lifecycle of the acitivity where the layout and
     * components are initialized.
     * @param savedInstanceState Bundle to save important information for when onCreate is destroyed.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Bundle aux = getIntent().getExtras();

        String toolbar_title = aux.getString("Key");

        toolbar = findViewById(R.id.toolbar_third);
        setSupportActionBar(toolbar);
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

    /**
     * It guarantees that when we click in the top left back arrow, we go to the previous activity.
     * @param item Menu item that represents the arrow.
     * @return true;
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
