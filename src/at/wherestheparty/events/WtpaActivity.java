package at.wherestheparty.events;

import android.app.Activity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.net.Uri;

import android.graphics.Bitmap;
import android.view.Window;

import android.util.Log;

public class WtpaActivity extends Activity {
  private WebView page;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.main);

    page().loadUrl("http://ejohn.org/wtpa/");
    page().setWebViewClient(new WtpaWebViewClient());
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && page().canGoBack()) {
      page().goBack();
      return true;
    } else
      return super.onKeyDown(keyCode, event);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.twitter:
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/theparty")));
        return true;
      case R.id.facebook:
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/pages/Wheres-The-Party-At/128506820514886")));
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class WtpaWebViewClient extends WebViewClient {
    /* Uncomment this if you want to follow all links within the app.
     * Can also only load links that begin with ejohn.org/wtpa/ in the app.
     */
    /*
    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
      v.loadUrl(url);
      return true;
    }
    */

    @Override
    public void onReceivedHttpAuthRequest(WebView v, HttpAuthHandler handler, String host, String realm) {
      handler.proceed(UsernamePassword.username, UsernamePassword.password);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      setProgressBarIndeterminateVisibility(false);
    }
  }

  private WebView page() {
    if (this.page == null) {
      this.page = (WebView)findViewById(R.id.webpage);
      WebSettings s = this.page.getSettings();
      s.setJavaScriptEnabled(true);
    }
    return this.page;
  }
}
