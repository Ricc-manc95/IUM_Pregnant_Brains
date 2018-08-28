// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SwipeDismissBehavior extends android.support.design.widget.CoordinatorLayout.Behavior
{
    public static interface OnDismissListener
    {

        public abstract void onDismiss(View view);

        public abstract void onDragStateChanged(int i);
    }

    final class SettleRunnable
        implements Runnable
    {

        private final boolean dismiss;
        private final SwipeDismissBehavior this$0;
        private final View view;

        public final void run()
        {
            if (viewDragHelper != null && viewDragHelper.continueSettling(true))
            {
                ViewCompat.postOnAnimation(view, this);
            } else
            if (dismiss && listener != null)
            {
                listener.onDismiss(view);
                return;
            }
        }

        SettleRunnable(View view1, boolean flag)
        {
            this$0 = SwipeDismissBehavior.this;
            super();
            view = view1;
            dismiss = flag;
        }
    }


    public float alphaEndSwipeDistance;
    public float alphaStartSwipeDistance;
    private final android.support.v4.widget.ViewDragHelper.Callback dragCallback = new _cls1();
    public float dragDismissThreshold;
    private boolean interceptingEvents;
    public OnDismissListener listener;
    private float sensitivity;
    public int swipeDirection;
    public ViewDragHelper viewDragHelper;

    public SwipeDismissBehavior()
    {
        sensitivity = 0.0F;
        swipeDirection = 2;
        dragDismissThreshold = 0.5F;
        alphaStartSwipeDistance = 0.0F;
        alphaEndSwipeDistance = 0.5F;
    }

    public boolean canSwipeDismissView(View view)
    {
        return true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = interceptingEvents;
        flag = flag2;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 48
    //                   0 99
    //                   1 127
    //                   2 52
    //                   3 127;
           goto _L1 _L2 _L3 _L4 _L3
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        flag = flag2;
_L6:
        if (flag)
        {
            if (viewDragHelper == null)
            {
                view = dragCallback;
                viewDragHelper = new ViewDragHelper(coordinatorlayout.getContext(), coordinatorlayout, view);
            }
            flag1 = viewDragHelper.shouldInterceptTouchEvent(motionevent);
        }
        return flag1;
_L2:
        interceptingEvents = coordinatorlayout.isPointInChildBounds(view, (int)motionevent.getX(), (int)motionevent.getY());
        flag = interceptingEvents;
        continue; /* Loop/switch isn't completed */
_L3:
        interceptingEvents = false;
        flag = flag2;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (viewDragHelper != null)
        {
            viewDragHelper.processTouchEvent(motionevent);
            return true;
        } else
        {
            return false;
        }
    }

    private class _cls1 extends android.support.v4.widget.ViewDragHelper.Callback
    {

        private int activePointerId;
        private int originalCapturedViewLeft;
        private final SwipeDismissBehavior this$0;

        public final int clampViewPositionHorizontal$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            int j;
            int k;
            if (ViewCompat.getLayoutDirection(view) == 1)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (swipeDirection == 0)
            {
                if (j != 0)
                {
                    k = originalCapturedViewLeft - view.getWidth();
                    j = originalCapturedViewLeft;
                } else
                {
                    k = originalCapturedViewLeft;
                    j = originalCapturedViewLeft + view.getWidth();
                }
            } else
            if (swipeDirection == 1)
            {
                if (j != 0)
                {
                    k = originalCapturedViewLeft;
                    j = originalCapturedViewLeft + view.getWidth();
                } else
                {
                    k = originalCapturedViewLeft - view.getWidth();
                    j = originalCapturedViewLeft;
                }
            } else
            {
                k = originalCapturedViewLeft - view.getWidth();
                j = originalCapturedViewLeft + view.getWidth();
            }
            return Math.min(Math.max(k, i), j);
        }

        public final int clampViewPositionVertical$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            return view.getTop();
        }

        public final int getViewHorizontalDragRange(View view)
        {
            return view.getWidth();
        }

        public final void onViewCaptured(View view, int i)
        {
            activePointerId = i;
            originalCapturedViewLeft = view.getLeft();
            view = view.getParent();
            if (view != null)
            {
                view.requestDisallowInterceptTouchEvent(true);
            }
        }

        public final void onViewDragStateChanged(int i)
        {
            if (listener != null)
            {
                listener.onDragStateChanged(i);
            }
        }

        public final void onViewPositionChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i, int j)
        {
            float f = (float)originalCapturedViewLeft + (float)view.getWidth() * alphaStartSwipeDistance;
            float f1 = (float)originalCapturedViewLeft + (float)view.getWidth() * alphaEndSwipeDistance;
            if ((float)i <= f)
            {
                view.setAlpha(1.0F);
                return;
            }
            if ((float)i >= f1)
            {
                view.setAlpha(0.0F);
                return;
            } else
            {
                view.setAlpha(Math.min(Math.max(0.0F, 1.0F - ((float)i - f) / (f1 - f)), 1.0F));
                return;
            }
        }

        public final void onViewReleased(View view, float f, float f1)
        {
            boolean flag = true;
            activePointerId = -1;
            int j = view.getWidth();
            int i;
            if (f != 0.0F)
            {
                if (ViewCompat.getLayoutDirection(view) == 1)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                int k;
                int l;
                if (swipeDirection == 2)
                {
                    i = 1;
                } else
                if (swipeDirection == 0)
                {
                    if (i != 0)
                    {
                        if (f < 0.0F)
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                    } else
                    if (f > 0.0F)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                } else
                if (swipeDirection == 1)
                {
                    if (i != 0)
                    {
                        if (f > 0.0F)
                        {
                            i = 1;
                        } else
                        {
                            i = 0;
                        }
                    } else
                    if (f < 0.0F)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                } else
                {
                    i = 0;
                }
            } else
            {
                i = view.getLeft();
                k = originalCapturedViewLeft;
                l = Math.round((float)view.getWidth() * dragDismissThreshold);
                if (Math.abs(i - k) >= l)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
            }
            if (i != 0)
            {
                if (view.getLeft() < originalCapturedViewLeft)
                {
                    i = originalCapturedViewLeft - j;
                } else
                {
                    i = originalCapturedViewLeft + j;
                }
            } else
            {
                i = originalCapturedViewLeft;
                flag = false;
            }
            if (viewDragHelper.settleCapturedViewAt(i, view.getTop()))
            {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, flag));
            } else
            if (flag && listener != null)
            {
                listener.onDismiss(view);
                return;
            }
        }

        public final boolean tryCaptureView(View view, int i)
        {
            return activePointerId == -1 && canSwipeDismissView(view);
        }

        _cls1()
        {
            this$0 = SwipeDismissBehavior.this;
            super();
            activePointerId = -1;
        }
    }

}
