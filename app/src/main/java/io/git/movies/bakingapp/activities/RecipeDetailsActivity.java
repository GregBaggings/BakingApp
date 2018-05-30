package io.git.movies.bakingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.pojos.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {
    private TextView recipeNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Recipe recipe = getIntent().getExtras().getParcelable("Recipe");

        recipeNameTv = findViewById(R.id.recipeNameTv);
        recipeNameTv.setText(recipe.getName());

    }
}
