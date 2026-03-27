package com.example.what2play;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListenActivity extends BaseActivity {

    private Button buttonChoice1, buttonChoice2, buttonChoice3;


    private AppDatabase db;

    private final ArrayList<Track> previewTracks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WebView webView1 = findViewById(R.id.webView1);
        WebView webView2 = findViewById(R.id.webView2);
        WebView webView3 = findViewById(R.id.webView3);

        buttonChoice1 = findViewById(R.id.buttonChoice1);
        buttonChoice2 = findViewById(R.id.buttonChoice2);
        buttonChoice3 = findViewById(R.id.buttonChoice3);

        findViewById(R.id.buttonPrevious);
        findViewById(R.id.buttonHome);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        ArrayList<String> selectedArtists = getIntent().getStringArrayListExtra("selected_artists");

        if (selectedArtists == null || selectedArtists.isEmpty()) {
            Toast.makeText(this, "No selected artists received", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadPreviewTracks(selectedArtists);

        if (previewTracks.isEmpty()) {
            Toast.makeText(this, "No tracks found for selected artists", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        bindPreviewSlot(0, webView1, buttonChoice1);
        bindPreviewSlot(1, webView2, buttonChoice2);
        bindPreviewSlot(2, webView3, buttonChoice3);
    }

    private void selectChoice(int choice, int trackId) {

        buttonChoice1.setText(choice == 1 ? "Selected" : "Choose Preview n°1");
        buttonChoice2.setText(choice == 2 ? "Selected" : "Choose Preview n°2");
        buttonChoice3.setText(choice == 3 ? "Selected" : "Choose Preview n°3");

        Toast.makeText(this, "Preview " + choice + " selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ListenActivity.this, QuizEndActivity.class);
        intent.putExtra("trackId", trackId);
        startActivity(intent);
    }

    private void setupSpotifyWebViewFromTrackId(WebView webView, String trackId) {
        String embedUrl = buildSpotifyEmbedUrl(trackId);
        setupSpotifyWebView(webView, embedUrl);
    }

    private String buildSpotifyEmbedUrl(String trackId) {
        return "https://open.spotify.com/embed/track/" + trackId + "?theme=0";
    }

    private void loadPreviewTracks(ArrayList<String> selectedArtists) {
        previewTracks.clear();
        Random random = new Random();

        for (String artistName : selectedArtists) {
            Artist artist = db.artistDao().findByName(artistName);

            if (artist == null) {
                continue;
            }

            List<Track> tracksForArtist = db.trackArtistDao().getTracksForArtist(artist.id);

            if (tracksForArtist == null || tracksForArtist.isEmpty()) {
                continue;
            }

            Track randomTrack = tracksForArtist.get(random.nextInt(tracksForArtist.size()));
            previewTracks.add(randomTrack);

            if (previewTracks.size() == 3) {
                return;
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private void bindPreviewSlot(int index, WebView webView, Button button) {
        if (index >= previewTracks.size()) {
            webView.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            return;
        }

        Track track = previewTracks.get(index);

        setupSpotifyWebViewFromTrackId(webView, track.link);
        button.setText("Choose Preview n°" + (index + 1));
        button.setVisibility(View.VISIBLE);
        webView.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetJavaScriptEnabled")
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

    public void clickChoice1(View view) {
        if (!previewTracks.isEmpty()) {
            selectChoice(1, previewTracks.get(0).id);
        }
    }

    public void clickChoice2(View view) {
        if (previewTracks.size() > 1) {
            selectChoice(2, previewTracks.get(1).id);
        }
    }

    public void clickChoice3(View view) {
        if (previewTracks.size() > 2) {
            selectChoice(3, previewTracks.get(2).id);
        }
    }

    public void clickPrevious(View view) {
        finish();
    }

    public void clickHome(View view) {
        Intent intent = new Intent(ListenActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}