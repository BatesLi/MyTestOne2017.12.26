package com.example.test.adapter;

import android.content.Context;
import android.view.ViewGroup;
import com.example.test.bean.GankAndroidBean;
import com.example.test.mytesttwo.R;
import com.example.test.viewHolder.GanHuViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class GanHuAdapter extends RecyclerArrayAdapter<GankAndroidBean.ResultsBean> {

  public GanHuAdapter(Context context) {
    super(context);
  }

  @Override public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
    return new GanHuViewHolder(parent, R.layout.item_ganhu);
  }
}
