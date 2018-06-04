package io.git.movies.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.pojos.Recipe;

public class StepsFragment extends ListFragment {

    private List<String> shortStepList = new ArrayList<>();
    OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            itemClickListener = (OnItemClickListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString());
        }
    }

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
        ListView listView;
        listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, shortStepList);
        setListAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClickListener.onItemClicked(position);
            }
        });
    }


    public List<String> getShortSteps(Recipe recipe) {
        for (int i = 0; i < recipe.getListOfSteps().size(); i++) {
            shortStepList.add(recipe.getListOfSteps().get(i).getShortStep());
        }
        return shortStepList;
    }
}
