package com.example.drawing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCircle, btnRectangle, btnClear;
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCircle = findViewById(R.id.btnCircle);
        btnRectangle = findViewById(R.id.btnRectangle);
        btnClear = findViewById(R.id.btnClear);
        drawingView = findViewById(R.id.drawingView);

        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setShapeType("circle");
            }
        });

        btnRectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setShapeType("rectangle");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.clearShapes();
            }
        });
    }
}