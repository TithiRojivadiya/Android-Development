package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private float startX, startY, endX, endY;
    private String currentShape = "";
    private Paint paint;
    private boolean drawing = false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
    }

    public void setShape(String shape) {
        currentShape = shape;
    }

    public void clear() {
        currentShape = "";
        drawing = false;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (currentShape.equals("")) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = event.getX();
            startY = event.getY();
            drawing = true;
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_UP) {
            endX = event.getX();
            endY = event.getY();
            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!drawing) return;

        if (currentShape.equals("circle")) {
            float r = (float) Math.hypot(endX - startX, endY - startY);
            canvas.drawCircle(startX, startY, r, paint);
        } else if (currentShape.equals("rectangle")) {
            canvas.drawRect(new RectF(startX, startY, endX, endY), paint);
        }
    }
}
