// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.text.Editable;
import android.text.TextWatcher;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerView

static class mDefault
    implements TextWatcher
{

    private int mDefault;
    private int max;
    private int min;

    public void afterTextChanged(Editable editable)
    {
        int i;
        int j;
        int k;
        try
        {
            i = Integer.parseInt(editable.toString());
        }
        catch (NumberFormatException numberformatexception)
        {
            i = mDefault;
        }
        k = Math.min(max, Math.max(min, i));
        j = i;
        if (k != i)
        {
            editable.clear();
            editable.append(String.format("%d", new Object[] {
                Integer.valueOf(k)
            }));
            j = k;
        }
        onChange(j);
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    void onChange(int i)
    {
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    Y(int i, int j, int k)
    {
        min = i;
        max = k;
        mDefault = j;
    }
}
