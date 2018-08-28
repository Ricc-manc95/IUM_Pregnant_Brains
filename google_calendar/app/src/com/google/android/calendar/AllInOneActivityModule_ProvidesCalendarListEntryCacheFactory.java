// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import dagger.internal.Factory;

public final class AllInOneActivityModule_ProvidesCalendarListEntryCacheFactory
    implements Factory
{

    public static final AllInOneActivityModule_ProvidesCalendarListEntryCacheFactory INSTANCE = new AllInOneActivityModule_ProvidesCalendarListEntryCacheFactory();

    public AllInOneActivityModule_ProvidesCalendarListEntryCacheFactory()
    {
    }

    public final Object get()
    {
        ListenableFutureCache listenablefuturecache = CalendarListEntryCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        listenablefuturecache = (ListenableFutureCache)listenablefuturecache;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ListenableFutureCache)listenablefuturecache;
        }
    }

}
