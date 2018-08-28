// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.os.Bundle;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.calendar.Utils;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public final class DefaultBundleFactory
{

    public final Calendar calendar = Calendar.getInstance();
    public final TimeUtils timeUtils;

    public DefaultBundleFactory(TimeUtils timeutils)
    {
        timeUtils = timeutils;
    }

    public static Bundle createDefaultBundleForTime(long l)
    {
        Object obj = CalendarProperties.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        obj = (Integer)((CalendarProperties)obj).getPropertyValue(3);
        if (((Integer) (obj)).intValue() == -1)
        {
            obj = null;
        } else
        {
            obj = Long.valueOf(TimeUnit.MINUTES.toMillis(((Integer) (obj)).intValue()) + l);
        }
        obj = Utils.getExtraEventBundle(l, ((Long) (obj)), false);
        ((Bundle) (obj)).putString("createEditSource", "grid");
        return ((Bundle) (obj));
    }
}
