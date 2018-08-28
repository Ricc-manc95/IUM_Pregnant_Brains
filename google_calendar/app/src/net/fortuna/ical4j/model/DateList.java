// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import net.fortuna.ical4j.model.parameter.Value;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Parameter, Date, DateTime, TimeZone

public final class DateList
    implements Serializable, List
{

    public static final long serialVersionUID = 0xcca3e535192cf23bL;
    private final List dates;
    public TimeZone timeZone;
    public final Value type;
    public boolean utc;

    public DateList()
    {
        this(false);
    }

    public DateList(String s, Value value, TimeZone timezone)
        throws ParseException
    {
        this(value, timezone);
        for (s = new StringTokenizer(s, ","); s.hasMoreTokens();)
        {
            if (Value.DATE.equals(type))
            {
                add(new Date(s.nextToken()));
            } else
            {
                add(new DateTime(s.nextToken(), timezone));
            }
        }

    }

    public DateList(Value value)
    {
        this(value, null);
    }

    private DateList(Value value, TimeZone timezone)
    {
        if (value == null)
        {
            type = Value.DATE_TIME;
        } else
        {
            type = value;
        }
        timeZone = timezone;
        dates = new ArrayList();
    }

    private DateList(boolean flag)
    {
        type = Value.DATE_TIME;
        dates = new ArrayList();
    }

    public final void add(int i, Object obj)
    {
        dates.add(i, obj);
    }

    public final boolean add(Object obj)
    {
        if (!(obj instanceof Date))
        {
            obj = String.valueOf(net/fortuna/ical4j/model/Date.getName());
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
            return dates.add(obj);
        }
    }

    public final boolean add(Date date)
    {
        if (!(date instanceof DateTime)) goto _L2; else goto _L1
_L1:
        if (!utc) goto _L4; else goto _L3
_L3:
        ((DateTime)date).setUtc(true);
_L6:
        return add(date);
_L4:
        if (timeZone != null)
        {
            ((DateTime)date).setTimeZone(timeZone);
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        if (!Value.DATE.equals(type))
        {
            date = new DateTime(date);
            date.setTimeZone(timeZone);
            return add(date);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final boolean addAll(int i, Collection collection)
    {
        return dates.addAll(i, collection);
    }

    public final boolean addAll(Collection collection)
    {
        return dates.addAll(collection);
    }

    public final void clear()
    {
        dates.clear();
    }

    public final boolean contains(Object obj)
    {
        return dates.contains(obj);
    }

    public final boolean containsAll(Collection collection)
    {
        return dates.containsAll(collection);
    }

    public final boolean equals(Object obj)
    {
        boolean flag;
        for (flag = false; obj == null || !getClass().isAssignableFrom(obj.getClass());)
        {
            return false;
        }

        obj = (DateList)obj;
        obj = (new EqualsBuilder()).append(dates, ((DateList) (obj)).dates).append(type, ((DateList) (obj)).type).append(timeZone, ((DateList) (obj)).timeZone);
        boolean flag1 = utc;
        boolean flag2 = utc;
        if (((EqualsBuilder) (obj)).isEquals)
        {
            if (flag1 == flag2)
            {
                flag = true;
            }
            obj.isEquals = flag;
        }
        return ((EqualsBuilder) (obj)).isEquals;
    }

    public final Object get(int i)
    {
        return dates.get(i);
    }

    public final int hashCode()
    {
        HashCodeBuilder hashcodebuilder = (new HashCodeBuilder()).append(dates).append(type).append(timeZone);
        boolean flag = utc;
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

    public final int indexOf(Object obj)
    {
        return dates.indexOf(obj);
    }

    public final boolean isEmpty()
    {
        return dates.isEmpty();
    }

    public final Iterator iterator()
    {
        return dates.iterator();
    }

    public final int lastIndexOf(Object obj)
    {
        return dates.lastIndexOf(obj);
    }

    public final ListIterator listIterator()
    {
        return dates.listIterator();
    }

    public final ListIterator listIterator(int i)
    {
        return dates.listIterator(i);
    }

    public final Object remove(int i)
    {
        return dates.remove(i);
    }

    public final boolean remove(Object obj)
    {
        return dates.remove(obj);
    }

    public final boolean removeAll(Collection collection)
    {
        return dates.removeAll(collection);
    }

    public final boolean retainAll(Collection collection)
    {
        return dates.retainAll(collection);
    }

    public final Object set(int i, Object obj)
    {
        return dates.set(i, obj);
    }

    public final void setTimeZone(TimeZone timezone)
    {
        if (!Value.DATE.equals(type))
        {
            for (Iterator iterator1 = iterator(); iterator1.hasNext(); ((DateTime)iterator1.next()).setTimeZone(timezone)) { }
        }
        timeZone = timezone;
        utc = false;
    }

    public final void setUtc(boolean flag)
    {
        if (!Value.DATE.equals(type))
        {
            for (Iterator iterator1 = iterator(); iterator1.hasNext(); ((DateTime)iterator1.next()).setUtc(flag)) { }
        }
        timeZone = null;
        utc = flag;
    }

    public final int size()
    {
        return dates.size();
    }

    public final List subList(int i, int j)
    {
        return dates.subList(i, j);
    }

    public final Object[] toArray()
    {
        return dates.toArray();
    }

    public final Object[] toArray(Object aobj[])
    {
        return dates.toArray(aobj);
    }

    public final String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Iterator iterator1 = iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            stringbuffer.append(iterator1.next());
            if (iterator1.hasNext())
            {
                stringbuffer.append(',');
            }
        } while (true);
        return stringbuffer.toString();
    }
}
