// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;


abstract class ScheduleItem
{

    ScheduleItem()
    {
    }

    abstract int getBottom();

    abstract Long getEndTimeMs();

    abstract int getLeft();

    abstract int getPosition();

    abstract int getRight();

    abstract Long getStartTimeMs();

    abstract int getTop();

    abstract boolean isTimedItem();
}
