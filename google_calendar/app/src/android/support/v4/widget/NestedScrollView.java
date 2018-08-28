// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.List;

public class NestedScrollView extends FrameLayout
    implements NestedScrollingParent2
{
    static final class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            view = (NestedScrollView)view;
            accessibilityevent.setClassName(android/widget/ScrollView.getName());
            boolean flag;
            if (view.getScrollRange() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            accessibilityevent.setScrollable(flag);
            accessibilityevent.setScrollX(view.getScrollX());
            accessibilityevent.setScrollY(view.getScrollY());
            accessibilityevent.setMaxScrollX(view.getScrollX());
            accessibilityevent.setMaxScrollY(view.getScrollRange());
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            view = (NestedScrollView)view;
            String s = android/widget/ScrollView.getName();
            accessibilitynodeinfocompat.mInfo.setClassName(s);
            if (view.isEnabled())
            {
                int i = view.getScrollRange();
                if (i > 0)
                {
                    accessibilitynodeinfocompat.mInfo.setScrollable(true);
                    if (view.getScrollY() > 0)
                    {
                        accessibilitynodeinfocompat.mInfo.addAction(8192);
                    }
                    if (view.getScrollY() < i)
                    {
                        accessibilitynodeinfocompat.mInfo.addAction(4096);
                    }
                }
            }
        }

        public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if (super.performAccessibilityAction(view, i, bundle))
            {
                return true;
            }
            view = (NestedScrollView)view;
            if (!view.isEnabled())
            {
                return false;
            }
            switch (i)
            {
            default:
                return false;

            case 4096: 
                i = Math.min((view.getHeight() - view.getPaddingBottom() - view.getPaddingTop()) + view.getScrollY(), view.getScrollRange());
                if (i != view.getScrollY())
                {
                    view.smoothScrollBy(0 - view.getScrollX(), i - view.getScrollY());
                    return true;
                } else
                {
                    return false;
                }

            case 8192: 
                i = view.getHeight();
                int j = view.getPaddingBottom();
                int k = view.getPaddingTop();
                i = Math.max(view.getScrollY() - (i - j - k), 0);
                break;
            }
            if (i != view.getScrollY())
            {
                view.smoothScrollBy(0 - view.getScrollX(), i - view.getScrollY());
                return true;
            } else
            {
                return false;
            }
        }

        AccessibilityDelegate()
        {
        }
    }

    public static interface OnScrollChangeListener
    {

        public abstract void onScrollChange$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TRMIP37CLQ2UJJ5EDQ6AP2JCDP6UR3CAPKMATPR954KII99AO______0(NestedScrollView nestedscrollview);
    }

    static final class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public int scrollPosition;

        public final String toString()
        {
            return (new StringBuilder("HorizontalScrollView.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" scrollPosition=").append(scrollPosition).append("}").toString();
        }

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(scrollPosition);
        }


        SavedState(Parcel parcel)
        {
            super(parcel);
            scrollPosition = parcel.readInt();
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }


    private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
    private static final int SCROLLVIEW_STYLEABLE[] = {
        0x101017a
    };
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mLastScrollerY;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    public OnScrollChangeListener mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private SavedState mSavedState;
    private final int mScrollConsumed[];
    private final int mScrollOffset[];
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    public NestedScrollView(Context context)
    {
        this(context, null);
    }

    public NestedScrollView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mTempRect = new Rect();
        mIsLayoutDirty = true;
        mIsLaidOut = false;
        mChildToScrollTo = null;
        mIsBeingDragged = false;
        mSmoothScrollingEnabled = true;
        mActivePointerId = -1;
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(0x40000);
        setWillNotDraw(false);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(getContext());
        mTouchSlop = viewconfiguration.getScaledTouchSlop();
        mMinimumVelocity = viewconfiguration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        context = context.obtainStyledAttributes(attributeset, SCROLLVIEW_STYLEABLE, i, 0);
        boolean flag = context.getBoolean(0, false);
        if (flag != mFillViewport)
        {
            mFillViewport = flag;
            requestLayout();
        }
        context.recycle();
        mParentHelper = new NestedScrollingParentHelper(this);
        mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
    }

    private final boolean arrowScroll(int i)
    {
        View view;
        View view1;
        int j;
        boolean flag;
        flag = false;
        view = findFocus();
        if (view == this)
        {
            view = null;
        }
        view1 = FocusFinder.getInstance().findNextFocus(this, view, i);
        j = (int)(0.5F * (float)getHeight());
        if (view1 == null || !isWithinDeltaOfScreen(view1, j, getHeight())) goto _L2; else goto _L1
_L1:
        view1.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view1, mTempRect);
        j = computeScrollDeltaToGetChildRectOnScreen(mTempRect);
        if (j != 0)
        {
            if (mSmoothScrollingEnabled)
            {
                smoothScrollBy(0, j);
            } else
            {
                scrollBy(0, j);
            }
        }
        view1.requestFocus(i);
_L8:
        if (view != null && view.isFocused())
        {
            i = ((flag) ? 1 : 0);
            if (!isWithinDeltaOfScreen(view, 0, getHeight()))
            {
                i = 1;
            }
            if (i != 0)
            {
                i = getDescendantFocusability();
                setDescendantFocusability(0x20000);
                requestFocus();
                setDescendantFocusability(i);
            }
        }
        return true;
_L2:
        if (i != 33 || getScrollY() >= j) goto _L4; else goto _L3
_L3:
        j = getScrollY();
_L6:
        if (j == 0)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (i == 130 && getChildCount() > 0)
        {
            View view2 = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view2.getLayoutParams();
            int k = view2.getBottom();
            j = Math.min((layoutparams.bottomMargin + k) - ((getScrollY() + getHeight()) - getPaddingBottom()), j);
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (i != 130)
        {
            j = -j;
        }
        if (j != 0)
        {
            if (mSmoothScrollingEnabled)
            {
                smoothScrollBy(0, j);
            } else
            {
                scrollBy(0, j);
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private final int computeScrollDeltaToGetChildRectOnScreen(Rect rect)
    {
        if (getChildCount() == 0)
        {
            return 0;
        }
        int l = getHeight();
        int i = getScrollY();
        int k = i + l;
        int j = getVerticalFadingEdgeLength();
        if (rect.top > 0)
        {
            i += j;
        }
        View view = getChildAt(0);
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
        if (rect.bottom < view.getHeight() + layoutparams.topMargin + layoutparams.bottomMargin)
        {
            j = k - j;
        } else
        {
            j = k;
        }
        if (rect.bottom > j && rect.top > i)
        {
            if (rect.height() > l)
            {
                i = (rect.top - i) + 0;
            } else
            {
                i = (rect.bottom - j) + 0;
            }
            j = view.getBottom();
            i = Math.min(i, (layoutparams.bottomMargin + j) - k);
        } else
        if (rect.top < i && rect.bottom < j)
        {
            if (rect.height() > l)
            {
                i = 0 - (j - rect.bottom);
            } else
            {
                i = 0 - (i - rect.top);
            }
            i = Math.max(i, -getScrollY());
        } else
        {
            i = 0;
        }
        return i;
    }

    private final void endDrag()
    {
        mIsBeingDragged = false;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        mChildHelper.stopNestedScroll(0);
        if (mEdgeGlowTop != null)
        {
            mEdgeGlowTop.onRelease();
            mEdgeGlowBottom.onRelease();
        }
    }

    private final void ensureGlows()
    {
        if (getOverScrollMode() != 2)
        {
            if (mEdgeGlowTop == null)
            {
                Context context = getContext();
                mEdgeGlowTop = new EdgeEffect(context);
                mEdgeGlowBottom = new EdgeEffect(context);
            }
            return;
        } else
        {
            mEdgeGlowTop = null;
            mEdgeGlowBottom = null;
            return;
        }
    }

    private final void flingWithNestedDispatch(int i)
    {
        int j = getScrollY();
        boolean flag;
        if ((j > 0 || i > 0) && (j < getScrollRange() || i < 0))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!dispatchNestedPreFling(0.0F, i))
        {
            dispatchNestedFling(0.0F, i, flag);
            if (getChildCount() > 0)
            {
                mChildHelper.startNestedScroll(2, 1);
                mScroller.fling(getScrollX(), getScrollY(), 0, i, 0, 0, 0x80000000, 0x7fffffff, 0, 0);
                mLastScrollerY = getScrollY();
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    private final boolean fullScroll(int i)
    {
        int j;
        int k;
        if (i == 130)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        k = getHeight();
        mTempRect.top = 0;
        mTempRect.bottom = k;
        if (j != 0)
        {
            j = getChildCount();
            if (j > 0)
            {
                View view = getChildAt(j - 1);
                android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                Rect rect = mTempRect;
                j = view.getBottom();
                rect.bottom = layoutparams.bottomMargin + j + getPaddingBottom();
                mTempRect.top = mTempRect.bottom - k;
            }
        }
        return scrollAndFocus(i, mTempRect.top, mTempRect.bottom);
    }

    private static boolean isViewDescendantOf(View view, View view1)
    {
        if (view == view1)
        {
            return true;
        }
        view = view.getParent();
        return (view instanceof ViewGroup) && isViewDescendantOf((View)view, view1);
    }

    private final boolean isWithinDeltaOfScreen(View view, int i, int j)
    {
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        return mTempRect.bottom + i >= getScrollY() && mTempRect.top - i <= getScrollY() + j;
    }

    private final void onSecondaryPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if (motionevent.getPointerId(i) == mActivePointerId)
        {
            if (i == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            mLastMotionY = (int)motionevent.getY(i);
            mActivePointerId = motionevent.getPointerId(i);
            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
        }
    }

    private final boolean overScrollByCompat$514KIIA9954KIIAQ55D0____0(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        getOverScrollMode();
        computeHorizontalScrollRange();
        computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        computeVerticalScrollExtent();
        j = l + j;
        l = j1 + 0;
        boolean flag;
        boolean flag1;
        if (k > 0)
        {
            i = 0;
            flag = true;
        } else
        if (k < 0)
        {
            i = 0;
            flag = true;
        } else
        {
            flag = false;
            i = k;
        }
        if (j > l)
        {
            flag1 = true;
            j = l;
        } else
        if (j < 0)
        {
            j = 0;
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            if (mChildHelper.getNestedScrollingParentForType(1) != null)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k == 0)
            {
                mScroller.springBack(i, j, 0, 0, 0, getScrollRange());
            }
        }
        onOverScrolled(i, j, flag, flag1);
        return flag || flag1;
    }

    private final boolean scrollAndFocus(int i, int j, int k)
    {
        Object obj;
        boolean flag2;
        int k1;
        int l1;
        int l = getHeight();
        k1 = getScrollY();
        l1 = k1 + l;
        View view;
        java.util.ArrayList arraylist;
        boolean flag;
        int i1;
        int i2;
        if (i == 33)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        arraylist = getFocusables(2);
        view = null;
        flag = false;
        i2 = arraylist.size();
        i1 = 0;
        while (i1 < i2) 
        {
            View view1 = (View)arraylist.get(i1);
            int j1 = view1.getTop();
            int j2 = view1.getBottom();
            if (j < j2 && j1 < k)
            {
                boolean flag1;
                if (j < j1 && j2 < k)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (view == null)
                {
                    view = view1;
                    flag = flag1;
                } else
                {
                    boolean flag3;
                    if (flag2 && j1 < view.getTop() || !flag2 && j2 > view.getBottom())
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                    if (flag)
                    {
                        if (flag1 && flag3)
                        {
                            view = view1;
                        }
                    } else
                    if (flag1)
                    {
                        view = view1;
                        flag = true;
                    } else
                    if (flag3)
                    {
                        view = view1;
                    }
                }
            }
            i1++;
        }
        obj = view;
        if (view == null)
        {
            obj = this;
        }
        if (j < k1 || k > l1) goto _L2; else goto _L1
_L1:
        boolean flag4 = false;
_L4:
        if (obj != findFocus())
        {
            ((View) (obj)).requestFocus(i);
        }
        return flag4;
_L2:
        if (flag2)
        {
            j -= k1;
        } else
        {
            j = k - l1;
        }
        if (j != 0)
        {
            if (mSmoothScrollingEnabled)
            {
                smoothScrollBy(0, j);
                flag4 = true;
                continue; /* Loop/switch isn't completed */
            }
            scrollBy(0, j);
        }
        flag4 = true;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final void scrollToChild(View view)
    {
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        int i = computeScrollDeltaToGetChildRectOnScreen(mTempRect);
        if (i != 0)
        {
            scrollBy(0, i);
        }
    }

    public void addView(View view)
    {
        if (getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view);
            return;
        }
    }

    public void addView(View view, int i)
    {
        if (getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view, i);
            return;
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view, i, layoutparams);
            return;
        }
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (getChildCount() > 0)
        {
            throw new IllegalStateException("ScrollView can host only one direct child");
        } else
        {
            super.addView(view, layoutparams);
            return;
        }
    }

    public void computeScroll()
    {
        if (!mScroller.computeScrollOffset()) goto _L2; else goto _L1
_L1:
        int i;
        int i1;
        mScroller.getCurrX();
        i1 = mScroller.getCurrY();
        int k = i1 - mLastScrollerY;
        int ai[] = mScrollConsumed;
        i = k;
        if (mChildHelper.dispatchNestedPreScroll(0, k, ai, null, 1))
        {
            i = k - mScrollConsumed[1];
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        int l;
        int j1;
        int k1;
        l = getScrollRange();
        j1 = getScrollY();
        overScrollByCompat$514KIIA9954KIIAQ55D0____0(0, i, getScrollX(), j1, 0, l, 0, 0);
        k1 = getScrollY() - j1;
        if (mChildHelper.dispatchNestedScroll(0, k1, 0, i - k1, null, 1)) goto _L4; else goto _L5
_L5:
        int j = getOverScrollMode();
        if (j == 0 || j == 1 && l > 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (!j) goto _L4; else goto _L6
_L6:
        ensureGlows();
        if (i1 > 0 || j1 <= 0) goto _L8; else goto _L7
_L7:
        mEdgeGlowTop.onAbsorb((int)mScroller.getCurrVelocity());
_L4:
        mLastScrollerY = i1;
        ViewCompat.postInvalidateOnAnimation(this);
        return;
_L8:
        if (i1 >= l && j1 < l)
        {
            mEdgeGlowBottom.onAbsorb((int)mScroller.getCurrVelocity());
        }
        if (true) goto _L4; else goto _L2
_L2:
        boolean flag;
        if (mChildHelper.getNestedScrollingParentForType(1) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mChildHelper.stopNestedScroll(1);
        }
        mLastScrollerY = 0;
        return;
    }

    public int computeVerticalScrollOffset()
    {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollRange()
    {
        int j = getChildCount();
        int i = getHeight() - getPaddingBottom() - getPaddingTop();
        if (j != 0)
        {
            View view = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            int k = view.getBottom();
            k = layoutparams.bottomMargin + k;
            int l = getScrollY();
            int i1 = Math.max(0, k - i);
            if (l < 0)
            {
                return k - l;
            }
            i = k;
            if (l > i1)
            {
                return k + (l - i1);
            }
        }
        return i;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return super.dispatchKeyEvent(keyevent) || executeKeyEvent(keyevent);
    }

    public boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        return mChildHelper.dispatchNestedFling(f, f1, flag);
    }

    public boolean dispatchNestedPreFling(float f, float f1)
    {
        return mChildHelper.dispatchNestedPreFling(f, f1);
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[])
    {
        return mChildHelper.dispatchNestedPreScroll(i, j, ai, ai1, 0);
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[])
    {
        return mChildHelper.dispatchNestedScroll(i, j, k, l, ai, 0);
    }

    public void draw(Canvas canvas)
    {
        boolean flag = false;
        super.draw(canvas);
        if (mEdgeGlowTop != null)
        {
            int k1 = getScrollY();
            if (!mEdgeGlowTop.isFinished())
            {
                int l1 = canvas.save();
                int j = getWidth();
                int j1 = getHeight();
                int i1 = Math.min(0, k1);
                int i;
                int k;
                int l;
                if (getClipToPadding())
                {
                    j -= getPaddingLeft() + getPaddingRight();
                    i = getPaddingLeft() + 0;
                } else
                {
                    i = 0;
                }
                l = i1;
                k = j1;
                if (getClipToPadding())
                {
                    k = j1 - (getPaddingTop() + getPaddingBottom());
                    l = i1 + getPaddingTop();
                }
                canvas.translate(i, l);
                mEdgeGlowTop.setSize(j, k);
                if (mEdgeGlowTop.draw(canvas))
                {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount(l1);
            }
            if (!mEdgeGlowBottom.isFinished())
            {
                l1 = canvas.save();
                k = getWidth();
                i1 = getHeight();
                j1 = Math.max(getScrollRange(), k1) + i1;
                i = k;
                j = ((flag) ? 1 : 0);
                if (getClipToPadding())
                {
                    i = k - (getPaddingLeft() + getPaddingRight());
                    j = getPaddingLeft() + 0;
                }
                l = j1;
                k = i1;
                if (getClipToPadding())
                {
                    k = i1 - (getPaddingTop() + getPaddingBottom());
                    l = j1 - getPaddingBottom();
                }
                canvas.translate(j - i, l);
                canvas.rotate(180F, i, 0.0F);
                mEdgeGlowBottom.setSize(i, k);
                if (mEdgeGlowBottom.draw(canvas))
                {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount(l1);
            }
        }
    }

    public final boolean executeKeyEvent(KeyEvent keyevent)
    {
        int j;
        boolean flag1;
        boolean flag2;
        j = 33;
        flag2 = false;
        mTempRect.setEmpty();
        boolean flag;
        if (getChildCount() > 0)
        {
            View view = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            int i = view.getHeight();
            int k = layoutparams1.topMargin;
            if (layoutparams1.bottomMargin + (i + k) > getHeight() - getPaddingTop() - getPaddingBottom())
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        flag1 = flag2;
        if (isFocused())
        {
            flag1 = flag2;
            if (keyevent.getKeyCode() != 4)
            {
                view = findFocus();
                keyevent = view;
                if (view == this)
                {
                    keyevent = null;
                }
                keyevent = FocusFinder.getInstance().findNextFocus(this, keyevent, 130);
                flag1 = flag2;
                if (keyevent != null)
                {
                    flag1 = flag2;
                    if (keyevent != this)
                    {
                        flag1 = flag2;
                        if (keyevent.requestFocus(130))
                        {
                            flag1 = true;
                        }
                    }
                }
            }
        }
_L4:
        return flag1;
_L2:
        flag1 = flag2;
        if (keyevent.getAction() != 0) goto _L4; else goto _L3
_L3:
        int l;
        switch (keyevent.getKeyCode())
        {
        default:
            return false;

        case 19: // '\023'
            if (!keyevent.isAltPressed())
            {
                return arrowScroll(33);
            } else
            {
                return fullScroll(33);
            }

        case 20: // '\024'
            if (!keyevent.isAltPressed())
            {
                return arrowScroll(130);
            } else
            {
                return fullScroll(130);
            }

        case 62: // '>'
            break;
        }
        android.widget.FrameLayout.LayoutParams layoutparams;
        char c;
        if (keyevent.isShiftPressed())
        {
            c = j;
        } else
        {
            c = '\202';
        }
        if (c == 130)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        l = getHeight();
        if (j == 0) goto _L6; else goto _L5
_L5:
        mTempRect.top = getScrollY() + l;
        j = getChildCount();
        if (j > 0)
        {
            keyevent = getChildAt(j - 1);
            layoutparams = (android.widget.FrameLayout.LayoutParams)keyevent.getLayoutParams();
            j = keyevent.getBottom();
            j = layoutparams.bottomMargin + j + getPaddingBottom();
            if (mTempRect.top + l > j)
            {
                mTempRect.top = j - l;
            }
        }
_L8:
        mTempRect.bottom = l + mTempRect.top;
        scrollAndFocus(c, mTempRect.top, mTempRect.bottom);
        return false;
_L6:
        mTempRect.top = getScrollY() - l;
        if (mTempRect.top < 0)
        {
            mTempRect.top = 0;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected float getBottomFadingEdgeStrength()
    {
        if (getChildCount() == 0)
        {
            return 0.0F;
        }
        View view = getChildAt(0);
        android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
        int i = getVerticalFadingEdgeLength();
        int j = getHeight();
        int k = getPaddingBottom();
        int l = view.getBottom();
        j = (layoutparams.bottomMargin + l) - getScrollY() - (j - k);
        if (j < i)
        {
            return (float)j / (float)i;
        } else
        {
            return 1.0F;
        }
    }

    public int getNestedScrollAxes()
    {
        return mParentHelper.mNestedScrollAxes;
    }

    final int getScrollRange()
    {
        if (getChildCount() > 0)
        {
            View view = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            int i = view.getHeight();
            int j = layoutparams.topMargin;
            return Math.max(0, (layoutparams.bottomMargin + (i + j)) - (getHeight() - getPaddingTop() - getPaddingBottom()));
        } else
        {
            return 0;
        }
    }

    protected float getTopFadingEdgeStrength()
    {
        if (getChildCount() == 0)
        {
            return 0.0F;
        }
        int i = getVerticalFadingEdgeLength();
        int j = getScrollY();
        if (j < i)
        {
            return (float)j / (float)i;
        } else
        {
            return 1.0F;
        }
    }

    public boolean hasNestedScrollingParent()
    {
        boolean flag = false;
        if (mChildHelper.getNestedScrollingParentForType(0) != null)
        {
            flag = true;
        }
        return flag;
    }

    public boolean isNestedScrollingEnabled()
    {
        return mChildHelper.mIsNestedScrollingEnabled;
    }

    protected void measureChild(View view, int i, int j)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), layoutparams.width), android.view.View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View view, int i, int j, int k, int l)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
        i = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginlayoutparams.leftMargin + marginlayoutparams.rightMargin + j, marginlayoutparams.width);
        j = marginlayoutparams.topMargin;
        view.measure(i, android.view.View.MeasureSpec.makeMeasureSpec(marginlayoutparams.bottomMargin + j, 0));
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mIsLaidOut = false;
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if ((motionevent.getSource() & 2) == 0) goto _L2; else goto _L1
_L1:
        motionevent.getAction();
        JVM INSTR tableswitch 8 8: default 32
    //                   8 34;
           goto _L2 _L3
_L2:
        return false;
_L3:
        int i;
        int j;
        int k;
        int l;
        float f;
        if (mIsBeingDragged || (f = motionevent.getAxisValue(9)) == 0.0F)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mVerticalScrollFactor == 0.0F)
        {
            motionevent = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(0x101004d, motionevent, true))
            {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            mVerticalScrollFactor = motionevent.getDimension(context.getResources().getDisplayMetrics());
        }
        i = (int)(f * mVerticalScrollFactor);
        j = getScrollRange();
        l = getScrollY();
        k = l - i;
        if (k >= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i = 0;
_L5:
        if (i != l)
        {
            super.scrollTo(getScrollX(), i);
            return true;
        }
        if (true) goto _L2; else goto _L4
_L4:
        i = j;
        if (k <= j)
        {
            i = k;
        }
          goto _L5
        if (true) goto _L2; else goto _L6
_L6:
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag1;
        flag1 = true;
        i = motionevent.getAction();
        if (i == 2 && mIsBeingDragged)
        {
            return true;
        }
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 68
    //                   0 218
    //                   1 423
    //                   2 73
    //                   3 423
    //                   4 68
    //                   5 68
    //                   6 492;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L1 _L5
_L1:
        return mIsBeingDragged;
_L4:
        int j = mActivePointerId;
        if (j != -1)
        {
            int i1 = motionevent.findPointerIndex(j);
            if (i1 == -1)
            {
                Log.e("NestedScrollView", (new StringBuilder("Invalid pointerId=")).append(j).append(" in onInterceptTouchEvent").toString());
            } else
            {
                int k = (int)motionevent.getY(i1);
                if (Math.abs(k - mLastMotionY) > mTouchSlop && (getNestedScrollAxes() & 2) == 0)
                {
                    mIsBeingDragged = true;
                    mLastMotionY = k;
                    if (mVelocityTracker == null)
                    {
                        mVelocityTracker = VelocityTracker.obtain();
                    }
                    mVelocityTracker.addMovement(motionevent);
                    mNestedYOffset = 0;
                    motionevent = getParent();
                    if (motionevent != null)
                    {
                        motionevent.requestDisallowInterceptTouchEvent(true);
                    }
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        int j1 = (int)motionevent.getY();
        int l = (int)motionevent.getX();
        boolean flag;
        if (getChildCount() > 0)
        {
            int k1 = getScrollY();
            View view = getChildAt(0);
            if (j1 >= view.getTop() - k1 && j1 < view.getBottom() - k1 && l >= view.getLeft() && l < view.getRight())
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            mIsBeingDragged = false;
            if (mVelocityTracker != null)
            {
                mVelocityTracker.recycle();
                mVelocityTracker = null;
            }
        } else
        {
            mLastMotionY = j1;
            mActivePointerId = motionevent.getPointerId(0);
            if (mVelocityTracker == null)
            {
                mVelocityTracker = VelocityTracker.obtain();
            } else
            {
                mVelocityTracker.clear();
            }
            mVelocityTracker.addMovement(motionevent);
            mScroller.computeScrollOffset();
            if (mScroller.isFinished())
            {
                flag1 = false;
            }
            mIsBeingDragged = flag1;
            mChildHelper.startNestedScroll(2, 0);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mIsBeingDragged = false;
        mActivePointerId = -1;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        if (mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        mChildHelper.stopNestedScroll(0);
        continue; /* Loop/switch isn't completed */
_L5:
        onSecondaryPointerUp(motionevent);
        if (true) goto _L1; else goto _L6
_L6:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        boolean flag1 = false;
        super.onLayout(flag, i, j, k, l);
        mIsLayoutDirty = false;
        if (mChildToScrollTo != null && isViewDescendantOf(mChildToScrollTo, this))
        {
            scrollToChild(mChildToScrollTo);
        }
        mChildToScrollTo = null;
        if (!mIsLaidOut)
        {
            if (mSavedState != null)
            {
                scrollTo(getScrollX(), mSavedState.scrollPosition);
                mSavedState = null;
            }
            if (getChildCount() > 0)
            {
                View view = getChildAt(0);
                android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                i = view.getMeasuredHeight();
                k = layoutparams.topMargin;
                k = layoutparams.bottomMargin + (i + k);
            } else
            {
                k = 0;
            }
            l = l - j - getPaddingTop() - getPaddingBottom();
            j = getScrollY();
            i = ((flag1) ? 1 : 0);
            if (l < k)
            {
                if (j < 0)
                {
                    i = ((flag1) ? 1 : 0);
                } else
                if (l + j > k)
                {
                    i = k - l;
                } else
                {
                    i = j;
                }
            }
            if (i != j)
            {
                scrollTo(getScrollX(), i);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        mIsLaidOut = true;
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        break MISSING_BLOCK_LABEL_6;
        if (mFillViewport && android.view.View.MeasureSpec.getMode(j) != 0 && getChildCount() > 0)
        {
            View view = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            j = view.getMeasuredHeight();
            int k = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - layoutparams.topMargin - layoutparams.bottomMargin;
            if (j < k)
            {
                view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutparams.leftMargin + layoutparams.rightMargin, layoutparams.width), android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000));
                return;
            }
        }
        return;
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        if (!flag)
        {
            flingWithNestedDispatch((int)f1);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        return dispatchNestedPreFling(f, f1);
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
        onNestedPreScroll(view, i, j, ai, 0);
    }

    public final void onNestedPreScroll(View view, int i, int j, int ai[], int k)
    {
        mChildHelper.dispatchNestedPreScroll(i, j, ai, null, k);
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        onNestedScroll(view, i, j, k, l, 0);
    }

    public final void onNestedScroll(View view, int i, int j, int k, int l, int i1)
    {
        i = getScrollY();
        scrollBy(0, l);
        i = getScrollY() - i;
        mChildHelper.dispatchNestedScroll(0, i, 0, l - i, null, i1);
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        onNestedScrollAccepted(view, view1, i, 0);
    }

    public final void onNestedScrollAccepted(View view, View view1, int i, int j)
    {
        mParentHelper.mNestedScrollAxes = i;
        mChildHelper.startNestedScroll(2, j);
    }

    public void onOverScrolled(int i, int j, boolean flag, boolean flag1)
    {
        super.scrollTo(i, j);
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        boolean flag = true;
        View view;
        int j;
        if (i == 2)
        {
            j = 130;
        } else
        {
            j = i;
            if (i == 1)
            {
                j = 33;
            }
        }
        if (rect == null)
        {
            view = FocusFinder.getInstance().findNextFocus(this, null, j);
        } else
        {
            view = FocusFinder.getInstance().findNextFocusFromRect(this, rect, j);
        }
        if (view != null)
        {
            if (!isWithinDeltaOfScreen(view, 0, getHeight()))
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                return view.requestFocus(j, rect);
            }
        }
        return false;
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            mSavedState = parcelable;
            requestLayout();
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.scrollPosition = getScrollY();
        return savedstate;
    }

    protected void onScrollChanged(int i, int j, int k, int l)
    {
        super.onScrollChanged(i, j, k, l);
        if (mOnScrollChangeListener != null)
        {
            mOnScrollChangeListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TRMIP37CLQ2UJJ5EDQ6AP2JCDP6UR3CAPKMATPR954KII99AO______0(this);
        }
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        View view;
        super.onSizeChanged(i, j, k, l);
        view = findFocus();
        break MISSING_BLOCK_LABEL_15;
        if (view != null && this != view && isWithinDeltaOfScreen(view, 0, l))
        {
            view.getDrawingRect(mTempRect);
            offsetDescendantRectToMyCoords(view, mTempRect);
            i = computeScrollDeltaToGetChildRectOnScreen(mTempRect);
            if (i != 0)
            {
                if (mSmoothScrollingEnabled)
                {
                    smoothScrollBy(0, i);
                    return;
                } else
                {
                    scrollBy(0, i);
                    return;
                }
            }
        }
        return;
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        return (i & 2) != 0;
    }

    public final boolean onStartNestedScroll(View view, View view1, int i, int j)
    {
        return (i & 2) != 0;
    }

    public void onStopNestedScroll(View view)
    {
        mParentHelper.mNestedScrollAxes = 0;
        mChildHelper.stopNestedScroll(0);
    }

    public final void onStopNestedScroll(View view, int i)
    {
        mParentHelper.mNestedScrollAxes = 0;
        mChildHelper.stopNestedScroll(i);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        MotionEvent motionevent1;
        int i;
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        motionevent1 = MotionEvent.obtain(motionevent);
        i = motionevent.getActionMasked();
        if (i == 0)
        {
            mNestedYOffset = 0;
        }
        motionevent1.offsetLocation(0.0F, mNestedYOffset);
        i;
        JVM INSTR tableswitch 0 6: default 88
    //                   0 109
    //                   1 778
    //                   2 212
    //                   3 869
    //                   4 88
    //                   5 924
    //                   6 954;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        if (mVelocityTracker != null)
        {
            mVelocityTracker.addMovement(motionevent1);
        }
        motionevent1.recycle();
        return true;
_L2:
        if (getChildCount() == 0)
        {
            return false;
        }
        boolean flag;
        if (!mScroller.isFinished())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mIsBeingDragged = flag;
        if (flag)
        {
            ViewParent viewparent = getParent();
            if (viewparent != null)
            {
                viewparent.requestDisallowInterceptTouchEvent(true);
            }
        }
        if (!mScroller.isFinished())
        {
            mScroller.abortAnimation();
        }
        mLastMotionY = (int)motionevent.getY();
        mActivePointerId = motionevent.getPointerId(0);
        mChildHelper.startNestedScroll(2, 0);
        continue; /* Loop/switch isn't completed */
_L4:
        int k;
        int j1;
        int k1;
        j1 = motionevent.findPointerIndex(mActivePointerId);
        if (j1 == -1)
        {
            Log.e("NestedScrollView", (new StringBuilder("Invalid pointerId=")).append(mActivePointerId).append(" in onTouchEvent").toString());
            continue; /* Loop/switch isn't completed */
        }
        int l = (int)motionevent.getY(j1);
        i = mLastMotionY - l;
        int ai[] = mScrollConsumed;
        int ai2[] = mScrollOffset;
        k = i;
        if (mChildHelper.dispatchNestedPreScroll(0, i, ai, ai2, 0))
        {
            k = i - mScrollConsumed[1];
            motionevent1.offsetLocation(0.0F, mScrollOffset[1]);
            mNestedYOffset = mNestedYOffset + mScrollOffset[1];
        }
        i = k;
        int l1;
        if (!mIsBeingDragged)
        {
            i = k;
            if (Math.abs(k) > mTouchSlop)
            {
                ViewParent viewparent1 = getParent();
                if (viewparent1 != null)
                {
                    viewparent1.requestDisallowInterceptTouchEvent(true);
                }
                mIsBeingDragged = true;
                int ai1[];
                if (k > 0)
                {
                    i = k - mTouchSlop;
                } else
                {
                    i = k + mTouchSlop;
                }
            }
        }
        if (!mIsBeingDragged)
        {
            continue; /* Loop/switch isn't completed */
        }
        mLastMotionY = l - mScrollOffset[1];
        l1 = getScrollY();
        k1 = getScrollRange();
        k = getOverScrollMode();
        if (k == 0 || k == 1 && k1 > 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (overScrollByCompat$514KIIA9954KIIAQ55D0____0(0, i, 0, getScrollY(), 0, k1, 0, 0))
        {
            int i1;
            if (mChildHelper.getNestedScrollingParentForType(0) != null)
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            if (i1 == 0)
            {
                mVelocityTracker.clear();
            }
        }
        i1 = getScrollY() - l1;
        ai1 = mScrollOffset;
        if (mChildHelper.dispatchNestedScroll(0, i1, 0, i - i1, ai1, 0))
        {
            mLastMotionY = mLastMotionY - mScrollOffset[1];
            motionevent1.offsetLocation(0.0F, mScrollOffset[1]);
            mNestedYOffset = mNestedYOffset + mScrollOffset[1];
            continue; /* Loop/switch isn't completed */
        }
        if (k == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        ensureGlows();
        k = l1 + i;
        if (k >= 0) goto _L9; else goto _L8
_L8:
        mEdgeGlowTop.onPull((float)i / (float)getHeight(), motionevent.getX(j1) / (float)getWidth());
        if (!mEdgeGlowBottom.isFinished())
        {
            mEdgeGlowBottom.onRelease();
        }
_L10:
        if (mEdgeGlowTop != null && (!mEdgeGlowTop.isFinished() || !mEdgeGlowBottom.isFinished()))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (k > k1)
        {
            mEdgeGlowBottom.onPull((float)i / (float)getHeight(), 1.0F - motionevent.getX(j1) / (float)getWidth());
            if (!mEdgeGlowTop.isFinished())
            {
                mEdgeGlowTop.onRelease();
            }
        }
        if (true) goto _L10; else goto _L3
_L3:
        motionevent = mVelocityTracker;
        motionevent.computeCurrentVelocity(1000, mMaximumVelocity);
        i = (int)motionevent.getYVelocity(mActivePointerId);
        if (Math.abs(i) <= mMinimumVelocity) goto _L12; else goto _L11
_L11:
        flingWithNestedDispatch(-i);
_L13:
        mActivePointerId = -1;
        endDrag();
        continue; /* Loop/switch isn't completed */
_L12:
        if (mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (true) goto _L13; else goto _L5
_L5:
        if (mIsBeingDragged && getChildCount() > 0 && mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        mActivePointerId = -1;
        endDrag();
        continue; /* Loop/switch isn't completed */
_L6:
        int j = motionevent.getActionIndex();
        mLastMotionY = (int)motionevent.getY(j);
        mActivePointerId = motionevent.getPointerId(j);
        continue; /* Loop/switch isn't completed */
_L7:
        onSecondaryPointerUp(motionevent);
        mLastMotionY = (int)motionevent.getY(motionevent.findPointerIndex(mActivePointerId));
        if (true) goto _L1; else goto _L14
_L14:
    }

    public void requestChildFocus(View view, View view1)
    {
        if (!mIsLayoutDirty)
        {
            scrollToChild(view1);
        } else
        {
            mChildToScrollTo = view1;
        }
        super.requestChildFocus(view, view1);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        int i;
        boolean flag1;
label0:
        {
            rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
            i = computeScrollDeltaToGetChildRectOnScreen(rect);
            if (i != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                if (!flag)
                {
                    break label0;
                }
                scrollBy(0, i);
            }
            return flag1;
        }
        smoothScrollBy(0, i);
        return flag1;
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        if (flag && mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        super.requestDisallowInterceptTouchEvent(flag);
    }

    public void requestLayout()
    {
        mIsLayoutDirty = true;
        super.requestLayout();
    }

    public void scrollTo(int i, int j)
    {
        if (getChildCount() > 0)
        {
            View view = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            int j1 = getWidth() - getPaddingLeft() - getPaddingRight();
            int k1 = view.getWidth() + layoutparams.leftMargin + layoutparams.rightMargin;
            int l = getHeight() - getPaddingTop() - getPaddingBottom();
            int k = view.getHeight();
            int i1 = layoutparams.topMargin;
            i1 = layoutparams.bottomMargin + (k + i1);
            if (j1 >= k1 || i < 0)
            {
                k = 0;
            } else
            {
                k = i;
                if (j1 + i > k1)
                {
                    k = k1 - j1;
                }
            }
            if (l >= i1 || j < 0)
            {
                i = 0;
            } else
            {
                i = j;
                if (l + j > i1)
                {
                    i = i1 - l;
                }
            }
            if (k != getScrollX() || i != getScrollY())
            {
                super.scrollTo(k, i);
            }
        }
    }

    public void setNestedScrollingEnabled(boolean flag)
    {
        NestedScrollingChildHelper nestedscrollingchildhelper = mChildHelper;
        if (nestedscrollingchildhelper.mIsNestedScrollingEnabled)
        {
            ViewCompat.stopNestedScroll(nestedscrollingchildhelper.mView);
        }
        nestedscrollingchildhelper.mIsNestedScrollingEnabled = flag;
    }

    public boolean shouldDelayChildPressedState()
    {
        return true;
    }

    public final void smoothScrollBy(int i, int j)
    {
        if (getChildCount() == 0)
        {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - mLastScroll > 250L)
        {
            View view = getChildAt(0);
            android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
            int k = view.getHeight();
            int l = layoutparams.topMargin;
            int i1 = layoutparams.bottomMargin;
            int j1 = getHeight();
            int k1 = getPaddingTop();
            int l1 = getPaddingBottom();
            i = getScrollY();
            j = Math.max(0, Math.min(i + j, Math.max(0, (i1 + (k + l)) - (j1 - k1 - l1))));
            mLastScrollerY = getScrollY();
            mScroller.startScroll(getScrollX(), i, 0, j - i);
            ViewCompat.postInvalidateOnAnimation(this);
        } else
        {
            if (!mScroller.isFinished())
            {
                mScroller.abortAnimation();
            }
            scrollBy(i, j);
        }
        mLastScroll = AnimationUtils.currentAnimationTimeMillis();
    }

    public boolean startNestedScroll(int i)
    {
        return mChildHelper.startNestedScroll(i, 0);
    }

    public void stopNestedScroll()
    {
        mChildHelper.stopNestedScroll(0);
    }

}
