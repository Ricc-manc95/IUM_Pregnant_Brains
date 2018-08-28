// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.util.Pair;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventNotificationClientBase, HabitNotificationClient

final class arg._cls5
    implements AsyncFunction
{

    private final EventNotificationClientBase arg$1;
    private final com.google.common.collect.e arg$2;
    private final Iterable arg$3;
    private final long arg$4;
    private final long arg$5;

    public final ListenableFuture apply(Object obj)
    {
        Iterable iterable;
        Object obj1;
        Object obj2;
        long l;
        long l1;
        obj1 = arg$1;
        obj2 = arg$2;
        iterable = arg$3;
        l = arg$4;
        l1 = arg$5;
        obj = (Pair)obj;
        obj2 = (com.google.common.collect.e..Lambda._cls0.arg._cls5)((com.google.common.collect.e..Lambda._cls0.arg._cls5) (obj2))._mth5((Iterable)((Pair) (obj)).first);
        obj1 = ((EventNotificationClientBase) (obj1)).habitNotificationClient;
        obj2 = (List)((Pair) (obj)).second;
        if (HabitNotificationClient.areHabitNotificationsSupported()) goto _L2; else goto _L1
_L1:
        obj = ImmutableList.of();
        if (obj == null)
        {
            obj = com.google.common.util.concurrent.ure.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ure(obj);
        }
_L4:
        return AbstractCatchingFuture.create(((ListenableFuture) (obj)), java/lang/Throwable, .instance, com.google.common.util.concurrent.a._cls3..instance);
_L2:
        com.google.common.base.Function function;
        if (obj2 instanceof FluentIterable)
        {
            obj = (FluentIterable)obj2;
        } else
        {
            obj = new com.google.common.collect.NSTANCE(((Iterable) (obj2)), ((Iterable) (obj2)));
        }
        function = tance;
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (function == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.ambda._cls0..instance(((Iterable) (obj)), function);
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.ambda._cls0..instance(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (!(obj instanceof Collection))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = ImmutableSet.copyOf((Collection)obj);
_L5:
        obj = CalendarApi.Habits.read((HabitDescriptor[])((Set) (obj)).toArray(new HabitDescriptor[((Set) (obj)).size()]));
        function = tance;
        obj = AbstractTransformFuture.create(AbstractTransformFuture.create(GmsFutures.asFuture(((com.google.android.gms.common.api.PendingResult) (obj))), new com.google.android.apps.calendar.util.gms.e(function), com.google.common.util.concurrent..instance), new t>(((HabitNotificationClient) (obj1)), iterable, ((List) (obj2)), l, l1), CalendarExecutor.BACKGROUND);
        if (true) goto _L4; else goto _L3
_L3:
        obj = ((Iterable) (obj)).iterator();
        if (!((Iterator) (obj)).hasNext())
        {
            obj = RegularImmutableSet.EMPTY;
        } else
        {
            Object obj3 = ((Iterator) (obj)).next();
            if (!((Iterator) (obj)).hasNext())
            {
                obj = new SingletonImmutableSet(obj3);
            } else
            {
                obj = ((com.google.common.collect.r.BACKGROUND)((com.google.common.collect.r.BACKGROUND)(new com.google.common.collect.r.BACKGROUND()).BACKGROUND(obj3)).BACKGROUND(((Iterator) (obj)))).BACKGROUND();
            }
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    Q(EventNotificationClientBase eventnotificationclientbase, com.google.common.collect.e e, Iterable iterable, long l, long l1)
    {
        arg$1 = eventnotificationclientbase;
        arg$2 = e;
        arg$3 = iterable;
        arg$4 = l;
        arg$5 = l1;
    }
}
