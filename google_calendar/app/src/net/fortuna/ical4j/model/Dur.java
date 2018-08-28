// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import net.fortuna.ical4j.util.Dates;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Date

public final class Dur
    implements Serializable, Comparable
{

    public static final long serialVersionUID = 0x45929432e6235677L;
    private int days;
    private int hours;
    private int minutes;
    private boolean negative;
    private int seconds;
    private int weeks;

    public Dur(int i, int j, int k, int l)
    {
        boolean flag = false;
        super();
        if (i < 0 && i > 0)
        {
            throw new IllegalArgumentException("Invalid duration representation");
        }
        weeks = 0;
        days = Math.abs(i);
        hours = Math.abs(0);
        minutes = Math.abs(0);
        seconds = Math.abs(0);
        if (i < 0)
        {
            flag = true;
        }
        negative = flag;
    }

    public Dur(String s)
    {
        negative = false;
        weeks = 0;
        days = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        Object obj = null;
        StringTokenizer stringtokenizer = new StringTokenizer(s, "+-PWDTHMS", true);
        s = obj;
        do
        {
            String s1;
            if (stringtokenizer.hasMoreTokens())
            {
                s1 = stringtokenizer.nextToken();
                if ("+".equals(s1))
                {
                    negative = false;
                    s = s1;
                    continue;
                }
                if ("-".equals(s1))
                {
                    negative = true;
                    s = s1;
                    continue;
                }
                if (!"P".equals(s1))
                {
                    if ("W".equals(s1))
                    {
                        weeks = Integer.parseInt(s);
                        s = s1;
                        continue;
                    }
                    if ("D".equals(s1))
                    {
                        days = Integer.parseInt(s);
                        s = s1;
                        continue;
                    }
                    if (!"T".equals(s1))
                    {
                        if ("H".equals(s1))
                        {
                            hours = Integer.parseInt(s);
                            s = s1;
                            continue;
                        }
                        if ("M".equals(s1))
                        {
                            minutes = Integer.parseInt(s);
                            s = s1;
                            continue;
                        }
                        if ("S".equals(s1))
                        {
                            seconds = Integer.parseInt(s);
                            s = s1;
                            continue;
                        }
                    }
                }
            } else
            {
                return;
            }
            s = s1;
        } while (true);
    }

    public Dur(Date date, Date date1)
    {
        Object obj;
        int i;
        int j;
        boolean flag;
        if (date.compareTo(date1) > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        negative = flag;
        if (!negative)
        {
            obj = date1;
            date1 = date;
            date = ((Date) (obj));
        }
        if (date1 instanceof net.fortuna.ical4j.model.Date)
        {
            obj = Dates.getCalendarInstance((net.fortuna.ical4j.model.Date)date1);
        } else
        {
            obj = Calendar.getInstance();
        }
        ((Calendar) (obj)).setTime(date1);
        date1 = Calendar.getInstance(((Calendar) (obj)).getTimeZone());
        date1.setTime(date);
        j = date1.get(1) - ((Calendar) (obj)).get(1);
        i = 0;
        for (; j > 0; j = date1.get(1) - ((Calendar) (obj)).get(1))
        {
            ((Calendar) (obj)).add(5, j * 365);
            i += j * 365;
        }

        j = date1.get(6);
        int k = ((Calendar) (obj)).get(6);
        int l = date1.get(11);
        int i1 = ((Calendar) (obj)).get(11);
        int j1 = date1.get(12);
        int k1 = ((Calendar) (obj)).get(12);
        i = (date1.get(13) - ((Calendar) (obj)).get(13)) + ((((j - k) + i) * 24 + (l - i1)) * 60 + (j1 - k1)) * 60;
        seconds = i % 60;
        i /= 60;
        minutes = i % 60;
        i /= 60;
        hours = i % 24;
        days = i / 24;
        weeks = 0;
        if (seconds == 0 && minutes == 0 && hours == 0 && days % 7 == 0)
        {
            weeks = days / 7;
            days = 0;
        }
    }

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
    }

    public final int compareTo(Object obj)
    {
        return compareTo((Dur)obj);
    }

    public final int compareTo(Dur dur)
    {
        if (negative == dur.negative) goto _L2; else goto _L1
_L1:
        if (!negative) goto _L4; else goto _L3
_L3:
        int j = 0x80000000;
_L6:
        return j;
_L4:
        return 0x7fffffff;
_L2:
        int i;
        if (weeks != dur.weeks)
        {
            i = weeks - dur.weeks;
        } else
        if (days != dur.days)
        {
            i = days - dur.days;
        } else
        if (hours != dur.hours)
        {
            i = hours - dur.hours;
        } else
        if (minutes != dur.minutes)
        {
            i = minutes - dur.minutes;
        } else
        {
            i = seconds - dur.seconds;
        }
        j = i;
        if (negative)
        {
            return -i;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Dur)
        {
            return ((Dur)obj).compareTo(this) == 0;
        } else
        {
            return super.equals(obj);
        }
    }

    public final Date getTime(Date date)
    {
        Calendar calendar;
        if (date instanceof net.fortuna.ical4j.model.Date)
        {
            calendar = Dates.getCalendarInstance((net.fortuna.ical4j.model.Date)date);
        } else
        {
            calendar = Calendar.getInstance();
        }
        calendar.setTime(date);
        if (negative)
        {
            calendar.add(3, -weeks);
            calendar.add(7, -days);
            calendar.add(11, -hours);
            calendar.add(12, -minutes);
            calendar.add(13, -seconds);
        } else
        {
            calendar.add(3, weeks);
            calendar.add(7, days);
            calendar.add(11, hours);
            calendar.add(12, minutes);
            calendar.add(13, seconds);
        }
        return calendar.getTime();
    }

    public final int hashCode()
    {
        HashCodeBuilder hashcodebuilder = new HashCodeBuilder();
        hashcodebuilder.iTotal = weeks + hashcodebuilder.iTotal * hashcodebuilder.iConstant;
        hashcodebuilder.iTotal = days + hashcodebuilder.iTotal * hashcodebuilder.iConstant;
        hashcodebuilder.iTotal = hours + hashcodebuilder.iTotal * hashcodebuilder.iConstant;
        hashcodebuilder.iTotal = minutes + hashcodebuilder.iTotal * hashcodebuilder.iConstant;
        hashcodebuilder.iTotal = seconds + hashcodebuilder.iTotal * hashcodebuilder.iConstant;
        boolean flag = negative;
        int j = hashcodebuilder.iTotal;
        int k = hashcodebuilder.iConstant;
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        hashcodebuilder.iTotal = i + j * k;
        return hashcodebuilder.iTotal;
    }

    public final String toString()
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        if (negative)
        {
            stringbuffer.append('-');
        }
        stringbuffer.append('P');
        if (weeks <= 0) goto _L2; else goto _L1
_L1:
        stringbuffer.append(weeks);
        stringbuffer.append('W');
_L4:
        return stringbuffer.toString();
_L2:
        if (days > 0)
        {
            stringbuffer.append(days);
            stringbuffer.append('D');
        }
        if (hours > 0 || minutes > 0 || seconds > 0)
        {
            stringbuffer.append('T');
            if (hours > 0)
            {
                stringbuffer.append(hours);
                stringbuffer.append('H');
            }
            if (minutes > 0)
            {
                stringbuffer.append(minutes);
                stringbuffer.append('M');
            }
            if (seconds > 0)
            {
                stringbuffer.append(seconds);
                stringbuffer.append('S');
            }
        }
        if (hours + minutes + seconds + days + weeks == 0)
        {
            stringbuffer.append("T0S");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
