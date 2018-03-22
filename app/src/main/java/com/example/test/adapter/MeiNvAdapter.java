package com.example.test.adapter;

import android.content.Context;
import android.view.ViewGroup;
import com.example.test.bean.GankMeiziInfo;
import com.example.test.mytesttwo.R;
import com.example.test.viewHolder.MeiNvViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class MeiNvAdapter extends RecyclerArrayAdapter<GankMeiziInfo>{

  public MeiNvAdapter(Context context) {
    super(context);
  }

  @Override public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
    return new MeiNvViewHolder(parent, R.layout.item_meizi);
  }
}
