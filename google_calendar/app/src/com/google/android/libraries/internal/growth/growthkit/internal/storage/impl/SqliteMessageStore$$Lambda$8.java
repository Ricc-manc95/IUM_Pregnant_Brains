// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore

final class arg._cls2
    implements AsyncFunction
{

    private final SqliteMessageStore arg$1;
    private final String arg$2;

    public final ListenableFuture apply(Object obj)
    {
        Object obj1 = arg$1;
        String s = arg$2;
        obj = (AsyncSQLiteDatabase)obj;
        obj1 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.(((AsyncSQLiteDatabase) (obj)), ((SqliteMessageStore) (obj1)).tableName, "(account = ? AND key = ?)", new String[] {
            ((SqliteMessageStore) (obj1)).accountName, s
        }));
        ((AsyncSQLiteDatabase) (obj)).exec.execute(((Runnable) (obj1)));
        return ((ListenableFuture) (obj1));
    }

    (SqliteMessageStore sqlitemessagestore, String s)
    {
        arg$1 = sqlitemessagestore;
        arg$2 = s;
    }
}
