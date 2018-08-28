// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.text.Editable;
import android.widget.EditText;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            Chip

public final class ChipSelectionWatcher
    implements com.google.android.calendar.newapi.common.ShinobiEditText.OnSelectionChangedListener
{

    private final EditText editText;

    public ChipSelectionWatcher(EditText edittext)
    {
        editText = edittext;
    }

    private final Chip getChip(int i)
    {
        Chip achip[] = (Chip[])editText.getText().getSpans(i, i, com/google/android/calendar/newapi/quickcreate/text/Chip);
        if (achip.length == 0)
        {
            return null;
        } else
        {
            return achip[0];
        }
    }

    private final int getClosestEdge(Chip chip, int i)
    {
        if (chip == null)
        {
            return i;
        }
        int j = editText.getText().getSpanStart(chip);
        int k = editText.getText().getSpanEnd(chip);
        if (Math.abs(j - i) < Math.abs(k - i))
        {
            return j;
        } else
        {
            return k;
        }
    }

    public final void onSelectionChanged(int i, int j)
    {
        if (i == j)
        {
            editText.setSelection(getClosestEdge(getChip(i), i));
        } else
        {
            Chip chip = getChip(i);
            Chip chip1 = getChip(j);
            if (chip != null || chip1 != null)
            {
                if (chip == chip1)
                {
                    editText.setSelection(editText.getText().getSpanStart(chip), editText.getText().getSpanEnd(chip));
                    return;
                } else
                {
                    editText.setSelection(getClosestEdge(chip, i), getClosestEdge(chip1, j));
                    return;
                }
            }
        }
    }
}
