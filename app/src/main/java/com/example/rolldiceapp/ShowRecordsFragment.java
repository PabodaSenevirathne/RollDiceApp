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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowRecordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowRecordsFragment extends Fragment {

    private TextView result;
    private DatabaseHelper dbHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_records, container, false);
        result = view.findViewById(R.id.result);
        dbHandler = new DatabaseHelper(getActivity());
        displayRolls();
        return view;
    }

    // Display all rolls in the TextView
    private void displayRolls() {
        Cursor cursor = dbHandler.getAllRecords();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int rollNumber = cursor.getInt(cursor.getColumnIndex("roll_number"));
                stringBuilder.append("Roll: ").append(rollNumber).append("\n");
            } while (cursor.moveToNext());
            result.setText(stringBuilder.toString());
        } else {
            Toast.makeText(getActivity(), "No rolls found", Toast.LENGTH_SHORT).show();
        }
    }
}