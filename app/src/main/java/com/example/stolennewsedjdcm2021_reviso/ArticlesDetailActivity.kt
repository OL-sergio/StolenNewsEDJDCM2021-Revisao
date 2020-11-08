package com.example.stolennewsedjdcm2021_reviso

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ArticlesDetailActivity : AppCompatActivity() {

    var urlString : String? = null
    var articleTitle : String? = null

    var webView : WebView?  =   null

    companion object    {

        const val ARTICLE_URL    =   "article_url"
        const val ARTICLE_TITLE     =   "article_title"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_detail)

            urlString       =   intent.getStringExtra(ARTICLE_URL)
            articleTitle    =   intent.getStringExtra(ARTICLE_TITLE)

            title = articleTitle

            webView =   findViewById(R.id.webViewArticles)

            urlString?.let {
                webView?.loadUrl(it)

            }

            val webViewClient = object : WebViewClient(){
                override fun shouldOverrideUrlLoading(view: WebView?, url:String?): Boolean {
                    url?.let{view?.loadUrl(it)}
                    return true


                }

            }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            webView?.webViewClient=webViewClient

        }

    }
}