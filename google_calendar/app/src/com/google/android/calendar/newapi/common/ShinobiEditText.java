// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.google.android.calendar.common.view.NinjaEditText;
import java.util.ArrayList;

public class ShinobiEditText extends NinjaEditText
{
    final class DeleteInterceptingInputConnection extends InputConnectionWrapper
    {

        private final ShinobiEditText this$0;

        private final boolean interceptDelete(CharSequence charsequence)
        {
            if (onDeletePressedListener != null)
            {
                Editable editable = getText();
                int i = BaseInputConnection.getComposingSpanStart(editable);
                int j = BaseInputConnection.getComposingSpanEnd(editable);
                if (i != -1 && j > i && editable.subSequence(i, j - 1).toString().equals(charsequence.toString()))
                {
                    return onDeletePressed();
                }
            }
            return false;
        }

        public final boolean commitText(CharSequence charsequence, int i)
        {
            return interceptDelete(charsequence) || super.commitText(charsequence, i);
        }

        public final boolean deleteSurroundingText(int i, int j)
        {
            if (i == 1 && j == 0 && onDeletePressed())
            {
                return true;
            } else
            {
                return super.deleteSurroundingText(i, j);
            }
        }

        public final boolean setComposingText(CharSequence charsequence, int i)
        {
            return interceptDelete(charsequence) || super.setComposingText(charsequence, i);
        }

        DeleteInterceptingInputConnection(InputConnection inputconnection)
        {
            this$0 = ShinobiEditText.this;
            super(inputconnection, true);
        }
    }

    public static interface OnDeletePressedListener
    {

        public abstract boolean onDeletePressed();
    }

    public static interface OnSelectionChangedListener
    {

        public abstract void onSelectionChanged(int i, int j);
    }

    public static interface OnWidthChangeListener
    {

        public abstract void onWidthChanged(int i);
    }


    private ArrayList globalSpans;
    public OnDeletePressedListener onDeletePressedListener;
    public OnSelectionChangedListener onSelectionChangedListener;

    public ShinobiEditText(Context context)
    {
        super(context);
    }

    public ShinobiEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public final void addGlobalSpan(Object obj)
    {
        if (globalSpans == null)
        {
            globalSpans = new ArrayList();
        }
        globalSpans.add(obj);
        getText().setSpan(obj, 0, getText().length(), 18);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        InputConnection inputconnection = super.onCreateInputConnection(editorinfo);
        if (inputconnection == null)
        {
            return null;
        } else
        {
            editorinfo.imeOptions = editorinfo.imeOptions & 0xbfffffff;
            return new DeleteInterceptingInputConnection(inputconnection);
        }
    }

    final boolean onDeletePressed()
    {
        boolean flag;
        if (getSelectionStart() == getSelectionEnd() && getSelectionStart() == length())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && onDeletePressedListener != null && onDeletePressedListener.onDeletePressed();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag2 = false;
        if (i == 67)
        {
            boolean flag;
            boolean flag1;
            if (getSelectionStart() == getSelectionEnd() && getSelectionStart() == length())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag1 = flag2;
            if (flag)
            {
                flag1 = flag2;
                if (onDeletePressedListener != null)
                {
                    flag1 = flag2;
                    if (onDeletePressedListener.onDeletePressed())
                    {
                        flag1 = true;
                    }
                }
            }
            if (flag1)
            {
                return true;
            }
        }
        return super.onKeyDown(i, keyevent);
    }

    protected void onSelectionChanged(int i, int j)
    {
        super.onSelectionChanged(i, j);
        if (onSelectionChangedListener != null)
        {
            onSelectionChangedListener.onSelectionChanged(i, j);
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        int i1 = 0;
        OnWidthChangeListener aonwidthchangelistener[] = (OnWidthChangeListener[])getText().getSpans(0, getText().length(), com/google/android/calendar/newapi/common/ShinobiEditText$OnWidthChangeListener);
        for (int j1 = aonwidthchangelistener.length; i1 < j1; i1++)
        {
            aonwidthchangelistener[i1].onWidthChanged(getWidth() - getPaddingLeft() - getPaddingRight());
        }

        super.onSizeChanged(i, j, k, l);
    }

    protected void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        super.onTextChanged(charsequence, i, j, k);
        if (globalSpans != null)
        {
            charsequence = (ArrayList)globalSpans;
            j = charsequence.size();
            i = 0;
            while (i < j) 
            {
                Object obj = charsequence.get(i);
                i++;
                getText().setSpan(obj, 0, getText().length(), 18);
            }
        }
    }
}
