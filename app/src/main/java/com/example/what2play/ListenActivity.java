package com.example.what2play;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


public class ListenActivity extends BaseActivity {

    private WebView webView1, webView2, webView3;
    private Button buttonChoice1, buttonChoice2, buttonChoice3;


    private int selectedChoice = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        // Récupération des vues
        webView1 = findViewById(R.id.webView1);
        webView2 = findViewById(R.id.webView2);
        webView3 = findViewById(R.id.webView3);

        buttonChoice1 = findViewById(R.id.buttonChoice1);
        buttonChoice2 = findViewById(R.id.buttonChoice2);
        buttonChoice3 = findViewById(R.id.buttonChoice3);

        // Exemple : on charge avec uniquement les IDs Spotify
        setupSpotifyWebViewFromTrackId(webView1, "75xky3ExE2E7u6WDqCR35g");
        setupSpotifyWebViewFromTrackId(webView2, "0tbJlonCpRcSkAwPiOBG6g");
        setupSpotifyWebViewFromTrackId(webView3, "5MOwFMdgsaysrVPcE7Flnj");

        // Boutons
        buttonChoice1.setOnClickListener(v -> selectChoice(1));
        buttonChoice2.setOnClickListener(v -> selectChoice(2));
        buttonChoice3.setOnClickListener(v -> selectChoice(3));
    }

    private void selectChoice(int choice) {
        selectedChoice = choice;

        buttonChoice1.setText(choice == 1 ? "Selected" : "Choose Preview n°1");
        buttonChoice2.setText(choice == 2 ? "Selected" : "Choose Preview n°2");
        buttonChoice3.setText(choice == 3 ? "Selected" : "Choose Preview n°3");

        Toast.makeText(this, "Preview " + choice + " selected", Toast.LENGTH_SHORT).show();
    }

    private void setupSpotifyWebViewFromTrackId(WebView webView, String trackId) {
        String embedUrl = buildSpotifyEmbedUrl(trackId);
        setupSpotifyWebView(webView, embedUrl);
    }

    private String buildSpotifyEmbedUrl(String trackId) {
        return "https://open.spotify.com/embed/track/" + trackId + "?theme=0";
    }

    private void setupSpotifyWebView(WebView webView, String embedUrl) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        String html =
                "<html style='margin:0;padding:0;width:100%;height:100%;overflow:hidden;'>" +
                        "<body style='margin:0;padding:0;width:100%;height:100%;overflow:hidden;background:transparent;'>" +
                        "<iframe " +
                        "src='" + embedUrl + "' " +
                        "style='display:block;border:none;border-radius:12px;width:100%;height:100%;margin:0;padding:0;'" +
                        "width='100%' " +
                        "height='100%' " +
                        "frameborder='0' " +
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