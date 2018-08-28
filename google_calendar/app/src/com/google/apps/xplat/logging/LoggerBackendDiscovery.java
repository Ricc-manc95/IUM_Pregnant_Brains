// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import java.lang.reflect.Constructor;

// Referenced classes of package com.google.apps.xplat.logging:
//            LoggerBackend, BaseJavaUtilLoggerBackend

class LoggerBackendDiscovery
{

    private static final String BACKEND_CLASS_NAMES[] = {
        "JavaUtilLoggerBackend", "AndroidLoggerBackend", "IosLoggerBackend"
    };

    private LoggerBackendDiscovery()
    {
    }

    static LoggerBackend discoverBackend()
    {
        Object obj = com/google/apps/xplat/logging/LoggerBackendDiscovery.getPackage();
        if (obj == null) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        int j;
        obj = ((Package) (obj)).getName();
        as = BACKEND_CLASS_NAMES;
        j = as.length;
        i = 0;
_L3:
        Object obj1;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = as[i];
        obj1 = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(obj1).length())).append(((String) (obj))).append('.').append(((String) (obj1))).toString();
        obj1 = (LoggerBackend)Class.forName(((String) (obj1))).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        return ((LoggerBackend) (obj1));
        Exception exception;
        exception;
        i++;
        if (true) goto _L3; else goto _L2
_L2:
        return new BaseJavaUtilLoggerBackend();
    }

}
