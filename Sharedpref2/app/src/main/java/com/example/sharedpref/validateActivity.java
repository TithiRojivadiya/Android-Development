package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class validateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validate);

        TextView result = findViewById(R.id.resultView);
        Button btnLogOut = findViewById(R.id.btnLogOut);

        // Get username from intent
        String username = getIntent().getStringExtra("username");
        result.setText("Hello, " + username);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(validateActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
