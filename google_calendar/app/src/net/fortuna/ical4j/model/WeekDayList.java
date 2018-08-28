// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import net.fortuna.ical4j.util.CompatibilityHints;

// Referenced classes of package net.fortuna.ical4j.model:
//            WeekDay

public final class WeekDayList extends ArrayList
    implements Serializable
{

    public static final long serialVersionUID = 0x1140f4a76a5aae5dL;

    public WeekDayList()
    {
    }

    public WeekDayList(String s)
    {
        boolean flag = CompatibilityHints.isHintEnabled("ical4j.compatibility.outlook");
        for (s = new StringTokenizer(s, ","); s.hasMoreTokens();)
        {
            if (flag)
            {
                add(new WeekDay(s.nextToken().replaceAll(" ", "")));
            } else
            {
                add(new WeekDay(s.nextToken()));
            }
        }

    }

    public final boolean add(Object obj)
    {
        if (!(obj instanceof WeekDay))
        {
            obj = String.valueOf(net/fortuna/ical4j/model/WeekDay.getName());
            if (((String) (obj)).length() != 0)
            {
                obj = "Argument not a ".concat(((String) (obj)));
            } else
            {
                obj = new String("Argument not a ");
            }
            throw new IllegalArgumentException(((String) (obj)));
        } else
        {
            return super.add(obj);
        }
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator = iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuffer.append(iterator.next());
            if (iterator.hasNext())
            {
                stringbuffer.append(',');
            }
        } while (true);
        return stringbuffer.toString();
    }
}
