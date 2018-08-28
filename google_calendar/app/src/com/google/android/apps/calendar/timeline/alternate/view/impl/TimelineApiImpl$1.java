// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.Layout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;

final class val.layoutManager extends android.support.v7.widget.ngListener
{

    private final LayoutManager val$layoutManager;

    public final boolean onFling(int i, int j)
    {
        return val$layoutManager.getLayout().fling(i, j);
    }

    er()
    {
        val$layoutManager = layoutmanager;
        super();
    }
}
