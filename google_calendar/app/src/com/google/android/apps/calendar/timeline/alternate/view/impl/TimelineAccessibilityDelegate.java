// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.Layout;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.LayoutManager;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.IdleTracker;
import com.google.android.apps.calendar.timeline.alternate.view.impl.viewtype.CalendarViewType;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            RecyclerViewAccessibilityDelegateHelper

public class TimelineAccessibilityDelegate
    implements RecyclerViewAccessibilityDelegateHelper.Delegate
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/timeline/alternate/view/impl/TimelineAccessibilityDelegate);
    private final SparseArray currentViews = new SparseArray();
    public RecyclerViewAccessibilityDelegateHelper helper;
    private final LayoutManager layoutManager;
    public int requestFocusOnNextUpdate;
    private final Rect tmpRect = new Rect();
    private final Supplier virtualViews;

    public TimelineAccessibilityDelegate(LayoutManager layoutmanager, Supplier supplier, IdleTracker idletracker)
    {
        requestFocusOnNextUpdate = -1;
        layoutManager = layoutmanager;
        virtualViews = supplier;
        class .Lambda._cls0
            implements Consumer
        {

            private final TimelineAccessibilityDelegate arg$1;

            public final void accept(Object obj)
            {
                TimelineAccessibilityDelegate timelineaccessibilitydelegate = arg$1;
                if (((Boolean)obj).booleanValue())
                {
                    obj = timelineaccessibilitydelegate.helper.hostView;
                    AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEvent(((android.view.View) (obj)), 2048);
                }
            }

            .Lambda._cls0()
            {
                arg$1 = TimelineAccessibilityDelegate.this;
            }
        }

        class .Lambda._cls1
            implements Executor
        {

            public static final Executor $instance = new .Lambda._cls1();

            public final void execute(Runnable runnable)
            {
                TimelineAccessibilityDelegate.lambda$new$1$TimelineAccessibilityDelegate(runnable);
            }


            private .Lambda._cls1()
            {
            }
        }

        idletracker.observable.subscribe(new .Lambda._cls0(), .Lambda._cls1..instance, false);
    }

    private final boolean handleScrollFuture(Optional optional)
    {
        class .Lambda._cls2
            implements Function
        {

            private final TimelineAccessibilityDelegate arg$1;

            public final Object apply(Object obj)
            {
                TimelineAccessibilityDelegate timelineaccessibilitydelegate = arg$1;
                obj = (Integer)obj;
                if (((Integer) (obj)).intValue() != -1)
                {
                    timelineaccessibilitydelegate.requestFocusOnNextUpdate = ((Integer) (obj)).intValue();
                }
                return null;
            }

            .Lambda._cls2()
            {
                arg$1 = TimelineAccessibilityDelegate.this;
            }
        }

        FluentFuture fluentfuture;
        if (optional.isPresent())
        {
            fluentfuture = (FluentFuture)AbstractTransformFuture.create((FluentFuture)optional.get(), new .Lambda._cls2(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        return optional.isPresent();
    }

    static final void lambda$new$1$TimelineAccessibilityDelegate(Runnable runnable)
    {
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        TimeUnit timeunit = TimeUnit.SECONDS;
        calendarexecutor.getDelegate().schedule(runnable, 1L, timeunit);
    }

    public final int getVirtualViewAt(float f, float f1)
    {
        AccessibilityVirtualView accessibilityvirtualview = null;
        int k = currentViews.size();
        int i = 0;
        while (i < k) 
        {
            AccessibilityVirtualView accessibilityvirtualview1 = (AccessibilityVirtualView)currentViews.get(currentViews.keyAt(i));
            if ((float)accessibilityvirtualview1.left() > f || (float)accessibilityvirtualview1.right() < f || (float)accessibilityvirtualview1.top() > f1 || (float)accessibilityvirtualview1.bottom() < f1 || accessibilityvirtualview != null && accessibilityvirtualview.zOrder() >= accessibilityvirtualview1.zOrder())
            {
                continue;
            }
            CalendarViewType calendarviewtype = CalendarViewType.VIRTUAL_TIMED_EVENTS;
            int j = accessibilityvirtualview1.id();
            boolean flag;
            if (j >= calendarviewtype.minPosition && j <= calendarviewtype.maxPosition)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                accessibilityvirtualview = accessibilityvirtualview1;
            }
            i++;
        }
        if (accessibilityvirtualview == null)
        {
            return 0x80000000;
        } else
        {
            return accessibilityvirtualview.id();
        }
    }

    public final boolean performActionForChild$514KIJ31DPI74RR9CGNMUSPF89QMSP3CCKTIIMG_0(int i, int j)
    {
        Object obj;
        Integer integer;
        boolean flag;
        boolean flag1;
        integer = null;
        flag1 = false;
        LogUtils.i(TAG, "performActionForChild: %d %d", new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
        obj = (AccessibilityVirtualView)currentViews.get(i);
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((AccessibilityVirtualView) (obj)).actionHandler();
        }
        if (j != ((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS.mAction).getId()) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if (obj != null)
        {
            flag = ((com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler) (obj)).focus();
        }
_L4:
        return flag;
_L2:
        if (j != ((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN.mAction).getId())
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (obj != null)
        {
            return ((com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler) (obj)).showOnScreen();
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (j == 4096 || j == 8192)
        {
            flag = flag1;
            if (obj != null)
            {
                Integer integer1 = Integer.valueOf(helper.accessibilityFocusedVirtualViewId);
                if (integer1.intValue() != 0x80000000)
                {
                    integer = integer1;
                }
                if (j == 4096)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                return handleScrollFuture(((com.google.android.apps.calendar.timeline.alternate.view.impl.layout.AccessibilityVirtualView.ActionHandler) (obj)).scroll(flag, integer));
            }
        }
        while (false) 
        {
            flag = flag1;
            if (j != 16)
            {
                continue;
            }
            obj = ((AccessibilityVirtualView)currentViews.get(i)).clickHandler();
            flag = flag1;
            if (obj != null)
            {
                ((Runnable) (obj)).run();
                return true;
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public final boolean performActionForHost$514KOOBECHP6UQB45TNN6BQ2ELN68R357CKLK___0(int i)
    {
        boolean flag = true;
        LogUtils.i(TAG, "Host action: %d", new Object[] {
            Integer.valueOf(i)
        });
        if (i == 4096 || i == 8192)
        {
            Layout layout = layoutManager.getLayout();
            if (i != 4096)
            {
                flag = false;
            }
            return handleScrollFuture(layout.scroll(flag));
        } else
        {
            LogUtils.d(TAG, "Unhandled host action: %d", new Object[] {
                Integer.valueOf(i)
            });
            return false;
        }
    }

    public final void populateEventForVirtualView$514KOOBECHP6UQB45TR6IPBN5TGM6OR5EDPMIOJ9DHKN8U9F85HM6PBJEDKM4QBCD5Q7IHBMCLN78EP9AO______0()
    {
    }

    public final void populateNodeForHost(RecyclerView recyclerview, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        accessibilitynodeinfocompat.mInfo.setFocusable(false);
        List list = (List)virtualViews.get();
        currentViews.clear();
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            AccessibilityVirtualView accessibilityvirtualview = (AccessibilityVirtualView)list.get(i);
            int k = accessibilityvirtualview.id();
            accessibilitynodeinfocompat.mInfo.addChild(recyclerview, k);
            currentViews.put(accessibilityvirtualview.id(), accessibilityvirtualview);
        }

        if (requestFocusOnNextUpdate != -1)
        {
            if (currentViews.get(requestFocusOnNextUpdate) != null)
            {
                helper.requestAccessibilityFocus(requestFocusOnNextUpdate);
            }
            requestFocusOnNextUpdate = -1;
        }
    }

    public final boolean populateNodeForVirtualView(RecyclerView recyclerview, int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        AccessibilityVirtualView accessibilityvirtualview = (AccessibilityVirtualView)currentViews.get(i);
        if (accessibilityvirtualview == null)
        {
            return false;
        }
        tmpRect.set(accessibilityvirtualview.left(), accessibilityvirtualview.top(), accessibilityvirtualview.right(), accessibilityvirtualview.bottom());
        Object obj = tmpRect;
        accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
        CharSequence charsequence = accessibilityvirtualview.contentDescription();
        obj = charsequence;
        if (charsequence == null)
        {
            obj = (new StringBuilder(11)).append(i).toString();
        }
        accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
        obj = accessibilityvirtualview.parentId();
        if (obj != null)
        {
            int j = ((Integer) (obj)).intValue();
            accessibilitynodeinfocompat.mParentVirtualDescendantId = j;
            accessibilitynodeinfocompat.mInfo.setParent(recyclerview, j);
            j = ((Integer) (obj)).intValue();
            obj = (AccessibilityVirtualView)currentViews.get(j);
            if (obj != null)
            {
                Rect rect = tmpRect;
                accessibilitynodeinfocompat.mInfo.getBoundsInParent(rect);
                tmpRect.offset(-((AccessibilityVirtualView) (obj)).left(), -((AccessibilityVirtualView) (obj)).top());
                obj = tmpRect;
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
            }
        }
        boolean flag;
        if (accessibilityvirtualview.canScrollForward().booleanValue() || accessibilityvirtualview.canScrollBackward().booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        accessibilitynodeinfocompat.mInfo.setScrollable(flag);
        if (accessibilityvirtualview.canScrollForward().booleanValue())
        {
            android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityactioncompat = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD;
            accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)accessibilityactioncompat.mAction);
        }
        if (accessibilityvirtualview.canScrollBackward().booleanValue())
        {
            android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityactioncompat1 = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD;
            accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)accessibilityactioncompat1.mAction);
        }
        if (accessibilityvirtualview.clickHandler() != null)
        {
            android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityactioncompat2 = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK;
            accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)accessibilityactioncompat2.mAction);
        }
        if (accessibilityvirtualview.traversalBefore() != null)
        {
            int k = accessibilityvirtualview.traversalBefore().intValue();
            if (android.os.Build.VERSION.SDK_INT >= 22)
            {
                accessibilitynodeinfocompat.mInfo.setTraversalBefore(recyclerview, k);
            }
        }
        if (accessibilityvirtualview.traversalAfter() != null)
        {
            int l = accessibilityvirtualview.traversalAfter().intValue();
            if (android.os.Build.VERSION.SDK_INT >= 22)
            {
                accessibilitynodeinfocompat.mInfo.setTraversalAfter(recyclerview, l);
            }
        }
        recyclerview = CalendarViewType.VIRTUAL_TIMED_EVENTS;
        if (i >= ((CalendarViewType) (recyclerview)).minPosition && i <= ((CalendarViewType) (recyclerview)).maxPosition)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            accessibilitynodeinfocompat.mInfo.setFocusable(false);
            recyclerview = new android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat(android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(0, 0, false, 1));
            accessibilitynodeinfocompat = accessibilitynodeinfocompat.mInfo;
            if (recyclerview == null)
            {
                recyclerview = null;
            } else
            {
                recyclerview = (android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat)recyclerview).mInfo;
            }
            accessibilitynodeinfocompat.setCollectionInfo(recyclerview);
        }
        return true;
    }

    public final void requestAccessibilityFocusOnNextUpdate(int i)
    {
        requestFocusOnNextUpdate = i;
    }

    public final void setHelper(RecyclerViewAccessibilityDelegateHelper recyclerviewaccessibilitydelegatehelper)
    {
        helper = recyclerviewaccessibilitydelegatehelper;
    }

}
