// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.PromotionKeysHelper;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.VisualElementEventsStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.SafeSQLiteQueryBuilder;
import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            GrowthDbHelper

public final class SqliteVisualElementEventsStore
    implements VisualElementEventsStore
{

    public final String accountName;
    private final ListeningExecutorService executor;
    private final GrowthDbHelper growthDbHelper;

    public SqliteVisualElementEventsStore(ListeningExecutorService listeningexecutorservice, GrowthDbHelper growthdbhelper, String s, Clock clock)
    {
        executor = listeningexecutorservice;
        accountName = s;
        growthDbHelper = growthdbhelper;
    }

    static void appendWherePart(SafeSQLiteQueryBuilder safesqlitequerybuilder, com.google.identity.growth.proto.Promotion.VisualElementEvent visualelementevent)
    {
        safesqlitequerybuilder.builder.append("(node_id_path = ?");
        Object obj = String.valueOf(PromotionKeysHelper.of(visualelementevent));
        safesqlitequerybuilder.args.add(obj);
        safesqlitequerybuilder.builder.append(" AND action = ?)");
        obj = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.forNumber(visualelementevent.action_);
        visualelementevent = ((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj));
        if (obj == null)
        {
            visualelementevent = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.UNKNOWN;
        }
        int i = ((com.google.identity.growth.proto.Promotion.VisualElementEvent.Action) (visualelementevent)).value;
        safesqlitequerybuilder.args.add(String.valueOf(i));
    }

    private final ListenableFuture doGetEventsCounts(Function function)
    {
        class .Lambda._cls3
            implements AsyncCloseableFunction
        {

            private final Function arg$1;

            public final AsyncCloseable apply(Object obj)
            {
                Object obj1 = arg$1;
                obj = (AsyncSQLiteDatabase)obj;
                Object obj2 = new SafeSQLiteQueryBuilder();
                ((SafeSQLiteQueryBuilder) (obj2)).builder.append("SELECT node_id_path,action, COUNT(*) as event_count");
                ((SafeSQLiteQueryBuilder) (obj2)).builder.append(" FROM visual_element_events_table");
                ((Function) (obj1)).apply(obj2);
                ((SafeSQLiteQueryBuilder) (obj2)).builder.append(" GROUP BY node_id_path,action");
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

            private final SqliteVisualElementEventsStore arg$1;

            public final Object apply(Object obj)
            {
                Object obj1 = arg$1;
                obj = (Cursor)obj;
                obj1 = new HashMap(((Cursor) (obj)).getCount());
                while (((Cursor) (obj)).moveToNext()) 
                {
                    Object obj2 = ((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("node_id_path"));
                    int i = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("action"));
                    int l1 = ((Cursor) (obj)).getInt(((Cursor) (obj)).getColumnIndexOrThrow("event_count"));
                    com.google.identity.growth.proto.Promotion.VisualElementEvent.Builder builder = (com.google.identity.growth.proto.Promotion.VisualElementEvent.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    Object obj3 = com.google.identity.growth.proto.Promotion.VisualElementEvent.Action.forNumber(i);
                    builder.copyOnWrite();
                    com.google.identity.growth.proto.Promotion.VisualElementEvent visualelementevent = (com.google.identity.growth.proto.Promotion.VisualElementEvent)builder.instance;
                    if (obj3 == null)
                    {
                        throw new NullPointerException();
                    }
                    visualelementevent.bitField0_ = visualelementevent.bitField0_ | 1;
                    visualelementevent.action_ = ((com.google.identity.growth.proto.Promotion.VisualElementEvent.Action) (obj3)).value;
                    String as[] = TextUtils.split(((String) (obj2)), ",");
                    obj3 = new ArrayList();
                    int i2 = as.length;
                    for (int j = 0; j < i2; j++)
                    {
                        ((List) (obj3)).add(Integer.valueOf(Integer.parseInt(as[j])));
                    }

                    builder.copyOnWrite();
                    as = (com.google.identity.growth.proto.Promotion.VisualElementEvent)builder.instance;
                    if (!((com.google.identity.growth.proto.Promotion.VisualElementEvent) (as)).nodeIdPath_.isModifiable())
                    {
                        as.nodeIdPath_ = GeneratedMessageLite.mutableCopy(((com.google.identity.growth.proto.Promotion.VisualElementEvent) (as)).nodeIdPath_);
                    }
                    as = ((com.google.identity.growth.proto.Promotion.VisualElementEvent) (as)).nodeIdPath_;
                    Internal.checkNotNull(obj3);
                    if (obj3 instanceof LazyStringList)
                    {
                        List list = ((LazyStringList)obj3).getUnderlyingElements();
                        obj3 = (LazyStringList)as;
                        int j2 = as.size();
                        for (as = list.iterator(); as.hasNext();)
                        {
                            Object obj4 = as.next();
                            if (obj4 == null)
                            {
                                int k = ((LazyStringList) (obj3)).size();
                                obj = (new StringBuilder(37)).append("Element at index ").append(k - j2).append(" is null.").toString();
                                for (int l = ((LazyStringList) (obj3)).size() - 1; l >= j2; l--)
                                {
                                    ((LazyStringList) (obj3)).remove(l);
                                }

                                throw new NullPointerException(((String) (obj)));
                            }
                            if (obj4 instanceof ByteString)
                            {
                                ((LazyStringList) (obj3)).add((ByteString)obj4);
                            } else
                            {
                                ((LazyStringList) (obj3)).add((String)obj4);
                            }
                        }

                    } else
                    if (obj3 instanceof PrimitiveNonBoxingCollection)
                    {
                        as.addAll((Collection)obj3);
                    } else
                    {
                        if ((as instanceof ArrayList) && (obj3 instanceof Collection))
                        {
                            ArrayList arraylist = (ArrayList)as;
                            int i1 = as.size();
                            arraylist.ensureCapacity(((Collection)obj3).size() + i1);
                        }
                        int k2 = as.size();
                        obj3 = ((Iterable) (obj3)).iterator();
                        while (((Iterator) (obj3)).hasNext()) 
                        {
                            Object obj5 = ((Iterator) (obj3)).next();
                            if (obj5 == null)
                            {
                                int j1 = as.size();
                                obj = (new StringBuilder(37)).append("Element at index ").append(j1 - k2).append(" is null.").toString();
                                for (int k1 = as.size() - 1; k1 >= k2; k1--)
                                {
                                    as.remove(k1);
                                }

                                throw new NullPointerException(((String) (obj)));
                            }
                            as.add(obj5);
                        }
                    }
                    ((Map) (obj1)).put((com.google.identity.growth.proto.Promotion.VisualElementEvent)(GeneratedMessageLite)builder.build(), Integer.valueOf(l1));
                }
                return Collections.unmodifiableMap(((Map) (obj1)));
            }

            .Lambda._cls4()
            {
                arg$1 = SqliteVisualElementEventsStore.this;
            }
        }

        return growthDbHelper.asyncSQLiteOpenHelper.openDatabase().transformAsyncCloseable(new .Lambda._cls3(function), executor).transformAndClose(new .Lambda._cls4(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
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
                ListenableFutureTask listenablefuturetask = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls2(((AsyncSQLiteDatabase) (obj)), "visual_element_events_table", "timestamp_ms <= ?", new String[] {
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
                ListenableFutureTask listenablefuturetask = new ListenableFutureTask(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteDatabase._cls2(((AsyncSQLiteDatabase) (obj)), "visual_element_events_table", "", new String[0]));
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

            private final SqliteVisualElementEventsStore arg$1;

            public final Object apply(Object obj)
            {
                SqliteVisualElementEventsStore sqlitevisualelementeventsstore = arg$1;
                SafeSQLiteQueryBuilder safesqlitequerybuilder = (SafeSQLiteQueryBuilder)obj;
                safesqlitequerybuilder.builder.append(" WHERE (account = ?");
                obj = sqlitevisualelementeventsstore.accountName;
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
                arg$1 = SqliteVisualElementEventsStore.this;
            }
        }

        return doGetEventsCounts(new .Lambda._cls2());
    }

    public final ListenableFuture getEventsCounts(Iterable iterable)
    {
        iterable = iterable.iterator();
        class .Lambda._cls1
            implements Function
        {

            private final SqliteVisualElementEventsStore arg$1;
            private final Iterator arg$2;

            public final Object apply(Object obj)
            {
                SqliteVisualElementEventsStore sqlitevisualelementeventsstore = arg$1;
                Iterator iterator = arg$2;
                SafeSQLiteQueryBuilder safesqlitequerybuilder = (SafeSQLiteQueryBuilder)obj;
                if (iterator.hasNext())
                {
                    safesqlitequerybuilder.builder.append(" WHERE (account = ?");
                    obj = sqlitevisualelementeventsstore.accountName;
                    if (obj == null)
                    {
                        obj = "signedout";
                    }
                    safesqlitequerybuilder.args.add(obj);
                    safesqlitequerybuilder.builder.append(" AND (");
                    SqliteVisualElementEventsStore.appendWherePart(safesqlitequerybuilder, (com.google.identity.growth.proto.Promotion.VisualElementEvent)iterator.next());
                    for (; iterator.hasNext(); SqliteVisualElementEventsStore.appendWherePart(safesqlitequerybuilder, (com.google.identity.growth.proto.Promotion.VisualElementEvent)iterator.next()))
                    {
                        safesqlitequerybuilder.builder.append(" OR ");
                    }

                    safesqlitequerybuilder.builder.append("))");
                }
                return null;
            }

            .Lambda._cls1(Iterator iterator)
            {
                arg$1 = SqliteVisualElementEventsStore.this;
                arg$2 = iterator;
            }
        }

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
            return doGetEventsCounts(new .Lambda._cls1(iterable));
        }
    }
}
