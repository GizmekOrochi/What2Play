package com.example.what2play;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class ActivityLink extends AppCompatActivity {

    private FrameLayout gameArea;

    private final MaterialCardView[] artistCards = new MaterialCardView[6];
    private final MaterialCardView[] rankCards = new MaterialCardView[6];
    private final TextView[] artistTexts = new TextView[6];

    private Button buttonPrevious, buttonHome, buttonValidate;

    private final String[] ARTISTS = {
            "Daft Punk",
            "Justice",
            "Modjo",
            "Cassius",
            "Alan Braxe",
            "Stardust"
    };

    // artist index -> rank index, -1 if none
    private final int[] artistToRank = {-1, -1, -1, -1, -1, -1};

    // rank index -> artist index, -1 if none
    private final int[] rankToArtist = {-1, -1, -1, -1, -1, -1};

    private int draggingArtist = -1;
    private ConnectionOverlayView overlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        bindViews();
        setupArtists();
        setupOverlay();
        setupButtons();
        setupArtistTouchListeners();
        updateVisualState();
    }

    private void bindViews() {
        gameArea = findViewById(R.id.gameArea);

        artistCards[0] = findViewById(R.id.cardArtist1);
        artistCards[1] = findViewById(R.id.cardArtist2);
        artistCards[2] = findViewById(R.id.cardArtist3);
        artistCards[3] = findViewById(R.id.cardArtist4);
        artistCards[4] = findViewById(R.id.cardArtist5);
        artistCards[5] = findViewById(R.id.cardArtist6);

        artistTexts[0] = findViewById(R.id.textArtist1);
        artistTexts[1] = findViewById(R.id.textArtist2);
        artistTexts[2] = findViewById(R.id.textArtist3);
        artistTexts[3] = findViewById(R.id.textArtist4);
        artistTexts[4] = findViewById(R.id.textArtist5);
        artistTexts[5] = findViewById(R.id.textArtist6);

        rankCards[0] = findViewById(R.id.cardRank1);
        rankCards[1] = findViewById(R.id.cardRank2);
        rankCards[2] = findViewById(R.id.cardRank3);
        rankCards[3] = findViewById(R.id.cardRank4);
        rankCards[4] = findViewById(R.id.cardRank5);
        rankCards[5] = findViewById(R.id.cardRank6);

        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonHome = findViewById(R.id.buttonHome);
        buttonValidate = findViewById(R.id.buttonValidate);
    }

    private void setupArtists() {
        for (int i = 0; i < ARTISTS.length; i++) {
            artistTexts[i].setText(ARTISTS[i]);
        }
    }

    private void setupOverlay() {
        overlayView = new ConnectionOverlayView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        overlayView.setLayoutParams(params);
        overlayView.setClickable(false);
        overlayView.setFocusable(false);
        gameArea.addView(overlayView);
    }

    private void setupButtons() {
        buttonPrevious.setOnClickListener(v -> finish());

        buttonHome.setOnClickListener(v -> {
            // Si tu as une vraie HomeActivity, remplace cette logique.
            finishAffinity();
        });

        buttonValidate.setOnClickListener(v -> {
            if (!allAssigned()) {
                Toast.makeText(this, "Please rank all 6 artists", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<String> orderedArtists = new ArrayList<>();
            for (int rank = 0; rank < 6; rank++) {
                int artistIndex = rankToArtist[rank];
                orderedArtists.add(ARTISTS[artistIndex]);
            }

            Intent result = new Intent();
            result.putStringArrayListExtra("ordered_artists", orderedArtists);
            setResult(RESULT_OK, result);

            Toast.makeText(this, "Ranking validated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupArtistTouchListeners() {
        for (int i = 0; i < artistCards.length; i++) {
            final int artistIndex = i;
            artistCards[i].setOnTouchListener((v, event) -> handleArtistTouch(artistIndex, event));
        }
    }

    private boolean handleArtistTouch(int artistIndex, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                draggingArtist = artistIndex;

                PointF start = getArtistAnchorPoint(artistCards[artistIndex]);
                PointF end = rawToGameArea(event.getRawX(), event.getRawY());

                overlayView.setTempLine(start, end);
                return true;

            case MotionEvent.ACTION_MOVE:
                if (draggingArtist != -1) {
                    PointF movePoint = rawToGameArea(event.getRawX(), event.getRawY());
                    overlayView.updateTempEnd(movePoint);
                }
                return true;

            case MotionEvent.ACTION_UP:
                if (draggingArtist != -1) {
                    int targetRank = findTouchedRank(event.getRawX(), event.getRawY());
                    if (targetRank != -1) {
                        assignConnection(draggingArtist, targetRank);
                    }
                }
                draggingArtist = -1;
                overlayView.clearTempLine();
                return true;

            case MotionEvent.ACTION_CANCEL:
                draggingArtist = -1;
                overlayView.clearTempLine();
                return true;
        }
        return false;
    }

    private void assignConnection(int artistIndex, int rankIndex) {
        int oldRankForArtist = artistToRank[artistIndex];
        if (oldRankForArtist != -1) {
            rankToArtist[oldRankForArtist] = -1;
        }

        int oldArtistOnRank = rankToArtist[rankIndex];
        if (oldArtistOnRank != -1) {
            artistToRank[oldArtistOnRank] = -1;
        }

        artistToRank[artistIndex] = rankIndex;
        rankToArtist[rankIndex] = artistIndex;

        updateVisualState();
    }

    private void updateVisualState() {
        int strokeSelected = dpToPx(3);
        int strokeNormal = dpToPx(1);

        int colorSelected = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
        int colorNormal = ContextCompat.getColor(this, android.R.color.darker_gray);

        for (int i = 0; i < artistCards.length; i++) {
            boolean connected = artistToRank[i] != -1;
            artistCards[i].setStrokeWidth(connected ? strokeSelected : strokeNormal);
            artistCards[i].setStrokeColor(connected ? colorSelected : colorNormal);
            artistCards[i].setAlpha(connected ? 1f : 0.75f);
        }

        for (int i = 0; i < rankCards.length; i++) {
            boolean occupied = rankToArtist[i] != -1;
            rankCards[i].setStrokeWidth(occupied ? strokeSelected : strokeNormal);
            rankCards[i].setStrokeColor(occupied ? colorSelected : colorNormal);
            rankCards[i].setAlpha(occupied ? 1f : 0.75f);
        }

        buttonValidate.setEnabled(allAssigned());
        buttonValidate.setAlpha(allAssigned() ? 1f : 0.5f);

        gameArea.post(this::refreshConnectionLines);
    }

    private boolean allAssigned() {
        for (int value : artistToRank) {
            if (value == -1) return false;
        }
        return true;
    }

    private void refreshConnectionLines() {
        List<Line> lines = new ArrayList<>();

        for (int artistIndex = 0; artistIndex < artistToRank.length; artistIndex++) {
            int rankIndex = artistToRank[artistIndex];
            if (rankIndex != -1) {
                PointF start = getArtistAnchorPoint(artistCards[artistIndex]);
                PointF end = getRankAnchorPoint(rankCards[rankIndex]);
                lines.add(new Line(start.x, start.y, end.x, end.y));
            }
        }

        overlayView.setFixedLines(lines);
    }

    private int findTouchedRank(float rawX, float rawY) {
        Rect rect = new Rect();

        for (int i = 0; i < rankCards.length; i++) {
            rankCards[i].getGlobalVisibleRect(rect);
            if (rect.contains((int) rawX, (int) rawY)) {
                return i;
            }
        }

        return -1;
    }

    private PointF getArtistAnchorPoint(View artistCard) {
        return getRelativePoint(artistCard, true);
    }

    private PointF getRankAnchorPoint(View rankCard) {
        return getRelativePoint(rankCard, false);
    }

    private PointF getRelativePoint(View view, boolean useRightEdge) {
        int[] gameLoc = new int[2];
        int[] viewLoc = new int[2];

        gameArea.getLocationOnScreen(gameLoc);
        view.getLocationOnScreen(viewLoc);

        float x;
        if (useRightEdge) {
            x = viewLoc[0] - gameLoc[0] + view.getWidth();
        } else {
            x = viewLoc[0] - gameLoc[0];
        }

        float y = viewLoc[1] - gameLoc[1] + (view.getHeight() / 2f);

        return new PointF(x, y);
    }

    private PointF rawToGameArea(float rawX, float rawY) {
        int[] gameLoc = new int[2];
        gameArea.getLocationOnScreen(gameLoc);
        return new PointF(rawX - gameLoc[0], rawY - gameLoc[1]);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private static class Line {
        final float startX;
        final float startY;
        final float endX;
        final float endY;

        Line(float startX, float startY, float endX, float endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    public static class ConnectionOverlayView extends View {

        private final Paint fixedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint tempPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        private final List<Line> fixedLines = new ArrayList<>();

        private PointF tempStart = null;
        private PointF tempEnd = null;

        public ConnectionOverlayView(@NonNull android.content.Context context) {
            super(context);

            fixedPaint.setColor(Color.parseColor("#4A90E2"));
            fixedPaint.setStrokeWidth(8f);
            fixedPaint.setStyle(Paint.Style.STROKE);

            tempPaint.setColor(Color.parseColor("#9B9B9B"));
            tempPaint.setStrokeWidth(6f);
            tempPaint.setStyle(Paint.Style.STROKE);
        }

        public void setFixedLines(List<Line> lines) {
            fixedLines.clear();
            fixedLines.addAll(lines);
            invalidate();
        }

        public void setTempLine(PointF start, PointF end) {
            tempStart = start;
            tempEnd = end;
            invalidate();
        }

        public void updateTempEnd(PointF end) {
            tempEnd = end;
            invalidate();
        }

        public void clearTempLine() {
            tempStart = null;
            tempEnd = null;
            invalidate();
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            for (Line line : fixedLines) {
                canvas.drawLine(line.startX, line.startY, line.endX, line.endY, fixedPaint);
            }

            if (tempStart != null && tempEnd != null) {
                canvas.drawLine(tempStart.x, tempStart.y, tempEnd.x, tempEnd.y, tempPaint);
            }
        }
    }
}