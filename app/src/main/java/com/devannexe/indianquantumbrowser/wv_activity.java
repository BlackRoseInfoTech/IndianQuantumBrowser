package com.devannexe.indianquantumbrowser;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
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
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wv_activity);
        //Function Declaring
        listView=(ListView)findViewById(R.id.btn_opt);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        brow=(WebView)findViewById(R.id.wv_brow);
        go=(Button)findViewById(R.id.btn_search);
        urledit=(SearchView)findViewById(R.id.urledit);

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
                String editextvalue = urledit.getQuery().toString();
                if (!editextvalue.startsWith("http:\\"))
                    editextvalue = "https:\\" + editextvalue;
                String url = editextvalue;
                brow.loadUrl(url);
                //HIDE kEYBOARD AFTER CLICKING GO BUTTON
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String editextvalue = urledit.getQuery().toString();

                return false;
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editextvalue = urledit.getQuery().toString();
                if (!editextvalue.startsWith("http:\\"))
                    editextvalue = "https:\\" + editextvalue;
                String url = editextvalue;
                brow.loadUrl(url);
                //HIDE kEYBOARD AFTER CLICKING GO BUTTON
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
            }
        });

    }
}
