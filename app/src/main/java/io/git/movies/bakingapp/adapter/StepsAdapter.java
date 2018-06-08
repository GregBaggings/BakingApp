package io.git.movies.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.pojos.Recipe;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private static List<String> myDataSet;
    private static int index;
    static ViewHolder.OnItemClickListener itemClickListener;
    private Context myContext;
    private Recipe mRecipe;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shortStepTv;
        private ImageView thumbnailIv;

        public interface OnItemClickListener {
            void onItemClicked(int position);
        }

        public ViewHolder(View v) {
            super(v);
            shortStepTv = v.findViewById(R.id.shortStepTv);
            thumbnailIv = v.findViewById(R.id.thumbnailIV);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = getAdapterPosition();
                    Log.i("TEST", "Index value at StepsAdapter: " + index);
                    itemClickListener.onItemClicked(index);
                }
            });
        }
    }

    public StepsAdapter(List<String> shortStepList, ViewHolder.OnItemClickListener clickListener, Context context, Recipe recipe) {
        myDataSet = shortStepList;
        itemClickListener = clickListener;
        myContext = context;
        mRecipe = recipe;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.shortStepTv.setText(myDataSet.get(position));
        String thumbnailPath = mRecipe.getListOfSteps().get(position).getThumbnailURL();
        if (thumbnailPath.equals("") || thumbnailPath.contains(".mp4")) {
            Picasso.with(myContext)
                    .load(R.drawable.ic_emotion)
                    .placeholder(R.drawable.ic_emotion)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.thumbnailIv);
        } else {
            Picasso.with(myContext)
                    .load(mRecipe.getListOfSteps().get(position).getThumbnailURL())
                    .placeholder(R.drawable.ic_emotion)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.thumbnailIv);
        }
    }

    @Override
    public int getItemCount() {
        return myDataSet.size();
    }
}
