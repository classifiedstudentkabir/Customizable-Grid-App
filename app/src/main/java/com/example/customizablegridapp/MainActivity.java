// MainActivity.java
package com.example.customizablegridapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private CardView selectedCard = null;
    private TextView selectedCheckMark = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(12);
        gridLayout.setColumnCount(5);
        findViewById(R.id.mainLayout).setBackgroundColor(Color.parseColor("#F0F0F0"));  // Light background

        Button colorButton = findViewById(R.id.colorButton);
        Button checkMarkButton = findViewById(R.id.checkMarkButton);

        colorButton.setOnClickListener(v -> showColorOptions());
        checkMarkButton.setOnClickListener(v -> addCheckMark());

        for (int i = 0; i < 60; i++) {
            CardView card = new CardView(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 150;
            params.height = 150;
            params.setMargins(10, 10, 10, 10);
            card.setLayoutParams(params);
            card.setCardBackgroundColor(Color.LTGRAY);
            card.setRadius(8);

            TextView checkMark = new TextView(this);
            checkMark.setText("✔");
            checkMark.setTextSize(24);
            checkMark.setVisibility(View.GONE);
            checkMark.setGravity(View.TEXT_ALIGNMENT_CENTER);
            checkMark.setTextColor(Color.BLACK);  // Visible checkmark color
            card.addView(checkMark);

            card.setOnClickListener(v -> {
                selectedCard = card;
                selectedCheckMark = checkMark;
            });

            gridLayout.addView(card);
        }
    }

    private void showColorOptions() {
        if (selectedCard != null) {
            int defaultColor = Color.LTGRAY;  // Default color for boxes
            int currentColor = ((CardView) selectedCard).getCardBackgroundColor().getDefaultColor();

            // Check if the box already has a custom color
            if (currentColor != defaultColor) {
                selectedCard.setCardBackgroundColor(defaultColor);  // Remove custom color (reset to default)
            } else {
                // Show color options if no custom color is set
                String[] colors = {"Red", "Green", "Blue", "Yellow", "Purple"};
                int[] colorValues = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, 0xFF800080};
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setItems(colors, (dialog, which) -> selectedCard.setCardBackgroundColor(colorValues[which]))
                        .show();
            }
        }
    }

    private void addCheckMark() {
        if (selectedCheckMark != null) {
            if (selectedCheckMark.getVisibility() == View.VISIBLE) {
                selectedCheckMark.setVisibility(View.GONE);   // Remove checkmark if already visible
            } else {
                selectedCheckMark.setVisibility(View.VISIBLE); // Show checkmark if not visible
            }
        }
    }
}

