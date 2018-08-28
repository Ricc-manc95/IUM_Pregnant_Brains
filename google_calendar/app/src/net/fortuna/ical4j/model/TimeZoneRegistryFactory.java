// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.util.Properties;
import net.fortuna.ical4j.util.Configurator;

// Referenced classes of package net.fortuna.ical4j.model:
//            DefaultTimeZoneRegistryFactory, TimeZoneRegistry

public abstract class TimeZoneRegistryFactory
{

    public static TimeZoneRegistryFactory instance = new DefaultTimeZoneRegistryFactory();

    public TimeZoneRegistryFactory()
    {
    }

    public abstract TimeZoneRegistry createRegistry();

    static 
    {
        String s1 = Configurator.CONFIG.getProperty("net.fortuna.ical4j.timezone.registry");
        String s;
        s = s1;
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        s = System.getProperty("net.fortuna.ical4j.timezone.registry");
        instance = (TimeZoneRegistryFactory)Class.forName(s).newInstance();
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
    }
}
