// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common;

import android.text.Editable;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

// Referenced classes of package com.google.android.calendar.newapi.common:
//            ShinobiEditText

final class this._cls0 extends InputConnectionWrapper
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

    a(InputConnection inputconnection)
    {
        this$0 = ShinobiEditText.this;
        super(inputconnection, true);
    }
}
