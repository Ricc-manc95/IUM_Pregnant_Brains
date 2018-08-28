// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.support.v4.widget:
//            DrawerLayout, ViewDragHelper

final class mAbsGravity extends mAbsGravity
{

    public final int mAbsGravity;
    public ViewDragHelper mDragger;
    public final Runnable mPeekRunnable = new _cls1();
    public final DrawerLayout this$0;

    public final int clampViewPositionHorizontal$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
    {
        if (checkDrawerViewAbsoluteGravity(view, 3))
        {
            return Math.max(-view.getWidth(), Math.min(i, 0));
        } else
        {
            int j = getWidth();
            return Math.max(j - view.getWidth(), Math.min(i, j));
        }
    }

    public final int clampViewPositionVertical$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
    {
        return view.getTop();
    }

    final void closeOtherDrawer()
    {
        byte byte0 = 3;
        if (mAbsGravity == 3)
        {
            byte0 = 5;
        }
        View view = findDrawerWithGravity(byte0);
        if (view != null)
        {
            closeDrawer(view, true);
        }
    }

    public final int getViewHorizontalDragRange(View view)
    {
        if (DrawerLayout.isDrawerView(view))
        {
            return view.getWidth();
        } else
        {
            return 0;
        }
    }

    public final void onEdgeDragStarted(int i, int j)
    {
        View view;
        if ((i & 1) == 1)
        {
            view = findDrawerWithGravity(3);
        } else
        {
            view = findDrawerWithGravity(5);
        }
        if (view != null && getDrawerLockMode(view) == 0)
        {
            mDragger.captureChildView(view, j);
        }
    }

    public final void onEdgeTouched$514KIAAM0()
    {
        postDelayed(mPeekRunnable, 160L);
    }

    public final void onViewCaptured(View view, int i)
    {
        ((mPeekRunnable)view.getLayoutParams()).eking = false;
        closeOtherDrawer();
    }

    public final void onViewDragStateChanged(int i)
    {
        updateDrawerState$514KIJ31DPI74RR9CGNNCQB5ESNLCQB5ESTIILG_0(i, mDragger.mCapturedView);
    }

    public final void onViewPositionChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i, int j)
    {
        j = view.getWidth();
        float f;
        if (checkDrawerViewAbsoluteGravity(view, 3))
        {
            f = (float)(j + i) / (float)j;
        } else
        {
            f = (float)(getWidth() - i) / (float)j;
        }
        setDrawerViewOffset(view, f);
        if (f == 0.0F)
        {
            i = 4;
        } else
        {
            i = 0;
        }
        view.setVisibility(i);
        invalidate();
    }

    public final void onViewReleased(View view, float f, float f1)
    {
        int k;
        f1 = DrawerLayout.getDrawerViewOffset(view);
        k = view.getWidth();
        if (!checkDrawerViewAbsoluteGravity(view, 3)) goto _L2; else goto _L1
_L1:
        int i;
        if (f > 0.0F || f == 0.0F && f1 > 0.5F)
        {
            i = 0;
        } else
        {
            i = -k;
        }
_L4:
        mDragger.settleCapturedViewAt(i, view.getTop());
        invalidate();
        return;
_L2:
        int j = getWidth();
        if (f >= 0.0F)
        {
            i = j;
            if (f != 0.0F)
            {
                continue; /* Loop/switch isn't completed */
            }
            i = j;
            if (f1 <= 0.5F)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        i = j - k;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final boolean tryCaptureView(View view, int i)
    {
        return DrawerLayout.isDrawerView(view) && checkDrawerViewAbsoluteGravity(view, mAbsGravity) && getDrawerLockMode(view) == 0;
    }

    _cls1.this._cls1(int i)
    {
        this$0 = DrawerLayout.this;
        super();
        class _cls1
            implements Runnable
        {

            private final DrawerLayout.ViewDragCallback this$1;

            public final void run()
            {
                boolean flag = false;
                Object obj1 = DrawerLayout.ViewDragCallback.this;
                int l = ((DrawerLayout.ViewDragCallback) (obj1)).mDragger.mEdgeSize;
                Object obj;
                int j;
                int k;
                if (((DrawerLayout.ViewDragCallback) (obj1)).mAbsGravity == 3)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    obj = ((DrawerLayout.ViewDragCallback) (obj1)).this$0.findDrawerWithGravity(3);
                    DrawerLayout.LayoutParams layoutparams;
                    long l1;
                    if (obj != null)
                    {
                        k = -((View) (obj)).getWidth();
                    } else
                    {
                        k = 0;
                    }
                    k += l;
                } else
                {
                    obj = ((DrawerLayout.ViewDragCallback) (obj1)).this$0.findDrawerWithGravity(5);
                    k = ((DrawerLayout.ViewDragCallback) (obj1)).this$0.getWidth();
                    k -= l;
                }
                if (obj != null && (j != 0 && ((View) (obj)).getLeft() < k || j == 0 && ((View) (obj)).getLeft() > k) && ((DrawerLayout.ViewDragCallback) (obj1)).this$0.getDrawerLockMode(((View) (obj))) == 0)
                {
                    layoutparams = (DrawerLayout.LayoutParams)((View) (obj)).getLayoutParams();
                    ((DrawerLayout.ViewDragCallback) (obj1)).mDragger.smoothSlideViewTo(((View) (obj)), k, ((View) (obj)).getTop());
                    layoutparams.isPeeking = true;
                    ((DrawerLayout.ViewDragCallback) (obj1)).this$0.invalidate();
                    ((DrawerLayout.ViewDragCallback) (obj1)).closeOtherDrawer();
                    obj = ((DrawerLayout.ViewDragCallback) (obj1)).this$0;
                    if (!((DrawerLayout) (obj)).mChildrenCanceledTouch)
                    {
                        l1 = SystemClock.uptimeMillis();
                        obj1 = MotionEvent.obtain(l1, l1, 3, 0.0F, 0.0F, 0);
                        k = ((DrawerLayout) (obj)).getChildCount();
                        for (j = ((flag) ? 1 : 0); j < k; j++)
                        {
                            ((DrawerLayout) (obj)).getChildAt(j).dispatchTouchEvent(((MotionEvent) (obj1)));
                        }

                        ((MotionEvent) (obj1)).recycle();
                        obj.mChildrenCanceledTouch = true;
                    }
                }
            }

            _cls1()
            {
                this$1 = DrawerLayout.ViewDragCallback.this;
                super();
            }
        }

        mAbsGravity = i;
    }
}
