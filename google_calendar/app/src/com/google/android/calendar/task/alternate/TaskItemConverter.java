// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task.alternate;

import android.content.Context;
import com.google.android.calendar.time.DateTimeService;

public final class TaskItemConverter
{

    public final DateTimeService dateTimeService;

    TaskItemConverter(Context context)
    {
        dateTimeService = new DateTimeService(context);
    }
}
