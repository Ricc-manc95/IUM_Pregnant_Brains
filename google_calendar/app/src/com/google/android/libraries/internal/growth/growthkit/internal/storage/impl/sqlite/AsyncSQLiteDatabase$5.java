// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.database.sqlite.SQLiteDatabase;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteDatabase

public final class ncSqliteDatabase
    implements Callable
{

    private final AsyncSQLiteDatabase this$0;
    public volatile boolean threwException;
    private final ncSqliteDatabase val$syncSqliteDatabase;
    private final nctionTransaction val$transaction;

    public final Object call()
        throws Exception
    {
        boolean flag = true;
        if (db.inTransaction())
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Thread is already in a transaction! This should never happen because thenthis will be treated as a nested transaction."));
        }
        class _cls1
            implements SQLiteTransactionListener
        {

            private final AsyncSQLiteDatabase._cls5 this$1;

            public final void onBegin()
            {
            }

            public final void onCommit()
            {
            }

            public final void onRollback()
            {
                if (!threwException)
                {
                    throw new SQLiteTransactionRolledBackException();
                } else
                {
                    return;
                }
            }

            _cls1()
            {
                this$1 = AsyncSQLiteDatabase._cls5.this;
                super();
            }
        }

        db.beginTransactionWithListener(new _cls1());
        Object obj;
        threwException = true;
        obj = val$transaction.execute(val$syncSqliteDatabase);
        AsyncSQLiteDatabase.checkInterrupt();
        db.setTransactionSuccessful();
        threwException = false;
        db.endTransaction();
        return obj;
        Exception exception;
        exception;
        db.endTransaction();
        throw exception;
    }

    public ncSqliteDatabase()
    {
        this$0 = final_asyncsqlitedatabase;
        val$transaction = nctiontransaction;
        val$syncSqliteDatabase = ncSqliteDatabase.this;
        super();
    }
}
