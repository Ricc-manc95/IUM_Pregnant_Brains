// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;

import java.util.Locale;
import java.util.logging.Logger;

public final class Platform
{
    static final class JdkPatternCompiler
    {

        JdkPatternCompiler()
        {
        }
    }


    private Platform()
    {
    }

    public static String emptyToNull(String s)
    {
        boolean flag;
        if (s == null || s.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            s = null;
        }
        return s;
    }

    static String formatCompact4Digits(double d)
    {
        return String.format(Locale.ROOT, "%.4g", new Object[] {
            Double.valueOf(d)
        });
    }

    public static String nullToEmpty(String s)
    {
        String s1 = s;
        if (s == null)
        {
            s1 = "";
        }
        return s1;
    }

    public static boolean stringIsNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }

    static long systemNanoTime()
    {
        return System.nanoTime();
    }

    static 
    {
        Logger.getLogger(com/google/common/base/Platform.getName());
        new JdkPatternCompiler();
    }
}
