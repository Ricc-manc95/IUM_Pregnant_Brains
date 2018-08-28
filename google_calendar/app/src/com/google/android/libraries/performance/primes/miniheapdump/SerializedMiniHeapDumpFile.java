// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.miniheapdump;

import android.content.Context;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import java.io.File;

public final class SerializedMiniHeapDumpFile
{

    public static File getSerializedObjectGraphFile(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException();
        }
        File file = context.getCacheDir();
        context = ProcessStats.getShortProcessName(context);
        if (context != null)
        {
            context = context.replaceAll("[^a-zA-Z0-9\\._]", "_");
            context = context.substring(0, Math.min(32, context.length()));
        } else
        {
            context = "";
        }
        return new File(file, (new StringBuilder(String.valueOf(context).length() + 11)).append(context).append("_primes_mhd").toString());
    }
}
