package io.git.movies.bakingapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.activities.RecipeDetailsActivity;
import io.git.movies.bakingapp.pojos.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder>{
    private List<Recipe> myDataSet;
    private Context mContext;
    Intent intent;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeNameTv;
        int index;

        public ViewHolder(View v) {
            super(v);
            recipeNameTv = v.findViewById(R.id.recipeNameTextView);
            intent = new Intent(mContext, RecipeDetailsActivity.class);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = getAdapterPosition();
                    intent.putExtra("Recipe", myDataSet.get(index));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public RecipesAdapter(Context context, List<Recipe> recipes) {
        myDataSet = recipes;
        mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recipeNameTv.setText(myDataSet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myDataSet.size();
    }
}
