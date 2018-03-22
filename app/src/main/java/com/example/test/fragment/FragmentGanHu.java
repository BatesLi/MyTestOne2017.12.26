package com.example.test.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import butterknife.BindView;
import com.example.test.adapter.GanHuAdapter;
import com.example.test.api.RetrofitHelper;
import com.example.test.base.BaseLazyFragment;
import com.example.test.bean.GankAndroidBean;
import com.example.test.mytesttwo.R;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 干货
 */

public class FragmentGanHu extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener{

  @BindView(R.id.easy_recycler) EasyRecyclerView mEasyRecyclerGanHu;
  public GanHuAdapter mGanHuAdapter;

  public static final int page = 0;

  @Override public int getLayout() {
    return R.layout.fragment_news;
  }

  @Override public void initViews(View view) {
    mEasyRecyclerGanHu.setLayoutManager(new LinearLayoutManager(getActivity()));
    mEasyRecyclerGanHu.getSwipeToRefresh().setColorSchemeResources(R.color.colorPrimary);
    /*DividerDecoration
        itemDecoration = new DividerDecoration(Color.BLACK, WindowU.dip2px(getContext(),0.5f), 0,0);//颜色 & 高度 & 左边距 & 右边距
    gankAndroidErv.addItemDecoration(itemDecoration);*/
    mGanHuAdapter=new GanHuAdapter(getActivity());
    mEasyRecyclerGanHu.setAdapterWithProgress(mGanHuAdapter);
    mEasyRecyclerGanHu.setRefreshListener(this);
  }

  @Override public void loadData() {
    mGanHuAdapter.clear();
    getGanHuData(page);
  }
  private void getGanHuData(int page) {
    RetrofitHelper
        .getGankAPI()
        .getGankAndroid(10,page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<GankAndroidBean>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(GankAndroidBean gankAndroidBean) {
            if (gankAndroidBean != null) {
                gankAndroidBean.getResults();
            }
          }
        });
  }

  @Override public void onRefresh() {
    mGanHuAdapter.clear();
    getGanHuData(page);
  }
}
