// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;

// Referenced classes of package com.android.datetimepicker.date:
//            DayPickerView, SimpleMonthAdapter, DatePickerController, MonthAdapter

public final class SimpleDayPickerView extends DayPickerView
{

    public SimpleDayPickerView(Context context, DatePickerController datepickercontroller)
    {
        super(context, datepickercontroller);
    }

    public final MonthAdapter createMonthAdapter(Context context, DatePickerController datepickercontroller)
    {
        return new SimpleMonthAdapter(context, datepickercontroller);
    }
}
