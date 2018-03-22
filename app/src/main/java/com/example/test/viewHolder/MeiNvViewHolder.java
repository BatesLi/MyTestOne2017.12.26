package com.example.test.viewHolder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.test.base.App;
import com.example.test.bean.GankAndroidBean;
import com.example.test.bean.GankMeiziInfo;
import com.example.test.mytesttwo.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class MeiNvViewHolder extends BaseViewHolder<GankMeiziInfo> {

  public ImageView imageView;
  public TextView textView;

  public MeiNvViewHolder(ViewGroup parent, @LayoutRes int res) {
    super(parent, res);

    imageView = $(R.id.item_iv);
    textView = $(R.id.txt_meinv);
  }
  @Override
  public void setData(final GankMeiziInfo data) {
    textView.setText(data.desc);

    Glide.with(App.getContext())
        .load(data.url)
        .into(imageView);
  }
}
