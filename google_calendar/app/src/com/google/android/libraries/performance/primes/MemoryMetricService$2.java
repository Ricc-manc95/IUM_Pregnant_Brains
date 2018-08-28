// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;


// Referenced classes of package com.google.android.libraries.performance.primes:
//            MemoryMetricService

final class this._cls0
    implements llback
{

    private final MemoryMetricService this$0;

    public final void onEvent(int i, String s)
    {
        recordEvent(null, false, i, s, null, null);
    }

    llback()
    {
        this$0 = MemoryMetricService.this;
        super();
    }
}
