package io.git.movies.bakingapp.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.adapter.RecipesAdapter;
import io.git.movies.bakingapp.api.AsyncEventListener;
import io.git.movies.bakingapp.api.AsyncRecipesRequestHandler;
import io.git.movies.bakingapp.api.RecipesAPI;
import io.git.movies.bakingapp.api.RecipesAPIInterface;
import io.git.movies.bakingapp.pojos.Recipe;
import okhttp3.ResponseBody;
import retrofit2.Call;

import static io.git.movies.bakingapp.utils.ConnectionChecker.checkInternetConnection;

public class MainActivity extends AppCompatActivity {

    private RecipesAPIInterface service = RecipesAPI.getRetrofit().create(RecipesAPIInterface.class);
    private Parcelable recyclerViewState;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            recyclerViewState = savedInstanceState.getParcelable("RECIPE_NAMES");
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (!checkInternetConnection(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_LONG).show();
        } else {
            loadingIndicator.setVisibility(View.INVISIBLE);
            Call<ResponseBody> call = service.getRecipes();
            getResponse(call);
        }

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("RECIPE_NAMES", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    private void getResponse(Call call) {
        loadingIndicator.setVisibility(View.VISIBLE);
        AsyncRecipesRequestHandler requestHandler = new AsyncRecipesRequestHandler(call, getApplicationContext(), new AsyncEventListener() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onSuccess(String response) {
                loadingIndicator.setVisibility(View.INVISIBLE);

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Recipe>>() {}.getType();
                List<Recipe> recipes = gson.fromJson(response, listType);

                mAdapter = new RecipesAdapter( recipes);
                recyclerView.setAdapter(mAdapter);
                recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);

            }
        });

        requestHandler.execute(call);
    }
}
