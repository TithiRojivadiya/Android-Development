package com.example.drawing2;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    DrawingView drawingView;
    EditText strokeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        strokeInput = findViewById(R.id.strokeSize);

        Button circleBtn = findViewById(R.id.circle);
        Button rectBtn = findViewById(R.id.ractangle);
        Button lineBtn = findViewById(R.id.line);
        Button clearBtn = findViewById(R.id.clear);
        Button redBtn = findViewById(R.id.red);
        Button blackBtn = findViewById(R.id.black);

        circleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apply("circle");
            }
        });

        rectBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apply("rectangle");
            }
        });

        lineBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apply("line");
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawingView.clear();
            }
        });

        redBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawingView.setStrokeColor(Color.RED);
            }
        });

        blackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawingView.setStrokeColor(Color.BLACK);
            }
        });
    }

    private void apply(String shape) {
        String s = strokeInput.getText().toString();
        if (!s.isEmpty()) {
            drawingView.setStrokeSize(Float.parseFloat(s));
        }
        drawingView.setShape(shape);
    }
}
