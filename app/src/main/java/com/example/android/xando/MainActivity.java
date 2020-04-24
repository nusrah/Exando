package com.example.android.xando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    // x is 0, y is 1, empty is 2
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // winning positions are horizontally, diagonally, and vertically.
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    int activePlayer = 0;
    boolean gameActive = true;
    public int i;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x_image);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o_image);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            // To determine that someone has won

            for (int[] winingPosition : winningPositions) {
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]] &&
                        gameState[winingPosition[1]] == gameState[winingPosition[2]] &&
                        gameState[winingPosition[0]] != 2) {
                    //Someone has won
                    gameActive = false;

                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Blue O ";
                    } else {
                        winner = "Yellow X ";
                    }


                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + "has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }


        public void playAgain (View view){
            Button playAgainButton = findViewById(R.id.playAgainButton);
            TextView winnerTextView = findViewById(R.id.winnerTextView);
            winnerTextView.setVisibility(View.INVISIBLE);
            playAgainButton.setVisibility(View.INVISIBLE);

            GridLayout gridLayout = findViewById(R.id.gridLayout);
            for (i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }

            for (i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }

            activePlayer = 0;
            gameActive = true;
        }


        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }
