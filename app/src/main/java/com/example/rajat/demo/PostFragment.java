package com.example.rajat.demo;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rajat.demo.databinding.FragmentPostBinding;
import com.example.rajat.demo.mgpl.InteractionTemplateLayout;
import com.example.rajat.demo.model.Data;
import com.example.rajat.demo.model.Gfs;
import com.example.rajat.demo.model.Post;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    private static final String TAG = "PostFragment";
    private int fragmentIndex;
    private FragmentPostBinding binding;
    private Post mPost;
    private SimpleExoPlayer mPlayer;
    private boolean fragmentVisibility = false;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private MainActivityViewModel mainActivityViewModel;

    private InteractionTemplateLayout.InteractiveItemClickListener interactionListener
            = this::updatePlay;

    public PostFragment(Post post, int fragmentIndex) {
        mPost = post;
        this.fragmentIndex = fragmentIndex;
    }

    PostFragment(Post post) {
        mPost = post;
    }

    public PostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainActivityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_post, container, false);

        return binding.getRoot();
    }

    private void init() {

        initializePlayer();
        updateInteractiveLayout(mPost);
        binding.interactionLayout.setInteractiveItemClickListener(interactionListener);

    }

    private void updateInteractiveLayout(Post post) {

        List<Gfs> gifList = new ArrayList<>();
        int templateId = -1;
        String font = "";
        for (Data data : post.getTracks().getData()) {

            if (data.getGfs() != null) {
                gifList.addAll(data.getGfs());
            }

            if (data.getTemp() != null) {
                font = data.getTemp().getFont();
                templateId = data.getTemp().getType();
            }
        }
        binding.interactionLayout.setFont(font);
        binding.interactionLayout.setTemplateId(templateId);
        for (int i = 0; i < gifList.size(); i++) {
            binding.interactionLayout.addGf(gifList.get(i));
        }
    }

    private void initializePlayer() {

        if (mPost == null) return;

        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
        mPlayer.addListener(eventListener);
        binding.videoView.setPlayer(mPlayer);

        MediaSource mediaSource = buildMediaSource(mPost);
        mPlayer.prepare(mediaSource, false, false);

    }

    private Uri getBoomerangUri(Post post) {

        String baseUrl = Constants.BASE_URL;
        String postId = post.getId() + "/";

        for (Data data : post.getTracks().getData()) {

            if (data.getTyp().equalsIgnoreCase("boomerang")) {
                String videoUrl = data.getUrl();
                String ulr = String.format("%s%s%s", baseUrl, postId, videoUrl);

                return Uri.parse(ulr);
            }
        }

        Log.e(TAG, "getBoomerangUri: is null");
        return null;
    }

    /**
     * Sample Urls:
     * <p>
     * https://yovo-app.storage.googleapis.com/zuyYZmQWg/yo240p/intro.mp4
     * https://yovo-app.storage.googleapis.com/zuyYZmQWg/yo240p/1.mp4
     * https://yovo-app.storage.googleapis.com/zuyYZmQWg/yo240p/2.mp4
     * https://yovo-app.storage.googleapis.com/zuyYZmQWg/yo240p/boomerang.mp4
     * <p>
     * <p>
     * Todo: Write the sequence of concatenation here and why
     *
     * @param post {@link Post}
     * @return {@link ConcatenatingMediaSource}
     */
    private MediaSource buildMediaSource(Post post) {

        ConcatenatingMediaSource concatenatingMediaSource = new ConcatenatingMediaSource();

        String baseUrl = Constants.BASE_URL;
        String postId = post.getId();
        String quality = "/yo240p/";

        // These factories are used to construct two media sources below
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), getString(R.string.app_name));
        ProgressiveMediaSource.Factory mediaSourceFactory =
                new ProgressiveMediaSource.Factory(dataSourceFactory);

        Uri boomerangUri = getBoomerangUri(post);
        MediaSource mediaSourceBoomerang = mediaSourceFactory.createMediaSource(boomerangUri);

        for (Data data : post.getTracks().getData()) {

            String videoUrl = data.getUrl();

            String ulr = String.format("%s%s%s%s", baseUrl, postId, quality, videoUrl);

            Uri uri = Uri.parse(ulr);

            Log.i(TAG, "buildMediaSource: videoUrl " + ulr);

            // Create a media source using the supplied URI
            MediaSource mediaSource = mediaSourceFactory.createMediaSource(uri);
            concatenatingMediaSource.addMediaSource(mediaSource);

            if (concatenatingMediaSource.getSize() == 1) {
                mPlayer.prepare(concatenatingMediaSource);
                pausePlayer();
            }

            if (!data.getTyp().equalsIgnoreCase("boomerang")) {
                concatenatingMediaSource.addMediaSource(mediaSourceBoomerang);
            }
        }
        return concatenatingMediaSource;
    }

    private void pausePlayer() {

        Log.i(TAG, "pausePlayer: ");

        if (mPlayer != null) {
            mPlayer.setPlayWhenReady(false);
        }

    }

    private void resumePlayer() {

        Log.i(TAG, "resumePlayer: ");

        if (mPlayer != null) {
            mPlayer.setPlayWhenReady(true);
        }

    }

    private void resetPlayer() {

        Log.i(TAG, "resetPlayer: ");
        if (mPlayer != null) {
            pausePlayer();
            mPlayer.seekTo(currentWindow, playbackPosition);
        }
    }


    private void releasePlayer() {

        Log.i(TAG, "releasePlayer: ");

        if (mPlayer != null) {
            playbackPosition = mPlayer.getCurrentPosition();
            currentWindow = mPlayer.getCurrentWindowIndex();
            mPlayer.release();
            mPlayer = null;
        }
    }

    private void updatePlay(int position) {

        mPlayer.seekTo(2 * position, 0);
        mainActivityViewModel.updateLiveScore(position);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Util.SDK_INT >= 24) {
            init();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart: " + fragmentIndex);

    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume: " + fragmentIndex);

        setFragmentVisibility(true);
        resumePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause: " + fragmentIndex);
        setFragmentVisibility(false);
        resetPlayer();

    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(TAG, "onStop: " + fragmentIndex);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy: " + fragmentIndex);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i(TAG, "onDestroyView: " + fragmentIndex);
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.i(TAG, "onDetach: " + fragmentIndex);
    }


    private Player.EventListener eventListener = new Player.EventListener() {

        @Override
        public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

        }

        @Override
        public void onLoadingChanged(boolean isLoading) {

        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        }

        @Override
        public void onIsPlayingChanged(boolean isPlaying) {

        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {

        }

        @Override
        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {

        }

        @Override
        public void onPositionDiscontinuity(int reason) {

            int sourceIndex = mPlayer.getCurrentWindowIndex();

            if (sourceIndex % 2 == 1) {

                mPlayer.setRepeatMode(Player.REPEAT_MODE_ONE);
                showBoomerangOptions(true);

            } else {

                mPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
                showBoomerangOptions(false);

            }

            switch (sourceIndex) {

                case 1:
                    break;
            }

        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }

        @Override
        public void onSeekProcessed() {

        }
    };

    @Override
    public void setRetainInstance(boolean retain) {
        Log.i(TAG, "setRetainInstance: " + retain);
        super.setRetainInstance(false);
    }

    private void showBoomerangOptions(boolean visibility) {

        if (visibility) {
            binding.interactionLayout.setVisibility(View.VISIBLE);

        } else {
            binding.interactionLayout.setVisibility(View.GONE);

        }
    }

    public boolean getFragmentVisibility() {
        return fragmentVisibility;
    }

    public void setFragmentVisibility(boolean fragmentVisibility) {
        this.fragmentVisibility = fragmentVisibility;
    }

}
