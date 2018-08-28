// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.Closeable;
import java.lang.reflect.Method;

final class 
    implements 
{

    public static final ress INSTANCE = new <init>();
    public static final Method addSuppressed = getAddSuppressed();

    private static Method getAddSuppressed()
    {
        Method method;
        try
        {
            method = java/lang/Throwable.getMethod("addSuppressed", new Class[] {
                java/lang/Throwable
            });
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return method;
    }

    public final void suppress(Closeable closeable, Throwable throwable, Throwable throwable1)
    {
        if (throwable == throwable1)
        {
            return;
        }
        try
        {
            addSuppressed.invoke(throwable, new Object[] {
                throwable1
            });
            return;
        }
        catch (Throwable throwable2)
        {
            ANCE.ress(closeable, throwable, throwable1);
        }
    }


    ()
    {
    }
}
