package example.web;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class WebActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        /**WebViewオブジェクト作成**/
        WebView webView = (WebView)findViewById(R.id.webview);

        /**URL指定**/
        webView.loadUrl("http://www.google.co.jp/");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web, menu);
        return true;
    }
    
}
