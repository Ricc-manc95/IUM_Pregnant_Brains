// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


// Referenced classes of package com.google.common.base:
//            Ticker, Platform

final class  extends Ticker
{

    public final long read()
    {
        return Platform.systemNanoTime();
    }

    ()
    {
    }
}
