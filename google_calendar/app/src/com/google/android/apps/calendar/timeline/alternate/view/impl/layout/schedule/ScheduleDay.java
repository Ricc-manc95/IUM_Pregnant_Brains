// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.schedule;

import java.util.List;

abstract class ScheduleDay
{

    ScheduleDay()
    {
    }

    abstract int getCacheGeneration();

    abstract int getHeightPx();

    abstract List getLayout();

    abstract boolean getLoaded();
}