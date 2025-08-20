package com.example.drawing2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

class Shape {
    String type;
    float startX, startY, endX, endY;
    Paint paint;

    Shape(String t, float sx, float sy, float ex, float ey, Paint p) {
        type = t;
        startX = sx;
        startY = sy;
        endX = ex;
        endY = ey;
        paint = new Paint(p); // copy of current paint (includes stroke size, color, style)
    }
}

public class DrawingView extends View {

    ArrayList<Shape> shapes = new ArrayList<>();
    float startX, startY, endX, endY;
    String currentShape = "";
    Paint paint;

    private boolean singleShapeMode = true;
    private boolean isFill = false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);   // default color
        paint.setStrokeWidth(5);    //default stroke size
    }

    public void setShape(String s) {
        currentShape = s;
    }

    public void setStrokeColor(int c) {
        paint.setColor(c);
    }

    public void setStrokeSize(float s) {
        paint.setStrokeWidth(s);
    }

    public void clear() {
        shapes.clear();
        invalidate();
    }

    public void setSingleShapeMode(boolean single) {
        this.singleShapeMode = single;
    }

    public void setFill(boolean fill) {
        this.isFill = fill;
        if (fill) {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        } else {
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (currentShape.equals("")) {
            return false;
        }

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            startX = e.getX();
            startY = e.getY();
        }
        else if (e.getAction() == MotionEvent.ACTION_UP) {
            endX = e.getX();
            endY = e.getY();

            if (singleShapeMode) {
                shapes.clear();
            }


            shapes.add(new Shape(currentShape, startX, startY, endX, endY, paint));
            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Shape s : shapes) {
            if (s.type.equals("circle")) {
                float r = (float) Math.hypot(s.endX - s.startX, s.endY - s.startY);
                canvas.drawCircle(s.startX, s.startY, r, s.paint);
            }
            else if (s.type.equals("rectangle")) {
                canvas.drawRect(new RectF(s.startX, s.startY, s.endX, s.endY), s.paint);
            }
            else if (s.type.equals("line")) {
                canvas.drawLine(s.startX, s.startY, s.endX, s.endY, s.paint);
            }
        }
    }
}
