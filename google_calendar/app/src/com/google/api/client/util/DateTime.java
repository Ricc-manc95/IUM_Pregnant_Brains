// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateTime
    implements Serializable
{

    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Pattern RFC3339_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
    public static final long serialVersionUID = 1L;
    private final boolean dateOnly;
    private final int tzShift;
    public final long value;

    public DateTime(long l)
    {
        this(false, 0L, null);
    }

    public DateTime(long l, int i)
    {
        this(false, l, Integer.valueOf(0));
    }

    public DateTime(boolean flag, long l, Integer integer)
    {
        dateOnly = flag;
        value = l;
        int i;
        if (flag)
        {
            i = 0;
        } else
        if (integer == null)
        {
            i = TimeZone.getDefault().getOffset(l) / 60000;
        } else
        {
            i = integer.intValue();
        }
        tzShift = i;
    }

    private static void appendInt(StringBuilder stringbuilder, int i, int j)
    {
        int k = i;
        if (i < 0)
        {
            stringbuilder.append('-');
            k = -i;
        }
        for (i = k; i > 0;)
        {
            i /= 10;
            j--;
        }

        for (i = 0; i < j; i++)
        {
            stringbuilder.append('0');
        }

        if (k != 0)
        {
            stringbuilder.append(k);
        }
    }

    public static DateTime parseRfc3339(String s)
        throws NumberFormatException
    {
        Matcher matcher;
        String s1;
        int i;
        int k;
        int i1;
        boolean flag;
        boolean flag1;
        int j2;
        int k2;
        int l2;
        matcher = RFC3339_PATTERN.matcher(s);
        if (!matcher.matches())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid date/time format: ".concat(s);
            } else
            {
                s = new String("Invalid date/time format: ");
            }
            throw new NumberFormatException(s);
        }
        j2 = Integer.parseInt(matcher.group(1));
        k2 = Integer.parseInt(matcher.group(2));
        l2 = Integer.parseInt(matcher.group(3));
        if (matcher.group(4) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s1 = matcher.group(9);
        if (s1 != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        i = 0;
        k = 0;
        i1 = 0;
        if (flag1 && !flag)
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Invalid date/time format, cannot specify time zone shift without specifying time: ".concat(s);
            } else
            {
                s = new String("Invalid date/time format, cannot specify time zone shift without specifying time: ");
            }
            throw new NumberFormatException(s);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        int j1;
        int k1;
        int l1;
        j1 = Integer.parseInt(matcher.group(5));
        k1 = Integer.parseInt(matcher.group(6));
        l1 = Integer.parseInt(matcher.group(7));
        i = j1;
        k = k1;
        i1 = l1;
        if (matcher.group(8) == null) goto _L2; else goto _L3
_L3:
        int i2;
        i = Integer.parseInt(matcher.group(8).substring(1));
        k = matcher.group(8).substring(1).length();
        i2 = (int)((double)(float)i / Math.pow(10D, k - 3));
        i1 = l1;
        k = k1;
        i = j1;
_L5:
        s = new GregorianCalendar(GMT);
        s.set(j2, k2 - 1, l2, i, k, i1);
        s.set(14, i2);
        long l3 = s.getTimeInMillis();
        boolean flag2;
        if (flag && flag1)
        {
            int j;
            if (Character.toUpperCase(s1.charAt(0)) == 'Z')
            {
                j = 0;
            } else
            {
                int l = Integer.parseInt(matcher.group(11)) * 60 + Integer.parseInt(matcher.group(12));
                j = l;
                if (matcher.group(10).charAt(0) == '-')
                {
                    j = -l;
                }
                l3 -= (long)j * 60000L;
            }
            s = Integer.valueOf(j);
        } else
        {
            s = null;
        }
        if (!flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        return new DateTime(flag2, l3, s);
_L2:
        i2 = 0;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof DateTime))
            {
                return false;
            }
            obj = (DateTime)obj;
            if (dateOnly != ((DateTime) (obj)).dateOnly || value != ((DateTime) (obj)).value || tzShift != ((DateTime) (obj)).tzShift)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        long l1 = value;
        long l;
        if (dateOnly)
        {
            l = 1L;
        } else
        {
            l = 0L;
        }
        return Arrays.hashCode(new long[] {
            l1, l, (long)tzShift
        });
    }

    public final String toString()
    {
        return toStringRfc3339();
    }

    public final String toStringRfc3339()
    {
        StringBuilder stringbuilder = new StringBuilder();
        GregorianCalendar gregoriancalendar = new GregorianCalendar(GMT);
        gregoriancalendar.setTimeInMillis(value + (long)tzShift * 60000L);
        appendInt(stringbuilder, gregoriancalendar.get(1), 4);
        stringbuilder.append('-');
        appendInt(stringbuilder, gregoriancalendar.get(2) + 1, 2);
        stringbuilder.append('-');
        appendInt(stringbuilder, gregoriancalendar.get(5), 2);
        if (!dateOnly)
        {
            stringbuilder.append('T');
            appendInt(stringbuilder, gregoriancalendar.get(11), 2);
            stringbuilder.append(':');
            appendInt(stringbuilder, gregoriancalendar.get(12), 2);
            stringbuilder.append(':');
            appendInt(stringbuilder, gregoriancalendar.get(13), 2);
            if (gregoriancalendar.isSet(14))
            {
                stringbuilder.append('.');
                appendInt(stringbuilder, gregoriancalendar.get(14), 3);
            }
            if (tzShift == 0)
            {
                stringbuilder.append('Z');
            } else
            {
                int i = tzShift;
                if (tzShift > 0)
                {
                    stringbuilder.append('+');
                } else
                {
                    stringbuilder.append('-');
                    i = -i;
                }
                appendInt(stringbuilder, i / 60, 2);
                stringbuilder.append(':');
                appendInt(stringbuilder, i % 60, 2);
            }
        }
        return stringbuilder.toString();
    }

}
