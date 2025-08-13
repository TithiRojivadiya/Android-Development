package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    private float startX, startY, endX, endY;
    private String shapeType = "circle"; // default
    private Paint strokePaint, previewPaint;
    private List<Shape> shapes = new ArrayList<>();
    private boolean isDragging = false;

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Stroke paint for all shapes
        strokePaint = new Paint();
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(5);

        // Preview paint while dragging
        previewPaint = new Paint();
        previewPaint.setColor(Color.GRAY);
        previewPaint.setStyle(Paint.Style.STROKE);
        previewPaint.setStrokeWidth(5);
    }

    public void setShapeType(String type) {
        this.shapeType = type;
    }

    public void clearShapes() {
        shapes.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw saved shapes
        for (Shape shape : shapes) {
            if (shape.type.equals("circle")) {
                float radius = (float) Math.sqrt(
                        Math.pow(shape.endX - shape.startX, 2) +
                                Math.pow(shape.endY - shape.startY, 2)
                );
                canvas.drawCircle(shape.startX, shape.startY, radius, strokePaint);
            } else if (shape.type.equals("rectangle")) {
                canvas.drawRect(shape.startX, shape.startY, shape.endX, shape.endY, strokePaint);
            }
        }

        // Draw preview while dragging
        if (isDragging) {
            if (shapeType.equals("circle")) {
                float radius = (float) Math.sqrt(
                        Math.pow(endX - startX, 2) +
                                Math.pow(endY - startY, 2)
                );
                canvas.drawCircle(startX, startY, radius, previewPaint);
            } else if (shapeType.equals("rectangle")) {
                canvas.drawRect(startX, startY, endX, endY, previewPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                isDragging = true;
                break;

            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                isDragging = false;
                shapes.add(new Shape(shapeType, startX, startY, endX, endY));
                invalidate();
                break;
        }
        return true;
    }

    // Shape data holder
    private static class Shape {
        String type;
        float startX, startY, endX, endY;

        Shape(String type, float startX, float startY, float endX, float endY) {
            this.type = type;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
}