package io.git.movies.bakingapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.adapter.StepsAdapter;
import io.git.movies.bakingapp.fragments.ExoplayerFragment;
import io.git.movies.bakingapp.fragments.StepsFragment;
import io.git.movies.bakingapp.pojos.Recipe;
import io.git.movies.bakingapp.widget.RecipeIngredientsWidget;

public class RecipeDetailsActivity extends AppCompatActivity implements StepsAdapter.ViewHolder.OnItemClickListener {

    private ExoplayerFragment exoplayerFragment;// = new ExoplayerFragment();
    private StepsFragment stepsFragment = new StepsFragment();
    private RecipeIngredientsWidget widget = new RecipeIngredientsWidget();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            ExoplayerFragment exoplayerFragment = new ExoplayerFragment();
            exoplayerFragment.setArguments(bundle);
        }

        Recipe recipe = getIntent().getExtras().getParcelable("Recipe");
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(recipe.getName());
        setSupportActionBar(toolbar);
        bundle.putParcelable("Recipe", recipe);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    int currentPosition = bundle.getInt("Position");

                    //TODO add check for length to avoid indexoutofbound exception
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Log.i("TEST", "current position is " + currentPosition);
                        switch (item.getItemId()) {
                            case R.id.prev_step:
                                onItemClicked(--currentPosition);
                            case R.id.backButton:
                                finish();
                            case R.id.next_step:
                                onItemClicked(++currentPosition);
                        }
                        return true;
                    }
                });


        FragmentManager fragmentManager = getSupportFragmentManager();

        stepsFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.stepsFragmentPlaceholder, stepsFragment).commit();

        widget.onReceive(getApplicationContext(), getIntent());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemClicked(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (exoplayerFragment != null) {
            fragmentManager.beginTransaction().remove(exoplayerFragment).commit();
        }

        ExoplayerFragment exoplayerFragment = new ExoplayerFragment();
        bundle.putInt("StepPosition", position);
        exoplayerFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.videoFragmentPlaceholder, exoplayerFragment).commit();

        Recipe recipe = getIntent().getExtras().getParcelable("Recipe");
        TextView stepDetailsTv = findViewById(R.id.stepDescriptionTv);
        stepDetailsTv.setText(recipe.getListOfSteps().get(position).getDescription());
    }
}
