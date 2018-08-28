// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import android.support.v7.widget.RecyclerView;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdaterImpl

final class arg._cls2
    implements Runnable
{

    private final LayoutUpdaterImpl arg$1;
    private final android.support.v7.widget.ew.impl.layout.LayoutUpdaterImpl arg$2;

    public final void run()
    {
        LayoutUpdaterImpl layoutupdaterimpl = arg$1;
        android.support.v7.widget.ew.impl.layout.LayoutUpdaterImpl layoutupdaterimpl1 = arg$2;
        layoutupdaterimpl.sortedViewsValid = false;
        if (layoutupdaterimpl1.RecyclerView != null)
        {
            layoutupdaterimpl1.RecyclerView.requestLayout();
        }
    }

    (LayoutUpdaterImpl layoutupdaterimpl, android.support.v7.widget.Impl impl)
    {
        arg$1 = layoutupdaterimpl;
        arg$2 = impl;
    }
}
