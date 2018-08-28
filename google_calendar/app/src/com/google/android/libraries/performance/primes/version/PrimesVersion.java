// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.version;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.libraries.performance.primes.PrimesLog;

public final class PrimesVersion
{

    public static Long getPrimesVersion(Context context)
    {
        context = context.getResources().getString(0x7f1303b5);
        long l = Long.parseLong(context);
        return Long.valueOf(l);
        NumberFormatException numberformatexception;
        numberformatexception;
        context = String.valueOf(context);
        if (context.length() == 0) goto _L2; else goto _L1
_L1:
        context = "Couldn't parse Primes version number from ".concat(context);
_L4:
        PrimesLog.log(5, "PrimesVersion", context, new Object[0]);
        break MISSING_BLOCK_LABEL_80;
_L2:
        context = new String("Couldn't parse Primes version number from ");
        if (true) goto _L4; else goto _L3
_L3:
        context;
        PrimesLog.log(5, "PrimesVersion", "Primes version number string not found", new Object[0]);
        return null;
    }
}
