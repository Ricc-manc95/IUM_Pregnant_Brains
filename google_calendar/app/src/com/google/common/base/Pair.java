// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

public final class Pair
    implements Serializable
{

    public static final long serialVersionUID = 0xa60d052d8237f63L;
    public final Object first;
    public final Object second;

    public Pair(Object obj, Object obj1)
    {
        first = obj;
        second = obj1;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof Pair)
        {
            obj = (Pair)obj;
            Object obj1 = first;
            Object obj3 = ((Pair) (obj)).first;
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
                Object obj2 = second;
                obj = ((Pair) (obj)).second;
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

    public final int hashCode()
    {
        int j = 0;
        int i;
        if (first == null)
        {
            i = 0;
        } else
        {
            i = first.hashCode();
        }
        if (second != null)
        {
            j = second.hashCode();
        }
        return i * 31 + j;
    }

    public final String toString()
    {
        String s = String.valueOf(first);
        String s1 = String.valueOf(second);
        return (new StringBuilder(String.valueOf(s).length() + 4 + String.valueOf(s1).length())).append("(").append(s).append(", ").append(s1).append(")").toString();
    }
}
