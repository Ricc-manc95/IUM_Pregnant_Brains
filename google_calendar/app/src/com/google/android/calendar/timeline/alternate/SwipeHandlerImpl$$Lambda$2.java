// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import com.google.android.apps.calendar.timebox.task.TaskData;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.common.base.Function;

final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return ((TaskItem)obj).getTaskData().getId();
    }


    private ()
    {
    }
}
