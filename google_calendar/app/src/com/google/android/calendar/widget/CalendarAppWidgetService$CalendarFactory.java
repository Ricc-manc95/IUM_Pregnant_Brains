// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.app.AlarmManager;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.RemoteViews;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.LaunchInfoActivityUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import java.util.List;

// Referenced classes of package com.google.android.calendar.widget:
//            CalendarAppWidgetService, CalendarAppWidgetProvider

public static final class appWidgetId
    implements android.widget.ice.CalendarFactory
{

    private int appWidgetId;
    private final Context context;
    private long lastWidgetDataChangedNotification;
    private List rowList;
    private appWidgetId viewContext;

    private final void notifyWidgetDataChanged()
    {
        long l = SystemClock.elapsedRealtime();
        if (l - lastWidgetDataChangedNotification > 1000L)
        {
            CalendarAppWidgetProvider.notifyWidgetDataChanged(context, AppWidgetManager.getInstance(context), appWidgetId);
            lastWidgetDataChangedNotification = l;
            return;
        } else
        {
            LogUtils.d("CalendarWidget", "Data changed notification dropped, sending them too frequently.", new Object[0]);
            return;
        }
    }

    public final int getCount()
    {
        if (rowList == null)
        {
            LogUtils.d("CalendarWidget", "CalendarFactory.getCount:mRowList == null", new Object[0]);
            return 1;
        } else
        {
            return rowList.size();
        }
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final RemoteViews getLoadingView()
    {
        String s;
        int i;
        if (AndroidPermissionUtils.hasMandatoryPermissions(context) && Utils.isCalendarStorageEnabled(context))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        s = context.getPackageName();
        if (i != 0)
        {
            i = 0x7f050194;
        } else
        {
            i = 0x7f050199;
        }
        return new RemoteViews(s, i);
    }

    public final RemoteViews getViewAt(int i)
    {
        if (!AndroidPermissionUtils.hasMandatoryPermissions(context) || !Utils.isCalendarStorageEnabled(context))
        {
            if (i > 0)
            {
                LogUtils.v("CalendarWidget", "getViewAt(%s) without access to calendar", new Object[] {
                    Integer.valueOf(i)
                });
                notifyWidgetDataChanged();
            }
            return getLoadingView();
        }
        if (rowList == null)
        {
            LogUtils.v("CalendarWidget", "getViewAt:%d at %d mRowList == null", new Object[] {
                Integer.valueOf(appWidgetId), Integer.valueOf(i)
            });
            RemoteViews remoteviews = getLoadingView();
            remoteviews.setOnClickFillInIntent(0x7f1003a8, LaunchInfoActivityUtils.getLaunchFillInIntent(context, null));
            return remoteviews;
        }
        if (i < 0 || rowList.size() <= i)
        {
            LogUtils.v("CalendarWidget", "getViewAt:%d at %d out of range request", new Object[] {
                Integer.valueOf(appWidgetId), Integer.valueOf(i)
            });
            notifyWidgetDataChanged();
            return getLoadingView();
        }
        Object obj = (getLoadingView)rowList.get(i);
        RemoteViews remoteviews1 = new RemoteViews(context.getPackageName(), ((context) (obj)).context(viewContext));
        if (obj instanceof viewContext)
        {
            obj = (viewContext)obj;
            if (remoteviews1.getLayoutId() == 0x7f05019c)
            {
                remoteviews1.setViewVisibility(0x7f1003ab, 0);
                remoteviews1.setTextViewText(0x7f1003ab, ((viewContext) (obj)).viewContext);
                remoteviews1.setContentDescription(0x7f1003ab, ((viewContext) (obj)).viewContext);
            }
            return remoteviews1;
        }
        if (obj instanceof viewContext)
        {
            obj = (viewContext)obj;
            if (remoteviews1.getLayoutId() != 0x7f05018f)
            {
                remoteviews1.setTextViewText(0x7f10019a, ((viewContext) (obj)).viewContext);
            }
            return remoteviews1;
        }
        if (obj instanceof viewContext)
        {
            obj = (viewContext)obj;
            ((viewContext) (obj)).viewContext(viewContext, remoteviews1);
            ((viewContext) (obj)).viewContext(context, remoteviews1);
            return remoteviews1;
        }
        if (obj instanceof context)
        {
            ((context) (obj)).context(viewContext, remoteviews1);
            return remoteviews1;
        }
        if (obj instanceof viewContext)
        {
            ((viewContext) (obj)).viewContext(viewContext, remoteviews1);
            remoteviews1.setOnClickFillInIntent(0x7f1003a9, LaunchInfoActivityUtils.getLaunchFillInIntent(context, null));
            return remoteviews1;
        } else
        {
            return remoteviews1;
        }
    }

    public final int getViewTypeCount()
    {
        return 23;
    }

    public final boolean hasStableIds()
    {
        return false;
    }

    public final void onCreate()
    {
    }

    public final void onDataSetChanged()
    {
        boolean flag;
        flag = true;
        LogUtils.d("CalendarWidget", "CalendarFactory.onDataSetChanged:%d", new Object[] {
            Integer.valueOf(appWidgetId)
        });
        LogUtils.v("CalendarWidget", "Cleaning up lifeboat for %s", new Object[] {
            Integer.valueOf(appWidgetId)
        });
        ((AlarmManager)context.getSystemService("alarm")).cancel(CalendarAppWidgetProvider.createWidgetLifeboatPendingIntent(context, appWidgetId));
        if (!AndroidPermissionUtils.hasMandatoryPermissions(context) || !Utils.isCalendarStorageEnabled(context))
        {
            LogUtils.v("CalendarWidget", "No calendar access in onDataSetChanged - bailing out", new Object[0]);
            rowList = null;
            return;
        }
        if (rowList != null)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        Object obj = CalendarAppWidgetService.rowListLock;
        obj;
        JVM INSTR monitorenter ;
        CalendarAppWidgetService.rowListLock.wait(2000L);
_L1:
        rowList = CalendarAppWidgetService.rowList;
        obj = context;
        Object obj1 = context;
        int j = appWidgetId;
        obj1 = AppWidgetManager.getInstance(((Context) (obj1)));
        int i = ((flag) ? 1 : 0);
        if (obj1 != null)
        {
            obj1 = ((AppWidgetManager) (obj1)).getAppWidgetOptions(j);
            Object obj2;
            if (obj1 != null)
            {
                i = ((flag) ? 1 : 0);
                if (180 < ((Bundle) (obj1)).getInt("appWidgetMinWidth"))
                {
                    i = 0;
                }
            } else
            {
                i = 1;
            }
        }
        viewContext = new viewContext(((Context) (obj)), i);
        return;
        obj2;
        LogUtils.i("CalendarWidget", ((Throwable) (obj2)), "Waiting for WidgetDataReceiver - Interrupted", new Object[0]);
          goto _L1
        obj2;
        obj;
        JVM INSTR monitorexit ;
        throw obj2;
    }

    public final void onDestroy()
    {
        LogUtils.d("CalendarWidget", "CalendarFactory.onDestroy:%d", new Object[] {
            Integer.valueOf(appWidgetId)
        });
    }

    Q(Context context1, Intent intent)
    {
        lastWidgetDataChangedNotification = 0L;
        context = context1;
        appWidgetId = intent.getIntExtra("appWidgetId", 0);
        rowList = CalendarAppWidgetService.rowList;
        LogUtils.v("CalendarWidget", "CalendarFactory construct %d", new Object[] {
            Integer.valueOf(appWidgetId)
        });
    }
}
