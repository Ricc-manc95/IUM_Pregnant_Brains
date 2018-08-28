// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.android.calendar.timely.TimelineTask;
import java.util.concurrent.Callable;

final class arg._cls2
    implements Callable
{

    private final arg._cls2 arg$1;
    private final TimelineTask arg$2;

    public final Object call()
    {
        return arg$1.ReminderBlocking(arg$2);
    }

    ( , TimelineTask timelinetask)
    {
        arg$1 = ;
        arg$2 = timelinetask;
    }
}
