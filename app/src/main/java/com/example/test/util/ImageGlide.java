package com.example.test.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Glide下载网络图片工具类
 */

public class  ImageGlide {

  public static void initGlide(Context context,String url,ImageView imageView) {
    Glide.with(context).load(url).into(imageView);
  }
}
