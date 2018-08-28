// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.ViewModeChangeListener;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AllInOneActivityModule_ProvidesViewModeChangeListenerFactory
    implements Factory
{

    private final Provider contextProvider;

    public AllInOneActivityModule_ProvidesViewModeChangeListenerFactory(Provider provider)
    {
        contextProvider = provider;
    }

    public final Object get()
    {
        AllInOneActivityModule..Lambda._cls5 _lcls5 = new AllInOneActivityModule..Lambda._cls5((Context)contextProvider.get());
        if (_lcls5 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ViewModeChangeListener)_lcls5;
        }
    }
}
