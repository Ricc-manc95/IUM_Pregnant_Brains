// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.bottomsheet;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class BottomSheetBehavior extends android.support.design.widget.CoordinatorLayout.Behavior
{
    public static abstract class BottomSheetCallback
    {

        public abstract void onSlide$51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0();

        public abstract void onStateChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR94KLC___0(int i);

        public BottomSheetCallback()
        {
        }
    }

    public static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public final int state;

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(state);
        }


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, int i)
        {
            super(parcelable);
            state = i;
        }

        class _cls1
            implements android.os.Parcelable.ClassLoaderCreator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
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

    final class SettleRunnable
        implements Runnable
    {

        private final int targetState;
        private final BottomSheetBehavior this$0;
        private final View view;

        public final void run()
        {
            if (viewDragHelper != null && viewDragHelper.continueSettling(true))
            {
                ViewCompat.postOnAnimation(view, this);
                return;
            } else
            {
                setStateInternal(targetState);
                return;
            }
        }

        SettleRunnable(View view1, int i)
        {
            this$0 = BottomSheetBehavior.this;
            super();
            view = view1;
            targetState = i;
        }
    }


    public int activePointerId;
    public BottomSheetCallback callback;
    public int collapsedOffset;
    private final android.support.v4.widget.ViewDragHelper.Callback dragCallback;
    public boolean fitToContents;
    public int fitToContentsOffset;
    public int halfExpandedOffset;
    public boolean hideable;
    private boolean ignoreEvents;
    private Map importantForAccessibilityMap;
    private int initialY;
    private int lastNestedScrollDy;
    private int lastPeekHeight;
    private float maximumVelocity;
    private boolean nestedScrolled;
    public WeakReference nestedScrollingChildRef;
    public int parentHeight;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    public boolean skipCollapsed;
    public int state;
    public boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    public ViewDragHelper viewDragHelper;
    public WeakReference viewRef;

    public BottomSheetBehavior()
    {
        fitToContents = true;
        state = 4;
        dragCallback = new _cls2();
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        fitToContents = true;
        state = 4;
        dragCallback = new _cls2();
        attributeset = context.obtainStyledAttributes(attributeset, R.styleable.BottomSheetBehavior_Layout);
        TypedValue typedvalue = attributeset.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        boolean flag;
        if (typedvalue != null && typedvalue.data == -1)
        {
            setPeekHeight(typedvalue.data);
        } else
        {
            setPeekHeight(attributeset.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        }
        hideable = attributeset.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false);
        flag = attributeset.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true);
        if (fitToContents != flag)
        {
            fitToContents = flag;
            if (viewRef != null)
            {
                calculateCollapsedOffset();
            }
            int i;
            if (fitToContents && state == 6)
            {
                i = 3;
            } else
            {
                i = state;
            }
            setStateInternal(i);
        }
        skipCollapsed = attributeset.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false);
        attributeset.recycle();
        maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private final void calculateCollapsedOffset()
    {
        if (fitToContents)
        {
            collapsedOffset = Math.max(parentHeight - lastPeekHeight, fitToContentsOffset);
            return;
        } else
        {
            collapsedOffset = parentHeight - lastPeekHeight;
            return;
        }
    }

    private final View findScrollingChild(View view)
    {
        if (ViewCompat.isNestedScrollingEnabled(view))
        {
            return view;
        }
        if (view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            int j = view.getChildCount();
            for (int i = 0; i < j; i++)
            {
                View view1 = findScrollingChild(view.getChildAt(i));
                if (view1 != null)
                {
                    return view1;
                }
            }

        }
        return null;
    }

    public static BottomSheetBehavior from(View view)
    {
        view = view.getLayoutParams();
        if (!(view instanceof android.support.design.widget.CoordinatorLayout.LayoutParams))
        {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        view = ((android.support.design.widget.CoordinatorLayout.LayoutParams)view).mBehavior;
        if (!(view instanceof BottomSheetBehavior))
        {
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        } else
        {
            return (BottomSheetBehavior)view;
        }
    }

    private final void updateImportantForAccessibility(boolean flag)
    {
        if (viewRef != null) goto _L2; else goto _L1
_L1:
        Object obj;
        return;
_L2:
        int j;
        if (!((obj = ((View)viewRef.get()).getParent()) instanceof CoordinatorLayout))
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (CoordinatorLayout)obj;
        j = ((CoordinatorLayout) (obj)).getChildCount();
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (importantForAccessibilityMap != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        importantForAccessibilityMap = new HashMap(j);
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        int i = 0;
        while (i < j) 
        {
            View view = ((CoordinatorLayout) (obj)).getChildAt(i);
            if (view != viewRef.get())
            {
                if (!flag)
                {
                    if (importantForAccessibilityMap != null && importantForAccessibilityMap.containsKey(view))
                    {
                        ViewCompat.setImportantForAccessibility(view, ((Integer)importantForAccessibilityMap.get(view)).intValue());
                    }
                } else
                {
                    if (android.os.Build.VERSION.SDK_INT >= 16)
                    {
                        importantForAccessibilityMap.put(view, Integer.valueOf(view.getImportantForAccessibility()));
                    }
                    ViewCompat.setImportantForAccessibility(view, 4);
                }
            }
            i++;
        }
        if (!flag)
        {
            importantForAccessibilityMap = null;
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    final void dispatchOnSlide(int i)
    {
        if ((View)viewRef.get() != null && callback != null)
        {
            i = collapsedOffset;
            callback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR8OKLC___0();
        }
    }

    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (view.isShown()) goto _L2; else goto _L1
_L1:
        ignoreEvents = true;
_L8:
        return false;
_L2:
        int i;
        i = motionevent.getActionMasked();
        if (i == 0)
        {
            activePointerId = -1;
            if (velocityTracker != null)
            {
                velocityTracker.recycle();
                velocityTracker = null;
            }
        }
        if (velocityTracker == null)
        {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(motionevent);
        i;
        JVM INSTR tableswitch 0 3: default 104
    //                   0 155
    //                   1 131
    //                   2 104
    //                   3 131;
           goto _L3 _L4 _L5 _L3 _L5
_L3:
        if (!ignoreEvents && viewDragHelper != null && viewDragHelper.shouldInterceptTouchEvent(motionevent))
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L5:
        touchingScrollingChild = false;
        activePointerId = -1;
        if (ignoreEvents)
        {
            ignoreEvents = false;
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = (int)motionevent.getX();
        initialY = (int)motionevent.getY();
        View view1;
        boolean flag;
        if (nestedScrollingChildRef != null)
        {
            view1 = (View)nestedScrollingChildRef.get();
        } else
        {
            view1 = null;
        }
        if (view1 != null && coordinatorlayout.isPointInChildBounds(view1, j, initialY))
        {
            activePointerId = motionevent.getPointerId(motionevent.getActionIndex());
            touchingScrollingChild = true;
        }
        if (activePointerId == -1 && !coordinatorlayout.isPointInChildBounds(view, j, initialY))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ignoreEvents = flag;
        if (true) goto _L3; else goto _L6
_L6:
        if (nestedScrollingChildRef != null)
        {
            view = (View)nestedScrollingChildRef.get();
        } else
        {
            view = null;
        }
        if (i == 2 && view != null && !ignoreEvents && state != 1 && !coordinatorlayout.isPointInChildBounds(view, (int)motionevent.getX(), (int)motionevent.getY()) && viewDragHelper != null && Math.abs((float)initialY - motionevent.getY()) > (float)viewDragHelper.mTouchSlop)
        {
            return true;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
    {
        int j;
        boolean flag = false;
        if (ViewCompat.getFitsSystemWindows(coordinatorlayout) && !ViewCompat.getFitsSystemWindows(view))
        {
            view.setFitsSystemWindows(true);
        }
        j = view.getTop();
        coordinatorlayout.onLayoutChild(view, i);
        parentHeight = coordinatorlayout.getHeight();
        android.support.v4.widget.ViewDragHelper.Callback callback1;
        if (peekHeightAuto)
        {
            if (peekHeightMin == 0)
            {
                peekHeightMin = coordinatorlayout.getResources().getDimensionPixelSize(0x7f0e00f2);
            }
            lastPeekHeight = Math.max(peekHeightMin, parentHeight - (coordinatorlayout.getWidth() * 9) / 16);
        } else
        {
            lastPeekHeight = peekHeight;
        }
        fitToContentsOffset = Math.max(0, parentHeight - view.getHeight());
        halfExpandedOffset = parentHeight / 2;
        calculateCollapsedOffset();
        if (state != 3) goto _L2; else goto _L1
_L1:
        i = ((flag) ? 1 : 0);
        if (fitToContents)
        {
            i = fitToContentsOffset;
        }
        ViewCompat.offsetTopAndBottom(view, i);
_L4:
        if (viewDragHelper == null)
        {
            callback1 = dragCallback;
            viewDragHelper = new ViewDragHelper(coordinatorlayout.getContext(), coordinatorlayout, callback1);
        }
        viewRef = new WeakReference(view);
        nestedScrollingChildRef = new WeakReference(findScrollingChild(view));
        return true;
_L2:
        if (state == 6)
        {
            ViewCompat.offsetTopAndBottom(view, halfExpandedOffset);
        } else
        if (hideable && state == 5)
        {
            ViewCompat.offsetTopAndBottom(view, parentHeight);
        } else
        if (state == 4)
        {
            ViewCompat.offsetTopAndBottom(view, collapsedOffset);
        } else
        if (state == 1 || state == 2)
        {
            ViewCompat.offsetTopAndBottom(view, j - view.getTop());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final boolean onNestedPreFling(CoordinatorLayout coordinatorlayout, View view, View view1, float f, float f1)
    {
        return view1 == nestedScrollingChildRef.get() && (state != 3 || super.onNestedPreFling(coordinatorlayout, view, view1, f, f1));
    }

    public final void onNestedPreScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int ai[], int k)
    {
        int l;
        while (k == 1 || view1 != (View)nestedScrollingChildRef.get()) 
        {
            return;
        }
        k = view.getTop();
        l = k - j;
        if (j <= 0) goto _L2; else goto _L1
_L1:
        if (fitToContents)
        {
            i = fitToContentsOffset;
        } else
        {
            i = 0;
        }
        if (l < i)
        {
            if (fitToContents)
            {
                i = fitToContentsOffset;
            } else
            {
                i = 0;
            }
            ai[1] = k - i;
            ViewCompat.offsetTopAndBottom(view, -ai[1]);
            setStateInternal(3);
        } else
        {
            ai[1] = j;
            ViewCompat.offsetTopAndBottom(view, -j);
            setStateInternal(1);
        }
_L4:
        dispatchOnSlide(view.getTop());
        lastNestedScrollDy = j;
        nestedScrolled = true;
        return;
_L2:
        if (j < 0 && !view1.canScrollVertically(-1))
        {
            if (l <= collapsedOffset || hideable)
            {
                ai[1] = j;
                ViewCompat.offsetTopAndBottom(view, -j);
                setStateInternal(1);
            } else
            {
                ai[1] = k - collapsedOffset;
                ViewCompat.offsetTopAndBottom(view, -ai[1]);
                setStateInternal(4);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, View view, Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(coordinatorlayout, view, ((AbsSavedState) (parcelable)).mSuperState);
        if (((SavedState) (parcelable)).state == 1 || ((SavedState) (parcelable)).state == 2)
        {
            state = 4;
            return;
        } else
        {
            state = ((SavedState) (parcelable)).state;
            return;
        }
    }

    public final Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, View view)
    {
        return new SavedState(super.onSaveInstanceState(coordinatorlayout, view), state);
    }

    public final boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i, int j)
    {
        boolean flag = false;
        lastNestedScrollDy = 0;
        nestedScrolled = false;
        if ((i & 2) != 0)
        {
            flag = true;
        }
        return flag;
    }

    public final void onStopNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i)
    {
        int j = view.getTop();
        if (fitToContents)
        {
            i = fitToContentsOffset;
        } else
        {
            i = 0;
        }
        if (j != i) goto _L2; else goto _L1
_L1:
        setStateInternal(3);
_L4:
        return;
_L2:
        if (view1 != nestedScrollingChildRef.get() || !nestedScrolled) goto _L4; else goto _L3
_L3:
        int k;
        if (lastNestedScrollDy > 0)
        {
            byte byte0;
            if (fitToContents)
            {
                i = fitToContentsOffset;
            } else
            {
                i = 0;
            }
            byte0 = 3;
            k = i;
            i = byte0;
        } else
        {
label0:
            {
                if (!hideable)
                {
                    break label0;
                }
                float f;
                if (velocityTracker == null)
                {
                    f = 0.0F;
                } else
                {
                    velocityTracker.computeCurrentVelocity(1000, maximumVelocity);
                    f = velocityTracker.getYVelocity(activePointerId);
                }
                if (!shouldHide(view, f))
                {
                    break label0;
                }
                k = parentHeight;
                i = 5;
            }
        }
_L5:
        if (viewDragHelper.smoothSlideViewTo(view, view.getLeft(), k))
        {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, i));
        } else
        {
            setStateInternal(i);
        }
        nestedScrolled = false;
        return;
        if (lastNestedScrollDy == 0)
        {
            i = view.getTop();
            if (fitToContents)
            {
                if (Math.abs(i - fitToContentsOffset) < Math.abs(i - collapsedOffset))
                {
                    k = fitToContentsOffset;
                    i = 3;
                } else
                {
                    k = collapsedOffset;
                    i = 4;
                }
            } else
            if (i < halfExpandedOffset)
            {
                if (i < Math.abs(i - collapsedOffset))
                {
                    i = 3;
                    k = 0;
                } else
                {
                    k = halfExpandedOffset;
                    i = 6;
                }
            } else
            if (Math.abs(i - halfExpandedOffset) < Math.abs(i - collapsedOffset))
            {
                k = halfExpandedOffset;
                i = 6;
            } else
            {
                k = collapsedOffset;
                i = 4;
            }
        } else
        {
            k = collapsedOffset;
            i = 4;
        }
          goto _L5
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
    {
        if (view.isShown())
        {
            int i = motionevent.getActionMasked();
            if (state == 1 && i == 0)
            {
                return true;
            }
            if (viewDragHelper != null)
            {
                viewDragHelper.processTouchEvent(motionevent);
            }
            if (i == 0)
            {
                activePointerId = -1;
                if (velocityTracker != null)
                {
                    velocityTracker.recycle();
                    velocityTracker = null;
                }
            }
            if (velocityTracker == null)
            {
                velocityTracker = VelocityTracker.obtain();
            }
            velocityTracker.addMovement(motionevent);
            if (i == 2 && !ignoreEvents && Math.abs((float)initialY - motionevent.getY()) > (float)viewDragHelper.mTouchSlop)
            {
                viewDragHelper.captureChildView(view, motionevent.getPointerId(motionevent.getActionIndex()));
            }
            if (!ignoreEvents)
            {
                return true;
            }
        }
        return false;
    }

    public final void setPeekHeight(int i)
    {
        boolean flag = true;
        if (i != -1) goto _L2; else goto _L1
_L1:
        if (peekHeightAuto) goto _L4; else goto _L3
_L3:
        peekHeightAuto = true;
        i = ((flag) ? 1 : 0);
_L6:
        if (i != 0 && state == 4 && viewRef != null)
        {
            View view = (View)viewRef.get();
            if (view != null)
            {
                view.requestLayout();
            }
        }
        return;
_L2:
        if (peekHeightAuto || peekHeight != i)
        {
            peekHeightAuto = false;
            peekHeight = Math.max(0, i);
            collapsedOffset = parentHeight - i;
            i = ((flag) ? 1 : 0);
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void setState(final int finalState)
    {
        if (finalState != state) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (viewRef != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (finalState == 4 || finalState == 3 || finalState == 6 || hideable && finalState == 5)
        {
            state = finalState;
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        final View child = (View)viewRef.get();
        if (child != null)
        {
            ViewParent viewparent = child.getParent();
            if (viewparent != null && viewparent.isLayoutRequested() && ViewCompat.isAttachedToWindow(child))
            {
                child.post(new _cls1());
                return;
            } else
            {
                startSettlingAnimation(child, finalState);
                return;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    final void setStateInternal(int i)
    {
        if (state != i)
        {
            state = i;
            if (i == 6 || i == 3)
            {
                updateImportantForAccessibility(true);
            } else
            if (i == 5 || i == 4)
            {
                updateImportantForAccessibility(false);
            }
            if ((View)viewRef.get() != null && callback != null)
            {
                callback._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR94KLC___0(i);
                return;
            }
        }
    }

    final boolean shouldHide(View view, float f)
    {
        if (!skipCollapsed)
        {
            if (view.getTop() < collapsedOffset)
            {
                return false;
            }
            if (Math.abs(((float)view.getTop() + 0.1F * f) - (float)collapsedOffset) / (float)peekHeight <= 0.5F)
            {
                return false;
            }
        }
        return true;
    }

    final void startSettlingAnimation(View view, int i)
    {
        int j;
        int k;
        if (i == 4)
        {
            j = collapsedOffset;
            k = i;
        } else
        if (i == 6)
        {
            int l = halfExpandedOffset;
            j = l;
            k = i;
            if (fitToContents)
            {
                j = l;
                k = i;
                if (l <= fitToContentsOffset)
                {
                    j = fitToContentsOffset;
                    k = 3;
                }
            }
        } else
        if (i == 3)
        {
            if (fitToContents)
            {
                j = fitToContentsOffset;
                k = i;
            } else
            {
                j = 0;
                k = i;
            }
        } else
        if (hideable && i == 5)
        {
            j = parentHeight;
            k = i;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder(35)).append("Illegal state argument: ").append(i).toString());
        }
        if (viewDragHelper.smoothSlideViewTo(view, view.getLeft(), j))
        {
            setStateInternal(2);
            ViewCompat.postOnAnimation(view, new SettleRunnable(view, k));
            return;
        } else
        {
            setStateInternal(k);
            return;
        }
    }

    private class _cls2 extends android.support.v4.widget.ViewDragHelper.Callback
    {

        private final BottomSheetBehavior this$0;

        public final int clampViewPositionHorizontal$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            return view.getLeft();
        }

        public final int clampViewPositionVertical$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954III8_0(View view, int i)
        {
            view = BottomSheetBehavior.this;
            int j;
            int k;
            if (((BottomSheetBehavior) (view)).fitToContents)
            {
                j = ((BottomSheetBehavior) (view)).fitToContentsOffset;
            } else
            {
                j = 0;
            }
            if (hideable)
            {
                k = parentHeight;
            } else
            {
                k = collapsedOffset;
            }
            if (i < j)
            {
                return j;
            }
            if (i > k)
            {
                return k;
            } else
            {
                return i;
            }
        }

        public final int getViewVerticalDragRange$51662RJ4E9NMIP1FEPKMATPFAPKMATPR554G____0()
        {
            if (hideable)
            {
                return parentHeight;
            } else
            {
                return collapsedOffset;
            }
        }

        public final void onViewDragStateChanged(int i)
        {
            if (i == 1)
            {
                setStateInternal(1);
            }
        }

        public final void onViewPositionChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i, int j)
        {
            dispatchOnSlide(j);
        }

        public final void onViewReleased(View view, float f, float f1)
        {
            byte byte0 = 3;
            int i;
            if (f1 < 0.0F)
            {
                if (fitToContents)
                {
                    i = fitToContentsOffset;
                } else
                if (view.getTop() > halfExpandedOffset)
                {
                    i = halfExpandedOffset;
                    byte0 = 6;
                } else
                {
                    i = 0;
                }
            } else
            if (hideable && shouldHide(view, f1) && (view.getTop() > collapsedOffset || Math.abs(f) < Math.abs(f1)))
            {
                i = parentHeight;
                byte0 = 5;
            } else
            if (f1 == 0.0F || Math.abs(f) > Math.abs(f1))
            {
                i = view.getTop();
                if (fitToContents)
                {
                    if (Math.abs(i - fitToContentsOffset) < Math.abs(i - collapsedOffset))
                    {
                        i = fitToContentsOffset;
                    } else
                    {
                        i = collapsedOffset;
                        byte0 = 4;
                    }
                } else
                if (i < halfExpandedOffset)
                {
                    if (i < Math.abs(i - collapsedOffset))
                    {
                        i = 0;
                    } else
                    {
                        i = halfExpandedOffset;
                        byte0 = 6;
                    }
                } else
                if (Math.abs(i - halfExpandedOffset) < Math.abs(i - collapsedOffset))
                {
                    i = halfExpandedOffset;
                    byte0 = 6;
                } else
                {
                    i = collapsedOffset;
                    byte0 = 4;
                }
            } else
            {
                i = collapsedOffset;
                byte0 = 4;
            }
            if (viewDragHelper.settleCapturedViewAt(view.getLeft(), i))
            {
                setStateInternal(2);
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, byte0));
                return;
            } else
            {
                setStateInternal(byte0);
                return;
            }
        }

        public final boolean tryCaptureView(View view, int i)
        {
            if (state == 1)
            {
                return false;
            }
            if (touchingScrollingChild)
            {
                return false;
            }
            if (state == 3 && activePointerId == i)
            {
                View view1 = (View)nestedScrollingChildRef.get();
                if (view1 != null && view1.canScrollVertically(-1))
                {
                    return false;
                }
            }
            return viewRef != null && viewRef.get() == view;
        }

        _cls2()
        {
            this$0 = BottomSheetBehavior.this;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final BottomSheetBehavior this$0;
        private final View val$child;
        private final int val$finalState;

        public final void run()
        {
            startSettlingAnimation(child, finalState);
        }

        _cls1()
        {
            this$0 = BottomSheetBehavior.this;
            child = view;
            finalState = i;
            super();
        }
    }

}
