// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            TooltipManager, ViewPositionTracker, ViewPosition, TooltipModel

final class arg._cls1
    implements android.widget.ener
{

    private final TooltipManager arg$1;

    public final void onDismiss()
    {
        ViewPositionTracker viewpositiontracker;
        boolean flag;
        TooltipManager tooltipmanager = arg$1;
        viewpositiontracker = tooltipmanager.targetViewTracker;
        Object obj;
        int i;
        if (viewpositiontracker.trackingViewReference != null)
        {
            obj = (View)viewpositiontracker.trackingViewReference.get();
        } else
        {
            obj = null;
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        viewpositiontracker.trackingViewReference = new WeakReference(null);
        if (viewpositiontracker.onTrackingViewChangedListener != null)
        {
            viewpositiontracker.onTrackingViewChangedListener.onTrackingViewChanged(null);
        }
        obj = viewpositiontracker.containerView.getViewTreeObserver();
        if (false && !viewpositiontracker.isPreDrawListenerAdded)
        {
            ((ViewTreeObserver) (obj)).addOnPreDrawListener(viewpositiontracker.preDrawListener);
            viewpositiontracker.isPreDrawListenerAdded = true;
        }
        if (true && viewpositiontracker.isPreDrawListenerAdded)
        {
            ((ViewTreeObserver) (obj)).removeOnPreDrawListener(viewpositiontracker.preDrawListener);
            viewpositiontracker.isPreDrawListenerAdded = false;
        }
        if (false)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        obj = viewpositiontracker.currentViewPosition;
        i = Math.max(((ViewPosition) (obj)).viewRect.left, ((ViewPosition) (obj)).clipRect.left);
        if (Math.max(Math.min(((ViewPosition) (obj)).viewRect.right, ((ViewPosition) (obj)).clipRect.right) - i, 0) <= 0) goto _L2; else goto _L1
_L1:
        i = Math.max(((ViewPosition) (obj)).viewRect.top, ((ViewPosition) (obj)).clipRect.top);
        if (Math.max(Math.min(((ViewPosition) (obj)).viewRect.bottom, ((ViewPosition) (obj)).clipRect.bottom) - i, 0) <= 0) goto _L2; else goto _L3
_L3:
        flag = true;
_L4:
        if (flag)
        {
            obj = viewpositiontracker.currentViewPosition;
            ((ViewPosition) (obj)).viewRect.set(0, 0, 0, 0);
            ((ViewPosition) (obj)).clipRect.set(0, 0, 0x3fffffff, 0x3fffffff);
            if (viewpositiontracker.onViewPositionChangedListener != null)
            {
                viewpositiontracker.onViewPositionChangedListener.onViewPositionChanged(viewpositiontracker.currentViewPosition);
            }
        }
_L5:
        obj = tooltipmanager.model.dismissListener();
        if (obj != null)
        {
            ((tener) (obj)).onDismiss();
        }
        return;
_L2:
        flag = false;
          goto _L4
        viewpositiontracker.calculateViewPosition();
          goto _L5
    }

    tener(TooltipManager tooltipmanager)
    {
        arg$1 = tooltipmanager;
    }
}
