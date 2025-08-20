package com.example.sqlite2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

public class MainActivity extends AppCompatActivity {

    EditText etName, etDesignation, etLocation;
    Button btnSave;
    LinearLayout entriesLayout;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDesignation = findViewById(R.id.etDesignation);
        etLocation = findViewById(R.id.etLocation);
        btnSave = findViewById(R.id.btnSave);
        entriesLayout = findViewById(R.id.entriesLayout);

        dbHelper = new DBHelper(this);

        // Load old entries when app opens
        loadEntries();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String desig = etDesignation.getText().toString();
                String loc = etLocation.getText().toString();

                if (name.isEmpty() || desig.isEmpty() || loc.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper.insertData(name, desig, loc);
                etName.setText("");
                etDesignation.setText("");
                etLocation.setText("");
                loadEntries();
            }
        });
    }

    void loadEntries() {
        entriesLayout.removeAllViews();
        Cursor cursor = dbHelper.getAllData();

        if (cursor.getCount() == 0) {
            return;
        }

        while (cursor.moveToNext()) {
            final int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desig = cursor.getString(2);
            String loc = cursor.getString(3);
            String time = cursor.getString(4);

            // Box for each entry
            LinearLayout box = new LinearLayout(this);
            box.setOrientation(LinearLayout.VERTICAL);
            box.setPadding(20, 20, 20, 20);
            box.setBackgroundColor(0xFFE0E0E0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            box.setLayoutParams(params);

            TextView tv = new TextView(this);
            tv.setText("ID: " + id + "\nName: " + name + "\nDesignation: " + desig + "\nLocation: " + loc + "\nTime: " + time);

            Button deleteBtn = new Button(this);
            deleteBtn.setText("Delete");

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.deleteData(id);
                    loadEntries();
                }
            });

            box.addView(tv);
            box.addView(deleteBtn);

            entriesLayout.addView(box);
        }
    }