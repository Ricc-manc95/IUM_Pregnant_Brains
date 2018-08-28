// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import java.util.List;

// Referenced classes of package android.support.design.appbar:
//            ViewUtilsLollipop, HeaderBehavior, ViewOffsetBehavior, HeaderScrollingViewBehavior

public class AppBarLayout extends LinearLayout
{
    public static class BaseBehavior extends HeaderBehavior
    {

        private WeakReference lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        public int offsetDelta;
        private int offsetToChildIndexOnLayout;
        private boolean offsetToChildIndexOnLayoutIsMinHeight;
        private float offsetToChildIndexOnLayoutPerc;

        private final void snapToChildIfNeeded(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout)
        {
            int i;
            int l;
            int k1;
            k1 = getTopBottomOffsetForScrollingSibling();
            i = 0;
            l = appbarlayout.getChildCount();
_L14:
            if (i >= l) goto _L2; else goto _L1
_L1:
            int j;
            int k;
            coordinatorlayout = appbarlayout.getChildAt(i);
            k = coordinatorlayout.getTop();
            j = coordinatorlayout.getBottom();
            coordinatorlayout = (LayoutParams)coordinatorlayout.getLayoutParams();
            if ((((LayoutParams) (coordinatorlayout)).scrollFlags & 0x20) == 32)
            {
                int l1 = ((LayoutParams) (coordinatorlayout)).topMargin;
                j = ((LayoutParams) (coordinatorlayout)).bottomMargin + j;
                k -= l1;
            }
            if (k > -k1 || j < -k1) goto _L4; else goto _L3
_L3:
            if (i < 0) goto _L6; else goto _L5
_L5:
            LayoutParams layoutparams;
            int i2;
            coordinatorlayout = appbarlayout.getChildAt(i);
            layoutparams = (LayoutParams)coordinatorlayout.getLayoutParams();
            i2 = layoutparams.scrollFlags;
            if ((i2 & 0x11) != 17) goto _L6; else goto _L7
_L7:
            k = -coordinatorlayout.getTop();
            j = -coordinatorlayout.getBottom();
            if (i == appbarlayout.getChildCount() - 1)
            {
                throw new NoSuchMethodError();
            }
              goto _L8
_L4:
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            i = -1;
              goto _L3
_L8:
            if ((i2 & 2) != 2) goto _L10; else goto _L9
_L9:
            j += ViewCompat.getMinimumHeight(coordinatorlayout);
            i = k;
_L12:
            int j1;
            if ((i2 & 0x20) == 32)
            {
                int i1 = layoutparams.topMargin;
                k = j - layoutparams.bottomMargin;
                j = i + i1;
                i = k;
            } else
            {
                k = i;
                i = j;
                j = k;
            }
            if (k1 >= (i + j) / 2);
            throw new NoSuchMethodError();
_L10:
            if ((i2 & 5) == 5)
            {
                j1 = ViewCompat.getMinimumHeight(coordinatorlayout) + j;
                i = j1;
                if (k1 >= j1)
                {
                    j = j1;
                    i = k;
                }
            } else
            {
                i = k;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            return;
            if (true) goto _L12; else goto _L11
_L11:
            if (true) goto _L14; else goto _L13
_L13:
        }

        final boolean canDragView(View view)
        {
label0:
            {
                view = (AppBarLayout)view;
                if (lastNestedScrollingChildRef != null)
                {
                    view = (View)lastNestedScrollingChildRef.get();
                    if (view == null || !view.isShown() || view.canScrollVertically(-1))
                    {
                        break label0;
                    }
                }
                return true;
            }
            return false;
        }

        final int getMaxDragOffset(View view)
        {
            view = (AppBarLayout)view;
            throw new NoSuchMethodError();
        }

        final int getScrollRangeForDragFling(View view)
        {
            view = (AppBarLayout)view;
            throw new NoSuchMethodError();
        }

        final int getTopBottomOffsetForScrollingSibling()
        {
            return getTopAndBottomOffset() + offsetDelta;
        }

        final void onFlingFinished(CoordinatorLayout coordinatorlayout, View view)
        {
            snapToChildIfNeeded(coordinatorlayout, (AppBarLayout)view);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i)
        {
            super.onLayoutChild(coordinatorlayout, appbarlayout, i);
            throw new NoSuchMethodError();
        }

        public final volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return onLayoutChild(coordinatorlayout, (AppBarLayout)view, i);
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, int i, int j, int k, int l)
        {
            if (((android.support.design.widget.CoordinatorLayout.LayoutParams)appbarlayout.getLayoutParams()).height == -2)
            {
                coordinatorlayout.onMeasureChild(appbarlayout, i, j, android.view.View.MeasureSpec.makeMeasureSpec(0, 0), l);
                return true;
            } else
            {
                return super.onMeasureChild(coordinatorlayout, appbarlayout, i, j, k, l);
            }
        }

        public final volatile boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
        {
            return onMeasureChild(coordinatorlayout, (AppBarLayout)view, i, j, k, l);
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, int i, int j, int ai[], int k)
        {
            if (j != 0)
            {
                if (j < 0)
                {
                    throw new NoSuchMethodError();
                } else
                {
                    throw new NoSuchMethodError();
                }
            } else
            {
                return;
            }
        }

        public final volatile void onNestedPreScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int ai[], int k)
        {
            onNestedPreScroll(coordinatorlayout, (AppBarLayout)view, view1, i, j, ai, k);
        }

        public void onNestedScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, int i, int j, int k, int l, 
                int i1)
        {
            if (l < 0)
            {
                throw new NoSuchMethodError();
            } else
            {
                throw new NoSuchMethodError();
            }
        }

        public final volatile void onNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int k, int l, 
                int i1)
        {
            onNestedScroll(coordinatorlayout, (AppBarLayout)view, view1, i, j, k, l, i1);
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, Parcelable parcelable)
        {
            if (parcelable instanceof SavedState)
            {
                parcelable = (SavedState)parcelable;
                super.onRestoreInstanceState(coordinatorlayout, appbarlayout, ((AbsSavedState) (parcelable)).mSuperState);
                offsetToChildIndexOnLayout = ((SavedState) (parcelable)).firstVisibleChildIndex;
                offsetToChildIndexOnLayoutPerc = ((SavedState) (parcelable)).firstVisibleChildPercentageShown;
                offsetToChildIndexOnLayoutIsMinHeight = ((SavedState) (parcelable)).firstVisibleChildAtMinimumHeight;
                return;
            } else
            {
                super.onRestoreInstanceState(coordinatorlayout, appbarlayout, parcelable);
                offsetToChildIndexOnLayout = -1;
                return;
            }
        }

        public final volatile void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, View view, Parcelable parcelable)
        {
            onRestoreInstanceState(coordinatorlayout, (AppBarLayout)view, parcelable);
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout)
        {
            coordinatorlayout = super.onSaveInstanceState(coordinatorlayout, appbarlayout);
            int j = getTopAndBottomOffset();
            int i = 0;
            for (int k = appbarlayout.getChildCount(); i < k; i++)
            {
                View view = appbarlayout.getChildAt(i);
                int l = view.getBottom();
                if (view.getTop() + j <= 0 && l + j >= 0)
                {
                    (new SavedState(coordinatorlayout)).firstVisibleChildIndex = i;
                    ViewCompat.getMinimumHeight(view);
                    throw new NoSuchMethodError();
                }
            }

            return coordinatorlayout;
        }

        public final volatile Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, View view)
        {
            return onSaveInstanceState(coordinatorlayout, (AppBarLayout)view);
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, View view1, int i, int j)
        {
            if ((i & 2) != 0)
            {
                throw new NoSuchMethodError();
            } else
            {
                lastNestedScrollingChildRef = null;
                lastStartedType = j;
                return false;
            }
        }

        public final volatile boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i, int j)
        {
            return onStartNestedScroll(coordinatorlayout, (AppBarLayout)view, view1, view2, i, j);
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, View view, int i)
        {
            if (lastStartedType == 0 || i == 1)
            {
                snapToChildIfNeeded(coordinatorlayout, appbarlayout);
            }
            lastNestedScrollingChildRef = new WeakReference(view);
        }

        public final volatile void onStopNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i)
        {
            onStopNestedScroll(coordinatorlayout, (AppBarLayout)view, view1, i);
        }

        final int setHeaderTopBottomOffset(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k)
        {
            coordinatorlayout = (AppBarLayout)view;
            int l = getTopBottomOffsetForScrollingSibling();
            if (j != 0 && l >= j && l <= k)
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
                if (l != j)
                {
                    throw new NoSuchMethodError();
                }
            } else
            {
                offsetDelta = 0;
            }
            return 0;
        }

        public BaseBehavior()
        {
            offsetToChildIndexOnLayout = -1;
        }

        public BaseBehavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            offsetToChildIndexOnLayout = -1;
        }

        private class LayoutParams extends android.widget.LinearLayout.LayoutParams
        {

            public int scrollFlags;
        }

    }

    public static final class BaseBehavior.SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public boolean firstVisibleChildAtMinimumHeight;
        public int firstVisibleChildIndex;
        public float firstVisibleChildPercentageShown;

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(firstVisibleChildIndex);
            parcel.writeFloat(firstVisibleChildPercentageShown);
            if (firstVisibleChildAtMinimumHeight)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeByte((byte)i);
        }


        public BaseBehavior.SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            firstVisibleChildIndex = parcel.readInt();
            firstVisibleChildPercentageShown = parcel.readFloat();
            boolean flag;
            if (parcel.readByte() != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            firstVisibleChildAtMinimumHeight = flag;
        }

        public BaseBehavior.SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.ClassLoaderCreator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new BaseBehavior.SavedState(parcel, null);
            }

            public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new BaseBehavior.SavedState(parcel, classloader);
            }

            public final Object[] newArray(int i)
            {
                return new BaseBehavior.SavedState[i];
            }

                _cls1()
                {
                }
        }

    }

    public static class Behavior extends BaseBehavior
    {

        public Behavior()
        {
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior
    {

        private static AppBarLayout findFirstDependency(List list)
        {
            int j = list.size();
            for (int i = 0; i < j; i++)
            {
                View view = (View)list.get(i);
                if (view instanceof AppBarLayout)
                {
                    return (AppBarLayout)view;
                }
            }

            return null;
        }

        final volatile View findFirstDependency(List list)
        {
            return findFirstDependency(list);
        }

        final float getOverlapRatioForOffset(View view)
        {
            if (view instanceof AppBarLayout)
            {
                view = (AppBarLayout)view;
                throw new NoSuchMethodError();
            } else
            {
                return 0.0F;
            }
        }

        final int getScrollRange(View view)
        {
            if (view instanceof AppBarLayout)
            {
                throw new NoSuchMethodError();
            } else
            {
                return super.getScrollRange(view);
            }
        }

        public final boolean layoutDependsOn$51662RJ4E9NMIP1FEDQN0S3FE9Q2UP35EDKMERHFETKM8PR5EGNK6RRFE9I6IRJ1EHNN4J31F5NNAT1R9HGMSP3IDTKM8BRMD5INEBQMD5INEEQCC5N68SJFD5I2UTJ9CLRIULJ9CLRJMAAQ0(View view)
        {
            return view instanceof AppBarLayout;
        }

        public final boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            coordinatorlayout = ((android.support.design.widget.CoordinatorLayout.LayoutParams)view1.getLayoutParams()).mBehavior;
            if (!(coordinatorlayout instanceof BaseBehavior)) goto _L2; else goto _L1
_L1:
            int l;
            int i1;
            int j1;
            int k1;
            coordinatorlayout = (BaseBehavior)coordinatorlayout;
            l = view1.getBottom();
            i1 = view.getTop();
            j1 = ((BaseBehavior) (coordinatorlayout)).offsetDelta;
            k1 = super.verticalLayoutGap;
            if (super.overlayTop != 0) goto _L4; else goto _L3
_L3:
            int i = 0;
_L6:
            ViewCompat.offsetTopAndBottom(view, (j1 + (l - i1) + k1) - i);
_L2:
            int j;
            int k;
            if (view1 instanceof AppBarLayout)
            {
                throw new NoSuchMethodError();
            } else
            {
                return false;
            }
_L4:
            j = (int)(getOverlapRatioForOffset(view1) * (float)super.overlayTop);
            k = super.overlayTop;
            if (j < 0)
            {
                i = 0;
            } else
            {
                i = k;
                if (j <= k)
                {
                    i = j;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public volatile boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
        {
            return super.onMeasureChild(coordinatorlayout, view, i, j, k, l);
        }

        public final boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorlayout, View view, Rect rect, boolean flag)
        {
            if (findFirstDependency(coordinatorlayout.getDependencies(view)) != null)
            {
                rect.offset(view.getLeft(), view.getTop());
                view = tempRect1;
                view.set(0, 0, coordinatorlayout.getWidth(), coordinatorlayout.getHeight());
                if (!view.contains(rect))
                {
                    throw new NoSuchMethodError();
                }
            }
            return false;
        }

        public ScrollingViewBehavior()
        {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, R.styleable.ScrollingViewBehavior_Layout);
            super.overlayTop = context.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0);
            context.recycle();
        }
    }


    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new NoSuchMethodError();
    }

    public volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        throw new NoSuchMethodError();
    }

    public volatile android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        throw new NoSuchMethodError();
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        throw new NoSuchMethodError();
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new NoSuchMethodError();
    }

    public volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        throw new NoSuchMethodError();
    }

    public volatile android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new NoSuchMethodError();
    }

    protected int[] onCreateDrawableState(int i)
    {
        throw new NoSuchMethodError();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        throw new NoSuchMethodError();
    }

    protected void onMeasure(int i, int j)
    {
        throw new NoSuchMethodError();
    }

    public void setOrientation(int i)
    {
        if (i != 1)
        {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        } else
        {
            super.setOrientation(i);
            return;
        }
    }

    public void setTargetElevation(float f)
    {
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, f);
    }
}
