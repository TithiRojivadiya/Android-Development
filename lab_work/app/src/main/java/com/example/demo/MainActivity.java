package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText num1;
    private EditText num2;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        add = findViewById(R.id.add);
        result = findViewById(R.id.result);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = num1.getText().toString();
                int n1 = Integer.parseInt(s);
                s = num2.getText().toString();
                int n2 = Integer.parseInt(s);
                int addition = n1 + n2;
                result.setText("The addition of " + n1 + " and " + n2 + " is " + addition);
                Toast.makeText(MainActivity.this, "Addition Successful", Toast.LENGTH_SHORT).show();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}