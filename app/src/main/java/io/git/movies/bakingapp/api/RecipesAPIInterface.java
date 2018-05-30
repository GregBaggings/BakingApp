package io.git.movies.bakingapp.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesAPIInterface {

    String recipesEndpoint = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @GET(recipesEndpoint)
    Call<ResponseBody> getRecipes();

}
