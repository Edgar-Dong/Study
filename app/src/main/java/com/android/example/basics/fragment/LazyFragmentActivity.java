package com.android.example.basics.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.example.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class LazyFragmentActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_fragment);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                navigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView = findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuItem = item;
                switch (item.getItemId()) {
                    case R.id.navigation_fragment1:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_fragment2:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_fragment3:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        ViewPagerAdapter pagerAdapter = initPagerAdapter();
        //ViewStatePagerAdapter pagerAdapter = initStatePagerAdapter();

        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.setFragments(initFragments());

    }

    private ViewPagerAdapter initPagerAdapter() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        return viewPagerAdapter;
    }

    private ViewStatePagerAdapter initStatePagerAdapter() {
        ViewStatePagerAdapter viewStatePagerAdapter = new ViewStatePagerAdapter(getSupportFragmentManager());
        //ViewStatePagerAdapter viewStatePagerAdapter = new ViewStatePagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        return viewStatePagerAdapter;
    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SuperFragment1.newInstance("fragment1"));
        fragments.add(SuperFragment2.newInstance("fragment2"));
        fragments.add(SuperFragment3.newInstance("fragment3"));
        return fragments;
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void setFragments(List<Fragment> fragments) {
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            if (fragments != null) {
                return fragments.size();
            }
            return 0;
        }
    }

    static class ViewStatePagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public ViewStatePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public ViewStatePagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void setFragments(List<Fragment> fragments) {
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            if (fragments != null) {
                return fragments.size();
            }
            return 0;
        }
    }
}