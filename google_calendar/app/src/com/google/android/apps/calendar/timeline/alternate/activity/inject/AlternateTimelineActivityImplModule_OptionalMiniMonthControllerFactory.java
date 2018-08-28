// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.activity.inject;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.activity.inject:
//            AlternateTimelineActivityImplModule

public final class AlternateTimelineActivityImplModule_OptionalMiniMonthControllerFactory
    implements Factory
{

    private final Provider alternateTimelineEnabledProvider;
    private final Provider providerProvider;

    public AlternateTimelineActivityImplModule_OptionalMiniMonthControllerFactory(Provider provider, Provider provider1)
    {
        alternateTimelineEnabledProvider = provider;
        providerProvider = provider1;
    }

    public final Object get()
    {
        Object obj = alternateTimelineEnabledProvider;
        Provider provider = providerProvider;
        obj = AlternateTimelineActivityImplModule.optionalMiniMonthController(((Boolean)((Provider) (obj)).get()).booleanValue(), provider);
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (Optional)obj;
        }
    }
}
