// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutManager, Layout, LayoutUpdaterImpl

public final class LayoutManagerImpl extends android.support.v7.widget.RecyclerView.LayoutManager
    implements LayoutManager
{

    private final IdleTracker idleTracker;
    private Layout layout;
    private final LayoutUpdaterImpl layoutUpdater;
    private com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker.OnGoingEvent scrollEventTracker;

    public LayoutManagerImpl(LayoutUpdaterImpl layoutupdaterimpl, IdleTracker idletracker)
    {
        layout = Layout.EMPTY;
        idleTracker = idletracker;
        layoutUpdater = layoutupdaterimpl;
    }

    public final boolean canScrollHorizontally()
    {
        return layoutUpdater.animator.isDone() && layout.canScrollHorizontally();
    }

    public final boolean canScrollVertically()
    {
        return layoutUpdater.animator.isDone() && layout.canScrollVertically();
    }

    public final android.support.v7.widget.RecyclerView.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutUpdaterImpl.LayoutParams(-2, -2);
    }

    public final Layout getLayout()
    {
        return layout;
    }

    public final void onLayoutChildren(android.support.v7.widget.RecyclerView.Recycler recycler, android.support.v7.widget.RecyclerView.State state)
    {
        layoutUpdater.init(recycler);
        layout.onLayoutChildren(layoutUpdater, state.mStructureChanged);
    }

    public final void onScrollStateChanged(int i)
    {
        Layout layout1 = layout;
        i;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 75
    //                   1 40
    //                   2 82;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException();
_L3:
        i = android.support.v4.content.ModernAsyncTask.Status.DRAGGING$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0;
_L10:
        if (layout1.onScrollStateChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFEHKMQPBCD5N6ABR1DHQ6ASJEC5Q6ABRMD5INEBR9DLO6OBRCC5SMUTBK5T9M6SJFDHM56T31EHIJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BR1E1O76BR3C5M6ARJ4C5P2UT39DLIMOQBECKNM2R3KCLP6SOBKCKNNCQB5ESNMIRBGDGNMOOBPDTQN8BQJCDP6UR3CADQ62T357C______0(i) != android.support.v4.content.ModernAsyncTask.Status.IDLE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0) goto _L6; else goto _L5
_L5:
        if (scrollEventTracker != null)
        {
            scrollEventTracker.onComplete();
            scrollEventTracker = null;
        }
_L8:
        return;
_L2:
        i = android.support.v4.content.ModernAsyncTask.Status.IDLE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0;
        continue; /* Loop/switch isn't completed */
_L4:
        i = android.support.v4.content.ModernAsyncTask.Status.SETTLING$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FC5O70SPFCDGMOPBECHGN4BRKD5MMAR39DPIIUOBCEHIN4RJ1EHIIUTJ9CLRIUQBDE1M2UR31F5NNAT1FADHN4RRCDH9N8OBKCKTG____0;
        continue; /* Loop/switch isn't completed */
_L6:
        if (scrollEventTracker != null) goto _L8; else goto _L7
_L7:
        scrollEventTracker = idleTracker.onEventBegin();
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final int scrollHorizontallyBy(int i, android.support.v7.widget.RecyclerView.Recycler recycler, android.support.v7.widget.RecyclerView.State state)
    {
        layoutUpdater.init(recycler);
        return layout.scrollHorizontallyBy(i, layoutUpdater);
    }

    public final int scrollVerticallyBy(int i, android.support.v7.widget.RecyclerView.Recycler recycler, android.support.v7.widget.RecyclerView.State state)
    {
        layoutUpdater.init(recycler);
        return layout.scrollVerticallyBy(i, layoutUpdater);
    }

    public final void setLayout(Layout layout1)
    {
        layout = layout1;
        if (super.mRecyclerView != null)
        {
            super.mRecyclerView.requestLayout();
        }
    }
}
