package io.git.movies.bakingapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.fragments.StepsFragment;
import io.git.movies.bakingapp.pojos.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {
    private TextView recipeIngredientsTv;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recipe recipe = getIntent().getExtras().getParcelable("Recipe");
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(recipe.getName());
        setSupportActionBar(toolbar);
        bundle.putParcelable("Recipe", recipe);
        recipeIngredientsTv = findViewById(R.id.recipeIngredientsTextView);
        recipeIngredientsTv.setText(getIngredients(recipe));

        StepsFragment stepsFragment = new StepsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        stepsFragment.setArguments(bundle);
        //somehow it is not visible
        fragmentManager.beginTransaction()
                .replace(R.id.stepsFragmentPlaceholder, stepsFragment)
                .commit();
    }

    private String getIngredients(Recipe recipe) {
        String retVal = "";
        for (int i = 0; i < recipe.getListOfIngredients().size(); i++) {
            String ingredientDetails = recipe.getListOfIngredients().get(i).getQuantity() + " " +
                    recipe.getListOfIngredients().get(i).getMeasure() + " of " +
                    recipe.getListOfIngredients().get(i).getIngredient() + ",\n";
            retVal = retVal + ingredientDetails;
        }
        return retVal.substring(0, retVal.length() - 2);
    }
}
