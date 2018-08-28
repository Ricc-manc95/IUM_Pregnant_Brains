// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;

import android.content.Context;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.experimental.ExperimentalOptions;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemOperation;
import com.google.android.calendar.timely.TimelineTaskType;

final class context extends TimelineItemOperation
{

    private Context context;

    public final Object onAny(TimelineItem timelineitem, Object aobj[])
    {
        return Integer.valueOf(0);
    }

    public final Object onAnyEvent(TimelineEvent timelineevent, Object aobj[])
    {
        aobj = (Void[])aobj;
        if (ExperimentalOptions.isProposeNewTimeEnabled(context) && timelineevent.shouldShowTimeProposalIcon())
        {
            return Integer.valueOf(0x7f02022f);
        }
        boolean flag;
        if (RemoteFeatureConfig.EVERYONE_DECLINED.enabled() && timelineevent.showEveryoneDeclined)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return Integer.valueOf(0x7f020127);
        }
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        if (((FeatureConfig)featureconfig).ooo() && timelineevent.isOutOfOffice)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return Integer.valueOf(0x7f0201ef);
        } else
        {
            return (Integer)super.onAnyEvent(timelineevent, aobj);
        }
    }

    public final Object onAnyReminder(TimelineTaskType timelinetasktype, Object aobj[])
    {
        return Integer.valueOf(0x7f020228);
    }

    public final Object onGoalEvent(TimelineGroove timelinegroove, Object aobj[])
    {
        return Integer.valueOf(0x7f0201fb);
    }

    public (Context context1)
    {
        context = context1;
    }
}
