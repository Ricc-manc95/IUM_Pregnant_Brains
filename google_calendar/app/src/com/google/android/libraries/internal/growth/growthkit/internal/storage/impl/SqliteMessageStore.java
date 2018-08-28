// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.MessageLite;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            GrowthDbHelper

public final class SqliteMessageStore
    implements MessageStore
{

    public final String accountName;
    public final Provider defaultInstance;
    private final ListeningExecutorService executor;
    private final GrowthDbHelper growthDbHelper;
    public final String tableName;

    public SqliteMessageStore(ListeningExecutorService listeningexecutorservice, GrowthDbHelper growthdbhelper, String s, String s1, Provider provider)
    {
        executor = listeningexecutorservice;
        growthDbHelper = growthdbhelper;
        tableName = s;
        defaultInstance = provider;
        if (s1 != null)
        {
            accountName = s1;
            return;
        } else
        {
            accountName = "signedout";
            return;
        }
    }

    public SqliteMessageStore(ListeningExecutorService listeningexecutorservice, GrowthDbHelper growthdbhelper, String s, Provider provider)
    {
        executor = listeningexecutorservice;
        growthDbHelper = growthdbhelper;
        tableName = s;
        defaultInstance = provider;
        accountName = "noaccount";
    }

    public static String buildInitialSchema(String s)
    {
        return (new StringBuilder(String.valueOf(s).length() + 106)).append("CREATE TABLE ").append(s).append(" (account").append(" TEXT NOT NULL, key").append(" TEXT  NOT NULL, value").append(" BLOB NOT NULL, PRIMARY KEY (account").append(", key").append("))").toString();
    }

    public final ListenableFuture clearAndPutAll(Map map)
    {
        class .Lambda._cls3
            implements AsyncFunction
        {

            private final SqliteMessageStore arg$1;
            private final Map arg$2;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                Object obj2 = arg$2;
                obj = (AsyncSQLiteDatabase)obj;
                class .Lambda._cls11
                    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.Transaction
                {

                    private final SqliteMessageStore arg$1;
                    private final Map arg$2;

                    public final void execute(com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase)
                    {
                        SqliteMessageStore sqlitemessagestore = arg$1;
                        Object obj3 = arg$2;
                        String s = sqlitemessagestore.tableName;
                        String s1 = sqlitemessagestore.accountName;
                        AsyncSQLiteDatabase.checkInterrupt();
                        syncsqlitedatabase.db.delete(s, "account = ?", new String[] {
                            s1
                        });
                        for (obj3 = ((Map) (obj3)).entrySet().iterator(); ((Iterator) (obj3)).hasNext();)
                        {
                            Object obj4 = (java.util.Map.Entry)((Iterator) (obj3)).next();
                            ContentValues contentvalues = new ContentValues(3);
                            contentvalues.put("account", sqlitemessagestore.accountName);
                            contentvalues.put("key", (String)((java.util.Map.Entry) (obj4)).getKey());
                            contentvalues.put("value", ((MessageLite)((java.util.Map.Entry) (obj4)).getValue()).toByteArray());
                            obj4 = sqlitemessagestore.tableName;
                            AsyncSQLiteDatabase.checkInterrupt();
                            if (syncsqlitedatabase.db.insertWithOnConflict(((String) (obj4)), null, contentvalues, 5) == -1L)
                            {
                                throw new SQLException("Failed to clearAndPutAll() to DB.");
                            }
                        }

                    }

                        .Lambda._cls11(Map map)
                        {
                            arg$1 = SqliteMessageStore.this;
                            arg$2 = map;
                        }
                }

                obj2 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls4(((.Lambda._cls11) (obj1)). new .Lambda._cls11(((Map) (obj2))));
                obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase(((AsyncSQLiteDatabase) (obj)).db);
                obj2 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls5(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.FunctionTransaction) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))));
                ((AsyncSQLiteDatabase) (obj)).transactionExecutor.execute(((Runnable) (obj2)));
                ((ListenableFutureTask) (obj2)).addListener(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls6(((ListenableFutureTask) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return ((ListenableFuture) (obj2));
            }

            .Lambda._cls3(Map map)
            {
                arg$1 = SqliteMessageStore.this;
                arg$2 = map;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsync(new .Lambda._cls3(map), executor).transformAndClose();
    }

    public final ListenableFuture contains(String s)
    {
        class .Lambda._cls9
            implements AsyncCloseableFunction
        {

            private final SqliteMessageStore arg$1;
            private final String arg$2;

            public final AsyncCloseable apply(Object obj)
            {
                Object obj2 = arg$1;
                String s1 = arg$2;
                Object obj1 = (AsyncSQLiteDatabase)obj;
                SafeSQLiteQueryBuilder safesqlitequerybuilder = new SafeSQLiteQueryBuilder();
                safesqlitequerybuilder.builder.append("SELECT value");
                obj = String.valueOf(((SqliteMessageStore) (obj2)).tableName);
                if (((String) (obj)).length() != 0)
                {
                    obj = " FROM ".concat(((String) (obj)));
                } else
                {
                    obj = new String(" FROM ");
                }
                safesqlitequerybuilder.builder.append(((String) (obj)));
                safesqlitequerybuilder.builder.append(" WHERE (key = ?");
                safesqlitequerybuilder.args.add(s1);
                safesqlitequerybuilder.builder.append(" AND account = ?");
                obj = ((SqliteMessageStore) (obj2)).accountName;
                safesqlitequerybuilder.args.add(obj);
                safesqlitequerybuilder.builder.append(")");
                safesqlitequerybuilder.builder.append(" LIMIT 1");
                obj = safesqlitequerybuilder.build();
                obj2 = ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder.SafeSQLStatement) (obj)).query;
                obj = DatabaseFuture.create(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls1(((AsyncSQLiteDatabase) (obj1)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder.SafeSQLStatement) (obj)).args, ((String) (obj2))));
                ((AsyncSQLiteDatabase) (obj1)).exec.execute(((Runnable) (obj)));
                obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList();
                return new AsyncCloseable(AbstractTransformFuture.create(((ListenableFuture) (obj)), new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable..Lambda._cls0(((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList) (obj1)), ((ListenableFuture) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), ((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList) (obj1)));
            }

            .Lambda._cls9(String s)
            {
                arg$1 = SqliteMessageStore.this;
                arg$2 = s;
            }
        }

        class .Lambda._cls10
            implements Function
        {

            public static final Function $instance = new .Lambda._cls10();

            public final Object apply(Object obj)
            {
                return Boolean.valueOf(((Cursor)obj).moveToFirst());
            }


            private .Lambda._cls10()
            {
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncCloseable(new .Lambda._cls9(s), executor).transformAndClose(.Lambda._cls10..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final ListenableFuture getAll()
    {
        class .Lambda._cls6
            implements AsyncCloseableFunction
        {

            private final SqliteMessageStore arg$1;

            public final AsyncCloseable apply(Object obj)
            {
                Object obj2 = arg$1;
                Object obj1 = (AsyncSQLiteDatabase)obj;
                SafeSQLiteQueryBuilder safesqlitequerybuilder = new SafeSQLiteQueryBuilder();
                safesqlitequerybuilder.builder.append("SELECT key, value");
                obj = String.valueOf(((SqliteMessageStore) (obj2)).tableName);
                if (((String) (obj)).length() != 0)
                {
                    obj = " FROM ".concat(((String) (obj)));
                } else
                {
                    obj = new String(" FROM ");
                }
                safesqlitequerybuilder.builder.append(((String) (obj)));
                safesqlitequerybuilder.builder.append(" WHERE account = ?");
                obj = ((SqliteMessageStore) (obj2)).accountName;
                safesqlitequerybuilder.args.add(obj);
                obj = safesqlitequerybuilder.build();
                obj2 = ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder.SafeSQLStatement) (obj)).query;
                obj = DatabaseFuture.create(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls1(((AsyncSQLiteDatabase) (obj1)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder.SafeSQLStatement) (obj)).args, ((String) (obj2))));
                ((AsyncSQLiteDatabase) (obj1)).exec.execute(((Runnable) (obj)));
                obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList();
                return new AsyncCloseable(AbstractTransformFuture.create(((ListenableFuture) (obj)), new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable..Lambda._cls0(((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList) (obj1)), ((ListenableFuture) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), ((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList) (obj1)));
            }

            .Lambda._cls6()
            {
                arg$1 = SqliteMessageStore.this;
            }
        }

        class .Lambda._cls7
            implements Function
        {

            private final SqliteMessageStore arg$1;

            public final Object apply(Object obj)
            {
                SqliteMessageStore sqlitemessagestore = arg$1;
                obj = (Cursor)obj;
                HashMap hashmap = new HashMap(((Cursor) (obj)).getCount());
                for (; ((Cursor) (obj)).moveToNext(); hashmap.put(((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("key")), ProtoParsers.mergeFrom(((Cursor) (obj)).getBlob(((Cursor) (obj)).getColumnIndexOrThrow("value")), (MessageLite)sqlitemessagestore.defaultInstance.get()))) { }
                return hashmap;
            }

            .Lambda._cls7()
            {
                arg$1 = SqliteMessageStore.this;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncCloseable(new .Lambda._cls6(), executor).transformAndClose(new .Lambda._cls7(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final ListenableFuture put(String s, MessageLite messagelite)
    {
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final SqliteMessageStore arg$1;
            private final String arg$2;
            private final MessageLite arg$3;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                Object obj2 = arg$2;
                MessageLite messagelite1 = arg$3;
                obj = (AsyncSQLiteDatabase)obj;
                class .Lambda._cls14
                    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.Transaction
                {

                    private final SqliteMessageStore arg$1;
                    private final String arg$2;
                    private final MessageLite arg$3;

                    public final void execute(com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase)
                    {
                        Object obj3 = arg$1;
                        String s1 = arg$2;
                        MessageLite messagelite2 = arg$3;
                        ContentValues contentvalues = new ContentValues(3);
                        contentvalues.put("account", ((SqliteMessageStore) (obj3)).accountName);
                        contentvalues.put("key", s1);
                        contentvalues.put("value", messagelite2.toByteArray());
                        obj3 = ((SqliteMessageStore) (obj3)).tableName;
                        AsyncSQLiteDatabase.checkInterrupt();
                        if (syncsqlitedatabase.db.insertWithOnConflict(((String) (obj3)), null, contentvalues, 5) == -1L)
                        {
                            throw new SQLException("Failed to put() to DB.");
                        } else
                        {
                            return;
                        }
                    }

                        .Lambda._cls14(String s, MessageLite messagelite)
                        {
                            arg$1 = SqliteMessageStore.this;
                            arg$2 = s;
                            arg$3 = messagelite;
                        }
                }

                obj2 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls4(((.Lambda._cls14) (obj1)). new .Lambda._cls14(((String) (obj2)), messagelite1));
                obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase(((AsyncSQLiteDatabase) (obj)).db);
                obj2 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls5(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.FunctionTransaction) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))));
                ((AsyncSQLiteDatabase) (obj)).transactionExecutor.execute(((Runnable) (obj2)));
                ((ListenableFutureTask) (obj2)).addListener(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls6(((ListenableFutureTask) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return ((ListenableFuture) (obj2));
            }

            .Lambda._cls0(String s, MessageLite messagelite)
            {
                arg$1 = SqliteMessageStore.this;
                arg$2 = s;
                arg$3 = messagelite;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsync(new .Lambda._cls0(s, messagelite), executor).transformAndClose();
    }

    public final ListenableFuture putAll(Map map)
    {
        class .Lambda._cls1
            implements AsyncFunction
        {

            private final SqliteMessageStore arg$1;
            private final Map arg$2;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                Object obj2 = arg$2;
                obj = (AsyncSQLiteDatabase)obj;
                class .Lambda._cls13
                    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.Transaction
                {

                    private final SqliteMessageStore arg$1;
                    private final Map arg$2;

                    public final void execute(com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase)
                    {
                        SqliteMessageStore sqlitemessagestore = arg$1;
                        for (Iterator iterator = arg$2.entrySet().iterator(); iterator.hasNext();)
                        {
                            Object obj3 = (java.util.Map.Entry)iterator.next();
                            ContentValues contentvalues = new ContentValues(3);
                            contentvalues.put("account", sqlitemessagestore.accountName);
                            contentvalues.put("key", (String)((java.util.Map.Entry) (obj3)).getKey());
                            contentvalues.put("value", ((MessageLite)((java.util.Map.Entry) (obj3)).getValue()).toByteArray());
                            obj3 = sqlitemessagestore.tableName;
                            AsyncSQLiteDatabase.checkInterrupt();
                            if (syncsqlitedatabase.db.insertWithOnConflict(((String) (obj3)), null, contentvalues, 5) == -1L)
                            {
                                throw new SQLException("Failed to putAll() to DB.");
                            }
                        }

                    }

                        .Lambda._cls13(Map map)
                        {
                            arg$1 = SqliteMessageStore.this;
                            arg$2 = map;
                        }
                }

                obj2 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls4(((.Lambda._cls13) (obj1)). new .Lambda._cls13(((Map) (obj2))));
                obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase(((AsyncSQLiteDatabase) (obj)).db);
                obj2 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls5(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.FunctionTransaction) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))));
                ((AsyncSQLiteDatabase) (obj)).transactionExecutor.execute(((Runnable) (obj2)));
                ((ListenableFutureTask) (obj2)).addListener(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls6(((ListenableFutureTask) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return ((ListenableFuture) (obj2));
            }

            .Lambda._cls1(Map map)
            {
                arg$1 = SqliteMessageStore.this;
                arg$2 = map;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsync(new .Lambda._cls1(map), executor).transformAndClose();
    }

    public final ListenableFuture remove(String s)
    {
        class .Lambda._cls8
            implements AsyncFunction
        {

            private final SqliteMessageStore arg$1;
            private final String arg$2;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                String s1 = arg$2;
                obj = (AsyncSQLiteDatabase)obj;
                obj1 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls2(((AsyncSQLiteDatabase) (obj)), ((SqliteMessageStore) (obj1)).tableName, "(account = ? AND key = ?)", new String[] {
                    ((SqliteMessageStore) (obj1)).accountName, s1
                }));
                ((AsyncSQLiteDatabase) (obj)).exec.execute(((Runnable) (obj1)));
                return ((ListenableFuture) (obj1));
            }

            .Lambda._cls8(String s)
            {
                arg$1 = SqliteMessageStore.this;
                arg$2 = s;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsync(new .Lambda._cls8(s), executor).transformAndClose();
    }
}
