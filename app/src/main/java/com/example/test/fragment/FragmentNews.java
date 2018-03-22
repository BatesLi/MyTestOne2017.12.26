package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import com.example.test.activity.ImagePagerActivity;
import com.example.test.activity.NewsDetailActivity;
import com.example.test.adapter.MeiNvAdapter;
import com.example.test.adapter.NewsAdapter;
import com.example.test.api.RetrofitHelper;
import com.example.test.base.BaseLazyFragment;
import com.example.test.bean.ZhiHuBean;
import com.example.test.mytesttwo.R;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FragmentNews extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener,
    RecyclerArrayAdapter.OnLoadMoreListener{

  @BindView(R.id.easy_recycler) EasyRecyclerView mEasyRecycler;

  public NewsAdapter mNewsApapter;
  public List<ZhiHuBean.StoriesBean> mNewsData;
  //当前日期
  private String currentDate;
  private Handler handler = new Handler();

  @Override public int getLayout() {
    return R.layout.fragment_news;
  }

  @Override public void initViews(View view) {
    mEasyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    mNewsApapter = new NewsAdapter(getActivity());
    mEasyRecycler.setAdapterWithProgress(mNewsApapter);
    mEasyRecycler.setRefreshListener(this);
    mNewsApapter.setMore(R.layout.view_more,this);
    mNewsApapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
      @Override public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        //此时""是分配了内存空间，值为空字符串，是相对的空,是一种有值（值存在为空字串）
        Bundle bundle = new Bundle();
        bundle.putString(NewsDetailActivity.ID,mNewsApapter.getItem(position).getId()+"");
        bundle.putString(NewsDetailActivity.TITLE,mNewsApapter.getItem(position).getTitle());
        intent.putExtras(bundle);
        getContext().startActivity(intent);
      }
    });
  }

  @Override public void loadData() {
    getNewsData();
  }

  public void getNewsData() {
    RetrofitHelper
        .getZhiHuAPI()
        .getLastZhihuBean()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ZhiHuBean>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(ZhiHuBean zhiHuBean) {
            if (zhiHuBean != null) {
                currentDate = zhiHuBean.getDate();
                if (zhiHuBean.getStories() != null) {
                    mNewsApapter.addAll(zhiHuBean.getStories());
                }
            }
          }
        });
  }
  private void getNewsDailyData() {
    RetrofitHelper
        .getZhiHuAPI()
        .getDailyZhihuBean(currentDate)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ZhiHuBean>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(ZhiHuBean zhiHuBean) {
            if (zhiHuBean != null) {
              currentDate = zhiHuBean.getDate();
              if (zhiHuBean.getStories() != null) {
                mNewsApapter.addAll(zhiHuBean.getStories());
              }
            }
          }
        });
  }
  @Override public void onRefresh() {
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        mNewsApapter.clear();
        getNewsData();
      }
    },2000);
  }

  @Override public void onLoadMore() {
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        getNewsDailyData();
      }
    },2000);
  }
}
