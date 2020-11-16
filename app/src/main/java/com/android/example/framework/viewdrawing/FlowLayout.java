package com.android.example.framework.viewdrawing;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.android.example.common.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:無忌
 * @date:2020/11/10
 * @description:
 */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getSize(heightMeasureSpec);

        Logger.get().d(TAG, String.format("onMeasure(sizeWidth=%s, sizeHeight=%s)", sizeWidth, sizeHeight));

        int width = 0, height = 0;
        int lineWidth = 0, lineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            if (lineWidth + childWidth > sizeWidth) {
                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }

        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? width : sizeWidth, modeHeight == MeasureSpec.EXACTLY ? height : sizeHeight);
    }

    private List<List<View>> mViewList = new ArrayList<>();
    private List<Integer> mLineHeightList = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mViewList.clear();
        mLineHeightList.clear();

        int width = getWidth();
        int lineWidth = 0, lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (childWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + lineWidth > width) {
                mLineHeightList.add(lineHeight);
                mViewList.add(lineViews);
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }
            lineWidth += childWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
            lineViews.add(child);
        }
        mLineHeightList.add(lineHeight);
        mViewList.add(lineViews);

        int left = 0, top = 0;
        for (int i = 0; i < mViewList.size(); i++) {
            lineViews = mViewList.get(i);
            lineHeight = mLineHeightList.get(i);
            Logger.get().d(TAG, "第" + i + "行视图个数：" + lineViews.size());
            Logger.get().d(TAG, "第" + i + "行行高：" + lineHeight);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                Logger.get().d(TAG, "第" + i + "行第" + j + "个元素,(l:" + lc + ", t:" + tc + ", r:" + rc + ", b:" + bc + ")");
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }
}
