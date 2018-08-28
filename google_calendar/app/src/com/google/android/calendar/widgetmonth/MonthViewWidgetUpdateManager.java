// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.Queue;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetUpdatesQueue

final class MonthViewWidgetUpdateManager
{

    public static MonthViewWidgetUpdateManager instance;
    public static long lastUpdateTimeMs;
    public final Context context;
    public final Handler mainHandler = new Handler(Looper.getMainLooper());
    public final Runnable updatesApplier = new _cls1();
    public final SparseArray updatesForAppWidgetId = new SparseArray();

    MonthViewWidgetUpdateManager(Context context1)
    {
        context = context1.getApplicationContext();
    }

    final void applyUpdateIfAny()
    {
        boolean flag;
        if (updatesForAppWidgetId.size() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            int i = updatesForAppWidgetId.keyAt(0);
            MonthViewWidgetUpdatesQueue monthviewwidgetupdatesqueue = (MonthViewWidgetUpdatesQueue)updatesForAppWidgetId.valueAt(0);
            Context context1 = context;
            if (monthviewwidgetupdatesqueue.baseUpdate != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                AppWidgetManager.getInstance(context1).updateAppWidget(i, monthviewwidgetupdatesqueue.baseUpdate);
                monthviewwidgetupdatesqueue.baseUpdate = null;
            } else
            {
                AppWidgetManager.getInstance(context1).partiallyUpdateAppWidget(i, (RemoteViews)monthviewwidgetupdatesqueue.partialUpdates.remove());
            }
            lastUpdateTimeMs = SystemClock.elapsedRealtime();
            if (monthviewwidgetupdatesqueue.baseUpdate == null && monthviewwidgetupdatesqueue.partialUpdates.isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                updatesForAppWidgetId.removeAt(0);
            }
        }
        if (updatesForAppWidgetId.size() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mainHandler.postDelayed(updatesApplier, 100);
            return;
        } else
        {
            instance = null;
            return;
        }
    }

    private class _cls1
        implements Runnable
    {

        private final MonthViewWidgetUpdateManager this$0;

        public final void run()
        {
            if (MonthViewWidgetUpdateManager.instance == MonthViewWidgetUpdateManager.this)
            {
                applyUpdateIfAny();
            }
        }

        _cls1()
        {
            this$0 = MonthViewWidgetUpdateManager.this;
            super();
        }
    }

}
