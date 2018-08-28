// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import com.google.common.util.concurrent.ListenableFuture;
import java.io.Closeable;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteOpenHelper

final class this._cls0
    implements Closeable
{

    private final AsyncSQLiteOpenHelper this$0;

    public final void close()
    {
        Object obj = refCountLock;
        obj;
        JVM INSTR monitorenter ;
        AsyncSQLiteOpenHelper asyncsqliteopenhelper;
        ListenableFuture listenablefuture;
        asyncsqliteopenhelper = AsyncSQLiteOpenHelper.this;
        asyncsqliteopenhelper.refCount = asyncsqliteopenhelper.refCount - 1;
        asyncsqliteopenhelper = AsyncSQLiteOpenHelper.this;
        if (asyncsqliteopenhelper.refCount != 0)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        listenablefuture = asyncsqliteopenhelper.db;
        asyncsqliteopenhelper.db = null;
        if (listenablefuture == null)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        if (!listenablefuture.cancel(true))
        {
            asyncsqliteopenhelper.dbOpenExecutor.execute(new <init>(listenablefuture));
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    q()
    {
        this$0 = AsyncSQLiteOpenHelper.this;
        super();
    }
}
