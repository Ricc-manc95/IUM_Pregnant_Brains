// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

final class arg._cls1
    implements Function
{

    private final Predicate arg$1;

    public final Object apply(Object obj)
    {
        Predicate predicate = arg$1;
        obj = Arrays.asList((CalendarListEntry[])obj);
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (predicate == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.collect.Delegate(((Iterable) (obj)), predicate);
        Object aobj[];
        if (obj instanceof FluentIterable)
        {
            obj = (FluentIterable)obj;
        } else
        {
            obj = new com.google.common.collect.(((Iterable) (obj)), ((Iterable) (obj)));
        }
        obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
        aobj = (Object[])Array.newInstance(com/google/android/calendar/api/calendarlist/CalendarListEntry, 0);
        if (obj instanceof Collection)
        {
            obj = (Collection)obj;
        } else
        {
            java.util.Iterator iterator = ((Iterable) (obj)).iterator();
            obj = new ArrayList();
            Iterators.addAll(((Collection) (obj)), iterator);
        }
        return (CalendarListEntry[])((Collection) (obj)).toArray(aobj);
    }

    (Predicate predicate)
    {
        arg$1 = predicate;
    }
}
