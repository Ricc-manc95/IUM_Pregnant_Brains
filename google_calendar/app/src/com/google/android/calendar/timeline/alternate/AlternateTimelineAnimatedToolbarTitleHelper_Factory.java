// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.app.Activity;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            AlternateTimelineAnimatedToolbarTitleHelper

public final class AlternateTimelineAnimatedToolbarTitleHelper_Factory
    implements Factory
{

    private final Provider activityProvider;
    private final Provider screenTypeProvider;

    public AlternateTimelineAnimatedToolbarTitleHelper_Factory(Provider provider, Provider provider1)
    {
        activityProvider = provider;
        screenTypeProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = activityProvider;
        Provider provider1 = screenTypeProvider;
        return new AlternateTimelineAnimatedToolbarTitleHelper((Activity)provider.get(), (ObservableReference)provider1.get());
    }
}
