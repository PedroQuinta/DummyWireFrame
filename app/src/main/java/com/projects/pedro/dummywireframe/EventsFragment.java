package com.projects.pedro.dummywireframe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Class that represents the fragment, Events,  used for the tab layout.
 */
public class EventsFragment extends Fragment{

    View view;

    public EventsFragment() {

    }

    /**
     * Inflates the view object into the layout.
     * @param inflater in order to use the inflate method.
     * @param container the viewgroup container.
     * @param savedInstanceState bundle to pass and save necessary data.
     * @return view object.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.events, container, false);
        return view;
    }


}
