// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.Map;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore

final class arg._cls2
    implements AsyncFunction
{

    private final SqliteMessageStore arg$1;
    private final Map arg$2;

    public final ListenableFuture apply(Object obj)
    {
        Object obj1 = arg$1;
        Object obj2 = arg$2;
        obj = (AsyncSQLiteDatabase)obj;
        obj2 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.eDatabase(new (((SqliteMessageStore) (obj1)), ((Map) (obj2))));
        obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.eDatabase(((AsyncSQLiteDatabase) (obj)).db);
        obj2 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.eDatabase(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.ransaction) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.eDatabase) (obj1))));
        ((AsyncSQLiteDatabase) (obj)).transactionExecutor.execute(((Runnable) (obj2)));
        ((ListenableFutureTask) (obj2)).addListener(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.onExecutor(((ListenableFutureTask) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.eDatabase) (obj1))), com.google.common.util.concurrent.INSTANCE);
        return ((ListenableFuture) (obj2));
    }

    ncSqliteDatabase(SqliteMessageStore sqlitemessagestore, Map map)
    {
        arg$1 = sqlitemessagestore;
        arg$2 = map;
    }
}
