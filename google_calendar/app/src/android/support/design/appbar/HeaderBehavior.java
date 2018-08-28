// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

// Referenced classes of package android.support.design.appbar:
//            ViewOffsetBehavior

class HeaderBehavior extends ViewOffsetBehavior
{

    private int activePointerId;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    public OverScroller scroller;
    private int touchSlop;
    private VelocityTracker velocityTracker;

    public HeaderBehavior()
    {
        activePointerId = -1;
        touchSlop = -1;
    }

    public HeaderBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        activePointerId = -1;
        touchSlop = -1;
    }

    boolean canDragView(View view)
    {
        return false;
    }

    int getMaxDragOffset(View view)
    {
        return -view.getHeight();
    }

    int getScrollRangeForDragFling(View view)
    {
        return view.getHeight();
    }

    int getTopBottomOffsetForScrollingSibling()
    {
        return getTopAndBottomOffset();
    }

    void onFlingFinished(CoordinatorLayout coordinatorlayout, View view)
    {
    }

    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (touchSlop < 0)
        {
            touchSlop = ViewConfiguration.get(coordinatorlayout.getContext()).getScaledTouchSlop();
        }
        if (motionevent.getAction() == 2 && isBeingDragged)
        {
            return true;
        }
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 72
    //                   0 92
    //                   1 229
    //                   2 163
    //                   3 229;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        if (velocityTracker != null)
        {
            velocityTracker.addMovement(motionevent);
        }
        return isBeingDragged;
_L2:
        isBeingDragged = false;
        int i = (int)motionevent.getX();
        int k = (int)motionevent.getY();
        if (canDragView(view) && coordinatorlayout.isPointInChildBounds(view, i, k))
        {
            lastMotionY = k;
            activePointerId = motionevent.getPointerId(0);
            if (velocityTracker == null)
            {
                velocityTracker = VelocityTracker.obtain();
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = activePointerId;
        if (j != -1)
        {
            j = motionevent.findPointerIndex(j);
            if (j != -1)
            {
                j = (int)motionevent.getY(j);
                if (Math.abs(j - lastMotionY) > touchSlop)
                {
                    isBeingDragged = true;
                    lastMotionY = j;
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        isBeingDragged = false;
        activePointerId = -1;
        if (velocityTracker != null)
        {
            velocityTracker.recycle();
            velocityTracker = null;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (touchSlop < 0)
        {
            touchSlop = ViewConfiguration.get(coordinatorlayout.getContext()).getScaledTouchSlop();
        }
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 56
    //                   0 73
    //                   1 275
    //                   2 141
    //                   3 419;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (velocityTracker != null)
        {
            velocityTracker.addMovement(motionevent);
        }
        return true;
_L2:
        int i = (int)motionevent.getX();
        int l = (int)motionevent.getY();
        if (coordinatorlayout.isPointInChildBounds(view, i, l) && canDragView(view))
        {
            lastMotionY = l;
            activePointerId = motionevent.getPointerId(0);
            if (velocityTracker == null)
            {
                velocityTracker = VelocityTracker.obtain();
            }
        } else
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = motionevent.findPointerIndex(activePointerId);
        if (j == -1)
        {
            return false;
        }
        int j1 = (int)motionevent.getY(j);
        int i1 = lastMotionY - j1;
        j = i1;
        if (!isBeingDragged)
        {
            j = i1;
            if (Math.abs(i1) > touchSlop)
            {
                isBeingDragged = true;
                if (i1 > 0)
                {
                    j = i1 - touchSlop;
                } else
                {
                    j = i1 + touchSlop;
                }
            }
        }
        if (isBeingDragged)
        {
            lastMotionY = j1;
            i1 = getMaxDragOffset(view);
            setHeaderTopBottomOffset(coordinatorlayout, view, getTopBottomOffsetForScrollingSibling() - j, i1, 0);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (velocityTracker != null)
        {
            velocityTracker.addMovement(motionevent);
            velocityTracker.computeCurrentVelocity(1000);
            float f = velocityTracker.getYVelocity(activePointerId);
            int k = -getScrollRangeForDragFling(view);
            if (flingRunnable != null)
            {
                view.removeCallbacks(flingRunnable);
                flingRunnable = null;
            }
            if (scroller == null)
            {
                scroller = new OverScroller(view.getContext());
            }
            scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(f), 0, 0, k, 0);
            if (scroller.computeScrollOffset())
            {
                flingRunnable = new FlingRunnable(coordinatorlayout, view);
                ViewCompat.postOnAnimation(view, flingRunnable);
            } else
            {
                onFlingFinished(coordinatorlayout, view);
            }
        }
_L5:
        isBeingDragged = false;
        activePointerId = -1;
        if (velocityTracker != null)
        {
            velocityTracker.recycle();
            velocityTracker = null;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k)
    {
        int i1 = getTopAndBottomOffset();
        boolean flag = false;
        int l = ((flag) ? 1 : 0);
        if (j != 0)
        {
            l = ((flag) ? 1 : 0);
            if (i1 >= j)
            {
                l = ((flag) ? 1 : 0);
                if (i1 <= k)
                {
                    if (i >= j)
                    {
                        if (i > k)
                        {
                            j = k;
                        } else
                        {
                            j = i;
                        }
                    }
                    l = ((flag) ? 1 : 0);
                    if (i1 != j)
                    {
                        setTopAndBottomOffset(j);
                        l = i1 - j;
                    }
                }
            }
        }
        return l;
    }

    private class FlingRunnable
        implements Runnable
    {

        private final View layout;
        private final CoordinatorLayout parent;
        private final HeaderBehavior this$0;

        public final void run()
        {
label0:
            {
                if (layout != null && scroller != null)
                {
                    if (!scroller.computeScrollOffset())
                    {
                        break label0;
                    }
                    setHeaderTopBottomOffset(parent, layout, scroller.getCurrY(), 0x80000000, 0x7fffffff);
                    ViewCompat.postOnAnimation(layout, this);
                }
                return;
            }
            onFlingFinished(parent, layout);
        }

        FlingRunnable(CoordinatorLayout coordinatorlayout, View view)
        {
            this$0 = HeaderBehavior.this;
            super();
            parent = coordinatorlayout;
            layout = view;
        }
    }

}
