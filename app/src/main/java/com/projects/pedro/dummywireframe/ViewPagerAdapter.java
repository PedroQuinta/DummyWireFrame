package com.projects.pedro.dummywireframe;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a view pager adapter that will store our fragments and allows us to construct our tab layout.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * List where will be stored our fragments.
     */
    private final List<Fragment> fragmentList = new ArrayList<>();

    /**
     * List where will be stored our View Pager items titles.
     */
    private final List<String> fragmentTitleList = new ArrayList<>();

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Getter of the specific item.
     * @param position integer with the position to serve as index in the list.
     * @return Fragment object.
     */
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    /**
     * Returns the size of the list.
     * @return integer.
     */
    @Override
    public int getCount() {
        return fragmentTitleList.size();
    }

    /**
     * Gets the title of the fragment.
     * @param position integer with the position to serve as index in the list.
     * @return a CharSequence
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    /**
     * Adds a fragment to the list.
     * @param fragment to be added.
     * @param title Title of the specific fragment.
     */
    public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
    }

    /**
     * Get item position.
     * @param object genetic object
     * @return integer.
     */
    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
