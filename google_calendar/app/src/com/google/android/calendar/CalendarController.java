// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.EventInfoActivity;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.settings.SettingsActivity;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.ActivitySingletonCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar:
//            Utils

public class CalendarController
{
    public static final class Command
    {

        public Time endTime;
        public long extraLong;
        public Time selectedTime;
        public Time startTime;
        public long type;

        public final String toString()
        {
            String s;
            StringBuilder stringbuilder;
            s = "Unknown";
            stringbuilder = new StringBuilder();
            if ((type & 32L) == 0L) goto _L2; else goto _L1
_L1:
            s = "Go to time/event";
_L4:
            stringbuilder.append(s);
            stringbuilder.append(": uri=");
            stringbuilder.append(": id=");
            stringbuilder.append(0L);
            stringbuilder.append(", selected=");
            stringbuilder.append(selectedTime);
            stringbuilder.append(", start=");
            stringbuilder.append(startTime);
            stringbuilder.append(", end=");
            stringbuilder.append(endTime);
            stringbuilder.append(", x=");
            stringbuilder.append(0);
            stringbuilder.append(", y=");
            stringbuilder.append(0);
            return stringbuilder.toString();
_L2:
            if ((type & 128L) != 0L)
            {
                s = "Refresh events";
            } else
            if ((type & 512L) != 0L)
            {
                s = "Gone home";
            } else
            if ((type & 1024L) != 0L)
            {
                s = "Update title";
            } else
            if ((type & 8192L) != 0L)
            {
                s = "Update alternate month range";
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public Command(long l)
        {
            type = l;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/CalendarController);
    public static final ActivitySingletonCache instances = new _cls1();
    public final Context activity;
    public volatile int dispatchInProgressCounter;
    public int endDay;
    public Pair firstHandler;
    public final LinkedHashMap handlers = new LinkedHashMap(5);
    public int startDay;
    public final Time time = new Time();
    private Pair toBeAddedFirstHandler;
    private final LinkedHashMap toBeAddedHandlers = new LinkedHashMap();
    public final List toBeRemovedHandlers = new ArrayList();
    private final Runnable updateTimezone = new _cls2();

    CalendarController(Activity activity1)
    {
        dispatchInProgressCounter = 0;
        activity = activity1;
        updateTimezone.run();
        activity1 = time;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        ((Time) (activity1)).impl.timezone = ((Time) (activity1)).timezone;
        ((Time) (activity1)).impl.set(l);
        ((Time) (activity1)).impl.toMillis(true);
        activity1.copyFieldsFromImpl();
        activity1 = time;
        activity1.writeFieldsToImpl();
        startDay = android.text.format.Time.getJulianDay(((Time) (activity1)).impl.toMillis(false), time.gmtoff);
        activity1 = time;
        activity1.writeFieldsToImpl();
        endDay = android.text.format.Time.getJulianDay(((Time) (activity1)).impl.toMillis(false), time.gmtoff);
    }

    public static void launchSettings(Activity activity1)
    {
        Intent intent = new Intent(activity1, com/google/android/calendar/settings/SettingsActivity);
        intent.setFlags(0x20020000);
        activity1.startActivity(intent);
    }

    public static void launchViewDetails(Context context, TimelineItem timelineitem)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(context, com/google/android/calendar/event/EventInfoActivity);
        intent.setFlags(32768);
        TimelineItemUtil.setLaunchTimelineItem(intent, timelineitem);
        LatencyLoggerHolder.get().markAt(3);
        context.startActivity(intent);
        if (context instanceof Activity)
        {
            ((Activity)context).overridePendingTransition(0x7f060016, 0x10a0001);
        }
    }

    public final void deregisterHandler(Integer integer)
    {
        this;
        JVM INSTR monitorenter ;
        if (dispatchInProgressCounter <= 0)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        toBeRemovedHandlers.add(integer);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        handlers.remove(integer);
        if (firstHandler != null && firstHandler.first == integer)
        {
            firstHandler = null;
        }
        if (true) goto _L2; else goto _L1
_L1:
        integer;
        this;
        JVM INSTR monitorexit ;
        throw integer;
    }

    public final void executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(Command command)
    {
        boolean flag1 = false;
        LogUtils.d(TAG, "%s", new Object[] {
            command
        });
        if ((command.type & 128L) != 0L)
        {
            LatencyLoggerHolder.get().markAt(8);
        }
        class Command.Handler
        {

            public abstract long getSupportedCommands();

            public abstract void handleCommand(Command command1);
        }

        Object obj;
        Object obj1;
        int i;
        boolean flag;
        long l;
        long l1;
        if (command.startTime != null)
        {
            Time time1 = command.startTime;
            time1.writeFieldsToImpl();
            l = time1.impl.toMillis(false);
        } else
        {
            l = 0L;
        }
        if (command.selectedTime == null) goto _L2; else goto _L1
_L1:
        obj = command.selectedTime;
        ((Time) (obj)).writeFieldsToImpl();
        if (((Time) (obj)).impl.toMillis(false) == 0L) goto _L2; else goto _L3
_L3:
        l1 = command.selectedTime.toMillisWithFallback();
        obj = time;
        ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
        ((Time) (obj)).impl.set(l1);
        ((Time) (obj)).impl.toMillis(true);
        ((Time) (obj)).copyFieldsFromImpl();
_L7:
        if (l == 0L)
        {
            command.startTime = time;
        }
        this;
        JVM INSTR monitorenter ;
        dispatchInProgressCounter = dispatchInProgressCounter + 1;
        LogUtils.d(TAG, "executeCommand: Dispatching to %d handlers", new Object[] {
            Integer.valueOf(handlers.size())
        });
        if (firstHandler == null)
        {
            break MISSING_BLOCK_LABEL_262;
        }
        obj = (Command.Handler)firstHandler.second;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_262;
        }
        if ((((Command.Handler) (obj)).getSupportedCommands() & command.type) != 0L && !toBeRemovedHandlers.contains(firstHandler.first))
        {
            ((Command.Handler) (obj)).handleCommand(command);
        }
        obj = handlers.entrySet().iterator();
_L5:
        do
        {
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break; /* Loop/switch isn't completed */
                }
                obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
                i = ((Integer)((java.util.Map.Entry) (obj1)).getKey()).intValue();
            } while (firstHandler != null && i == ((Integer)firstHandler.first).intValue());
            obj1 = (Command.Handler)((java.util.Map.Entry) (obj1)).getValue();
        } while (obj1 == null);
        if ((((Command.Handler) (obj1)).getSupportedCommands() & command.type) == 0L || toBeRemovedHandlers.contains(Integer.valueOf(i))) goto _L5; else goto _L4
_L4:
        ((Command.Handler) (obj1)).handleCommand(command);
          goto _L5
        command;
        this;
        JVM INSTR monitorexit ;
        throw command;
_L2:
label0:
        {
            flag = flag1;
            if (command.startTime == null)
            {
                break label0;
            }
            flag = flag1;
            if (command.type == 8192L)
            {
                break label0;
            }
            obj = time;
            ((Time) (obj)).writeFieldsToImpl();
            l1 = ((Time) (obj)).impl.toMillis(false);
            obj = command.startTime;
            ((Time) (obj)).writeFieldsToImpl();
            if (l1 >= ((Time) (obj)).impl.toMillis(false))
            {
                flag = flag1;
                if (command.endTime == null)
                {
                    break label0;
                }
                obj = command.endTime;
                ((Time) (obj)).writeFieldsToImpl();
                flag = flag1;
                if (l1 <= ((Time) (obj)).impl.toMillis(false))
                {
                    break label0;
                }
            }
            flag = true;
        }
        if (flag)
        {
            l1 = command.startTime.toMillisWithFallback();
            obj = time;
            ((Time) (obj)).impl.timezone = ((Time) (obj)).timezone;
            ((Time) (obj)).impl.set(l1);
            ((Time) (obj)).impl.toMillis(true);
            ((Time) (obj)).copyFieldsFromImpl();
            command.selectedTime = time;
        }
        if (true) goto _L7; else goto _L6
_L6:
        dispatchInProgressCounter = dispatchInProgressCounter - 1;
        if (dispatchInProgressCounter != 0)
        {
            break MISSING_BLOCK_LABEL_763;
        }
        if (toBeRemovedHandlers.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_672;
        }
        command = toBeRemovedHandlers.iterator();
        do
        {
            if (!command.hasNext())
            {
                break;
            }
            obj = (Integer)command.next();
            handlers.remove(obj);
            if (firstHandler != null && ((Integer) (obj)).equals(firstHandler.first))
            {
                firstHandler = null;
            }
        } while (true);
        toBeRemovedHandlers.clear();
        if (toBeAddedFirstHandler != null)
        {
            firstHandler = toBeAddedFirstHandler;
            toBeAddedFirstHandler = null;
        }
        if (toBeAddedHandlers.size() > 0)
        {
            for (command = toBeAddedHandlers.entrySet().iterator(); command.hasNext(); handlers.put((Integer)((java.util.Map.Entry) (obj)).getKey(), (Command.Handler)((java.util.Map.Entry) (obj)).getValue()))
            {
                obj = (java.util.Map.Entry)command.next();
            }

        }
        this;
        JVM INSTR monitorexit ;
    }

    public final void goTo(Object obj, Time time1, long l)
    {
        obj = new Command(32L);
        obj.startTime = time1;
        obj.selectedTime = time1;
        obj.extraLong = l;
        executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(((Command) (obj)));
    }

    public final void registerFirstHandler(int i, Command.Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        registerHandler(0, handler);
        if (dispatchInProgressCounter <= 0)
        {
            break MISSING_BLOCK_LABEL_34;
        }
        toBeAddedFirstHandler = new Pair(Integer.valueOf(0), handler);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        firstHandler = new Pair(Integer.valueOf(0), handler);
        if (true) goto _L2; else goto _L1
_L1:
        handler;
        this;
        JVM INSTR monitorexit ;
        throw handler;
    }

    public final void registerHandler(int i, Command.Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        if (dispatchInProgressCounter <= 0)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        toBeAddedHandlers.put(Integer.valueOf(i), handler);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        handlers.put(Integer.valueOf(i), handler);
        if (true) goto _L2; else goto _L1
_L1:
        handler;
        this;
        JVM INSTR monitorexit ;
        throw handler;
    }

    public final void setTime(long l)
    {
        Time time1 = time;
        time1.impl.timezone = time1.timezone;
        time1.impl.set(l);
        time1.impl.toMillis(true);
        time1.copyFieldsFromImpl();
    }

    public final void updateVisibleRange(Object obj, Time time1, Time time2, Time time3, boolean flag, long l)
    {
        obj = new Command(1024L);
        obj.startTime = time1;
        Time time4 = new Time(Utils.getTimeZoneId(activity));
        int i;
        long l1;
        if (Clock.mockedTimestamp > 0L)
        {
            l1 = Clock.mockedTimestamp;
        } else
        {
            l1 = System.currentTimeMillis();
        }
        time4.impl.timezone = time4.timezone;
        time4.impl.set(l1);
        time4.impl.toMillis(true);
        time4.copyFieldsFromImpl();
        time1.writeFieldsToImpl();
        startDay = android.text.format.Time.getJulianDay(time1.impl.toMillis(false), time1.gmtoff);
        time2.writeFieldsToImpl();
        endDay = android.text.format.Time.getJulianDay(time2.impl.toMillis(false), time2.gmtoff);
        time4.writeFieldsToImpl();
        i = android.text.format.Time.getJulianDay(time4.impl.toMillis(false), time4.gmtoff);
        if (time3 != null)
        {
            obj.selectedTime = time3;
        } else
        if (startDay <= i && i <= endDay)
        {
            obj.selectedTime = time4;
        }
        if (!flag)
        {
            time2 = time1;
        }
        obj.endTime = time2;
        obj.extraLong = l;
        executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(((Command) (obj)));
    }


    private class _cls2
        implements Runnable
    {

        private final CalendarController this$0;

        public final void run()
        {
            Time time1 = time;
            String s = Utils.getTimeZoneId(activity, this);
            time1.writeFieldsToImpl();
            time1.impl.switchTimezone(s);
            time1.copyFieldsFromImpl();
        }

        _cls2()
        {
            this$0 = CalendarController.this;
            super();
        }
    }


    private class _cls1 extends ActivitySingletonCache
    {

        protected final Object createInstance(Activity activity1)
        {
            return new CalendarController(activity1);
        }

        _cls1()
        {
        }
    }

}
