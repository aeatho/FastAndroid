/*
 * Copyright (C) 2014 loQua.Xee <loquaciouser@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.fastlibrary.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.*;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.android.fastlibrary.R;
import com.android.fastlibrary.ui.UIHelper;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.androd.fastlirary.ui.activity.FastBrowserActivity
 * @Description: TODO 浏览器模板 必须设置启用ActionBar
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 17:58
 * @version: V1.0
 */

@SuppressLint("SetJavaScriptEnabled")
public final class FastBrowserActivity extends BaseActivity {
    private static final String TAG = "Fugao-FastBrowserActivity";

    protected WebView webView;
    private ProgressBar progressBar;
    private ImageButton btnBack, btnForward, btnRefresh;
    private static final String EXTRA_STRING_NAME = "url_string";

    public FastBrowserActivity() {
        setHiddenActionBar(false);
        setAllowFullScreen(false);
    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_browser);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) this.findViewById(R.id.browser_webview);
        progressBar = (ProgressBar) findViewById(R.id.browser_progressbar);
        btnBack = (ImageButton) this.findViewById(R.id.browser_toolbar_btn_back);
        btnForward = (ImageButton) this.findViewById(R.id.browser_toolbar_btn_forward);
        btnRefresh = (ImageButton) this.findViewById(R.id.browser_toolbar_btn_refresh);

        btnBack.setEnabled(false);
        btnForward.setEnabled(false);

        initWebView();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_STRING_NAME)) {
            String urlString = intent.getStringExtra(EXTRA_STRING_NAME);

            webView.loadUrl(urlString);
        } else {
            finish();
        }
    }

    /**
     * 初始化浏览器设置信息
     */
    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        initWebView(webView);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setClipToPadding(false);
//        webView.setFitsSystemWindows(true);
//        webView.setInitialScale(0);
//        webView.loadDataWithBaseURL(null, htmlString, "text/html","utf-8", null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                setActionBarTitle("正在加载...");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
                btnBack.setEnabled(view.canGoBack());
                btnForward.setEnabled(view.canGoForward());
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(final WebView view, final int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(final WebView view, final String title) {
                super.onReceivedTitle(view, title);
                setActionBarTitle(title);
            }
        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, final String userAgent,
                                        final String contentDisposition, final String mimetype,
                                        final long contentLength) {
                try {
                    setActionBarTitle("正在下载...");
                    Uri uri = Uri.parse(url);
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(Intent.createChooser(it, "下载"));
                } catch (Exception e) {
                    e.printStackTrace();
                    UIHelper.longToast("没有应用可执行此操作");
                }
            }
        });
    }

    protected void initWebView(final WebView webView) {}

    ;

    private void setActionBarTitle(String title) {
        SpannableString spannableString = new SpannableString(title);
//        String font = "LockScreen_Clock.ttf";
//        spannableString.setSpan(new TypefaceSpan(font, Typeface.createFromAsset(getAssets(),
// font)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getActionBar().setTitle(spannableString);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        btnBack.setOnClickListener(toolbar_listener);
        btnForward.setOnClickListener(toolbar_listener);
        btnRefresh.setOnClickListener(toolbar_listener);
    }


    View.OnClickListener toolbar_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.browser_toolbar_btn_back) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }

            } else if (i == R.id.browser_toolbar_btn_forward) {
                if (webView.canGoForward()) {
                    webView.goForward();
                }

            } else if (i == R.id.browser_toolbar_btn_refresh) {
                webView.reload();

            } else {
            }
        }
    };

    /**
     * 返回事件屏蔽
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.stopLoading();
        webView.destroy();
    }
}
