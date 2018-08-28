// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.TzId;
import net.fortuna.ical4j.model.property.TzUrl;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.Configurator;
import net.fortuna.ical4j.util.ResourceLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package net.fortuna.ical4j.model:
//            TimeZoneRegistry, Component, PropertyList, Calendar, 
//            ComponentList, Content, TimeZone

public class TimeZoneRegistryImpl
    implements TimeZoneRegistry
{

    private static final Properties ALIASES;
    private static final Map DEFAULT_TIMEZONES = new ConcurrentHashMap();
    private static final Pattern TZ_ID_SUFFIX = Pattern.compile("(?<=/)[^/]*/[^/]*$");
    private String resourcePrefix;
    private Map timezones;

    public TimeZoneRegistryImpl()
    {
        this("zoneinfo/");
    }

    private TimeZoneRegistryImpl(String s)
    {
        resourcePrefix = s;
        timezones = new ConcurrentHashMap();
    }

    private static VTimeZone updateDefinition(VTimeZone vtimezone)
    {
        Object obj;
        obj = (TzUrl)((Component) (vtimezone)).properties.getProperty("TZURL");
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        obj = (VTimeZone)(new CalendarBuilder()).build(new UnfoldingReader(new InputStreamReader(((TzUrl) (obj)).uri.toURL().openStream(), CalendarBuilder.DEFAULT_CHARSET))).components.getComponent("VTIMEZONE");
        if (obj != null)
        {
            return ((VTimeZone) (obj));
        }
        break MISSING_BLOCK_LABEL_120;
        Exception exception;
        exception;
        Log log = LogFactory.getLog(net/fortuna/ical4j/model/TimeZoneRegistryImpl);
        String s = String.valueOf(((TzId)((Component) (vtimezone)).properties.getProperty("TZID")).getValue());
        if (s.length() != 0)
        {
            s = "Unable to retrieve updates for timezone: ".concat(s);
        } else
        {
            s = new String("Unable to retrieve updates for timezone: ");
        }
        log.warn(s, exception);
        return vtimezone;
    }

    public final void clear()
    {
        timezones.clear();
    }

    public final TimeZone getTimeZone(String s)
    {
        Object obj;
        obj = s;
        do
        {
            TimeZone timezone = (TimeZone)timezones.get(obj);
            s = timezone;
            if (timezone != null)
            {
                break;
            }
            timezone = (TimeZone)DEFAULT_TIMEZONES.get(obj);
            s = timezone;
            if (timezone != null)
            {
                break;
            }
label0:
            {
                s = ALIASES.getProperty(((String) (obj)));
                if (s == null)
                {
                    break label0;
                }
                obj = s;
            }
        } while (true);
        if (false) goto _L2; else goto _L1
_L2:
        Map map = DEFAULT_TIMEZONES;
        map;
        JVM INSTR monitorenter ;
        TimeZone timezone1 = (TimeZone)DEFAULT_TIMEZONES.get(obj);
        s = timezone1;
        if (timezone1 != null) goto _L4; else goto _L3
_L3:
        s = resourcePrefix;
        s = ResourceLoader.getResource((new StringBuilder(String.valueOf(s).length() + 4 + String.valueOf(obj).length())).append(s).append(((String) (obj))).append(".ics").toString());
        if (s == null) goto _L6; else goto _L5
_L5:
        VTimeZone vtimezone;
        String s1;
        vtimezone = (VTimeZone)(new CalendarBuilder()).build(new UnfoldingReader(new InputStreamReader(s.openStream(), CalendarBuilder.DEFAULT_CHARSET))).components.getComponent("VTIMEZONE");
        s1 = Configurator.CONFIG.getProperty("net.fortuna.ical4j.timezone.update.enabled");
        s = s1;
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_206;
        }
        s = System.getProperty("net.fortuna.ical4j.timezone.update.enabled");
        if ("false".equals(s)) goto _L8; else goto _L7
_L7:
        s = updateDefinition(vtimezone);
_L11:
        if (s == null) goto _L10; else goto _L9
_L9:
        s = new TimeZone(s);
        DEFAULT_TIMEZONES.put(s.getID(), s);
_L4:
        map;
        JVM INSTR monitorexit ;
_L1:
        return s;
_L8:
        s = vtimezone;
          goto _L11
_L6:
        s = null;
          goto _L11
_L10:
        s = timezone1;
        if (!CompatibilityHints.isHintEnabled("ical4j.parsing.relaxed")) goto _L4; else goto _L12
_L12:
        obj = TZ_ID_SUFFIX.matcher(((CharSequence) (obj)));
        s = timezone1;
        if (!((Matcher) (obj)).find()) goto _L4; else goto _L13
_L13:
        s = getTimeZone(((Matcher) (obj)).group());
        map;
        JVM INSTR monitorexit ;
        return s;
        s;
        map;
        JVM INSTR monitorexit ;
        throw s;
        Exception exception;
        exception;
        s = timezone1;
_L14:
        LogFactory.getLog(net/fortuna/ical4j/model/TimeZoneRegistryImpl).warn("Error occurred loading VTimeZone", exception);
          goto _L4
        exception;
          goto _L14
    }

    public final void register(TimeZone timezone)
    {
        timezones.put(timezone.getID(), timezone);
    }

    static 
    {
        ALIASES = new Properties();
        try
        {
            ALIASES.load(ResourceLoader.getResourceAsStream("net/fortuna/ical4j/model/tz.alias"));
        }
        catch (IOException ioexception)
        {
            Log log = LogFactory.getLog(net/fortuna/ical4j/model/TimeZoneRegistryImpl);
            String s = String.valueOf(ioexception.getMessage());
            if (s.length() != 0)
            {
                s = "Error loading timezone aliases: ".concat(s);
            } else
            {
                s = new String("Error loading timezone aliases: ");
            }
            log.warn(s);
        }
        String s1;
        Log log1;
        try
        {
            ALIASES.load(ResourceLoader.getResourceAsStream("tz.alias"));
            return;
        }
        catch (Exception exception)
        {
            log1 = LogFactory.getLog(net/fortuna/ical4j/model/TimeZoneRegistryImpl);
            s1 = String.valueOf(exception.getMessage());
        }
        if (s1.length() != 0)
        {
            s1 = "Error loading custom timezone aliases: ".concat(s1);
        } else
        {
            s1 = new String("Error loading custom timezone aliases: ");
        }
        log1.debug(s1);
    }
}
