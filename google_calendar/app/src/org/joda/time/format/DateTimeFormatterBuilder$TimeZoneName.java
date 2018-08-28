// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.NameProvider;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

final class iParseLookup
    implements DateTimeParser, DateTimePrinter
{

    private final Map iParseLookup;
    private final int iType;

    public final int estimateParsedLength()
    {
        return iType != 1 ? 20 : 4;
    }

    public final int estimatePrintedLength()
    {
        return iType != 1 ? 20 : 4;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        Object obj;
        Map map;
        map = iParseLookup;
        String s1;
        String s2;
        Iterator iterator;
        if (map == null)
        {
            map = DateTimeUtils.cZoneNames;
        }
        s2 = s.substring(i);
        iterator = map.keySet().iterator();
        s = null;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = (String)iterator.next();
        if (!s2.startsWith(s1))
        {
            break MISSING_BLOCK_LABEL_143;
        }
        obj = s1;
        if (s != null)
        {
            if (s1.length() <= s.length())
            {
                break MISSING_BLOCK_LABEL_143;
            }
            obj = s1;
        }
_L4:
        s = ((String) (obj));
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_34;
_L1:
        if (s != null)
        {
            obj = (DateTimeZone)map.get(s);
            datetimeparserbucket.iSavedState = null;
            datetimeparserbucket.iZone = ((DateTimeZone) (obj));
            return s.length() + i;
        } else
        {
            return ~i;
        }
        obj = s;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        l -= i;
        if (datetimezone == null) goto _L2; else goto _L1
_L1:
        iType;
        JVM INSTR tableswitch 0 1: default 36
    //                   0 48
    //                   1 212;
           goto _L2 _L3 _L4
_L2:
        chronology = "";
_L6:
        stringbuffer.append(chronology);
        return;
_L3:
        chronology = locale;
        if (locale == null)
        {
            chronology = Locale.getDefault();
        }
        locale = datetimezone.getNameKey(l);
        if (locale == null)
        {
            chronology = datetimezone.iID;
        } else
        {
            Object obj = DateTimeZone.cNameProvider;
            if (obj instanceof DefaultNameProvider)
            {
                obj = (DefaultNameProvider)obj;
                String s = datetimezone.iID;
                boolean flag;
                if (datetimezone.getOffset(l) == datetimezone.getStandardOffset(l))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                chronology = ((DefaultNameProvider) (obj)).getNameSet(chronology, s, locale, flag);
                if (chronology == null)
                {
                    locale = null;
                } else
                {
                    locale = chronology[1];
                }
            } else
            {
                locale = ((NameProvider) (obj)).getName(chronology, datetimezone.iID, locale);
            }
            chronology = locale;
            if (locale == null)
            {
                chronology = DateTimeZone.printOffset(datetimezone.getOffset(l));
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        chronology = locale;
        if (locale == null)
        {
            chronology = Locale.getDefault();
        }
        locale = datetimezone.getNameKey(l);
        if (locale == null)
        {
            chronology = datetimezone.iID;
        } else
        {
            Object obj1 = DateTimeZone.cNameProvider;
            if (obj1 instanceof DefaultNameProvider)
            {
                obj1 = (DefaultNameProvider)obj1;
                String s1 = datetimezone.iID;
                boolean flag1;
                if (datetimezone.getOffset(l) == datetimezone.getStandardOffset(l))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                chronology = ((DefaultNameProvider) (obj1)).getNameSet(chronology, s1, locale, flag1);
                if (chronology == null)
                {
                    locale = null;
                } else
                {
                    locale = chronology[0];
                }
            } else
            {
                locale = ((NameProvider) (obj1)).getShortName(chronology, datetimezone.iID, locale);
            }
            chronology = locale;
            if (locale == null)
            {
                chronology = DateTimeZone.printOffset(datetimezone.getOffset(l));
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    (int i, Map map)
    {
        iType = i;
        iParseLookup = map;
    }
}
