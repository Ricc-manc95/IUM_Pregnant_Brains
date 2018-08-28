// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapterViewHolder;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineHostView, RecyclerViewAccessibilityDelegateHelper

public final class TimelineRecyclerView extends RecyclerView
    implements TimelineHostView
{

    public final ObservableReference isA11yEnabled;
    private final com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapterViewHolder.ViewDrawer viewDrawer = new .Lambda._cls0();

    public TimelineRecyclerView(Context context, int i, ObservableReference observablereference)
    {
        super(context);
        class .Lambda._cls0
            implements com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.TimelineAdapterViewHolder.ViewDrawer
        {

            private final TimelineRecyclerView arg$1;

            public final boolean draw(Canvas canvas, View view, long l)
            {
                return arg$1.lambda$new$0$TimelineRecyclerView(canvas, view, l);
            }

            .Lambda._cls0()
            {
                arg$1 = TimelineRecyclerView.this;
            }
        }

        isA11yEnabled = observablereference;
        context = super.mRecycler;
        if (((android.support.v7.widget.RecyclerView.Recycler) (context)).mRecyclerPool == null)
        {
            context.mRecyclerPool = new android.support.v7.widget.RecyclerView.RecycledViewPool();
        }
        ((android.support.v7.widget.RecyclerView.Recycler) (context)).mRecyclerPool.setMaxRecycledViews(CalendarViewType.EVENT.ordinal(), (i << 1) * 5);
        context = super.mRecycler;
        if (((android.support.v7.widget.RecyclerView.Recycler) (context)).mRecyclerPool == null)
        {
            context.mRecyclerPool = new android.support.v7.widget.RecyclerView.RecycledViewPool();
        }
        ((android.support.v7.widget.RecyclerView.Recycler) (context)).mRecyclerPool.setMaxRecycledViews(CalendarViewType.MONTH_VIEW_DAY_HEADER.ordinal(), i << 1);
    }

    protected final boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        Object obj = super.mAccessibilityDelegate;
        if (!(obj instanceof RecyclerViewAccessibilityDelegateHelper)) goto _L2; else goto _L1
_L1:
        obj = (RecyclerViewAccessibilityDelegateHelper)obj;
        if (((RecyclerViewAccessibilityDelegateHelper) (obj)).manager.isEnabled() && ((RecyclerViewAccessibilityDelegateHelper) (obj)).manager.isTouchExplorationEnabled()) goto _L4; else goto _L3
_L3:
        return false;
_L4:
        motionevent.getAction();
        JVM INSTR tableswitch 7 10: default 72
    //                   7 78
    //                   8 72
    //                   9 78
    //                   10 113;
           goto _L5 _L6 _L5 _L6 _L7
_L7:
        continue; /* Loop/switch isn't completed */
_L5:
        boolean flag = false;
_L9:
        return flag;
_L6:
        int i;
        i = ((RecyclerViewAccessibilityDelegateHelper) (obj))._flddelegate.getVirtualViewAt(motionevent.getX(), motionevent.getY());
        ((RecyclerViewAccessibilityDelegateHelper) (obj)).updateHoveredVirtualView(i);
        if (i == 0x80000000) goto _L3; else goto _L8
_L8:
        flag = true;
          goto _L9
        if (((RecyclerViewAccessibilityDelegateHelper) (obj)).accessibilityFocusedVirtualViewId == 0x80000000) goto _L3; else goto _L10
_L10:
        ((RecyclerViewAccessibilityDelegateHelper) (obj)).updateHoveredVirtualView(0x80000000);
        return true;
_L2:
        return super.dispatchHoverEvent(motionevent);
    }

    public final boolean drawChild(Canvas canvas, View view, long l)
    {
        TimelineAdapterViewHolder timelineadapterviewholder = (TimelineAdapterViewHolder)getChildViewHolder(view);
        if (timelineadapterviewholder != null)
        {
            return timelineadapterviewholder.draw(viewDrawer, canvas, l);
        } else
        {
            return super.drawChild(canvas, view, l);
        }
    }

    final boolean lambda$new$0$TimelineRecyclerView(Canvas canvas, View view, long l)
    {
        return super.drawChild(canvas, view, l);
    }

    protected final void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        invalidate();
        requestLayout();
    }

    public final void requestFocusAfterLayout(ListenableFuture listenablefuture, Supplier supplier)
    {
        class .Lambda._cls1
            implements Runnable
        {

            private final TimelineRecyclerView arg$1;
            private final Supplier arg$2;

            public final void run()
            {
                Object obj = arg$1;
                Object obj1 = arg$2;
                CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
                class .Lambda._cls2
                    implements Runnable
                {

                    private final TimelineRecyclerView arg$1;
                    private final Supplier arg$2;

                    public final void run()
                    {
                        Object obj3 = arg$1;
                        Object obj2 = arg$2;
                        if (((Boolean)((TimelineRecyclerView) (obj3)).isA11yEnabled.get()).booleanValue())
                        {
                            obj3 = ((RecyclerView) (obj3)).mAccessibilityDelegate;
                            if (obj3 instanceof RecyclerViewAccessibilityDelegateHelper)
                            {
                                obj3 = (RecyclerViewAccessibilityDelegateHelper)obj3;
                                int i = ((Integer)((Supplier) (obj2)).get()).intValue();
                                ((RecyclerViewAccessibilityDelegateHelper) (obj3))._flddelegate.requestAccessibilityFocusOnNextUpdate(i);
                                obj2 = ((RecyclerViewAccessibilityDelegateHelper) (obj3)).hostView;
                                AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEvent(((View) (obj2)), 2048);
                            }
                        }
                    }

                        .Lambda._cls2(Supplier supplier)
                        {
                            arg$1 = TimelineRecyclerView.this;
                            arg$2 = supplier;
                        }
                }

                obj = ((.Lambda._cls2) (obj)). new .Lambda._cls2(((Supplier) (obj1)));
                obj1 = TimeUnit.SECONDS;
                calendarexecutor.getDelegate().schedule(((Runnable) (obj)), 1L, ((TimeUnit) (obj1)));
            }

            .Lambda._cls1(Supplier supplier)
            {
                arg$1 = TimelineRecyclerView.this;
                arg$2 = supplier;
            }
        }

        listenablefuture.addListener(new .Lambda._cls1(supplier), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }
}
