package com.example.test.adapter;

import android.content.Context;
import android.view.ViewGroup;
import com.example.test.bean.ZhiHuBean;
import com.example.test.mytesttwo.R;
import com.example.test.viewHolder.NewViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 *新闻Adapter
 */

public class NewsAdapter extends RecyclerArrayAdapter<ZhiHuBean.StoriesBean> {

  public NewsAdapter(Context context) {
    super(context);
  }

  @Override public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
    return new NewViewHolder(parent, R.layout.item_news);
  }
}
