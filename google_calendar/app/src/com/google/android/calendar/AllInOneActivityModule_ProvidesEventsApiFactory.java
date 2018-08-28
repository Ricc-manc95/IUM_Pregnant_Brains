// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.google.android.apps.calendar.timebox.EventsApi;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_ProvidesEventsApiFactory
    implements Factory
{

    private final Provider contextProvider;
    private final Provider timeZoneProvider;

    public AllInOneActivityModule_ProvidesEventsApiFactory(Provider provider, Provider provider1)
    {
        contextProvider = provider;
        timeZoneProvider = provider1;
    }

    public final Object get()
    {
        Object obj1 = contextProvider;
        Object obj = timeZoneProvider;
        obj1 = (Context)((Provider) (obj1)).get();
        obj = (ObservableReference)((Provider) (obj)).get();
        obj.getClass();
        obj = new EventsApiImpl(((Context) (obj1)), new AllInOneActivityModule..Lambda._cls6(((ObservableReference) (obj))));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (EventsApi)obj;
        }
    }
}
