// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.collect.Range;
import dagger.internal.Factory;

public final class AllInOneActivityModule_ProvidesSelectedDayObservableFactory
    implements Factory
{

    public static final AllInOneActivityModule_ProvidesSelectedDayObservableFactory INSTANCE = new AllInOneActivityModule_ProvidesSelectedDayObservableFactory();

    public AllInOneActivityModule_ProvidesSelectedDayObservableFactory()
    {
    }

    public final Object get()
    {
        com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1 _lcls1 = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Range.closed(Integer.valueOf(0), Integer.valueOf(0)));
        if (_lcls1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ObservableReference)_lcls1;
        }
    }

}
