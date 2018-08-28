// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public final class NumberList extends ArrayList
    implements Serializable
{

    public static final long serialVersionUID = 0xe8dbea243f035b9fL;
    private final boolean allowsNegativeValues;
    private final int maxValue;
    private final int minValue;

    public NumberList()
    {
        this(0x80000000, 0x7fffffff, true);
    }

    public NumberList(int i, int j, boolean flag)
    {
        minValue = i;
        maxValue = j;
        allowsNegativeValues = flag;
    }

    public NumberList(String s, int i, int j, boolean flag)
    {
        this(i, j, flag);
        Object obj;
        for (s = new StringTokenizer(s, ","); s.hasMoreTokens(); add(obj))
        {
            obj = s.nextToken();
            if (obj != null && ((String) (obj)).charAt(0) == '+')
            {
                i = Integer.parseInt(((String) (obj)).substring(1));
            } else
            {
                i = Integer.parseInt(((String) (obj)));
            }
            obj = new Integer(i);
            j = ((Integer) (obj)).intValue();
            i = j;
            if ((j >> 31 | -j >>> 31) < 0)
            {
                if (!allowsNegativeValues)
                {
                    s = String.valueOf(obj);
                    throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 28)).append("Negative value not allowed: ").append(s).toString());
                }
                i = Math.abs(j);
            }
            if (i < minValue || i > maxValue)
            {
                i = minValue;
                j = maxValue;
                s = String.valueOf(obj);
                throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 47)).append("Value not in range [").append(i).append("..").append(j).append("]: ").append(s).toString());
            }
        }

    }

    public final boolean add(Object obj)
    {
        if (!(obj instanceof Integer))
        {
            obj = String.valueOf(java/lang/Integer.getName());
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
