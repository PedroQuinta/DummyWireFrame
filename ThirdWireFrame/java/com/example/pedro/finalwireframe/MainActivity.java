package com.example.pedro.finalwireframe;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.pedro.finalwireframe.R.menu.search_tool;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;



    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar(getString(R.string.meet_mindera));
        setUpNavigationDrawer();


        // Create the adapter that will return a fragment for each of the two
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


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
        Drawable drawable = menu.findItem(R.id.search_icon).getIcon();
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

    private void setUpNavigationDrawer(){
        //initialize attributes.
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // adds listener and sync toggle.
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        private RecyclerView.Adapter adapter;
        private List<ListItemMain> itemsLista;


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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = new View(getContext());
            LinearLayoutManager linearLayoutManage;
            RecyclerView recyclerView;
            switch(getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    recyclerView = rootView.findViewById(R.id.recycler);
                    linearLayoutManage = new LinearLayoutManager(this.getActivity());
                    String[] titles = {getString(R.string.open_day_18)
                            , getString(R.string.graduate_program), getString(R.string.meet_mindera_code_amp_culture)};

                    recyclerView.setLayoutManager(linearLayoutManage);
                    itemsLista = new ArrayList<>();
                    String d = getString(R.string.days);
                    int j = 1;
                    for(int i = 0; i < 3; i++){
                        itemsLista.add(new ListItemMain(titles[i], d+j, d+(j+1), d+(j+2)));
                        j=j+3;
                    }

                    adapter = new MyMainAdapter(itemsLista, this.getActivity());
                    recyclerView.setAdapter(adapter);

                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_second, container, false);
                    recyclerView = rootView.findViewById(R.id.recycler);
                    break;
            }


            return rootView;
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
