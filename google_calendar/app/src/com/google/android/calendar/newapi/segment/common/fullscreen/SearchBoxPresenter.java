// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common.fullscreen;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public final class SearchBoxPresenter
    implements TextWatcher, android.view.View.OnAttachStateChangeListener, android.view.View.OnClickListener, android.widget.TextView.OnEditorActionListener
{

    private final View clearButton;
    private final View doneButton;
    private final EditText editText;
    private final Listener listener;

    private SearchBoxPresenter(EditText edittext, View view, View view1, Listener listener1)
    {
        editText = edittext;
        clearButton = view;
        doneButton = view1;
        listener = listener1;
    }

    public static void create(EditText edittext, View view, View view1, Listener listener1)
    {
        boolean flag;
        if (!view.hasOnClickListeners())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        listener1 = new SearchBoxPresenter(edittext, view, view1, listener1);
        edittext.setOnEditorActionListener(listener1);
        edittext.addTextChangedListener(listener1);
        edittext.addOnAttachStateChangeListener(listener1);
        view.setOnClickListener(listener1);
        if (view1 != null)
        {
            view1.setOnClickListener(listener1);
        }
        listener1.updateButtonVisibilities(edittext.getText());
    }

    private final void updateButtonVisibilities(CharSequence charsequence)
    {
        boolean flag1 = true;
        boolean flag = false;
        int i;
        if (!TextUtils.isEmpty(charsequence))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        charsequence = clearButton;
        if (charsequence != null)
        {
            int j;
            if (i != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            charsequence.setVisibility(j);
        }
        charsequence = doneButton;
        if (i == 0)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (charsequence != null)
        {
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            charsequence.setVisibility(i);
        }
    }

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final void onClick(View view)
    {
        if (view == clearButton)
        {
            editText.setText("");
            return;
        }
        if (view == doneButton)
        {
            listener.onDonePressed();
            return;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public final boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        listener.onEnterPressed();
        return true;
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        updateButtonVisibilities(charsequence);
        listener.onTextChanged(charsequence);
    }

    public final void onViewAttachedToWindow(View view)
    {
        updateButtonVisibilities(editText.getText());
    }

    public final void onViewDetachedFromWindow(View view)
    {
    }

    private class Listener
    {

        public abstract void onDonePressed();

        public abstract void onEnterPressed();

        public abstract void onTextChanged(CharSequence charsequence);
    }

}
