package com.qmx163.activity.Found;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.base.BaseAc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BannerDetailActivity extends BaseAc {

    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mTvUp;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.Right_img)
    ImageView mRightImg;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    WebView mWebView;
    String bannerUrl = "";
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_detail);
        ButterKnife.bind(this);
        mTvTitle.setText("");
        mWebView = (WebView) findViewById(R.id.web_view);
        mIbtnLeft.setVisibility(View.VISIBLE);
        bannerUrl = getIntent().getStringExtra("banner_url");

        showProgressDialog();
        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) mTvUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mTvUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

//        WebSettings用来对WebView的配置进行配置和管理，比如
// 是否可以进行文件操作、缓存的设置、页面是否支持放大和缩小、是否允许使用数据库api、字体及文字编码设置、
// 是否允许js脚本运行、是否允许图片自动加载、是否允许数据及密码保存等等

//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setAllowFileAccess(true);
//        webSettings.setSavePassword(true);
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);


        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN : 适应屏幕，内容将自动缩放
         */
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webSettings.setUseWideViewPort(true);
//
//        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        mWebView.setHorizontalScrollbarOverlay(true);
//        mWebView.setHorizontalScrollBarEnabled(true);
//        mWebView.requestFocus();
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setDomStorageEnabled(true);
//
//        mWebView.loadUrl("www.baidu.com");
//
//        mWebView.setBackgroundColor(0);

        this.mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.loadUrl("http://www.bigzbj.com/dzbjfund/app/bankGuaranteeWebDetail?id=1&userId=82");
        mWebView.loadUrl("https://" + bannerUrl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTvTitle.setText(view.getTitle());
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                mTvTitle.setText(view.getTitle());
//                showProgressDialog();

            }

            /**
             * 加载给定URL资源内容   该方法待使用    判断webview是否可以返回,不能返回就隐藏返回按钮
             */
            @Override
            public void onLoadResource(final WebView view, String url) {
                super.onLoadResource(view, url);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();

                    }
                }, 1000);

//                mTvTitle.setText(view.getTitle());

            }

            /**
             * 页面加载完成回调方法，获取对应url地址
             * */
            @Override
            public void onPageFinished(final WebView view, String url) {
                super.onPageFinished(view, url);
//                dismissProgressDialog();
                view.setVisibility(View.VISIBLE);
                mWebView.setVisibility(View.VISIBLE);
                mLlEmpty.setVisibility(View.GONE);
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.setVisibility(View.GONE);
                dismissProgressDialog();
                mWebView.setVisibility(View.GONE);
                mLlEmpty.setVisibility(View.VISIBLE);
                mIvEmpty.setImageResource(R.mipmap.no_intelnet);
                mTvEmpty.setText("网络错误");
                super.onReceivedError(view, request, error);
            }

            /**
             * 自己定义加载错误处理效果，比如：TeachCourse定义在没有网络时候，显示一张无网络的图片
             *API 23之前调用
             */
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                view.setVisibility(View.GONE);
                super.onReceivedError(view, errorCode, description, failingUrl);
                dismissProgressDialog();
            }
        });

//        mWebView.setOnLongClickListener(view -> true); //禁止长按复制
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.iamgeleft)
    public void onViewClicked() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }
}
