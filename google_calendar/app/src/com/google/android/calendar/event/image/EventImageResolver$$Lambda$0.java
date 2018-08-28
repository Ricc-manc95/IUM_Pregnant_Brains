// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import android.text.format.Time;
import com.google.android.calendar.time.TimeUtils;
import com.google.common.base.Supplier;
import java.util.TimeZone;

final class arg._cls1
    implements Supplier
{

    private final Context arg$1;

    public final Object get()
    {
        Object obj = arg$1;
        com.google.android.calendar.time.mbda._cls0 _lcls0 = TimeUtils.tZUtils;
        if (com.google.android.calendar.time.TZRequest)
        {
            com.google.android.calendar.time.meZone(((Context) (obj)), null, false);
        }
        if (com.google.android.calendar.time.meTZ)
        {
            obj = com.google.android.calendar.time.Z;
        } else
        {
            obj = Time.getCurrentTimezone();
        }
        return TimeZone.getTimeZone(((String) (obj)));
    }

    (Context context)
    {
        arg$1 = context;
    }
}
