package bonaguasato.location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web_location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView WebView = (WebView)
                findViewById(R.id.WebView);
        WebView.setWebChromeClient(new WebChromeClient());
        WebView.setWebViewClient(new WebViewClient());
        WebView.getSettings().setJavaScriptEnabled(true);

        WebView.loadUrl("https://www.google.com.ph/maps/@14.5545927,121.0331898,14z");
    }
}
