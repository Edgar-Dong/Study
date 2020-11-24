package com.android.example.basics.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.example.R;
import com.android.example.common.log.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuperFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuperFragment3 extends LazyFragment {
    private static final String TAG = "SuperFragment3";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public SuperFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment SuperFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static SuperFragment3 newInstance(String param1) {
        SuperFragment3 fragment = new SuperFragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.get().d(TAG, "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.get().d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_super3, container, false);
        ((TextView) view.findViewById(R.id.content)).setText(String.format("hello %s", mParam1));
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Logger.get().d(TAG, "onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.get().d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.get().d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.get().d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.get().d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.get().d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.get().d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.get().d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.get().d(TAG, "onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //Logger.get().d(TAG, "setUserVisibleHint isVisibleToUser:" + isVisibleToUser);
    }

    @Override
    public void requestData() {
        Logger.get().d(TAG, "当前页面可见，从网络获取数据...");
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        Logger.get().d(TAG, "setMenuVisibility menuVisible:" + menuVisible);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.get().d(TAG, "onHiddenChanged hidden:" + hidden);
    }
}