// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.common.util.concurrent:
//            ExecutionList

static final class next
{

    public final Executor executor;
    public next next;
    public final Runnable runnable;

    (Runnable runnable1, Executor executor1,  )
    {
        runnable = runnable1;
        executor = executor1;
        next = ;
    }
}
