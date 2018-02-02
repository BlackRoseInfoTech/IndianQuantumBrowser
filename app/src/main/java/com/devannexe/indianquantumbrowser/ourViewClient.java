package com.devannexe.indianquantumbrowser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dev on 1/2/18.
 */

public class ourViewClient extends WebViewClient {

    //Deprecated means that "Hey we have something else to offer, you might wanna give it a shot
    //Because our minimum api is 15 and the new method is only valid in android N
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
