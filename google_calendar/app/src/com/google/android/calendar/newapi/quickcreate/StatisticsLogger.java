// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import android.content.Context;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class StatisticsLogger
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/quickcreate/StatisticsLogger);

    StatisticsLogger()
    {
    }

    static final Long lambda$average$0$StatisticsLogger(Long long1, Long long2)
    {
        return Long.valueOf(long1.longValue() + long2.longValue());
    }

    static void logStatistics(Context context, List list)
    {
        if (!list.isEmpty())
        {
            ArrayList arraylist = new ArrayList(list);
            Collections.sort(arraylist);
            int i = arraylist.size();
            long l = ((Long)arraylist.get(arraylist.size() / 2)).longValue();
            list = Long.valueOf(0L);
            class .Lambda._cls0
                implements com.google.android.calendar.utils.collection.Iterables2.Folder
            {

                public static final com.google.android.calendar.utils.collection.Iterables2.Folder $instance = new .Lambda._cls0();

                public final Object onFold(Object obj, Object obj1)
                {
                    return StatisticsLogger.lambda$average$0$StatisticsLogger((Long)obj, (Long)obj1);
                }


            private .Lambda._cls0()
            {
            }
            }

            com.google.android.calendar.utils.collection.Iterables2.Folder folder = .Lambda._cls0..instance;
            for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
            {
                list = ((List) (folder.onFold(iterator.next(), list)));
            }

            long l1 = ((Long)list).longValue() / (long)arraylist.size();
            long l2 = ((Long)arraylist.get(arraylist.size() - 1)).longValue();
            list = AnalyticsLoggerHolder.instance;
            if (list == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            }
            list = (AnalyticsLogger)list;
            list.trackTiming(context, "latency", l, "QuickCreateMedianRequestDuration", null);
            list.trackTiming(context, "task_assist", l1, "quick_create_suggest_latency", "average");
            list.trackTiming(context, "task_assist", l2, "quick_create_suggest_latency", "max");
            context = TAG;
            boolean flag;
            if (LogUtils.maxEnabledLogLevel > 4)
            {
                flag = false;
            } else
            if (Log.isLoggable(context, 4))
            {
                flag = true;
            } else
            {
                flag = Log.isLoggable(context, 4);
            }
            if (flag)
            {
                LogUtils.i(TAG, "Request statistics: %d requests, median: %dms, avg: %dms, max: %dms", new Object[] {
                    Integer.valueOf(i), Long.valueOf(l), Long.valueOf(l1), Long.valueOf(l2)
                });
                return;
            }
        }
    }

}
