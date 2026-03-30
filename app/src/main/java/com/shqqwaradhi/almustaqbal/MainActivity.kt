package com.shqqwaradhi.almustaqbal
  import android.annotation.SuppressLint
  import android.content.Intent
  import android.net.Uri
  import android.os.Bundle
  import android.view.KeyEvent
  import android.webkit.*
  import androidx.appcompat.app.AppCompatActivity
  import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
  class MainActivity : AppCompatActivity() {
      private lateinit var webView: WebView
      private lateinit var swipeRefresh: SwipeRefreshLayout
      companion object { const val APP_URL = "https://design-palette--josefsmirah.replit.app" }
      @SuppressLint("SetJavaScriptEnabled")
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
          swipeRefresh = findViewById(R.id.swipeRefresh)
          webView = findViewById(R.id.webView)
          webView.settings.apply {
              javaScriptEnabled = true; domStorageEnabled = true; databaseEnabled = true
              cacheMode = WebSettings.LOAD_DEFAULT; allowFileAccess = true
              mediaPlaybackRequiresUserGesture = false; setSupportZoom(false)
              builtInZoomControls = false; displayZoomControls = false
              useWideViewPort = true; loadWithOverviewMode = true
          }
          webView.webViewClient = object : WebViewClient() {
              override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                  val url = request?.url?.toString() ?: return false
                  return if (url.startsWith(APP_URL)) false
                  else { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))); true }
              }
              override fun onPageFinished(view: WebView?, url: String?) { swipeRefresh.isRefreshing = false }
          }
          webView.webChromeClient = WebChromeClient()
          swipeRefresh.setOnRefreshListener { webView.reload() }
          swipeRefresh.setColorSchemeColors(0xFF1d5c28.toInt())
          if (savedInstanceState != null) webView.restoreState(savedInstanceState)
          else webView.loadUrl(APP_URL)
      }
      override fun onSaveInstanceState(outState: Bundle) { super.onSaveInstanceState(outState); webView.saveState(outState) }
      override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
          if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) { webView.goBack(); return true }
          return super.onKeyDown(keyCode, event)
      }
  }