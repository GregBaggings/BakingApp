package io.git.movies.bakingapp.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.pojos.Recipe;
import io.git.movies.bakingapp.pojos.Steps;

public class StepsFragment extends ListFragment{

    private List<Steps> stepsList = new ArrayList<>();
    private List<String> shortStepList = new ArrayList<>();
    private Recipe recipe;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            recipe = getArguments().getParcelable("Recipe");
            Log.i("TEST", "Recipe from Bundle: " + recipe);
            getSteps(recipe);
            getShortSteps(recipe);
        }

        View view = inflater.inflate(R.layout.step_list_fragment, container, false);
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, shortStepList);
        setListAdapter(adapter);
    }

    public List<Steps> getSteps(Recipe recipe) {
        for (int i = 0; i < recipe.getListOfSteps().size(); i++) {
            stepsList.add(recipe.getListOfSteps().get(i));
        }
        return stepsList;
    }

    public List<String> getShortSteps(Recipe recipe) {
        for (int i = 0; i < recipe.getListOfSteps().size(); i++) {
            shortStepList.add(recipe.getListOfSteps().get(i).getShortStep());
        }
        return shortStepList;
    }
}
