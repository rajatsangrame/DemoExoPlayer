package com.example.rajat.demo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rajat.demo.model.Post;

import java.util.List;


/**
 * Created by Rajat Sangrame on 25/2/20.
 * http://github.com/rajatsangrame
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    List<Post> postList;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager,
                            @NonNull Lifecycle lifecycle,
                            List<Post> postList) {
        super(fragmentManager, lifecycle);
        this.postList = postList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new PostFragment(postList.get(0));
    }

    @Override
    public int getItemCount() {
        if (postList == null) return 0;
        return postList.size();
    }


}
