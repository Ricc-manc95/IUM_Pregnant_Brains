// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import android.database.Cursor;
import com.google.android.calendar.api.common.TimeZoneHelper;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import com.google.common.base.Optional;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.api.event.time:
//            EventKindUtils, RecurrenceStoreUtils, DurationUtils, Recurrence

public final class EventTiming
{

    public final boolean allDay;
    public final long endMillisUtc;
    public final String endTimeZoneId;
    public final long firstStartMillisUtc;
    public final Recurrence recurrence;
    public final long startMillisUtc;
    public final String timeZoneId;

    private EventTiming(long l, long l1, boolean flag, String s, String s1, 
            Recurrence recurrence1, long l2)
    {
        l1 = Math.max(l, l1);
        boolean flag1;
        if (s == null || TimeZoneHelper.isValidTimeZoneId(s))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            s = null;
        }
        if (s1 == null || TimeZoneHelper.isValidTimeZoneId(s1))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            s1 = null;
        }
        allDay = flag;
        if (flag)
        {
            startMillisUtc = TimestampUtil.roundToMidnight(l, s, true, "UTC");
            endMillisUtc = TimestampUtil.roundToMidnight(l1, s1, false, "UTC");
            timeZoneId = null;
            endTimeZoneId = null;
            firstStartMillisUtc = TimestampUtil.roundToMidnight(l2, s, true, "UTC");
        } else
        {
            startMillisUtc = l;
            endMillisUtc = l1;
            timeZoneId = s;
            endTimeZoneId = s1;
            firstStartMillisUtc = l2;
        }
        recurrence = recurrence1;
    }

    public static EventTiming create(Cursor cursor, String as[])
        throws IOException
    {
        boolean flag = true;
        int i = EventKindUtils.getEventKind(cursor);
        long l2 = 0L;
        long l;
        long l1;
        if (i == 1)
        {
            String s;
            if (as == LoadDetailsConstants.INSTANCE_PROJECTION)
            {
                l = cursor.getLong(42);
                l1 = cursor.getLong(43);
            } else
            {
                l = cursor.getLong(0);
                class .Lambda._cls0
                    implements Function
                {

                    private final long arg$1;

                    public final Object apply(Object obj)
                    {
                        return Long.valueOf(arg$1 + ((Long)obj).longValue());
                    }

            .Lambda._cls0(long l)
            {
                arg$1 = l;
            }
                }

                l1 = ((Long)DurationUtils.parse(cursor.getString(8)).transform(new .Lambda._cls0(l)).or(Long.valueOf(l))).longValue();
            }
            l2 = cursor.getLong(0);
        } else
        {
            l = cursor.getLong(0);
            l1 = cursor.getLong(1);
        }
        as = cursor.getString(9);
        s = cursor.getString(31);
        if (cursor.getInt(5) != 1)
        {
            flag = false;
        }
        return new EventTiming(l, l1, flag, as, s, RecurrenceStoreUtils.extractRecurrence(cursor), l2);
    }
}
