// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.io.Serializable;
import java.util.Arrays;

// Referenced classes of package com.google.common.base:
//            Supplier

public final class instance
    implements Supplier, Serializable
{

    public static final long serialVersionUID = 0L;
    private final Object instance;

    public final boolean equals(Object obj)
    {
        boolean flag;
label0:
        {
            boolean flag1 = false;
            flag = flag1;
            if (!(obj instanceof instance))
            {
                break label0;
            }
            Object obj1 = (instance)obj;
            obj = instance;
            obj1 = ((instance) (obj1)).instance;
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

    public final Object get()
    {
        return instance;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            instance
        });
    }

    public final String toString()
    {
        String s = String.valueOf(instance);
        return (new StringBuilder(String.valueOf(s).length() + 22)).append("Suppliers.ofInstance(").append(s).append(")").toString();
    }

    public _cls9(Object obj)
    {
        instance = obj;
    }
}
