package com.yangbo28.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebDetailActivity extends AppCompatActivity {

    private WebView webView;
    private String currPage  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_detail);
        currPage = getIntent().getStringExtra("url");
        initData();
    }

    protected void initData() {
        // TODO Auto-generated method stub
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(currPage);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    //Android8.0以下的需要返回true 并且需要loadUrl；8.0之后效果相反
                    if(Build.VERSION.SDK_INT<26) {
                        view.loadUrl(url);
                        currPage = url;
                        return true;
                    }
                    return false;
                }
                return true;
            }

        });
    }
}
