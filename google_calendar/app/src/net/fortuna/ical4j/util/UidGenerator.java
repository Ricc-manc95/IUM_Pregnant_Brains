// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import net.fortuna.ical4j.model.DateTime;

// Referenced classes of package net.fortuna.ical4j.util:
//            HostInfo

public class UidGenerator
{

    private static long lastMillis;
    public final HostInfo hostInfo;
    public final String pid;

    public UidGenerator(HostInfo hostinfo, String s)
    {
        hostInfo = hostinfo;
        pid = s;
    }

    public static DateTime uniqueTimestamp()
    {
        net/fortuna/ical4j/util/UidGenerator;
        JVM INSTR monitorenter ;
        long l1 = System.currentTimeMillis();
        long l = l1;
        if (l1 < lastMillis)
        {
            l = lastMillis;
        }
        l1 = l;
        if (l - lastMillis < 1000L)
        {
            l1 = l + 1000L;
        }
        lastMillis = l1;
        net/fortuna/ical4j/util/UidGenerator;
        JVM INSTR monitorexit ;
        DateTime datetime = new DateTime(l1);
        datetime.setUtc(true);
        return datetime;
        Exception exception;
        exception;
        net/fortuna/ical4j/util/UidGenerator;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
