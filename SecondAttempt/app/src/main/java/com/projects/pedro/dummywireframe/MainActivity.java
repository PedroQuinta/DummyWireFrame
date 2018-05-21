package com.projects.pedro.dummywireframe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * Method run in the beginning of the lifecycle of the acitivity where the layout and
     * components are initialized.
     * @param savedInstanceState Bundle to save important information for when onCreate is destroyed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar(getString(R.string.meetMinderaToolbar));
        setUpNavigationDrawer();
        viewPager = findViewById(R.id.viewPager_id);
        tabLayout = findViewById(R.id.tabs);

        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter  = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

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


    /**
     * Sets up the toolbar with the corresponding title.
     * @param title to serve as title.
     */
    private void setUpToolbar(String title){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         //Set the title of the toolbar
        if(getSupportActionBar() != null){
            try{
                getSupportActionBar().setTitle(title);
            }catch (Exception e){e.printStackTrace();}
        }
    }

    /**
     *  Sets up the variables and navigation drawer functionality
     */
    private void setUpNavigationDrawer(){
        //initialize attributes.
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // adds listener and sync toggle.
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private RecyclerView recycler;
        private List<VacanciesListRow> list;
        private VacanciesAdapter vacanciesAdapter;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View rootView = new View(getContext());

            switch(getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:
                    rootView = inflater.inflate(R.layout.events, container, false);

                    /* This case is just an example since we would have to implement listeners
                    to all buttons. A cleaner way to do this is to use a recycler view. In that way
                    we just have to define listeners to the elements in the row and we would be able to
                    know the specific row title we selected.
                    */
                    Button btn = (Button)rootView.findViewById(R.id.btn1);
                    final String title = btn.getText().toString().trim();
                    btn.setOnClickListener(this);

                    break;
                case 2:
                    String[] jobs = {getString(R.string.android_developer)
                            ,getString(R.string.java_engineer), getString(R.string.front_end)
                            ,getString(R.string.graduate_program_job), getString(R.string.infrastructure_engineer)
                            ,getString(R.string.ios_developer), getString(R.string.system_admin), getString(R.string.teste_automation)};
                    rootView = inflater.inflate(R.layout.vacancies, container, false);
                    recycler = rootView.findViewById(R.id.vacancies_recycler);
                    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    list = new ArrayList<>();
                    int j = 0;
                    for(int i = 0; i < 4; i++){
                        list.add(new VacanciesListRow(jobs[j], jobs[j+1]));
                        j+=2;
                    }
                    vacanciesAdapter = new VacanciesAdapter(list, getContext());
                    recycler.setAdapter(vacanciesAdapter);
                    break;

            }

            return rootView;
        }



        @Override
        public void onClick(View v) {
            Log.i("", "onClick: it fired this event");
            Button btn = (Button)v;
            Intent intent = new Intent(getActivity(), SecondActivity.class);
            intent.putExtra("description","_"+btn.getText());
            startActivity(intent);
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

}
