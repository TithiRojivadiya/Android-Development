package com.example.lab2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private RadioButton male, female;
    private CheckBox c, cpp, java;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        c = findViewById(R.id.c);
        cpp = findViewById(R.id.cpp);
        java = findViewById(R.id.java);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString().trim();
                String gender = male.isChecked() ? "Male" : female.isChecked() ? "Female" : "Not specified";

                StringBuilder langs = new StringBuilder();
                if (c.isChecked()) langs.append("C ");
                if (cpp.isChecked()) langs.append("C++ ");
                if (java.isChecked()) langs.append("Java ");

                String languages = langs.toString().trim();

                String data = "Name: " + n + "\n" +
                        "Gender: " + gender + "\n" +
                        "Languages: " + languages + "\n\n";

                try {
                    FileOutputStream fos = openFileOutput("user_data.txt", Context.MODE_APPEND);
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(MainActivity.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("name", n);
                    intent.putExtra("gender", gender);
                    intent.putExtra("languages", languages);
                    startActivity(intent);
                    clearFields();

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void clearFields() {
        name.setText("");
        male.setChecked(false);
        female.setChecked(false);
        c.setChecked(false);
        cpp.setChecked(false);
        java.setChecked(false);
    }
}
