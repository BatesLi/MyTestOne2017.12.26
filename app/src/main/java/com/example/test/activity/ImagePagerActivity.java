package com.example.test.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.test.base.App;
import com.example.test.base.BaseActivity;
import com.example.test.bean.ResultsBean;
import com.example.test.fragment.FragmentFuLiDetail;
import com.example.test.mytesttwo.R;
import io.realm.Realm;
import io.realm.RealmResults;

public class ImagePagerActivity extends BaseActivity {

  @BindView(R.id.app_bar_layout) AppBarLayout mAppBarLayout;
  @BindView(R.id.toolbar_image_detail) Toolbar mToolbarImageDetail;
  @BindView(R.id.viewpager_image_detail) ViewPager mViewImageDetail;

  private RealmResults<ResultsBean> gankMeizis;
  public MeiViewPagerAdapter meiViewPagerAdapter;
  public static String POSITION = "position";
  private Realm realm;
  private String url;
  //当前图片的位置
  private int currentIndex;

  @Override protected void setLayout() {
    setContentView(getLayoutId());
    ButterKnife.bind(this);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_image_pager;
  }

  @Override protected void initView() {
    Intent intent = getIntent();
    //传入并获取item的position位置
    currentIndex = intent.getIntExtra(POSITION,-1);
    //fragmentList.add(new FragmentFuLiDetail());
    realm = Realm.getDefaultInstance();
    gankMeizis = realm.where(ResultsBean.class).findAll();
    initToolbar();
  }

  @Override protected void setListener() {

  }

  @Override protected void loadData() {
    meiViewPagerAdapter = new MeiViewPagerAdapter(getSupportFragmentManager());
    mViewImageDetail.setAdapter(meiViewPagerAdapter);
  }
  @Override
  protected void onResume() {
    super.onResume();
    /*setCurrentItem(int index)方法主要用来制定初始化的页面,
        例如加入3个页面通过setCurrentItem(0)制定第一个页面为当前页面*/
    mViewImageDetail.setCurrentItem(currentIndex);
    url = gankMeizis.get(currentIndex).getUrl();
  }

  public void initToolbar() {
    mToolbarImageDetail.setNavigationIcon(R.drawable.ic_back);
    mAppBarLayout.setAlpha(0.5f);
    mToolbarImageDetail.setBackgroundResource(R.color.black_90);
    mAppBarLayout.setBackgroundResource(R.color.black_90);
    mToolbarImageDetail.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onBackPressed();
      }
    });
    mToolbarImageDetail.inflateMenu(R.menu.menu_mei);
    mToolbarImageDetail.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.mei_save:
            Toast.makeText(App.getContext(),"保存",Toast.LENGTH_LONG).show();
            break;
          case R.id.share:
            shareImage();
            break;
        }
        return false;
      }
    });
  }
  private void shareImage() {
    Intent shareIntent = new Intent();
    shareIntent.setAction(Intent.ACTION_SEND);
    String url = "http://7xi8d6.com1.z0.glb.clouddn.com/20171227115959_lmlLZ3_Screenshot.jpeg";
    //url的值,其实需要的是fragment3里面的由retrofit获取到的url值(返回数据)
    //url地址值,暂时由固定值来传入,之后弄明白RxJava异步线程之后在重构此部分
    shareIntent.putExtra(Intent.EXTRA_STREAM,url);
    shareIntent.setType("image/jpeg");
    this.startActivity(Intent.createChooser(shareIntent, "分享图片"));
  }

  public class MeiViewPagerAdapter extends FragmentPagerAdapter {

    public  MeiViewPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
     // return fragmentList.get(position);
      return FragmentFuLiDetail.newInstance(gankMeizis.get(position).getUrl());
    }

    @Override public int getCount() {
      return gankMeizis.size();
    }
  }
}
