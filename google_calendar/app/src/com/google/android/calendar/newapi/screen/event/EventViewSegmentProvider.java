// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.app.Activity;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.calendar.newapi.model.EventViewScreenModel;
import com.google.android.calendar.newapi.screen.EventViewScreenController;
import com.google.android.calendar.newapi.segment.conference.ConferenceViewSegment;
import com.google.android.calendar.newapi.segment.conference.thirdparty.ThirdPartyConferenceViewSegment;
import java.util.List;

public final class EventViewSegmentProvider
{

    public EventViewSegmentProvider()
    {
    }

    public static void addConferenceSegments(List list, Activity activity, EventViewScreenModel eventviewscreenmodel, EventViewScreenController eventviewscreencontroller)
    {
        list.add(new ConferenceViewSegment(activity, eventviewscreencontroller, eventviewscreencontroller, eventviewscreenmodel));
        if (RemoteFeatureConfig.THIRD_PARTY_CONFERENCES.enabled())
        {
            list.add(new ThirdPartyConferenceViewSegment(activity, eventviewscreencontroller, eventviewscreenmodel));
        }
    }
}
