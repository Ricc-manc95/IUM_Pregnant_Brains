// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.util.Log;
import java.util.Locale;

public final class PrimesLog
{

    public static transient void log(int i, String s, String s1, Object aobj[])
    {
        if (Log.isLoggable(s, i))
        {
            if (aobj.length != 0)
            {
                s1 = String.format(Locale.US, s1, aobj);
            }
            Log.println(i, s, s1);
        }
    }

    public static transient void log(int i, String s, Throwable throwable, String s1, Object aobj[])
    {
        if (!Log.isLoggable(s, i)) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 2 6: default 44
    //                   2 78
    //                   3 95
    //                   4 112
    //                   5 129
    //                   6 156;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L7:
        break MISSING_BLOCK_LABEL_129;
_L8:
        break MISSING_BLOCK_LABEL_156;
_L3:
        if (aobj.length != 0)
        {
            s1 = String.format(Locale.US, s1, aobj);
        }
        log(5, "PrimesLog", "unexpected priority: %d for log %s: %s", new Object[] {
            Integer.valueOf(i), s, s1
        });
_L2:
        return;
_L4:
        if (aobj.length == 0) goto _L2; else goto _L9
_L9:
        String.format(Locale.US, s1, aobj);
        return;
_L5:
        if (aobj.length == 0) goto _L2; else goto _L10
_L10:
        String.format(Locale.US, s1, aobj);
        return;
_L6:
        if (aobj.length == 0) goto _L2; else goto _L11
_L11:
        String.format(Locale.US, s1, aobj);
        return;
        if (aobj.length != 0)
        {
            s1 = String.format(Locale.US, s1, aobj);
        }
        Log.w(s, s1, throwable);
        return;
        if (aobj.length != 0)
        {
            s1 = String.format(Locale.US, s1, aobj);
        }
        Log.e(s, s1, throwable);
        return;
    }
}
