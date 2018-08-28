// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.ClearcutEventsStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            GrowthDbHelper

public final class SqliteClearcutEventsStore
    implements ClearcutEventsStore
{

    public final String accountName;
    private final ListeningExecutorService executor;
    private final GrowthDbHelper growthDbHelper;

    public SqliteClearcutEventsStore(ListeningExecutorService listeningexecutorservice, GrowthDbHelper growthdbhelper, String s)
    {
        executor = listeningexecutorservice;
        accountName = s;
        growthDbHelper = growthdbhelper;
    }

    static void appendWherePart(SafeSQLiteQueryBuilder safesqlitequerybuilder, com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent)
    {
        safesqlitequerybuilder.builder.append("(log_source = ?");
        int i = clearcutevent.logSource_;
        safesqlitequerybuilder.args.add(String.valueOf(i));
        safesqlitequerybuilder.builder.append(" AND event_code = ?");
        i = clearcutevent.eventCode_;
        safesqlitequerybuilder.args.add(String.valueOf(i));
        safesqlitequerybuilder.builder.append(" AND package_name = ?)");
        clearcutevent = clearcutevent.bundleIdentifier_;
        safesqlitequerybuilder.args.add(clearcutevent);
    }

    public final ListenableFuture addEvent(com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent)
    {
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final SqliteClearcutEventsStore arg$1;
            private final com.google.identity.growth.proto.Promotion.ClearcutEvent arg$2;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                Object obj2 = arg$2;
                obj = (AsyncSQLiteDatabase)obj;
                class .Lambda._cls7
                    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.Transaction
                {

                    private final SqliteClearcutEventsStore arg$1;
                    private final com.google.identity.growth.proto.Promotion.ClearcutEvent arg$2;

                    public final void execute(com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase syncsqlitedatabase)
                    {
                        Object obj3 = arg$1;
                        com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent1 = arg$2;
                        ContentValues contentvalues = new ContentValues(4);
                        long l = System.currentTimeMillis();
                        obj3 = ((SqliteClearcutEventsStore) (obj3)).accountName;
                        if (obj3 == null)
                        {
                            obj3 = "signedout";
                        }
                        contentvalues.put("account", ((String) (obj3)));
                        contentvalues.put("timestamp_ms", Long.valueOf(l));
                        contentvalues.put("log_source", Integer.valueOf(clearcutevent1.logSource_));
                        contentvalues.put("event_code", Integer.valueOf(clearcutevent1.eventCode_));
                        contentvalues.put("package_name", clearcutevent1.bundleIdentifier_);
                        AsyncSQLiteDatabase.checkInterrupt();
                        syncsqlitedatabase.db.insertWithOnConflict("clearcut_events_table", null, contentvalues, 0);
                    }

                        .Lambda._cls7(com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent)
                        {
                            arg$1 = SqliteClearcutEventsStore.this;
                            arg$2 = clearcutevent;
                        }
                }

                obj2 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls4(((.Lambda._cls7) (obj1)). new .Lambda._cls7(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj2))));
                obj1 = new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase(((AsyncSQLiteDatabase) (obj)).db);
                obj2 = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls5(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.FunctionTransaction) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))));
                ((AsyncSQLiteDatabase) (obj)).transactionExecutor.execute(((Runnable) (obj2)));
                ((ListenableFutureTask) (obj2)).addListener(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls6(((ListenableFutureTask) (obj2)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase.SyncSqliteDatabase) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                return ((ListenableFuture) (obj2));
            }

            .Lambda._cls0(com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent)
            {
                arg$1 = SqliteClearcutEventsStore.this;
                arg$2 = clearcutevent;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncAndClose(new .Lambda._cls0(clearcutevent), executor);
    }

    public final ListenableFuture cleanup(long l)
    {
        class .Lambda._cls6
            implements AsyncFunction
        {

            private final long arg$1;

            public final ListenableFuture apply(Object obj)
            {
                long l1 = arg$1;
                obj = (AsyncSQLiteDatabase)obj;
                ListenableFutureTask listenablefuturetask = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls2(((AsyncSQLiteDatabase) (obj)), "clearcut_events_table", "timestamp_ms <= ?", new String[] {
                    String.valueOf(l1)
                }));
                ((AsyncSQLiteDatabase) (obj)).exec.execute(listenablefuturetask);
                return listenablefuturetask;
            }

            .Lambda._cls6(long l)
            {
                arg$1 = l;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncAndClose(new .Lambda._cls6(l), executor);
    }

    public final ListenableFuture clearAll()
    {
        class .Lambda._cls5
            implements AsyncFunction
        {

            public static final AsyncFunction $instance = new .Lambda._cls5();

            public final ListenableFuture apply(Object obj)
            {
                obj = (AsyncSQLiteDatabase)obj;
                ListenableFutureTask listenablefuturetask = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls2(((AsyncSQLiteDatabase) (obj)), "clearcut_events_table", "", new String[0]));
                ((AsyncSQLiteDatabase) (obj)).exec.execute(listenablefuturetask);
                return listenablefuturetask;
            }


            private .Lambda._cls5()
            {
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncAndClose(.Lambda._cls5..instance, executor);
    }

    public final ListenableFuture getAllEventsCounts()
    {
        class .Lambda._cls2
            implements Function
        {

            private final SqliteClearcutEventsStore arg$1;

            public final Object apply(Object obj)
            {
                SqliteClearcutEventsStore sqliteclearcuteventsstore = arg$1;
                SafeSQLiteQueryBuilder safesqlitequerybuilder = (SafeSQLiteQueryBuilder)obj;
                safesqlitequerybuilder.builder.append(" WHERE (account = ?");
                obj = sqliteclearcuteventsstore.accountName;
                if (obj == null)
                {
                    obj = "signedout";
                }
                safesqlitequerybuilder.args.add(obj);
                safesqlitequerybuilder.builder.append(")");
                return null;
            }

            .Lambda._cls2()
            {
                arg$1 = SqliteClearcutEventsStore.this;
            }
        }

        .Lambda._cls2 _lcls2 = new .Lambda._cls2();
        class .Lambda._cls3
            implements AsyncCloseableFunction
        {

            private final Function arg$1;

            public final AsyncCloseable apply(Object obj)
            {
                Object obj1 = arg$1;
                obj = (AsyncSQLiteDatabase)obj;
                Object obj2 = new SafeSQLiteQueryBuilder();
                ((SafeSQLiteQueryBuilder) (obj2)).builder.append("SELECT log_source,event_code, package_name, COUNT(*) as event_count");
                ((SafeSQLiteQueryBuilder) (obj2)).builder.append(" FROM clearcut_events_table");
                ((Function) (obj1)).apply(obj2);
                ((SafeSQLiteQueryBuilder) (obj2)).builder.append(" GROUP BY log_source,event_code, package_name");
                obj1 = ((SafeSQLiteQueryBuilder) (obj2)).build();
                obj2 = ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder.SafeSQLStatement) (obj1)).query;
                obj1 = DatabaseFuture.create(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls1(((AsyncSQLiteDatabase) (obj)), ((com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder.SafeSQLStatement) (obj1)).args, ((String) (obj2))));
                ((AsyncSQLiteDatabase) (obj)).exec.execute(((Runnable) (obj1)));
                obj = new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList();
                return new AsyncCloseable(AbstractTransformFuture.create(((ListenableFuture) (obj1)), new com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable..Lambda._cls0(((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList) (obj)), ((ListenableFuture) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), ((com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.CloseableList) (obj)));
            }

            .Lambda._cls3(Function function)
            {
                arg$1 = function;
            }
        }

        class .Lambda._cls4
            implements Function
        {

            public static final Function $instance = new .Lambda._cls4();

            public final Object apply(Object obj)
            {
                obj = (Cursor)obj;
                HashMap hashmap = new HashMap(((Cursor) (obj)).getCount());
                com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder builder;
                int k;
                for (; ((Cursor) (obj)).moveToNext(); hashmap.put((com.google.identity.growth.proto.Promotion.ClearcutEvent)(GeneratedMessageLite)builder.build(), Integer.valueOf(k)))
                {
                    int i = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("log_source"));
                    int j = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("event_code"));
                    String s = ((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("package_name"));
                    k = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("event_count"));
                    builder = (com.google.identity.growth.proto.Promotion.ClearcutEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    builder.copyOnWrite();
                    com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent = (com.google.identity.growth.proto.Promotion.ClearcutEvent)builder.instance;
                    clearcutevent.bitField0_ = clearcutevent.bitField0_ | 1;
                    clearcutevent.logSource_ = i;
                    builder.copyOnWrite();
                    clearcutevent = (com.google.identity.growth.proto.Promotion.ClearcutEvent)builder.instance;
                    clearcutevent.bitField0_ = clearcutevent.bitField0_ | 2;
                    clearcutevent.eventCode_ = j;
                    builder.copyOnWrite();
                    clearcutevent = (com.google.identity.growth.proto.Promotion.ClearcutEvent)builder.instance;
                    if (s == null)
                    {
                        throw new NullPointerException();
                    }
                    clearcutevent.bitField0_ = clearcutevent.bitField0_ | 4;
                    clearcutevent.bundleIdentifier_ = s;
                }

                return Collections.unmodifiableMap(hashmap);
            }


            private .Lambda._cls4()
            {
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncCloseable(new .Lambda._cls3(_lcls2), executor).transformAndClose(.Lambda._cls4..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final ListenableFuture getEventsCounts(Iterable iterable)
    {
        iterable = iterable.iterator();
        if (!iterable.hasNext())
        {
            iterable = Collections.emptyMap();
            if (iterable == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(iterable);
            }
        } else
        {
            class .Lambda._cls1
                implements Function
            {

                private final SqliteClearcutEventsStore arg$1;
                private final Iterator arg$2;

                public final Object apply(Object obj)
                {
                    SqliteClearcutEventsStore sqliteclearcuteventsstore = arg$1;
                    Iterator iterator = arg$2;
                    SafeSQLiteQueryBuilder safesqlitequerybuilder = (SafeSQLiteQueryBuilder)obj;
                    if (iterator.hasNext())
                    {
                        safesqlitequerybuilder.builder.append(" WHERE (account = ?");
                        obj = sqliteclearcuteventsstore.accountName;
                        if (obj == null)
                        {
                            obj = "signedout";
                        }
                        safesqlitequerybuilder.args.add(obj);
                        safesqlitequerybuilder.builder.append(" AND (");
                        SqliteClearcutEventsStore.appendWherePart(safesqlitequerybuilder, (com.google.identity.growth.proto.Promotion.ClearcutEvent)iterator.next());
                        for (; iterator.hasNext(); SqliteClearcutEventsStore.appendWherePart(safesqlitequerybuilder, (com.google.identity.growth.proto.Promotion.ClearcutEvent)iterator.next()))
                        {
                            safesqlitequerybuilder.builder.append(" OR ");
                        }

                        safesqlitequerybuilder.builder.append("))");
                    }
                    return null;
                }

            .Lambda._cls1(Iterator iterator)
            {
                arg$1 = SqliteClearcutEventsStore.this;
                arg$2 = iterator;
            }
            }

            iterable = new .Lambda._cls1(iterable);
            return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncCloseable(new .Lambda._cls3(iterable), executor).transformAndClose(.Lambda._cls4..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }
}
