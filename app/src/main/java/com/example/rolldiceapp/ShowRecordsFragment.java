package com.example.rolldiceapp;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ShowRecordsFragment extends Fragment {

    private TextView result;
    private DatabaseHelper dbHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_records, container, false);

        // Initialize views and database helper
        result = view.findViewById(R.id.result);
        dbHandler = new DatabaseHelper(getActivity());
        // Display records
        displayRecords();
        return view;
    }

    // Method to display all records in the TextView
    private void displayRecords() {
        // Get all records from the database
        Cursor cursor = dbHandler.getAllRecords();
        StringBuilder stringBuilder = new StringBuilder();
        // Check if cursor is not null and contains data
        if (cursor != null && cursor.moveToFirst()) {
            // Iterate through the cursor and append record details to StringBuilder
            do {
                @SuppressLint("Range") int rollId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID));
                @SuppressLint("Range") int rollValue = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ROLL_VALUE));
                stringBuilder.append("Roll: ").append(rollId).append(", Roll Value: ").append(rollValue).append("\n");
            } while (cursor.moveToNext());
            // Set the text of the result TextView with the records
            result.setText(stringBuilder.toString());
        } else {
            // If no records found, show a toast message
            Toast.makeText(getActivity(), "No records found", Toast.LENGTH_SHORT).show();
        }

        // Close the cursor
        if (cursor != null) {
            cursor.close();
        }
    }
}