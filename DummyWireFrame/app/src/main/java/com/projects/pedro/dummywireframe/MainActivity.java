package com.projects.pedro.dummywireframe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.projects.pedro.dummywireframe.R.menu.search_tool;

/**
 * Represents the Main Activity where the user is prompt as the application starts.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Class attributes
     */
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TabLayout tabLayout;
    ViewPager viewPager;


    /**
     * Method run in the beginning of the lifecycle of the acitivity where the layout and
     * components are initialized.
     * @param savedInstanceState Bundle to save important information for when onCreate is destroyed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Set the title of the toolbar
         */
        String title = "Meet Mindera"; // FIXME hardcoded strings
        if(getSupportActionBar() != null){
            try{
                getSupportActionBar().setTitle(title);
            }catch (Exception e){e.printStackTrace();}
        }
        // FIXME the great wall of code

        TextView label = findViewById(R.id.title1_id);
        final String label_text = (String) label.getText();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = findViewById(R.id.viewPager_id);
        tabLayout = findViewById(R.id.tabLayout_id);

        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new EventsFragment(), "Events");
        adapter.addFragment(new VacanciesFragment(), "Vacancies");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        Button btn = findViewById(R.id.btn1);
        final String extra_text = (String)btn.getText(); // FIXME code conventions?
        final String endTitle = label_text+"_"+extra_text; // FIXME hardcoded string concats

        // FIXME what about the other items?
        btn.setOnClickListener(new View.OnClickListener() {

            /**
             * When clicked it passes the text of the component and the current toolbar title for the next title
             * as parameters for a bundle to the next activity.
             * @param view
             */
            @Override
            public void onClick(View view) {
                Bundle temp = new Bundle();
                temp.putString("Key", endTitle); // FIXME hardcoded strings, could be static consts
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtras(temp);
                startActivity(i);
            }
        });
    }


    /**
     * When the navigation drawer is opened, this allows going back to the Main activity by pressing the left arrow icon.
     */
    @Override
    public void onBackPressed() {
        int gravity = GravityCompat.START;
        if(drawerLayout.isDrawerOpen(gravity)){
            drawerLayout.closeDrawer(gravity);
        }else{
            super.onBackPressed();
        }
    }


    /**
     * removes the listener for the drawer upon activity destruction.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawerLayout.removeDrawerListener(toggle);
    }

    /**
     * Inflates a search item into the menu layout in order to provide the search option.
     * @param menu Object menu that contain menu information.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(search_tool, menu);
        Drawable drawable = menu.findItem(R.id.search).getIcon();
        if(drawable != null){
            drawable.mutate();
            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }
        return true;
    }

    /**
     * In order to illustrate it's working each time we click upon a component of the header or subheader of the navigation
     * drawer a Toast is generated to show that component was clicked successfully.
     * @param item Item clicked.
     * @return true
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // FIXME nav drawer is displayed behind fragment (android 6.0.1)

        int id = item.getItemId();

        switch (id){
            case R.id.info1_id:
                generateToastMessage("Info 1");
                break;
            case R.id.info2_id:
                generateToastMessage("Info 2");
                break;
            case R.id.info3_id:
                generateToastMessage("Info 3");
                break;
            case R.id.info4_id:
                generateToastMessage("Info 4");
                break;
            case R.id.info5_id:
                generateToastMessage("Info 5");
                break;
            case R.id.another_info1_1:
                generateToastMessage("Subheader Info 1");
                break;
            case R.id.another_info1_2:
                generateToastMessage("Subheader Info 2");
                break;
            case R.id.another_info1_3:
                generateToastMessage("Subheader Info 3");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Generates Toast messages for illustration purposes only.
     * @param msg String with the message to show.
     */
    private void generateToastMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
