package com.example.test.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import butterknife.BindView;
import com.example.test.activity.ImagePagerActivity;
import com.example.test.adapter.MeiNvAdapter;
import com.example.test.api.RetrofitHelper;
import com.example.test.base.BaseLazyFragment;
import com.example.test.bean.GankMeiziBean;
import com.example.test.bean.GankMeiziInfo;
import com.example.test.mytesttwo.R;
import com.example.test.util.DataUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FragmentFuLi extends BaseLazyFragment
    implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{

  @BindView(R.id.easy_recycler) EasyRecyclerView mEasyRecycler;

  public List<GankMeiziInfo> mData = new ArrayList<>();
  public MeiNvAdapter mMeiNvAdapter;
  private Realm realm;

  @Override public int getLayout() {
    return R.layout.fragment_news;
  }

  @Override public void initViews(View view) {
    //realm = Realm.getDefaultInstance();
    StaggeredGridLayoutManager staggeredGridLayoutManager =
        new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    mEasyRecycler.setLayoutManager(staggeredGridLayoutManager);
    mMeiNvAdapter = new MeiNvAdapter(getActivity());
    mEasyRecycler.setAdapterWithProgress(mMeiNvAdapter);
    mEasyRecycler.setRefreshListener(this);
    mMeiNvAdapter.setMore(R.layout.view_more,this);
    mMeiNvAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
      @Override public void onItemClick(int position) {
            Intent intent = new Intent(getActivity(), ImagePagerActivity.class);
            intent.putExtra(ImagePagerActivity.POSITION,position);
            getContext().startActivity(intent);
      }
    });
  }

  @Override public void loadData() {
    mData.clear();
    mMeiNvAdapter.clear();
    getData();
  }
  public void getData() {
    RetrofitHelper
        .getGankAPI()
        .getGankMeizi(10,0)
        .compose(this.<GankMeiziBean>bindToLifecycle())
        .map(new Func1<GankMeiziBean, List<GankMeiziInfo>>() {

          @Override public List<GankMeiziInfo> call(GankMeiziBean gankMeiziBean) {
            return gankMeiziBean.getResults();
          }
        }).doOnNext(new Action1<List<GankMeiziInfo>>() {
      @Override public void call(List<GankMeiziInfo> gankMeiziInfos) {
        DataUtil.putGankMeiziCache(gankMeiziInfos);
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<GankMeiziInfo>>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(List<GankMeiziInfo> gankMeiziInfos) {
            mData.addAll(gankMeiziInfos);
            mMeiNvAdapter.addAll(gankMeiziInfos);
          }
        });
  }

  @Override public void onRefresh() {
   if (mMeiNvAdapter != null) {
     mData.clear();
     mMeiNvAdapter.clear();
     getData();
   }
  }

  @Override public void onLoadMore() {
    getData();
  }
}
