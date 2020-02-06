package com.example.sampleapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {

    boolean flg = false;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //レイアウトで指定したWebViewのIDを指定する。
        final WebView mapWebView = (WebView)findViewById(R.id.mapView);
        final WebView anbiWebView = (WebView)findViewById(R.id.anbiView);
        final Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flg == false){
                    mapWebView.setVisibility(View.GONE);
                    anbiWebView.setVisibility(View.VISIBLE);
                    button.setText("Map");
                    flg = true;
                    Log.d("flg0","nowflg="+flg);
                }else{
                    mapWebView.setVisibility(View.VISIBLE);
                    anbiWebView.setVisibility(View.GONE);
                    button.setText("health");
                    flg = false;
                    Log.d("flg1","nowflg="+flg);
                }
            }
        });

        mapWebView.setVisibility(View.VISIBLE);
        anbiWebView.setVisibility(View.GONE);

        //リンクをタップしたときに標準ブラウザを起動させない
        mapWebView.setWebViewClient(new WebViewClient());
        anbiWebView.setWebViewClient(new WebViewClient());

        //最初にgoogleのページを表示する。
        mapWebView.loadUrl("https://walking.kns-iv.com/");
        anbiWebView.loadUrl("https://ambidata.io/usr/login.html");

        //jacascriptを許可する
        mapWebView.getSettings().setJavaScriptEnabled(true);
        anbiWebView.getSettings().setJavaScriptEnabled(true);

        //SSL認証を無視する
        mapWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        anbiWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

    }
}
