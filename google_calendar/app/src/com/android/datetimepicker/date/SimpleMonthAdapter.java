// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;

// Referenced classes of package com.android.datetimepicker.date:
//            MonthAdapter, SimpleMonthView, MonthView, DatePickerController

public final class SimpleMonthAdapter extends MonthAdapter
{

    public SimpleMonthAdapter(Context context, DatePickerController datepickercontroller)
    {
        super(context, datepickercontroller);
    }

    public final MonthView createMonthView(Context context)
    {
        context = new SimpleMonthView(context);
        context.mController = mController;
        return context;
    }
}
