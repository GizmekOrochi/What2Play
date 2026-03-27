package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListenActivity extends BaseActivity {

    private WebView webView1, webView2, webView3;
    private Button buttonChoice1, buttonChoice2, buttonChoice3;


    private AppDatabase db;

    private final ArrayList<Track> previewTracks = new ArrayList<>();
    private int selectedChoice = -1;
    private int selectedTrackId = -1;

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
        selectedChoice = choice;
        selectedTrackId = trackId;

        buttonChoice1.setText(choice == 1 ? "Selected" : "Choose Preview n°1");
        buttonChoice2.setText(choice == 2 ? "Selected" : "Choose Preview n°2");
        buttonChoice3.setText(choice == 3 ? "Selected" : "Choose Preview n°3");

        Toast.makeText(this, "Preview " + choice + " selected", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ListenActivity.this, QuizEndActivity.class);
        intent.putExtra("trackId", selectedTrackId);
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

        button.setOnClickListener(v -> selectChoice(index + 1, track.id));
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