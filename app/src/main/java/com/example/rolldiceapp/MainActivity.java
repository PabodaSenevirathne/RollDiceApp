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

        button = findViewById(R.id.button);
        viewRecordButton = findViewById(R.id.viewRecordButton);
        imageView = findViewById(R.id.imageContainer);
        result = findViewById(R.id.result);
        dbHandler = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

//        viewRecordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showRecordsFragment();
//            }
//        });
//    }

        viewRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRecordShown) {
                    showRecordsFragment();
                    viewRecordButton.setText("Close"); // Change button text to "Close"
                    isRecordShown = true; // Records are shown
                } else {
                    closeRecordsFragment();
                    viewRecordButton.setText("View Records"); // Change button text back to "View Records"
                    isRecordShown = false; // Records are not shown
                }
            }
        });
    }

    // Roll the dice and update UI
    private void rollDice() {
        Random random = new Random();
        int rollNumber = random.nextInt(6) + 1; // Generate random number from 1 to 6

        // Update dice image
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

        // Display roll result
        result.setText("Result: " + rollNumber);

        // Add the roll to the database
        dbHandler.addRoll(rollNumber, rollNumber);

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
        getSupportFragmentManager().popBackStack(); // Close the fragment
    }
}