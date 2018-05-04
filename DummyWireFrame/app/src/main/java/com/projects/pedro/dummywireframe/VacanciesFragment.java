package com.projects.pedro.dummywireframe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Class that represents the fragment, Vacancies,  used for the tab layout.
 */
public class VacanciesFragment extends Fragment{ // FIXME why have a fragment that does nothing

    View view; // FIXME what is this for?

    public VacanciesFragment() {

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
        view = inflater.inflate(R.layout.vacancies, container, false);
        return view;
    }

}
