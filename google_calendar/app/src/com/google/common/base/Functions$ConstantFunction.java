// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;

// Referenced classes of package com.google.common.base:
//            Function

public final class value
    implements Function, Serializable
{

    public static final long serialVersionUID = 0L;
    private final Object value;

    public final Object apply(Object obj)
    {
        return value;
    }

    public final boolean equals(Object obj)
    {
        boolean flag;
label0:
        {
            boolean flag1 = false;
            flag = flag1;
            if (!(obj instanceof value))
            {
                break label0;
            }
            Object obj1 = (value)obj;
            obj = value;
            obj1 = ((value) (obj1)).value;
            if (obj != obj1)
            {
                flag = flag1;
                if (obj == null)
                {
                    break label0;
                }
                flag = flag1;
                if (!obj.equals(obj1))
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public final int hashCode()
    {
        if (value == null)
        {
            return 0;
        } else
        {
            return value.hashCode();
        }
    }

    public final String toString()
    {
        String s = String.valueOf(value);
        return (new StringBuilder(String.valueOf(s).length() + 20)).append("Functions.constant(").append(s).append(")").toString();
    }

    public (Object obj)
    {
        value = obj;
    }
}
