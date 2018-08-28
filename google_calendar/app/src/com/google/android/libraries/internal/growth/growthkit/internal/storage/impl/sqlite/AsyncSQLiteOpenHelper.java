// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.SerializingExecutor;
import com.google.android.libraries.stitch.util.Closeables;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public final class AsyncSQLiteOpenHelper
{

    public final ConnectionConfig config;
    public final Context context;
    public ListenableFuture db;
    private final AsyncCallable dbName;
    public final Executor dbOpenExecutor;
    public final Executor executor;
    public int refCount;
    public final Object refCountLock;
    private final List triggerStepList;
    public final List upgradeStepList;

    private AsyncSQLiteOpenHelper(AsyncCallable asynccallable, Context context1, Executor executor1, List list, List list1, ConnectionConfig connectionconfig)
    {
        refCountLock = new Object();
        refCount = 0;
        dbName = asynccallable;
        executor = executor1;
        dbOpenExecutor = new SerializingExecutor(executor1);
        context = context1;
        upgradeStepList = list;
        triggerStepList = list1;
        config = connectionconfig;
    }

    public AsyncSQLiteOpenHelper(String s, Context context1, Executor executor1, List list)
    {
        this(s, context1, executor1, list, ((List) (new ArrayList())), new ConnectionConfig());
    }

    private AsyncSQLiteOpenHelper(String s, Context context1, Executor executor1, List list, List list1, ConnectionConfig connectionconfig)
    {
        class .Lambda._cls0
            implements AsyncCallable
        {

            private final String arg$1;

            public final ListenableFuture call()
            {
                String s1 = arg$1;
                if (s1 == null)
                {
                    return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(s1);
                }
            }

            .Lambda._cls0(String s)
            {
                arg$1 = s;
            }
        }

        this(((AsyncCallable) (new .Lambda._cls0(s))), context1, executor1, list, list1, connectionconfig);
    }

    private static void addTriggerSteps(AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase, List list)
        throws InterruptedException
    {
        if (list != null)
        {
            if ((syncsqlitedatabase = list.iterator()).hasNext())
            {
                syncsqlitedatabase.next();
                throw new NoSuchMethodError();
            }
        }
    }

    private final ListenableFuture innerOpenDatabase()
    {
        ListenableFuture listenablefuture;
        try
        {
            listenablefuture = AbstractTransformFuture.create(dbName.call(), new _cls2(), dbOpenExecutor);
        }
        catch (Exception exception)
        {
            if (exception == null)
            {
                throw new NullPointerException();
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(exception);
            }
        }
        return listenablefuture;
    }

    final void backUpDatabase(SQLiteDatabase sqlitedatabase)
    {
        FileChannel filechannel = null;
        Object obj = null;
        Object obj2 = null;
        FileChannel filechannel1 = null;
        Object obj1 = new File(sqlitedatabase.getPath());
        File file = new File(String.valueOf(sqlitedatabase.getPath()).concat(".bak"));
        RandomAccessFile randomaccessfile;
        long l;
        long l1;
        try
        {
            sqlitedatabase = (new RandomAccessFile(((File) (obj1)), "r")).getChannel();
        }
        // Misplaced declaration of an exception variable
        catch (SQLiteDatabase sqlitedatabase)
        {
            obj = null;
            sqlitedatabase = null;
            continue; /* Loop/switch isn't completed */
        }
        finally
        {
            obj1 = null;
            sqlitedatabase = ((SQLiteDatabase) (obj));
            obj = exception;
        }
        obj = sqlitedatabase;
        obj1 = obj;
        sqlitedatabase = obj2;
        randomaccessfile = new RandomAccessFile(file, "rw");
        filechannel = filechannel1;
        obj1 = obj;
        sqlitedatabase = obj2;
        l = ((FileChannel) (obj)).size();
        filechannel = filechannel1;
        obj1 = obj;
        sqlitedatabase = obj2;
        randomaccessfile.setLength(l);
        filechannel = filechannel1;
        obj1 = obj;
        sqlitedatabase = obj2;
        filechannel1 = randomaccessfile.getChannel();
_L2:
        filechannel = filechannel1;
        obj1 = obj;
        sqlitedatabase = filechannel1;
        l1 = ((FileChannel) (obj)).position();
        if (l1 >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        filechannel = filechannel1;
        obj1 = obj;
        sqlitedatabase = filechannel1;
        ((FileChannel) (obj)).position(l1 + ((FileChannel) (obj)).transferTo(l1, l, filechannel1));
        if (true) goto _L2; else goto _L1
        sqlitedatabase;
        sqlitedatabase = randomaccessfile;
_L6:
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        obj1 = obj;
        sqlitedatabase = filechannel;
        file.delete();
        Closeables.closeQuietly(((Closeable) (obj)));
        Closeables.closeQuietly(filechannel);
        return;
_L1:
        filechannel = filechannel1;
        obj1 = obj;
        sqlitedatabase = filechannel1;
        filechannel1.close();
        Closeables.closeQuietly(((Closeable) (obj)));
        Closeables.closeQuietly(filechannel1);
        return;
_L4:
        Closeables.closeQuietly(((Closeable) (obj1)));
        Closeables.closeQuietly(sqlitedatabase);
        throw obj;
        obj;
        if (true) goto _L4; else goto _L3
_L3:
        sqlitedatabase;
        sqlitedatabase = null;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final AsyncCloseable openDatabase()
    {
        Object obj1 = refCountLock;
        obj1;
        JVM INSTR monitorenter ;
        refCount = refCount + 1;
        if (db != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        Exception exception;
        boolean flag;
        if (refCount == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        throw new IllegalStateException(String.valueOf("DB was null with nonzero refcount"));
        exception;
        obj1;
        JVM INSTR monitorexit ;
        throw exception;
        db = innerOpenDatabase();
        Object obj = db;
        obj1;
        JVM INSTR monitorexit ;
        if (!((ListenableFuture) (obj)).isDone())
        {
            com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture noncancellationpropagatingfuture = new com.google.common.util.concurrent.Futures.NonCancellationPropagatingFuture(((ListenableFuture) (obj)));
            ((ListenableFuture) (obj)).addListener(noncancellationpropagatingfuture, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            obj = noncancellationpropagatingfuture;
        }
        return AsyncCloseable.fromFutureWithCloseables(((ListenableFuture) (obj)), new Closeable[] {
            new _cls1()
        });
    }

    final void upgradeDatabase(SQLiteDatabase sqlitedatabase, List list)
        throws InterruptedException
    {
        AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase;
        int i;
        i = sqlitedatabase.getVersion();
        boolean flag;
        if (i <= list.size())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Preconditions.checkState(flag, "Can't downgrade from version %s to version %s", i, list.size());
        syncsqlitedatabase = new AsyncSQLiteDatabase.SyncSqliteDatabase(sqlitedatabase);
        if (i == list.size())
        {
            addTriggerSteps(syncsqlitedatabase, triggerStepList);
            return;
        }
        backUpDatabase(sqlitedatabase);
        sqlitedatabase.beginTransaction();
        for (Iterator iterator = list.subList(i, list.size()).iterator(); iterator.hasNext(); ((UpgradeStep)iterator.next()).upgrade(syncsqlitedatabase)) { }
        break MISSING_BLOCK_LABEL_136;
        list;
        sqlitedatabase.endTransaction();
        throw list;
        addTriggerSteps(syncsqlitedatabase, triggerStepList);
        sqlitedatabase.setVersion(list.size());
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        return;
    }

    private class ConnectionConfig
    {

        public boolean forceWriteAheadLogging;
        public List pragmaStatements;

        ConnectionConfig()
        {
            forceWriteAheadLogging = false;
            pragmaStatements = new ArrayList();
        }
    }


    private class _cls2
        implements Function
    {

        private final AsyncSQLiteOpenHelper this$0;

        private final AsyncSQLiteDatabase apply(String s)
        {
            int j = 0x30000000;
            File file = context.getDatabasePath(s);
            s = AsyncSQLiteOpenHelper.this;
            SQLiteDatabase sqlitedatabase;
            Iterator iterator;
            boolean flag;
            int i;
            if (!((ActivityManager)((AsyncSQLiteOpenHelper) (s)).context.getSystemService("activity")).isLowRamDevice())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            Exception exception;
            if (flag)
            {
                i = 0x30000000;
            } else
            {
                i = 0x10000000;
            }
            file.getParentFile().mkdirs();
            sqlitedatabase = SQLiteDatabase.openDatabase(file.getPath(), AsyncSQLiteDatabase.getDefaultCursorFactory(), i, s. new _cls4());
            if (flag)
            {
                sqlitedatabase.enableWriteAheadLogging();
            }
            sqlitedatabase.setForeignKeyConstraintsEnabled(true);
            iterator = config.pragmaStatements.iterator();
_L5:
            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            s = String.valueOf((String)iterator.next());
            if (s.length() == 0) goto _L4; else goto _L3
_L3:
            s = "PRAGMA ".concat(s);
_L6:
            sqlitedatabase.execSQL(s);
              goto _L5
            s;
            if (sqlitedatabase != null)
            {
                try
                {
                    sqlitedatabase.close();
                }
                // Misplaced declaration of an exception variable
                catch (String s) { }
            }
            file.delete();
            s = AsyncSQLiteOpenHelper.this;
            if (!((ActivityManager)((AsyncSQLiteOpenHelper) (s)).context.getSystemService("activity")).isLowRamDevice())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                i = j;
            } else
            {
                i = 0x10000000;
            }
            file.getParentFile().mkdirs();
            s = SQLiteDatabase.openDatabase(file.getPath(), AsyncSQLiteDatabase.getDefaultCursorFactory(), i, s. new _cls4());
            if (flag)
            {
                s.enableWriteAheadLogging();
            }
            upgradeDatabase(s, upgradeStepList);
_L7:
            return new AsyncSQLiteDatabase(s, executor);
_L4:
            s = new String("PRAGMA ");
              goto _L6
_L2:
            upgradeDatabase(sqlitedatabase, upgradeStepList);
            (new File(String.valueOf(sqlitedatabase.getPath()).concat(".bak"))).delete();
            s = sqlitedatabase;
              goto _L7
            exception;
            if (s != null)
            {
                try
                {
                    s.close();
                }
                // Misplaced declaration of an exception variable
                catch (String s) { }
            }
            throw new RuntimeException(exception);
              goto _L6
        }

        public final volatile Object apply(Object obj)
        {
            return apply((String)obj);
        }

        _cls2()
        {
            this$0 = AsyncSQLiteOpenHelper.this;
            super();
        }

        private class _cls4
            implements DatabaseErrorHandler
        {

            private final AsyncSQLiteOpenHelper this$0;

            public final void onCorruption(SQLiteDatabase sqlitedatabase)
            {
                backUpDatabase(sqlitedatabase);
            }

            _cls4()
            {
                this$0 = AsyncSQLiteOpenHelper.this;
                super();
            }
        }

    }


    private class _cls1
        implements Closeable
    {

        private final AsyncSQLiteOpenHelper this$0;

        public final void close()
        {
            Object obj = refCountLock;
            obj;
            JVM INSTR monitorenter ;
            AsyncSQLiteOpenHelper asyncsqliteopenhelper;
            final ListenableFuture localDb;
            asyncsqliteopenhelper = AsyncSQLiteOpenHelper.this;
            asyncsqliteopenhelper.refCount = asyncsqliteopenhelper.refCount - 1;
            asyncsqliteopenhelper = AsyncSQLiteOpenHelper.this;
            if (asyncsqliteopenhelper.refCount != 0)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            localDb = asyncsqliteopenhelper.db;
            asyncsqliteopenhelper.db = null;
            if (localDb == null)
            {
                break MISSING_BLOCK_LABEL_78;
            }
            if (!localDb.cancel(true))
            {
                asyncsqliteopenhelper.dbOpenExecutor.execute(new _cls3());
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

        _cls1()
        {
            this$0 = AsyncSQLiteOpenHelper.this;
            super();
        }

        private class _cls3
            implements Runnable
        {

            private final ListenableFuture val$localDb;

            public final void run()
            {
                if (!localDb.isCancelled())
                {
                    try
                    {
                        ListenableFuture listenablefuture = localDb;
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

            _cls3()
            {
                localDb = listenablefuture;
                super();
            }
        }

    }


    private class UpgradeStep
    {

        public abstract void upgrade(AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase)
            throws SQLiteException, InterruptedException;
    }

}
