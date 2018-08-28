// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;

public final class AlternateTimelineUtils
{

    public static boolean isDataFactoryEnabled(Context context)
    {
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).alternate_timeline();
        boolean flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
        context = Features.instance;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)context).experimental_options();
        return !flag;
    }

    static com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewIdToViewType(int i)
    {
        if (i == 0x7f100004)
        {
            return com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.AGENDA;
        }
        if (i == 0x7f100022)
        {
            return com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.DAY;
        }
        if (i == 0x7f100026)
        {
            return com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.THREE_DAY;
        }
        if (i == 0x7f100050)
        {
            return com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.WEEK;
        }
        if (i == 0x7f100027)
        {
            return com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType.MONTH;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(28)).append("Unknown item id: ").append(i).toString());
        }
    }

    static int viewTypeToViewId(com.google.android.apps.calendar.timeline.alternate.fragment.api.CalendarFragment.ViewType viewtype)
    {
        switch (viewtype.ordinal())
        {
        default:
            viewtype = String.valueOf(viewtype);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(viewtype).length() + 25)).append("Not supported view type: ").append(viewtype).toString());

        case 0: // '\0'
            return 0x7f100022;

        case 1: // '\001'
            return 0x7f100026;

        case 2: // '\002'
            return 0x7f100050;

        case 3: // '\003'
            return 0x7f100004;

        case 4: // '\004'
            return 0x7f100027;
        }
    }
}
