// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.habit.HabitDescriptor;

// Referenced classes of package com.google.android.calendar.api.event:
//            HabitNotificationClient, EventKey

static abstract class Q
{

    public abstract HabitDescriptor getDescriptor();

    public abstract long getEndMillis();

    public abstract EventKey getEventKey();

    public abstract boolean getResolvedByFit();

    public abstract long getStartMillis();

    Q()
    {
    }
}
