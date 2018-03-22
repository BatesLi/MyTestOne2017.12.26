package com.example.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.test.activity.GanHuActivity;
import com.example.test.bean.GankAndroidBean;
import com.example.test.mytesttwo.R;
import com.example.test.util.ImageGlide;
import java.util.List;

public class GanHuAdapterNew extends RecyclerView.Adapter<GanHuAdapterNew.GanHuViewHolderNew> {

  public List<GankAndroidBean.ResultsBean> mData;
  public Context mContext;

  public GanHuAdapterNew(Context context,List<GankAndroidBean.ResultsBean> data) {
    this.mContext = context;
    this.mData = data;
  }
  @Override
  public GanHuViewHolderNew onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_ganhu,parent,false);
    GanHuViewHolderNew ganHuViewHolderNew = new GanHuViewHolderNew(view);
    return ganHuViewHolderNew;
  }

  @Override public void onBindViewHolder(GanHuViewHolderNew holder, final int position) {
     //和UI相关的操作不要使用全局 Context
      holder.mTxtGanHuTitle.setText(mData.get(position).getDesc());
      holder.mTxtGanUserName.setText(mData.get(position).getWho());
      holder.mTxtGanShowTime.setText(mData.get(position).getCreatedAt());
      if (mData.get(position).getImages() !=null && mData.get(position).getImages().size() > 0) {
          ImageGlide.initGlide(mContext,mData.get(position).getImages().get(0),holder.mImageGanHuConver);
          holder.mImageGanHuConver.setVisibility(View.VISIBLE);
      }else {
          holder.mImageGanHuConver.setVisibility(View.GONE);
      }

      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Intent intent = new Intent(mContext,GanHuActivity.class);
          intent.putExtra(GanHuActivity.TITLE,mData.get(position).getDesc());
          intent.putExtra(GanHuActivity.URL,mData.get(position).getUrl());
          mContext.startActivity(intent);
        }
      });
  }

  @Override public int getItemCount() {
    return mData.size();
  }

  public class GanHuViewHolderNew extends RecyclerView.ViewHolder{

    @BindView(R.id.txt_ganhu_title) TextView mTxtGanHuTitle;
    @BindView(R.id.txt_ganhuu_user_name) TextView  mTxtGanUserName;
    @BindView(R.id.txt_ganhu_show_time) TextView mTxtGanShowTime;
    @BindView(R.id.image_ganhu_cover) ImageView mImageGanHuConver;

    public GanHuViewHolderNew(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
