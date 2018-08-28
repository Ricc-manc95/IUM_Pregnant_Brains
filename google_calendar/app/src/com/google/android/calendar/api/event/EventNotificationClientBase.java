// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventNotificationClient, HabitNotificationClient

abstract class EventNotificationClientBase
    implements EventNotificationClient
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/EventNotificationClientBase);
    public Context context;
    public HabitNotificationClient habitNotificationClient;

    EventNotificationClientBase()
    {
    }

    static FluentIterable getDefaultReminders(CalendarListEntry calendarlistentry, boolean flag)
    {
        class .Lambda._cls2
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls2();

            public final boolean apply(Object obj)
            {
                return EventNotificationClientBase.lambda$getDefaultReminders$3$EventNotificationClientBase((Notification)obj);
            }


            private .Lambda._cls2()
            {
            }
        }

        Predicate predicate;
        byte byte0;
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        calendarlistentry = calendarlistentry.getDefaultNotifications(byte0);
        if (calendarlistentry instanceof FluentIterable)
        {
            calendarlistentry = (FluentIterable)calendarlistentry;
        } else
        {
            calendarlistentry = new com.google.common.collect.FluentIterable._cls1(calendarlistentry, calendarlistentry);
        }
        predicate = .Lambda._cls2..instance;
        calendarlistentry = (Iterable)((FluentIterable) (calendarlistentry)).iterableDelegate.or(calendarlistentry);
        if (calendarlistentry == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        calendarlistentry = new com.google.common.collect.Iterables._cls4(calendarlistentry, predicate);
        if (calendarlistentry instanceof FluentIterable)
        {
            return (FluentIterable)calendarlistentry;
        } else
        {
            return new com.google.common.collect.FluentIterable._cls1(calendarlistentry, calendarlistentry);
        }
    }

    static final boolean lambda$getDefaultReminders$3$EventNotificationClientBase(Notification notification)
    {
        return notification.method == 1;
    }

    static final List lambda$getNotifications$2$EventNotificationClientBase(com.google.common.collect.ImmutableList.Builder builder, List list)
    {
        builder = (com.google.common.collect.ImmutableList.Builder)builder.addAll(list);
        builder.forceCopy = true;
        return ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    static long triggerMillis(long l, int i)
    {
        return l - TimeUnit.MINUTES.toMillis(i);
    }

    abstract ListenableFuture getEventNotificationsAndHabitInstancesAsync(Iterable iterable, long l, long l1);

    public final ListenableFuture getNotifications(Iterable iterable, long l, long l1)
    {
        if (Iterables.isEmpty(iterable))
        {
            iterable = ImmutableList.of();
            if (iterable == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(iterable);
            }
        }
        com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
        Object obj = getEventNotificationsAndHabitInstancesAsync(iterable, l, l1);
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final EventNotificationClientBase arg$1;
            private final com.google.common.collect.ImmutableList.Builder arg$2;
            private final Iterable arg$3;
            private final long arg$4;
            private final long arg$5;

            public final ListenableFuture apply(Object obj1)
            {
                Iterable iterable1;
                Object obj2;
                Object obj3;
                long l2;
                long l3;
                obj2 = arg$1;
                obj3 = arg$2;
                iterable1 = arg$3;
                l2 = arg$4;
                l3 = arg$5;
                obj1 = (Pair)obj1;
                obj3 = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).addAll((Iterable)((Pair) (obj1)).first);
                obj2 = ((EventNotificationClientBase) (obj2)).habitNotificationClient;
                obj3 = (List)((Pair) (obj1)).second;
                if (HabitNotificationClient.areHabitNotificationsSupported()) goto _L2; else goto _L1
_L1:
                obj1 = ImmutableList.of();
                class .Lambda._cls3
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls3();

                    public final Object apply(Object obj5)
                    {
                        obj5 = (Throwable)obj5;
                        LogUtils.e(EventNotificationClientBase.TAG, ((Throwable) (obj5)), "Failed obtaining notifications for habits.", new Object[0]);
                        return ImmutableList.of();
                    }


                        private .Lambda._cls3()
                        {
                        }
                }

                if (obj1 == null)
                {
                    obj1 = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                } else
                {
                    obj1 = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj1);
                }
_L4:
                return AbstractCatchingFuture.create(((ListenableFuture) (obj1)), java/lang/Throwable, .Lambda._cls3..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
_L2:
                Function function;
                if (obj3 instanceof FluentIterable)
                {
                    obj1 = (FluentIterable)obj3;
                } else
                {
                    obj1 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj3)), ((Iterable) (obj3)));
                }
                function = HabitNotificationClient..Lambda._cls0.$instance;
                obj1 = (Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1);
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (function == null)
                {
                    throw new NullPointerException();
                }
                obj1 = new com.google.common.collect.Iterables._cls5(((Iterable) (obj1)), function);
                if (obj1 instanceof FluentIterable)
                {
                    obj1 = (FluentIterable)obj1;
                } else
                {
                    obj1 = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj1)), ((Iterable) (obj1)));
                }
                obj1 = (Iterable)((FluentIterable) (obj1)).iterableDelegate.or(obj1);
                if (!(obj1 instanceof Collection))
                {
                    break; /* Loop/switch isn't completed */
                }
                obj1 = ImmutableSet.copyOf((Collection)obj1);
_L5:
                obj1 = CalendarApi.Habits.read((HabitDescriptor[])((Set) (obj1)).toArray(new HabitDescriptor[((Set) (obj1)).size()]));
                function = HabitNotificationClient..Lambda._cls1.$instance;
                obj1 = AbstractTransformFuture.create(AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj1))), new com.google.android.apps.calendar.util.gms.GmsFutures..Lambda._cls0(function), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new HabitNotificationClient..Lambda._cls2(((HabitNotificationClient) (obj2)), iterable1, ((List) (obj3)), l2, l3), CalendarExecutor.BACKGROUND);
                if (true) goto _L4; else goto _L3
_L3:
                obj1 = ((Iterable) (obj1)).iterator();
                if (!((Iterator) (obj1)).hasNext())
                {
                    obj1 = RegularImmutableSet.EMPTY;
                } else
                {
                    Object obj4 = ((Iterator) (obj1)).next();
                    if (!((Iterator) (obj1)).hasNext())
                    {
                        obj1 = new SingletonImmutableSet(obj4);
                    } else
                    {
                        obj1 = ((com.google.common.collect.ImmutableSet.Builder)((com.google.common.collect.ImmutableSet.Builder)(new com.google.common.collect.ImmutableSet.Builder()).add(obj4)).addAll(((Iterator) (obj1)))).build();
                    }
                }
                  goto _L5
                if (true) goto _L4; else goto _L6
_L6:
            }

            .Lambda._cls0(com.google.common.collect.ImmutableList.Builder builder, Iterable iterable, long l, long l1)
            {
                arg$1 = EventNotificationClientBase.this;
                arg$2 = builder;
                arg$3 = iterable;
                arg$4 = l;
                arg$5 = l1;
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final com.google.common.collect.ImmutableList.Builder arg$1;

            public final Object apply(Object obj1)
            {
                return EventNotificationClientBase.lambda$getNotifications$2$EventNotificationClientBase(arg$1, (List)obj1);
            }

            .Lambda._cls1(com.google.common.collect.ImmutableList.Builder builder)
            {
                arg$1 = builder;
            }
        }

        if (obj instanceof FluentFuture)
        {
            obj = (FluentFuture)obj;
        } else
        {
            obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
        }
        return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls0(builder, iterable, l, l1), CalendarExecutor.BACKGROUND), new .Lambda._cls1(builder), CalendarExecutor.BACKGROUND);
    }

    public void initialize(Context context1)
    {
        context = context1;
        habitNotificationClient = new HabitNotificationClient(context1);
    }

}
