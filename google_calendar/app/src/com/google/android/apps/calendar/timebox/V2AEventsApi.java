// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.HabitCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.calendar.v2a.shared.storage.AsyncAccountService;
import com.google.calendar.v2a.shared.storage.AsyncEventService;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;

public final class V2AEventsApi
{

    public final AsyncAccountService asyncAccountService;
    public final AsyncEventService asyncEventService;
    public final Supplier timeZoneSupplier;

    V2AEventsApi(Supplier supplier, AsyncAccountService asyncaccountservice, AsyncEventService asynceventservice)
    {
        timeZoneSupplier = supplier;
        asyncAccountService = asyncaccountservice;
        asyncEventService = asynceventservice;
    }

    public final FluentFuture getAsync(int i, int j, Predicate predicate, Function function)
    {
        Object obj = HabitCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        ListenableFuture listenablefuture1 = ((ListenableFutureCache)obj).getValueAsync();
        obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        ListenableFuture listenablefuture = ((ListenableFutureCache)obj).getValueAsync();
        obj = listenablefuture;
        if (predicate != null)
        {
            class .Lambda._cls3
                implements Function
            {

                private final Predicate arg$1;

                public final Object apply(Object obj1)
                {
                    Predicate predicate1 = arg$1;
                    obj1 = Arrays.asList((CalendarListEntry[])obj1);
                    if (obj1 instanceof FluentIterable)
                    {
                        obj1 = (FluentIterable)obj1;
                    } else
                    {
                        obj1 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj1)), ((Iterable) (obj1)));
                    }
                    obj1 = (Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1);
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (predicate1 == null)
                    {
                        throw new NullPointerException();
                    }
                    obj1 = new com.google.common.collect.Iterables._cls4(((Iterable) (obj1)), predicate1);
                    Object aobj[];
                    if (obj1 instanceof FluentIterable)
                    {
                        obj1 = (FluentIterable)obj1;
                    } else
                    {
                        obj1 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj1)), ((Iterable) (obj1)));
                    }
                    obj1 = (Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1);
                    aobj = (Object[])Array.newInstance(com/google/android/calendar/api/calendarlist/CalendarListEntry, 0);
                    if (obj1 instanceof Collection)
                    {
                        obj1 = (Collection)obj1;
                    } else
                    {
                        Iterator iterator = ((Iterable) (obj1)).iterator();
                        obj1 = new ArrayList();
                        Iterators.addAll(((Collection) (obj1)), iterator);
                    }
                    return (CalendarListEntry[])((Collection) (obj1)).toArray(aobj);
                }

            .Lambda._cls3(Predicate predicate)
            {
                arg$1 = predicate;
            }
            }

            class .Lambda._cls1
                implements AsyncFunction
            {

                private final V2AEventsApi arg$1;

                public final ListenableFuture apply(Object obj1)
                {
                    V2AEventsApi v2aeventsapi = arg$1;
                    obj1 = (CalendarListEntry[])obj1;
                    HashMap hashmap = new HashMap();
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    int k = obj1.length;
                    CollectPreconditions.checkNonnegative(k, "arraySize");
                    long l = 5L + (long)k + (long)(k / 10);
                    class .Lambda._cls8
                        implements Function
                    {

                        private final V2AEventsApi arg$1;
                        private final Map arg$2;

                        public final Object apply(Object obj2)
                        {
                            V2AEventsApi v2aeventsapi1 = arg$1;
                            Map map = arg$2;
                            obj2 = (CalendarListEntry)obj2;
                            Account account = ((CalendarListEntry) (obj2)).getDescriptor().account;
                            if (((CalendarListEntry) (obj2)).isVisible() && AccountUtil.isGoogleAccount(account))
                            {
                                ListenableFuture listenablefuture2 = (ListenableFuture)map.get(account);
                                obj2 = listenablefuture2;
                                if (listenablefuture2 == null)
                                {
                                    obj2 = v2aeventsapi1.asyncAccountService.getAccountKey(account.name);
                                    map.put(account, obj2);
                                }
                                return obj2;
                            } else
                            {
                                return CalendarFutures.IMMEDIATE_ABSENT_FUTURE;
                            }
                        }

                            .Lambda._cls8(Map map)
                            {
                                arg$1 = V2AEventsApi.this;
                                arg$2 = map;
                            }
                    }

                    class .Lambda._cls9
                        implements TriFunction
                    {

                        public static final TriFunction $instance = new .Lambda._cls9();

                        public final Object apply(Object obj2, Object obj3, Object obj4)
                        {
                            obj2 = (CalendarListEntry)obj2;
                            Object obj5 = (Optional)obj3;
                            obj3 = (Map)obj4;
                            if (((Optional) (obj5)).isPresent())
                            {
                                obj4 = (com.google.calendar.v2a.shared.storage.proto.CalendarKey.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)CalendarKey.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                                obj5 = (AccountKey)((Optional) (obj5)).get();
                                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                                CalendarKey calendarkey = (CalendarKey)((com.google.calendar.v2a.shared.storage.proto.CalendarKey.Builder) (obj4)).instance;
                                if (obj5 == null)
                                {
                                    throw new NullPointerException();
                                }
                                calendarkey.accountKey_ = ((AccountKey) (obj5));
                                calendarkey.bitField0_ = calendarkey.bitField0_ | 1;
                                obj5 = ((CalendarListEntry) (obj2)).getDescriptor().calendarId;
                                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                                calendarkey = (CalendarKey)((com.google.calendar.v2a.shared.storage.proto.CalendarKey.Builder) (obj4)).instance;
                                if (obj5 == null)
                                {
                                    throw new NullPointerException();
                                }
                                calendarkey.bitField0_ = calendarkey.bitField0_ | 2;
                                calendarkey.calendarId_ = ((String) (obj5));
                                ((Map) (obj3)).put((CalendarKey)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build(), obj2);
                            }
                            return obj3;
                        }


                            private .Lambda._cls9()
                            {
                            }
                    }

                    ArrayList arraylist;
                    if (l > 0x7fffffffL)
                    {
                        k = 0x7fffffff;
                    } else
                    if (l < 0xffffffff80000000L)
                    {
                        k = 0x80000000;
                    } else
                    {
                        k = (int)l;
                    }
                    arraylist = new ArrayList(k);
                    Collections.addAll(arraylist, ((Object []) (obj1)));
                    return CalendarFutures.mapFold(arraylist, v2aeventsapi. new .Lambda._cls8(hashmap), new HashMap(), .Lambda._cls9..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                }

            .Lambda._cls1()
            {
                arg$1 = V2AEventsApi.this;
            }
            }

            class .Lambda._cls2
                implements BiFunction
            {

                private final V2AEventsApi arg$1;
                private final int arg$2;
                private final int arg$3;
                private final Function arg$4;

                public final Object apply(Object obj1, Object obj2)
                {
                    V2AEventsApi v2aeventsapi = arg$1;
                    int k = arg$2;
                    int i2 = arg$3;
                    Object obj3 = arg$4;
                    Object obj4 = (Map)obj1;
                    obj1 = (Map)obj2;
                    obj2 = (Predicate)((Function) (obj3)).apply(obj4);
                    obj3 = (com.google.calendar.v2a.shared.storage.proto.GetEventsRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)GetEventsRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    Object obj5 = ((Map) (obj1)).keySet();
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
                    obj4 = (GetEventsRequest)((com.google.calendar.v2a.shared.storage.proto.GetEventsRequest.Builder) (obj3)).instance;
                    if (!((GetEventsRequest) (obj4)).calendarKey_.isModifiable())
                    {
                        obj4.calendarKey_ = GeneratedMessageLite.mutableCopy(((GetEventsRequest) (obj4)).calendarKey_);
                    }
                    obj4 = ((GetEventsRequest) (obj4)).calendarKey_;
                    Internal.checkNotNull(obj5);
                    class .Lambda._cls10
                        implements Function
                    {

                        private final V2AEventsApi arg$1;
                        private final Predicate arg$2;
                        private final Map arg$3;

                        public final Object apply(Object obj8)
                        {
                            V2AEventsApi v2aeventsapi1 = arg$1;
                            Object obj9 = arg$2;
                            Map map = arg$3;
                            obj8 = (GetEventsResponse)obj8;
                            ArrayList arraylist1 = new ArrayList();
                            obj8 = ((GetEventsResponse) (obj8)).eventBundle_;
                            if (obj8 == null)
                            {
                                throw new NullPointerException();
                            }
                            if (obj9 == null)
                            {
                                throw new NullPointerException();
                            }
                            obj9 = (new com.google.common.collect.Iterables._cls4(((Iterable) (obj8)), ((Predicate) (obj9)))).iterator();
                            while (((Iterator) (obj9)).hasNext()) 
                            {
                                EventBundle eventbundle = (EventBundle)((Iterator) (obj9)).next();
                                TimeZone timezone = (TimeZone)v2aeventsapi1.timeZoneSupplier.get();
                                if (eventbundle.calendarKey_ == null)
                                {
                                    obj8 = CalendarKey.DEFAULT_INSTANCE;
                                } else
                                {
                                    obj8 = eventbundle.calendarKey_;
                                }
                                arraylist1.addAll(V2AToEntryAdapter.toEntries(timezone, eventbundle, (CalendarListEntry)map.get(obj8)));
                            }
                            return arraylist1;
                        }

                            .Lambda._cls10(Predicate predicate, Map map)
                            {
                                arg$1 = V2AEventsApi.this;
                                arg$2 = predicate;
                                arg$3 = map;
                            }
                    }

                    if (obj5 instanceof LazyStringList)
                    {
                        List list = ((LazyStringList)obj5).getUnderlyingElements();
                        obj5 = (LazyStringList)obj4;
                        int j1 = ((List) (obj4)).size();
                        for (obj4 = list.iterator(); ((Iterator) (obj4)).hasNext();)
                        {
                            Object obj6 = ((Iterator) (obj4)).next();
                            if (obj6 == null)
                            {
                                k = ((LazyStringList) (obj5)).size();
                                obj1 = (new StringBuilder(37)).append("Element at index ").append(k - j1).append(" is null.").toString();
                                for (k = ((LazyStringList) (obj5)).size() - 1; k >= j1; k--)
                                {
                                    ((LazyStringList) (obj5)).remove(k);
                                }

                                throw new NullPointerException(((String) (obj1)));
                            }
                            if (obj6 instanceof ByteString)
                            {
                                ((LazyStringList) (obj5)).add((ByteString)obj6);
                            } else
                            {
                                ((LazyStringList) (obj5)).add((String)obj6);
                            }
                        }

                    } else
                    if (obj5 instanceof PrimitiveNonBoxingCollection)
                    {
                        ((List) (obj4)).addAll((Collection)obj5);
                    } else
                    {
                        if ((obj4 instanceof ArrayList) && (obj5 instanceof Collection))
                        {
                            ArrayList arraylist = (ArrayList)obj4;
                            int k1 = ((List) (obj4)).size();
                            arraylist.ensureCapacity(((Collection)obj5).size() + k1);
                        }
                        int l1 = ((List) (obj4)).size();
                        obj5 = ((Iterable) (obj5)).iterator();
                        while (((Iterator) (obj5)).hasNext()) 
                        {
                            Object obj7 = ((Iterator) (obj5)).next();
                            if (obj7 == null)
                            {
                                int l = ((List) (obj4)).size();
                                obj1 = (new StringBuilder(37)).append("Element at index ").append(l - l1).append(" is null.").toString();
                                for (int i1 = ((List) (obj4)).size() - 1; i1 >= l1; i1--)
                                {
                                    ((List) (obj4)).remove(i1);
                                }

                                throw new NullPointerException(((String) (obj1)));
                            }
                            ((List) (obj4)).add(obj7);
                        }
                    }
                    obj4 = (com.google.calendar.v2a.shared.storage.proto.DayRange.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)DayRange.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    obj5 = (DayRange)((com.google.calendar.v2a.shared.storage.proto.DayRange.Builder) (obj4)).instance;
                    obj5.bitField0_ = ((DayRange) (obj5)).bitField0_ | 1;
                    obj5.firstDay_ = k - 0x253d8c;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).copyOnWrite();
                    obj5 = (DayRange)((com.google.calendar.v2a.shared.storage.proto.DayRange.Builder) (obj4)).instance;
                    obj5.bitField0_ = ((DayRange) (obj5)).bitField0_ | 2;
                    obj5.lastDay_ = i2 - 0x253d8c;
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).copyOnWrite();
                    obj5 = (GetEventsRequest)((com.google.calendar.v2a.shared.storage.proto.GetEventsRequest.Builder) (obj3)).instance;
                    obj5.dayRange_ = (DayRange)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj4)).build();
                    obj5.bitField0_ = ((GetEventsRequest) (obj5)).bitField0_ | 1;
                    obj3 = (GetEventsRequest)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj3)).build();
                    return AbstractTransformFuture.create(v2aeventsapi.asyncEventService.getEvents(((GetEventsRequest) (obj3))), v2aeventsapi. new .Lambda._cls10(((Predicate) (obj2)), ((Map) (obj1))), CalendarExecutor.BACKGROUND);
                }

            .Lambda._cls2(int i, int j, Function function)
            {
                arg$1 = V2AEventsApi.this;
                arg$2 = i;
                arg$3 = j;
                arg$4 = function;
            }
            }

            if (listenablefuture instanceof FluentFuture)
            {
                obj = (FluentFuture)listenablefuture;
            } else
            {
                obj = new ForwardingFluentFuture(listenablefuture);
            }
            obj = (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls3(predicate), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.API));
        }
        if (obj instanceof FluentFuture)
        {
            predicate = (FluentFuture)obj;
        } else
        {
            predicate = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        return CalendarFutures.transformAsync(listenablefuture1, (FluentFuture)AbstractTransformFuture.create(predicate, new .Lambda._cls1(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls2(i, j, function), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }
}
