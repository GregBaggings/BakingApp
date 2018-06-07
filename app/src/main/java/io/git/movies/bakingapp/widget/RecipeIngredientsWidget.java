package io.git.movies.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.activities.MainActivity;
import io.git.movies.bakingapp.pojos.Recipe;

public class RecipeIngredientsWidget extends AppWidgetProvider {
    private Recipe recipe = null;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, Recipe recipe,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredients_widget);

        if(recipe != null){
            views.setTextViewText(R.id.widgetIngredientsTv, recipe.getListOfIngredients().toString());
            Log.i("TEST", "Recipe at the widget: " + recipe.toString());
        }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.widgetIngredientsTv, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context.getApplicationContext(), RecipeIngredientsWidget.class));

        final String action = intent.getAction();

        if (action != null && action.equals(IngredientsService.UPDATE_INGREDIENTS)) {

            if (intent.getExtras() != null) {
                recipe = intent.getExtras().getParcelable("Recipe");
            } else {
                Log.e(context.getPackageName(), "Recipe is null");
            }
            onUpdate(context, appWidgetManager, appWidgetIds);
        }

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }
}
