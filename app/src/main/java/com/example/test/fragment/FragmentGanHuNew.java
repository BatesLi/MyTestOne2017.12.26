package com.example.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.test.adapter.GanHuAdapterNew;
import com.example.test.api.GankApi;
import com.example.test.bean.GankAndroidBean;
import com.example.test.mytesttwo.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 重新写一遍干货的fragment
 */

public class FragmentGanHuNew extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

  @BindView(R.id.recycler_new) RecyclerView mRecyclerNew;
  @BindView(R.id.swipe_refresh_new) SwipeRefreshLayout mSwipeRefreshNew;

  public List<GankAndroidBean.ResultsBean> mData;
  public GanHuAdapterNew mGanHuAdapterNew;
  //public Context mContext;

  @Override
  public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_new_ganhu,container,false);
    ButterKnife.bind(this,view);

    initData();
    mRecyclerNew.setLayoutManager(new LinearLayoutManager(getActivity()));
    mData = new ArrayList<>();
    mGanHuAdapterNew  = new GanHuAdapterNew(getContext(),mData);
   // mGanHuAdapterNew.setOnItemClickListener(this);
    mRecyclerNew.setAdapter(mGanHuAdapterNew);
    mSwipeRefreshNew.setOnRefreshListener(this);
    return view;
  }
  public void initData() {
    Retrofit retrofit=new Retrofit.Builder()
        .baseUrl("http://gank.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    GankApi gankApi = retrofit.create(GankApi.class);
    final Call<GankAndroidBean> call = gankApi.getGankAndroidNew(10,0);
    call.enqueue(new Callback<GankAndroidBean>() {
      @Override
      public void onResponse(Call<GankAndroidBean> call, Response<GankAndroidBean> response) {
        GankAndroidBean gankAndroidBean = response.body();
        if (gankAndroidBean != null) {
            mData.addAll(gankAndroidBean.getResults());
            mGanHuAdapterNew.notifyDataSetChanged();
        }
      }

      @Override public void onFailure(Call<GankAndroidBean> call, Throwable t) {

      }
    });
  }

  @Override public void onRefresh() {
    mData.clear();
    mGanHuAdapterNew.notifyDataSetChanged();
    initData();
    mSwipeRefreshNew.setRefreshing(false);
  }
}
