// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Calendar;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetUtils

final class MonthViewWidgetState
{

    public final int selectedMonth;
    public final int selectedYear;

    private MonthViewWidgetState(int i, int j)
    {
        selectedYear = i;
        selectedMonth = j;
    }

    static MonthViewWidgetState ensureStateExists(Context context, int i)
    {
        int j;
        int k;
label0:
        {
            Object obj = context.getSharedPreferences("com.google.android.calendar.widgetmonth", 0);
            k = ((SharedPreferences) (obj)).getInt((new StringBuilder(String.valueOf(".selectedYear").length() + 11)).append(i).append(".selectedYear").toString(), 0x80000000);
            int l = ((SharedPreferences) (obj)).getInt((new StringBuilder(String.valueOf(".selectedMonth").length() + 11)).append(i).append(".selectedMonth").toString(), 0x80000000);
            if (k != 0x80000000)
            {
                j = l;
                if (l != 0x80000000)
                {
                    break label0;
                }
            }
            obj = ((SharedPreferences) (obj)).edit();
            context = MonthViewWidgetUtils.getCalendar(context);
            k = context.get(1);
            j = context.get(2);
            ((android.content.SharedPreferences.Editor) (obj)).putInt((new StringBuilder(String.valueOf(".selectedYear").length() + 11)).append(i).append(".selectedYear").toString(), k);
            ((android.content.SharedPreferences.Editor) (obj)).putInt((new StringBuilder(String.valueOf(".selectedMonth").length() + 11)).append(i).append(".selectedMonth").toString(), j);
            ((android.content.SharedPreferences.Editor) (obj)).apply();
        }
        return new MonthViewWidgetState(k, j);
    }

    static MonthViewWidgetState loadState(Context context, int i)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("com.google.android.calendar.widgetmonth", 0);
        int j = sharedpreferences.getInt((new StringBuilder(String.valueOf(".selectedYear").length() + 11)).append(i).append(".selectedYear").toString(), 0x80000000);
        int k = sharedpreferences.getInt((new StringBuilder(String.valueOf(".selectedMonth").length() + 11)).append(i).append(".selectedMonth").toString(), 0x80000000);
        if (j == 0x80000000 || k == 0x80000000)
        {
            return ensureStateExists(context, i);
        } else
        {
            return new MonthViewWidgetState(j, k);
        }
    }

    static void storeSelectedYearMonth(Context context, int i, int j, int k)
    {
        context = context.getSharedPreferences("com.google.android.calendar.widgetmonth", 0).edit();
        context.putInt((new StringBuilder(String.valueOf(".selectedYear").length() + 11)).append(i).append(".selectedYear").toString(), j);
        context.putInt((new StringBuilder(String.valueOf(".selectedMonth").length() + 11)).append(i).append(".selectedMonth").toString(), k);
        context.apply();
    }
}
