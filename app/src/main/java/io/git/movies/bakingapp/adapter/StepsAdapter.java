package io.git.movies.bakingapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.git.movies.bakingapp.R;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private static List<String> myDataSet;
    private static int index;
    static ViewHolder.OnItemClickListener itemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shortStepTv;

        public interface OnItemClickListener {
            void onItemClicked(int position);
        }

        public ViewHolder(View v) {
            super(v);
            shortStepTv = v.findViewById(R.id.shortStepTv);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = getAdapterPosition();
                    Log.i("TEST", "Index value at StepsAdapter: "+ index);
                    itemClickListener.onItemClicked(index);
                }
            });
        }
    }

    public StepsAdapter(List<String> shortStepList, ViewHolder.OnItemClickListener clickListener) {
        myDataSet = shortStepList;
        itemClickListener = clickListener;
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
    }

    @Override
    public int getItemCount() {
        return myDataSet.size();
    }
}
