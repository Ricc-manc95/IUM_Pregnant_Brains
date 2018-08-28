// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.view.View;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column.HoursDrawable;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl, ViewLayoutParams

final class HoursViewHolder extends TimelineAdapterViewHolderImpl
{

    private final HoursDrawable hoursDrawable;

    public HoursViewHolder(Context context, HoursDrawable hoursdrawable)
    {
        super(new View(context));
        hoursDrawable = hoursdrawable;
        itemView.setBackground(hoursdrawable);
    }

    public final void onLayoutUpdate(ViewLayoutParams viewlayoutparams)
    {
        hoursDrawable.invalidateSelf();
    }
}
