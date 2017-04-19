package com.yugal.android.mobilemouse;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by Yugal on 18-Apr-17.
 */
public class myEditText extends EditText {

    private Random r = new Random();

    public myEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public myEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myEditText(Context context) {
        super(context);
    }


    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new ZanyInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }

    private class ZanyInputConnection extends InputConnectionWrapper {

        public ZanyInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            return super.sendKeyEvent(event);
        }

    }






}

