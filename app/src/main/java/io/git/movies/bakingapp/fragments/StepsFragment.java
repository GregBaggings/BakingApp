package io.git.movies.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.adapter.StepsAdapter;
import io.git.movies.bakingapp.pojos.Recipe;

public class StepsFragment extends Fragment{

    private List<String> shortStepList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Recipe recipe;
        if (getArguments() != null) {
            recipe = getArguments().getParcelable("Recipe");
            Log.i("TEST", "Recipe from Bundle: " + recipe);
            getShortSteps(recipe);
        }

        View view = inflater.inflate(R.layout.step_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.steps_recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new StepsAdapter(shortStepList, (StepsAdapter.ViewHolder.OnItemClickListener)getActivity());
        recyclerView.setAdapter(mAdapter);
    }

    public List<String> getShortSteps(Recipe recipe) {
        for (int i = 0; i < recipe.getListOfSteps().size(); i++) {
            shortStepList.add(recipe.getListOfSteps().get(i).getShortStep());
        }
        return shortStepList;
    }
}
