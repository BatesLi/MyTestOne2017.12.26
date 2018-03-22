package com.example.test.viewHolder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.test.base.App;
import com.example.test.bean.ZhiHuBean;
import com.example.test.mytesttwo.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


public class NewViewHolder extends BaseViewHolder<ZhiHuBean.StoriesBean> {

  public ImageView mImageView;
  public TextView mTextView;

  public NewViewHolder(ViewGroup parent, @LayoutRes int res) {
    super(parent, res);

    mImageView = $(R.id.img_news);
    mTextView = $(R.id.txt_news_title);
  }
  public void setData(ZhiHuBean.StoriesBean data) {
    mTextView.setText(data.getTitle());
    Glide.with(App.getContext())
        .load(data.getImages().get(0))
        .into(mImageView);
  }
}
