// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;

public final class GcoreClearcutStreamzLogger
{

    public final GcoreClearcutLogger gcoreClearcutLogger;
    public final String logSourceName;

    public GcoreClearcutStreamzLogger(GcoreClearcutLogger gcoreclearcutlogger, String s)
    {
        if (!s.startsWith("STREAMZ_"))
        {
            throw new IllegalArgumentException("logSourceName should be prefixed by STREAMZ_");
        } else
        {
            gcoreClearcutLogger = gcoreclearcutlogger;
            logSourceName = s;
            return;
        }
    }
}
