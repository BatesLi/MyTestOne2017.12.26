package com.example.test.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.test.api.RetrofitHelper;
import com.example.test.base.BaseActivity;
import com.example.test.bean.ZhiHuStory;
import com.example.test.mytesttwo.R;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends BaseActivity {

  public static final String ID = "id";
  public static final String TITLE = "title";

  //知乎日报ID
  private String id;
  //知乎日报标题
  private String title;
  //知乎日报地址
  private String url;

  //网页的HTML内容
  private String mBody;
  //网页的CSS样式
  private String[] css;

  @BindView(R.id.toolbar_news_detail) Toolbar mToolbarNewsDetail;
  @BindView(R.id.web_view_new_detail) WebView mWebViewNewsDetail;

  @Override protected void setLayout() {
    setContentView(getLayoutId());
    ButterKnife.bind(this);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_news_detail;
  }

  @Override protected void initView() {
    Bundle bundle = getIntent().getExtras();
    id = bundle.getString(ID);
    title = bundle.getString(TITLE);

    mToolbarNewsDetail.setTitle(title);
    mToolbarNewsDetail.setNavigationIcon(R.drawable.ic_back);
    mToolbarNewsDetail.setBackgroundResource(R.color.colorPrimaryDark);
    mToolbarNewsDetail.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onBackPressed();
      }
    });
    initWebView();
    //mWebViewNewsDetail.loadUrl("http://cn.bing.com/?scope=web");
  }
  public void initWebView() {
    WebSettings settings = mWebViewNewsDetail.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    settings.setLoadWithOverviewMode(true);
    settings.setBuiltInZoomControls(false);
    settings.setSupportZoom(false);
    //settings.setUseWideViewPort(true);造成文字太小
    settings.setDomStorageEnabled(true);
    settings.setDatabaseEnabled(true);
    settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
    settings.setAppCacheEnabled(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    mWebViewNewsDetail.setWebChromeClient(new WebChromeClient());
  }
  @Override protected void loadData() {
    getWebData();
  }
  public void getWebData() {
    RetrofitHelper
        .getZhiHuAPI()
        .getZhihuStory(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ZhiHuStory>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(ZhiHuStory zhiHuStory) {
            url = zhiHuStory.getShareUrl();
            mBody = zhiHuStory.getBody();
            css = zhiHuStory.getCss();
            mWebViewNewsDetail.loadUrl(url);
          }
        });
  }
  @Override protected void setListener() {

  }

}
