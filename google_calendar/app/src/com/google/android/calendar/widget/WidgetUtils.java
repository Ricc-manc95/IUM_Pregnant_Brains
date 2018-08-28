// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import com.google.android.calendar.time.Time;

public final class WidgetUtils
{

    static String formatDebugTime(long l, long l1)
    {
        Time time = new Time();
        time.set(l);
        l1 = l - l1;
        if (l1 > 60000L)
        {
            l1 /= 60000L;
            time.writeFieldsToImpl();
            return String.format(null, "[%d] %s (%+d minutes)", new Object[] {
                Long.valueOf(l), (new android.text.format.Time(time.impl)).format("%H:%M:%S"), Long.valueOf(l1)
            });
        } else
        {
            l1 /= 1000L;
            time.writeFieldsToImpl();
            return String.format(null, "[%d] %s (%+d seconds)", new Object[] {
                Long.valueOf(l), (new android.text.format.Time(time.impl)).format("%H:%M:%S"), Long.valueOf(l1)
            });
        }
    }

    public static int getChipTextColor(com.google.android.calendar.timeline.chip.ChipViewModel.ColorStyle colorstyle, int i, int j, int k)
    {
        switch (colorstyle.ordinal())
        {
        default:
            i = j;
            // fall through

        case 1: // '\001'
            return i;

        case 2: // '\002'
            return k;
        }
    }

    public static String getWidgetCallerIsSyncAdapterAction(Context context)
    {
        try
        {
            context = String.valueOf(context.getPackageName()).concat(".APPWIDGET_CALLER_IS_SYNCADAPTER");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }

    public static String getWidgetTaskChanged(Context context)
    {
        try
        {
            context = String.valueOf(context.getPackageName()).concat(".APPWIDGET_TASK_CHANGED");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }
}
