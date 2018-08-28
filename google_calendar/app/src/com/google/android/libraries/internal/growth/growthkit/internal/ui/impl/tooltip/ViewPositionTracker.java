// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            ViewPosition

public final class ViewPositionTracker
{

    public View containerView;
    public ViewPosition currentViewPosition;
    public boolean isPreDrawListenerAdded;
    private ViewPosition lastViewPosition;
    public OnTrackingViewChangedListener onTrackingViewChangedListener;
    public OnViewPositionChangedListener onViewPositionChangedListener;
    public final android.view.ViewTreeObserver.OnPreDrawListener preDrawListener;
    public WeakReference trackingViewReference;

    public ViewPositionTracker(View view)
    {
        if (view == null)
        {
            throw new NullPointerException();
        } else
        {
            containerView = (View)view;
            lastViewPosition = new ViewPosition();
            currentViewPosition = new ViewPosition();
            preDrawListener = new _cls1();
            isPreDrawListenerAdded = false;
            return;
        }
    }

    final void calculateViewPosition()
    {
        Object obj;
        ViewPosition viewposition;
        View view;
        if (trackingViewReference != null)
        {
            obj = (View)trackingViewReference.get();
        } else
        {
            obj = null;
        }
        if (obj == null) goto _L2; else goto _L1
_L1:
        viewposition = lastViewPosition;
        lastViewPosition = currentViewPosition;
        view = containerView;
        if (viewposition == null)
        {
            throw new NullPointerException();
        }
        viewposition.viewRect.set(0, 0, 0, 0);
        viewposition.clipRect.set(0, 0, 0x3fffffff, 0x3fffffff);
        if (obj == null || view == null) goto _L4; else goto _L3
_L3:
        Object obj1;
        ViewParent viewparent;
        if (!(view instanceof ViewParent))
        {
            break MISSING_BLOCK_LABEL_228;
        }
        viewparent = (ViewParent)view;
        obj1 = obj;
_L11:
        if (obj1 == null) goto _L6; else goto _L5
_L5:
        if (obj1 != viewparent) goto _L8; else goto _L7
_L7:
        boolean flag = true;
_L12:
        if (flag)
        {
            viewposition.viewRect.set(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
            int i = 0;
            while (obj != view && obj != null) 
            {
                obj1 = (View)((View) (obj)).getParent();
                int j = ((View) (obj)).getLeft();
                int k = (int)((View) (obj)).getTranslationX() + j;
                j = ((View) (obj)).getTop() + (int)((View) (obj)).getTranslationY();
                if (obj1 instanceof ViewPager)
                {
                    obj = (ViewPager)obj1;
                    int l = ((ViewPager) (obj)).getScrollX();
                    j -= ((ViewPager) (obj)).getScrollY();
                    k -= l;
                }
                viewposition.viewRect.offset(k, j);
                if (i != 0)
                {
                    i = ((View) (obj1)).getWidth();
                    int i1 = ((View) (obj1)).getHeight();
                    viewposition.clipRect.left = Math.max(viewposition.clipRect.left + k, 0);
                    viewposition.clipRect.top = Math.max(viewposition.clipRect.top + j, 0);
                    viewposition.clipRect.right = Math.min(k + viewposition.clipRect.right, i);
                    viewposition.clipRect.bottom = Math.min(j + viewposition.clipRect.bottom, i1);
                    obj = ((View) (obj1)).getTag(0x7f10001e);
                    if (obj instanceof ViewPosition.ClipSupplier)
                    {
                        obj = (Rect)((ViewPosition.ClipSupplier)obj).get();
                        viewposition.clipRect.left = Math.max(viewposition.clipRect.left, ((Rect) (obj)).left);
                        viewposition.clipRect.top = Math.max(viewposition.clipRect.top, ((Rect) (obj)).top);
                        viewposition.clipRect.right = Math.min(viewposition.clipRect.right, ((Rect) (obj)).right);
                        viewposition.clipRect.bottom = Math.min(viewposition.clipRect.bottom, ((Rect) (obj)).bottom);
                    }
                }
                i = 1;
                obj = obj1;
            }
        }
_L4:
        currentViewPosition = viewposition;
        if (onViewPositionChangedListener != null)
        {
            obj = currentViewPosition;
            obj1 = lastViewPosition;
            boolean flag1 = ((ViewPosition) (obj)).isVisible();
            boolean flag2 = ((ViewPosition) (obj1)).isVisible();
            if (!flag1 && !flag2)
            {
                flag1 = true;
            } else
            {
                flag1 = ((ViewPosition) (obj)).equals(obj1);
            }
            if (!flag1)
            {
                onViewPositionChangedListener.onViewPositionChanged(currentViewPosition);
            }
        }
_L2:
        return;
_L8:
        if (!(((View) (obj1)).getParent() instanceof View)) goto _L6; else goto _L9
_L9:
        obj1 = ((View) (obj1)).getParent();
        if (!(obj1 instanceof View)) goto _L6; else goto _L10
_L10:
        obj1 = (View)obj1;
          goto _L11
_L6:
        flag = false;
          goto _L12
        if (obj == view)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L12
    }

    private class _cls1
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final ViewPositionTracker this$0;

        public final boolean onPreDraw()
        {
            calculateViewPosition();
            return true;
        }

        _cls1()
        {
            this$0 = ViewPositionTracker.this;
            super();
        }
    }


    private class OnViewPositionChangedListener
    {

        public abstract void onViewPositionChanged(ViewPosition viewposition);
    }

}
