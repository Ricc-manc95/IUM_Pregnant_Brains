// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.tracing;


// Referenced classes of package com.google.apps.xplat.tracing:
//            TraceSampler

public final class NoOpTraceSampler
    implements TraceSampler
{

    public static final NoOpTraceSampler INSTANCE = new NoOpTraceSampler();

    private NoOpTraceSampler()
    {
    }

    public final boolean isTracing()
    {
        return false;
    }

}
