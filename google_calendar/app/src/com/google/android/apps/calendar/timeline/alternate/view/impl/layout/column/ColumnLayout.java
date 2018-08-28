// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.Layout;
import com.google.common.util.concurrent.FluentFuture;

public abstract class ColumnLayout extends Layout
{

    public ColumnLayout()
    {
    }

    public abstract void onHide();

    public abstract FluentFuture onShow(int i, int j, boolean flag);

    public abstract FluentFuture setNumDaysAndStart(int i, int j);
}
