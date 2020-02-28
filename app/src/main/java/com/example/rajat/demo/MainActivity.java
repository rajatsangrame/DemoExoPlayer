package com.example.rajat.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import com.example.rajat.demo.databinding.ActivityMainBinding;
import com.example.rajat.demo.mgpl.App;
import com.example.rajat.demo.model.Post;
import com.example.rajat.demo.model.Response;
import com.google.gson.Gson;

import java.util.List;

/**
 * Ref: https://proandroiddev.com/look-deep-into-viewpager2-13eb8e06e419
 * Ref: https://blog.mindorks.com/exploring-android-view-pager2-in-android
 * Ref: https://medium.com/better-programming/android-fragments-common-queries-mistakes-1c42e9f6b44f
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Gson gson = new Gson();
        Response response = gson.fromJson(Constants.JSON, Response.class);
        List<Post> postsList = response.getPosts();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(),
                postsList);
        binding.pager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        binding.pager.setOffscreenPageLimit(1);
        binding.pager.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //hideSystemUI();
        setWindowScreenParameters();
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
