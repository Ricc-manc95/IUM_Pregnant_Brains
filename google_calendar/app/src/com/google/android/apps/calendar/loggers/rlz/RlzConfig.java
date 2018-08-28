// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers.rlz;

import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RlzConfig
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/loggers/rlz/RlzConfig);
    private static RlzConfig instance;
    public final ImmutableList accessPoints;
    public final String brandCode;
    public final boolean isPreInstalled;

    private RlzConfig(String s, ImmutableList immutablelist, boolean flag)
    {
        brandCode = s;
        accessPoints = immutablelist;
        isPreInstalled = flag;
    }

    public static RlzConfig getInstance(boolean flag)
    {
        com/google/android/apps/calendar/loggers/rlz/RlzConfig;
        JVM INSTR monitorenter ;
        if (instance != null) goto _L2; else goto _L1
_L1:
        Object obj;
        String s;
        s = getSystemProperty("ro.com.google.rlzbrandcode");
        obj = getSystemProperty("ro.com.google.rlz_ap_whitelist");
        if (!TextUtils.isEmpty(((CharSequence) (obj)))) goto _L4; else goto _L3
_L3:
        obj = ImmutableList.of();
_L7:
        if (((ImmutableCollection) (obj)).contains("Y8")) goto _L6; else goto _L5
_L5:
        obj = ImmutableList.of();
_L8:
        instance = new RlzConfig(s, ((ImmutableList) (obj)), flag);
_L2:
        obj = instance;
        com/google/android/apps/calendar/loggers/rlz/RlzConfig;
        JVM INSTR monitorexit ;
        return ((RlzConfig) (obj));
_L4:
        obj = ImmutableList.copyOf(((String) (obj)).toUpperCase().split(","));
          goto _L7
_L6:
label0:
        {
            if (!((ImmutableCollection) (obj)).contains("Y0"))
            {
                break label0;
            }
            obj = ImmutableList.of("Y0", "Y8");
        }
          goto _L8
        obj = ImmutableList.of("Y8");
          goto _L8
        Exception exception;
        exception;
        throw exception;
          goto _L7
    }

    private static String getSystemProperty(String s)
    {
        s = (String)Class.forName("android.os.SystemProperties").getMethod("get", new Class[] {
            java/lang/String, java/lang/String
        }).invoke(null, new Object[] {
            s, ""
        });
        return s;
        s;
_L2:
        LogUtils.wtf(TAG, s, "Getting system property", new Object[0]);
        return "";
        s;
        continue; /* Loop/switch isn't completed */
        s;
        continue; /* Loop/switch isn't completed */
        s;
        continue; /* Loop/switch isn't completed */
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

}
