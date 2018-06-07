package io.git.movies.bakingapp.widget;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import io.git.movies.bakingapp.pojos.Recipe;

public class IngredientsService extends IntentService {

    public static final String UPDATE_INGREDIENTS = "io.git.bakingapp.widget.update_widget";

    public IngredientsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (UPDATE_INGREDIENTS.equals(action)) {
                Recipe recipe = intent.getExtras().getParcelable("Recipe");
                handleActionUpdateWidget(recipe);
            }
        }
    }

    public void handleActionUpdateWidget(Recipe recipe) {
        Intent intent = new Intent(UPDATE_INGREDIENTS);
        intent.putExtra("Recipe", recipe);
        intent.setAction(UPDATE_INGREDIENTS);
        getApplicationContext().startService(intent);
    }

    //TODO add the update implementation here?
}
