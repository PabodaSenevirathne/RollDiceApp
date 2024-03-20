package com.example.rolldiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private DatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageContainer);
        dbHandler = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    // Roll the dice and update UI
    private void rollDice() {
        Random random = new Random();
        int rollNumber = random.nextInt(6) + 1; // Generate random number from 1 to 6
        switch (rollNumber) {
            case 1:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dice_1);
                break;
        }
        // Add the roll to the database
        dbHandler.addRoll(rollNumber, rollNumber);
        Toast.makeText(this, "Roll added successfully", Toast.LENGTH_SHORT).show();
    }
}