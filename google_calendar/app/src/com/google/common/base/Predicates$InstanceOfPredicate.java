// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

// Referenced classes of package com.google.common.base:
//            Predicate

public final class clazz
    implements Predicate, Serializable
{

    public static final long serialVersionUID = 0L;
    private final Class clazz;

    public final boolean apply(Object obj)
    {
        return clazz.isInstance(obj);
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof clazz)
        {
            obj = (clazz)obj;
            flag = flag1;
            if (clazz == ((clazz) (obj)).clazz)
            {
                flag = true;
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        return clazz.hashCode();
    }

    public final String toString()
    {
        String s = clazz.getName();
        return (new StringBuilder(String.valueOf(s).length() + 23)).append("Predicates.instanceOf(").append(s).append(")").toString();
    }

    public (Class class1)
    {
        if (class1 == null)
        {
            throw new NullPointerException();
        } else
        {
            clazz = (Class)class1;
            return;
        }
    }
}
