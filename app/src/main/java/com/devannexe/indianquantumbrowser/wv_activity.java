package com.devannexe.indianquantumbrowser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

public class wv_activity extends AppCompatActivity {
    //Declaring Variables
   ProgressBar progress;
   WebView brow;
   Button go;
   SearchView urledit;
   ListView listView;

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wv_activity);
        //Function Declaring
        listView= findViewById(R.id.listView);
        progress= findViewById(R.id.progressBar);
        brow= findViewById(R.id.wv_brow);
        go= findViewById(R.id.btn_search);
        urledit= findViewById(R.id.urledit);
        ((EditText) findViewById(R.id.urledit).findViewById( findViewById(R.id.urledit).getContext().getResources().getIdentifier("android:id/search_src_text", null, null))).setTextColor(Color.BLACK);
        ((EditText) findViewById(R.id.urledit).findViewById( findViewById(R.id.urledit).getContext().getResources().getIdentifier("android:id/search_src_text", null, null))).setHintTextColor(Color.BLACK);
        ((EditText) findViewById(R.id.urledit).findViewById( findViewById(R.id.urledit).getContext().getResources().getIdentifier("android:id/search_src_text", null, null))).setHintTextColor(Color.BLACK);
        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress){
                progress.setProgress(newProgress);
                if(newProgress==100) {
                    progress.setVisibility(View.GONE);
                }
                else if(newProgress<1) {
                    progress.setVisibility(View.GONE);
                }
                else {
                    progress.setVisibility(View.VISIBLE);
                }
            }
        });

        WebSettings webSettings = brow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.getCacheMode();
        webSettings.getCursiveFontFamily();
        webSettings.getDatabaseEnabled();
        webSettings.getJavaScriptCanOpenWindowsAutomatically();
        webSettings.getMediaPlaybackRequiresUserGesture();
        webSettings.setGeolocationEnabled(true);
        webSettings.supportMultipleWindows();
        webSettings.getAllowContentAccess();
        webSettings.getAllowFileAccessFromFileURLs();
        webSettings.getDisplayZoomControls();

        //brow.loadUrl("http://www.google.com");
        urledit.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                brow.setVisibility(View.VISIBLE);
                String editextvalue = urledit.getQuery().toString();
                if (!editextvalue.startsWith("http:\\"))
                    editextvalue = "https:\\" + editextvalue;
                String url = editextvalue;
                brow.loadUrl(url);
                //HIDE kEYBOARD AFTER CLICKING GO BUTTON
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String editextvalue = urledit.getQuery().toString();

                return false;
            }
        });
        go.setOnClickListener( v -> {
            String editextvalue = urledit.getQuery().toString();
            if (!editextvalue.startsWith("http:\\"))
                editextvalue = "https:\\" + editextvalue;
            String url = editextvalue;
            brow.loadUrl(url);
            //HIDE kEYBOARD AFTER CLICKING GO BUTTON
            InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
        } );
    }
}
