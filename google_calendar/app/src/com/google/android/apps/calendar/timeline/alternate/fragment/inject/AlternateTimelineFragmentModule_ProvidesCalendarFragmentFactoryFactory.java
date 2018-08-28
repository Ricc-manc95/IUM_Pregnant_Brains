// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.inject;

import com.google.android.apps.calendar.util.function.BiFunction;
import dagger.internal.Factory;

public final class AlternateTimelineFragmentModule_ProvidesCalendarFragmentFactoryFactory
    implements Factory
{

    public AlternateTimelineFragmentModule_ProvidesCalendarFragmentFactoryFactory()
    {
    }

    public static BiFunction proxyProvidesCalendarFragmentFactory()
    {
        BiFunction bifunction = AlternateTimelineFragmentModule..Lambda._cls0.$instance;
        if (bifunction == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (BiFunction)bifunction;
        }
    }

    public final Object get()
    {
        BiFunction bifunction = AlternateTimelineFragmentModule..Lambda._cls0.$instance;
        if (bifunction == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (BiFunction)bifunction;
        }
    }

    static 
    {
        new AlternateTimelineFragmentModule_ProvidesCalendarFragmentFactoryFactory();
    }
}
