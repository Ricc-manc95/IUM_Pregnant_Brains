// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.util;

import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Method;

public final class SystemProperties
{

    private static final Method sGetStringMethod;

    public static String getString(String s, String s1)
    {
        try
        {
            s = (String)sGetStringMethod.invoke(null, new Object[] {
                s, s1
            });
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            Log.e("SystemProperties", "get error", s);
            return s1;
        }
        return s;
    }

    static 
    {
        Method method;
        Method method1;
        Method method2;
        Object obj;
        obj = null;
        method2 = null;
        method1 = method2;
        method = obj;
        Class class1 = Class.forName("android.os.SystemProperties");
        method1 = method2;
        method = obj;
        method2 = class1.getMethod("get", new Class[] {
            java/lang/String, java/lang/String
        });
        method1 = method2;
        method = method2;
        class1.getMethod("getInt", new Class[] {
            java/lang/String, Integer.TYPE
        });
        method1 = method2;
        method = method2;
        class1.getMethod("getLong", new Class[] {
            java/lang/String, Long.TYPE
        });
        sGetStringMethod = method2;
        return;
        Exception exception1;
        exception1;
        method = method1;
        ThrowableExtension.STRATEGY.printStackTrace(exception1);
        sGetStringMethod = method1;
        return;
        Exception exception;
        exception;
        sGetStringMethod = method;
        throw exception;
    }
}
