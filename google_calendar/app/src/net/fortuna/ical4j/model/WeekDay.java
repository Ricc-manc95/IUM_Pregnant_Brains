// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WeekDay
    implements Serializable
{

    private static final WeekDay FR = new WeekDay("FR", 0);
    private static final WeekDay MO = new WeekDay("MO", 0);
    private static final WeekDay SA = new WeekDay("SA", 0);
    private static final WeekDay SU = new WeekDay("SU", 0);
    private static final WeekDay TH = new WeekDay("TH", 0);
    private static final WeekDay TU = new WeekDay("TU", 0);
    private static final WeekDay WE = new WeekDay("WE", 0);
    public static final long serialVersionUID = 0xc2c56c79b110f5b3L;
    private String day;
    public int offset;

    public WeekDay(String s)
    {
        if (s.length() > 2)
        {
            String s1 = s.substring(0, s.length() - 2);
            int i;
            if (s1 != null && s1.charAt(0) == '+')
            {
                i = Integer.parseInt(s1.substring(1));
            } else
            {
                i = Integer.parseInt(s1);
            }
            offset = i;
        } else
        {
            offset = 0;
        }
        day = s.substring(s.length() - 2).toUpperCase();
        if (!SU.day.equals(day) && !MO.day.equals(day) && !TU.day.equals(day) && !WE.day.equals(day) && !TH.day.equals(day) && !FR.day.equals(day) && !SA.day.equals(day))
        {
            s = String.valueOf(day);
            if (s.length() != 0)
            {
                s = "Invalid day: ".concat(s);
            } else
            {
                s = new String("Invalid day: ");
            }
            throw new IllegalArgumentException(s);
        } else
        {
            return;
        }
    }

    private WeekDay(String s, int i)
    {
        day = s;
        offset = 0;
    }

    public WeekDay(WeekDay weekday, int i)
    {
        day = weekday.day;
        offset = 0;
    }

    public static int getCalendarDay(WeekDay weekday)
    {
        byte byte0 = -1;
        if (SU.day.equals(weekday.day))
        {
            byte0 = 1;
        } else
        {
            if (MO.day.equals(weekday.day))
            {
                return 2;
            }
            if (TU.day.equals(weekday.day))
            {
                return 3;
            }
            if (WE.day.equals(weekday.day))
            {
                return 4;
            }
            if (TH.day.equals(weekday.day))
            {
                return 5;
            }
            if (FR.day.equals(weekday.day))
            {
                return 6;
            }
            if (SA.day.equals(weekday.day))
            {
                return 7;
            }
        }
        return byte0;
    }

    public static WeekDay getDay(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
            return SU;

        case 2: // '\002'
            return MO;

        case 3: // '\003'
            return TU;

        case 4: // '\004'
            return WE;

        case 5: // '\005'
            return TH;

        case 6: // '\006'
            return FR;

        case 7: // '\007'
            return SA;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof WeekDay))
        {
            obj = (WeekDay)obj;
            if (ObjectUtils.equals(((WeekDay) (obj)).day, day) && ((WeekDay) (obj)).offset == offset)
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        HashCodeBuilder hashcodebuilder = (new HashCodeBuilder()).append(day);
        hashcodebuilder.iTotal = offset + hashcodebuilder.iTotal * hashcodebuilder.iConstant;
        return hashcodebuilder.iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if (offset != 0)
        {
            stringbuffer.append(offset);
        }
        stringbuffer.append(day);
        return stringbuffer.toString();
    }

}
