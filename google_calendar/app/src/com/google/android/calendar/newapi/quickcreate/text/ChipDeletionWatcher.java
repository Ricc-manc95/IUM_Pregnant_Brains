// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.widget.EditText;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            Chip

public final class ChipDeletionWatcher
    implements SpanWatcher
{

    private final EditText editText;

    public ChipDeletionWatcher(EditText edittext)
    {
        editText = edittext;
    }

    public final void onSpanAdded(Spannable spannable, Object obj, int i, int j)
    {
    }

    public final void onSpanChanged(Spannable spannable, Object obj, int i, int j, int k, int l)
    {
        if ((obj instanceof Chip) && !((Chip)obj).text.equals(spannable.subSequence(k, Math.min(l, spannable.length())).toString()))
        {
            spannable = editText.getText();
            spannable.delete(k, Math.min(l, spannable.length()));
            editText.setSelection(k);
        }
    }

    public final void onSpanRemoved(Spannable spannable, Object obj, int i, int j)
    {
    }
}
