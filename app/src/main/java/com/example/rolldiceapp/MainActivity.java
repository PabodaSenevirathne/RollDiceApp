package com.example.rolldiceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Variables
    private Button button;
    private ImageView imageView;
    private TextView result;
    private DatabaseHelper dbHandler;
    private Button viewRecordButton;
    private boolean isRecordShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables with views from layout
        button = findViewById(R.id.button);
        viewRecordButton = findViewById(R.id.viewRecordButton);
        imageView = findViewById(R.id.imageContainer);
        result = findViewById(R.id.result);
        dbHandler = new DatabaseHelper(this);

        // Set click listener for the roll button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        // Set click listener for the view record button
        viewRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if records fragment is already shown
                if (!isRecordShown) {
                    // Show records fragment
                    showRecordsFragment();
                    // Change button text to "Close"
                    viewRecordButton.setText(getString(R.string.button_text_close));
                    isRecordShown = true;
                } else {
                    // Close records fragment
                    closeRecordsFragment();
                    // Change button text back to "View Records"
                    viewRecordButton.setText(getString(R.string.button_text_view_records));
                    isRecordShown = false;
                }
            }
        });
    }

    // Roll the dice and update UI
    private void rollDice() {
        Random random = new Random();
        // Generate random number from 1 to 6
        int rollNumber = random.nextInt(6) + 1;

        // Update dice image based on roll number
        switch (rollNumber) {
            case 1:
                imageView.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.dice_6);
                break;
        }

        // Display result
        result.setText("Result: " + rollNumber);

        // Add the roll to the database
        dbHandler.addRoll(rollNumber, rollNumber);
        // Show toast message
        Toast.makeText(this, "Roll added successfully", Toast.LENGTH_SHORT).show();
    }

    // Show the fragment to view records
    private void showRecordsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ShowRecordsFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    // Close the records fragment
    private void closeRecordsFragment() {
        // Close the fragment
        getSupportFragmentManager().popBackStack();
    }
}