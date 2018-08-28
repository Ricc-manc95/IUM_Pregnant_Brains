// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.api;


public interface CalendarFragment
{

    public abstract void clearCreatePhantom();

    public abstract ViewType getViewType();

    public abstract void goToDay(int i);

    public abstract void goToNow();

    public abstract void goToTime(long l);

    public abstract CalendarFragment onSwitchView(ViewType viewtype, int i, boolean flag);
}
