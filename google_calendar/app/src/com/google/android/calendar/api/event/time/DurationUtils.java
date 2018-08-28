// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.time;

import com.android.calendarcommon2.DateException;
import com.android.calendarcommon2.Duration;
import com.android.calendarcommon2.LogUtils;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Present;

public class DurationUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/time/DurationUtils);

    public DurationUtils()
    {
    }

    public static Optional parse(String s)
    {
        Object obj;
        if (Platform.stringIsNullOrEmpty(s))
        {
            break MISSING_BLOCK_LABEL_59;
        }
        obj = new Duration();
        ((Duration) (obj)).parse(s);
        obj = Long.valueOf(((Duration) (obj)).getMillis());
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            LogUtils.e(TAG, ((Throwable) (obj)), "Unable to parse duration %s", new Object[] {
                s
            });
        }
        return Absent.INSTANCE;
        obj = new Present(obj);
        return ((Optional) (obj));
    }

    public static String toStringDuration(long l, boolean flag)
    {
label0:
        {
            long l1 = Math.abs(l);
            String s;
            String s1;
            if (flag)
            {
                l1 = ((l1 + 0x5265c00L) - 1L) / 0x5265c00L;
                s = (new StringBuilder(22)).append("P").append(l1).append("D").toString();
            } else
            {
                l1 /= 1000L;
                s = (new StringBuilder(23)).append("PT").append(l1).append("S").toString();
            }
            s1 = s;
            if (l < 0L)
            {
                s = String.valueOf(s);
                if (s.length() == 0)
                {
                    break label0;
                }
                s1 = "-".concat(s);
            }
            return s1;
        }
        return new String("-");
    }

}
