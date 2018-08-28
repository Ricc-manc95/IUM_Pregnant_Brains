// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class TimeUpdater
{

    public static final long UPDATE_PERIOD;
    public final ObservableReference currentJulianDay;
    public final ObservableReference currentTime;
    public ListenableScheduledFuture future;
    public final TimeUtils timeUtils;

    public TimeUpdater(TimeUtils timeutils, ObservableReference observablereference, ObservableReference observablereference1)
    {
        timeUtils = timeutils;
        currentTime = observablereference;
        currentJulianDay = observablereference1;
    }

    static 
    {
        UPDATE_PERIOD = TimeUnit.SECONDS.toMillis(60L);
    }
}
