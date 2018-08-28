// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

final class arg._cls1
    implements Predicate
{

    private final FluentIterable arg$1;

    public final boolean apply(Object obj)
    {
        return ly._cls2.V2AEventsApi.SearchFilter(arg$1, (String)obj);
    }

    (FluentIterable fluentiterable)
    {
        arg$1 = fluentiterable;
    }
}
