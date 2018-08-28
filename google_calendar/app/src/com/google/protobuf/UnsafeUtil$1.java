// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class Action
    implements PrivilegedExceptionAction
{

    public final Object run()
        throws Exception
    {
        Object obj1 = null;
        Field afield[] = sun/misc/Unsafe.getDeclaredFields();
        int j = afield.length;
        int i = 0;
        do
        {
label0:
            {
                Object obj = obj1;
                if (i < j)
                {
                    obj = afield[i];
                    ((Field) (obj)).setAccessible(true);
                    obj = ((Field) (obj)).get(null);
                    if (!sun/misc/Unsafe.isInstance(obj))
                    {
                        break label0;
                    }
                    obj = (Unsafe)sun/misc/Unsafe.cast(obj);
                }
                return obj;
            }
            i++;
        } while (true);
    }

    Action()
    {
    }
}
