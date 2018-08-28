// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.Utils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.task.ArpTaskDataManager;
import com.google.android.calendar.task.BaseTaskDataManager;
import com.google.android.calendar.task.TaskDataFactory;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            MonthData, EventRangedQueryHandler, AutoValue_EventRangedQueryHandler_QueryConfig

public class DataFactory extends Fragment
{
    public static interface OnAllEventsDataReadyListener
    {

        public abstract void onAllEventsDataReady();
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/DataFactory);
    private Integer currentDataInd;
    public final MonthData dataToday = new MonthData(true);
    private final MonthData datas[] = new MonthData[25];
    public EventRangedQueryHandler eventQueryHandler;
    private BaseTaskDataManager taskDataManager;
    private int timePassageDirection;

    public DataFactory()
    {
        currentDataInd = Integer.valueOf(0);
    }

    private final MonthData getFurthestData(int i)
    {
        Object obj = datas;
        obj;
        JVM INSTR monitorenter ;
        MonthData amonthdata[];
        MonthData monthdata;
        if (currentDataInd.intValue() >= 25)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        amonthdata = datas;
        i = currentDataInd.intValue();
        monthdata = new MonthData(false);
        amonthdata[i] = monthdata;
        currentDataInd = Integer.valueOf(currentDataInd.intValue() + 1);
        obj;
        JVM INSTR monitorexit ;
        return monthdata;
        obj;
        JVM INSTR monitorexit ;
        Object obj1;
        MonthData amonthdata1[] = datas;
        int j1 = amonthdata1.length;
        int l = 0;
        int k = 0;
        obj1 = null;
        while (l < j1) 
        {
            MonthData monthdata1 = amonthdata1[l];
            int j = monthdata1.startDay - i;
            if (timePassageDirection == 0)
            {
                j = Math.abs(j);
                int i1;
                boolean flag;
                if (j > k)
                {
                    obj = monthdata1;
                } else
                {
                    j = k;
                    obj = obj1;
                }
            } else
            {
                i1 = j * timePassageDirection;
                if (i1 < 0)
                {
                    j = k;
                    obj = obj1;
                    if (i1 < k)
                    {
                        j = i1;
                        obj = monthdata1;
                    }
                } else
                {
                    j = k;
                    obj = obj1;
                    if (k >= 0)
                    {
                        j = k;
                        obj = obj1;
                        if (i1 > k)
                        {
                            j = i1;
                            obj = monthdata1;
                        }
                    }
                }
            }
            l++;
            k = j;
            obj1 = obj;
        }
        break MISSING_BLOCK_LABEL_227;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        if (obj1 == null)
        {
            LogUtils.wtf(TAG, "Somehow, found no data for julian day %d", new Object[] {
                Integer.valueOf(i)
            });
            return null;
        }
        obj = TAG;
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable(((String) (obj)), 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(((String) (obj)), 3);
        }
        if (flag)
        {
            j = ((MonthData) (obj1)).startDay;
            LogUtils.d(TAG, "data was recycled for %d [%s] from %d with data[%s]", new Object[] {
                Integer.valueOf(i), Utils.julianToDebugString(i), Integer.valueOf(j), ((MonthData) (obj1)).getDebugTag()
            });
            return ((MonthData) (obj1));
        } else
        {
            return ((MonthData) (obj1));
        }
    }

    public static int getNumData()
    {
        return 25;
    }

    private final void refreshDataToday(boolean flag)
    {
        boolean flag2;
        flag2 = false;
        Trace.beginSection("DataFactory.refreshDataToday");
        if (flag) goto _L2; else goto _L1
_L1:
        MonthData monthdata = dataToday;
        if (!monthdata.eventsReady) goto _L4; else goto _L3
_L3:
        boolean flag1;
        if (monthdata.currentMonth && !monthdata.tasksReady)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L4; else goto _L5
_L5:
        flag1 = true;
_L7:
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_100;
        }
_L2:
        flag1 = flag2;
        if (dataToday.numDays != 0)
        {
            flag1 = true;
        }
        if (!flag1)
        {
            throw new IllegalStateException();
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag1 = false;
        if (true) goto _L7; else goto _L6
_L6:
        getData(dataToday.startDay, true);
        Trace.endSection();
        return;
    }

    public final MonthData getData(int i, boolean flag)
    {
        Trace.beginSection("DataFactory.getData");
        MonthData monthdata1 = getDataAllowNull(i);
        if (monthdata1 != null) goto _L2; else goto _L1
_L1:
        MonthData monthdata = getFurthestData(i);
_L4:
        eventQueryHandler.refreshData(monthdata, i);
        taskDataManager.includeData(i);
        Trace.endSection();
        return monthdata;
_L2:
        monthdata = monthdata1;
        if (flag) goto _L4; else goto _L3
_L3:
        Trace.endSection();
        return monthdata1;
        Exception exception;
        exception;
        Trace.endSection();
        throw exception;
    }

    public final MonthData getDataAllowNull(int i)
    {
        MonthData monthdata = dataToday;
        if (i < monthdata.startDay) goto _L2; else goto _L1
_L1:
        int j = monthdata.startDay;
        if (i > (monthdata.numDays + j) - 1) goto _L2; else goto _L3
_L3:
        j = 1;
_L12:
        if (!j) goto _L5; else goto _L4
_L4:
        monthdata = dataToday;
_L9:
        return monthdata;
_L2:
        j = 0;
        continue; /* Loop/switch isn't completed */
_L5:
        MonthData amonthdata[];
        int k;
        amonthdata = datas;
        k = amonthdata.length;
        j = 0;
_L10:
        if (j >= k) goto _L7; else goto _L6
_L6:
        monthdata = amonthdata[j];
        if (monthdata == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag;
        if (i >= monthdata.startDay && i <= (monthdata.startDay + monthdata.numDays) - 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L9; else goto _L8
_L8:
        j++;
          goto _L10
_L7:
        return null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void initialize(Context context)
    {
        Context context1 = context.getApplicationContext();
        if (eventQueryHandler == null)
        {
            eventQueryHandler = new _cls1(context1, true);
        }
        LatencyLoggerHolder.get().markAt(13);
        boolean flag = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_hide_declined", false);
        eventQueryHandler.setHideDeclinedEvents(flag);
    }

    public final void onCreate(Bundle bundle)
    {
        Object obj = null;
        super.onCreate(bundle);
        super.mRetainInstance = true;
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        TaskDataFactory taskdatafactory = TaskDataFactory.instance;
        MonthData monthdata;
        MonthData amonthdata[];
        boolean flag;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        monthdata = dataToday;
        amonthdata = datas;
        taskdatafactory.currentTaskDataManager = new ArpTaskDataManager(bundle, taskdatafactory.getTaskConnection(), monthdata, amonthdata);
        taskDataManager = taskdatafactory.currentTaskDataManager;
        LatencyLoggerHolder.get().markAt(14);
        if (eventQueryHandler != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (super.mHost == null)
            {
                bundle = obj;
            } else
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            initialize(bundle);
        }
        updateToday();
    }

    public final void onDestroy()
    {
        EventRangedQueryHandler eventrangedqueryhandler;
label0:
        {
            super.onDestroy();
            if (taskDataManager != null)
            {
                taskDataManager.onDestroy();
            }
            if (eventQueryHandler != null)
            {
                eventrangedqueryhandler = eventQueryHandler;
                if (eventrangedqueryhandler.calendarListSubscription != null)
                {
                    break label0;
                }
                LogUtils.e("MonthQueryHandler", "not observing calendar list", new Object[0]);
            }
            return;
        }
        eventrangedqueryhandler.calendarListSubscription.cancel(false);
    }

    public final void refreshDataAroundDay(int i, boolean flag, boolean flag1)
    {
        Trace.beginSection("refreshDataAroundDay");
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        LogUtils.d(TAG, "refreshDataAroundDay - refreshExisting", new Object[0]);
        int j;
        int k;
        int l;
        taskDataManager.refreshStart();
        refreshDataToday(flag);
        MonthData monthdata = getData(i, flag);
        i = monthdata.startDay;
        j = monthdata.startDay;
        monthdata = getData(((monthdata.numDays + j) - 1) + 1, flag);
        k = monthdata.startDay;
        l = monthdata.numDays;
        j = getData(i - 1, flag).startDay;
        if (!flag1) goto _L2; else goto _L1
_L1:
        k = (l + k) - 1;
        i = 2;
_L8:
        if (i >= 24) goto _L2; else goto _L3
_L3:
        if (i % 2 != 0) goto _L5; else goto _L4
_L4:
        MonthData monthdata1 = getData(k + 1, flag);
        k = monthdata1.startDay;
        k = (monthdata1.numDays + k) - 1;
          goto _L6
_L5:
        j = getData(j - 1, flag).startDay;
          goto _L6
_L2:
        taskDataManager.refreshComplete();
        Trace.endSection();
        return;
        Exception exception;
        exception;
        Trace.endSection();
        throw exception;
_L6:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void registerOnAllEventsDataReadyListener(OnAllEventsDataReadyListener onalleventsdatareadylistener)
    {
        EventRangedQueryHandler eventrangedqueryhandler = eventQueryHandler;
        if (eventrangedqueryhandler.allDataReadyListeners == null)
        {
            eventrangedqueryhandler.allDataReadyListeners = new ArrayList();
        }
        eventrangedqueryhandler.allDataReadyListeners.add(onalleventsdatareadylistener);
    }

    public final void setHideDeclinedEvents(Context context)
    {
        boolean flag = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_hide_declined", false);
        EventRangedQueryHandler eventrangedqueryhandler = eventQueryHandler;
        synchronized (eventrangedqueryhandler.queriesQueue)
        {
            eventrangedqueryhandler.queryConfig = new AutoValue_EventRangedQueryHandler_QueryConfig(flag, eventrangedqueryhandler.queryConfig.getVisibleSyncedCalendarIds());
        }
        return;
        exception;
        context;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void setTimePassage(int i, int j)
    {
        timePassageDirection = i;
        EventRangedQueryHandler eventrangedqueryhandler = eventQueryHandler;
        eventrangedqueryhandler.queryVelocity = i;
        eventrangedqueryhandler.focusDay = j;
        LogUtils.d("MonthQueryHandler", "setQueryVelocity: %d focusDay: %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
    }

    public final void updateToday()
    {
        boolean flag;
        Trace.beginSection("DataFactory.updateToday");
        Object obj;
        int i;
        int j;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        j = Utils.getTodayJulianDay(((Context) (obj)));
        if (dataToday.numDays == 0) goto _L2; else goto _L1
_L1:
        obj = dataToday;
        if (j < ((MonthData) (obj)).startDay) goto _L4; else goto _L3
_L3:
        i = ((MonthData) (obj)).startDay;
        if (j > (((MonthData) (obj)).numDays + i) - 1) goto _L4; else goto _L5
_L5:
        flag = true;
_L8:
        if (flag) goto _L6; else goto _L2
_L2:
        dataToday.recycle(j);
        refreshDataToday(true);
_L6:
        taskDataManager.updateToday();
        Trace.endSection();
        return;
_L4:
        flag = false;
        if (true) goto _L8; else goto _L7
_L7:
    }


    private class _cls1 extends EventRangedQueryHandler
    {

        protected final RangedData.EventResults createStorage(int i)
        {
            return new MonthData.MonthEventResults(i);
        }

        protected final void processResults(RangedData rangeddata, RangedData.EventResults eventresults)
        {
            RangedData.EventResults eventresults1 = eventresults;
            if (eventresults == null)
            {
                eventresults1 = createStorage(0);
            }
            if (rangeddata instanceof MonthData)
            {
                ((MonthData)rangeddata).addEvents(eventresults1);
            }
        }

        _cls1(Context context, boolean flag)
        {
            super(context, true);
        }
    }

}
