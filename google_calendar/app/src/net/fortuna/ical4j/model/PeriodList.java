// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// Referenced classes of package net.fortuna.ical4j.model:
//            Period, TimeZone

public final class PeriodList
    implements Serializable, Set
{

    public static final long serialVersionUID = 0xdfd646a48679c0c4L;
    private final Set periods;
    public TimeZone timezone;
    public final boolean unmodifiable;
    public boolean utc;

    public PeriodList()
    {
        this(true);
    }

    public PeriodList(String s)
        throws ParseException
    {
        this();
        for (s = new StringTokenizer(s, ","); s.hasMoreTokens(); add(new Period(s.nextToken()))) { }
    }

    private PeriodList(boolean flag)
    {
        this(true, false);
    }

    public PeriodList(boolean flag, boolean flag1)
    {
        utc = flag;
        unmodifiable = flag1;
        if (flag1)
        {
            periods = Collections.EMPTY_SET;
            return;
        } else
        {
            periods = new TreeSet();
            return;
        }
    }

    public final boolean add(Object obj)
    {
        if (!(obj instanceof Period))
        {
            obj = String.valueOf(net/fortuna/ical4j/model/Period.getName());
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
            return periods.add(obj);
        }
    }

    public final boolean addAll(Collection collection)
    {
        for (collection = collection.iterator(); collection.hasNext(); add(collection.next())) { }
        return true;
    }

    public final void clear()
    {
        periods.clear();
    }

    public final boolean contains(Object obj)
    {
        return periods.contains(obj);
    }

    public final boolean containsAll(Collection collection)
    {
        return periods.containsAll(collection);
    }

    public final boolean equals(Object obj)
    {
        boolean flag;
        for (flag = false; obj == null || !getClass().isAssignableFrom(obj.getClass());)
        {
            return false;
        }

        obj = (PeriodList)obj;
        obj = (new EqualsBuilder()).append(periods, ((PeriodList) (obj)).periods).append(timezone, ((PeriodList) (obj)).timezone);
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

    public final int hashCode()
    {
        HashCodeBuilder hashcodebuilder = (new HashCodeBuilder()).append(periods).append(timezone);
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

    public final boolean isEmpty()
    {
        return periods.isEmpty();
    }

    public final Iterator iterator()
    {
        return periods.iterator();
    }

    public final boolean remove(Object obj)
    {
        return periods.remove(obj);
    }

    public final boolean removeAll(Collection collection)
    {
        return periods.removeAll(collection);
    }

    public final boolean retainAll(Collection collection)
    {
        return periods.retainAll(collection);
    }

    public final int size()
    {
        return periods.size();
    }

    public final Object[] toArray()
    {
        return periods.toArray();
    }

    public final Object[] toArray(Object aobj[])
    {
        return periods.toArray(aobj);
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
            stringbuffer.append(iterator1.next().toString());
            if (iterator1.hasNext())
            {
                stringbuffer.append(',');
            }
        } while (true);
        return stringbuffer.toString();
    }
}
