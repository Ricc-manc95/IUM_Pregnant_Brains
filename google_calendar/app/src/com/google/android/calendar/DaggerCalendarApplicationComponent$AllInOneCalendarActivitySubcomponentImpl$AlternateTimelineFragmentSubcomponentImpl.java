// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragment;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.AlternateTimelineFragmentSubcomponent;
import com.google.android.apps.calendar.timeline.alternate.fragment.impl.TimeUpdater;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.TimelineApi;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            DaggerCalendarApplicationComponent, CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory, CalendarActivityPropertiesManager

final class this._cls1
    implements AlternateTimelineFragmentSubcomponent
{

    private final this._cls1 this$1;

    public final void inject(Object obj)
    {
        obj = (AlternateTimelineFragment)obj;
        obj.controller = (TimelineApi)_fld1.get();
        Object obj1 = this._cls1.this;
        obj.timeUpdater = new TimeUpdater(new TimeUtils((ObservableReference)((this._cls1) (obj1))._fld1.providesTimeZoneObservableProvider.get(), CalendarApplicationPropertiesModule_ProvidesFirstDayOfWeekFactory.proxyProvidesFirstDayOfWeek(((this._cls1) (obj1))._fld1.calendarApplicationPropertiesManagerProvider.get())), (ObservableReference)providesCurrentTimeProvider.get(), (ObservableReference)providesCurrentJulianDayProvider.get());
        obj1 = ((CalendarActivityPropertiesManager)_fld1.get()).isPortrait;
        if (obj1 == null)
        {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        } else
        {
            obj.isPortrait = (ObservableReference)obj1;
            obj.screenType = (ObservableReference)_fld1.get();
            obj.phantom = (ObservableReference)_fld1.get();
            return;
        }
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
