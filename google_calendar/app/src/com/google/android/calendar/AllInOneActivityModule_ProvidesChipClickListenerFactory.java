// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timeline.alternate.fragment.api.ChipClickListener;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timeline.alternate.AlternateTimelineRescheduleManager;
import com.google.android.calendar.timeline.chipviewmodelfactory.ChipFragmentInfoFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            AllInOneCalendarActivity

public final class AllInOneActivityModule_ProvidesChipClickListenerFactory
    implements Factory
{

    private final Provider activityProvider;
    private final Provider fragmentInfoFactoryProvider;
    private final Provider rescheduleManagerProvider;
    private final Provider timeBoxToTimelineAdapterProvider;

    public AllInOneActivityModule_ProvidesChipClickListenerFactory(Provider provider, Provider provider1, Provider provider2, Provider provider3)
    {
        activityProvider = provider;
        timeBoxToTimelineAdapterProvider = provider1;
        fragmentInfoFactoryProvider = provider2;
        rescheduleManagerProvider = provider3;
    }

    public final Object get()
    {
        Object obj2 = activityProvider;
        Object obj3 = timeBoxToTimelineAdapterProvider;
        Object obj1 = fragmentInfoFactoryProvider;
        Object obj = rescheduleManagerProvider;
        obj2 = (AllInOneCalendarActivity)((Provider) (obj2)).get();
        obj3 = (TimeBoxToTimelineAdapter)((Provider) (obj3)).get();
        obj1 = (ChipFragmentInfoFactory)((Provider) (obj1)).get();
        obj = new AllInOneActivityModule..Lambda._cls1(((AllInOneCalendarActivity) (obj2)), (AlternateTimelineRescheduleManager)((Provider) (obj)).get(), ((TimeBoxToTimelineAdapter) (obj3)), ((ChipFragmentInfoFactory) (obj1)));
        if (obj == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            return (ChipClickListener)obj;
        }
    }
}
