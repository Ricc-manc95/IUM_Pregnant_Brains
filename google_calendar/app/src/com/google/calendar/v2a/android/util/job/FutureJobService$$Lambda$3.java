// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.job;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.calendar.v2a.android.util.job:
//            FutureJobService

final class arg._cls1
    implements AsyncFunction
{

    private final AsyncCallable arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return FutureJobService.lambda$callAsyncAfterJob$2$FutureJobService$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T0N6UBECD1M2R3CC5H6OP9R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0(arg$1);
    }

    I(AsyncCallable asynccallable)
    {
        arg$1 = asynccallable;
    }
}
