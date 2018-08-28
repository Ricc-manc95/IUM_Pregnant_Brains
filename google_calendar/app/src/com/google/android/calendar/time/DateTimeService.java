// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.time;

import android.content.Context;
import android.os.Looper;
import android.text.format.Time;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.calendar.v2.client.service.api.time.DateTime;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.time:
//            Time, TimeZoneImpl, TimeUtils, DateTimeImpl

public class DateTimeService
    implements OnPropertyChangedListener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/time/DateTimeService);
    public static DateTimeService instance;
    public TimeZoneImpl calendarTimeZone;
    public com.google.android.calendar.time.Time recycle;

    private DateTimeService()
    {
        recycle = new com.google.android.calendar.time.Time();
    }

    public DateTimeService(final Context context)
    {
        this();
        calendarTimeZone = new TimeZoneImpl(TimeUtils.getTimeZoneId(context, new _cls1()));
    }

    public DateTimeService(String s)
    {
        this();
        calendarTimeZone = new TimeZoneImpl(s);
    }

    public static DateTimeService getInstance()
    {
        boolean flag;
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("DateTimeService.getInstance() must be called on main thread"));
        }
        DateTimeService datetimeservice = instance;
        if (datetimeservice == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeService#initialize(...) must be called first"));
        } else
        {
            return (DateTimeService)datetimeservice;
        }
    }

    public static void initialize(Context context)
    {
        com/google/android/calendar/time/DateTimeService;
        JVM INSTR monitorenter ;
        DateTimeService datetimeservice = instance;
        if (datetimeservice == null) goto _L2; else goto _L1
_L1:
        com/google/android/calendar/time/DateTimeService;
        JVM INSTR monitorexit ;
        return;
_L2:
        instance = new DateTimeService(context);
        if (true) goto _L1; else goto _L3
_L3:
        context;
        throw context;
    }

    public static DateTimeImpl toDateTimeImpl(DateTime datetime)
    {
        if (datetime == null)
        {
            throw new NullPointerException();
        }
        if (datetime instanceof DateTimeImpl)
        {
            return (DateTimeImpl)datetime;
        } else
        {
            return new DateTimeImpl(datetime.getMillis(), datetime.getTimeZone());
        }
    }

    public final long convertLocalToAllDayLocal(long l)
    {
        Calendar calendar = Calendar.getInstance(calendarTimeZone.timeZone);
        calendar.setTimeInMillis(l);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public final long convertLocalToAllDayUtc(long l)
    {
        Calendar calendar = Calendar.getInstance(calendarTimeZone.timeZone);
        calendar.setTimeInMillis(l);
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar1.clear();
        calendar1.set(calendar.get(1), calendar.get(2), calendar.get(5));
        return calendar1.getTimeInMillis();
    }

    public final long fromLocalTime(DateTime datetime, boolean flag, com.google.calendar.v2.client.service.api.time.TimeZone timezone)
    {
        if (!flag)
        {
            datetime = (new DateTimeImpl(datetime.getMillis(), timezone)).time;
            datetime.writeFieldsToImpl();
            return ((com.google.android.calendar.time.Time) (datetime)).impl.toMillis(false);
        } else
        {
            return TimeUtils.convertAlldayLocalToUTC(null, datetime.getMillis(), calendarTimeZone.timeZone.getID());
        }
    }

    public final long getMillis(int i, int j, int k, int l, int i1)
    {
        recycle.timezone = calendarTimeZone.timeZone.getID();
        com.google.android.calendar.time.Time time = recycle;
        time.writeFieldsToImpl();
        time.impl.set(0, i1, l, k, j, i);
        time.copyFieldsFromImpl();
        time = recycle;
        time.writeFieldsToImpl();
        return time.impl.toMillis(true);
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        if (i == 0)
        {
            if (obj instanceof String)
            {
                calendarTimeZone = new TimeZoneImpl((String)obj);
                return;
            } else
            {
                LogUtils.wtf(TAG, "Wrong time zone property format, should be a String.", new Object[0]);
                return;
            }
        } else
        {
            LogUtils.wtf(TAG, "Unexpected property passed to onCalendarPropertyChanged.", new Object[0]);
            return;
        }
    }


    private class _cls1
        implements Runnable
    {

        private final DateTimeService this$0;
        private final Context val$context;

        public final void run()
        {
            DateTimeService datetimeservice = DateTimeService.this;
            Object obj = context;
            TimeUtils.TimeZoneUtils timezoneutils = TimeUtils.tZUtils;
            if (TimeUtils.TimeZoneUtils.firstTZRequest)
            {
                TimeUtils.TimeZoneUtils.getTimeZone(((Context) (obj)), null, false);
            }
            if (TimeUtils.TimeZoneUtils.useHomeTZ)
            {
                obj = TimeUtils.TimeZoneUtils.homeTZ;
            } else
            {
                obj = Time.getCurrentTimezone();
            }
            datetimeservice.calendarTimeZone = new TimeZoneImpl(((String) (obj)));
        }

        _cls1()
        {
            this$0 = DateTimeService.this;
            context = context1;
            super();
        }
    }

}
