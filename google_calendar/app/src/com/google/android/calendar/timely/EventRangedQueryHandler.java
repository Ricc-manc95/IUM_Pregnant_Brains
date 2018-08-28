// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.os.Trace;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.timely:
//            AutoValue_EventRangedQueryHandler_QueryConfig, RangedData, TimelineItem

public abstract class EventRangedQueryHandler
{

    public List allDataReadyListeners;
    public final Subscription calendarListSubscription;
    public boolean doingQuery;
    private final EventsApiImpl eventsApi;
    public int focusDay;
    private LatencyLogger latencyLogger;
    public final LinkedBlockingDeque queriesQueue = new LinkedBlockingDeque();
    public QueryConfig queryConfig;
    public int queryVelocity;
    public RangeQuery rangeQuery;
    private final TimeBoxToTimelineAdapter timeBoxToTimelineAdapter;
    private AtomicInteger tokenCounter;

    public EventRangedQueryHandler(Context context, boolean flag)
    {
        queryConfig = new AutoValue_EventRangedQueryHandler_QueryConfig(false, Collections.emptySet());
        class .Lambda._cls0
            implements Supplier
        {

            private final Context arg$1;

            public final Object get()
            {
                return Utils.getTimeZone(arg$1);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        eventsApi = new EventsApiImpl(context, new .Lambda._cls0(context));
        timeBoxToTimelineAdapter = new TimeBoxToTimelineAdapter(context);
        doingQuery = false;
        queryVelocity = 0;
        tokenCounter = new AtomicInteger();
        latencyLogger = LatencyLoggerHolder.get();
        if (flag)
        {
            context = CalendarListEntryCache.instance;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            class .Lambda._cls1
                implements Consumer
            {

                private final EventRangedQueryHandler arg$1;

                public final void accept(Object obj)
                {
                    EventRangedQueryHandler eventrangedqueryhandler;
                    CalendarListEntry acalendarlistentry[];
                    int i;
                    i = 0;
                    eventrangedqueryhandler = arg$1;
                    acalendarlistentry = (CalendarListEntry[])obj;
                    obj = eventrangedqueryhandler.queriesQueue;
                    obj;
                    JVM INSTR monitorenter ;
                    QueryConfig queryconfig;
                    HashSet hashset;
                    int j;
                    queryconfig = eventrangedqueryhandler.queryConfig;
                    hashset = new HashSet();
                    j = acalendarlistentry.length;
_L2:
                    CalendarListEntry calendarlistentry;
                    if (i >= j)
                    {
                        break MISSING_BLOCK_LABEL_93;
                    }
                    calendarlistentry = acalendarlistentry[i];
                    if (calendarlistentry.isVisible() && calendarlistentry.isSyncEnabled())
                    {
                        hashset.add(calendarlistentry.getDescriptor().calendarKey);
                    }
                    break MISSING_BLOCK_LABEL_180;
                    AutoValue_EventRangedQueryHandler_QueryConfig autovalue_eventrangedqueryhandler_queryconfig;
                    autovalue_eventrangedqueryhandler_queryconfig = new AutoValue_EventRangedQueryHandler_QueryConfig(queryconfig.hideDeclined(), hashset);
                    if (!autovalue_eventrangedqueryhandler_queryconfig.equals(eventrangedqueryhandler.queryConfig))
                    {
                        break MISSING_BLOCK_LABEL_122;
                    }
                    obj;
                    JVM INSTR monitorexit ;
                    return;
                    eventrangedqueryhandler.queryConfig = autovalue_eventrangedqueryhandler_queryconfig;
                    LogUtils.d("MonthQueryHandler", "QueryConfig %s", new Object[] {
                        eventrangedqueryhandler.queryConfig
                    });
                    if (eventrangedqueryhandler.doingQuery)
                    {
                        RangedData rangeddata = eventrangedqueryhandler.rangeQuery.data;
                        eventrangedqueryhandler.refreshData(rangeddata, rangeddata.getStartDay());
                    }
                    obj;
                    JVM INSTR monitorexit ;
                    return;
                    Exception exception;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                    i++;
                    if (true) goto _L2; else goto _L1
_L1:
                }

            .Lambda._cls1()
            {
                arg$1 = EventRangedQueryHandler.this;
            }

                private class QueryConfig
                {

                    abstract Set getVisibleSyncedCalendarIds();

                    abstract boolean hideDeclined();

                    QueryConfig()
                    {
                    }
                }


                private class RangeQuery
                {

                    public QueryConfig config;
                    public RangedData data;
                    public int token;

                    RangeQuery(RangedData rangeddata, int i)
                    {
                        rangeddata.setToken(i);
                        data = rangeddata;
                        token = i;
                        config = null;
                    }
                }

            }

            context = ((ListenableFutureCache)context).subscribe(new .Lambda._cls1(), CalendarExecutor.MAIN, true);
        } else
        {
            context = null;
        }
        calendarListSubscription = context;
    }

    private final void doQuery(RangeQuery rangequery, QueryConfig queryconfig)
    {
        Trace.beginSection("AbstractMonthQueryHandler doQuery");
        final RangedData data;
        doingQuery = true;
        rangeQuery = rangequery;
        rangeQuery.config = queryconfig;
        data = rangequery.data;
        latencyLogger.markAt(10, rangequery.token);
        data;
        JVM INSTR monitorenter ;
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor;
        final int queryToken = rangequery.token;
        if (queryToken != data.getToken())
        {
            break MISSING_BLOCK_LABEL_143;
        }
        boolean flag = queryconfig.hideDeclined();
        rangequery = getQueryFuture(data, eventsApi, flag);
        queryconfig = new _cls1();
        directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (queryconfig != null)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        throw new NullPointerException();
        rangequery;
        data;
        JVM INSTR monitorexit ;
        throw rangequery;
        rangequery;
        Trace.endSection();
        throw rangequery;
        rangequery.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(rangequery, queryconfig), directexecutor);
        data;
        JVM INSTR monitorexit ;
        Trace.endSection();
        return;
        data;
        JVM INSTR monitorexit ;
        LogUtils.d("MonthQueryHandler", "Data's token was changed before query with token %d could begin.", new Object[] {
            Integer.valueOf(rangequery.token)
        });
        class .Lambda._cls2
            implements Runnable
        {

            private final EventRangedQueryHandler arg$1;

            public final void run()
            {
                arg$1.deQueue();
            }

            .Lambda._cls2()
            {
                arg$1 = EventRangedQueryHandler.this;
            }
        }

        CalendarExecutor.MAIN.execute(new .Lambda._cls2());
        Trace.endSection();
        return;
    }

    public abstract RangedData.EventResults createStorage(int i);

    final void deQueue()
    {
        Object obj;
        int j;
        obj = null;
        j = 0;
        LinkedBlockingDeque linkedblockingdeque = queriesQueue;
        linkedblockingdeque;
        JVM INSTR monitorenter ;
        if (queryVelocity != 0) goto _L2; else goto _L1
_L1:
        int i = 0x80000000;
        Iterator iterator = queriesQueue.iterator();
        Object obj1;
        int k;
        obj1 = null;
        k = 0x7fffffff;
_L13:
        Object obj2;
        RangedData rangeddata;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_365;
        }
        obj2 = (RangeQuery)iterator.next();
        rangeddata = ((RangeQuery) (obj2)).data;
        if (!rangeddata.containsDay(focusDay)) goto _L4; else goto _L3
_L3:
        iterator.remove();
        obj = obj2;
_L11:
        if (obj != null) goto _L6; else goto _L5
_L5:
        latencyLogger.markAt(0);
        LogUtils.d("MonthQueryHandler", "No data queued.", new Object[0]);
        doingQuery = false;
        rangeQuery = null;
        if (allDataReadyListeners != null)
        {
            for (obj = allDataReadyListeners.iterator(); ((Iterator) (obj)).hasNext(); ((DataFactory.OnAllEventsDataReadyListener)((Iterator) (obj)).next()).onAllEventsDataReady()) { }
        }
          goto _L7
        obj;
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        throw obj;
_L4:
        int l = rangeddata.getStartDay() - focusDay;
label0:
        {
            if (l <= 0 || l >= k)
            {
                break label0;
            }
            obj1 = obj2;
            k = l;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (l >= 0 || l <= i) goto _L9; else goto _L8
_L8:
        i = rangeddata.getEndDay() - focusDay;
        j = l;
          goto _L10
        if (obj1 != null && obj != null && j > -31 && k > -i)
        {
            obj1 = obj;
        }
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_261;
        }
        queriesQueue.remove(obj1);
        obj = obj1;
          goto _L11
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_277;
        }
        queriesQueue.remove(obj);
          goto _L11
        if (queriesQueue.size() > 0)
        {
            LogUtils.wtf("MonthQueryHandler", "Unexpected failure to find best query", new Object[0]);
        }
_L2:
        obj = (RangeQuery)queriesQueue.poll();
          goto _L11
_L7:
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        return;
_L6:
        doQuery(((RangeQuery) (obj)), queryConfig);
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        return;
_L9:
        i1 = j;
        j = i;
        i = i1;
        obj2 = obj;
_L10:
        i1 = j;
        obj = obj2;
        j = i;
        i = i1;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public FluentFuture getQueryFuture(RangedData rangeddata, EventsApiImpl eventsapiimpl, boolean flag)
    {
        return eventsapiimpl.getAsync(rangeddata.getStartDay(), rangeddata.getEndDay(), flag);
    }

    final void onQueryComplete(int i, RangedData rangeddata, List list)
    {
        if (rangeddata.getToken() != i)
        {
            LogUtils.d("MonthQueryHandler", "Data was recycled before query completed for token: %d with new token %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(rangeddata.getToken())
            });
            latencyLogger.markAt(15, i, "recycled");
            return;
        }
        if (list != null)
        {
            Object obj = timeBoxToTimelineAdapter.entriesToItems(list);
            list = createStorage(((List) (obj)).size());
            for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); list.addTimelineItem((TimelineItem)((Iterator) (obj)).next())) { }
        } else
        {
            list = null;
        }
        if (list == null)
        {
            LogUtils.d("MonthQueryHandler", "Cursor was empty for token: %d", new Object[] {
                Integer.valueOf(i)
            });
            latencyLogger.markAt(11, i, "empty");
            processResults(rangeddata, null);
            return;
        }
        rangeddata;
        JVM INSTR monitorenter ;
        if (rangeddata.getToken() != i)
        {
            break MISSING_BLOCK_LABEL_230;
        }
        latencyLogger.markAt(11, i);
        processResults(rangeddata, list);
_L1:
        rangeddata;
        JVM INSTR monitorexit ;
        LogUtils.d("MonthQueryHandler", "queryComplete for %d with data %s", new Object[] {
            Integer.valueOf(i), rangeddata.getDebugTag()
        });
        return;
        LogUtils.d("MonthQueryHandler", "Data was recycled while cursoring for token: %d with new token %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(rangeddata.getToken())
        });
          goto _L1
        list;
        rangeddata;
        JVM INSTR monitorexit ;
        throw list;
    }

    public abstract void processResults(RangedData rangeddata, RangedData.EventResults eventresults);

    public final void refreshData(RangedData rangeddata, int i)
    {
        boolean flag1 = false;
        LinkedBlockingDeque linkedblockingdeque = queriesQueue;
        linkedblockingdeque;
        JVM INSTR monitorenter ;
        if (!doingQuery) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        obj = rangeQuery.config;
        obj1 = queryConfig;
        if (obj == obj1) goto _L4; else goto _L3
_L3:
        boolean flag = flag1;
        if (obj == null) goto _L6; else goto _L5
_L5:
        flag = flag1;
        if (!obj.equals(obj1)) goto _L6; else goto _L4
_L6:
        if (!flag) goto _L2; else goto _L7
_L7:
        obj = rangeQuery.data;
        if (!((RangedData) (obj)).containsDay(i) || rangeQuery.token != ((RangedData) (obj)).getToken()) goto _L2; else goto _L8
_L8:
        LogUtils.v("MonthQueryHandler", "Query request for %d already running for %s", new Object[] {
            Integer.valueOf(i), rangeddata.toString()
        });
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        return;
_L2:
        obj = queriesQueue;
        obj;
        JVM INSTR monitorenter ;
        obj1 = queriesQueue.iterator();
_L12:
        if (!((Iterator) (obj1)).hasNext()) goto _L10; else goto _L9
_L9:
        if (!((RangeQuery)((Iterator) (obj1)).next()).data.containsDay(i)) goto _L12; else goto _L11
_L11:
        ((Iterator) (obj1)).remove();
_L13:
        rangeddata;
        JVM INSTR monitorenter ;
        rangeddata.recycle(i);
        obj = new RangeQuery(rangeddata, tokenCounter.incrementAndGet());
        LogUtils.d("MonthQueryHandler", "Query created for %s with token %d", new Object[] {
            rangeddata, Integer.valueOf(((RangeQuery) (obj)).token)
        });
        rangeddata;
        JVM INSTR monitorexit ;
        rangeddata = queriesQueue;
        rangeddata;
        JVM INSTR monitorenter ;
        if (!doingQuery)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        queriesQueue.push(obj);
_L14:
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        return;
        rangeddata;
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        throw rangeddata;
_L10:
        obj;
        JVM INSTR monitorexit ;
          goto _L13
        rangeddata;
        obj;
        JVM INSTR monitorexit ;
        throw rangeddata;
        obj;
        rangeddata;
        JVM INSTR monitorexit ;
        throw obj;
        doQuery(((RangeQuery) (obj)), queryConfig);
          goto _L14
        Exception exception;
        exception;
        rangeddata;
        JVM INSTR monitorexit ;
        throw exception;
_L4:
        flag = true;
        if (true) goto _L6; else goto _L15
_L15:
    }

    public final void setHideDeclinedEvents(boolean flag)
    {
        synchronized (queriesQueue)
        {
            queryConfig = new AutoValue_EventRangedQueryHandler_QueryConfig(flag, queryConfig.getVisibleSyncedCalendarIds());
        }
        return;
        exception;
        linkedblockingdeque;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private class _cls1
        implements FutureCallback
    {

        private final EventRangedQueryHandler this$0;
        private final RangedData val$data;
        private final int val$queryToken;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.wtf("MonthQueryHandler", throwable, "Error processing events", new Object[0]);
            onQueryComplete(queryToken, data, null);
            class .Lambda._cls2
                implements Runnable
            {

                private final EventRangedQueryHandler arg$1;

                public final void run()
                {
                    arg$1.deQueue();
                }

                .Lambda._cls2()
                {
                    arg$1 = EventRangedQueryHandler.this;
                }
            }

            CalendarExecutor.MAIN.execute(new .Lambda._cls2());
        }

        public final void onSuccess(Object obj)
        {
            obj = (List)obj;
            Trace.beginSection("AbstractRangedQueryHandler queryComplete");
            onQueryComplete(queryToken, data, ((List) (obj)));
            class .Lambda._cls0
                implements Runnable
            {

                private final EventRangedQueryHandler arg$1;

                public final void run()
                {
                    arg$1.deQueue();
                }

                .Lambda._cls0()
                {
                    arg$1 = EventRangedQueryHandler.this;
                }
            }

            CalendarExecutor.MAIN.execute(new .Lambda._cls0());
            Trace.endSection();
            return;
            obj;
            class .Lambda._cls1
                implements Runnable
            {

                private final EventRangedQueryHandler arg$1;

                public final void run()
                {
                    arg$1.deQueue();
                }

                .Lambda._cls1()
                {
                    arg$1 = EventRangedQueryHandler.this;
                }
            }

            CalendarExecutor.MAIN.execute(new .Lambda._cls1());
            Trace.endSection();
            throw obj;
        }

        _cls1()
        {
            this$0 = EventRangedQueryHandler.this;
            queryToken = i;
            data = rangeddata;
            super();
        }
    }

}
