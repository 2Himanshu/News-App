package com.example.newsappjava.fragment_adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newsappjava.MainActivity;
import com.example.newsappjava.fragment.LifeStyleFragment;
import com.example.newsappjava.fragment.PoliticalFragment;
import com.example.newsappjava.fragment.ScienceFragment;
import com.example.newsappjava.fragment.TechFragment;

public class NewsFragmentAdapter extends FragmentPagerAdapter {
    private final String[] tabs = MainActivity.tabList;
    private Context context;
    public NewsFragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }
//    public NewsFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position){
//            case 0:
//                new TechFragment();
//                break;
//
//            case 1:
//                new PoliticalFragment();
//                break;
//
//            case 2:
//                new ScienceFragment();
//
//            case 3:
//                new LifeStyleFragment();
//        }
//        return new PoliticalFragment();
//    }
//
//    @Override
//    public int getItemCount() {
//        return tabs.length;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new TechFragment();
        }
        else if(position==1){
            return new PoliticalFragment();
        }
        else if(position==2){
            return new ScienceFragment();
        }
        else{
            return new LifeStyleFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Technology";

            case 1:
                return "Politics";

            case 2:
                return "Science";

            case 3:
                return "LifeStyle";

            default:
                return null;
        }
    }
}
