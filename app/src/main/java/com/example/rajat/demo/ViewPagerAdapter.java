package com.example.rajat.demo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.rajat.demo.model.Post;

import java.util.List;


/**
 * Created by Rajat Sangrame on 25/2/20.
 * http://github.com/rajatsangrame
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<Post> postList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Post> postList) {
        super(fragmentActivity);
        this.postList = postList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new PostFragment(postList.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (postList == null) return 0;
        return postList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull FragmentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
    }
}
