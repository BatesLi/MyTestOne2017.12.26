package com.example.test.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.example.test.base.App;
import com.example.test.base.BaseFragment;
import com.example.test.mytesttwo.R;

/**
 * 妹纸的图片详情fragment
 */

public class FragmentFuLiDetail extends BaseFragment {

  @BindView(R.id.img_meizi) ImageView mImgMeizi;
  @BindView(R.id.txt_image_error) TextView mTxtImageError;

  public static final String URL="url";
  private String url;

  public static FragmentFuLiDetail newInstance(String url) {
    FragmentFuLiDetail fragment3Detail = new FragmentFuLiDetail();
    Bundle bundle = new Bundle();
    bundle.putString(URL,url);
    fragment3Detail.setArguments(bundle);
    return fragment3Detail;
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_meizi_details;
  }

  @Override protected void init(View view, Bundle savedInstanceState) {
    /*Glide.with(App.getContext())
        .load("http://7xi8d6.com1.z0.glb.clouddn.com/20171219115747_tH0TN5_Screenshot.jpeg")
        .into(mImgMeizi);*/
    url=getArguments().getString(URL);
    Glide.with(App.getContext())
        .load(url)
        .into(mImgMeizi);
  }
}
