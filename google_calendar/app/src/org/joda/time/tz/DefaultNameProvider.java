// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.tz;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.DateTimeUtils;

// Referenced classes of package org.joda.time.tz:
//            NameProvider

public final class DefaultNameProvider
    implements NameProvider
{

    private HashMap iByLocaleCache;
    private HashMap iByLocaleCache2;

    public DefaultNameProvider()
    {
        iByLocaleCache = new HashMap(7);
        iByLocaleCache2 = new HashMap(7);
    }

    private final String[] getNameSet(Locale locale, String s, String s1)
    {
        Object obj2 = null;
        this;
        JVM INSTR monitorenter ;
        if (locale != null && s != null && s1 != null) goto _L2; else goto _L1
_L1:
        locale = null;
_L14:
        this;
        JVM INSTR monitorexit ;
        return locale;
_L2:
        Object obj = (Map)iByLocaleCache.get(locale);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        HashMap hashmap = iByLocaleCache;
        obj = new HashMap(7);
        hashmap.put(locale, obj);
        Object obj3 = (Map)((Map) (obj)).get(s);
        Object obj1 = obj3;
        if (obj3 != null) goto _L4; else goto _L3
_L3:
        int j;
        obj3 = new HashMap(7);
        ((Map) (obj)).put(s, obj3);
        obj1 = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
        j = obj1.length;
        int i = 0;
_L15:
        if (i >= j) goto _L6; else goto _L5
_L5:
        obj = obj1[i];
        if (obj == null) goto _L8; else goto _L7
_L7:
        if (obj.length < 5 || !s.equals(obj[0])) goto _L8; else goto _L9
_L9:
        obj1 = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
        j = obj1.length;
        i = 0;
_L16:
        locale = obj2;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_220;
        }
        locale = obj1[i];
        if (locale == null)
        {
            break MISSING_BLOCK_LABEL_392;
        }
        if (locale.length < 5 || !s.equals(locale[0]))
        {
            break MISSING_BLOCK_LABEL_392;
        }
        obj1 = obj3;
        if (obj == null) goto _L4; else goto _L10
_L10:
        obj1 = obj3;
        if (locale == null) goto _L4; else goto _L11
_L11:
        ((Map) (obj3)).put(obj[2], new String[] {
            locale[2], locale[1]
        });
        if (!obj[2].equals(obj[4])) goto _L13; else goto _L12
_L12:
        ((Map) (obj3)).put(String.valueOf(obj[4]).concat("-Summer"), new String[] {
            locale[4], locale[3]
        });
        obj1 = obj3;
_L4:
        locale = (String[])((Map) (obj1)).get(s1);
          goto _L14
_L13:
        ((Map) (obj3)).put(obj[4], new String[] {
            locale[4], locale[3]
        });
        obj1 = obj3;
          goto _L4
        locale;
        throw locale;
_L6:
        obj = null;
          goto _L9
_L8:
        i++;
          goto _L15
        i++;
          goto _L16
    }

    public final String getName(Locale locale, String s, String s1)
    {
        locale = getNameSet(locale, s, s1);
        if (locale == null)
        {
            return null;
        } else
        {
            return locale[1];
        }
    }

    public final String[] getNameSet(Locale locale, String s, String s1, boolean flag)
    {
        Object obj1 = null;
        this;
        JVM INSTR monitorenter ;
        if (locale != null && s != null && s1 != null) goto _L2; else goto _L1
_L1:
        locale = null;
_L14:
        this;
        JVM INSTR monitorexit ;
        return locale;
_L2:
        s1 = (Map)iByLocaleCache2.get(locale);
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        HashMap hashmap = iByLocaleCache2;
        s1 = new HashMap(7);
        hashmap.put(locale, s1);
        Object obj2 = (Map)s1.get(s);
        Object obj = obj2;
        if (obj2 != null) goto _L4; else goto _L3
_L3:
        int j;
        obj2 = new HashMap(7);
        s1.put(s, obj2);
        obj = DateTimeUtils.getDateFormatSymbols(Locale.ENGLISH).getZoneStrings();
        j = obj.length;
        int i = 0;
_L15:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_326;
        }
        s1 = obj[i];
        if (s1 == null) goto _L6; else goto _L5
_L5:
        if (s1.length < 5 || !s.equals(s1[0])) goto _L6; else goto _L7
_L7:
        obj = DateTimeUtils.getDateFormatSymbols(locale).getZoneStrings();
        j = obj.length;
        i = 0;
_L16:
        locale = obj1;
        if (i >= j) goto _L9; else goto _L8
_L8:
        locale = obj[i];
        if (locale == null) goto _L11; else goto _L10
_L10:
        if (locale.length < 5 || !s.equals(locale[0])) goto _L11; else goto _L9
_L9:
        obj = obj2;
        if (s1 == null) goto _L4; else goto _L12
_L12:
        obj = obj2;
        if (locale == null) goto _L4; else goto _L13
_L13:
        ((Map) (obj2)).put(Boolean.TRUE, new String[] {
            locale[2], locale[1]
        });
        ((Map) (obj2)).put(Boolean.FALSE, new String[] {
            locale[4], locale[3]
        });
        obj = obj2;
_L4:
        locale = (String[])((Map) (obj)).get(Boolean.valueOf(flag));
          goto _L14
_L6:
        i++;
          goto _L15
_L11:
        i++;
          goto _L16
        locale;
        throw locale;
        s1 = null;
          goto _L7
    }

    public final String getShortName(Locale locale, String s, String s1)
    {
        locale = getNameSet(locale, s, s1);
        if (locale == null)
        {
            return null;
        } else
        {
            return locale[0];
        }
    }
}
