// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.app.Activity;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar.timeline.alternate:
//            AlternateTimelineRescheduleManager

public final class AlternateTimelineRescheduleManager_Factory
    implements Factory
{

    private final Provider activityProvider;
    private final Provider taskCacheProvider;

    public AlternateTimelineRescheduleManager_Factory(Provider provider, Provider provider1)
    {
        activityProvider = provider;
        taskCacheProvider = provider1;
    }

    public final Object get()
    {
        Provider provider = activityProvider;
        Provider provider1 = taskCacheProvider;
        return new AlternateTimelineRescheduleManager((Activity)provider.get(), (ListenableFutureCache)provider1.get());
    }
}
