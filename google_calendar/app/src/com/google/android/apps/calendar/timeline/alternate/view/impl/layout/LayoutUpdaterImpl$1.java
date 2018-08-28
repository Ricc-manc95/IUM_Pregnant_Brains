// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import android.support.v7.widget.RecyclerView;
import android.view.View;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutUpdaterImpl

final class val.recyclerView
    implements android.view.yChangeListener
{

    private final LayoutUpdaterImpl this$0;
    private final RecyclerView val$recyclerView;

    public final void onChildViewAdded(View view, View view1)
    {
        sortedViewsValid = false;
        expectedViewCount = val$recyclerView.getChildCount();
    }

    public final void onChildViewRemoved(View view, View view1)
    {
        sortedViewsValid = false;
        expectedViewCount = val$recyclerView.getChildCount();
    }

    ()
    {
        this$0 = final_layoutupdaterimpl;
        val$recyclerView = RecyclerView.this;
        super();
    }
}
