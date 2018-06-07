package io.git.movies.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import io.git.movies.bakingapp.R;
import io.git.movies.bakingapp.pojos.Recipe;

public class ExoplayerFragment extends Fragment {

    private Recipe recipe;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private int position = 0;
    private long videoPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            videoPosition = savedInstanceState.getLong("videoPosition");
                Log.i("TEST", "videoPosition is " + videoPosition);
        }

        if (getArguments() != null) {
            recipe = getArguments().getParcelable("Recipe");
            position = getArguments().getInt("StepPosition");
            Log.i("TEST", "Recipe from Bundle: " + recipe);
        }

        View view = inflater.inflate(R.layout.exoplayer_fragment, container, false);
        setRetainInstance(true);

        mPlayerView = view.findViewById(R.id.playerView);
        String uri = recipe.getListOfSteps().get(position).getVideoURL();
        Log.i("Test", "Uri is " + uri);

        if (!uri.equals("")) {
            initializePlayer(Uri.parse(uri));
        } else {
            Toast.makeText(getContext(), "No video for that step!", Toast.LENGTH_LONG).show();
            view.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("videoPosition", videoPosition);
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            videoPosition = mExoPlayer.getCurrentPosition();
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    private void initializePlayer(Uri videoUri) {
        if (mExoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(this.getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(
                    this.getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
}
