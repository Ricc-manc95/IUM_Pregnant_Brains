// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.calendar.common.view.NinjaEditText;

public class NoteEditText extends NinjaEditText
{

    public NoteEditText(Context context)
    {
        super(context);
    }

    public NoteEditText(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public NoteEditText(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public NoteEditText(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public boolean onTextContextMenuItem(int i)
    {
        boolean flag = super.onTextContextMenuItem(i);
        if (i == 0x1020022)
        {
            android.text.Editable editable = getText();
            i = getSelectionStart();
            int j = getSelectionEnd();
            setText(editable.toString(), android.widget.TextView.BufferType.EDITABLE);
            setSelection(i, j);
        }
        return flag;
    }
}
