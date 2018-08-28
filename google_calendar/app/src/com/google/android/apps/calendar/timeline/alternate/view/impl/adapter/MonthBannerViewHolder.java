// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl, MonthBannerView, ViewLayoutParams

final class MonthBannerViewHolder extends TimelineAdapterViewHolderImpl
{

    public final ScheduleProvider scheduleProvider;
    public final ObservableReference screenType;
    public final TimeUtils timeUtils;
    public final MonthBannerView view;

    MonthBannerViewHolder(MonthBannerView monthbannerview, TimeUtils timeutils, ScheduleProvider scheduleprovider, ObservableReference observablereference)
    {
        super(monthbannerview);
        view = monthbannerview;
        timeUtils = timeutils;
        scheduleProvider = scheduleprovider;
        screenType = observablereference;
    }

    public final void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
        view.invalidate();
    }

    public final void onRecycled()
    {
        view.clean();
    }
}
