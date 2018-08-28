// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.event.image.EventImageFutureCache;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.common.util.concurrent.ListenableFuture;

final class context extends TimelineItemOperation
{

    private final Context context;

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        if (timelineitem.hasInvitedStatus()) goto _L2; else goto _L1
_L1:
        if (!(timelineitem instanceof TimelineEvent)) goto _L4; else goto _L3
_L3:
        boolean flag;
        aobj = (TimelineEvent)timelineitem;
        if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled() && ((TimelineEvent) (aobj)).showEveryoneDeclined)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L2
_L2:
        flag = true;
_L6:
        if (!timelineitem.hasImage() || timelineitem.hasDeclinedStatus() || flag)
        {
            return null;
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L5
_L5:
        timelineitem = timelineitem.getEventImageResolver();
        if (timelineitem == null)
        {
            return null;
        } else
        {
            aobj = context.getResources();
            return EventImageFutureCache.getFuture(context, timelineitem, ((Resources) (aobj)).getDimensionPixelSize(0x7f0e009d), ((Resources) (aobj)).getDimensionPixelSize(0x7f0e009c));
        }
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        aobj = (Void[])aobj;
        if (timelinegroove.completed)
        {
            return null;
        } else
        {
            return (ListenableFuture)super.onGoalEvent(timelinegroove, aobj);
        }
    }

    public (Context context1)
    {
        context = context1;
    }
}
