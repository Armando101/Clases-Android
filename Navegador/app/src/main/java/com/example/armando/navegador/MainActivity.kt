package com.example.armando.navegador

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BackButton.setOnClickListener {
            if(webView.canGoForward()) {
                webView.goForward()
            }
        }
        refreshButton = findViewById<Button>(R.id.refreshButton)
        refreshButton.setOnClickListener {
            webView.reload()
        }

        goButton.setOnClickListener {
            var url = UrlEditText.text.toString().trim()

            if (url.startsWith("https://") || url.startsWith("https://")) {
                webView.loadUrl(url)
            }
            else {
                url = "http://" + url
            webView.loadUrl(url)
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webView.loadUrl("https://youtube.com")
    }
}
