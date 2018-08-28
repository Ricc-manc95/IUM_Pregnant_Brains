// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package net.fortuna.ical4j.util:
//            Configurator

public final class CompatibilityHints
{

    public static final Map HINTS;

    public static boolean isHintEnabled(String s)
    {
        if (HINTS.get(s) != null)
        {
            return ((Boolean)HINTS.get(s)).booleanValue();
        }
        String s2 = Configurator.CONFIG.getProperty(s);
        String s1 = s2;
        if (s2 == null)
        {
            s1 = System.getProperty(s);
        }
        return "true".equals(s1);
    }

    static 
    {
        HINTS = new ConcurrentHashMap();
        String s1 = Configurator.CONFIG.getProperty("ical4j.unfolding.relaxed");
        String s = s1;
        if (s1 == null)
        {
            s = System.getProperty("ical4j.unfolding.relaxed");
        }
        boolean flag = "true".equals(s);
        HINTS.put("ical4j.unfolding.relaxed", Boolean.valueOf(flag));
        s1 = Configurator.CONFIG.getProperty("ical4j.parsing.relaxed");
        s = s1;
        if (s1 == null)
        {
            s = System.getProperty("ical4j.parsing.relaxed");
        }
        flag = "true".equals(s);
        HINTS.put("ical4j.parsing.relaxed", Boolean.valueOf(flag));
        s1 = Configurator.CONFIG.getProperty("ical4j.validation.relaxed");
        s = s1;
        if (s1 == null)
        {
            s = System.getProperty("ical4j.validation.relaxed");
        }
        flag = "true".equals(s);
        HINTS.put("ical4j.validation.relaxed", Boolean.valueOf(flag));
        s1 = Configurator.CONFIG.getProperty("ical4j.compatibility.outlook");
        s = s1;
        if (s1 == null)
        {
            s = System.getProperty("ical4j.compatibility.outlook");
        }
        flag = "true".equals(s);
        HINTS.put("ical4j.compatibility.outlook", Boolean.valueOf(flag));
        s1 = Configurator.CONFIG.getProperty("ical4j.compatibility.notes");
        s = s1;
        if (s1 == null)
        {
            s = System.getProperty("ical4j.compatibility.notes");
        }
        flag = "true".equals(s);
        HINTS.put("ical4j.compatibility.notes", Boolean.valueOf(flag));
    }
}
