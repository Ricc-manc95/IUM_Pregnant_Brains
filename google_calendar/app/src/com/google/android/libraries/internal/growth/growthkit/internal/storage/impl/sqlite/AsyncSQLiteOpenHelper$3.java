// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.sqlite.SQLiteDatabase;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteDatabase

final class val.localDb
    implements Runnable
{

    private final ListenableFuture val$localDb;

    public final void run()
    {
        if (!val$localDb.isCancelled())
        {
            try
            {
                ListenableFuture listenablefuture = val$localDb;
                if (!listenablefuture.isDone())
                {
                    throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                        listenablefuture
                    }));
                } else
                {
                    ((AsyncSQLiteDatabase)Uninterruptibles.getUninterruptibly(listenablefuture)).db.close();
                    return;
                }
            }
            catch (ExecutionException executionexception) { }
        }
    }

    q()
    {
        val$localDb = listenablefuture;
        super();
    }
}
