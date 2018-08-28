// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.latency;


// Referenced classes of package com.google.android.calendar.latency:
//            LatencyLogger

final class NoopLatencyLogger
    implements LatencyLogger
{

    public static final LatencyLogger LOGGER = new NoopLatencyLogger();

    private NoopLatencyLogger()
    {
    }

    public final void markAt(int i)
    {
    }

    public final void markAt(int i, int j)
    {
    }

    public final void markAt(int i, int j, String s)
    {
    }

    public final void markAt(int i, String s)
    {
    }

}
