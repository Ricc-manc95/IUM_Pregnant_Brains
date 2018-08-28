// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.common.util.isemulator;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;

public final class IsEmulator
{

    private static Exception exception;
    private static Boolean isEmulator;

    private static boolean isEmulator()
        throws Exception
    {
        boolean flag;
        flag = false;
        if (isEmulator != null)
        {
            return isEmulator.booleanValue();
        }
        if (exception != null)
        {
            throw exception;
        }
        if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 8)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        Object obj;
        Field field;
        if ("sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT))
        {
            flag = true;
        }
        try
        {
            isEmulator = Boolean.valueOf(flag);
        }
        catch (Exception exception1)
        {
            exception = exception1;
            throw exception1;
        }
        return isEmulator.booleanValue();
        obj = Class.forName("android.os.Build");
        field = ((Class) (obj)).getField("HARDWARE");
        field.setAccessible(true);
        obj = (String)field.get(obj);
        if (!"goldfish".equals(obj) && !"ranchu".equals(obj))
        {
            flag = false;
        } else
        {
            flag = true;
        }
        isEmulator = Boolean.valueOf(flag);
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_68;
        }
    }

    public static boolean isProbablyEmulator()
    {
        boolean flag;
        try
        {
            flag = isEmulator();
        }
        catch (Exception exception1)
        {
            Log.w("IsEmulator", "Could not determine if emulator.  Assuming false.", exception1);
            return false;
        }
        return flag;
    }
}
