// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.app.Activity;
import com.google.android.calendar.timeline.chip.ChipFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.fragment.impl:
//            AlternateTimelineModule

public final class AlternateTimelineModule_ProvidesChipFactoryFactory
    implements Factory
{

    private final Provider activityProvider;

    public AlternateTimelineModule_ProvidesChipFactoryFactory(Provider provider)
    {
        activityProvider = provider;
    }

    public final Object get()
    {
        ChipFactory chipfactory = AlternateTimelineModule.providesChipFactory((Activity)activityProvider.get());
        if (chipfactory == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ChipFactory)chipfactory;
        }
    }
}
