// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.mail.log;

import android.util.Log;
import android.util.LruCache;
import com.google.apps.xplat.logging.LoggingApi;
import com.google.apps.xplat.logging.XLogLevel;
import com.google.apps.xplat.logging.XLogger;
import java.util.Locale;

public class LogUtils
{

    public static String classTag = "UnifiedEmail";
    private static final XLogger logger = new XLogger(com/android/mail/log/LogUtils);
    private static int maxEnabledLogLevel = 3;

    public LogUtils()
    {
    }

    public static transient void e(String s, String s1, Object aobj[])
    {
        if (isLoggable(s, 6))
        {
            logger.getLoggingApi(XLogLevel.ERROR).log(formatForXLogger(s, s1, aobj));
        }
    }

    public static transient void e(String s, Throwable throwable, String s1, Object aobj[])
    {
        if (isLoggable(s, 6))
        {
            logger.getLoggingApi(XLogLevel.ERROR).withCause(throwable).log(formatForXLogger(s, s1, aobj));
        }
    }

    private static transient String formatForXLogger(String s, String s1, Object aobj[])
    {
        String s2 = s1;
        if (aobj.length > 0)
        {
            s2 = String.format(Locale.US, s1, aobj);
        }
        return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s2).length())).append(s).append(":").append(s2).toString();
    }

    private static boolean isLoggable(String s, int i)
    {
        while (maxEnabledLogLevel > i || !Log.isLoggable(s, i) && !Log.isLoggable(classTag, i)) 
        {
            return false;
        }
        return true;
    }

    public static transient void w(String s, String s1, Object aobj[])
    {
        if (isLoggable(s, 5))
        {
            logger.getLoggingApi(XLogLevel.WARN).log(formatForXLogger(s, s1, aobj));
        }
    }

    public static transient void wtf(String s, String s1, Object aobj[])
    {
        if (isLoggable(s, 7))
        {
            logger.getLoggingApi(XLogLevel.ERROR).withCause(new Error()).log(formatForXLogger(s, s1, aobj));
            String s2 = s1;
            if (aobj.length > 0)
            {
                s2 = String.format(Locale.US, s1, aobj);
            }
            Log.wtf(s, s2);
        }
    }

    static 
    {
        new LruCache(10);
    }
}
