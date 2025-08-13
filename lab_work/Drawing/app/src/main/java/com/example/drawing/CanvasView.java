package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    public static final int SHAPE_NONE = 0;
    public static final int SHAPE_CIRCLE = 1;
    public static final int SHAPE_SQUARE = 2;
    public static final int SHAPE_RECTANGLE = 3;

    private int currentShape = SHAPE_NONE;
    private Paint paint;

    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setShape(int shape) {
        currentShape = shape;
        invalidate(); // request to redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int centerY = height / 2;

        int size = Math.min(width, height) / 3;

        switch (currentShape) {
            case SHAPE_CIRCLE:
                canvas.drawCircle(centerX, centerY, size, paint);
                break;
            case SHAPE_SQUARE:
                canvas.drawRect(centerX - size, centerY - size, centerX + size, centerY + size, paint);
                break;
            case SHAPE_RECTANGLE:
                canvas.drawRect(centerX - size, centerY - size / 2, centerX + size, centerY + size / 2, paint);
                break;
            default:
                // no shape, do nothing
                break;
        }
    }
}