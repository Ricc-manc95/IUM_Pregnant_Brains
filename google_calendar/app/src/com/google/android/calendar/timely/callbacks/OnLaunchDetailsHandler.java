// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.callbacks;

import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;

public interface OnLaunchDetailsHandler
{

    public abstract void onLaunchDetails(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata);
}
