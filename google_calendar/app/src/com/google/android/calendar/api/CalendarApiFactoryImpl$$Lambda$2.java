// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api;

import android.content.Context;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Function;

final class arg._cls1
    implements Function
{

    private final Context arg$1;

    public final Object apply(Object obj)
    {
        Object obj1 = arg$1;
        obj = (Consumer)obj;
        obj1 = TimelyStore.acquire(((Context) (obj1)));
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        return ((TimelyStore) (obj1)).conferenceSubscribers.subscribeFunction(new com.google.android.apps.calendar.util.concurrent.it>(((Consumer) (obj))), calendarexecutor);
    }

    ger(Context context)
    {
        arg$1 = context;
    }
}
