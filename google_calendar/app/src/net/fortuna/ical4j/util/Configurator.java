// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.util;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package net.fortuna.ical4j.util:
//            ResourceLoader

public final class Configurator
{

    public static final Properties CONFIG;
    private static final Log LOG;

    private Configurator()
    {
    }

    static 
    {
        LOG = LogFactory.getLog(net/fortuna/ical4j/util/Configurator);
        CONFIG = new Properties();
        try
        {
            CONFIG.load(ResourceLoader.getResourceAsStream("ical4j.properties"));
        }
        catch (Exception exception)
        {
            LOG.info("ical4j.properties not found.");
        }
    }
}
