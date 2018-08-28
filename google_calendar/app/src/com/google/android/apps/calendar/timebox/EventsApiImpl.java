// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.accounts.Account;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.config.remote.UnifiedSyncAndStoreFeature;
import com.google.android.apps.calendar.timebox.bucket.Bucketer;
import com.google.android.apps.calendar.timebox.bucket.EventBucketerConfig;
import com.google.android.apps.calendar.timebox.cp.CpEventsApi;
import com.google.android.apps.calendar.timebox.cp.EventsQueryInfo;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.common.ApiOperation;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.V2AEventKey;
import com.google.android.calendar.utils.habit.HabitInstancesUtil;
import com.google.calendar.v2a.android.util.metric.MetricUtils;
import com.google.calendar.v2a.shared.android.AndroidSharedApi;
import com.google.calendar.v2a.shared.storage.AsyncEventService;
import com.google.calendar.v2a.shared.storage.proto.GetEventRequest;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventsApi, V2AEventsApi, TimeRangeEntry, Item, 
//            GoalItem, TimeBoxUtil

public class EventsApiImpl
    implements EventsApi
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timebox/EventsApiImpl);
    private static final AtomicInteger logCounter = new AtomicInteger(0);
    public final Context context;
    public final CpEventsApi cpEventsApi;
    private Supplier timeZoneSupplier;
    public final V2AEventsApi v2aEventsApi;

    public EventsApiImpl(Context context1, Supplier supplier)
    {
        class .Lambda._cls0
            implements Supplier
        {

            public static final Supplier $instance = new .Lambda._cls0();

            public final Object get()
            {
                return EventsApiImpl.lambda$createCalendarsFutureSupplier$0$EventsApiImpl();
            }


            private .Lambda._cls0()
            {
            }
        }

        CpEventsApi cpeventsapi = new CpEventsApi(context1, supplier, .Lambda._cls0..instance);
        Object obj;
        if (!(context1.getApplicationContext() instanceof com.google.calendar.v2a.shared.android.AndroidSharedApi.Holder) || !RemoteFeatureConfig.UNIFIED_SYNC_AND_STORE.enabled() || !RemoteFeatureConfig.UNIFIED_SYNC_AND_STORE.getSupportsEvents())
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = ((com.google.calendar.v2a.shared.android.AndroidSharedApi.Holder)context1.getApplicationContext()).getSharedApi();
            if (((Optional) (obj)).isPresent())
            {
                obj = new V2AEventsApi(supplier, ((AndroidSharedApi)((Optional) (obj)).get()).accountService(), ((AndroidSharedApi)((Optional) (obj)).get()).eventService());
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                obj = new Present(obj);
            } else
            {
                obj = Absent.INSTANCE;
            }
        }
        this(context1, supplier, cpeventsapi, (V2AEventsApi)((Optional) (obj)).orNull());
    }

    private EventsApiImpl(Context context1, Supplier supplier, CpEventsApi cpeventsapi, V2AEventsApi v2aeventsapi)
    {
        context = context1;
        timeZoneSupplier = supplier;
        cpEventsApi = cpeventsapi;
        v2aEventsApi = v2aeventsapi;
    }

    static final List bridge$lambda$0$EventsApiImpl(List list)
    {
        ArrayList arraylist = new ArrayList();
        for (list = list.iterator(); list.hasNext();)
        {
            TimeRangeEntry timerangeentry = (TimeRangeEntry)list.next();
            if (!((Item)timerangeentry.getValue() instanceof GoalItem))
            {
                LogUtils.wtf(TAG, "Encountered non goal item", new Object[0]);
            } else
            {
                arraylist.add((TimeRangeEntry)timerangeentry);
            }
        }

        return arraylist;
    }

    static final ListenableFuture lambda$createCalendarsFutureSupplier$0$EventsApiImpl()
    {
        ListenableFutureCache listenablefuturecache = CalendarListEntryCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        listenablefuturecache = (ListenableFutureCache)listenablefuturecache;
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])listenablefuturecache.result;
        if (acalendarlistentry != null)
        {
            if (acalendarlistentry == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(acalendarlistentry);
            }
        } else
        {
            return listenablefuturecache.getValueAsync();
        }
    }

    static final List lambda$getAsync$2$EventsApiImpl(List list)
    {
        return ImmutableList.copyOf(Bucketer.bucket(new EventBucketerConfig(), list));
    }

    static final List lambda$searchAsync$3$EventsApiImpl(List list, List list1)
    {
        list = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)ImmutableList.builder().addAll(list)).addAll(list1);
        list.forceCopy = true;
        return ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (list)).contents, ((com.google.common.collect.ImmutableList.Builder) (list)).size);
    }

    static final boolean lambda$searchGoalsAsync$4$EventsApiImpl(Account account, CalendarListEntry calendarlistentry)
    {
        return calendarlistentry.isPrimary() && calendarlistentry.getDescriptor().account.equals(account);
    }

    public final FluentFuture getAsync(int i, int j, boolean flag)
    {
        boolean flag2 = true;
        boolean flag1;
        if (i <= j)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        }
        int k = logCounter.incrementAndGet();
        LogUtils.d(TAG, "%s> getAsync: %s - %s, hideDeclined: %s", new Object[] {
            Integer.valueOf(k), Integer.valueOf(i), Integer.valueOf(j), Boolean.valueOf(flag)
        });
        Object obj;
        Object obj1;
        boolean flag3;
        if (v2aEventsApi == null)
        {
            obj = Collections.emptyList();
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
        } else
        {
            obj = v2aEventsApi.getAsync(i, j, null, new V2AEventsApi..Lambda._cls0(flag, null));
        }
        obj1 = cpEventsApi;
        if (v2aEventsApi != null)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (i <= j)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        }
        Object obj2 = android.provider.CalendarContract.Instances.CONTENT_BY_DAY_URI.buildUpon();
        ContentUris.appendId(((android.net.Uri.Builder) (obj2)), i);
        ContentUris.appendId(((android.net.Uri.Builder) (obj2)), j);
        obj2 = ((android.net.Uri.Builder) (obj2)).build();
        obj2 = ((CpEventsApi) (obj1)).queryAsync(((CpEventsApi) (obj1)).context, ((Uri) (obj2)), EventsQueryInfo.getInstancesSelection(flag, flag3), null, null);
        obj1 = ApiOperation.EVENT_INSTANCES_LIST;
        class .Lambda._cls1
            implements BiFunction
        {

            private final int arg$1;

            public final Object apply(Object obj3, Object obj4)
            {
                int l = arg$1;
                obj3 = (List)obj3;
                obj4 = (List)obj4;
                LogUtils.d(EventsApiImpl.TAG, "%s> V2A Events: %s", new Object[] {
                    Integer.valueOf(l), Integer.valueOf(((List) (obj3)).size())
                });
                LogUtils.d(EventsApiImpl.TAG, "%s> CP Events: %s", new Object[] {
                    Integer.valueOf(l), Integer.valueOf(((List) (obj4)).size())
                });
                obj3 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableList.Builder)ImmutableList.builder().addAll(((Iterable) (obj3)))).addAll(((Iterable) (obj4)));
                obj3.forceCopy = true;
                return ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj3)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj3)).size);
            }

            .Lambda._cls1(int i)
            {
                arg$1 = i;
            }
        }

        obj = CalendarFutures.transform(((ListenableFuture) (obj)), ((ListenableFuture) (obj2)), new .Lambda._cls1(k), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        obj1 = (FluentFuture)MetricUtils.withMetrics(new com.google.common.base.Functions.ConstantFunction(com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS), ((ListenableFuture) (obj)), ((com.google.calendar.v2a.android.util.metric.MetricUtils.Operation) (obj1)));
        obj = context;
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).alternate_timeline();
        flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
        obj = Features.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)obj).experimental_options();
        obj = obj1;
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj3)
            {
                return EventsApiImpl.lambda$getAsync$2$EventsApiImpl((List)obj3);
            }


            private .Lambda._cls2()
            {
            }
        }

        if (flag)
        {
            obj = (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj1)), .Lambda._cls2..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        return ((FluentFuture) (obj));
    }

    public final FluentFuture getAsync(EventKey eventkey)
    {
        ApiOperation apioperation;
        if ((eventkey instanceof V2AEventKey) && v2aEventsApi != null)
        {
            V2AEventsApi v2aeventsapi = v2aEventsApi;
            Object obj = (V2AEventKey)eventkey;
            eventkey = CalendarListEntryCache.instance;
            if (eventkey == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            eventkey = AbstractTransformFuture.create(((ListenableFutureCache)eventkey).getValueAsync(), new V2AEventsApi..Lambda._cls6(((V2AEventKey) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            AsyncEventService asynceventservice = v2aeventsapi.asyncEventService;
            com.google.calendar.v2a.shared.storage.proto.GetEventRequest.Builder builder = (com.google.calendar.v2a.shared.storage.proto.GetEventRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)GetEventRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            obj = ((V2AEventKey) (obj)).getV2Key();
            builder.copyOnWrite();
            GetEventRequest geteventrequest = (GetEventRequest)builder.instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            geteventrequest.eventKey_ = ((com.google.calendar.v2a.shared.storage.proto.EventKey) (obj));
            geteventrequest.bitField0_ = geteventrequest.bitField0_ | 1;
            eventkey = CalendarFutures.transform(eventkey, asynceventservice.getEvent((GetEventRequest)(GeneratedMessageLite)builder.build()), new V2AEventsApi..Lambda._cls7(v2aeventsapi), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        } else
        if (eventkey instanceof CpEventKey)
        {
            CpEventsApi cpeventsapi = cpEventsApi;
            CpEventKey cpeventkey = (CpEventKey)eventkey;
            if (cpeventkey.hasStartMillis())
            {
                eventkey = Long.valueOf(cpeventkey.startMillis());
                if (eventkey == null)
                {
                    eventkey = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    eventkey = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(eventkey);
                }
                if (eventkey instanceof FluentFuture)
                {
                    eventkey = (FluentFuture)eventkey;
                } else
                {
                    eventkey = new ForwardingFluentFuture(eventkey);
                }
            } else
            {
                eventkey = (FluentFuture)CalendarExecutor.API.submit(new com.google.android.apps.calendar.timebox.cp.CpEventsApi..Lambda._cls0(cpeventsapi, cpeventkey));
            }
            eventkey = (FluentFuture)AbstractTransformFuture.create(eventkey, new com.google.android.apps.calendar.timebox.cp.CpEventsApi..Lambda._cls1(cpeventsapi, cpeventkey), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        } else
        {
            throw new IllegalArgumentException(eventkey.getClass().toString());
        }
        apioperation = ApiOperation.EVENT_INSTANCES_GET;
        return (FluentFuture)MetricUtils.withMetrics(new com.google.common.base.Functions.ConstantFunction(com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS), eventkey, apioperation);
    }

    public final FluentFuture searchGoalsAsync(Account account, String s, long l, long l1)
    {
        if (v2aEventsApi != null)
        {
            TimeZone timezone = (TimeZone)timeZoneSupplier.get();
            class .Lambda._cls4
                implements Predicate
            {

                private final Account arg$1;

                public final boolean apply(Object obj1)
                {
                    return EventsApiImpl.lambda$searchGoalsAsync$4$EventsApiImpl(arg$1, (CalendarListEntry)obj1);
                }

            .Lambda._cls4(Account account)
            {
                arg$1 = account;
            }
            }

            class .Lambda._cls5
                implements Function
            {

                public static final Function $instance = new .Lambda._cls5();

                public final Object apply(Object obj1)
                {
                    return EventsApiImpl.bridge$lambda$0$EventsApiImpl((List)obj1);
                }


            private .Lambda._cls5()
            {
            }
            }

            account = (FluentFuture)AbstractTransformFuture.create(v2aEventsApi.getAsync(TimeBoxUtil.msToJulianDay(timezone, l), TimeBoxUtil.msToJulianDay(timezone, l1), new .Lambda._cls4(account), new V2AEventsApi..Lambda._cls0(false, s)), .Lambda._cls5..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        } else
        {
            CpEventsApi cpeventsapi = cpEventsApi;
            Object obj = android.provider.CalendarContract.Instances.CONTENT_URI.buildUpon();
            ContentUris.appendId(((android.net.Uri.Builder) (obj)), l);
            ContentUris.appendId(((android.net.Uri.Builder) (obj)), l1);
            obj = ((android.net.Uri.Builder) (obj)).build();
            String as[] = HabitInstancesUtil.getSelectionArgs(s);
            s = new String[as.length + 2];
            s[0] = account.name;
            s[1] = account.type;
            s[2] = as[0];
            if (as.length > 1)
            {
                s[3] = as[1];
            }
            account = String.format("account_name = ? AND account_type = ? AND deleted=0 AND sync_data9 & 256=0 AND %s", new Object[] {
                "(sync_data8=? OR sync_data8 LIKE ?)"
            });
            class .Lambda._cls6
                implements Function
            {

                public static final Function $instance = new .Lambda._cls6();

                public final Object apply(Object obj1)
                {
                    return EventsApiImpl.bridge$lambda$0$EventsApiImpl((List)obj1);
                }


            private .Lambda._cls6()
            {
            }
            }

            account = (FluentFuture)AbstractTransformFuture.create(cpeventsapi.queryAsync(cpeventsapi.context, ((Uri) (obj)), account, s, "dtstart ASC"), .Lambda._cls6..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        s = ApiOperation.EVENT_INSTANCES_SEARCH_HABITS;
        return (FluentFuture)MetricUtils.withMetrics(new com.google.common.base.Functions.ConstantFunction(com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS), account, s);
    }

}
