// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.UserManager;
import android.provider.CalendarContract;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.android.calendar.event.LaunchInfoActivity;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.launch.LaunchIntentConstants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetState, MonthViewWidgetProvider

public class MonthViewWidgetUtils
{
    static interface DayRangeGetter
    {

        public abstract int getEndDay(Object obj);

        public abstract int getStartDay(Object obj);

        public abstract boolean spansAtLeastOneDay(Object obj);
    }


    private static final String TAG = com/google/android/calendar/widgetmonth/MonthViewWidgetUtils.getSimpleName();

    public MonthViewWidgetUtils()
    {
    }

    private static void addItemToJulianDay(Object obj, int i, SparseArray sparsearray)
    {
        List list = (List)sparsearray.get(i);
        Object obj1 = list;
        if (list == null)
        {
            obj1 = new ArrayList();
            sparsearray.put(i, obj1);
        }
        ((List) (obj1)).add(obj);
    }

    private static void addItemsToJulianDays(List list, DayRangeGetter dayrangegetter, int i, int j, SparseArray sparsearray)
    {
        for (list = list.iterator(); list.hasNext();)
        {
            Object obj = list.next();
            if (dayrangegetter.spansAtLeastOneDay(obj))
            {
                int k = Math.max(i, dayrangegetter.getStartDay(obj));
                int l = Math.min(j, dayrangegetter.getEndDay(obj));
                while (k <= l) 
                {
                    addItemToJulianDay(obj, k, sparsearray);
                    k++;
                }
            } else
            {
                addItemToJulianDay(obj, dayrangegetter.getStartDay(obj), sparsearray);
            }
        }

    }

    public static void addPartitionItemsToJulianDays(List list, int i, int j, SparseArray sparsearray)
    {
        addItemsToJulianDays(list, new _cls1(), i, j, sparsearray);
    }

    public static void addTimelineItemsToJulianDays(List list, int i, int j, SparseArray sparsearray)
    {
        addItemsToJulianDays(list, new _cls2(), i, j, sparsearray);
    }

    public static int computeMaxItemsPerDayForWidgetHeight(Context context, int i, int j)
    {
        context = context.getResources();
        i = Math.round((float)i * context.getDisplayMetrics().density);
        int k = context.getDimensionPixelSize(0x7f0e042b);
        if (i < k)
        {
            LogUtils.w(TAG, "The reported height of the widget is lower than the allowed minimum: %d < %d.", new Object[] {
                Integer.valueOf(i), Integer.valueOf(k)
            });
        }
        i = Math.max(i, k);
        k = context.getDimensionPixelSize(0x7f0e0428);
        int l = context.getDimensionPixelSize(0x7f0e0422);
        int i1 = context.getDimensionPixelSize(0x7f0e0421);
        int j1 = context.getDimensionPixelSize(0x7f0e0433);
        int k1 = context.getDimensionPixelSize(0x7f0e0410);
        i = (i - k - (l + i1) - j1 - j * k1) / (k1 * j);
        if (i < 0)
        {
            LogUtils.wtf(TAG, "Computations yielded an invalid number of max items per day: %d", new Object[] {
                Integer.valueOf(i)
            });
            return 0;
        } else
        {
            return i;
        }
    }

    public static int computeTodayNumberTextWidthPx(Context context, String s)
    {
        context = (TextView)LayoutInflater.from(context).inflate(0x7f0501ae, null);
        context.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        context.setText(s);
        context.measure(0, 0);
        return context.getMeasuredWidth();
    }

    public static PendingIntent createLaunchCalendarPendingIntent(Context context)
    {
        Intent intent = new Intent("android.intent.action.MAIN", null, context, com/android/calendar/event/LaunchInfoActivity);
        intent.putExtra("intent_source", "monthwidget");
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    public static PendingIntent createPendingIntent(Context context, Class class1, String s, int i)
    {
        class1 = new Intent(context, class1);
        if (s != null)
        {
            class1.setAction(s);
        }
        class1.putExtra("appWidgetId", i);
        class1.setData(Uri.parse(class1.toUri(1)));
        return PendingIntent.getBroadcast(context, 0, class1, 0);
    }

    public static PendingIntent createViewDayPendingIntent(Context context, int i)
    {
        if (LaunchIntentConstants.dayViewAction == null)
        {
            LaunchIntentConstants.dayViewAction = String.valueOf(context.getPackageName()).concat(".DAY_VIEW");
        }
        Intent intent = new Intent(LaunchIntentConstants.dayViewAction, null, context, com/android/calendar/event/LaunchInfoActivity);
        intent.putExtra("intent_source", "monthwidget");
        intent.putExtra("julianDay", i);
        String s = String.valueOf(CalendarContract.CONTENT_URI);
        long l = Utils.getMillisFromJulianDay(i);
        intent.setData(Uri.parse((new StringBuilder(String.valueOf(s).length() + 26)).append(s).append("/time/").append(l).toString()));
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    public static SpannableString getBoldText(CharSequence charsequence)
    {
        SpannableString spannablestring = new SpannableString(charsequence);
        spannablestring.setSpan(new StyleSpan(1), 0, charsequence.length(), 0);
        return spannablestring;
    }

    public static Calendar getCalendar(Context context)
    {
        return Calendar.getInstance(Utils.getTimeZone(context));
    }

    public static Calendar getCalendarWithSelectedMonth(Context context, int i)
    {
        Calendar calendar = Calendar.getInstance(Utils.getTimeZone(context));
        context = MonthViewWidgetState.loadState(context, i);
        calendar.clear();
        calendar.set(((MonthViewWidgetState) (context)).selectedYear, ((MonthViewWidgetState) (context)).selectedMonth, 1);
        return calendar;
    }

    public static Object getLeft(boolean flag, Object obj, Object obj1)
    {
        if (flag)
        {
            return obj1;
        } else
        {
            return obj;
        }
    }

    public static Object getRight(boolean flag, Object obj, Object obj1)
    {
        if (flag)
        {
            return obj;
        } else
        {
            return obj1;
        }
    }

    public static int[] getWidgetIds(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT < 24) goto _L2; else goto _L1
_L1:
        UserManager usermanager = (UserManager)context.getSystemService("user");
        if (usermanager == null || usermanager.isUserUnlocked()) goto _L2; else goto _L3
_L3:
        context = new int[0];
_L5:
        return context;
_L2:
        int ai[] = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, com/google/android/calendar/widgetmonth/MonthViewWidgetProvider));
        context = ai;
        if (ai == null)
        {
            return new int[0];
        }
        if (true) goto _L5; else goto _L4
_L4:
    }


    private class _cls1
        implements DayRangeGetter
    {

        public final int getEndDay(Object obj)
        {
            return ((TimelineSegment)obj).getEndDay();
        }

        public final int getStartDay(Object obj)
        {
            return ((TimelineSegment)obj).getStartDay();
        }

        public final boolean spansAtLeastOneDay(Object obj)
        {
            return ((TimelineSegment)obj).spansAtLeastOneDay();
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements DayRangeGetter
    {

        public final int getEndDay(Object obj)
        {
            return ((TimelineItem)obj).getEndDay();
        }

        public final int getStartDay(Object obj)
        {
            return ((TimelineItem)obj).getStartDay();
        }

        public final boolean spansAtLeastOneDay(Object obj)
        {
            return ((TimelineItem)obj).spansAtLeastOneDay();
        }

        _cls2()
        {
        }
    }

}
