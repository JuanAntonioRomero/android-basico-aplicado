package com.example.everis.android.androidbasicoaplicado.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.everis.android.androidbasicoaplicado.R;

public class CustomView extends LinearLayout {

    private static final Paint PAINT_TEXT = new Paint();
    private static final int[] BACKGROUND_COLOR = {Color.WHITE, Color.YELLOW, Color.RED, Color.CYAN, Color.MAGENTA, Color.LTGRAY};
    private static final int TEXT_MARGIN = 10;

    private String mTitle = "Default title";
    private int mSelectedColorIndex = 0;
    private Rect mTextBounds;

    public CustomView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (context != null && attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorChangingView);
            mTitle = typedArray.getString(R.styleable.ColorChangingView_customText);
            typedArray.recycle();
        }

        int spSize = 28;
        float scaledSizeInPixels = spSize * getResources().getDisplayMetrics().scaledDensity;
        PAINT_TEXT.setTextSize(scaledSizeInPixels);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedColorIndex = (mSelectedColorIndex + 1) % BACKGROUND_COLOR.length;
                invalidate();
            }
        });

        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v("CustomView", "widthMeasureSpec: " + MeasureSpec.toString(widthMeasureSpec));
        Log.v("CustomView", "heightMeasureSpec: " + MeasureSpec.toString(heightMeasureSpec));

        mTextBounds = measureText();

        int desiredWidth = Math.max(getSuggestedMinimumWidth(), mTextBounds.width() + 2 * TEXT_MARGIN) + getPaddingLeft() + getPaddingRight();
        int desiredHeight = Math.max(getSuggestedMinimumHeight(), mTextBounds.height() + 2 * TEXT_MARGIN) + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec), measureDimension(desiredHeight, heightMeasureSpec));
    }

    private Rect measureText() {
        Rect bounds = new Rect();
        PAINT_TEXT.getTextBounds(mTitle, 0, mTitle.length(), bounds);
        return bounds;
    }

    private int measureDimension(int desiredSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // Must use the exact provided size
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = desiredSize; // No restrictions
            if (specMode == MeasureSpec.AT_MOST) {
                // The provided size is a maximum
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize) {
            Log.e("CustomView", "Resulting view is too small");
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v("CustomView", "OnDraw");
        super.onDraw(canvas);

        canvas.drawColor(BACKGROUND_COLOR[mSelectedColorIndex]);
        canvas.drawText(mTitle, TEXT_MARGIN, mTextBounds.height() - mTextBounds.bottom + TEXT_MARGIN, PAINT_TEXT);
    }
}
