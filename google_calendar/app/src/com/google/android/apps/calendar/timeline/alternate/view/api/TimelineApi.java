// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;

import android.view.View;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.api:
//            ViewMode

public interface TimelineApi
{

    public abstract Runnable disableChildClipping();

    public abstract android.view.View.OnDragListener getOnDragListener();

    public abstract View getView();

    public abstract ViewMode getViewMode();

    public abstract void goToDay(int i);

    public abstract void goToNow(boolean flag);

    public abstract void goToTime(long l);

    public abstract void onDestroy();

    public abstract void setDragOffset(int i, int j);

    public abstract void showGridLayout(int i, int j, boolean flag);

    public abstract void showMonthLayout(Integer integer, boolean flag);

    public abstract void showScheduleLayout(int i, boolean flag);
}
