// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.joda.time.format;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.base.AbstractDateTime;

// Referenced classes of package org.joda.time.format:
//            DateTimeFormatter, DateTimeFormatterBuilder

public final class DateTimeFormat
{

    private static final ConcurrentHashMap PATTERN_CACHE = new ConcurrentHashMap();

    public static DateTimeFormatter forPattern(String s)
    {
        Object obj;
        int ai[];
        int i;
        int l;
        if (s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter datetimeformatter = (DateTimeFormatter)PATTERN_CACHE.get(s);
        obj = datetimeformatter;
        if (datetimeformatter != null)
        {
            break MISSING_BLOCK_LABEL_1518;
        }
        obj = new DateTimeFormatterBuilder();
        l = s.length();
        ai = new int[1];
        i = 0;
_L23:
        Object obj1;
        int k;
        int i1;
        char c;
        if (i >= l)
        {
            break MISSING_BLOCK_LABEL_1485;
        }
        ai[0] = i;
        obj1 = parseToken(s, ai);
        i1 = ai[0];
        k = ((String) (obj1)).length();
        if (k == 0)
        {
            break MISSING_BLOCK_LABEL_1485;
        }
        c = ((String) (obj1)).charAt(0);
        c;
        JVM INSTR lookupswitch 22: default 288
    //                   39: 1403
    //                   67: 376
    //                   68: 1169
    //                   69: 1055
    //                   71: 316
    //                   72: 956
    //                   75: 984
    //                   77: 740
    //                   83: 1026
    //                   89: 391
    //                   90: 1275
    //                   97: 888
    //                   100: 874
    //                   101: 1041
    //                   104: 942
    //                   107: 970
    //                   109: 998
    //                   115: 1012
    //                   119: 1183
    //                   120: 391
    //                   121: 391
    //                   122: 1197;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L11 _L11 _L21
_L2:
        break MISSING_BLOCK_LABEL_1403;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        s = String.valueOf(obj1);
        DateTimeFormatterBuilder.TimeZoneId timezoneid;
        int j;
        boolean flag;
        boolean flag1;
        if (s.length() != 0)
        {
            s = "Illegal pattern component: ".concat(s);
        } else
        {
            s = new String("Illegal pattern component: ");
        }
        throw new IllegalArgumentException(s);
_L6:
        obj1 = DateTimeFieldType.ERA_TYPE;
        if (obj1 == null)
        {
            throw new IllegalArgumentException("Field type must not be null");
        }
        obj1 = new DateTimeFormatterBuilder.TextField(((DateTimeFieldType) (obj1)), false);
        obj.iFormatter = null;
        ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
_L24:
        i = i1 + 1;
        if (true) goto _L23; else goto _L22
_L22:
        ((DateTimeFormatterBuilder) (obj)).appendSignedDecimal(DateTimeFieldType.CENTURY_OF_ERA_TYPE, k, k);
          goto _L24
_L11:
        if (k == 2)
        {
            flag = true;
            flag1 = true;
            if (i1 + 1 < l)
            {
                ai[0] = ai[0] + 1;
                flag = flag1;
                if (isNumericToken(parseToken(s, ai)))
                {
                    flag = false;
                }
                ai[0] = ai[0] - 1;
            }
            switch (c)
            {
            default:
                obj1 = new DateTime();
                i = ((AbstractDateTime) (obj1)).getChronology().year().get(((AbstractDateTime) (obj1)).getMillis());
                obj1 = new DateTimeFormatterBuilder.TwoDigitYear(DateTimeFieldType.YEAR_TYPE, i - 30, flag);
                obj.iFormatter = null;
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
                break;

            case 120: // 'x'
                obj1 = new DateTime();
                i = ((AbstractDateTime) (obj1)).getChronology().weekyear().get(((AbstractDateTime) (obj1)).getMillis());
                obj1 = new DateTimeFormatterBuilder.TwoDigitYear(DateTimeFieldType.WEEKYEAR_TYPE, i - 30, flag);
                obj.iFormatter = null;
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
                break;
            }
        } else
        {
            i = 9;
            j = i;
            if (i1 + 1 < l)
            {
                ai[0] = ai[0] + 1;
                if (isNumericToken(parseToken(s, ai)))
                {
                    i = k;
                }
                ai[0] = ai[0] - 1;
                j = i;
            }
            switch (c)
            {
            case 89: // 'Y'
                ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.YEAR_OF_ERA_TYPE, k, j);
                break;

            case 120: // 'x'
                ((DateTimeFormatterBuilder) (obj)).appendSignedDecimal(DateTimeFieldType.WEEKYEAR_TYPE, k, j);
                break;

            case 121: // 'y'
                ((DateTimeFormatterBuilder) (obj)).appendSignedDecimal(DateTimeFieldType.YEAR_TYPE, k, j);
                break;
            }
        }
        if (true) goto _L24; else goto _L25
_L25:
_L9:
        if (k >= 3)
        {
            if (k >= 4)
            {
                obj1 = DateTimeFieldType.MONTH_OF_YEAR_TYPE;
                if (obj1 == null)
                {
                    throw new IllegalArgumentException("Field type must not be null");
                }
                obj1 = new DateTimeFormatterBuilder.TextField(((DateTimeFieldType) (obj1)), false);
                obj.iFormatter = null;
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            } else
            {
                obj1 = DateTimeFieldType.MONTH_OF_YEAR_TYPE;
                if (obj1 == null)
                {
                    throw new IllegalArgumentException("Field type must not be null");
                }
                obj1 = new DateTimeFormatterBuilder.TextField(((DateTimeFieldType) (obj1)), true);
                obj.iFormatter = null;
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
                ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            }
        } else
        {
            ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.MONTH_OF_YEAR_TYPE, k, 2);
        }
          goto _L24
_L14:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.DAY_OF_MONTH_TYPE, k, 2);
          goto _L24
_L13:
        obj1 = DateTimeFieldType.HALFDAY_OF_DAY_TYPE;
        if (obj1 == null)
        {
            throw new IllegalArgumentException("Field type must not be null");
        }
        obj1 = new DateTimeFormatterBuilder.TextField(((DateTimeFieldType) (obj1)), false);
        obj.iFormatter = null;
        ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
          goto _L24
_L16:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE, k, 2);
          goto _L24
_L7:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.HOUR_OF_DAY_TYPE, k, 2);
          goto _L24
_L17:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE, k, 2);
          goto _L24
_L8:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.HOUR_OF_HALFDAY_TYPE, k, 2);
          goto _L24
_L18:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.MINUTE_OF_HOUR_TYPE, k, 2);
          goto _L24
_L19:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.SECOND_OF_MINUTE_TYPE, k, 2);
          goto _L24
_L10:
        ((DateTimeFormatterBuilder) (obj)).appendFraction(DateTimeFieldType.SECOND_OF_DAY_TYPE, k, k);
          goto _L24
_L15:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.DAY_OF_WEEK_TYPE, k, 1);
          goto _L24
_L5:
        if (k >= 4)
        {
            obj1 = DateTimeFieldType.DAY_OF_WEEK_TYPE;
            if (obj1 == null)
            {
                throw new IllegalArgumentException("Field type must not be null");
            }
            obj1 = new DateTimeFormatterBuilder.TextField(((DateTimeFieldType) (obj1)), false);
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        } else
        {
            obj1 = DateTimeFieldType.DAY_OF_WEEK_TYPE;
            if (obj1 == null)
            {
                throw new IllegalArgumentException("Field type must not be null");
            }
            obj1 = new DateTimeFormatterBuilder.TextField(((DateTimeFieldType) (obj1)), true);
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        }
          goto _L24
_L4:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.DAY_OF_YEAR_TYPE, k, 3);
          goto _L24
_L20:
        ((DateTimeFormatterBuilder) (obj)).appendDecimal(DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE, k, 2);
          goto _L24
_L21:
        if (k >= 4)
        {
            obj1 = new DateTimeFormatterBuilder.TimeZoneName(0, null);
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(null);
        } else
        {
            obj1 = new DateTimeFormatterBuilder.TimeZoneName(1, null);
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        }
          goto _L24
_L12:
        if (k == 1)
        {
            obj1 = new DateTimeFormatterBuilder.TimeZoneOffset(null, "Z", false, 2, 2);
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        } else
        if (k == 2)
        {
            obj1 = new DateTimeFormatterBuilder.TimeZoneOffset(null, "Z", true, 2, 2);
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        } else
        {
            obj1 = DateTimeFormatterBuilder.TimeZoneId.INSTANCE;
            timezoneid = DateTimeFormatterBuilder.TimeZoneId.INSTANCE;
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(timezoneid);
        }
          goto _L24
        obj1 = ((String) (obj1)).substring(1);
        if (((String) (obj1)).length() == 1)
        {
            obj1 = new DateTimeFormatterBuilder.CharacterLiteral(((String) (obj1)).charAt(0));
            obj.iFormatter = null;
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
            ((DateTimeFormatterBuilder) (obj)).iElementPairs.add(obj1);
        } else
        {
            ((DateTimeFormatterBuilder) (obj)).appendLiteral(new String(((String) (obj1))));
        }
          goto _L24
        DateTimeFormatter datetimeformatter1;
        datetimeformatter1 = ((DateTimeFormatterBuilder) (obj)).toFormatter();
        if (PATTERN_CACHE.size() >= 500)
        {
            break MISSING_BLOCK_LABEL_1520;
        }
        obj = (DateTimeFormatter)PATTERN_CACHE.putIfAbsent(s, datetimeformatter1);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_1520;
        }
        return ((DateTimeFormatter) (obj));
        return datetimeformatter1;
    }

    private static boolean isNumericToken(String s)
    {
        int i;
        boolean flag;
        flag = true;
        i = s.length();
        if (i <= 0) goto _L2; else goto _L1
_L1:
        s.charAt(0);
        JVM INSTR lookupswitch 19: default 180
    //                   67: 182
    //                   68: 182
    //                   70: 182
    //                   72: 182
    //                   75: 182
    //                   77: 184
    //                   83: 182
    //                   87: 182
    //                   89: 182
    //                   99: 182
    //                   100: 182
    //                   101: 182
    //                   104: 182
    //                   107: 182
    //                   109: 182
    //                   115: 182
    //                   119: 182
    //                   120: 182
    //                   121: 182;
           goto _L2 _L3 _L3 _L3 _L3 _L3 _L4 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3 _L3
_L2:
        flag = false;
_L3:
        return flag;
_L4:
        if (i <= 2)
        {
            return true;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    private static String parseToken(String s, int ai[])
    {
        char c;
        StringBuilder stringbuilder;
        int i;
        int l;
        stringbuilder = new StringBuilder();
        i = ai[0];
        l = s.length();
        c = s.charAt(i);
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) goto _L2; else goto _L1
_L1:
        int j;
        stringbuilder.append(c);
        do
        {
            j = i;
            if (i + 1 >= l)
            {
                break;
            }
            j = i;
            if (s.charAt(i + 1) != c)
            {
                break;
            }
            stringbuilder.append(c);
            i++;
        } while (true);
          goto _L3
_L2:
        int k;
        stringbuilder.append('\'');
        k = 0;
_L7:
        j = i;
        if (i >= l) goto _L3; else goto _L4
_L4:
        c = s.charAt(i);
        if (c != '\'') goto _L6; else goto _L5
_L5:
        if (i + 1 < l && s.charAt(i + 1) == '\'')
        {
            i++;
            stringbuilder.append(c);
            j = k;
        } else
        if (k == 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
_L8:
        i++;
        k = j;
          goto _L7
_L6:
        if (k != 0 || (c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))
        {
            break MISSING_BLOCK_LABEL_247;
        }
        j = i - 1;
_L3:
        ai[0] = j;
        return stringbuilder.toString();
        stringbuilder.append(c);
        j = k;
          goto _L8
    }

}
