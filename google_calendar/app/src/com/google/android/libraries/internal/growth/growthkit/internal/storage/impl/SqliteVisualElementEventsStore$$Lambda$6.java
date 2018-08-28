// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.concurrent.Executor;

final class arg._cls1
    implements AsyncFunction
{

    private final long arg$1;

    public final ListenableFuture apply(Object obj)
    {
        long l = arg$1;
        obj = (AsyncSQLiteDatabase)obj;
        ListenableFutureTask listenablefuturetask = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.(((AsyncSQLiteDatabase) (obj)), "visual_element_events_table", "timestamp_ms <= ?", new String[] {
            String.valueOf(l)
        }));
        ((AsyncSQLiteDatabase) (obj)).exec.execute(listenablefuturetask);
        return listenablefuturetask;
    }

    (long l)
    {
        arg$1 = l;
    }
}
