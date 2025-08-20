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
    Button btnPlus, btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        strokeInput = findViewById(R.id.strokeSize);

        btnPlus = findViewById(R.id.plus);
        btnMinus = findViewById(R.id.minus);

        Button circleBtn = findViewById(R.id.circle);
        Button rectBtn = findViewById(R.id.ractangle);
        Button lineBtn = findViewById(R.id.line);
        Button clearBtn = findViewById(R.id.clear);
        Button redBtn = findViewById(R.id.red);
        Button blackBtn = findViewById(R.id.black);
        Button greenBtn = findViewById(R.id.green);
        Button blueBtn = findViewById(R.id.blue);
        Button fillBtn = findViewById(R.id.fillColor);

        redBtn.setBackgroundColor(Color.RED);
        blackBtn.setBackgroundColor(Color.BLACK);
        greenBtn.setBackgroundColor(Color.GREEN);
        blueBtn.setBackgroundColor(Color.BLUE);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = strokeInput.getText().toString();
                int value = s.isEmpty() ? 1 : Integer.parseInt(s);
                value++;
                strokeInput.setText(String.valueOf(value));
                drawingView.setStrokeSize(value);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = strokeInput.getText().toString();
                int value = s.isEmpty() ? 1 : Integer.parseInt(s);
                if (value > 1) { // avoid 0 or negative
                    value--;
                }
                strokeInput.setText(String.valueOf(value));
                drawingView.setStrokeSize(value);
            }
        });

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

        blueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawingView.setStrokeColor(Color.BLUE);
            }
        });

        greenBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                drawingView.setStrokeColor(Color.GREEN);
            }
        });


        fillBtn.setOnClickListener(new View.OnClickListener() {
            boolean isFill = false;

            @Override
            public void onClick(View v) {
                isFill = !isFill;
                drawingView.setFill(isFill);

                if (isFill) {
                    fillBtn.setText("No Fill");
                } else {
                    fillBtn.setText("Fill");
                }
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
