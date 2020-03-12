package com.example.rajat.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.example.rajat.demo.databinding.ActivityMainBinding;
import com.example.rajat.demo.mgpl.App;
import com.example.rajat.demo.model.Post;
import com.example.rajat.demo.model.Response;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref: https://proandroiddev.com/look-deep-into-viewpager2-13eb8e06e419
 * Ref: https://blog.mindorks.com/exploring-android-view-pager2-in-android
 * Ref: https://medium.com/better-programming/android-fragments-common-queries-mistakes-1c42e9f6b44f
 * Ref: https://stackoverflow.com/questions/57885849/in-androidx-fragment-app-fragment-setuservisiblehint-is-deprecated-and-not-exec/57886441?noredirect=1#comment106884235_57886441
 * <p>
 * Ref: offscreenPageLimit = 1
 * https://juejin.im/post/5cda3964f265da035d0c9d8f
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        Gson gson = new Gson();
        Response response = gson.fromJson(Constants.STORY_JSON, Response.class);

        List<Post> postsList = response.getPosts();
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, postsList);
        binding.pager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        binding.pager.setOffscreenPageLimit(1);
        binding.pager.setAdapter(adapter);
        disableCacheOfViewPager();
        viewModel.updateLiveScore(0);
        viewModel.getLiveScore().observe(this, score -> {

            binding.tvScore.setText(String.format("Score: %s", score));
            Log.i(TAG, "onCreate: " + score);
        });

    }

    /**
     * ViewPager2 is a @{@link RecyclerView}.
     */
    private void disableCacheOfViewPager() {

        try {

            ((RecyclerView) binding.pager.getChildAt(0)).getLayoutManager().setItemPrefetchEnabled(false);
            ((RecyclerView) binding.pager.getChildAt(0)).setItemViewCacheSize(0);

        } catch (NullPointerException e) {

            Log.i(TAG, "disableCacheOfViewPager: " + e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        //hideSystemUI();
        setWindowScreenParameters();
    }

    List<Fragment> getFragmentList(List<Post> postList) {

        List<Fragment> list = new ArrayList<>();

        for (Post post : postList) {
            list.add(new PostFragment(post));
        }
        return list;
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    void setWindowScreenParameters() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        App.setScreenHeight(size.y);
        App.setScreenWidth(size.x);
    }

}
