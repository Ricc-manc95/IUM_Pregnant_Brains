// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.widget.RemoteViews;
import java.util.Queue;

final class MonthViewWidgetUpdatesQueue
{

    public RemoteViews baseUpdate;
    public final Queue partialUpdates;

    MonthViewWidgetUpdatesQueue(RemoteViews remoteviews, Queue queue)
    {
        boolean flag1 = true;
        super();
        baseUpdate = remoteviews;
        partialUpdates = queue;
        boolean flag;
        if (baseUpdate == null && partialUpdates.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            return;
        }
    }
}
