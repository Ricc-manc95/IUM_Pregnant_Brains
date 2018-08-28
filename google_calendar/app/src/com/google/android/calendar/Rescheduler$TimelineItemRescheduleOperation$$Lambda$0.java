// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.api.event.Event;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

final class arg._cls1
    implements AsyncFunction
{

    private final bda._cls0.Rescheduler.TimelineItemRescheduleOperation arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return arg$1.TimelineItemRescheduleOperation((Event)obj);
    }

    ( )
    {
        arg$1 = ;
    }
}
