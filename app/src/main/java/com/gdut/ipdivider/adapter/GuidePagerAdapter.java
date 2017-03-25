package com.gdut.ipdivider.adapter;

import java.util.*;
import android.view.*;
import android.support.v4.view.*;

public class GuidePagerAdapter extends PagerAdapter
{
    private List<View> mViewList;

    public GuidePagerAdapter(final List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(final ViewGroup viewGroup, final int n, final Object o) {
        ((ViewPager)viewGroup).removeView(this.mViewList.get(n));
    }

    @Override
    public void finishUpdate(final ViewGroup viewGroup) {
        super.finishUpdate(viewGroup);
    }

    @Override
    public int getCount() {
        if (this.mViewList.size() != 0) {
            return this.mViewList.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(final ViewGroup viewGroup, final int n) {
        ((ViewPager)viewGroup).addView((View)this.mViewList.get(n), 0);
        return this.mViewList.get(n);
    }

    @Override
    public boolean isViewFromObject(final View view, final Object o) {
        return view == o;
    }
}
