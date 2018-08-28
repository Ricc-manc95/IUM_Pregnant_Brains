// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.Arrays;

public final class className
{

    private final String className;
    private final ValueHolder holderHead = new ValueHolder();
    public ValueHolder holderTail;
    public boolean omitNullValues;

    public final className add(String s, int i)
    {
        ValueHolder valueholder = new ValueHolder();
        holderTail.next = valueholder;
        holderTail = valueholder;
        valueholder.value = String.valueOf(i);
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)s;
            return this;
        }
    }

    public final ValueHolder.name add(String s, long l)
    {
        ValueHolder valueholder = new ValueHolder();
        holderTail.next = valueholder;
        holderTail = valueholder;
        valueholder.value = String.valueOf(l);
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)s;
            return this;
        }
    }

    public final ValueHolder.name add(String s, Object obj)
    {
        ValueHolder valueholder = new ValueHolder();
        holderTail.next = valueholder;
        holderTail = valueholder;
        valueholder.value = obj;
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)s;
            return this;
        }
    }

    public final ValueHolder.name add(String s, boolean flag)
    {
        ValueHolder valueholder = new ValueHolder();
        holderTail.next = valueholder;
        holderTail = valueholder;
        valueholder.value = String.valueOf(flag);
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            valueholder.name = (String)s;
            return this;
        }
    }

    public final ValueHolder.name addValue(Object obj)
    {
        ValueHolder valueholder = new ValueHolder();
        holderTail.next = valueholder;
        holderTail = valueholder;
        valueholder.value = obj;
        return this;
    }

    public final String toString()
    {
        boolean flag = omitNullValues;
        StringBuilder stringbuilder = (new StringBuilder(32)).append(className).append('{');
        ValueHolder valueholder = holderHead.next;
        String s = "";
        while (valueholder != null) 
        {
label0:
            {
                Object obj = valueholder.value;
                String s1;
                if (flag)
                {
                    s1 = s;
                    if (obj == null)
                    {
                        break label0;
                    }
                }
                stringbuilder.append(s);
                s1 = ", ";
                if (valueholder.name != null)
                {
                    stringbuilder.append(valueholder.name).append('=');
                }
                if (obj != null && obj.getClass().isArray())
                {
                    s = Arrays.deepToString(new Object[] {
                        obj
                    });
                    stringbuilder.append(s, 1, s.length() - 1);
                } else
                {
                    stringbuilder.append(obj);
                }
            }
            valueholder = valueholder.next;
            s = s1;
        }
        return stringbuilder.append('}').toString();
    }

    public ValueHolder(String s)
    {
        class ValueHolder
        {

            public String name;
            public ValueHolder next;
            public Object value;

            public ValueHolder()
            {
            }
        }

        holderTail = holderHead;
        omitNullValues = false;
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            className = (String)s;
            return;
        }
    }
}
