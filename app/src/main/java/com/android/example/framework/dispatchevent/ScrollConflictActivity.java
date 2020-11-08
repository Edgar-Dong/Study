package com.android.example.framework.dispatchevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.example.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class ScrollConflictActivity extends AppCompatActivity {

    private BadViewPager mViewPager;
    private List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict);
        initViews();
        initData(true);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        mViews = new ArrayList<>();
    }

    private void initData(final boolean isListView) {
        Flowable.just("view1", "view2", "view3", "view4").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                View view;
                if (isListView) {
                    //ListView listView = new ListView(ScrollConflictActivity.this);
                    FixListView listView = new FixListView(ScrollConflictActivity.this);
                    final ArrayList<String> datas = new ArrayList<>();
                    Flowable.range(0, 50).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            datas.add("data" + integer);
                        }
                    });
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ScrollConflictActivity.this, android.R.layout.simple_list_item_1, datas);
                    listView.setAdapter(adapter);
                    view = listView;
                } else {
                    TextView textView = new TextView(ScrollConflictActivity.this);
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(s);
                    //textView.setClickable(true);
                    view = textView;
                }
                mViews.add(view);
            }
        });
        mViewPager.setAdapter(new MyPagerAdapter(mViews));
    }

    static class MyPagerAdapter extends PagerAdapter {

        private List<View> mViews;

        public MyPagerAdapter(List<View> views) {
            mViews = views;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = mViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }
    }
}