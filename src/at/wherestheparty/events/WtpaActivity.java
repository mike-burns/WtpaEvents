package at.wherestheparty.events;

import android.app.Activity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;

public class WtpaActivity extends Activity {
  private WebView page;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

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

  private class WtpaWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
      v.loadUrl(url);
      return true;
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView v, HttpAuthHandler handler, String host, String realm) {
      handler.proceed(UsernamePassword.username, UsernamePassword.password);
    }
  }

  private WebView page() {
    if (this.page == null)
      this.page = (WebView)findViewById(R.id.webpage);
    return this.page;
  }
}
