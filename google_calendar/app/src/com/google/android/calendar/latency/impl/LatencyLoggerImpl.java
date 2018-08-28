// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency.impl;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.latency.LatencyLogger;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.latency.impl:
//            PerformanceSpan, PerformanceMark, IntraSpans

public final class LatencyLoggerImpl
    implements LatencyLogger
{

    private IntraSpans coldStart;
    private PerformanceSpan coldStartAllEvents;
    private PerformanceSpan coldStartAllTasks;
    private PerformanceSpan coldStartShortTasks;
    private Map grooveCreation;
    private Map grooveDefer;
    private PerformanceSpan notificationAppStart;
    private PerformanceSpan openEventView;
    private IntraSpans saveEvent;
    private PerformanceSpan taskLoad;
    private PerformanceSpan taskProcessing;
    private IntraSpans toggleCalendar;

    public LatencyLoggerImpl(Context context)
    {
        grooveCreation = new HashMap();
        grooveDefer = new HashMap();
        SparseArray sparsearray = new SparseArray();
        sparsearray.append(0, "AllDataReady");
        sparsearray.append(1, "AppCreated");
        sparsearray.append(2, "CanOpenEvent");
        sparsearray.append(3, "ClickEventOpen");
        sparsearray.append(4, "ClickSaveEvent");
        sparsearray.append(5, "ClickToggleCalendar");
        sparsearray.append(6, "EventLoadBegin");
        sparsearray.append(7, "EventLoadFinished");
        sparsearray.append(8, "EventsChanged");
        sparsearray.append(9, "EventViewUpdated");
        sparsearray.append(32, "EventOpenAnimationStart");
        sparsearray.append(31, "EventOpenAnimationFinished");
        sparsearray.append(33, "EventCreateContentViewStart");
        sparsearray.append(34, "EventCreateContentViewEnd");
        sparsearray.append(12, "MainActivityCreated");
        sparsearray.append(13, "DataFactoryInitialized");
        sparsearray.append(14, "DataFactoryCreated");
        sparsearray.append(10, "MonthQueryBegin");
        sparsearray.append(11, "MonthQueryFinished");
        sparsearray.append(15, "MonthQueryDropped");
        sparsearray.append(46, "TimelineQueryBegin");
        sparsearray.append(47, "TimelineQueryEnd");
        sparsearray.append(18, "GrooveCreateBegin");
        sparsearray.append(19, "RequestSyncStart");
        sparsearray.append(20, "RequestSyncEnd");
        sparsearray.append(21, "ForceSyncScheduled");
        sparsearray.append(22, "ForceSyncStart");
        sparsearray.append(23, "ForceSyncEnd");
        sparsearray.append(24, "GrooveCreateEnd");
        sparsearray.append(25, "GrooveDeferBegin");
        sparsearray.append(26, "UpsyncDeferRequestFinished");
        sparsearray.append(27, "DeferForceSyncScheduled");
        sparsearray.append(28, "DeferForceSyncFinished");
        sparsearray.append(29, "GrooveDeferEnd");
        sparsearray.append(16, "AllTasksReady");
        sparsearray.append(17, "ShortTasksReady");
        sparsearray.append(35, "TaskLoadBegin");
        sparsearray.append(36, "TaskLoadFinished");
        sparsearray.append(37, "TaskLoadRestart");
        sparsearray.append(38, "TaskProcessingStart");
        sparsearray.append(39, "TaskProcessingStorageInitialized");
        sparsearray.append(40, "TaskProcessingStartTimelineTasksCreated");
        sparsearray.append(41, "TaskProcessingStorageFinalized");
        sparsearray.append(42, "TaskProcessingUpdateMonthData");
        sparsearray.append(43, "TaskProcessingUpdatedSearch");
        sparsearray.append(44, "TaskProcessingUpdatedAnalytics");
        sparsearray.append(45, "TaskProcessingFinished");
        sparsearray.append(30, "NotificationAppStart");
        PerformanceSpan.context = context.getApplicationContext();
        PerformanceMark.markNames = sparsearray;
    }

    private static boolean isDevelopment()
    {
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            return false;
        }
        if (Log.isLoggable("LatencyLogger", 3))
        {
            return true;
        } else
        {
            return Log.isLoggable("LatencyLogger", 3);
        }
    }

    private static boolean isVerboseDevelopment()
    {
        if (LogUtils.maxEnabledLogLevel > 2)
        {
            return false;
        }
        if (Log.isLoggable("LatencyLogger", 2))
        {
            return true;
        } else
        {
            return Log.isLoggable("LatencyLogger", 2);
        }
    }

    private static void logMarkVerbose(String s, int i, int j, String s1)
    {
        if (s1 == null)
        {
            LogUtils.v("LatencyLogger", "  [dev] %s mark %s-%d", new Object[] {
                s, PerformanceMark.getMarkName(i), Integer.valueOf(j)
            });
            return;
        } else
        {
            LogUtils.v("LatencyLogger", "  [dev] %s mark %s-%d from %s", new Object[] {
                s, PerformanceMark.getMarkName(i), Integer.valueOf(j), s1
            });
            return;
        }
    }

    public final void markAt(int i)
    {
        markAt(i, ((String) (null)));
    }

    public final void markAt(int i, int j)
    {
        markAt(i, j, null);
    }

    public final void markAt(int i, int j, String s)
    {
        Object obj;
        Object obj1;
        obj1 = null;
        obj = null;
        i;
        JVM INSTR lookupswitch 5: default 56
    //                   10: 115
    //                   11: 254
    //                   15: 396
    //                   46: 497
    //                   47: 636;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        boolean flag;
        if (isVerboseDevelopment())
        {
            logMarkVerbose("unhandled", i, j, s);
        }
        flag = false;
_L12:
        if (!flag || !isVerboseDevelopment()) goto _L8; else goto _L7
_L7:
        if (s != null) goto _L10; else goto _L9
_L9:
        LogUtils.v("LatencyLogger", "[[dev]] mark %s-%d", new Object[] {
            PerformanceMark.getMarkName(i), Integer.valueOf(j)
        });
_L8:
        return;
_L2:
        boolean flag1;
        if (coldStart != null)
        {
            obj = coldStart.startSubSpanAt(i, j, null, s);
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj1 = obj;
        if (saveEvent != null)
        {
            obj1 = saveEvent.startSubSpanAt(i, j, ((PerformanceMark) (obj)), s);
            flag1 = true;
        }
        flag = flag1;
        if (toggleCalendar != null)
        {
            obj = toggleCalendar;
            if (((IntraSpans) (obj)).oneShots != null && ((IntraSpans) (obj)).oneShots.containsKey(Integer.valueOf(i)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag = flag1 | flag;
            toggleCalendar.startSubSpanAt(i, j, ((PerformanceMark) (obj1)), s);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (coldStart != null)
        {
            obj = coldStart.stopSubSpanAt(i, j, null, s, 10);
            flag1 = true;
        } else
        {
            flag1 = false;
            obj = null;
        }
        if (saveEvent != null)
        {
            saveEvent.endAt(i, ((PerformanceMark) (obj)), s);
            saveEvent = null;
            flag1 = true;
        }
        flag = flag1;
        if (toggleCalendar != null)
        {
            obj1 = toggleCalendar;
            if (((IntraSpans) (obj1)).oneShots != null && ((IntraSpans) (obj1)).oneShots.containsKey(Integer.valueOf(i)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            toggleCalendar.stopSubSpanAt(i, j, ((PerformanceMark) (obj)), s, 10);
            flag = flag1 | flag;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (coldStart != null)
        {
            coldStart.clearAt(10, 15);
            flag = true;
        } else
        {
            flag = false;
        }
        if (saveEvent != null)
        {
            saveEvent.clearAt(10, 15);
            flag = true;
        }
        flag1 = flag;
        if (toggleCalendar != null)
        {
            toggleCalendar.clearAt(10, 15);
            flag1 = true;
        }
        flag = flag1;
        if (!flag1)
        {
            flag = flag1;
            if (isVerboseDevelopment())
            {
                logMarkVerbose("unhandled", i, j, s);
                flag = flag1;
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (coldStart != null)
        {
            obj = coldStart.startSubSpanAt(i, j, null, s);
            flag1 = true;
        } else
        {
            flag1 = false;
            obj = obj1;
        }
        obj1 = obj;
        if (saveEvent != null)
        {
            obj1 = saveEvent.startSubSpanAt(i, j, ((PerformanceMark) (obj)), s);
            flag1 = true;
        }
        flag = flag1;
        if (toggleCalendar != null)
        {
            obj = toggleCalendar;
            if (((IntraSpans) (obj)).oneShots != null && ((IntraSpans) (obj)).oneShots.containsKey(Integer.valueOf(i)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag = flag1 | flag;
            toggleCalendar.startSubSpanAt(i, j, ((PerformanceMark) (obj1)), s);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (coldStart != null)
        {
            obj = coldStart.stopSubSpanAt(i, j, null, s, 46);
            flag1 = true;
        } else
        {
            flag1 = false;
            obj = null;
        }
        if (saveEvent != null)
        {
            saveEvent.endAt(i, ((PerformanceMark) (obj)), s);
            saveEvent = null;
            flag1 = true;
        }
        flag = flag1;
        if (toggleCalendar != null)
        {
            obj1 = toggleCalendar;
            if (((IntraSpans) (obj1)).oneShots != null && ((IntraSpans) (obj1)).oneShots.containsKey(Integer.valueOf(i)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            toggleCalendar.stopSubSpanAt(i, j, ((PerformanceMark) (obj)), s, 46);
            flag = flag1 | flag;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        LogUtils.v("LatencyLogger", "[[dev]] mark %s-%d from %s", new Object[] {
            PerformanceMark.getMarkName(i), Integer.valueOf(j), s
        });
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final void markAt(int i, String s)
    {
        i;
        JVM INSTR tableswitch 0 45: default 200
    //                   0 230
    //                   1 388
    //                   2 760
    //                   3 520
    //                   4 538
    //                   5 556
    //                   6 593
    //                   7 612
    //                   8 574
    //                   9 713
    //                   10 200
    //                   11 200
    //                   12 443
    //                   13 388
    //                   14 388
    //                   15 200
    //                   16 340
    //                   17 364
    //                   18 947
    //                   19 985
    //                   20 1012
    //                   21 1041
    //                   22 1068
    //                   23 1105
    //                   24 1134
    //                   25 1172
    //                   26 1210
    //                   27 1237
    //                   28 1264
    //                   29 1293
    //                   30 1331
    //                   31 652
    //                   32 633
    //                   33 673
    //                   34 692
    //                   35 804
    //                   36 822
    //                   37 200
    //                   38 846
    //                   39 864
    //                   40 904
    //                   41 883
    //                   42 904
    //                   43 904
    //                   44 904
    //                   45 923;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L1 _L1 _L12 _L3 _L3 _L1 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L1 _L34 _L35 _L36 _L37 _L36 _L36 _L36 _L38
_L1:
        if (!isVerboseDevelopment()) goto _L40; else goto _L39
_L39:
        if (s != null) goto _L42; else goto _L41
_L41:
        LogUtils.v("LatencyLogger", "  [dev] unhandled mark %s", new Object[] {
            PerformanceMark.getMarkName(i)
        });
_L40:
        return;
_L2:
        Object obj;
        Object obj1;
        if (coldStartAllEvents != null)
        {
            obj = coldStartAllEvents.endAt(i, null, s);
            coldStartAllEvents = null;
        } else
        {
            obj = null;
        }
        obj1 = obj;
        if (toggleCalendar != null)
        {
            obj1 = toggleCalendar.endAt(i, ((PerformanceMark) (obj)), s);
            toggleCalendar = null;
        }
        if (obj1 != null || !isDevelopment()) goto _L40; else goto _L43
_L43:
        if (s == null)
        {
            LogUtils.d("LatencyLogger", "[dev] mark %s", new Object[] {
                PerformanceMark.getMarkName(i)
            });
            return;
        } else
        {
            LogUtils.d("LatencyLogger", "[dev] mark %s from %s", new Object[] {
                PerformanceMark.getMarkName(i), s
            });
            return;
        }
_L13:
        if (coldStartAllTasks == null) goto _L40; else goto _L44
_L44:
        coldStartAllTasks.endAt(i, null, s);
        coldStartAllTasks = null;
        return;
_L14:
        if (coldStartShortTasks == null) goto _L40; else goto _L45
_L45:
        coldStartShortTasks.endAt(i, null, s);
        coldStartShortTasks = null;
        return;
_L3:
        if (!isDevelopment()) goto _L40; else goto _L46
_L46:
        if (s == null)
        {
            LogUtils.d("LatencyLogger", "[dev] mark %s", new Object[] {
                PerformanceMark.getMarkName(i)
            });
            return;
        } else
        {
            LogUtils.d("LatencyLogger", "[dev] mark %s from %s", new Object[] {
                PerformanceMark.getMarkName(i), s
            });
            return;
        }
_L12:
        coldStart = new IntraSpans("ColdStart", i, null, s);
        obj = ((PerformanceSpan) (coldStart)).start;
        coldStartAllEvents = new PerformanceSpan("ColdStartAll", i, ((PerformanceMark) (obj)), s);
        coldStartAllTasks = new PerformanceSpan("ColdStartAllTasks", i, ((PerformanceMark) (obj)), s);
        coldStartShortTasks = new PerformanceSpan("ColdStartShortTasks", i, ((PerformanceMark) (obj)), s);
        return;
_L5:
        openEventView = new PerformanceSpan("OpenEventView", i, null, s);
        return;
_L6:
        saveEvent = new IntraSpans("SaveEvent", i, null, s);
        return;
_L7:
        toggleCalendar = new IntraSpans("ToggleCalendar", i, null, s);
        return;
_L10:
        if (saveEvent == null) goto _L40; else goto _L47
_L47:
        saveEvent.markAt(i, null, s);
        return;
_L8:
        if (openEventView == null) goto _L40; else goto _L48
_L48:
        openEventView.startSubSpanAt(i, null, s);
        return;
_L9:
        if (openEventView == null) goto _L40; else goto _L49
_L49:
        openEventView.stopSubSpanAt(i, null, s, 6);
        return;
_L29:
        if (openEventView == null) goto _L40; else goto _L50
_L50:
        openEventView.startSubSpanAt(i, null, s);
        return;
_L28:
        if (openEventView == null) goto _L40; else goto _L51
_L51:
        openEventView.stopSubSpanAt(i, null, s, 32);
        return;
_L30:
        if (openEventView == null) goto _L40; else goto _L52
_L52:
        openEventView.startSubSpanAt(i, null, s);
        return;
_L31:
        if (openEventView == null) goto _L40; else goto _L53
_L53:
        openEventView.stopSubSpanAt(i, null, s, 33);
        return;
_L11:
        if (openEventView != null)
        {
            openEventView.endAt(i, null, s);
            openEventView = null;
        }
        if (notificationAppStart == null) goto _L40; else goto _L54
_L54:
        notificationAppStart.endAt(i, null, s);
        notificationAppStart = null;
        return;
_L4:
        if (coldStart == null) goto _L40; else goto _L55
_L55:
        obj = com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements.APP_INTERACTIVE;
        if (((com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements) (obj)).action != null)
        {
            ((com.google.calendar.v2a.android.util.metric.MetricUtils.OneStepMeasurements) (obj)).action.run();
        }
        coldStart.endAt(i, null, s);
        coldStart = null;
        return;
_L32:
        taskLoad = new PerformanceSpan("TaskLoad", i, null, s);
        return;
_L33:
        if (taskLoad == null) goto _L40; else goto _L56
_L56:
        taskLoad.endAt(i, null, s);
        taskLoad = null;
        return;
_L34:
        taskProcessing = new PerformanceSpan("TaskProcessing", i, null, s);
        return;
_L35:
        if (taskProcessing == null) goto _L40; else goto _L57
_L57:
        taskProcessing.startSubSpanAt(i, null, s);
        return;
_L37:
        if (taskProcessing == null) goto _L40; else goto _L58
_L58:
        taskProcessing.stopSubSpanAt(i, null, s, 39);
        return;
_L36:
        if (taskProcessing == null) goto _L40; else goto _L59
_L59:
        taskProcessing.markAt(i, null, s);
        return;
_L38:
        if (taskProcessing == null) goto _L40; else goto _L60
_L60:
        taskProcessing.endAt(i, null, s);
        taskProcessing = null;
        return;
_L15:
        obj = grooveCreation;
        obj1 = new PerformanceSpan("GrooveCreation", i, null, s, true);
        obj1.alsoLogAsEvent = true;
        ((Map) (obj)).put(s, obj1);
        return;
_L16:
        obj = (PerformanceSpan)grooveCreation.get(s);
        if (obj == null) goto _L40; else goto _L61
_L61:
        ((PerformanceSpan) (obj)).startSubSpanAt(i, null, s);
        return;
_L17:
        obj = (PerformanceSpan)grooveCreation.get(s);
        if (obj == null) goto _L40; else goto _L62
_L62:
        ((PerformanceSpan) (obj)).stopSubSpanAt(i, null, s, 19);
        return;
_L18:
        obj = (PerformanceSpan)grooveCreation.get(s);
        if (obj == null) goto _L40; else goto _L63
_L63:
        ((PerformanceSpan) (obj)).startSubSpanAt(i, null, s);
        return;
_L19:
        obj = (PerformanceSpan)grooveCreation.get(s);
        if (obj == null) goto _L40; else goto _L64
_L64:
        ((PerformanceSpan) (obj)).stopSubSpanAt(i, null, s, 21);
        ((PerformanceSpan) (obj)).startSubSpanAt(i, null, s);
        return;
_L20:
        obj = (PerformanceSpan)grooveCreation.get(s);
        if (obj == null) goto _L40; else goto _L65
_L65:
        ((PerformanceSpan) (obj)).stopSubSpanAt(i, null, s, 22);
        return;
_L21:
        obj = (PerformanceSpan)grooveCreation.get(s);
        if (obj == null) goto _L40; else goto _L66
_L66:
        ((PerformanceSpan) (obj)).endAt(i, null, s);
        grooveCreation.remove(s);
        return;
_L22:
        obj = grooveDefer;
        obj1 = new PerformanceSpan("GrooveDefer", i, null, s, true);
        obj1.alsoLogAsEvent = true;
        ((Map) (obj)).put(s, obj1);
        return;
_L23:
        obj = (PerformanceSpan)grooveDefer.get(s);
        if (obj == null) goto _L40; else goto _L67
_L67:
        ((PerformanceSpan) (obj)).markAt(i, null, s);
        return;
_L24:
        obj = (PerformanceSpan)grooveDefer.get(s);
        if (obj == null) goto _L40; else goto _L68
_L68:
        ((PerformanceSpan) (obj)).startSubSpanAt(i, null, s);
        return;
_L25:
        obj = (PerformanceSpan)grooveDefer.get(s);
        if (obj == null) goto _L40; else goto _L69
_L69:
        ((PerformanceSpan) (obj)).stopSubSpanAt(i, null, s, 27);
        return;
_L26:
        obj = (PerformanceSpan)grooveDefer.get(s);
        if (obj == null) goto _L40; else goto _L70
_L70:
        ((PerformanceSpan) (obj)).endAt(i, null, s);
        grooveDefer.remove(s);
        return;
_L27:
        notificationAppStart = new IntraSpans("NotificationStart", i, null, s);
        return;
_L42:
        LogUtils.v("LatencyLogger", "  [dev] unhandled mark %s from %s", new Object[] {
            PerformanceMark.getMarkName(i), s
        });
        return;
    }
}
