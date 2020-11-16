package com.android.example.hybird.webview;

import android.webkit.JavascriptInterface;

import com.android.example.common.log.Logger;

/**
 * @author:無忌
 * @date:2020/8/26
 * @description:
 */
public class JsBridge {

    //js->native:step3
    @JavascriptInterface
    public void nativeNoParamsAndResult() {
        Logger.get().d(JsBridge.class.getSimpleName(), "js->native:无返回值，无参数");
    }

    @JavascriptInterface
    public void nativeHasParamsNoResult(String message) {
        Logger.get().d(JsBridge.class.getSimpleName(), "js->native:无返回值，有参数：" + message);
    }

    @JavascriptInterface
    public String nativeNoParamsHasResult() {
        String result = "native返回值";
        Logger.get().d(JsBridge.class.getSimpleName(), "js->native:无参数，有返回值：" + result);
        return result;
    }
}
