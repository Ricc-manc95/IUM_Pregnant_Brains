// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


abstract class AbstractMapEntry
    implements java.util.Map.Entry
{

    AbstractMapEntry()
    {
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof java.util.Map.Entry)
        {
            obj = (java.util.Map.Entry)obj;
            Object obj1 = getKey();
            Object obj3 = ((java.util.Map.Entry) (obj)).getKey();
            boolean flag;
            if (obj1 == obj3 || obj1 != null && obj1.equals(obj3))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Object obj2 = getValue();
                obj = ((java.util.Map.Entry) (obj)).getValue();
                if (obj2 == obj || obj2 != null && obj2.equals(obj))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public abstract Object getKey();

    public abstract Object getValue();

    public int hashCode()
    {
        int j = 0;
        Object obj = getKey();
        Object obj1 = getValue();
        int i;
        if (obj == null)
        {
            i = 0;
        } else
        {
            i = obj.hashCode();
        }
        if (obj1 != null)
        {
            j = obj1.hashCode();
        }
        return j ^ i;
    }

    public Object setValue(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    public String toString()
    {
        String s = String.valueOf(getKey());
        String s1 = String.valueOf(getValue());
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("=").append(s1).toString();
    }
}
