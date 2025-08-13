package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private TextView nameInfo, genderInfo, langInfo;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        nameInfo = findViewById(R.id.name_info);
        genderInfo = findViewById(R.id.gender_info);
        langInfo = findViewById(R.id.lang_info);
        backBtn = findViewById(R.id.back_btn);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String languages = intent.getStringExtra("languages");

        nameInfo.setText("Name: " + name);
        genderInfo.setText("Gender: " + gender);
        langInfo.setText("Languages: " + languages);

        backBtn.setText("Back");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
