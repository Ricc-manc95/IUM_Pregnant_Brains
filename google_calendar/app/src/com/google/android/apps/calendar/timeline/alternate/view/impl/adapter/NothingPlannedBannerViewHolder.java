// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import com.google.android.apps.calendar.timeline.alternate.view.api.ScheduleProvider;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl

final class NothingPlannedBannerViewHolder extends TimelineAdapterViewHolderImpl
{

    public final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.NothingPlannedBannerOnClickListener onClickListener;
    public final TextView view;

    NothingPlannedBannerViewHolder(Context context, ScheduleProvider scheduleprovider, com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.NothingPlannedBannerOnClickListener nothingplannedbanneronclicklistener)
    {
        super(new TextView(context));
        view = (TextView)itemView;
        onClickListener = nothingplannedbanneronclicklistener;
        view.setGravity(0x800013);
        view.setText(scheduleprovider.getNothingPlannedText());
        view.setTextAlignment(5);
        view.setTextColor(ContextCompat.getColor(context, 0x7f0d021a));
    }
}
