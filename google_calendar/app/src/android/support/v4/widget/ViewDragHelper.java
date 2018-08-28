// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

public final class ViewDragHelper
{

    private static final Interpolator sInterpolator = new _cls1();
    public int mActivePointerId;
    private final Callback mCallback;
    public View mCapturedView;
    public int mDragState;
    public int mEdgeDragsInProgress[];
    public int mEdgeDragsLocked[];
    public int mEdgeSize;
    public int mInitialEdgesTouched[];
    public float mInitialMotionX[];
    public float mInitialMotionY[];
    public float mLastMotionX[];
    public float mLastMotionY[];
    private float mMaxVelocity;
    public float mMinVelocity;
    private final ViewGroup mParentView;
    public int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final Runnable mSetIdleRunnable = new _cls2();
    public int mTouchSlop;
    public int mTrackingEdges;
    public VelocityTracker mVelocityTracker;

    public ViewDragHelper(Context context, ViewGroup viewgroup, Callback callback)
    {
        mActivePointerId = -1;
        if (viewgroup == null)
        {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null)
        {
            throw new IllegalArgumentException("Callback may not be null");
        } else
        {
            mParentView = viewgroup;
            mCallback = callback;
            viewgroup = ViewConfiguration.get(context);
            mEdgeSize = (int)(context.getResources().getDisplayMetrics().density * 20F + 0.5F);
            mTouchSlop = viewgroup.getScaledTouchSlop();
            mMaxVelocity = viewgroup.getScaledMaximumFlingVelocity();
            mMinVelocity = viewgroup.getScaledMinimumFlingVelocity();
            mScroller = new OverScroller(context, sInterpolator);
            return;
        }
    }

    private final boolean checkNewEdgeDrag(float f, float f1, int i, int j)
    {
        f = Math.abs(f);
        for (f1 = Math.abs(f1); (mInitialEdgesTouched[i] & j) != j || (mTrackingEdges & j) == 0 || (mEdgeDragsLocked[i] & j) == j || (mEdgeDragsInProgress[i] & j) == j || f <= (float)mTouchSlop && f1 <= (float)mTouchSlop || (mEdgeDragsInProgress[i] & j) != 0 || f <= (float)mTouchSlop;)
        {
            return false;
        }

        return true;
    }

    private final boolean checkTouchSlop(View view, float f, float f1)
    {
        if (view != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        boolean flag;
        boolean flag1;
        if (mCallback.getViewHorizontalDragRange(view) > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR554G____0() > 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag || !flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (f * f + f1 * f1 > (float)(mTouchSlop * mTouchSlop))
        {
            return true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (Math.abs(f) <= (float)mTouchSlop) goto _L1; else goto _L4
_L4:
        return true;
        if (!flag1 || Math.abs(f1) <= (float)mTouchSlop) goto _L1; else goto _L5
_L5:
        return true;
    }

    private static float clampMag(float f, float f1, float f2)
    {
        float f3 = Math.abs(f);
        if (f3 < f1)
        {
            f1 = 0.0F;
        } else
        if (f3 > f2)
        {
            f1 = f2;
            if (f <= 0.0F)
            {
                return -f2;
            }
        } else
        {
            return f;
        }
        return f1;
    }

    private static int clampMag(int i, int j, int k)
    {
        int l = Math.abs(i);
        if (l < j)
        {
            j = 0;
        } else
        if (l > k)
        {
            j = k;
            if (i <= 0)
            {
                return -k;
            }
        } else
        {
            return i;
        }
        return j;
    }

    private final void clearMotionHistory(int i)
    {
label0:
        {
            if (mInitialMotionX != null)
            {
                boolean flag;
                if ((mPointersDown & 1 << i) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            return;
        }
        mInitialMotionX[i] = 0.0F;
        mInitialMotionY[i] = 0.0F;
        mLastMotionX[i] = 0.0F;
        mLastMotionY[i] = 0.0F;
        mInitialEdgesTouched[i] = 0;
        mEdgeDragsInProgress[i] = 0;
        mEdgeDragsLocked[i] = 0;
        mPointersDown = mPointersDown & ~(1 << i);
    }

    private final int computeAxisDuration(int i, int j, int k)
    {
        if (i == 0)
        {
            return 0;
        }
        int l = mParentView.getWidth();
        int i1 = l / 2;
        float f2 = Math.min(1.0F, (float)Math.abs(i) / (float)l);
        float f = i1;
        float f1 = i1;
        f2 = (float)Math.sin((f2 - 0.5F) * 0.4712389F);
        j = Math.abs(j);
        if (j > 0)
        {
            i = Math.round(Math.abs((f2 * f1 + f) / (float)j) * 1000F) * 4;
        } else
        {
            i = (int)(((float)Math.abs(i) / (float)k + 1.0F) * 256F);
        }
        return Math.min(i, 600);
    }

    public static ViewDragHelper create(ViewGroup viewgroup, float f, Callback callback)
    {
        viewgroup = new ViewDragHelper(viewgroup.getContext(), viewgroup, callback);
        viewgroup.mTouchSlop = (int)((float)((ViewDragHelper) (viewgroup)).mTouchSlop * (1.0F / f));
        return viewgroup;
    }

    private final void dispatchViewReleased(float f, float f1)
    {
        mReleaseInProgress = true;
        mCallback.onViewReleased(mCapturedView, f, f1);
        mReleaseInProgress = false;
        if (mDragState == 1)
        {
            setDragState(0);
        }
    }

    private final boolean forceSettleCapturedViewAt(int i, int j, int k, int l)
    {
        int i1 = mCapturedView.getLeft();
        int j1 = mCapturedView.getTop();
        i -= i1;
        j -= j1;
        if (i == 0 && j == 0)
        {
            mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        View view = mCapturedView;
        k = clampMag(k, (int)mMinVelocity, (int)mMaxVelocity);
        l = clampMag(l, (int)mMinVelocity, (int)mMaxVelocity);
        int k1 = Math.abs(i);
        int l1 = Math.abs(j);
        int i2 = Math.abs(k);
        int j2 = Math.abs(l);
        int k2 = i2 + j2;
        int l2 = k1 + l1;
        float f;
        float f1;
        float f2;
        if (k != 0)
        {
            f = (float)i2 / (float)k2;
        } else
        {
            f = (float)k1 / (float)l2;
        }
        if (l != 0)
        {
            f1 = (float)j2 / (float)k2;
        } else
        {
            f1 = (float)l1 / (float)l2;
        }
        k = computeAxisDuration(i, k, mCallback.getViewHorizontalDragRange(view));
        l = computeAxisDuration(j, l, mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR554G____0());
        f2 = k;
        k = (int)(f1 * (float)l + f * f2);
        mScroller.startScroll(i1, j1, i, j, k);
        setDragState(2);
        return true;
    }

    private final boolean isValidPointerForActionMove(int i)
    {
        boolean flag;
        if ((mPointersDown & 1 << i) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            Log.e("ViewDragHelper", (new StringBuilder("Ignoring pointerId=")).append(i).append(" because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because ").append(" ViewDragHelper did not receive all the events in the event stream.").toString());
            return false;
        } else
        {
            return true;
        }
    }

    private final void releaseViewForPointerUp()
    {
        mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
        dispatchViewReleased(clampMag(mVelocityTracker.getXVelocity(mActivePointerId), mMinVelocity, mMaxVelocity), clampMag(mVelocityTracker.getYVelocity(mActivePointerId), mMinVelocity, mMaxVelocity));
    }

    private final void reportNewEdgeDrags(float f, float f1, int i)
    {
        int k = 1;
        int j;
        if (!checkNewEdgeDrag(f, f1, i, 1))
        {
            k = 0;
        }
        j = k;
        if (checkNewEdgeDrag(f1, f, i, 4))
        {
            j = k | 4;
        }
        k = j;
        if (checkNewEdgeDrag(f, f1, i, 2))
        {
            k = j | 2;
        }
        j = k;
        if (checkNewEdgeDrag(f1, f, i, 8))
        {
            j = k | 8;
        }
        if (j != 0)
        {
            int ai[] = mEdgeDragsInProgress;
            ai[i] = ai[i] | j;
            mCallback.onEdgeDragStarted(j, i);
        }
    }

    private final void saveInitialMotion(float f, float f1, int i)
    {
        int k = 0;
        if (mInitialMotionX == null || mInitialMotionX.length <= i)
        {
            float af[] = new float[i + 1];
            float af2[] = new float[i + 1];
            float af3[] = new float[i + 1];
            float af4[] = new float[i + 1];
            int ai[] = new int[i + 1];
            int ai1[] = new int[i + 1];
            int ai2[] = new int[i + 1];
            if (mInitialMotionX != null)
            {
                System.arraycopy(mInitialMotionX, 0, af, 0, mInitialMotionX.length);
                System.arraycopy(mInitialMotionY, 0, af2, 0, mInitialMotionY.length);
                System.arraycopy(mLastMotionX, 0, af3, 0, mLastMotionX.length);
                System.arraycopy(mLastMotionY, 0, af4, 0, mLastMotionY.length);
                System.arraycopy(mInitialEdgesTouched, 0, ai, 0, mInitialEdgesTouched.length);
                System.arraycopy(mEdgeDragsInProgress, 0, ai1, 0, mEdgeDragsInProgress.length);
                System.arraycopy(mEdgeDragsLocked, 0, ai2, 0, mEdgeDragsLocked.length);
            }
            mInitialMotionX = af;
            mInitialMotionY = af2;
            mLastMotionX = af3;
            mLastMotionY = af4;
            mInitialEdgesTouched = ai;
            mEdgeDragsInProgress = ai1;
            mEdgeDragsLocked = ai2;
        }
        float af1[] = mInitialMotionX;
        mLastMotionX[i] = f;
        af1[i] = f;
        af1 = mInitialMotionY;
        mLastMotionY[i] = f1;
        af1[i] = f1;
        af1 = mInitialEdgesTouched;
        int i1 = (int)f;
        int l = (int)f1;
        if (i1 < mParentView.getLeft() + mEdgeSize)
        {
            k = 1;
        }
        int j = k;
        if (l < mParentView.getTop() + mEdgeSize)
        {
            j = k | 4;
        }
        k = j;
        if (i1 > mParentView.getRight() - mEdgeSize)
        {
            k = j | 2;
        }
        j = k;
        if (l > mParentView.getBottom() - mEdgeSize)
        {
            j = k | 8;
        }
        af1[i] = j;
        mPointersDown = mPointersDown | 1 << i;
    }

    private final void saveLastMotion(MotionEvent motionevent)
    {
        int j = motionevent.getPointerCount();
        for (int i = 0; i < j; i++)
        {
            int k = motionevent.getPointerId(i);
            if (isValidPointerForActionMove(k))
            {
                float f = motionevent.getX(i);
                float f1 = motionevent.getY(i);
                mLastMotionX[k] = f;
                mLastMotionY[k] = f1;
            }
        }

    }

    private final boolean tryCaptureViewForDrag(View view, int i)
    {
        if (view == mCapturedView && mActivePointerId == i)
        {
            return true;
        }
        if (view != null && mCallback.tryCaptureView(view, i))
        {
            mActivePointerId = i;
            captureChildView(view, i);
            return true;
        } else
        {
            return false;
        }
    }

    public final void cancel()
    {
        mActivePointerId = -1;
        if (mInitialMotionX != null)
        {
            Arrays.fill(mInitialMotionX, 0.0F);
            Arrays.fill(mInitialMotionY, 0.0F);
            Arrays.fill(mLastMotionX, 0.0F);
            Arrays.fill(mLastMotionY, 0.0F);
            Arrays.fill(mInitialEdgesTouched, 0);
            Arrays.fill(mEdgeDragsInProgress, 0);
            Arrays.fill(mEdgeDragsLocked, 0);
            mPointersDown = 0;
        }
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public final void captureChildView(View view, int i)
    {
        if (view.getParent() != mParentView)
        {
            throw new IllegalArgumentException((new StringBuilder("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (")).append(mParentView).append(")").toString());
        } else
        {
            mCapturedView = view;
            mActivePointerId = i;
            mCallback.onViewCaptured(view, i);
            setDragState(1);
            return;
        }
    }

    public final boolean continueSettling(boolean flag)
    {
        boolean flag1 = false;
        if (mDragState == 2)
        {
            boolean flag2 = mScroller.computeScrollOffset();
            int i = mScroller.getCurrX();
            int j = mScroller.getCurrY();
            int k = i - mCapturedView.getLeft();
            int l = j - mCapturedView.getTop();
            if (k != 0)
            {
                ViewCompat.offsetLeftAndRight(mCapturedView, k);
            }
            if (l != 0)
            {
                ViewCompat.offsetTopAndBottom(mCapturedView, l);
            }
            if (k != 0 || l != 0)
            {
                mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(mCapturedView, i, j);
            }
            flag = flag2;
            if (flag2)
            {
                flag = flag2;
                if (i == mScroller.getFinalX())
                {
                    flag = flag2;
                    if (j == mScroller.getFinalY())
                    {
                        mScroller.abortAnimation();
                        flag = false;
                    }
                }
            }
            if (!flag)
            {
                mParentView.post(mSetIdleRunnable);
            }
        }
        flag = flag1;
        if (mDragState == 2)
        {
            flag = true;
        }
        return flag;
    }

    public final View findTopChildUnder(int i, int j)
    {
        for (int k = mParentView.getChildCount() - 1; k >= 0; k--)
        {
            View view = mParentView.getChildAt(k);
            if (i >= view.getLeft() && i < view.getRight() && j >= view.getTop() && j < view.getBottom())
            {
                return view;
            }
        }

        return null;
    }

    public final void processTouchEvent(MotionEvent motionevent)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        k = 0;
        i = 0;
        j = 0;
        i1 = motionevent.getActionMasked();
        l = motionevent.getActionIndex();
        if (i1 == 0)
        {
            cancel();
        }
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        i1;
        JVM INSTR tableswitch 0 6: default 96
    //                   0 97
    //                   1 812
    //                   2 330
    //                   3 829
    //                   4 96
    //                   5 162
    //                   6 683;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        return;
_L2:
        float f = motionevent.getX();
        float f2 = motionevent.getY();
        i = motionevent.getPointerId(0);
        motionevent = findTopChildUnder((int)f, (int)f2);
        saveInitialMotion(f, f2, i);
        tryCaptureViewForDrag(motionevent, i);
        if ((mInitialEdgesTouched[i] & mTrackingEdges) != 0)
        {
            mCallback._mth514KIAAM0();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        float f1;
        float f3;
        k = motionevent.getPointerId(l);
        f1 = motionevent.getX(l);
        f3 = motionevent.getY(l);
        saveInitialMotion(f1, f3, k);
        if (mDragState != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        tryCaptureViewForDrag(findTopChildUnder((int)f1, (int)f3), k);
        if ((mInitialEdgesTouched[k] & mTrackingEdges) != 0)
        {
            mCallback._mth514KIAAM0();
            return;
        }
        if (true) goto _L1; else goto _L8
_L8:
        l = (int)f1;
        int j1 = (int)f3;
        motionevent = mCapturedView;
        i = j;
        if (motionevent != null)
        {
            i = j;
            if (l >= motionevent.getLeft())
            {
                i = j;
                if (l < motionevent.getRight())
                {
                    i = j;
                    if (j1 >= motionevent.getTop())
                    {
                        i = j;
                        if (j1 < motionevent.getBottom())
                        {
                            i = 1;
                        }
                    }
                }
            }
        }
        if (i != 0)
        {
            tryCaptureViewForDrag(mCapturedView, k);
            return;
        }
          goto _L1
_L4:
        if (mDragState == 1)
        {
            if (isValidPointerForActionMove(mActivePointerId))
            {
                i = motionevent.findPointerIndex(mActivePointerId);
                f1 = motionevent.getX(i);
                f3 = motionevent.getY(i);
                l = (int)(f1 - mLastMotionX[mActivePointerId]);
                int k1 = (int)(f3 - mLastMotionY[mActivePointerId]);
                j = mCapturedView.getLeft() + l;
                k = mCapturedView.getTop() + k1;
                int i2 = mCapturedView.getLeft();
                int l1 = mCapturedView.getTop();
                i = j;
                if (l != 0)
                {
                    i = mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(mCapturedView, j);
                    ViewCompat.offsetLeftAndRight(mCapturedView, i - i2);
                }
                j = k;
                if (k1 != 0)
                {
                    j = mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(mCapturedView, k);
                    ViewCompat.offsetTopAndBottom(mCapturedView, j - l1);
                }
                if (l != 0 || k1 != 0)
                {
                    mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(mCapturedView, i, j);
                }
                saveLastMotion(motionevent);
                return;
            }
        } else
        {
            j = motionevent.getPointerCount();
            for (i = k; i < j; i++)
            {
                k = motionevent.getPointerId(i);
                if (!isValidPointerForActionMove(k))
                {
                    continue;
                }
                f1 = motionevent.getX(i);
                f3 = motionevent.getY(i);
                float f4 = f1 - mInitialMotionX[k];
                float f5 = f3 - mInitialMotionY[k];
                reportNewEdgeDrags(f4, f5, k);
                if (mDragState == 1)
                {
                    break;
                }
                View view = findTopChildUnder((int)f1, (int)f3);
                if (checkTouchSlop(view, f4, f5) && tryCaptureViewForDrag(view, k))
                {
                    break;
                }
            }

            saveLastMotion(motionevent);
            return;
        }
          goto _L1
_L7:
        j = motionevent.getPointerId(l);
        if (mDragState != 1 || j != mActivePointerId) goto _L10; else goto _L9
_L9:
        k = motionevent.getPointerCount();
_L16:
        if (i >= k) goto _L12; else goto _L11
_L11:
        l = motionevent.getPointerId(i);
        if (l == mActivePointerId) goto _L14; else goto _L13
_L13:
        f1 = motionevent.getX(i);
        f3 = motionevent.getY(i);
        if (findTopChildUnder((int)f1, (int)f3) != mCapturedView || !tryCaptureViewForDrag(mCapturedView, l)) goto _L14; else goto _L15
_L15:
        i = mActivePointerId;
_L17:
        if (i == -1)
        {
            releaseViewForPointerUp();
        }
_L10:
        clearMotionHistory(j);
        return;
_L14:
        i++;
          goto _L16
_L3:
        if (mDragState == 1)
        {
            releaseViewForPointerUp();
        }
        cancel();
        return;
_L5:
        if (mDragState == 1)
        {
            dispatchViewReleased(0.0F, 0.0F);
        }
        cancel();
        return;
_L12:
        i = -1;
          goto _L17
    }

    final void setDragState(int i)
    {
        mParentView.removeCallbacks(mSetIdleRunnable);
        if (mDragState != i)
        {
            mDragState = i;
            mCallback.onViewDragStateChanged(i);
            if (mDragState == 0)
            {
                mCapturedView = null;
            }
        }
    }

    public final boolean settleCapturedViewAt(int i, int j)
    {
        if (!mReleaseInProgress)
        {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        } else
        {
            return forceSettleCapturedViewAt(i, j, (int)mVelocityTracker.getXVelocity(mActivePointerId), (int)mVelocityTracker.getYVelocity(mActivePointerId));
        }
    }

    public final boolean shouldInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        int j;
        j = motionevent.getActionMasked();
        i = motionevent.getActionIndex();
        if (j == 0)
        {
            cancel();
        }
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        j;
        JVM INSTR tableswitch 0 6: default 88
    //                   0 98
    //                   1 578
    //                   2 279
    //                   3 578
    //                   4 88
    //                   5 181
    //                   6 565;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L5 _L6
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_578;
_L7:
        float f;
        float f3;
        return mDragState == 1;
_L2:
        f = motionevent.getX();
        f3 = motionevent.getY();
        i = motionevent.getPointerId(0);
        saveInitialMotion(f, f3, i);
        motionevent = findTopChildUnder((int)f, (int)f3);
        if (motionevent == mCapturedView && mDragState == 2)
        {
            tryCaptureViewForDrag(motionevent, i);
        }
        if ((mInitialEdgesTouched[i] & mTrackingEdges) != 0)
        {
            mCallback._mth514KIAAM0();
        }
          goto _L7
_L5:
        int k = motionevent.getPointerId(i);
        float f1 = motionevent.getX(i);
        float f4 = motionevent.getY(i);
        saveInitialMotion(f1, f4, k);
        if (mDragState == 0)
        {
            if ((mInitialEdgesTouched[k] & mTrackingEdges) != 0)
            {
                mCallback._mth514KIAAM0();
            }
        } else
        if (mDragState == 2)
        {
            motionevent = findTopChildUnder((int)f1, (int)f4);
            if (motionevent == mCapturedView)
            {
                tryCaptureViewForDrag(motionevent, k);
            }
        }
          goto _L7
_L4:
        if (mInitialMotionX != null && mInitialMotionY != null)
        {
            int l = motionevent.getPointerCount();
            i = 0;
            while (i < l) 
            {
                int i1 = motionevent.getPointerId(i);
                if (!isValidPointerForActionMove(i1))
                {
                    continue;
                }
                float f2 = motionevent.getX(i);
                float f5 = motionevent.getY(i);
                float f6 = f2 - mInitialMotionX[i1];
                float f7 = f5 - mInitialMotionY[i1];
                View view = findTopChildUnder((int)f2, (int)f5);
                boolean flag;
                if (view != null && checkTouchSlop(view, f6, f7))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    int j1 = view.getLeft();
                    int k1 = (int)f6;
                    k1 = mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(view, k1 + j1);
                    int l1 = view.getTop();
                    int i2 = (int)f7;
                    i2 = mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(view, i2 + l1);
                    int j2 = mCallback.getViewHorizontalDragRange(view);
                    int k2 = mCallback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR554G____0();
                    if ((j2 == 0 || j2 > 0 && k1 == j1) && (k2 == 0 || k2 > 0 && i2 == l1))
                    {
                        break;
                    }
                }
                reportNewEdgeDrags(f6, f7, i1);
                if (mDragState == 1 || flag && tryCaptureViewForDrag(view, i1))
                {
                    break;
                }
                i++;
            }
            saveLastMotion(motionevent);
        }
          goto _L7
_L6:
        clearMotionHistory(motionevent.getPointerId(i));
          goto _L7
        cancel();
          goto _L7
    }

    public final boolean smoothSlideViewTo(View view, int i, int j)
    {
        mCapturedView = view;
        mActivePointerId = -1;
        boolean flag = forceSettleCapturedViewAt(i, j, 0, 0);
        if (!flag && mDragState == 0 && mCapturedView != null)
        {
            mCapturedView = null;
        }
        return flag;
    }


    private class _cls2
        implements Runnable
    {

        private final ViewDragHelper this$0;

        public final void run()
        {
            setDragState(0);
        }

        _cls2()
        {
            this$0 = ViewDragHelper.this;
            super();
        }
    }


    private class Callback
    {

        public int clampViewPositionHorizontal$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            return 0;
        }

        public int clampViewPositionVertical$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            return 0;
        }

        public int getViewHorizontalDragRange(View view)
        {
            return 0;
        }

        public int getViewVerticalDragRange$51662RJ4E9NMIP1FEPKMATPFAPKMATPR554G____0()
        {
            return 0;
        }

        public void onEdgeDragStarted(int i, int j)
        {
        }

        public void onEdgeTouched$514KIAAM0()
        {
        }

        public void onViewCaptured(View view, int i)
        {
        }

        public void onViewDragStateChanged(int i)
        {
        }

        public void onViewPositionChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i, int j)
        {
        }

        public void onViewReleased(View view, float f, float f1)
        {
        }

        public abstract boolean tryCaptureView(View view, int i);

        public Callback()
        {
        }
    }


    private class _cls1
        implements Interpolator
    {

        public final float getInterpolation(float f)
        {
            f--;
            return f * (f * f * f * f) + 1.0F;
        }

        _cls1()
        {
        }
    }

}
