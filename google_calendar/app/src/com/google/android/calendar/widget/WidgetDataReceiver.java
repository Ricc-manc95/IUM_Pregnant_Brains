// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.calendar.widget.CalendarAppWidgetProvider;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.EventRangedQueryHandler;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetRow, CalendarAppWidgetProvider, CalendarAppWidgetService, WidgetDataMachine, 
//            WidgetLoaderManager, CalendarAppWidgetModel, WidgetEventLoader

public class WidgetDataReceiver extends BroadcastReceiver
{

    public Context context;
    public String timezone;
    private WidgetLoaderManager widgetLoaderManager;

    public WidgetDataReceiver()
    {
        LogUtils.d("CalendarWidget", "WidgetDataReceiver construct", new Object[0]);
    }

    static void addAll(List list, int i, Time time, List list1, boolean flag, int j, DateTimeFormatHelper datetimeformathelper)
    {
        int k = 1;
        if (flag)
        {
            WidgetRow.Chip chip = WidgetRow.createChip((TimelineItem)list1.get(0), i, datetimeformathelper);
            chip.isFirst = true;
            chip.dayInfo = new WidgetRow.DayInfo(j, i, time);
            list.add(chip);
            for (; k < list1.size(); k++)
            {
                WidgetRow.Chip chip1 = WidgetRow.createChip((TimelineItem)list1.get(k), i, datetimeformathelper);
                chip1.dayInfo = new WidgetRow.DayInfo(j, i, time);
                list.add(chip1);
            }

        } else
        {
            WidgetRow.Chip chip2;
            for (list1 = list1.iterator(); list1.hasNext(); list.add(chip2))
            {
                chip2 = WidgetRow.createChip((TimelineItem)list1.next(), i, datetimeformathelper);
                chip2.dayInfo = new WidgetRow.DayInfo(j, i, time);
            }

        }
    }

    static long getNextMidnightTimeMillis(long l, String s)
    {
        Time time = new Time();
        time.set(l);
        time.monthDay = time.monthDay + 1;
        time.hour = 0;
        time.minute = 0;
        time.second = 0;
        time.writeFieldsToImpl();
        long l1 = time.impl.normalize(true);
        time.copyFieldsFromImpl();
        time.timezone = s;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        time.set(l);
        time.monthDay = time.monthDay + 1;
        time.hour = 0;
        time.minute = 0;
        time.second = 0;
        time.writeFieldsToImpl();
        l = time.impl.normalize(true);
        time.copyFieldsFromImpl();
        return Math.min(l1, l);
    }

    final void notifyWidgetsChanged()
    {
        int i = 0;
        AppWidgetManager appwidgetmanager = AppWidgetManager.getInstance(context);
        int ai[] = appwidgetmanager.getAppWidgetIds(new ComponentName(context, com/android/calendar/widget/CalendarAppWidgetProvider));
        boolean flag;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable("CalendarWidget", 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable("CalendarWidget", 3);
        }
        if (flag)
        {
            LogUtils.d("CalendarWidget", "... notifyAppWidgetViewDataChanged -> %s", new Object[] {
                Arrays.toString(ai)
            });
        }
        for (int j = ai.length; i < j; i++)
        {
            int k = ai[i];
            CalendarAppWidgetProvider.notifyWidgetDataChanged(context, appwidgetmanager, k);
        }

    }

    public void onReceive(Context context1, final Intent result)
    {
        context = context1.getApplicationContext();
        LogUtils.d("CalendarWidget", "Received intent: %s", new Object[] {
            result
        });
        if (!AndroidPermissionUtils.hasMandatoryPermissions(context1) || !Utils.isCalendarStorageEnabled(context1))
        {
            notifyWidgetsChanged();
            LogUtils.d("CalendarWidget", "Exiting. Calendar permissions not granted or storage disabled.", new Object[0]);
            return;
        } else
        {
            widgetLoaderManager = CalendarAppWidgetService.getLoaderManager(context);
            context1 = context;
            result = goAsync();
            LogUtils.v("CalendarWidget", "createRefreshDataRunnable when:%d", new Object[] {
                Integer.valueOf(CalendarAppWidgetService.currentVersion.get())
            });
            Utils.loadTimeZone(context1, new _cls1());
            return;
        }
    }

    final void startRefreshData(final android.content.BroadcastReceiver.PendingResult result)
    {
        final AtomicBoolean isResultFinished;
        WidgetDataMachine widgetdatamachine;
        WidgetEventLoader widgeteventloader;
        int i;
        boolean flag;
        if (result == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isResultFinished = new AtomicBoolean(flag);
        widgetdatamachine = new WidgetDataMachine(context, timezone, new _cls2());
        widgeteventloader = widgetLoaderManager.widgetEventLoader;
        widgeteventloader.setHideDeclinedEvents(Utils.getHideDeclinedEvents(context));
        i = ((CalendarAppWidgetModel) (widgetdatamachine)).maxJulianDay;
        widgeteventloader.eventsProcessor = widgetdatamachine;
        widgeteventloader.refreshData(widgetdatamachine, i);
        if (((CalendarAppWidgetModel) (widgetdatamachine)).eventToken < 0)
        {
            LogUtils.v("CalendarWidget", "Query dropped when:%d", new Object[] {
                Integer.valueOf(CalendarAppWidgetService.currentVersion.get())
            });
            if (!isResultFinished.getAndSet(true))
            {
                result.finish();
            }
            return;
        } else
        {
            LogUtils.d("CalendarWidget", "started refresh data for %d when:%d", new Object[] {
                Integer.valueOf(((CalendarAppWidgetModel) (widgetdatamachine)).eventToken), Integer.valueOf(CalendarAppWidgetService.currentVersion.incrementAndGet())
            });
            return;
        }
    }

    private class _cls1
        implements Runnable
    {

        private final WidgetDataReceiver this$0;
        private final android.content.BroadcastReceiver.PendingResult val$result;

        public final void run()
        {
            LogUtils.d("CalendarWidget", "running refresh runnable when:%d", new Object[] {
                Integer.valueOf(CalendarAppWidgetService.currentVersion.get())
            });
            timezone = Utils.getTimeZoneId(context);
            startRefreshData(result);
        }

        _cls1()
        {
            this$0 = WidgetDataReceiver.this;
            result = pendingresult;
            super();
        }
    }


    private class _cls2
        implements WidgetDataMachine.OnProcessCompleteListener
    {

        private final WidgetDataReceiver this$0;
        private final AtomicBoolean val$isResultFinished;
        private final android.content.BroadcastReceiver.PendingResult val$result;

        public final void onProcessComplete(CalendarAppWidgetModel calendarappwidgetmodel)
        {
            Object obj1;
            Object obj2;
            WidgetDataReceiver widgetdatareceiver;
            int i;
            int i1;
label0:
            {
                CalendarAppWidgetModel.RangedEventResults rangedeventresults = calendarappwidgetmodel.getStorage();
                long l1;
                if (rangedeventresults != null)
                {
                    i = rangedeventresults.allDayBuckets.size();
                    i = rangedeventresults.timedBuckets.size() + i;
                } else
                {
                    i = 0;
                }
                obj = new ArrayList(i + 15);
                widgetdatareceiver = WidgetDataReceiver.this;
                LogUtils.d("CalendarWidget", "... fillRows from ....", new Object[0]);
                obj1 = new Time(widgetdatareceiver.timezone);
                if (Clock.mockedTimestamp > 0L)
                {
                    l1 = Clock.mockedTimestamp;
                } else
                {
                    l1 = System.currentTimeMillis();
                }
                ((Time) (obj1)).set(l1);
                i1 = calendarappwidgetmodel.todayJulianDay;
                obj2 = DateTimeFormatHelper.instance;
                if (obj2 == null)
                {
                    throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
                }
                obj2 = (DateTimeFormatHelper)obj2;
                if (calendarappwidgetmodel.rangedEventResults != null)
                {
                    CalendarAppWidgetModel.RangedEventResults rangedeventresults1 = calendarappwidgetmodel.rangedEventResults;
                    long l4;
                    if (rangedeventresults1.allDayBuckets.size() == 0 && rangedeventresults1.timedBuckets.size() == 0)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        break MISSING_BLOCK_LABEL_430;
                    }
                }
                i = 1;
                break label0;
            }
            i = 0;
            continue;
            calendarappwidgetmodel;
            obj;
            JVM INSTR monitorexit ;
            throw calendarappwidgetmodel;
        }

        _cls2()
        {
            this$0 = WidgetDataReceiver.this;
            isResultFinished = atomicboolean;
            result = pendingresult;
            super();
        }
    }

}
