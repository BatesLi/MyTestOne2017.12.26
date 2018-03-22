package com.example.test.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.test.base.App;
import com.example.test.bean.NewsBean;
import com.example.test.mytesttwo.R;

public class GanHuActivity extends AppCompatActivity {

  @BindView(R.id.toolbar_ganhu) Toolbar mToolbarGanhu;
  @BindView(R.id.web_view_ganhu) WebView mWebViewGanHu;

  public static final String TITLE = "title";
  public static final String URL = "url";
  public String title;
  public String url;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gan_hu);
    ButterKnife.bind(this);
    initToolbar();
    initWebView();
  }
  public void initToolbar() {
    Intent intent = getIntent();
    title = intent.getStringExtra(TITLE);
    url = intent.getStringExtra(URL);
    mToolbarGanhu.setTitle(title);
    mToolbarGanhu.setNavigationIcon(R.drawable.ic_back);
    mToolbarGanhu.setBackgroundResource(R.color.colorPrimaryDark);
    mToolbarGanhu.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
    mToolbarGanhu.inflateMenu(R.menu.menu_ganhu);
    mToolbarGanhu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
          case R.id.share_ganhu:
            //Toast.makeText(App.getContext(),"保存",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
            intent.putExtra(Intent.EXTRA_TEXT, "来自{Bates}的分享:" + "url");
            startActivity(Intent.createChooser(intent, title));
            break;
        }
        return false;
      }
    });
  }
  private void initWebView() {
    WebSettings webSettings = mWebViewGanHu.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setBuiltInZoomControls(false);
    webSettings.setSupportZoom(false);
    mWebViewGanHu.loadUrl(url);
  }
}
