// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.EditText;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

public final class TitleEditSegment extends EditSegment
{

    public View rippleView;
    public ShinobiEditText titleEditText;

    public TitleEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, null);
        setFocusableInTouchMode(true);
        inflate(context, 0x7f0500f0, this);
        rippleView = findViewById(0x7f10024a);
        titleEditText = (ShinobiEditText)findViewById(0x7f100268);
        class .Lambda._cls0
            implements android.view.View.OnTouchListener
        {

            private final TitleEditSegment arg$1;

            public final boolean onTouch(View view, MotionEvent motionevent)
            {
                return ((Listener)((EditSegment) (arg$1)).mListener).onTitleTouched();
            }

            .Lambda._cls0()
            {
                arg$1 = TitleEditSegment.this;
            }

            private class Listener
            {

                public abstract void onKeyboardDone();

                public abstract void onTitleChanged(String s);

                public abstract boolean onTitleTouched();
            }

        }

        titleEditText.setOnTouchListener(new .Lambda._cls0());
        titleEditText.addTextChangedListener(new _cls1());
        class .Lambda._cls1
            implements android.widget.TextView.OnEditorActionListener
        {

            private final TitleEditSegment arg$1;

            public final boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
            {
                textview = arg$1;
                if (i == 6 || i == 1)
                {
                    ((Listener)((TitleEditSegment) (textview)).mListener).onKeyboardDone();
                    textview = ((TitleEditSegment) (textview)).titleEditText;
                    ((InputMethodManager)textview.getContext().getSystemService("input_method")).hideSoftInputFromWindow(textview.getWindowToken(), 0);
                    return true;
                } else
                {
                    return false;
                }
            }

            .Lambda._cls1()
            {
                arg$1 = TitleEditSegment.this;
            }
        }

        titleEditText.setOnEditorActionListener(new .Lambda._cls1());
    }

    public final WindowInsets onApplyWindowInsets(WindowInsets windowinsets)
    {
        View view = findViewById(0x7f1002a1);
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        marginlayoutparams.topMargin = windowinsets.getSystemWindowInsetTop();
        view.setLayoutParams(marginlayoutparams);
        return windowinsets;
    }

    public final void setTitle(String s)
    {
        String s1 = s;
        if (TextUtils.isEmpty(s))
        {
            s1 = "";
        }
        if (!s1.equals(titleEditText.getText().toString()))
        {
            s = titleEditText;
            s.stealth = true;
            s.setText(s1);
            s.stealth = false;
            titleEditText.setSelection(titleEditText.getText().length());
        }
    }

    public final void showKeyboard()
    {
        if (titleEditText != null)
        {
            titleEditText.setSelection(titleEditText.getText().length());
            ShinobiEditText shinobiedittext = titleEditText;
            shinobiedittext.getClass();
            shinobiedittext.post(new com.google.android.calendar.newapi.common.Keyboard..Lambda._cls0(shinobiedittext));
            shinobiedittext.setOnFocusChangeListener(com.google.android.calendar.newapi.common.Keyboard..Lambda._cls1.$instance);
        }
    }

    private class _cls1
        implements TextWatcher
    {

        private final TitleEditSegment this$0;

        public final void afterTextChanged(Editable editable)
        {
            if (mListener == null || editable == null)
            {
                return;
            } else
            {
                ((Listener)mListener).onTitleChanged(editable.toString());
                return;
            }
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        _cls1()
        {
            this$0 = TitleEditSegment.this;
            super();
        }
    }

}
