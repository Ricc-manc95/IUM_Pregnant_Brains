// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;


// Referenced classes of package com.google.apps.xplat.tracing:
//            TracingApi, NoOpTraceSection, AsyncTraceSection

final class NoOpTracingApi
    implements TracingApi
{

    public static final NoOpTracingApi INSTANCE = new NoOpTracingApi();

    private NoOpTracingApi()
    {
    }

    public final AsyncTraceSection beginAsync(String s)
    {
        return NoOpTraceSection.INSTANCE;
    }

}