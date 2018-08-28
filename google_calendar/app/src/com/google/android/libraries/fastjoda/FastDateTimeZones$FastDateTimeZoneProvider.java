// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.fastjoda;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;
import org.joda.time.tz.Provider;

// Referenced classes of package com.google.android.libraries.fastjoda:
//            FastDateTimeZones

public static class 
    implements Provider
{

    private static final ImmutableSet AVAILABLE_IDS = ImmutableSet.copyOf(TimeZone.getAvailableIDs());
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private static final ConcurrentHashMap zoneMap = new ConcurrentHashMap();

    public final Set getAvailableIDs()
    {
        return AVAILABLE_IDS;
    }

    public final DateTimeZone getZone(String s)
    {
        Object obj;
        if (s == null)
        {
            obj = DateTimeZone.UTC;
        } else
        {
            DateTimeZone datetimezone = (DateTimeZone)zoneMap.get(s);
            obj = datetimezone;
            if (datetimezone == null)
            {
                obj = TimeZone.getTimeZone(s);
                Object obj1;
                if (obj == null || ((TimeZone) (obj)).hasSameRules(UTC))
                {
                    obj1 = DateTimeZone.UTC;
                } else
                {
                    obj1 = new UTC(((TimeZone) (obj)));
                }
                s = (DateTimeZone)zoneMap.putIfAbsent(s, obj1);
                obj = s;
                if (s == null)
                {
                    return ((DateTimeZone) (obj1));
                }
            }
        }
        return ((DateTimeZone) (obj));
    }


    public ()
    {
    }
}
