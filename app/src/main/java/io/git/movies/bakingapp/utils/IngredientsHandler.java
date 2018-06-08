package io.git.movies.bakingapp.utils;


import io.git.movies.bakingapp.pojos.Recipe;

public class IngredientsHandler {

    public String getIngredients(Recipe recipe) {
        String ingredientsListInString = "";
        for (int i = 0; i < recipe.getListOfIngredients().size(); i++) {
            String ingredientDetails = recipe.getListOfIngredients().get(i).getQuantity() + " " +
                    recipe.getListOfIngredients().get(i).getMeasure() + " of " +
                    recipe.getListOfIngredients().get(i).getIngredient() + ",\n";
            ingredientsListInString = ingredientsListInString + ingredientDetails;
        }
        ingredientsListInString = ingredientsListInString.substring(0, ingredientsListInString.length() - 2);

        String ingredientsWithName = "Ingredients for " + recipe.getName() + ":\n" + ingredientsListInString;
        return ingredientsWithName;
    }
}
