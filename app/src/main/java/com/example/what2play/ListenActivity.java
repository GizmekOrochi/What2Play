package com.example.what2play;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListenActivity extends AppCompatActivity {

    private WebView webView1;
    private Button buttonChoice1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        webView1 = findViewById(R.id.webView1);
        buttonChoice1 = findViewById(R.id.buttonChoice1);

        setupSpotifyWebView(
                webView1,
                "https://open.spotify.com/embed/track/7ftimUupzPRi0m8z3WaXvw?utm_source=generator&theme=0"
        );

        buttonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListenActivity.this, "Extrait 1 sélectionné", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSpotifyWebView(WebView webView, String embedUrl) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        webView.setWebChromeClient(new WebChromeClient());

        String html =
                "<html>" +
                        "<body style='margin:0;padding:0;background-color:transparent;'>" +
                        "<iframe " +
                        "style='border-radius:12px' " +
                        "src='" + embedUrl + "' " +
                        "width='100%' " +
                        "height='120' " +
                        "frameborder='0' " +
                        "allowfullscreen='' " +
                        "allow='autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture'>" +
                        "</iframe>" +
                        "</body>" +
                        "</html>";

        webView.loadDataWithBaseURL(
                "https://open.spotify.com",
                html,
                "text/html",
                "utf-8",
                null
        );
    }
}