// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v7.widget.RecyclerView;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            TimelineRecyclerView, RecyclerViewAccessibilityDelegateHelper

final class arg._cls2
    implements Runnable
{

    private final TimelineRecyclerView arg$1;
    private final Supplier arg$2;

    public final void run()
    {
        Object obj1 = arg$1;
        Object obj = arg$2;
        if (((Boolean)((TimelineRecyclerView) (obj1)).isA11yEnabled.get()).booleanValue())
        {
            obj1 = ((RecyclerView) (obj1)).mAccessibilityDelegate;
            if (obj1 instanceof RecyclerViewAccessibilityDelegateHelper)
            {
                obj1 = (RecyclerViewAccessibilityDelegateHelper)obj1;
                int i = ((Integer)((Supplier) (obj)).get()).intValue();
                ((RecyclerViewAccessibilityDelegateHelper) (obj1))._flddelegate.requestAccessibilityFocusOnNextUpdate(i);
                obj = ((RecyclerViewAccessibilityDelegateHelper) (obj1)).hostView;
                AccessibilityDelegateCompat.DEFAULT_DELEGATE.AccessibilityEvent(((android.view.View) (obj)), 2048);
            }
        }
    }

    teHelper.Delegate(TimelineRecyclerView timelinerecyclerview, Supplier supplier)
    {
        arg$1 = timelinerecyclerview;
        arg$2 = supplier;
    }
}
