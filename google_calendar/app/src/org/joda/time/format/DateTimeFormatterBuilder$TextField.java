// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

// Referenced classes of package org.joda.time.format:
//            DateTimeParser, DateTimePrinter, DateTimeParserBucket

final class iShort
    implements DateTimeParser, DateTimePrinter
{

    private static Map cParseCache = new HashMap();
    private final DateTimeFieldType iFieldType;
    private final boolean iShort;

    public final int estimateParsedLength()
    {
        return !iShort ? 20 : 6;
    }

    public final int estimatePrintedLength()
    {
        return !iShort ? 20 : 6;
    }

    public final int parseInto(DateTimeParserBucket datetimeparserbucket, String s, int i)
    {
        Locale locale = datetimeparserbucket.iLocale;
        Map map = cParseCache;
        map;
        JVM INSTR monitorenter ;
        Object obj = (Map)cParseCache.get(locale);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_56;
        }
        obj = new HashMap();
        cParseCache.put(locale, obj);
        Object obj1 = ((Object) ((Object[])((Map) (obj)).get(iFieldType)));
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        Object obj2;
        DateTimeFieldType datetimefieldtype;
        obj1 = new HashSet(32);
        obj2 = new MutableDateTime(0L, DateTimeZone.UTC);
        datetimefieldtype = iFieldType;
        if (datetimefieldtype != null)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        datetimeparserbucket;
        map;
        JVM INSTR monitorexit ;
        throw datetimeparserbucket;
        int j;
        int l;
        DateTimeField datetimefield = datetimefieldtype.getField(((BaseDateTime) (obj2)).iChronology);
        if (!datetimefield.isSupported())
        {
            datetimeparserbucket = String.valueOf(datetimefieldtype);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(datetimeparserbucket).length() + 25)).append("Field '").append(datetimeparserbucket).append("' is not supported").toString());
        }
        obj2 = new org.joda.time.tField.iFieldType(((MutableDateTime) (obj2)), datetimefield);
        j = ((AbstractReadableInstantFieldProperty) (obj2)).getField().getMinimumValue();
        l = ((AbstractReadableInstantFieldProperty) (obj2)).getField().getMaximumValue();
        if (l - j <= 32)
        {
            break MISSING_BLOCK_LABEL_246;
        }
        map;
        JVM INSTR monitorexit ;
        return ~i;
        int k = ((AbstractReadableInstantFieldProperty) (obj2)).getField().getMaximumTextLength(locale);
_L4:
        if (j > l)
        {
            break; /* Loop/switch isn't completed */
        }
        ((org.joda.time.roperty.getField) (obj2)).getField.setMillis(((AbstractReadableInstantFieldProperty) (obj2)).getField().set(((BaseDateTime) (((org.joda.time.roperty.getField) (obj2)).getField)).iMillis, j));
        MutableDateTime mutabledatetime = ((org.joda.time.roperty.getField) (obj2)).getField;
        ((Set) (obj1)).add(((AbstractReadableInstantFieldProperty) (obj2)).getField().getAsShortText(((AbstractReadableInstantFieldProperty) (obj2)).getMillis(), locale));
        ((Set) (obj1)).add(((AbstractReadableInstantFieldProperty) (obj2)).getField().getAsShortText(((AbstractReadableInstantFieldProperty) (obj2)).getMillis(), locale).toLowerCase(locale));
        ((Set) (obj1)).add(((AbstractReadableInstantFieldProperty) (obj2)).getField().getAsShortText(((AbstractReadableInstantFieldProperty) (obj2)).getMillis(), locale).toUpperCase(locale));
        ((Set) (obj1)).add(((AbstractReadableInstantFieldProperty) (obj2)).getField().getAsText(((AbstractReadableInstantFieldProperty) (obj2)).getMillis(), locale));
        ((Set) (obj1)).add(((AbstractReadableInstantFieldProperty) (obj2)).getField().getAsText(((AbstractReadableInstantFieldProperty) (obj2)).getMillis(), locale).toLowerCase(locale));
        ((Set) (obj1)).add(((AbstractReadableInstantFieldProperty) (obj2)).getField().getAsText(((AbstractReadableInstantFieldProperty) (obj2)).getMillis(), locale).toUpperCase(locale));
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        j = k;
        if (!"en".equals(locale.getLanguage()))
        {
            break MISSING_BLOCK_LABEL_539;
        }
        j = k;
        if (iFieldType != DateTimeFieldType.ERA_TYPE)
        {
            break MISSING_BLOCK_LABEL_539;
        }
        ((Set) (obj1)).add("BCE");
        ((Set) (obj1)).add("bce");
        ((Set) (obj1)).add("CE");
        ((Set) (obj1)).add("ce");
        j = 3;
        ((Map) (obj)).put(iFieldType, ((Object) (new Object[] {
            obj1, Integer.valueOf(j)
        })));
        obj = obj1;
_L8:
        map;
        JVM INSTR monitorexit ;
        j = Math.min(s.length(), j + i);
_L6:
        if (j <= i)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = s.substring(i, j);
        if (((Set) (obj)).contains(obj1))
        {
            datetimeparserbucket.saveField(new it>(iFieldType.getField(datetimeparserbucket.iChrono), ((String) (obj1)), locale));
            return j;
        }
        j--;
        continue; /* Loop/switch isn't completed */
_L2:
        obj = (Set)obj1[0];
        j = ((Integer)obj1[1]).intValue();
        continue; /* Loop/switch isn't completed */
        if (true) goto _L6; else goto _L5
_L5:
        return ~i;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void printTo(StringBuffer stringbuffer, long l, Chronology chronology, int i, DateTimeZone datetimezone, Locale locale)
    {
        chronology = iFieldType.getField(chronology);
        if (!iShort)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        chronology = chronology.getAsShortText(l, locale);
_L1:
        stringbuffer.append(chronology);
        return;
        try
        {
            chronology = chronology.getAsText(l, locale);
        }
        // Misplaced declaration of an exception variable
        catch (Chronology chronology)
        {
            stringbuffer.append('\uFFFD');
            return;
        }
          goto _L1
    }


    (DateTimeFieldType datetimefieldtype, boolean flag)
    {
        iFieldType = datetimefieldtype;
        iShort = flag;
    }
}
