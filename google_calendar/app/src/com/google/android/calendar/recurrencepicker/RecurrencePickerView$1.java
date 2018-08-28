// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.recurrencepicker;

import android.widget.ArrayAdapter;
import android.widget.EditText;

// Referenced classes of package com.google.android.calendar.recurrencepicker:
//            RecurrencePickerView, RecurrencePickerState

final class nMaxTextWatcher extends nMaxTextWatcher
{

    private final RecurrencePickerView this$0;

    final void onChange(int i)
    {
        frequencyAdapter.clear();
        frequencyAdapter.addAll(getFrequencyPluralityOptionsList(i));
        if (state.getInterval().intValue() != i)
        {
            refresh(state.toBuilder().setInterval(Integer.valueOf(i)).build());
            interval.requestFocus();
        }
    }

    uilder(int i, int j, int k)
    {
        this$0 = RecurrencePickerView.this;
        super(1, 1, 99);
    }
}
