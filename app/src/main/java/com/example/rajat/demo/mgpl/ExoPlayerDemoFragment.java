//package com.example.rajat.demo.mgpl;
//
//import android.animation.ValueAnimator;
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.SurfaceTexture;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import android.view.GestureDetector;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.TextureView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestManager;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.RequestOptions;
//import com.bumptech.glide.request.target.Target;
//import com.bumptech.glide.signature.ObjectKey;
//import com.crashlytics.android.Crashlytics;
////import com.danikula.videocache.HttpProxyCacheServer;
//import com.crashlytics.android.answers.EventLogger;
//import com.facebook.appevents.AppEventsLogger;
//import com.google.android.exoplayer2.C;
//import com.google.android.exoplayer2.DefaultLoadControl;
//import com.google.android.exoplayer2.DefaultRenderersFactory;
//import com.google.android.exoplayer2.ExoPlaybackException;
//import com.google.android.exoplayer2.ExoPlayerFactory;
//import com.google.android.exoplayer2.LoadControl;
//import com.google.android.exoplayer2.PlaybackParameters;
//import com.google.android.exoplayer2.Player;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.analytics.AnalyticsListener;
//import com.google.android.exoplayer2.audio.AudioAttributes;
//import com.google.android.exoplayer2.audio.AudioListener;
//import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
//import com.google.android.exoplayer2.extractor.Extractor;
//import com.google.android.exoplayer2.extractor.ExtractorsFactory;
//import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
//import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
//import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
//import com.google.android.exoplayer2.source.ExtractorMediaSource;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.source.MediaSourceEventListener;
//import com.google.android.exoplayer2.source.TrackGroupArray;
//import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
//import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
//import com.google.android.exoplayer2.trackselection.TrackSelection;
//import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
//import com.google.android.exoplayer2.trackselection.TrackSelector;
//import com.google.android.exoplayer2.upstream.BandwidthMeter;
//import com.google.android.exoplayer2.upstream.DataSpec;
//import com.google.android.exoplayer2.upstream.DefaultAllocator;
//import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
//import com.google.android.exoplayer2.upstream.TransferListener;
//import com.google.android.exoplayer2.util.Log;
//import com.google.android.exoplayer2.util.Util;
//import com.google.android.exoplayer2.video.VideoListener;
//import com.google.gson.Gson;
//import com.lib.AppConstant;
//import com.lib.model.NetworkSpeed;
//import com.lib.model.PlayAgain;
//
//import com.lib.model.UpdateFragment;
//import com.lib.model.feed.Template;
//import com.lib.storage.SharedPreferencesManager;
//import com.mgpl.analytics.YovoSocketEvents;
//import com.mgpl.appmanager.DialogManager;
//
//import com.mgpl.appupdate.DownloadService;
//import com.mgpl.common.RxBus;
//import com.mgpl.common.constants.CommonConstants;
//import com.mgpl.common.customviews.CustomPoppinsBoldTextView;
//import com.mgpl.grpc.DialerConnection;
//import com.mgpl.login.LoginBottmSheetFragment;
//import com.mgpl.network.DataService;
//import com.mgpl.network.RetrofitClientInstance;
//import com.mgpl.networkConnectivity.ConnectivityReceiver;
//import com.mgpl.videos.customview.texture_view.KeepSurfaceTextureView;
//import com.mgpl.videos.customview.OnSwipeTouchListener;
//import com.mgpl.videos.customview.texture_view.ScalableType;
//import com.mgpl.videos.home.BottomBarVisibilityModel;
//import com.mgpl.videos.home.InteractionItemClickListener;
//import com.mgpl.videos.home.ProfileFragment;
//import com.mgpl.videos.home.VideoHomeActivity;
//import com.lib.model.feed.Datum;
//import com.lib.model.feed.Gf;
//import com.lib.model.feed.Trk;
//import com.mgpl.videos.models.FtuiModel;
//import com.mgpl.videos.profile.OtherUserProfileActivity;
//import com.mgpl.videos.profile.ReportActivity;
//import com.mgpl.videos.profile.WatchProfileVideoActivity;
//import com.mgpl.videos.recorder.customview.template.InteractionTemplateLayout;
//import com.mgpl.videos.recorder.utils.RecorderFileUtils;
//import com.mgpl.videos.videoplayer.customview.InteractiveOptionsLayout;
//import com.mgpl.videos.videoplayer.util.AppUtils;
//import com.mgpl.videos.videoplayer.util.PlayerUtils;
//import com.mgpl.videoview.SingleVideoActivity;
//import com.vincan.medialoader.MediaLoader;
//import com.singular.sdk.Singular;
//import com.vincan.medialoader.DownloadManager;
//import com.vincan.medialoader.MediaLoader;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.Closeable;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.Socket;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.Callable;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import io.reactivex.Single;
//import io.reactivex.SingleObserver;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//import playy.PlayyOuterClass;
//import retrofit2.Call;
//import retrofit2.Callback;
//import rx.Subscription;
//import rx.functions.Action1;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.FileProvider;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import org.json.JSONObject;
//import org.parceler.Parcels;
//
///**
// * Created by Govind Singh on 6/8/19.
// * version V1.
// */
//public class ExoPlayerDemoFragment extends Fragment implements GestureDetector.OnGestureListener,
//        GestureDetector.OnDoubleTapListener, ExtractorMediaSource.EventListener, ExoPlayerDataConsumeListener.DataConsumedlistener
////        implements SensorEventListener
//{
//    private static final String DATA_KEY = "dataKey";
//    private static final String ISMAIN_FEED_KEY = "main_feed";
//    private static final String FROM_KEY = "main_feed";
//    private static final String CANVOTE_KEY = "canVote";
//    public InteractiveOptionsLayout interactiveOptionsLayout;
//    @BindView(R.id.interaction_temp_layout)
//    public InteractionTemplateLayout interactionTemplateLayout;
//    public FrameLayout mediaContainer;
//    public ImageView mediaCoverImage;
//    public ProgressBar progressBar;
//    public RequestManager requestManager;
//    boolean mRecombeeSent, isImageLoaded, isRemoveRecorded;
//    //    @BindView(R.id.video_exoplayer_view)
////    PlayerView mPlayerView;
//    @BindView(R.id.iv_user_profile)
//    ImageView mProfileImage;
//    @BindView(R.id.comment_layout)
//    LinearLayout mCommentLayout;
//    @BindView(R.id.like_layout)
//    LinearLayout mLikeLayout;
//    @BindView(R.id.options_view)
//    View optionsView;
//    @BindView(R.id.user_image)
//    ImageView userImageView;
//
//    @BindView(R.id.creator_name)
//    CustomPoppinsBoldTextView userNameTextView;
//    @BindView(R.id.user_info_layout)
//    View mUserInfoLayout;
//    private ExoPlayerDataConsumeListener exoPlayerDataConsumeListener;
//
//    boolean isScheduleFtui;
//
//    public String fromSection;
//    boolean isResumedAferBuffering = false;
//
//    private boolean isMainFeed, isNodeClicked;
//    String tempIds = "tGOC_VLWa,_aORYVLZa,bY7FlVYWa";
//
//    Handler mBufferHandler = new Handler();
//    Runnable mBufferingRunnable = new Runnable() {
//        @Override
//        public void run() {
//            loadingLayout.setVisibility(View.VISIBLE);
//            loadingLayout.setAlpha(0);
//            loadingLayout.animate().alpha(1).withEndAction(new Runnable() {
//                @Override
//                public void run() {
//                    if (loadingLayout != null) {
//                        loadingLayout.setVisibility(View.VISIBLE);
//                        laodingLottieBar.setVisibility(View.VISIBLE);
//                        retryNetworkButton.setVisibility(View.GONE);
//                    }
//                }
//            }).start();
//
//
//        }
//    };
//
//
//    @OnClick(R.id.text_copy)
//    public void postIdCopy() {
//        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getActivity().CLIPBOARD_SERVICE);
//        ClipData clip = ClipData.newPlainText("label", datum.getId());
//        clipboard.setPrimaryClip(clip);
////        Toast.makeText(getActivity(),datum.getId(),Toast.LENGTH_LONG).show();
//    }
//
//
//    private ValueAnimator timerAnimator;
//
//    {
//        timerAnimator = ValueAnimator.ofInt(INT_VOLUME_MIN, INT_VOLUME_MAX);
//        timerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                updateVolume(1);
//            }
//        });
//        timerAnimator.setDuration(800);
//    }
//
//    @BindView(R.id.top_layout)
//    View mTopLayout;
///*
//
//    @BindView(R.id.upload_error)
//    Button uploadError;
//*/
//
//    SharedPreferencesManager sharedPreferencesManager;
//    int i = 0;
////    @BindView(R.id.loading_bar)
////    View loadingBar;
//
//
//    boolean showScrollFtui;
//    boolean buffering;
//    Handler slowNetworkHandler = new Handler();
//    Runnable slowNetworkRunnable = new Runnable() {
//        @Override
//        public void run() {
//            if (getUserVisibleHint() && getView() != null) {
//                //handle slow connection
//            }
//        }
//    };
//
//    Runnable runnable = null;
//    Handler handler = new Handler();
//    Handler handler1 = new Handler();
//    Handler ftuiHandler = new Handler();
//    private int ftuiChild = 0;
//    Runnable ftuiRunnable = new Runnable() {
//        @Override
//        public void run() {
//            if (interactiveOptionsLayout.getChildCount() == 0) {
//                return;
//            }
//            int[] location = new int[2];
//            interactiveOptionsLayout.getChildAt(ftuiChild).getLocationOnScreen(location);
//            location[0] = location[0] + AppUtils.dpToPx(30);
//            location[1] = location[1] + AppUtils.dpToPx(40);
//            FtuiModel model = new FtuiModel("tap", location);
//            if (ftuiChild == 1) {
//                showScrollFtui = true;
//                model.setFinal(true);
//            } else if (ftuiChild == 0) {
//                model.setFinal(false);
//            }
//            RxBus.getInstance().send(model);
//            ftuiChild++;
//        }
//    };
//    //    @BindView(R.id.aspect_ratio_frame_layout)
////    AspectRatioFrameLayout aspectRatioFrameLayout;
//    int playingTrack = 0;
//    boolean isvisible;
//    private SimpleExoPlayer mPlayer;
//    private Unbinder unbinder;
//    private ConcatenatingMediaSource dynamicConcatenatingMediaSource;
//    private Datum datum = null;
//    private Subscription msubscription, mConnectAginaSubscription;
//    long masterNodeTime, childOneNodeTime, childTwoNodeTime;
//    long mGlobalTime;
//    private static final String SHARED_FOLDER = "shared";
//    private static final String SHARED_PROVIDER_AUTHORITY = BuildConfig.APPLICATION_ID + ".myfileprovider";
//    //    @BindView(R.id.like_count)
////    TextView likeCountTextView;
////    @BindView(R.id.comment_count)
////    TextView commentCountTextView;
////    @BindView(R.id.share_count)
////    TextView shareCountTextView;
////    @BindView(R.id.user_name)
////    TextView userNameTextView;
////    @BindView(R.id.caption_text)
////    TextView captionTextView;
////    @BindView(R.id.follow_button)
////    View followButton;
//    @BindView(R.id.loading_lottie_bar)
//    View laodingLottieBar;
//    @BindView(R.id.id_retry_network)
//    View retryNetworkButton;
//    @BindView(R.id.loading_layout)
//    View loadingLayout;
//    @BindView(R.id.user_temp_id)
//    TextView mUserID;
//
//    //Minimum Video you want to buffer while Playing
//    public static final int MIN_BUFFER_DURATION = 1000;
//    //Max Video you want to buffer during PlayBack
//    public static final int MAX_BUFFER_DURATION = 7000;
//    //Min Video you want to buffer before start Playing it
//    public static final int MIN_PLAYBACK_START_BUFFER = 0;
//    //Min video You want to buffer when user resumes video
//    public static final int MIN_PLAYBACK_RESUME_BUFFER = 0;
//
//
//    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//    private boolean isVideoWatched;
//    Handler uiHandler = new Handler();
//
//
//    LoadControl loadControl;
//
//
//    public void increaseCommentCount(int count) {
////        commentCountTextView.setText(count+"");
//    }
//
//
//    @OnClick(R.id.share_layout)
//    public void openShareSection() {
//        try {
//            shareList();
//        } catch (Exception e) {
//
//        }
//
//    }
//
//
//    public static ExoPlayerDemoFragment newInstance(Datum datum, boolean isMainFeed, String from) {
//        ExoPlayerDemoFragment fragment = new ExoPlayerDemoFragment();
//        Bundle bundle = new Bundle();
//
//        bundle.putParcelable(DATA_KEY, Parcels.wrap(datum));
//        fragment.isMainFeed = isMainFeed;
//        //  bundle.putString(ISMAIN_FEED_KEY, String.valueOf(isMainFeed));
//        Log.d("isMainFeed", String.valueOf(isMainFeed));
//        bundle.putString(FROM_KEY, from);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (getContext() instanceof VideoHomeActivity) {
//            if (mPlayer != null && getContext() != null && ((VideoHomeActivity) getContext()).cameraFragment == null) {
//                pause();
////            pauseWithCrossdFade();
//                handler.removeCallbacksAndMessages(runnable);
//                handler1.removeCallbacksAndMessages(runnable);
//                map.clear();
//            }
//        } else if (getContext() instanceof WatchProfileVideoActivity) {
//            pause();
//            handler.removeCallbacksAndMessages(runnable);
//            handler1.removeCallbacksAndMessages(runnable);
//            map.clear();
//        } else {
//            pause();
//            handler.removeCallbacksAndMessages(runnable);
//            handler1.removeCallbacksAndMessages(runnable);
//            map.clear();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (getContext() instanceof VideoHomeActivity) {
//            if (getContext() != null && HomeFrament.isVisible && ((VideoHomeActivity) getContext()).cameraFragment == null) {
//                if (mPlayer != null) {
////                mPlayer.setPlayWhenReady(true);
//                    resume();
//                }
//            }
//        } else if (getContext() instanceof WatchProfileVideoActivity) {
//            resume();
//        } else {
//            resume();
//        }
//    }
//
//    @BindView(R.id.texture_view)
//    KeepSurfaceTextureView textureView;
//
//    @BindView(R.id.back_button)
//    View backButton;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.layout_media_list_item1, container, false);
//        unbinder = ButterKnife.bind(this, rootView);
//        datum = Parcels.unwrap(getArguments().getParcelable(DATA_KEY));
//        Log.d("isMainFeed", String.valueOf(isMainFeed));
//        fromSection = this.getArguments().getString(FROM_KEY);
//        Log.d("fromSection", String.valueOf(fromSection));
//        mRecombeeSent = false;
//        isImageLoaded = false;
//        isRemoveRecorded = false;
//        mediaCoverImage = rootView.findViewById(R.id.ivMediaCoverImage);
//        if (!isMainFeed) {
//            mediaCoverImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        }
//
//        if (datum == null) {
//            Log.e("datum", "null");
//            if (getContext() != null) {
//                Intent i = getContext().getPackageManager().
//                        getLaunchIntentForPackage(getContext().getPackageName());
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                ((AppCompatActivity) getContext()).finish();
//            }
//            return rootView;
//        }
//        Log.d("oncreateView", datum.getId());
//        List<Trk> trkList = datum.getTrks();
//        for (int i = 0; i < trkList.size(); i++) {
//            if (TextUtils.isEmpty(trkList.get(i).getUrl())) {
//                continue;
//            }
//            if (getContext() instanceof VideoHomeActivity) {
//
//                ((VideoHomeActivity) getActivity()).startDownload(Utils.url(trkList.get(i).getUrl(), datum.getId()));
//                Log.i("checkFileUrl", "start download" + " = file name = ");
//                // ((VideoHomeActivity) getActivity()).startDownload(Utils.url(trkList.get(i).getUrl(), datum.getId()));
//            }
//
//        }
//
//        setUserImage();
//        textureView.setOnTouchListener(new OnSwipeTouchListener(getContext(), new OnSwipeTouchListener.OnSwipedListener() {
//            @Override
//            public void onSwiped(OnSwipeTouchListener.Direction direction) {
//                ///For quick debug enjoyment purpose only
//                if (direction == OnSwipeTouchListener.Direction.RIGHT_TO_LEFT) {
////                    if (mPlayer.getPlayWhenReady() && mPlayer
////                            .getPlaybackState() == Player.STATE_READY) {
////                        if (mPlayer.getCurrentWindowIndex() == 0) {
////                            mPlayer.seekTo(1, 0);
////                        }
////                    }
//                    mUserInfoLayout.performClick();
//                } else if (direction == OnSwipeTouchListener.Direction.LEFT_TO_RIGHT)
//                    if (mPlayer.getPlayWhenReady() && mPlayer
//                            .getPlaybackState() == Player.STATE_READY) {
//                        if (mPlayer.getCurrentWindowIndex() == 0) {
//                            mPlayer.seekTo(0, 0);
//                        } else if (mPlayer.getCurrentWindowIndex() % 2 != 0) {
//                            mPlayer.seekTo(0, 0);
//                        } else {
//                            mPlayer.seekTo(1, 0);
//                        }
//                    }
//                Log.e("swiped", "swiped_left");
//            }
//
//            @Override
//            public void singleTapUp() {
//
//            }
//        }));
//        setUpUi();
//        mediaContainer = rootView.findViewById(R.id.mediaContainer);
//        progressBar = rootView.findViewById(R.id.progressBar);
//        interactiveOptionsLayout = rootView.findViewById(R.id.ifram);
//        mUserID.setText(datum.getUserId() + "," + datum.getId());
//
//     /*   if (!TextUtils.isEmpty(datum.getAlias())) {
//            userNameTextView.setText(datum.getAlias());
//            Log.d("username",datum.getAlias());
//        } else {
//            userNameTextView.setText(datum.getUserName());
//        }
//*/
//        userNameTextView.setText(datum.getUserName());
//
// /*   uploadError.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("postId",datum.getId());
//                writeToFile(datum.getId());
//            }
//        });*/
////        mPlayerView.setShutterBackgroundColor(Color.TRANSPARENT);
////        mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
////        mPlayerView.setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS);
//        sharedPreferencesManager = new SharedPreferencesManager(getActivity().getSharedPreferences(AppConstant.BETTR_PREF, getActivity().MODE_PRIVATE));
//
//        getPlayer();
//        GestureDetector gestureDetector = new GestureDetector(getActivity(), this);
//        //String replaceString=datum.getTrks().get(0).getUrl().replace(".mp4",".jpg");
//        String replaceString = Utils.url("intro.jpg", datum.getId());
//        if (getContext() != null) {
//        /*    Glide.with(getContext())
//                    .load(replaceString)
//                    .into(mediaCoverImage);
//
//*/
//            GlideApp.with(this)
//                    .load(replaceString)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            isImageLoaded = false;
//
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            Log.d("imageLoaded", "imageL:oaded");
//                            isImageLoaded = true;
//                            loadingLayout.setVisibility(View.GONE);
//
//                            return false;
//                        }
//                    }).into(mediaCoverImage);
//
//
//        }
//        mTopLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });
//
////        Log.d("oncreateView","oncreateView"+datum.getId());
////        Glide.with(getActivity())
////                .load(datum.getUserId())
////                .into(mediaCoverImage);
//
//        if (getUserVisibleHint()) {//&& !mRadioHomePresenter.isViewSet()) {
//            Log.d("playTime", "playTime");
//            sendAnalytics("indivisual_post_reach", 0, false);
//            if (getContext() instanceof VideoHomeActivity) {
//                ((VideoHomeActivity) getContext()).updateWatchedConstant(datum.getId());
//            }
//            map.put(0, 0);
//            Log.d("visible", "visible");
//            if (msubscription == null) {
//                msubscription = RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        if (o instanceof UpdateFragment) {
//
//                            if (((UpdateFragment) o).isPlay()) {
//                                resume();
//                            } else {
//                                pause();
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        Crashlytics.logException(new Throwable("RxBus Exception from onError " + this.getClass().getSimpleName()));
//                    }
//                });
//            }
//
//
////            resume();
//        }
//        if (mConnectAginaSubscription == null) {
//            mConnectAginaSubscription = RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
//                @Override
//                public void call(Object o) {
//                    if (o instanceof PlayAgain && loadingLayout.getVisibility() == View.VISIBLE) {
//                        getPlayer();
////                        mPlayer.setPlayWhenReady(true);
//                        resume();
//                    }
//                }
//            }, new Action1<Throwable>() {
//                @Override
//                public void call(Throwable throwable) {
//                    Crashlytics.logException(new Throwable("RxBus Exception from onError " + this.getClass().getSimpleName()));
//                }
//            });
//        }
//
//        return rootView;
//    }
//
//    private void setUpUi() {
//        Log.e("main_feed", String.valueOf(isMainFeed));
//        if (isMainFeed) {
//            backButton.setVisibility(View.GONE);
//            mUserInfoLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), OtherUserProfileActivity.class);
//               /*     if (!TextUtils.isEmpty(datum.getAlias())) {
//                        intent.putExtra("isAlias", true);
//                        intent.putExtra("alias", datum.getAlias());
//                    }*/
//                    intent.putExtra("userName", datum.getUserName());
//                    Log.d("userID", datum.getUserId());
//                    intent.putExtra("userId", datum.getUserId());
//                    startActivity(intent);
//                    if (getContext() != null)
//                        ((AppCompatActivity) getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                }
//            });
//        } else {
//            if (!fromSection.equalsIgnoreCase(AppConstant.SINGLEPOST)) {
//                backButton.setVisibility(View.VISIBLE);
//                backButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ((AppCompatActivity) getContext()).onBackPressed();
//                    }
//                });
//            } else {
//                userNameTextView.setVisibility(View.GONE);
//            }
//        }
//
//
//        optionsView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                textureView.setPivotX(textureView.getWidth()/2);
////                textureView.setPivotY(textureView.getHeight()/2);
////                textureView.animate().scaleX(0.9f).scaleY(0.9f).start();
//                DialogManager.getInstance().setContext(getContext());
//                DialogManager.getInstance().showPostOptionsDialog(1, datum.getUserId(), datum.getId(), new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        textureView.animate().scaleX(1f).scaleY(1f).start();
//                    }
//                }, new ProfileFragment.ProfileOptionsClickListener() {
//                    @Override
//                    public void reportProfile(String postID) {
//                        reportUserProfile(postID);
//                    }
//
//                    @Override
//                    public void sharePost(String postID) {
//                        Utils.shareIndivisualPost(getActivity(), Utils.getPostUrl(postID), false);
//                    }
//
//                    @Override
//                    public void shareProfile(String userId) {
//                        String endPoint = "";
///*
//                        if (!TextUtils.isEmpty(datum.getAlias())) {
//                            endPoint = "app" + datum.getAlias() + "/" + datum.getUserId();
//
//                        } else {
//                            endPoint = datum.getUserName() + "/" + datum.getUserId();
//                        }*/
//                        endPoint = datum.getUserName();
//
//
//                        Utils.shareIndivisualPost(getActivity(), Utils.getProfileUrl(endPoint), true);
//
//                    }
//                }, false);
//            }
//        });
////        if(!TextUtils.isEmpty(datum.getCmt())){
////            commentCountTextView.setText(datum.getCmt());
////        }else{
////            commentCountTextView.setText("0");
////        }
////        if(!TextUtils.isEmpty(datum.getLike())) {
////            likeCountTextView.setText(datum.getLike());
////        }else{
////            likeCountTextView.setText("0");
////        }
////        if(!TextUtils.isEmpty(datum.getShareCount())) {
////            shareCountTextView.setText(datum.getShareCount());
////        }else{
////            shareCountTextView.setText("0");
////        }
////        userNameTextView.setText(datum.getId());
////        if(!TextUtils.isEmpty(datum.getMsg())) {
////            captionTextView.setText(datum.getMsg());
////        }else{
////            captionTextView.setVisibility(View.GONE);
////        }
//    }
//
//    private long lastPlayerPosition = 0;
//
//    private void reportUserProfile(String postID) {
//        Intent intent = new Intent(getContext(), ReportActivity.class);
//        intent.putExtra("postid", postID);
//        startActivity(intent);
//    }
//
//    private void getPlayer() {
//        if (datum == null)
//            return;
//
//        updateTracksData();
//	/* A TrackSelector that selects tracks provided by the MediaSource to be consumed by each of the available Renderers.
//	  A TrackSelector is injected when the player is created. */
//        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        TrackSelection.Factory videoTrackSelectionFactory =
//                new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        TrackSelector trackSelector =
//                new DefaultTrackSelector(videoTrackSelectionFactory);
//
//
//        // Create the player with previously created TrackSelector
//        //TODO: load controller added to make buffering smoother and faster for short videos
//
//        if (NetworkSpeed.SPEEDSELECTOR.equalsIgnoreCase(NetworkSpeed.SPEED240)) {
//            loadControl = new DefaultLoadControl.Builder()
//                    .setAllocator(new DefaultAllocator(true, 16))
//                    .setBufferDurationsMs(MIN_BUFFER_DURATION,
//                            4000,
//                            MIN_PLAYBACK_START_BUFFER,
//                            MIN_PLAYBACK_RESUME_BUFFER)
//                    .setTargetBufferBytes(-1)
//                    .setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();
//        } else {
//            loadControl = new DefaultLoadControl.Builder()
//                    .setAllocator(new DefaultAllocator(true, 16))
//                    .setBufferDurationsMs(MIN_BUFFER_DURATION,
//                            MAX_BUFFER_DURATION,
//                            MIN_PLAYBACK_START_BUFFER,
//                            MIN_PLAYBACK_RESUME_BUFFER)
//                    .setTargetBufferBytes(-1)
//                    .setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();
//        }
//
//        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultRenderersFactory(getContext()), trackSelector, loadControl);
////        AudioAttributes audioAttributes = new AudioAttributes.Builder()
////                .setUsage(C.USAGE_MEDIA)
////                .setContentType(C.CONTENT_TYPE_MOVIE)
////                .build();
////        mPlayer.setAudioAttributes(audioAttributes,true);
//      /*  if(volumeBooster==null){
//            volumeBooster = new VolumeBooster(true);
//        }*/
////        PlaybackParameters playbackParameters = new PlaybackParameters(3);
////        mPlayer.setPlaybackParameters(playbackParameters);
//        // mPlayer.addAudioListener(volumeBooster);
////        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector);
//
//
////        mPlayerView.setUseController(false);
////        mPlayerView.requestFocus();
//        mPlayer.addVideoListener(new VideoListener() {
//            @Override
//            public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
//                try {
//
//                    if (!isMainFeed && textureView != null) {
//                        textureView.scaleVideoSize(width, height, ScalableType.FIT_CENTER);
//                    }
//                } catch (Exception e) {
//
//                }
//            }
//        });
//
//        mPlayer.addListener(new Player.EventListener() {
//
//            @Override
//            public void onTracksChanged(TrackGroupArray trackGroups,
//                                        TrackSelectionArray trackSelections) {
//                Log.d("track", "changed");
//                System.out.println(trackSelections);
//
//
//            }
//
//            @Override
//            public void onLoadingChanged(boolean isLoading) {
//
//                Log.i("checkFileUrl", " = loading =  " + isLoading);
//            }
//
//            @Override
//            public void onPositionDiscontinuity(int reason) {
//
//                if (mPlayer == null)
//                    return;
//                int sourceIndex = mPlayer.getCurrentWindowIndex();
//
//                switch (sourceIndex) {
//                    case 1:
//                        if (masterNodeTime == 0) {
//                            masterNodeTime = mGlobalTime;// mPlayer.getCurrentPosition()/1000;
//                            Log.d("playertime", String.valueOf(mGlobalTime));
//                        }
//                        break;
//                    case 3:
//                        if (childOneNodeTime == 0) {
//                            childOneNodeTime = mGlobalTime;//mPlayer.getCurrentPosition()/1000;
//                            Log.d("childOneNodeTime", String.valueOf(mGlobalTime));
//                        }
//                        break;
//                    case 5:
//                        if (childTwoNodeTime == 0) {
//                            childTwoNodeTime = mGlobalTime;//mPlayer.getCurrentPosition()/1000;
//                            Log.d("childTwoNodeTime", String.valueOf(mGlobalTime));
//                        }
//                        break;
//                    default:
//                        break;
//                }
//
//                playingTrack = sourceIndex;
//                if (map != null && map.containsKey(0) && map.containsKey(2) && map.containsKey(4)) {
//                    if (getContext() instanceof VideoHomeActivity)
//                        ((VideoHomeActivity) getActivity()).updateViewpager();
//                    return;
//                }
//                if (map != null) {
//                    map.put(sourceIndex, sourceIndex);
//                    Log.d("mapSize", String.valueOf(map.size()));
//                    System.out.println(map);
//                }
//                if (sourceIndex % 2 == 1) {
//                    mPlayer.setRepeatMode(Player.REPEAT_MODE_ONE);
//                    showOptions(true);
//                    if (isScheduleFtui) {
//                        scheduleFtui(true);
//                    }
//                    isScheduleFtui = false;
//                } else {
//                    mPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
//                    showOptions(false);
//                    scheduleFtui(false);
//                    isScheduleFtui = true;
//                }
//                Log.d("currentPosition", String.valueOf(sourceIndex));
//            }
//
//            @Override
//            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                // if((!ConnectivityReceiver.isConnected()) && !isCached(datum.getTrks().get(playingTrack).getUrl())){
//               /* if((!ConnectivityReceiver.isConnected()) && !isCached(datum.getTrks().get(playingTrack).getUrl())){
//
//                    if(loadingLayout==null || loadingLayout.getVisibility()==View.VISIBLE&&getUserVisibleHint())
//                       return;
//
//                    loadingLayout.setVisibility(View.VISIBLE);
//                    loadingLayout.setAlpha(0);
//                    loadingLayout.animate().alpha(1).withEndAction(new Runnable() {
//                        @Override
//                        public void run() {
//                            if(loadingLayout!=null){
//                                loadingLayout.setVisibility(View.VISIBLE);
//                                laodingLottieBar.setVisibility(View.GONE);
//                                retryNetworkButton.setVisibility(View.VISIBLE);
//                                retryNetworkButton.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        getPlayer();
//                                    }
//                                });
//                            }
//                        }
//                    }).start();
//                    return;
//                }*/
//                if (playbackState == Player.STATE_BUFFERING) {
//                    Log.i("checkFileUrl", " = loading =  " + "STATE_BUFFERING");
//
////                    if (getUserVisibleHint() && (!isImageLoaded || isNodeClicked) && loadingLayout != null) {
//                    if (isVisible() && loadingLayout != null) {
//                        mBufferHandler.removeCallbacksAndMessages(null);
//                        mBufferHandler.postDelayed(mBufferingRunnable, 1000);
//                    }
//                } else {
//                    mBufferHandler.removeCallbacksAndMessages(null);
//                    if (loadingLayout != null)
//                        if (getContext() != null) {
//                            ((AppCompatActivity) getContext()).runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (loadingLayout != null) {
//                                        try {
//                                            loadingLayout.setVisibility(View.GONE);
//                                        } catch (Exception e) {
//                                        }
//                                    }
//                                }
//                            });
//                        }
//                }
//                switch (playbackState) {
//
//                    case Player.STATE_BUFFERING:
//                        if (getActivity() instanceof VideoHomeActivity)
//                            ((VideoHomeActivity) getActivity()).dismissSnackBar();
//
//                        break;
//                    case Player.STATE_ENDED:
//                        Log.d("track ended", "ended");
//                        handler.removeCallbacksAndMessages(runnable);
//                        break;
//                    case Player.STATE_IDLE:
//
//                        break;
//                    case Player.STATE_READY:
//                        if (getUserVisibleHint() && isResumed()) {
//                            if (mediaCoverImage.getVisibility() == View.VISIBLE) {
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        mediaCoverImage.setVisibility(View.GONE);
//                                    }
//                                }, 100);
//                            }
//                        }
//                        if (loadingLayout != null && loadingLayout.getVisibility() == View.VISIBLE) {
//                            loadingLayout.animate().alpha(0).withEndAction(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (loadingLayout != null) {
//                                        loadingLayout.setVisibility(View.GONE);
//                                    }
//                                }
//                            }).start();
//                        }
//
//                        if (!getUserVisibleHint()) {
//                            onPause();
//                            handler.removeCallbacksAndMessages(runnable);
//
//                        }
///*
//
//                        else {
//
//                            runnable = new Runnable() {
//                                @Override
//                                public void run() {
//                                    handler1.removeCallbacks(runnable);
//                                    int progress = (int) ((mPlayer.getCurrentPosition() * 100) / mPlayer.getDuration());
//                                     if (progress >= 98) {
//                                        next(true, dynamicConcatenatingMediaSource.getSize() - 1);
//                                        handler.removeCallbacksAndMessages(runnable);
//                                    } else {
////                                        showOptions(false);
//                                        handler.postDelayed(runnable, 20);
//                                    }
//                                }
//
//                            };
//                            if(mPlayer.getRepeatMode()!=com.google.android.exoplayer2.Player.REPEAT_MODE_ALL) {
//                                handler1.post(runnable);
//                            }else{
//                                ftuiHandler.removeCallbacksAndMessages(ftuiRunnable);
//                            }
//                        }
//*/
//
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onRepeatModeChanged(int repeatMode) {
//
//            }
//
//
//            @Override
//            public void onPlayerError(ExoPlaybackException error) {
//                System.out.println("exoplayerError" + error.getMessage());
//                String TAG = "ExoPlayerDemoFragment";
//                handler.removeCallbacksAndMessages(runnable);
//                handler1.removeCallbacksAndMessages(runnable);
//
//                try {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("postID", datum.getId());
//                    AppEventsLogger logger = AppEventsLogger.newLogger(getActivity());
//                    logger.logEvent("video_error",
//                            bundle);
//                } catch (Exception e) {
//
//                }
////
////            }
//
//                //               postId
////                    switch (error.type) {
////                    case ExoPlaybackException.TYPE_SOURCE:
////                       /* Toast.makeText(getActivity(), "no network connectiivy", Toast.LENGTH_LONG).show();
////                        Log.e(TAG, "TYPE_SOURCE: " + error.getSourceException().getMessage());
////*/
////                        Log.e(TAG, "TYPE_SOURCE: " + error.getSourceException().getMessage());
////
////                        if (getUserVisibleHint())
//////                            ((VideoHomeActivity) getActivity()).showSnackBar();
////
////                        break;
////
////                    case ExoPlaybackException.TYPE_RENDERER:
////                        Log.e(TAG, "TYPE_RENDERER: " + error.getRendererException().getMessage());
////                        break;
////
////                    case ExoPlaybackException.TYPE_UNEXPECTED:
////                        Log.e(TAG, "TYPE_UNEXPECTED: " + error.getUnexpectedException().getMessage());
////                        break;
////                }
//
//            }
//
//
//            @Override
//            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
//
//            }
//
//        });
//
//
//        dynamicConcatenatingMediaSource = new ConcatenatingMediaSource();
//        List<Trk> mediaUrl = datum.getTrks();
//
//        //for testing purpose
//        //String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
//        // exoPlayerDataConsumeListener = new ExoPlayerDataConsumeListener(getContext(), mPlayer, this, url);
//
//        if (mediaUrl != null) {
//
//            for (int i = 0; i < mediaUrl.size(); i++) {
//                if (TextUtils.isEmpty(mediaUrl.get(i).getUrl())) {
//                    continue;
//                }
//                //DefaultBandwidthMeter bandwidthMeterA = new DefaultBandwidthMeter();
//                /*DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
//                        Util.getUserAgent(getActivity(), String.valueOf(datum.getId())), bandwidthMeterA);*/
//                DefaultDataSourceFactory dataSourceFactory = null;
//                String url = "";
//                try {
//                    url = Utils.url(mediaUrl.get(i).getUrl(), datum.getId());
//                    File file = MediaLoader.getInstance(getContext()).getCacheFile(url);
//                    //Log.i("CheckFileName", " = FileName = " + file.getName() + " = url = " + url);
//                    exoPlayerDataConsumeListener = new ExoPlayerDataConsumeListener(getContext(), mPlayer, this, url);
//                    dataSourceFactory = exoPlayerDataConsumeListener.createDataSourceFactory(datum);
//                } catch (Exception e) {
//
//                }
//                Log.d("endUrl", url);
//
//
////                int trackno = i + 1;
////                https://playy-activation.s3.ap-south-1.amazonaws.com/JgGbZdW5CWHMzHL/8G_SHqtWR.mp4
////                String url = mediaUrl.get(i).getUrl();//"https://playy-test.s3.ap-south-1.amazonaws.com/" + datum.getId() + "/" + trackno + ".mp4";
////                String url = "https://playy-activation.s3.ap-south-1.amazonaws.com/"+datum.getUserId()+"/" + datum.getId() + ".mp4";
//
//                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//                MediaSource mediaSource = null;
//           /*     if (new File(mediaUrl.get(i).getUrl()).exists()) {
//                    mediaSource = new ExtractorMediaSource(Uri.parse(new File(mediaUrl.get(i).getUrl()).getAbsolutePath()),
//                            dataSourceFactory, extractorsFactory, null, null);
//                } else {
//                    HttpProxyCacheServer proxy = MyApplication.getProxy(getActivity());
//                    String proxyUrl = proxy.getProxyUrl(url);
//                    mediaSource = new ExtractorMediaSource(Uri.parse(proxyUrl),
//                            dataSourceFactory, extractorsFactory, null, null);
//                }*/
//                //  HttpProxyCacheServer proxy = MyApplication.getProxy(getActivity());
//                // String proxyUrl = proxy.getProxyUrl(url);
//                String proxyUrl;
//                ExtractorsFactory extractorsFactory1 = new ExtractorsFactory() {
//                    @Override
//                    public Extractor[] createExtractors() {
//                        Extractor[] extractor = new Extractor[2];
//                        extractor[0] = new Mp4Extractor();
//                        extractor[1] = new Mp3Extractor();
//                        return extractor;
//                    }
//                };
//                if (MediaLoader.getInstance(getContext()).isCached(url)) {
//                    android.util.Log.v("isFileExist", "= 3 =" + "true" + " = " + MediaLoader.getInstance(getContext()).getCacheFile(url).getName());
//                    proxyUrl = MediaLoader.getInstance(getContext()).getProxyUrl(url);
//                    Log.i("checkFileUrl", proxyUrl + " = playing from local" + datum.getUserName());
//
//                } else {
//                    String url240 = Utils.urlWithManualSpeed(mediaUrl.get(i).getUrl(), datum.getId(), NetworkSpeed.SPEED240);
//                    String url540 = Utils.urlWithManualSpeed(mediaUrl.get(i).getUrl(), datum.getId(), NetworkSpeed.SPEED540);
//                    if (MediaLoader.getInstance(getContext()).isCached(url240)) {
//                        proxyUrl = MediaLoader.getInstance(getContext()).getProxyUrl(url240);
//                        Log.i("checkFileUrl", proxyUrl + " = playing from Url" + datum.getUserName());
//                        android.util.Log.v("isFileExist", "= 4 =" + "true" + " = " + MediaLoader.getInstance(getContext()).getCacheFile(url240).getName());
//
//                    } else if (MediaLoader.getInstance(getContext()).isCached(url240)) {
//                        android.util.Log.v("isFileExist", "= 5 =" + "true");
//                        proxyUrl = MediaLoader.getInstance(getContext()).getProxyUrl(url540);
//                        Log.i("checkFileUrl", proxyUrl + " = playing from Url" + datum.getUserName());
//
//                    } else {
//                        android.util.Log.v("isFileExist", "= 6 =" + "true" + " = " + MediaLoader.getInstance(getContext()).getCacheFile(url).getName());
//                        proxyUrl = MediaLoader.getInstance(getContext()).getProxyUrl(url);
//                        Log.i("checkFileUrl", proxyUrl + " = playing from Url" + datum.getUserName());
//
//                    }
//
//                }
//
//                Log.d("pxoryUrl", proxyUrl);
//
//                mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                        .setExtractorsFactory(extractorsFactory1)
//                        .createMediaSource(Uri.parse(proxyUrl));
//
//
//                if (dynamicConcatenatingMediaSource.getSize() == 0) {
//                    dynamicConcatenatingMediaSource.addMediaSource(mediaSource);
//                    mPlayer.prepare(dynamicConcatenatingMediaSource);
//                    pause();
////                    pauseWithCrossdFade();
//                    mPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
//
//
//                } else {
//
//                    dynamicConcatenatingMediaSource.addMediaSource(mediaSource);
//                }
//
//
//            }
//
//          /*  MediaSource mediaSource = null;
//
//            String proxyUrl = "";
//            ExtractorsFactory extractorsFactory1 = new ExtractorsFactory() {
//                @Override
//                public Extractor[] createExtractors() {
//                    Extractor[] extractor = new Extractor[2];
//                    extractor[0] = new Mp4Extractor();
//                    extractor[1] = new Mp3Extractor();
//                    return extractor;
//                }
//            };
//
//            File fileUrl = MediaLoader.getInstance(getContext()).getCacheFile(url);
//            String name = fileUrl.getName() + ".mp4";
//            File file = new File(fileUrl.getParent(), name);
//            if(fileUrl.exists()) {
//                proxyUrl = Uri.fromFile(fileUrl).toString();
//                Log.i("checkFileUrl", proxyUrl + " = playing from local");
//            }
//            else if(file.exists())
//            {
//                proxyUrl = Uri.fromFile(file).toString();
//                Log.i("checkFileUrl", proxyUrl + " = playing from local");
//            }
//            else
//            {
//                Log.i("checkFileUrl", proxyUrl + " = playing from url");
//                proxyUrl =  MediaLoader.getInstance(getContext()).getProxyUrl(url);
//            }
//
//
//                    mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                    .setExtractorsFactory(extractorsFactory1)
//                    .createMediaSource(Uri.parse(proxyUrl));
//
//            *//**//*
//
//            if (dynamicConcatenatingMediaSource.getSize() == 0) {
//                dynamicConcatenatingMediaSource.addMediaSource(mediaSource);
//                mPlayer.prepare(dynamicConcatenatingMediaSource);
//                pause();
////                    pauseWithCrossdFade();
//                mPlayer.setRepeatMode(com.google.android.exoplayer2.Player.REPEAT_MODE_OFF);
//
//
//
//            } else {
//
//                dynamicConcatenatingMediaSource.addMediaSource(mediaSource);
//            }*/
//        }
//        if (textureView.getSurface() != null) {
//            initiateWithSurface();
//        }
//
//        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
//            @Override
//            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
//                initiateWithSurface();
//            }
//
//            @Override
//            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//                // Log.e("texture_state","changed,"+datum.getId());
//            }
//
//            @Override
//            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//                //  Log.e("texture_state", "destroyed,"+datum.getId());
//                if (mPlayer != null) {
//                    lastPlayerPosition = mPlayer.getCurrentPosition();
//                    return false;
//                } else {
//                    return true;
//                }
//            }
//
//            @Override
//            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//                if (!mRecombeeSent && mPlayer.getCurrentWindowIndex() == 0) {
//                    // if(!mRecombeeSent&&mPlayer.getCurrentWindowIndex()==0&&mPlayer.getCurrentPosition()>0){
//                    if (getUserVisibleHint() && !isRemoveRecorded) {
//                        isRemoveRecorded = true;
//                        Log.d("datumId", datum.getId());
//                        Log.d("remove", "remove");
//                 /*       if (getContext() instanceof VideoHomeActivity && !tempIds.contains(datum.getId())) {
//                            Log.d("removeFromList", "remove");
//                            sharedPreferencesManager.removeFeedFromList(datum);
//                        } else {
//                            Log.d("addedToList", "remove");
//
//                            sharedPreferencesManager.addFeed(datum.getId());
//                        }*/
//                        sharedPreferencesManager.removeFeedFromList(datum);
//                    }
//
//                    if (mPlayer.getCurrentPosition() / 1000 > 3) {
//
//                        mRecombeeSent = true;
//                        try {
//                            HashMap<String, Object> hashMap = new HashMap<>();
//                            hashMap.put("duration", mPlayer.getCurrentPosition() / 1000);
//                            hashMap.put("timestamp", MyApplication.getCurrentTime());
//                            hashMap.put("cascadeCreate", false);
//                            hashMap.put("recommId", datum.getRecordId());
//                            pushAnalytics("detailviews", hashMap);
//                            if (isMainFeed) {
//                                YovoSocketEvents.recordVideoViewEvent("int_view", datum.getId(), datum.getRecordId(), mPlayer.getCurrentPosition() / 1000, sharedPreferencesManager.getDeviceID());
//                            }
//
//                        } catch (Exception e) {
//
//                        }
//                    }
//                }
//                if (loadingLayout != null && loadingLayout.getVisibility() == View.VISIBLE) {
//                    mBufferHandler.removeCallbacksAndMessages(null);
//                    if (loadingLayout != null)
//                        loadingLayout.setVisibility(View.GONE);
//                }
//
//                mGlobalTime = mPlayer.getCurrentPosition() / 1000;
//            }
//        });
//    }
//
//    private void initiateWithSurface() {
//        if (fromSection.equalsIgnoreCase(AppConstant.FEED)) {
//            mPlayer.setVolume(0f);
//        }
//        mPlayer.setVideoSurface(textureView.getSurface());
//        mPlayer.seekTo(lastPlayerPosition);
//        mPlayer.setPlayWhenReady(true);
//        if (!(getUserVisibleHint() && isResumed())) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (mPlayer != null) {
//                        mPlayer.seekTo(0);
//                        mPlayer.setPlayWhenReady(false);
//                    }
//                }
//            }, 10);
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        if (getContext() != null && !((AppCompatActivity) getContext()).isFinishing()) {
//            Glide.with(this).clear(mediaCoverImage);
//            Log.e("clearing_image", "clearing_image");
//        }
//        Log.d("DestroyView", datum.getId());
//        if (mPlayer != null) {
//            releasePlayer();
//        }
//        unbinder.unbind();
//        super.onDestroyView();
//        if (mConnectAginaSubscription != null) {
//            mConnectAginaSubscription.unsubscribe();
//            mConnectAginaSubscription = null;
//        }
//        if (msubscription != null) {
//            msubscription.unsubscribe();
//            msubscription = null;
//        }
//        if (map != null) {
//            map.clear();
//            map = null;
//        }
//
//        if (dynamicConcatenatingMediaSource != null) {
//            dynamicConcatenatingMediaSource.clear();
//        }
//        if (loadControl != null) {
//            loadControl = null;
//        }
//
//        if (backButton != null) {
//            backButton.setOnClickListener(null);
//            backButton = null;
//        }
//        if (mUserInfoLayout != null) {
//            mUserInfoLayout.setOnClickListener(null);
//            mUserInfoLayout = null;
//
//        }
//        if (optionsView != null) {
//            optionsView.setOnClickListener(null);
//            optionsView = null;
//        }
//        if (textureView != null) {
//            textureView.setSurfaceTextureListener(null);
//            textureView = null;
//        }
//    }
//
//    private void releasePlayer() {
//        if (mPlayer != null) {
//            mPlayer.stop();
//            mPlayer.release();
//            mPlayer = null;
//        }
//        isResumedAferBuffering = false;
//        if (exoPlayerDataConsumeListener != null) {
//            exoPlayerDataConsumeListener.onDestroy();
//            exoPlayerDataConsumeListener = null;
//        }
//
//    }
//
//    private void scheduleFtui(boolean isBoomernag) {
//        if (isBoomernag) {
//            if (showScrollFtui) {
//                if (sharedPreferencesManager.getShowFtui("scroll")) {
//                    ftuiHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            RxBus.getInstance().send(new FtuiModel("scroll"));
//                        }
//                    }, 2000);
//                    ftuiHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            RxBus.getInstance().send(new FtuiModel("scroll"));
//                        }
//                    }, 5000);
//                }
//            } else {
//                if (sharedPreferencesManager.getShowFtui("tap")) {
//                    ftuiHandler.postDelayed(ftuiRunnable, 1000);
//                }
//            }
//        } else {
//            RxBus.getInstance().send(new FtuiModel("clear"));
//            ftuiHandler.removeCallbacksAndMessages(null);
//        }
//    }
//
//
//    private File getDirPath(String appName) {
//     /*   File firstDir = new File(getCacheDirectory(this), directory);
//        File secondDir = new File(firstDir, subDirectory);
//*/
//        File file = new File(DownloadService.getCacheDirectory(getActivity()), appName + ".mp4");
//        return new File(DownloadService.getCacheDirectory(getActivity()), appName + ".mp4");
//    }
//
//    public void next(boolean isloop, int position) {
//        playingTrack = position;
//        if (map.containsKey(1) && map.containsKey(2) && map.containsKey(3)) {
//            if (getContext() instanceof VideoHomeActivity)
//                ((VideoHomeActivity) getActivity()).updateViewpager();
//            return;
//        }
//        map.put(position, position);
//        System.out.println(map);
//
//
//        mPlayer.stop();
//  /*      if(!getUserVisibleHint())
//         return;
//*/
//        if (isloop) {
//            mPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
//            showOptions(true);
//            //((MainActivityExotest)context).setVisible(1);
//        } else {
//            mPlayer.setRepeatMode(Player.REPEAT_MODE_OFF);
//            showOptions(false);
//            // ((MainActivityExotest)context).setVisible(0);
//        }
////        videoPlayer.setRepeatMode(com.google.android.exoplayer2.Player.REPEAT_MODE_OFF);
//
//        mPlayer.prepare(dynamicConcatenatingMediaSource.getMediaSource(position));
///*        runnable = new Runnable() {
//            @Override
//            public void run() {
////                android.util.Log.d("progress", String.valueOf((int) ((mPlayer.getCurrentPosition() * 100) / mPlayer.getDuration())));
//                // long mCurrentPosition = player.getCurrentPosition() / 1000;
//                //  Log.d("progress",String.valueOf(mCurrentPosition));
//                int progress = (int) ((mPlayer.getCurrentPosition() * 100) / mPlayer.getDuration());
//                Log.d("progressnext",String.valueOf(progress)+datum.getId());
//                if (progress >= 95) {
//                    //next(true,currentPosition);
//
////                    videoPlayer.setPlayWhenReady(false);
//                    next(true, dynamicConcatenatingMediaSource.getSize() - 1);
//                    handler.removeCallbacksAndMessages(runnable);
//
////                    new Handler().postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
////                            if(videoPlayer.getPlaybackState()!= Player.STATE_READY) {
////                                 next(1);
////                            }
////                        }
////                    }, 8000);
//                } else {
//                    showOptions(false);
//                    handler.postDelayed(runnable, 20);
//                }
//            }
//        };
//        if(!isloop)
//        handler.postDelayed(runnable, 0);*/
////        mPlayer.setPlayWhenReady(true);
////        resume();
////        if(position==dynamicConcatenatingMediaSource.getSize()-1){
////            if(showScrollFtui){
////                if(sharedPreferencesManager.getShowFtui("scroll")) {
////                    ftuiHandler.postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
////                            RxBus.getInstance().send(new FtuiModel("scroll"));
////                        }
////                    }, 4000);
////                }
////            }else {
////                if(sharedPreferencesManager.getShowFtui("tap")) {
////                    ftuiHandler.postDelayed(ftuiRunnable, 4000);
////                }
////            }
////        }else{
////            ftuiHandler.removeCallbacksAndMessages(null);
////        }
//    }
//
//
//    private void showOptions(boolean boool) {
//        visibleOptions(boool);
//    }
//
//    public void visibleOptions(boolean bool) {
//        if (bool) {
//            interactionTemplateLayout.setVisibility(View.VISIBLE);
//            interactiveOptionsLayout.setVisibility(View.VISIBLE);
//        } else {
//            interactionTemplateLayout.setVisibility(View.GONE);
//            interactiveOptionsLayout.setVisibility(View.GONE);
//
//        }
//    }
//
//    private void sendAnalytics(String eventName, long playedTime, boolean isNodeClicked) {
//        Log.d("firedEvent", eventName + " " + datum.getId());
//        try {
//            Bundle args = new Bundle();
//            args.putString("deviceId", sharedPreferencesManager.getDeviceID());
//
//            if (eventName.equalsIgnoreCase("indivisual_post_left")) {
//                args.putLong("totalPlayedTime", playedTime);
//                args.putString("isNodeClicked", String.valueOf(isNodeClicked));
//            }
//
//            args.putString("postId", datum.getId());
//            args.putLong("currentTime", MyApplication.getCurrentTime());
//            args.putLong("localVc", sharedPreferencesManager.getLocalVc());
//
//            args.putInt("videoPosition", ((VideoHomeActivity) getActivity()).getCurrentVisiblePage());
//            AnalyticsManager.getInstance(getActivity())
//                    .pushFaceookEvent(eventName, args);
//        } catch (Exception e) {
//            Log.d("exception", e.getMessage());
//        }
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        lastPlayerPosition = 0;
//
//        if (isVisibleToUser && getView() != null) {
//            Log.d("playTime", "playTime");
//            Log.d("token", String.valueOf(sharedPreferencesManager.getUserTokenValue()));
//            sharedPreferencesManager.putWatchedVideoId(datum.getId());
//            sendAnalytics("indivisual_post_reach", 0, false);
//            if (msubscription == null) {
//                msubscription = RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        if (o instanceof UpdateFragment) {
//                            if (((UpdateFragment) o).isPlay()) {
//                                if (getContext() instanceof VideoHomeActivity) {
//                                    if (((VideoHomeActivity) getContext()).loginBottmSheetFragment == null) {
//                                        resume();
//                                    }
//                                } else {
//                                    resume();
//                                }
//                            } else {
//                                pause();
//                            }
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        Crashlytics.logException(new Throwable("RxBus Exception from onError " + this.getClass().getSimpleName()));
//                    }
//                });
//            }
//
////            RxBus.getInstance().send(new SoundInfo("winn"));
//            showOptions(false);
//            mPlayer.seekTo(0, 0);
//            mRecombeeSent = false;
//            resume();
//            //mPlayer.setPlayWhenReady(true);
//            map.put(0, 0);
//
//            try {
//                RxBus.getInstance().send(new BottomBarVisibilityModel(true));
//            } catch (Exception e) {
//            }
//
//
//        } else if (getView() != null) {
//
//            if (loadingLayout != null)
//                loadingLayout.setVisibility(View.GONE);
//            Log.e("invisible", "invisible");
//            if (getContext() instanceof VideoHomeActivity && !tempIds.contains(datum.getId()))
//                ((VideoHomeActivity) getActivity()).updateWatchedConstant(datum.getId());
//
//            mediaCoverImage.setVisibility(View.VISIBLE);
//            if (msubscription != null) {
//
//                msubscription.unsubscribe();
//                msubscription = null;
//            }
//
//
//            try {
//                long totalTime = masterNodeTime;
//                if (isNodeClicked) {
//                    totalTime = masterNodeTime + childOneNodeTime + childTwoNodeTime;
//                    if (mPlayer != null && (mPlayer.getCurrentWindowIndex() == 2 || mPlayer.getCurrentWindowIndex() == 4)) {
//                        totalTime = totalTime + (mPlayer.getCurrentPosition() / 1000);
//                    }
//
//                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
//                    hashMap.put("duration", totalTime);
//                    hashMap.put("timestamp", MyApplication.getCurrentTime());
//                    hashMap.put("recommId", datum.getRecordId());
//                    System.out.println("hashmap" + hashMap);
//                    pushAnalytics("cartadditions", hashMap);
//                    if (isMainFeed) {
//                        YovoSocketEvents.recordVideoViewEvent("vid_view", datum.getId(), datum.getRecordId(), totalTime, sharedPreferencesManager.getDeviceID());
//                    }
//                }
//                sendAnalytics("indivisual_post_left", totalTime, isNodeClicked);
//
//            } catch (Exception e) {
//                Log.d("exception", e.getMessage());
//
//            }
//
//            masterNodeTime = 0;
//            mGlobalTime = 0;
//            childTwoNodeTime = 0;
//            childOneNodeTime = 0;
//
//
//            map.clear();
//            mPlayer.seekTo(0, 0);
//            pause();
//            handler.removeCallbacksAndMessages(runnable);
//            handler1.removeCallbacksAndMessages(runnable);
//
//            interactiveOptionsLayout.setVisibility(View.GONE);
//            interactionTemplateLayout.setVisibility(View.GONE);
//
//            isNodeClicked = false;
//        }
//    }
//
//    public void pause() {
//        Log.e("player_run", "player_pause");
//        if (mPlayer != null) {
//            mPlayer.setPlayWhenReady(false);
//            if (timerAnimator != null && timerAnimator.isRunning())
//                timerAnimator.cancel();
//        }
//        map.clear();
//    }
//
//    public void resume() {
//        if (isResumedAferBuffering)
//            return;
//        Log.e("player_run", "player_run");
//        if (mPlayer != null) {
//
//            playWithCrossFade();
//        }
//    }
//
//    private void updateTracksData() {
//        if (datum == null || datum.getTrks() == null || datum.getTrks().size() == 0)
//            return;
//        Trk track = datum.getTrks().get(datum.getTrks().size() - 1);
//        if (track.getTemplate() == null) {
//            interactiveOptionsLayout.removeAllViews();
//            if (track.getGfs() == null)
//                return;
//            for (int i = 0; i < track.getGfs().size(); i++) {
//                Gf gif = track.getGfs().get(i);
//                interactiveOptionsLayout.addInteractiveAnswerItem(datum.getType(), gif.getNxt(), gif, new InteractionItemClickListener() {
//                    @Override
//                    public void onInteractionItemClicked(int position) {
//                        if (!isVideoWatched) {
//                            isVideoWatched = true;
//                            sharedPreferencesManager.setVideoWathed(sharedPreferencesManager.getVideoWatched() + 1);
//                            Log.e("video_watched_count", sharedPreferencesManager.getVideoWatched() + "");
//                        }
//                        interactiveOptionsLayout.setVisibility(View.GONE);
//                        updatePlay(position);
//                        updateTracksData();
////                        showScrollFtui = true;
//                    }
//                });
//            }
//            interactiveOptionsLayout.requestLayout();
//        } else {
//            Log.e("url_data", new Gson().toJson(datum));
//            if (interactionTemplateLayout.getChildCount() != 0) {
//                return;
//            }
//            List<Gf> gifList = track.getGfs();
//            Template template = track.getTemplate();
//            interactionTemplateLayout.setFont(template.getFont());
//            interactionTemplateLayout.setTemplateId(template.getTemplateID());
//            for (int i = 0; i < gifList.size(); i++) {
//                interactionTemplateLayout.addGf(gifList.get(i));
//            }
//            interactionTemplateLayout.setInteractiveItemClickListener(new InteractionTemplateLayout.InteractiveItemClickListener() {
//                @Override
//                public void interactiveItemClicked(int pos) {
//                    if (!isVideoWatched) {
//                        isVideoWatched = true;
//                        sharedPreferencesManager.setVideoWathed(sharedPreferencesManager.getVideoWatched() + 1);
//                        Log.e("video_watched_count", sharedPreferencesManager.getVideoWatched() + "");
//                    }
//                    interactionTemplateLayout.setVisibility(View.GONE);
//                    updatePlay(pos);
////                    showScrollFtui = true;
//                }
//            });
//            interactionTemplateLayout.setTemplate(template.getTemplateID());
////            interactionTemplateLayout.changeColor(template.getColorId());
//            interactionTemplateLayout.changeFont(template.getFont());
//        }
//
//    }
//
//    public void updatePlay(int position) {
// /*       if(datum.getId().equalsIgnoreCase(AppConstant.BLOCK_FEED_ID)){
//
//        }*/
//        RxBus.getInstance().send(new ViewPagerScrollEnabled(true));
//        isNodeClicked = true;
//        /*     if (position == 1) {
//             } else {
//                mPlayer.seekTo(4, 0);
//            }*/
//        try {
//            if (getContext() instanceof VideoHomeActivity) {
////                ((VideoHomeActivity) getContext()).variablePrizeRecieved();
////            }
//                if (datum.getRandomNumber() == position && datum.getRewardedAmount() != 0 && !datum.isAmountRewared()) {
//                    ((VideoHomeActivity) getActivity()).variablePrizeRecieved(datum);
//                }
//            }
//            mPlayer.seekTo(2 * position, 0);
//        } catch (Exception e) {
//        }
//
//
//        try {
//            Bundle args = new Bundle();
//            args.putString("postId", datum.getId());
//            args.putString("deviceId", sharedPreferencesManager.getDeviceID());
//            args.putString("nodePosition", String.valueOf(position));
//            AnalyticsManager.getInstance(getActivity())
//                    .pushFaceookEvent("video_node_click", args);
//        } catch (Exception e) {
//
//        }
//
//    }
//
//
//    private void shareList() throws IOException {
//        final File sharedFile = createFile();
//        final Uri uri = FileProvider.getUriForFile(getActivity(), SHARED_PROVIDER_AUTHORITY, sharedFile);
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "");
//        intent.putExtra(Intent.EXTRA_TEXT, "Join Playy");
//        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
//        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
//
//    }
//
//    @NonNull
//    private File createFile() throws IOException {
//        final Bitmap randomBitmap = ScreenshotUtils.getScreenShot(mTopLayout);
//        final File sharedFolder = new File(getActivity().getFilesDir(), SHARED_FOLDER);
//        if (sharedFolder.exists()) {
//            sharedFolder.delete();
//        }
//        sharedFolder.mkdirs();
//
//        final File sharedFile = File.createTempFile("picture", ".png", sharedFolder);
//
//        sharedFile.createNewFile();
//        writeBitmap(sharedFile, randomBitmap);
//        return sharedFile;
//    }
//
//    private static void writeBitmap(final File destination,
//                                    final Bitmap bitmap) {
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(destination);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            close(outputStream);
//        }
//    }
//
//    private static void close(final Closeable closeable) {
//        if (closeable == null) return;
//        try {
//            closeable.close();
//        } catch (IOException ignored) {
//        }
//    }
//
//
//    @Override
//    public boolean onDown(MotionEvent event) {
//
//        return true;
//    }
//
//    @Override
//    public boolean onFling(MotionEvent event1, MotionEvent event2,
//                           float velocityX, float velocityY) {
//
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent event) {
//
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//                            float distanceY) {
//
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent event) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent event) {
//
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTap(MotionEvent event) {
//
////        if(!sharedPreferencesManager.isUserLoggedIn()){
////            LoginBottmSheetFragment loginBottmSheetFragment = LoginBottmSheetFragment.newInstance(new LoginBottmSheetFragment.OnLoginListener() {
////                @Override
////                public void onloginResultReady(boolean success) {
////
////                }
////            });
////            loginBottmSheetFragment.show(getActivity().getSupportFragmentManager(),"login_fragment");
////
////            return true;
////        }
////        likePost();
////        return true;
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTapEvent(MotionEvent event) {
//
//        return true;
//    }
//
//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent event) {
//
//        return false;
//
//
//    }
//
//    private void closeSocketOutput(Socket socket) {
//        try {
//            if (socket.isConnected() && !socket.isOutputShutdown()) {
//                socket.shutdownOutput();
//            }
//        } catch (IOException e) {
//            // onError(new ProxyCacheException("Error closing socket output stream", e));
//        }
//    }
//
//    @Override
//    public void onLoadError(IOException error) {
//        Log.d("errorIOException", error.getMessage());
//    }
//
//    private int iVolume;
//
//    private final static int INT_VOLUME_MAX = 100;
//    private final static int INT_VOLUME_MIN = 30;
//    private final static float FLOAT_VOLUME_MAX = 1;
//    private final static float FLOAT_VOLUME_MIN = 0;
//    private final int fadeDuration = 1000;
//
//    public void playWithCrossFade() {
//        if (getContext() instanceof VideoHomeActivity) {
//            if (((VideoHomeActivity) getContext()).loginBottmSheetFragment == null) {
//                resumeVideo();
//            }
//        } else {
//            resumeVideo();
//        }
//    }
//
//    private void resumeVideo() {
//        if (!(mPlayer.getPlaybackState() == Player.STATE_READY && mPlayer.getPlayWhenReady())) {
//            if (textureView.getSurface() != null) {
//                mPlayer.setVideoSurface(textureView.getSurface());
//                mPlayer.seekTo(0);
//                mPlayer.setPlayWhenReady(true);
//            }
//        }
//        mPlayer.setVolume(INT_VOLUME_MIN);
//        timerAnimator.start();
//    }
//
//    private void updateVolume(int change) {
//        // increment or decrement depending on type of fade
//        iVolume = iVolume + change;
//
//        // ensure iVolume within boundaries
//        if (iVolume < INT_VOLUME_MIN)
//            iVolume = INT_VOLUME_MIN;
//        else if (iVolume > INT_VOLUME_MAX)
//            iVolume = INT_VOLUME_MAX;
//
//        // convert to float value
//        float fVolume = 1 - ((float) Math.log(INT_VOLUME_MAX - iVolume) / (float) Math.log(INT_VOLUME_MAX));
//
//        // ensure fVolume within boundaries
//        if (fVolume < FLOAT_VOLUME_MIN)
//            fVolume = FLOAT_VOLUME_MIN;
//        else if (fVolume > FLOAT_VOLUME_MAX)
//            fVolume = FLOAT_VOLUME_MAX;
//        if (mPlayer != null)
//            mPlayer.setVolume(fVolume);
//    }
//
//    boolean isCached(String url) {
//        if (getContext() != null)
//            return MediaLoader.getInstance(getContext()).isCached(url);
//        else
//            return false;
//    }
//
//
//    public void generateNoteOnSD(Context context, String sBody) {
//        File file = new File(Environment.getExternalStorageDirectory(), "errorText");
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        try {
//            File gpxfile = new File(file, "sample");
//
//            FileWriter writer = new FileWriter(gpxfile);
//            BufferedWriter fbw = new BufferedWriter(writer);
//            fbw.write(sBody);
//            fbw.newLine();
//            fbw.close();
//            Toast.makeText(context, "Saved your text", Toast.LENGTH_LONG).show();
//
//
//        } catch (Exception e) {
//        }
//    }
//
//
//    public static void writeToFile(String data) {
//        FileWriter writer = null;
//        try {
//
//            StringBuilder feed = readFromFile();
//            feed.append("'");
//            feed.append(data);
//            feed.append("'");
//            feed.append(",");
//
//            File file = new File(RecorderFileUtils.getOutputFilePath(), "config1.txt");
//            writer = new FileWriter(file);
//            writer.write(feed.toString());
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//    public static StringBuilder readFromFile() {
//        StringBuilder jsonBuilder = new StringBuilder();
//        FileReader fileReader = null;
//        BufferedReader bufferedReader = null;
//        try {
//            File file = new File(RecorderFileUtils.getOutputFilePath(), "config1.txt");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            fileReader = new FileReader(file);
//            bufferedReader = new BufferedReader(fileReader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                jsonBuilder.append(line);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//                if (fileReader != null)
//                    fileReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return jsonBuilder;
//    }
//
//
//    private void pushAnalytics(String endPoint, HashMap<String, Object> map) {
//        if (isMainFeed) {
//            Log.d("feedType", datum.getType());
//
//            //    if (datum.getType().equalsIgnoreCase("null")) {
//            Log.d("feedTypeInside", datum.getType());
//            HashMap<String, Object> hashMap = new HashMap<String, Object>();
//            hashMap.put("itemId", datum.getId());
//            hashMap.put("userId", sharedPreferencesManager.getDeviceID());
//            hashMap.put("cascadeCreate", true);
//            hashMap.putAll(map);
//            System.out.print("analyticsmap" + hashMap);
//            DataService service = RetrofitClientInstance.getRetrofitInstance().create(DataService.class);
//
//            Call<Void> call = service.setAnalytics(Utils.getEndUrl(endPoint), hashMap);
//            call.enqueue(new Callback<Void>() {
//                @Override
//                public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
//                    android.util.Log.d("successAnalytics", "successAnalytics");
//                    System.out.println("successAnalytics");
//                }
//
//                @Override
//                public void onFailure(Call<Void> call, Throwable t) {
//                    android.util.Log.d("exeption", t.getMessage());
//                    System.out.println("exeption" + t.getMessage());
//                }
//            });
//            //}
//        }
//
//    }
//
//    private void setUserImage() {
//        String url = "";
//     /*   if (!TextUtils.isEmpty(datum.getAlias())) {
//            url = CommonConstants.USER_PROFILE_BASE_URL + datum.getAlias() + ".jpg";
//        } else {
//            url  = CommonConstants.USER_PROFILE_BASE_URL + datum.getUserId() + ".jpg";
//        }*/
//        url = Utils.getImageUrl() + datum.getUserName() + ".jpg";
//
//        GlideApp.with(this)
//                .load(url)
//                .apply(RequestOptions.circleCropTransform())
//                .placeholder(R.drawable.ic_profile_placeholder)
//                .into(userImageView);
//        if (isMainFeed) {
//            userImageView.setVisibility(View.VISIBLE);
//        } else {
//            userImageView.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (msubscription != null) {
//
//            msubscription.unsubscribe();
//            msubscription = null;
//        }
//    }
//
//
//    @Override
//    public void onLoadingData() {
//
//        isResumedAferBuffering = true;
//        if (isVisible() && loadingLayout != null) {
//            mBufferHandler.removeCallbacksAndMessages(null);
//            mBufferHandler.post(mBufferingRunnable);
//            Log.i("checkBuffering", "visible");
//        }
//    }
//
//    @Override
//    public void onLoadingDataComplete() {
//
//        isResumedAferBuffering = false;
//        mBufferHandler.removeCallbacksAndMessages(null);
//        if (loadingLayout != null)
//            loadingLayout.setVisibility(View.GONE);
//        Log.i("checkBuffering", "gone");
//    }
//
//
///*
//    public static long getCachePercent(SharedPreferencesManager sharedPreferencesManager, String fileName)
//    {
//        File file = MediaLoader.getInstance(getContext()).getCacheFile(url);
//        int cachePercentage;
//        HashMap<String, String[]> hashMap;
//        hashMap = sharedPreferencesManager.getVideoInfo();
//        if (hashMap != null) {
//            String[] videoInfo = hashMap.get(fileName);
//            if (videoInfo != null) {
//                long fileLength = Long.parseLong(videoInfo[0]);
//                long contentLength = Long.parseLong(videoInfo[1]);
//                cachePercentage = (contentLength == 0) ? 100 : (int) (fileLength * 100 / contentLength);
//                Log.v("checkFileUrl", "= file name =" + fileName + " = fileLength = " + videoInfo[0] + " = contentLength = " + videoInfo[1]);
//                return cachePercentage;
//            }
//        }
//        return 0;
//    }
//*/
//
//
//}