// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency.impl;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.libraries.performance.primes.TimerEvent;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.latency.impl:
//            PerformanceMark

class PerformanceSpan
{

    public static Context context;
    public boolean alsoLogAsEvent;
    private boolean logIntraMarksToAnalytics;
    public ArrayList marks;
    private String spanName;
    private TimerEvent spanTimer;
    public PerformanceMark start;
    private SparseArray timerEvents;

    public PerformanceSpan(String s, int i, PerformanceMark performancemark, String s1)
    {
        alsoLogAsEvent = false;
        logIntraMarksToAnalytics = false;
        initialize(s, i, performancemark, s1, false);
    }

    public PerformanceSpan(String s, int i, PerformanceMark performancemark, String s1, boolean flag)
    {
        alsoLogAsEvent = false;
        logIntraMarksToAnalytics = false;
        initialize(s, i, null, s1, true);
    }

    private final void initialize(String s, int i, PerformanceMark performancemark, String s1, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        spanName = s;
        logIntraMarksToAnalytics = flag;
        timerEvents = new SparseArray();
        s = PerformanceMetricCollectorHolder.instance;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        spanTimer = ((PerformanceMetricCollector)s).initializeTimer();
        if (performancemark == null) goto _L2; else goto _L1
_L1:
        start = performancemark;
_L10:
        if (LogUtils.maxEnabledLogLevel <= 2) goto _L4; else goto _L3
_L3:
        boolean flag1 = false;
          goto _L5
_L11:
        marks = new ArrayList();
_L12:
        if (!isDevelopment()) goto _L7; else goto _L6
_L6:
        if (start.tag != null) goto _L9; else goto _L8
_L8:
        LogUtils.d("PerformanceSpan", "[dev] span %s started by mark %s", new Object[] {
            spanName, PerformanceMark.getMarkName(i)
        });
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        start = new PerformanceMark(s1);
          goto _L10
_L4:
        if (Log.isLoggable("PerformanceSpan", 2))
        {
            flag1 = true;
            continue; /* Loop/switch isn't completed */
        }
        flag1 = Log.isLoggable("PerformanceSpan", 2);
        continue; /* Loop/switch isn't completed */
_L9:
        LogUtils.d("PerformanceSpan", "[dev] span %s started by mark %s from %s", new Object[] {
            spanName, PerformanceMark.getMarkName(i), start.tag
        });
          goto _L7
_L5:
        if (!flag1 && !flag) goto _L12; else goto _L11
    }

    private static boolean isDevelopment()
    {
        if (LogUtils.maxEnabledLogLevel > 3)
        {
            return false;
        }
        if (Log.isLoggable("PerformanceSpan", 3))
        {
            return true;
        } else
        {
            return Log.isLoggable("PerformanceSpan", 3);
        }
    }

    private final PerformanceMark markAtNoTimer(int i, PerformanceMark performancemark, String s)
    {
        PerformanceMark performancemark1;
label0:
        {
            PerformanceMark performancemark2 = performancemark;
            if (marks != null)
            {
                performancemark1 = performancemark;
                if (performancemark == null)
                {
                    performancemark1 = new PerformanceMark(i, s);
                }
                marks.add(performancemark1);
                performancemark2 = performancemark1;
                if (isDevelopment())
                {
                    if (performancemark1.tag != null)
                    {
                        break label0;
                    }
                    LogUtils.d("PerformanceSpan", "[dev] mark %s in %s", new Object[] {
                        performancemark1.mark, spanName
                    });
                    performancemark2 = performancemark1;
                }
            }
            return performancemark2;
        }
        LogUtils.d("PerformanceSpan", "[dev] mark %s in %s from %s", new Object[] {
            performancemark1.mark, spanName, performancemark1.tag
        });
        return performancemark1;
    }

    public void clearAt(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = (TimerEvent)timerEvents.get(i);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        Object obj1;
        Object obj2;
        obj1 = PerformanceMark.getMarkName(j);
        obj2 = PerformanceMetricCollectorHolder.instance;
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj2 = (PerformanceMetricCollector)obj2;
        String s = spanName;
        ((PerformanceMetricCollector) (obj2)).logTime(((TimerEvent) (obj)), (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(obj1).length())).append(s).append(".").append(((String) (obj1))).toString());
        if (marks == null) goto _L2; else goto _L1
_L1:
        obj2 = marks.iterator();
        obj = null;
_L4:
        if (!((Iterator) (obj2)).hasNext())
        {
            break MISSING_BLOCK_LABEL_179;
        }
        obj1 = (PerformanceMark)((Iterator) (obj2)).next();
        obj = obj1;
        if (((PerformanceMark) (obj1)).markId != i) goto _L4; else goto _L3
_L3:
        ((Iterator) (obj2)).remove();
        obj = obj1;
        if (!isDevelopment()) goto _L2; else goto _L5
_L5:
        if (obj != null) goto _L7; else goto _L6
_L6:
        LogUtils.d("PerformanceSpan", "[dev] mark %d dropped from %s", new Object[] {
            Integer.valueOf(i), spanName
        });
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L7:
label0:
        {
            if (((PerformanceMark) (obj)).tag != null)
            {
                break label0;
            }
            LogUtils.d("PerformanceSpan", "[dev] mark %s dropped from %s", new Object[] {
                ((PerformanceMark) (obj)).mark, spanName
            });
        }
          goto _L2
        LogUtils.d("PerformanceSpan", "[dev] mark %s dropped from %s by %s", new Object[] {
            ((PerformanceMark) (obj)).mark, spanName, ((PerformanceMark) (obj)).tag
        });
          goto _L2
    }

    public final PerformanceMark endAt(int i, PerformanceMark performancemark, String s)
    {
        this;
        JVM INSTR monitorenter ;
        PerformanceMark performancemark1;
        performancemark1 = performancemark;
        if (performancemark != null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        performancemark1 = new PerformanceMark(i, s);
        if (!isDevelopment()) goto _L2; else goto _L1
_L1:
        if (performancemark1.tag != null) goto _L4; else goto _L3
_L3:
        LogUtils.d("PerformanceSpan", "[dev] mark end %s <- %s", new Object[] {
            performancemark1.mark, spanName
        });
_L2:
        performancemark = null;
        LogUtils.d("PerformanceSpan", "Start report for span %s", new Object[] {
            spanName
        });
        if (marks == null) goto _L6; else goto _L5
_L5:
        marks.add(performancemark1);
        if (marks.size() >= 2) goto _L8; else goto _L7
_L7:
        performancemark = null;
_L6:
        long l2;
        long l = (performancemark1.cpuTimeNanos - start.cpuTimeNanos) / 0xf4240L;
        l2 = performancemark1.wallTimeMillis - start.wallTimeMillis;
        LogUtils.d("PerformanceSpan", "finish report for span %s: %d elapsed, %d cpu", new Object[] {
            spanName, Long.valueOf(l2), Long.valueOf(l)
        });
        StringBuilder stringbuilder;
        int j;
        long l1;
        long l3;
        long l4;
        if (!logIntraMarksToAnalytics)
        {
            performancemark = null;
        }
        s = AnalyticsLoggerHolder.instance;
        if (s != null) goto _L10; else goto _L9
_L9:
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        performancemark;
        this;
        JVM INSTR monitorexit ;
        throw performancemark;
_L4:
        LogUtils.d("PerformanceSpan", "[dev] mark end %s <- %s from %s", new Object[] {
            performancemark1.mark, spanName, performancemark1.tag
        });
          goto _L2
_L8:
        stringbuilder = new StringBuilder();
        performancemark = start;
        i = 0;
_L18:
        if (i >= marks.size())
        {
            break MISSING_BLOCK_LABEL_648;
        }
        s = (PerformanceMark)marks.get(i);
        l1 = (((PerformanceMark) (s)).cpuTimeNanos - performancemark.cpuTimeNanos) / 0xf4240L;
        l2 = ((PerformanceMark) (s)).wallTimeMillis - performancemark.wallTimeMillis;
        l3 = ((PerformanceMark) (s)).wallTimeMillis - start.wallTimeMillis;
        if (performancemark != start) goto _L12; else goto _L11
_L11:
        if (((PerformanceMark) (s)).tag != null) goto _L14; else goto _L13
_L13:
        LogUtils.v("PerformanceSpan", "report mark %s[%s]: %d elapsed, %d cpu", new Object[] {
            spanName, ((PerformanceMark) (s)).mark, Long.valueOf(l2), Long.valueOf(l1)
        });
_L15:
        if (i >= marks.size() - 1)
        {
            break MISSING_BLOCK_LABEL_916;
        }
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_416;
        }
        stringbuilder.append(",");
        stringbuilder.append(((PerformanceMark) (s)).mark).append("=").append(l3);
        break MISSING_BLOCK_LABEL_916;
_L14:
        LogUtils.v("PerformanceSpan", "report mark %s[%s]: %d elapsed, %d cpu from %s", new Object[] {
            spanName, ((PerformanceMark) (s)).mark, Long.valueOf(l2), Long.valueOf(l1), ((PerformanceMark) (s)).tag
        });
          goto _L15
_L12:
label0:
        {
            l4 = (((PerformanceMark) (s)).cpuTimeNanos - start.cpuTimeNanos) / 0xf4240L;
            if (((PerformanceMark) (s)).tag != null)
            {
                break label0;
            }
            LogUtils.v("PerformanceSpan", "report mark %s[%s]: %d elapsed, %d cpu;  %d total, %d cpu", new Object[] {
                spanName, ((PerformanceMark) (s)).mark, Long.valueOf(l2), Long.valueOf(l1), Long.valueOf(l3), Long.valueOf(l4)
            });
        }
          goto _L15
        LogUtils.v("PerformanceSpan", "report mark %s[%s]: %d elapsed, %d cpu;  %d total, %d cpu from %s", new Object[] {
            spanName, ((PerformanceMark) (s)).mark, Long.valueOf(l2), Long.valueOf(l1), Long.valueOf(l3), Long.valueOf(l4), ((PerformanceMark) (s)).tag
        });
          goto _L15
        performancemark = stringbuilder.toString();
          goto _L6
_L10:
        ((AnalyticsLogger)s).trackTiming(context, "latency", l2, spanName, performancemark);
        if (!alsoLogAsEvent)
        {
            break MISSING_BLOCK_LABEL_730;
        }
        s = AnalyticsLoggerHolder.instance;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_706;
        }
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        ((AnalyticsLogger)s).trackEvent(context, "latency", spanName, performancemark, Long.valueOf(l2));
        performancemark = PerformanceMetricCollectorHolder.instance;
        if (performancemark != null)
        {
            break MISSING_BLOCK_LABEL_751;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        ((PerformanceMetricCollector)performancemark).logTime(spanTimer, spanName);
        i = 0;
_L17:
        if (i >= timerEvents.size())
        {
            break; /* Loop/switch isn't completed */
        }
        performancemark = (TimerEvent)timerEvents.valueAt(i);
        j = timerEvents.keyAt(i);
        s = PerformanceMetricCollectorHolder.instance;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_824;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        s = (PerformanceMetricCollector)s;
        String s1 = spanName;
        String s2 = PerformanceMark.getMarkName(j);
        s.logTime(performancemark, (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s2).length())).append(s1).append(".").append(s2).toString());
        timerEvents.removeAt(i);
        i++;
        if (true) goto _L17; else goto _L16
_L16:
        return performancemark1;
        i++;
        performancemark = s;
          goto _L18
    }

    public final PerformanceMark markAt(int i, PerformanceMark performancemark, String s)
    {
        this;
        JVM INSTR monitorenter ;
        performancemark = PerformanceMetricCollectorHolder.instance;
        if (performancemark != null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        performancemark;
        this;
        JVM INSTR monitorexit ;
        throw performancemark;
        performancemark = (PerformanceMetricCollector)performancemark;
        TimerEvent timerevent = spanTimer;
        String s1 = spanName;
        String s2 = PerformanceMark.getMarkName(i);
        performancemark.logTime(timerevent, (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s2).length())).append(s1).append(".").append(s2).toString());
        performancemark = markAtNoTimer(i, null, s);
        this;
        JVM INSTR monitorexit ;
        return performancemark;
    }

    public final PerformanceMark startSubSpanAt(int i, PerformanceMark performancemark, String s)
    {
        this;
        JVM INSTR monitorenter ;
        SparseArray sparsearray;
        PerformanceMetricCollector performancemetriccollector;
        sparsearray = timerEvents;
        performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector != null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        performancemark;
        this;
        JVM INSTR monitorexit ;
        throw performancemark;
        sparsearray.put(i, ((PerformanceMetricCollector)performancemetriccollector).initializeTimer());
        performancemark = markAtNoTimer(i, performancemark, s);
        this;
        JVM INSTR monitorexit ;
        return performancemark;
    }

    public final PerformanceMark stopSubSpanAt(int i, PerformanceMark performancemark, String s, int j)
    {
        this;
        JVM INSTR monitorenter ;
        TimerEvent timerevent = (TimerEvent)timerEvents.get(j);
        if (timerevent == null)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        performancemark;
        this;
        JVM INSTR monitorexit ;
        throw performancemark;
        performancemetriccollector = (PerformanceMetricCollector)performancemetriccollector;
        String s1 = spanName;
        String s2 = PerformanceMark.getMarkName(i);
        performancemetriccollector.logTime(timerevent, (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s2).length())).append(s1).append(".").append(s2).toString());
        timerEvents.remove(j);
        performancemark = markAtNoTimer(i, performancemark, s);
        this;
        JVM INSTR monitorexit ;
        return performancemark;
    }
}
