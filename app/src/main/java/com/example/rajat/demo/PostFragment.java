package com.example.rajat.demo;


import android.content.Context;
import android.graphics.Color;
import android.hardware.SensorManager;
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
import com.example.rajat.demo.model.Temp;
import com.example.rajat.demo.model.Tracks;
import com.google.android.exoplayer2.C;
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
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.AssetDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ref: 360 Video https://stackoverflow.com/questions/60166742/how-to-enable-gyrosensors-for-exoplayer-360-videos
 */
public class PostFragment extends Fragment {

    private static final String TAG = "PostFragment";
    //public static final String URL_360 = "https://raw.githubusercontent.com/rajatsangrame/temp/master/Shark%203d%20360%20-%20panocam3d.com.mp4";
    private static final String URL_360 = "https://theyouthbuzz.com/ytplayer/Seoul8KVR3DTrailer.mp4";

    /*
    private static final String MAIN = "https://raw.githubusercontent.com/rajatsangrame/temp/master/Main20Cam201.mp4";
    private static final String CAM2 = "https://raw.githubusercontent.com/rajatsangrame/temp/master/Cam202.mp4";
    private static final String CAM3 = "https://raw.githubusercontent.com/rajatsangrame/temp/master/Cam203.mp4";
     */

    private static final String MAIN = "assets:///MainCam201.mp4";
    private static final String CAM2 = "assets:///Cam202.mp4";
    private static final String CAM3 = "assets:///Cam203.mp4";

    private int fragmentIndex;
    private FragmentPostBinding binding;
    private boolean fragmentVisibility = false;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private MainActivityViewModel mainActivityViewModel;
    private Temp mTemp;
    private Post mPost;
    private Tracks mFirstTrack;
    private SimpleExoPlayer mPlayer;
    private SimpleExoPlayer mPlayerSpherical;
    private List<String> mIndexTypeList;
    private List<Gfs> mGifList;
    private Map<String, Tracks> mTracksHashMap;
    private ConcatenatingMediaSource mConcatMediaSource;

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
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        binding.sphericalView.setSensorManager(sensorManager);
        binding.sphericalView.setVisibility(View.GONE);

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

        if (post == null) return;

        if (post.getType().equals("story")) {

            binding.interactionLayout.removeAllViews();
            binding.interactionLayout.setFont(mTemp.getFont());
            binding.interactionLayout.setTemplateId(mTemp.getType());
            for (Gfs gf : mGifList) {
                binding.interactionLayout.addGf(gf);

            }

        } else if (post.getType().equals("bvf")) {

            if (post.getTracks().getData().size() == 1) {
                //Playing Contest Video
                mPlayer.setRepeatMode(Player.REPEAT_MODE_ONE);
                return;
            }
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
    }

    private void initializePlayer() {

        if (mPost == null) return;

        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
        mPlayerSpherical = ExoPlayerFactory.newSimpleInstance(getContext());
        binding.videoView.setPlayer(mPlayer);
        binding.videoView.setShutterBackgroundColor(Color.TRANSPARENT);
        //binding.videoView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

        //Bellow line will ensure that aspect ratio is correctly maintained even for 4:3 videos.
        binding.videoView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        mPlayer.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

        mPlayer.addListener(eventListener);

        /*
        binding.controlView.setPlayer(mPlayer);
        binding.controlView.setProgressUpdateListener(new PlayerControlView.ProgressUpdateListener() {
            @Override
            public void onProgressUpdate(long position, long bufferedPosition) {

                Log.i(TAG, "onProgressUpdate: " + position);

                if ((int) position == 82000) {
                    Gfs gfs = new Gson().fromJson(Constants.BTN_3, Gfs.class);
                    binding.interactionLayout.addGf(gfs);
                }
            }
        });
        */
        binding.sphericalView.setPlayer(mPlayerSpherical);

        mIndexTypeList = new ArrayList<>();
        mGifList = new ArrayList<>();
        mTracksHashMap = new HashMap<>();
        mConcatMediaSource = new ConcatenatingMediaSource();

        if (mPost.getType().equals("story")) {
            loadTracks(mPost.getTracks());
            preparePlayer(mFirstTrack);

            //MediaSource mediaSource = buildMediaSource(mPost.getTracks());
            //mPlayer.prepare(mediaSource, false, false);
        } else if (mPost.getType().equals("bvf")) {
            MediaSource mediaSource = buildMediaSource(mPost);
            mPlayer.prepare(mediaSource, false, false);
        }
    }

    private String getTypeFromIndex(int index) {

        try {
            return mIndexTypeList.get(index);

        } catch (IndexOutOfBoundsException e) {
            return "";
        }
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

    private void loadTracks(Tracks track) {

        if (mFirstTrack == null) {
            mFirstTrack = track;
        }
        List<Data> data = track.getData();

        if (!mTracksHashMap.containsKey(track.getTrackId())) {
            mTracksHashMap.put(track.getTrackId(), track);
        }
        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getTracks() != null
                    && data.get(i).getTracks().getData() != null) {

                loadTracks(data.get(i).getTracks());
            }
        }

    }

    private void preparePlayer(Tracks track) {

        if (mTracksHashMap.isEmpty() || track == null) return;

        MediaSource mediaSource = buildMediaSourceFromMap(track);
        mPlayer.prepare(mediaSource, false, false);

    }

    private MediaSource buildMediaSourceFromMap(Tracks track) {

        mGifList = new ArrayList<>();

        DataSource.Factory dataSourceFactory = () -> new AssetDataSource(getContext());
        ProgressiveMediaSource.Factory mediaSourceFactory =
                new ProgressiveMediaSource.Factory(dataSourceFactory);

        List<Data> dataList = track.getData();
        for (Data data : dataList) {

            if (data.getUrl() != null && !data.getUrl().isEmpty() && !data.getTyp().equals("boomerang")) {

                Uri uri = Uri.parse("assets:///" + data.getUrl());
                MediaSource m = mediaSourceFactory.createMediaSource(uri);
                mConcatMediaSource.addMediaSource(m);
                mIndexTypeList.add(data.getTyp());
            }


            if (data.getTemp() != null) {

                mTemp = data.getTemp();
            }

            if (data.getGfs() != null && !data.getGfs().isEmpty()) {

                mGifList.addAll(data.getGfs());
            }

        }

        return mConcatMediaSource;
    }

    private MediaSource buildMediaSource(Tracks tracks) {

        DataSource.Factory dataSourceFactory = () -> new AssetDataSource(getContext());

        ProgressiveMediaSource.Factory mediaSourceFactory =
                new ProgressiveMediaSource.Factory(dataSourceFactory);

        ConcatenatingMediaSource concat = new ConcatenatingMediaSource();

        List<Data> data = tracks.getData();

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getUrl() == null || data.get(i).getUrl().isEmpty()) {
                continue;
            }

            Uri uri = Uri.parse("assets:///" + data.get(i).getUrl());
            MediaSource m = mediaSourceFactory.createMediaSource(uri);
            concat.addMediaSource(m);

            if (data.get(i).getGfs() != null && !data.get(i).getGfs().isEmpty()) {
                //mGifLIst.add(data.get(i).getGfs());
            }

            if (data.get(i).getTyp() != null && !data.get(i).getTyp().equals("boomerang")) {
                mIndexTypeList.add(data.get(i).getTyp());
            }

            if (data.get(i).getTracks() != null && data.get(i).getTracks().getData() != null) {
                concat.addMediaSource(buildMediaSource(data.get(i).getTracks()));
            }
        }

        return concat;
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
            initializePlayer();
            updateInteractiveLayout(mPost);
        }
        showBoomerangOptions(false);
        gifIndex = 0;
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

    private void updatePlay(int position, String trackId) {

        if (mPost.getType().equals("story")) {

            showBoomerangOptions(false);

            final int currentIndex = mPlayer.getCurrentWindowIndex();
            if (position == 0) {
                mConcatMediaSource.moveMediaSource(currentIndex, currentIndex + 1);
            }

            mPlayer.next();
            //mPlayer.seekTo(currentIndex + 1, playbackPosition);
            removeMedia(currentIndex);
            resumePlayer();

            Tracks track = mTracksHashMap.get(trackId);
            if (track != null) {
                preparePlayer(track);
                updateInteractiveLayout(mPost);
            }
            //mainActivityViewModel.updateLiveScore(position);

        } else if (mPost.getType().equals("bvf")) {

            playbackPosition = mPlayer.getCurrentPosition();
            mPlayer.seekTo(2 * position, playbackPosition);
            //mainActivityViewModel.updateLiveScore(position);
        }

        Log.i(TAG, "updatePlay: " + playbackPosition);

    }

    private void removeMedia(int index) {

        Log.i(TAG, "removeMedia Size Before: " + mConcatMediaSource.getSize());
        Log.i(TAG, "removeMedia Size Before: " + mIndexTypeList.size());

        for (int i = 0; i <= index; i++) {

            //Remove the top item as the index will shift for every removal
            mConcatMediaSource.removeMediaSource(0);
            mIndexTypeList.remove(0);
        }

        Log.i(TAG, "removeMedia Size After: " + mConcatMediaSource.getSize());
        Log.i(TAG, "removeMedia Size After: " + mIndexTypeList.size());

    }

    private void tempCode() {

        binding.videoView.setVisibility(View.GONE);
        binding.sphericalView.setVisibility(View.VISIBLE);

        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), getString(R.string.app_name));
        ProgressiveMediaSource.Factory mediaSourceFactory =
                new ProgressiveMediaSource.Factory(dataSourceFactory);

        Uri uri = Uri.parse(URL_360);
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(uri);

        mPlayerSpherical.prepare(mediaSource, false, false);
        mPlayerSpherical.setPlayWhenReady(true);

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
        if (binding.sphericalView.getVisibility() == View.VISIBLE) {
            binding.sphericalView.registerListener();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause: " + fragmentIndex);
        setFragmentVisibility(false);
        resetPlayer();

        if (binding.sphericalView.getVisibility() == View.VISIBLE) {
            binding.sphericalView.unRegisterListener();
        }
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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

            //Log.i(TAG, "onPlayerStateChanged: " + playbackState);

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

            if (mPost.getType().equals("story")) {

                if (reason == Player.DISCONTINUITY_REASON_SEEK) {
                    return;
                }

                int index = mPlayer.getCurrentWindowIndex();
                String type = getTypeFromIndex(index);

                Log.i(TAG, "onPositionDiscontinuity: " + index);
                Log.i(TAG, "onPositionDiscontinuity: " + type);

                switch (type) {
                    case "base":
                        showBoomerangOptions(false);
                        break;
                    case "node":
                        pausePlayer();
                        showBoomerangOptions(true);
                        break;
                    default:
                        break;
                }


            } else if (mPost.getType().

                    equals("bvf")) {

                int sourceIndex = mPlayer.getCurrentWindowIndex();
                Log.i(TAG, "onPositionDiscontinuity: " + sourceIndex);


                if (sourceIndex % 2 == 1 || sourceIndex == 0) {
                    mPlayer.setRepeatMode(Player.REPEAT_MODE_ONE);
                    showBoomerangOptions(true);
                } else {
                    mPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
                    showBoomerangOptions(false);
                }
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

