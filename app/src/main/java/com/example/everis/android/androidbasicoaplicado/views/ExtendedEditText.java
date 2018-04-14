package com.example.everis.android.androidbasicoaplicado.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class ExtendedEditText extends AppCompatEditText {

    private static final Paint PAINT = new Paint();
    private static final int GRID_STEP = 30;

    public ExtendedEditText(Context context) {
        super(context);
        init();
    }

    public ExtendedEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExtendedEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        PAINT.setColor(Color.rgb(120, 120, 120));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int horizontalSteps = width / GRID_STEP;
        int verticalSteps = height / GRID_STEP;
        for (int i = 0; i < horizontalSteps; i++) {
            for (int j = 0; j < verticalSteps; j++) {
                canvas.drawLine(0, GRID_STEP*j, width, GRID_STEP*j, PAINT);
                canvas.drawLine(GRID_STEP*i, 0, GRID_STEP*i, width, PAINT);
            }
        }
        super.onDraw(canvas);
    }
}
