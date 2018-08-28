// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import com.google.android.apps.calendar.timeline.alternate.view.impl.LayoutDimens;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapter;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.concurrent.Subscription;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnDimens, ColumnViewport

final class AllDayManager
{

    public final TimelineAdapter adapter;
    public ValueAnimator allDayAnimator;
    public int animatedHeightPx;
    public final ColumnDimens columnDimens;
    public boolean firstShow;
    public final TimelineHostView hostView;
    public final IdleTracker idleTracker;
    public final ObservableReference isExpanded;
    public Subscription isExpandedSubscription;
    public final com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier isVisibleSupplier;
    public final LayoutDimens layoutDimens;
    public boolean shouldShowExpandButton;
    public int targetHeightPx;
    public final ColumnViewport viewport;

    AllDayManager(TimelineHostView timelinehostview, TimelineAdapter timelineadapter, ColumnViewport columnviewport, ColumnDimens columndimens, LayoutDimens layoutdimens, ObservableReference observablereference, IdleTracker idletracker, 
            com.google.android.apps.calendar.timeline.alternate.view.api.TimelineSpi.IsVisibleSupplier isvisiblesupplier)
    {
        firstShow = true;
        shouldShowExpandButton = false;
        hostView = timelinehostview;
        adapter = timelineadapter;
        viewport = columnviewport;
        columnDimens = columndimens;
        layoutDimens = layoutdimens;
        isExpanded = observablereference;
        idleTracker = idletracker;
        isVisibleSupplier = isvisiblesupplier;
    }
}
