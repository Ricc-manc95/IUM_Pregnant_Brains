// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.os.CancellationSignal;
import com.google.common.util.concurrent.ListenableFutureTask;

public final class val.syncSqliteDatabase
    implements Runnable
{

    private final ncSqliteDatabase val$syncSqliteDatabase;
    private final ListenableFutureTask val$transactionFuture;

    public final void run()
    {
        if (val$transactionFuture.isCancelled())
        {
            val$syncSqliteDatabase.cancellationSignal.cancel();
        }
    }

    public ncSqliteDatabase()
    {
        val$transactionFuture = listenablefuturetask;
        val$syncSqliteDatabase = ncsqlitedatabase;
        super();
    }
}
