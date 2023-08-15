package com.example.sharespaceanimation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to WebView and ProgressBar
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        // Configure WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        // Set WebView loading progress listener
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

                // If loading progress reaches 100%, hide ProgressBar and show WebView
                if (newProgress == 100) {
                    // Add a delay of 3 seconds before hiding the ProgressBar and showing WebView
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            webView.setVisibility(View.VISIBLE);
                        }
                    }, 3000); // 3 seconds delay
                }
            }
        });

        // Load the specified URL
        webView.loadUrl("http://current.stephenphyo.com:3000/");
    }
}
