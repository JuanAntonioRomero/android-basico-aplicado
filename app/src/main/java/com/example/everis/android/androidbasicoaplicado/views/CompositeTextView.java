package com.example.everis.android.androidbasicoaplicado.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.everis.android.androidbasicoaplicado.R;

public class CompositeTextView extends LinearLayout {

    public CompositeTextView(Context context) {
        super(context);
        init(context);
    }

    public CompositeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CompositeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LinearLayout rootView = (LinearLayout) inflate(context, R.layout.view_composite_textview, this);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "CompositeTextView says: \"It works!\"", Toast.LENGTH_LONG).show();
            }
        });
    }
}
