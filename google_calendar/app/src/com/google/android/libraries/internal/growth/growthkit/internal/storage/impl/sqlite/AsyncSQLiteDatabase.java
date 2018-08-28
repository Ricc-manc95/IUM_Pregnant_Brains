// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.SerializingExecutor;
import java.util.concurrent.Executor;

public final class AsyncSQLiteDatabase
{

    private static final CloseableCursorFactory CLOSEABLE_CURSOR_FACTORY = null;
    public final SQLiteDatabase db;
    public final Executor exec;
    public final Executor transactionExecutor;

    public AsyncSQLiteDatabase(SQLiteDatabase sqlitedatabase, Executor executor)
    {
        db = sqlitedatabase;
        exec = executor;
        transactionExecutor = new SerializingExecutor(executor);
    }

    public static void checkInterrupt()
        throws InterruptedException
    {
        if (Thread.interrupted())
        {
            throw new InterruptedException();
        } else
        {
            return;
        }
    }

    static android.database.sqlite.SQLiteDatabase.CursorFactory getDefaultCursorFactory()
    {
        return null;
    }

}
