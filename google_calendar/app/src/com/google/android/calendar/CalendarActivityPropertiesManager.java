// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.utils.rtl.RtlUtils;

public final class CalendarActivityPropertiesManager
{

    private final Activity activity;
    public final ObservableReference isPortrait = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));
    public final ObservableReference isRtl = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(Boolean.valueOf(false));

    CalendarActivityPropertiesManager(Activity activity1)
    {
        activity = activity1;
        onConfigurationChanged();
    }

    final void onConfigurationChanged()
    {
        boolean flag = true;
        ObservableReference observablereference = isPortrait;
        Boolean boolean1;
        if (activity.getResources().getConfiguration().orientation != 1)
        {
            flag = false;
        }
        boolean1 = Boolean.valueOf(flag);
        if (!observablereference.get().equals(boolean1))
        {
            observablereference.set(boolean1);
        }
        observablereference = isRtl;
        boolean1 = Boolean.valueOf(RtlUtils.isLayoutDirectionRtl(activity));
        if (!observablereference.get().equals(boolean1))
        {
            observablereference.set(boolean1);
        }
    }
}
