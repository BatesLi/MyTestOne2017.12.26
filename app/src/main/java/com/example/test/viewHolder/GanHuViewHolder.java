package com.example.test.viewHolder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.test.bean.GankAndroidBean;
import com.example.test.mytesttwo.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class GanHuViewHolder extends BaseViewHolder<GankAndroidBean.ResultsBean> {

  public TextView mTxtGanhuTitle;
  public TextView mTxtGanhuUserName;
  public TextView mTxtGanhuShowTime;

  public GanHuViewHolder(ViewGroup parent, @LayoutRes int res) {
    super(parent, res);

    mTxtGanhuTitle = $(R.id.txt_ganhu_title);
    mTxtGanhuUserName = $(R.id.txt_ganhuu_user_name);
    mTxtGanhuShowTime = $(R.id.txt_ganhu_show_time);

  }
  public void setData(GankAndroidBean.ResultsBean data) {
    mTxtGanhuTitle.setText(data.getDesc());
    mTxtGanhuUserName.setText("");
    mTxtGanhuShowTime.setText("");
  }
}
