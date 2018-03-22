package com.example.test;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.test.base.BaseActivity;
import com.example.test.fragment.FragmentGanHuNew;
import com.example.test.fragment.FragmentNews;
import com.example.test.fragment.FragmentGanHu;
import com.example.test.fragment.FragmentFuLi;
import com.example.test.mytesttwo.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

  @BindView(R.id.viewpager) ViewPager mViewPager;
  @BindView(R.id.tab) TabLayout mTab;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  public String[] mTitle = {"日报","干货","图片"};
  public List<Fragment> fragmentList = new ArrayList<>();

  @Override protected void setLayout() {
    setContentView(getLayoutId());
    ButterKnife.bind(this);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override protected void initView() {
    mToolbar.setTitle("仿知乎日报");
    mToolbar.setTitleTextColor(Color.WHITE);
    fragmentList.add(new FragmentNews());
    fragmentList.add(new FragmentGanHuNew());
    fragmentList.add(new FragmentFuLi());
  }

  @Override protected void setListener() {

  }

  @Override protected void loadData() {
    MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
    mViewPager.setAdapter(adapter);
    mTab.setupWithViewPager(mViewPager);
    mTab.setTabMode(TabLayout.MODE_FIXED);
  }
  public class MyViewPagerAdapter extends FragmentPagerAdapter {
    /*FragmentPagerAdapter是android-support-v4支持包里面出现的一个新的适配器，继承自PagerAdapter，
    是专门用来给支持包中出现的ViewPager进行数据适配的
    * */
    public MyViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      return fragmentList.get(position);
    }
    @Override public CharSequence getPageTitle(int position) {
      return mTitle[position];
    }
    @Override public int getCount() {
      return fragmentList.size();
    }
  }
}
