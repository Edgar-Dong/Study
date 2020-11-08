package com.android.example.hybird.webview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.example.R;
import com.android.example.log.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * js->native:
 * 方式一：JsBridge方式 step1->step2->step3
 * 方式二：shouldOverrideUrlLoading
 * native->js:
 */
public class WebActivity extends AppCompatActivity {

    WebView myWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.get().d(WebActivity.class.getSimpleName(), "onCreate");
        setContentView(R.layout.activity_web);

        initWeb();

        loadUrlFor2();
    }

    private void initWeb() {
        myWeb = findViewById(R.id.myWeb);
        //js->native:step1
        myWeb.addJavascriptInterface(new JsBridge(), "android");
        myWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Logger.get().d(WebActivity.class.getSimpleName(), "onJsAlert url:" + url + ", message:" + message);
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Logger.get().d(WebActivity.class.getSimpleName(), "onJsConfirm");
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Logger.get().d(WebActivity.class.getSimpleName(), "onJsPrompt");
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                Logger.get().d(WebActivity.class.getSimpleName(), "onJsBeforeUnload");
                return super.onJsBeforeUnload(view, url, message, result);
            }
        });

        //js调用native方式2
        myWeb.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                Logger.get().d(WebActivity.class.getSimpleName(), "shouldOverrideUrlLoading uri:" + uri.toString());
                String scheme = uri.getScheme();
                if ("ceshi".equals(scheme)) {
                    String host = uri.getHost();
                    Set<String> names = uri.getQueryParameterNames();
                    Map<String, String> paramMap = new HashMap<>();
                    for (String name : names) {
                        paramMap.put(name, uri.getQueryParameter(name));
                    }
                    if ("setH5Info".equals(host)) {
                        jsCallNativeForShouldOverrideUrlLoading(paramMap);
                    }
                } else {
                    view.loadUrl(uri.toString());
                }
                return true;
            }
        });

        getWebSettings();
    }

    private void loadUrlFor1() {
        myWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        myWeb.loadUrl("https://www.baidu.com/");
    }

    private void loadUrlFor2() {
        myWeb.loadUrl("file:///android_asset/test.html");
    }

    private WebSettings getWebSettings() {
        WebSettings webSettings = myWeb.getSettings();
        //js->native:step2
        webSettings.setJavaScriptEnabled(true);
        return webSettings;
    }

    private void jsCallNativeForShouldOverrideUrlLoading(Map<String, String> paramMap) {
        JSONObject jsonObject = new JSONObject(paramMap);
        if (jsonObject != null && jsonObject.has("params")) {
            Logger.get().d(WebActivity.class.getSimpleName(), "jsCallNativeForShouldOverrideUrlLoading 参数：" + jsonObject.optString("params"));
        }
    }

    public void callJs(View view) {
        //该种传参需注意，字符串类型需要两侧拼接双引号，否则js层不认为是字符串类型，会报Uncaught ReferenceError: XXX is not defined
        String jsonParams = "\"native传递过来的参数\"";
        String url = "javascript:jsHasParamsAndNoResult(" + jsonParams + ")";
        Logger.get().d(WebActivity.class.getSimpleName(), "callJs url:" + url);
        myWeb.loadUrl(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void callJs2(View view) {
        String jsonParams = "\"Native say:Hello Js!\"";
        String url = "javascript:jsHasParamsAndResult(" + jsonParams + ")";
        Logger.get().d(WebActivity.class.getSimpleName(), "callJs2 url:" + url);
        myWeb.evaluateJavascript(url, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                String curThreadName = Thread.currentThread().getName();
                Logger.get().d(WebActivity.class.getSimpleName(),"onReceiveValue value:" + value + ", curThreadName:" + curThreadName);
            }
        });
    }
}