// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.common:
//            FuturePendingResult

final class arg._cls1
    implements Callable
{

    private final FuturePendingResult arg$1;

    public final Object call()
    {
        return arg$1.calculateResult();
    }

    (FuturePendingResult futurependingresult)
    {
        arg$1 = futurependingresult;
    }
}
