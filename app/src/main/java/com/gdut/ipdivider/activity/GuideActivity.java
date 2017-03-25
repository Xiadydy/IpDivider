package com.gdut.ipdivider.activity;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.adapter.*;

import java.util.*;

import android.view.*;
import android.support.v4.view.*;
import android.widget.*;

import com.gdut.ipdivider.storage.*;

import android.content.*;
import android.os.*;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private LinearLayout ButtonlinearLayout;
    private int CurrentPage;
    private GuidePagerAdapter adapter;
    private Button btnStart;
    private View[] dot;
    private Context mContext;
    private ViewPager mViewPager;
    private CheckBox noMoreGuideChekBox;
    private List<View> pagerViewList;
    private int[] picId;

    public GuideActivity() {
        this.dot = new View[4];
    }

    private void iniView() {
        this.pagerViewList = new ArrayList<>();
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        for (int i = 0; i < this.picId.length; ++i) {
            final ImageView imageView = new ImageView(this.mContext);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(this.picId[i]);
            this.pagerViewList.add((View) imageView);
        }
        this.mViewPager = (ViewPager) this.findViewById(R.id.viewPager);
        this.adapter = new GuidePagerAdapter(this.pagerViewList);
        this.mViewPager.setAdapter(this.adapter);
        this.mViewPager.addOnPageChangeListener(this);
        this.mViewPager.setCurrentItem(0);
        this.CurrentPage = 0;
        this.initDot();
        this.ButtonlinearLayout = (LinearLayout) this.findViewById(R.id.guideBtnLinearLayout);
        this.btnStart = (Button) this.ButtonlinearLayout.findViewById(R.id.btnStartUse);
        (this.noMoreGuideChekBox = (CheckBox) this.ButtonlinearLayout.findViewById(R.id.check_noMoreGuide)).setOnCheckedChangeListener(this);
        this.btnStart.setOnClickListener(this);
    }

    private void initData() {
        this.picId = new int[]{R.drawable.guide_pic1, R.drawable.guide_pic2, R.drawable.guide_pic3, R.drawable.guide_pic4};
    }

    private void initDot() {
        final LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.dotLinaerLayout);
        for (int i = 0; i < this.picId.length; ++i) {
            final View[] dot = this.dot;
            final View child = linearLayout.getChildAt(i);
            (dot[i] = child).setOnClickListener(this);
            child.setEnabled(false);
            child.setTag(i);
        }
        this.dot[0].setEnabled(true);
    }

    public void onBackPressed() {
        if (this.CurrentPage != 0) {
            this.dot[this.CurrentPage].setEnabled(false);
            this.mViewPager.setCurrentItem(--this.CurrentPage);
            this.dot[this.CurrentPage].setEnabled(true);
        }
    }

    public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
        if (compoundButton.getId() == R.id.check_noMoreGuide && b) {
            MySharedPreferences.getInstance(MySharedPreferences.TYPE.record, (Context) this.getApplication()).edit().putBoolean("no_more_guide", true).commit();
        }
    }

    @Override
    public void onClick(final View view) {
        super.onClick(view);
        if (view.getTag() != null && this.picId[(int) view.getTag()] == view.getId()) {
            view.setEnabled(true);
            this.CurrentPage = (int) view.getTag();
            this.mViewPager.setCurrentItem(this.CurrentPage);
        }
        switch (view.getId()) {
            default: {
            }
            case R.id.btnStartUse: {
                this.startActivity(new Intent(this.getApplication(), (Class) MainActivity.class).addFlags(67108864));
            }
        }
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_guide);
        ((GuideActivity) (this.mContext = this)).initData();
        this.iniView();
    }

    @Override
    public void onPageScrollStateChanged(final int n) {
    }

    @Override
    public void onPageScrolled(final int n, final float n2, final int n3) {
    }

    @Override
    public void onPageSelected(final int currentPage) {
        this.dot[this.CurrentPage].setEnabled(false);
        this.CurrentPage = currentPage;
        this.dot[currentPage].setEnabled(true);
        if (currentPage == 3) {
            this.ButtonlinearLayout.setVisibility(View.VISIBLE);
            return;
        }
        this.ButtonlinearLayout.setVisibility(View.GONE);
    }
}
