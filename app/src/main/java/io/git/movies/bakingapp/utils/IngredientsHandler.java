package io.git.movies.bakingapp.utils;


import io.git.movies.bakingapp.pojos.Recipe;

public class IngredientsHandler {

    public String getIngredients(Recipe recipe) {
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
