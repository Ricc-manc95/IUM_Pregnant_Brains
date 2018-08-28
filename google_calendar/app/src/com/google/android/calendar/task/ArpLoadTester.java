// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.timely.MonthData;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDataManager, ArpTaskAccount, TaskConnection, TaskAccount

final class ArpLoadTester
    implements TaskAccount.TaskAccountListener
{

    private static final String accountNames[] = new String[0];
    private final ArpTaskDataManager arpTaskDataManager;
    private final Context context;
    public final LatencyLogger latencyLogger = LatencyLoggerHolder.get();
    public boolean shouldProfile;
    private final TaskConnection taskConnection;
    public final MonthData todayMonthData;

    public ArpLoadTester(Context context1, TaskConnection taskconnection, MonthData monthdata, ArpTaskDataManager arptaskdatamanager)
    {
        context = context1;
        taskConnection = taskconnection;
        todayMonthData = monthdata;
        arpTaskDataManager = arptaskdatamanager;
        shouldProfile = false;
    }

    public static void logLoadEnd(String s, int i, int j)
    {
        LogUtils.v("ArpLoadTester", "nShownTasks: %d", new Object[] {
            Integer.valueOf(i)
        });
        LogUtils.v("ArpLoadTester", "nFilteredTasks: %d", new Object[] {
            Integer.valueOf(j)
        });
        LogUtils.v("ArpLoadTester", "Done account: %s", new Object[] {
            s
        });
    }

    public final void onTaskAccountChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHGN6QPFAHGN6QQ1CDHMUTBEEGTIILG_0()
    {
    }

    public final void onTaskAccountLoaded(TaskAccount taskaccount, Integer integer)
    {
        if (!shouldProfile)
        {
            return;
        } else
        {
            arpTaskDataManager.onTaskAccountLoaded(taskaccount, integer);
            latencyLogger.markAt(45);
            shouldProfile = false;
            ((ArpTaskAccount)taskaccount).shouldProfile = false;
            return;
        }
    }

}
