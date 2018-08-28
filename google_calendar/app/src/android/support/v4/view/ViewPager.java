// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package android.support.v4.view:
//            PagerAdapter, ViewCompat, AbsSavedState, AccessibilityDelegateCompat

public class ViewPager extends ViewGroup
{
    public static interface DecorView
        extends Annotation
    {
    }

    public static final class ItemInfo
    {

        public Object object;
        public float offset;
        public int position;
        public boolean scrolling;
        public float widthFactor;

        ItemInfo()
        {
        }
    }

    public static final class LayoutParams extends android.view.ViewGroup.LayoutParams
    {

        public int childIndex;
        public int gravity;
        public boolean isDecor;
        public boolean needsMeasure;
        public int position;
        public float widthFactor;

        public LayoutParams()
        {
            super(-1, -1);
            widthFactor = 0.0F;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            widthFactor = 0.0F;
            context = context.obtainStyledAttributes(attributeset, ViewPager.LAYOUT_ATTRS);
            gravity = context.getInteger(0, 48);
            context.recycle();
        }
    }

    final class MyAccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private final ViewPager this$0;

        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            boolean flag = true;
            super.onInitializeAccessibilityEvent(view, accessibilityevent);
            accessibilityevent.setClassName(android/support/v4/view/ViewPager.getName());
            if (mAdapter == null || mAdapter.getCount() <= 1)
            {
                flag = false;
            }
            accessibilityevent.setScrollable(flag);
            if (accessibilityevent.getEventType() == 4096 && mAdapter != null)
            {
                accessibilityevent.setItemCount(mAdapter.getCount());
                accessibilityevent.setFromIndex(mCurItem);
                accessibilityevent.setToIndex(mCurItem);
            }
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            view = android/support/v4/view/ViewPager.getName();
            accessibilitynodeinfocompat.mInfo.setClassName(view);
            boolean flag;
            if (mAdapter != null && mAdapter.getCount() > 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            accessibilitynodeinfocompat.mInfo.setScrollable(flag);
            if (canScrollHorizontally(1))
            {
                accessibilitynodeinfocompat.mInfo.addAction(4096);
            }
            if (canScrollHorizontally(-1))
            {
                accessibilitynodeinfocompat.mInfo.addAction(8192);
            }
        }

        public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            if (super.performAccessibilityAction(view, i, bundle))
            {
                return true;
            }
            switch (i)
            {
            default:
                return false;

            case 4096: 
                if (canScrollHorizontally(1))
                {
                    setCurrentItem(mCurItem + 1);
                    return true;
                } else
                {
                    return false;
                }

            case 8192: 
                break;
            }
            if (canScrollHorizontally(-1))
            {
                setCurrentItem(mCurItem - 1);
                return true;
            } else
            {
                return false;
            }
        }

        MyAccessibilityDelegate()
        {
            this$0 = ViewPager.this;
            super();
        }
    }

    public static interface OnPageChangeListener
    {

        public abstract void onPageScrollStateChanged(int i);

        public abstract void onPageScrolled(int i, float f, int j);

        public abstract void onPageSelected(int i);
    }

    final class PagerObserver extends DataSetObserver
    {

        private final ViewPager this$0;

        public final void onChanged()
        {
            dataSetChanged();
        }

        public final void onInvalidated()
        {
            dataSetChanged();
        }

        PagerObserver()
        {
            this$0 = ViewPager.this;
            super();
        }
    }

    public static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public Parcelable adapterState;
        public ClassLoader loader;
        public int position;

        public final String toString()
        {
            return (new StringBuilder("FragmentPager.SavedState{")).append(Integer.toHexString(System.identityHashCode(this))).append(" position=").append(position).append("}").toString();
        }

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(position);
            parcel.writeParcelable(adapterState, i);
        }


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            ClassLoader classloader1 = classloader;
            if (classloader == null)
            {
                classloader1 = getClass().getClassLoader();
            }
            position = parcel.readInt();
            adapterState = parcel.readParcelable(classloader1);
            loader = classloader1;
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
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

    public static class SimpleOnPageChangeListener
        implements OnPageChangeListener
    {

        public void onPageScrollStateChanged(int i)
        {
        }

        public void onPageScrolled(int i, float f, int j)
        {
        }

        public void onPageSelected(int i)
        {
        }

        public SimpleOnPageChangeListener()
        {
        }
    }

    static final class ViewPositionComparator
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (View)obj;
            obj1 = (View)obj1;
            obj = (LayoutParams)((View) (obj)).getLayoutParams();
            obj1 = (LayoutParams)((View) (obj1)).getLayoutParams();
            if (((LayoutParams) (obj)).isDecor != ((LayoutParams) (obj1)).isDecor)
            {
                return !((LayoutParams) (obj)).isDecor ? -1 : 1;
            } else
            {
                return ((LayoutParams) (obj)).position - ((LayoutParams) (obj1)).position;
            }
        }

        ViewPositionComparator()
        {
        }
    }


    private static final Comparator COMPARATOR = new _cls1();
    public static final int LAYOUT_ATTRS[] = {
        0x10100b3
    };
    private static final Interpolator sInterpolator = new _cls2();
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    public int mActivePointerId;
    public PagerAdapter mAdapter;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mCloseEnough;
    public int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    public long mFakeDragBeginTime;
    public boolean mFakeDragging;
    private boolean mFirstLayout;
    public float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    public float mInitialMotionX;
    private float mInitialMotionY;
    public boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    public boolean mIsUnableToDrag;
    public final ArrayList mItems;
    public float mLastMotionX;
    private float mLastMotionY;
    public float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    public int mMaximumVelocity;
    private int mMinimumVelocity;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private List mOnPageChangeListeners;
    private int mPageMargin;
    public boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    public VelocityTracker mVelocityTracker;

    public ViewPager(Context context)
    {
        super(context);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mFirstOffset = -3.402823E+38F;
        mLastOffset = 3.402823E+38F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mEndScrollRunnable = new _cls3();
        mScrollState = 0;
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mItems = new ArrayList();
        mTempItem = new ItemInfo();
        mTempRect = new Rect();
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        mFirstOffset = -3.402823E+38F;
        mLastOffset = 3.402823E+38F;
        mOffscreenPageLimit = 1;
        mActivePointerId = -1;
        mFirstLayout = true;
        mEndScrollRunnable = new _cls3();
        mScrollState = 0;
        initViewPager();
    }

    private final ItemInfo addNewItem(int i, int j)
    {
        ItemInfo iteminfo = new ItemInfo();
        iteminfo.position = i;
        iteminfo.object = mAdapter.instantiateItem(this, i);
        iteminfo.widthFactor = mAdapter.getPageWidth(i);
        if (j < 0 || j >= mItems.size())
        {
            mItems.add(iteminfo);
            return iteminfo;
        } else
        {
            mItems.add(j, iteminfo);
            return iteminfo;
        }
    }

    private final boolean arrowScroll(int i)
    {
        View view;
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = true;
        view = findFocus();
        if (view != this) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L9:
        int j;
        view = FocusFinder.getInstance().findNextFocus(this, ((View) (obj)), i);
        if (view != null && view != obj)
        {
            if (i == 17)
            {
                j = getChildRectInPagerCoordinates(mTempRect, view).left;
                int k = getChildRectInPagerCoordinates(mTempRect, ((View) (obj))).left;
                StringBuilder stringbuilder;
                if (obj != null && j >= k)
                {
                    if (mCurItem > 0)
                    {
                        setCurrentItem(mCurItem - 1, true);
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                } else
                {
                    flag = view.requestFocus();
                }
            } else
            if (i == 66)
            {
                j = getChildRectInPagerCoordinates(mTempRect, view).left;
                int l = getChildRectInPagerCoordinates(mTempRect, ((View) (obj))).left;
                if (obj != null && j <= l)
                {
                    flag = pageRight();
                } else
                {
                    flag = view.requestFocus();
                }
            }
        } else
        if (i == 17 || i == 1)
        {
            if (mCurItem > 0)
            {
                setCurrentItem(mCurItem - 1, true);
                flag = flag1;
            } else
            {
                flag = false;
            }
        } else
        if (i == 66 || i == 2)
        {
            flag = pageRight();
        }
        if (flag)
        {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return flag;
_L2:
        if (view == null) goto _L4; else goto _L3
_L3:
        obj = view.getParent();
_L8:
        if (!(obj instanceof ViewGroup))
        {
            break MISSING_BLOCK_LABEL_399;
        }
        if (obj != this) goto _L6; else goto _L5
_L5:
        j = 1;
_L10:
        if (j != 0) goto _L4; else goto _L7
_L6:
        obj = ((ViewParent) (obj)).getParent();
          goto _L8
_L7:
        stringbuilder = new StringBuilder();
        stringbuilder.append(view.getClass().getSimpleName());
        for (obj = view.getParent(); obj instanceof ViewGroup; obj = ((ViewParent) (obj)).getParent())
        {
            stringbuilder.append(" => ").append(obj.getClass().getSimpleName());
        }

        Log.e("ViewPager", (new StringBuilder("arrowScroll tried to find focus based on non-child current focused view ")).append(stringbuilder.toString()).toString());
        obj = null;
          goto _L9
_L4:
        obj = view;
          goto _L9
        j = 0;
          goto _L10
    }

    private final boolean canScroll(View view, boolean flag, int i, int j, int k)
    {
        if (!(view instanceof ViewGroup)) goto _L2; else goto _L1
_L1:
        ViewGroup viewgroup;
        int l;
        int i1;
        int j1;
        viewgroup = (ViewGroup)view;
        i1 = view.getScrollX();
        j1 = view.getScrollY();
        l = viewgroup.getChildCount() - 1;
_L8:
        if (l < 0) goto _L2; else goto _L3
_L3:
        View view1 = viewgroup.getChildAt(l);
        if (j + i1 < view1.getLeft() || j + i1 >= view1.getRight() || k + j1 < view1.getTop() || k + j1 >= view1.getBottom() || !canScroll(view1, true, i, (j + i1) - view1.getLeft(), (k + j1) - view1.getTop())) goto _L5; else goto _L4
_L4:
        return true;
_L5:
        l--;
        continue; /* Loop/switch isn't completed */
_L2:
        if (flag && view.canScrollHorizontally(-i)) goto _L4; else goto _L6
_L6:
        return false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private final void completeScroll(boolean flag)
    {
label0:
        {
            int i;
            int j;
            if (mScrollState == 2)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                if (mScrollingCacheEnabled)
                {
                    mScrollingCacheEnabled = false;
                }
                ItemInfo iteminfo;
                boolean flag1;
                if (!mScroller.isFinished())
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    mScroller.abortAnimation();
                    j = getScrollX();
                    int k = getScrollY();
                    int l = mScroller.getCurrX();
                    int i1 = mScroller.getCurrY();
                    if (j != l || k != i1)
                    {
                        scrollTo(l, i1);
                        if (l != j)
                        {
                            pageScrolled(l);
                        }
                    }
                }
            }
            mPopulatePending = false;
            flag1 = false;
            j = i;
            for (i = ((flag1) ? 1 : 0); i < mItems.size(); i++)
            {
                iteminfo = (ItemInfo)mItems.get(i);
                if (iteminfo.scrolling)
                {
                    iteminfo.scrolling = false;
                    j = 1;
                }
            }

            if (j != 0)
            {
                if (!flag)
                {
                    break label0;
                }
                ViewCompat.postOnAnimation(this, mEndScrollRunnable);
            }
            return;
        }
        mEndScrollRunnable.run();
    }

    private final void dispatchOnPageSelected(int i)
    {
        if (mOnPageChangeListener != null)
        {
            mOnPageChangeListener.onPageSelected(i);
        }
        if (mOnPageChangeListeners != null)
        {
            int k = mOnPageChangeListeners.size();
            for (int j = 0; j < k; j++)
            {
                OnPageChangeListener onpagechangelistener = (OnPageChangeListener)mOnPageChangeListeners.get(j);
                if (onpagechangelistener != null)
                {
                    onpagechangelistener.onPageSelected(i);
                }
            }

        }
    }

    private final Rect getChildRectInPagerCoordinates(Rect rect, View view)
    {
        if (rect == null)
        {
            rect = new Rect();
        }
        if (view == null)
        {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        for (view = view.getParent(); (view instanceof ViewGroup) && view != this; view = view.getParent())
        {
            view = (ViewGroup)view;
            rect.left = rect.left + view.getLeft();
            rect.right = rect.right + view.getRight();
            rect.top = rect.top + view.getTop();
            rect.bottom = rect.bottom + view.getBottom();
        }

        return rect;
    }

    private final ItemInfo infoForChild(View view)
    {
        for (int i = 0; i < mItems.size(); i++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(i);
            if (mAdapter.isViewFromObject(view, iteminfo.object))
            {
                return iteminfo;
            }
        }

        return null;
    }

    private final ItemInfo infoForPosition(int i)
    {
        for (int j = 0; j < mItems.size(); j++)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(j);
            if (iteminfo.position == i)
            {
                return iteminfo;
            }
        }

        return null;
    }

    private final void initViewPager()
    {
        setWillNotDraw(false);
        setDescendantFocusability(0x40000);
        setFocusable(true);
        Context context = getContext();
        mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewconfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        mTouchSlop = viewconfiguration.getScaledPagingTouchSlop();
        mMinimumVelocity = (int)(400F * f);
        mMaximumVelocity = viewconfiguration.getScaledMaximumFlingVelocity();
        mLeftEdge = new EdgeEffect(context);
        mRightEdge = new EdgeEffect(context);
        mFlingDistance = (int)(25F * f);
        mCloseEnough = (int)(2.0F * f);
        mDefaultGutterSize = (int)(16F * f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0)
        {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new _cls4());
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
            mLastMotionX = motionevent.getX(i);
            mActivePointerId = motionevent.getPointerId(i);
            if (mVelocityTracker != null)
            {
                mVelocityTracker.clear();
            }
        }
    }

    private final boolean pageRight()
    {
        if (mAdapter != null && mCurItem < mAdapter.getCount() - 1)
        {
            setCurrentItem(mCurItem + 1, true);
            return true;
        } else
        {
            return false;
        }
    }

    private final boolean performDrag(float f)
    {
        boolean flag3 = true;
        boolean flag2 = true;
        float f1 = mLastMotionX;
        mLastMotionX = f;
        float f2 = (float)getScrollX() + (f1 - f);
        int i = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        f = (float)i * mFirstOffset;
        f1 = i;
        float f3 = mLastOffset;
        ItemInfo iteminfo = (ItemInfo)mItems.get(0);
        ItemInfo iteminfo1 = (ItemInfo)mItems.get(mItems.size() - 1);
        boolean flag;
        boolean flag1;
        if (iteminfo.position != 0)
        {
            f = iteminfo.offset * (float)i;
            flag = false;
        } else
        {
            flag = true;
        }
        if (iteminfo1.position != mAdapter.getCount() - 1)
        {
            f1 = iteminfo1.offset * (float)i;
            flag1 = false;
        } else
        {
            f1 *= f3;
            flag1 = true;
        }
        if (f2 < f)
        {
            if (flag)
            {
                mLeftEdge.onPull(Math.abs(f - f2) / (float)i);
            } else
            {
                flag2 = false;
            }
        } else
        if (f2 > f1)
        {
            if (flag1)
            {
                mRightEdge.onPull(Math.abs(f2 - f1) / (float)i);
                flag2 = flag3;
            } else
            {
                flag2 = false;
            }
            f = f1;
        } else
        {
            f = f2;
            flag2 = false;
        }
        mLastMotionX = mLastMotionX + (f - (float)(int)f);
        scrollTo((int)f, getScrollY());
        pageScrolled((int)f);
        return flag2;
    }

    private final void recomputeScrollPosition(int i, int j, int k, int l)
    {
        if (j <= 0 || mItems.isEmpty()) goto _L2; else goto _L1
_L1:
        if (mScroller.isFinished()) goto _L4; else goto _L3
_L3:
        mScroller.setFinalX(getCurrentItem() * (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()));
_L6:
        return;
_L4:
        int i1 = getPaddingLeft();
        int j1 = getPaddingRight();
        int k1 = getPaddingLeft();
        int l1 = getPaddingRight();
        float f = (float)getScrollX() / (float)((j - k1 - l1) + l);
        scrollTo((int)((float)((i - i1 - j1) + k) * f), getScrollY());
        return;
_L2:
        ItemInfo iteminfo = infoForPosition(mCurItem);
        float f1;
        if (iteminfo != null)
        {
            f1 = Math.min(iteminfo.offset, mLastOffset);
        } else
        {
            f1 = 0.0F;
        }
        i = (int)(f1 * (float)(i - getPaddingLeft() - getPaddingRight()));
        if (i != getScrollX())
        {
            completeScroll(false);
            scrollTo(i, getScrollY());
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private final boolean resetTouch()
    {
        boolean flag = false;
        mActivePointerId = -1;
        mIsBeingDragged = false;
        mIsUnableToDrag = false;
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
        mLeftEdge.onRelease();
        mRightEdge.onRelease();
        if (mLeftEdge.isFinished() || mRightEdge.isFinished())
        {
            flag = true;
        }
        return flag;
    }

    private final void scrollToItem(int i, boolean flag, int j, boolean flag1)
    {
        ItemInfo iteminfo = infoForPosition(i);
        int l;
        if (iteminfo != null)
        {
            l = (int)((float)(getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) * Math.max(mFirstOffset, Math.min(iteminfo.offset, mLastOffset)));
        } else
        {
            l = 0;
        }
        if (flag)
        {
            if (getChildCount() == 0)
            {
                if (mScrollingCacheEnabled)
                {
                    mScrollingCacheEnabled = false;
                }
            } else
            {
                int k;
                int i1;
                int j1;
                if (mScroller != null && !mScroller.isFinished())
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0)
                {
                    if (mIsScrollStarted)
                    {
                        k = mScroller.getCurrX();
                    } else
                    {
                        k = mScroller.getStartX();
                    }
                    mScroller.abortAnimation();
                    if (mScrollingCacheEnabled)
                    {
                        mScrollingCacheEnabled = false;
                    }
                } else
                {
                    k = getScrollX();
                }
                i1 = getScrollY();
                l -= k;
                j1 = 0 - i1;
                if (l == 0 && j1 == 0)
                {
                    completeScroll(false);
                    populate(mCurItem);
                    setScrollState(0);
                } else
                {
                    if (!mScrollingCacheEnabled)
                    {
                        mScrollingCacheEnabled = true;
                    }
                    setScrollState(2);
                    int k1 = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
                    int l1 = k1 / 2;
                    float f4 = Math.min(1.0F, (1.0F * (float)Math.abs(l)) / (float)k1);
                    float f = l1;
                    float f2 = l1;
                    f4 = (float)Math.sin((f4 - 0.5F) * 0.4712389F);
                    j = Math.abs(j);
                    if (j > 0)
                    {
                        j = Math.round(1000F * Math.abs((f2 * f4 + f) / (float)j)) * 4;
                    } else
                    {
                        float f1 = k1;
                        float f3 = mAdapter.getPageWidth(mCurItem);
                        j = (int)(((float)Math.abs(l) / (f1 * f3 + (float)mPageMargin) + 1.0F) * 100F);
                    }
                    j = Math.min(j, 600);
                    mIsScrollStarted = false;
                    mScroller.startScroll(k, i1, l, j1, j);
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else
        {
            if (flag1)
            {
                dispatchOnPageSelected(i);
            }
            completeScroll(false);
            scrollTo(l, 0);
            pageScrolled(l);
            return;
        }
        if (flag1)
        {
            dispatchOnPageSelected(i);
        }
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        int l = arraylist.size();
        int i1 = getDescendantFocusability();
        if (i1 != 0x60000)
        {
            for (int k = 0; k < getChildCount(); k++)
            {
                View view = getChildAt(k);
                if (view.getVisibility() == 0)
                {
                    ItemInfo iteminfo = infoForChild(view);
                    if (iteminfo != null && iteminfo.position == mCurItem)
                    {
                        view.addFocusables(arraylist, i, j);
                    }
                }
            }

        }
        while (i1 == 0x40000 && l != arraylist.size() || !isFocusable() || (j & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode() || arraylist == null) 
        {
            return;
        }
        arraylist.add(this);
    }

    public void addOnPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        if (mOnPageChangeListeners == null)
        {
            mOnPageChangeListeners = new ArrayList();
        }
        mOnPageChangeListeners.add(onpagechangelistener);
    }

    public void addTouchables(ArrayList arraylist)
    {
        for (int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if (view.getVisibility() != 0)
            {
                continue;
            }
            ItemInfo iteminfo = infoForChild(view);
            if (iteminfo != null && iteminfo.position == mCurItem)
            {
                view.addTouchables(arraylist);
            }
        }

    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (!checkLayoutParams(layoutparams))
        {
            layoutparams = generateLayoutParams(layoutparams);
        }
        LayoutParams layoutparams1 = (LayoutParams)layoutparams;
        boolean flag1 = layoutparams1.isDecor;
        boolean flag;
        if (view.getClass().getAnnotation(android/support/v4/view/ViewPager$DecorView) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        layoutparams1.isDecor = flag | flag1;
        if (mInLayout)
        {
            if (layoutparams1 != null && layoutparams1.isDecor)
            {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            } else
            {
                layoutparams1.needsMeasure = true;
                addViewInLayout(view, i, layoutparams);
                return;
            }
        } else
        {
            super.addView(view, i, layoutparams);
            return;
        }
    }

    public boolean canScrollHorizontally(int i)
    {
        if (mAdapter != null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j;
        int k;
        j = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        k = getScrollX();
        if (i >= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k <= (int)((float)j * mFirstOffset)) goto _L1; else goto _L3
_L3:
        return true;
        if (i <= 0 || k >= (int)((float)j * mLastOffset)) goto _L1; else goto _L4
_L4:
        return true;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public void computeScroll()
    {
        mIsScrollStarted = true;
        if (!mScroller.isFinished() && mScroller.computeScrollOffset())
        {
            int i = getScrollX();
            int j = getScrollY();
            int k = mScroller.getCurrX();
            int l = mScroller.getCurrY();
            if (i != k || j != l)
            {
                scrollTo(k, l);
                if (!pageScrolled(k))
                {
                    mScroller.abortAnimation();
                    scrollTo(0, l);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        } else
        {
            completeScroll(true);
            return;
        }
    }

    final void dataSetChanged()
    {
        Object obj;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int l1 = mAdapter.getCount();
        mExpectedAdapterCount = l1;
        int j1;
        if (mItems.size() < (mOffscreenPageLimit << 1) + 1 && mItems.size() < l1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = mCurItem;
        k = 0;
        i1 = 0;
        l = i;
        i = j;
        j = k;
        k = i1;
        if (k >= mItems.size())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (ItemInfo)mItems.get(k);
        i1 = mAdapter.getItemPosition(((ItemInfo) (obj)).object);
        if (i1 == -1)
        {
            break MISSING_BLOCK_LABEL_370;
        }
        if (i1 == -2)
        {
            mItems.remove(k);
            l = k - 1;
            k = j;
            if (j == 0)
            {
                mAdapter.startUpdate(this);
                k = 1;
            }
            mAdapter.destroyItem(this, ((ItemInfo) (obj)).position, ((ItemInfo) (obj)).object);
            int k1;
            if (mCurItem == ((ItemInfo) (obj)).position)
            {
                j = Math.max(0, Math.min(mCurItem, l1 - 1));
                i = k;
                k = 1;
            } else
            {
                j = i;
                boolean flag = true;
                i = k;
                k = ((flag) ? 1 : 0);
            }
        } else
        {
            if (((ItemInfo) (obj)).position == i1)
            {
                break MISSING_BLOCK_LABEL_370;
            }
            if (((ItemInfo) (obj)).position == mCurItem)
            {
                i = i1;
            }
            obj.position = i1;
            i1 = i;
            k1 = 1;
            l = k;
            i = j;
            j = i1;
            k = k1;
        }
        i1 = k;
        j1 = j;
        k = l + 1;
        j = i;
        i = j1;
        l = i1;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_70;
_L1:
        if (j != 0)
        {
            mAdapter.finishUpdate(this);
        }
        Collections.sort(mItems, COMPARATOR);
        if (l != 0)
        {
            k = getChildCount();
            for (j = 0; j < k; j++)
            {
                obj = (LayoutParams)getChildAt(j).getLayoutParams();
                if (!((LayoutParams) (obj)).isDecor)
                {
                    obj.widthFactor = 0.0F;
                }
            }

            setCurrentItemInternal(i, false, true, 0);
            requestLayout();
        }
        return;
        i1 = i;
        k1 = l;
        l = k;
        i = j;
        j = i1;
        k = k1;
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_204;
        }
    }

    public final int determineTargetPage(int i, float f, int j, int k)
    {
        if (Math.abs(k) > mFlingDistance && Math.abs(j) > mMinimumVelocity)
        {
            if (j <= 0)
            {
                i++;
            }
        } else
        {
            float f1;
            if (i >= mCurItem)
            {
                f1 = 0.4F;
            } else
            {
                f1 = 0.6F;
            }
            i += (int)(f1 + f);
        }
        j = i;
        if (mItems.size() > 0)
        {
            ItemInfo iteminfo = (ItemInfo)mItems.get(0);
            ItemInfo iteminfo1 = (ItemInfo)mItems.get(mItems.size() - 1);
            j = Math.max(iteminfo.position, Math.min(i, iteminfo1.position));
        }
        return j;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag1 = false;
        if (super.dispatchKeyEvent(keyevent)) goto _L2; else goto _L1
_L1:
        if (keyevent.getAction() != 0) goto _L4; else goto _L3
_L3:
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 3: default 56
    //                   21: 66
    //                   22: 112
    //                   61: 138;
           goto _L4 _L5 _L6 _L7
_L4:
        boolean flag = false;
_L9:
        if (!flag) goto _L8; else goto _L2
_L2:
        flag1 = true;
_L8:
        return flag1;
_L5:
        if (keyevent.hasModifiers(2))
        {
            if (mCurItem > 0)
            {
                setCurrentItem(mCurItem - 1, true);
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = arrowScroll(17);
        }
          goto _L9
_L6:
        if (keyevent.hasModifiers(2))
        {
            flag = pageRight();
        } else
        {
            flag = arrowScroll(66);
        }
          goto _L9
_L7:
        if (!keyevent.hasNoModifiers())
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = arrowScroll(2);
          goto _L9
        if (!keyevent.hasModifiers(1)) goto _L4; else goto _L10
_L10:
        flag = arrowScroll(1);
          goto _L9
        if (true) goto _L4; else goto _L11
_L11:
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        boolean flag1 = false;
        if (accessibilityevent.getEventType() != 4096) goto _L2; else goto _L1
_L1:
        boolean flag = super.dispatchPopulateAccessibilityEvent(accessibilityevent);
_L4:
        return flag;
_L2:
        int j = getChildCount();
        int i = 0;
        do
        {
            flag = flag1;
            if (i >= j)
            {
                continue;
            }
            View view = getChildAt(i);
            if (view.getVisibility() == 0)
            {
                ItemInfo iteminfo = infoForChild(view);
                if (iteminfo != null && iteminfo.position == mCurItem && view.dispatchPopulateAccessibilityEvent(accessibilityevent))
                {
                    return true;
                }
            }
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        int j = 0;
        int i = 0;
        int k = getOverScrollMode();
        if (k == 0 || k == 1 && mAdapter != null && mAdapter.getCount() > 1)
        {
            if (!mLeftEdge.isFinished())
            {
                j = canvas.save();
                i = getHeight() - getPaddingTop() - getPaddingBottom();
                int l = getWidth();
                canvas.rotate(270F);
                canvas.translate(-i + getPaddingTop(), mFirstOffset * (float)l);
                mLeftEdge.setSize(i, l);
                i = mLeftEdge.draw(canvas) | false;
                canvas.restoreToCount(j);
            }
            j = i;
            if (!mRightEdge.isFinished())
            {
                int i1 = canvas.save();
                j = getWidth();
                int j1 = getHeight();
                int k1 = getPaddingTop();
                int l1 = getPaddingBottom();
                canvas.rotate(90F);
                canvas.translate(-getPaddingTop(), -(mLastOffset + 1.0F) * (float)j);
                mRightEdge.setSize(j1 - k1 - l1, j);
                j = i | mRightEdge.draw(canvas);
                canvas.restoreToCount(i1);
            }
        } else
        {
            mLeftEdge.finish();
            mRightEdge.finish();
        }
        if (j != 0)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mMarginDrawable;
        if (drawable != null && drawable.isStateful())
        {
            drawable.setState(getDrawableState());
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter()
    {
        return mAdapter;
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        return ((LayoutParams)((View)mDrawingOrderedChildren.get(j)).getLayoutParams()).childIndex;
    }

    public int getCurrentItem()
    {
        return mCurItem;
    }

    public final ItemInfo infoForCurrentScrollPosition()
    {
        int i = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float f;
        float f1;
        float f2;
        float f3;
        ItemInfo iteminfo1;
        boolean flag;
        int j;
        if (i > 0)
        {
            f = (float)getScrollX() / (float)i;
        } else
        {
            f = 0.0F;
        }
        if (i > 0)
        {
            f1 = (float)mPageMargin / (float)i;
        } else
        {
            f1 = 0.0F;
        }
        f3 = 0.0F;
        f2 = 0.0F;
        j = -1;
        i = 0;
        flag = true;
        iteminfo1 = null;
        do
        {
            ItemInfo iteminfo;
label0:
            {
                ItemInfo iteminfo2;
label1:
                {
                    iteminfo2 = iteminfo1;
                    if (i >= mItems.size())
                    {
                        break label1;
                    }
                    iteminfo = (ItemInfo)mItems.get(i);
                    if (!flag && iteminfo.position != j + 1)
                    {
                        iteminfo = mTempItem;
                        iteminfo.offset = f3 + f2 + f1;
                        iteminfo.position = j + 1;
                        iteminfo.widthFactor = mAdapter.getPageWidth(iteminfo.position);
                        i--;
                    }
                    f2 = iteminfo.offset;
                    f3 = iteminfo.widthFactor;
                    if (!flag)
                    {
                        iteminfo2 = iteminfo1;
                        if (f < f2)
                        {
                            break label1;
                        }
                    }
                    if (f >= f3 + f2 + f1 && i != mItems.size() - 1)
                    {
                        break label0;
                    }
                    iteminfo2 = iteminfo;
                }
                return iteminfo2;
            }
            j = iteminfo.position;
            f3 = iteminfo.widthFactor;
            flag = false;
            i++;
            iteminfo1 = iteminfo;
        } while (true);
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    protected void onDetachedFromWindow()
    {
        removeCallbacks(mEndScrollRunnable);
        if (mScroller != null && !mScroller.isFinished())
        {
            mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mPageMargin > 0 && mMarginDrawable != null && mItems.size() > 0 && mAdapter != null)
        {
            int k = getScrollX();
            int l = getWidth();
            float f2 = (float)mPageMargin / (float)l;
            Object obj = (ItemInfo)mItems.get(0);
            float f = ((ItemInfo) (obj)).offset;
            int i1 = mItems.size();
            int i = ((ItemInfo) (obj)).position;
            int j1 = ((ItemInfo)mItems.get(i1 - 1)).position;
            int j = 0;
            do
            {
                if (i >= j1)
                {
                    break;
                }
                for (; i > ((ItemInfo) (obj)).position && j < i1; obj = (ItemInfo)((ArrayList) (obj)).get(j))
                {
                    obj = mItems;
                    j++;
                }

                float f1;
                if (i == ((ItemInfo) (obj)).position)
                {
                    f1 = (((ItemInfo) (obj)).offset + ((ItemInfo) (obj)).widthFactor) * (float)l;
                    f = ((ItemInfo) (obj)).offset + ((ItemInfo) (obj)).widthFactor + f2;
                } else
                {
                    float f3 = mAdapter.getPageWidth(i);
                    f1 = (f + f3) * (float)l;
                    f += f3 + f2;
                }
                if ((float)mPageMargin + f1 > (float)k)
                {
                    mMarginDrawable.setBounds(Math.round(f1), mTopPageBounds, Math.round((float)mPageMargin + f1), mBottomPageBounds);
                    mMarginDrawable.draw(canvas);
                }
                if (f1 > (float)(k + l))
                {
                    break;
                }
                i++;
            } while (true);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getAction() & 0xff;
        if (i != 3 && i != 1) goto _L2; else goto _L1
_L1:
        resetTouch();
_L4:
        return false;
_L2:
        if (i == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mIsBeingDragged)
        {
            return true;
        }
        if (mIsUnableToDrag) goto _L4; else goto _L3
_L3:
        i;
        JVM INSTR lookupswitch 3: default 88
    //                   0: 422
    //                   2: 115
    //                   6: 577;
           goto _L5 _L6 _L7 _L8
_L5:
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        return mIsBeingDragged;
_L7:
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int j = mActivePointerId;
        if (j == -1)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = motionevent.findPointerIndex(j);
        f2 = motionevent.getX(j);
        f = f2 - mLastMotionX;
        f4 = Math.abs(f);
        f3 = motionevent.getY(j);
        f5 = Math.abs(f3 - mInitialMotionY);
        if (f != 0.0F)
        {
            float f6 = mLastMotionX;
            boolean flag;
            if (f6 < (float)mGutterSize && f > 0.0F || f6 > (float)(getWidth() - mGutterSize) && f < 0.0F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag && canScroll(this, false, (int)f, (int)f2, (int)f3))
            {
                mLastMotionX = f2;
                mLastMotionY = f3;
                mIsUnableToDrag = true;
                return false;
            }
        }
        if (f4 <= (float)mTouchSlop || 0.5F * f4 <= f5) goto _L10; else goto _L9
_L9:
        mIsBeingDragged = true;
        ViewParent viewparent = getParent();
        if (viewparent != null)
        {
            viewparent.requestDisallowInterceptTouchEvent(true);
        }
        setScrollState(1);
        if (f > 0.0F)
        {
            f = mInitialMotionX + (float)mTouchSlop;
        } else
        {
            f = mInitialMotionX - (float)mTouchSlop;
        }
        mLastMotionX = f;
        mLastMotionY = f3;
        if (!mScrollingCacheEnabled)
        {
            mScrollingCacheEnabled = true;
        }
_L11:
        if (mIsBeingDragged && performDrag(f2))
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if (f5 > (float)mTouchSlop)
        {
            mIsUnableToDrag = true;
        }
        if (true) goto _L11; else goto _L6
_L6:
        float f1 = motionevent.getX();
        mInitialMotionX = f1;
        mLastMotionX = f1;
        f1 = motionevent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = motionevent.getPointerId(0);
        mIsUnableToDrag = false;
        mIsScrollStarted = true;
        mScroller.computeScrollOffset();
        if (mScrollState == 2 && Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough)
        {
            mScroller.abortAnimation();
            mPopulatePending = false;
            populate(mCurItem);
            mIsBeingDragged = true;
            ViewParent viewparent1 = getParent();
            if (viewparent1 != null)
            {
                viewparent1.requestDisallowInterceptTouchEvent(true);
            }
            setScrollState(1);
        } else
        {
            completeScroll(false);
            mIsBeingDragged = false;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        onSecondaryPointerUp(motionevent);
        if (true) goto _L5; else goto _L12
_L12:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        int j1;
        int j2;
        int l3;
        int i4;
        int j4;
        int k4;
        l3 = getChildCount();
        j4 = k - i;
        i4 = l - j;
        j = getPaddingLeft();
        i = getPaddingTop();
        i1 = getPaddingRight();
        k = getPaddingBottom();
        k4 = getScrollX();
        j1 = 0;
        j2 = 0;
_L14:
        if (j2 >= l3) goto _L2; else goto _L1
_L1:
        View view = getChildAt(j2);
        if (view.getVisibility() == 8) goto _L4; else goto _L3
_L3:
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (!layoutparams.isDecor) goto _L4; else goto _L5
_L5:
        int l4;
        l = layoutparams.gravity;
        l4 = layoutparams.gravity;
        l & 7;
        JVM INSTR tableswitch 1 5: default 148
    //                   1 304
    //                   2 148
    //                   3 285
    //                   4 148
    //                   5 326;
           goto _L6 _L7 _L6 _L8 _L6 _L9
_L6:
        int k1;
        l = j;
        k1 = j;
_L15:
        l4 & 0x70;
        JVM INSTR lookupswitch 3: default 192
    //                   16: 394
    //                   48: 367
    //                   80: 422;
           goto _L10 _L11 _L12 _L13
_L10:
        int k2 = i;
        j = i;
        i = k;
        k = k2;
_L16:
        l += k4;
        view.layout(l, k, view.getMeasuredWidth() + l, view.getMeasuredHeight() + k);
        j1++;
        l = i1;
        k = k1;
        i1 = i;
        i = j1;
_L17:
        j2++;
        k1 = k;
        j1 = i;
        i = j;
        k = i1;
        i1 = l;
        j = k1;
          goto _L14
_L8:
        k1 = view.getMeasuredWidth();
        l = j;
        k1 += j;
          goto _L15
_L7:
        l = Math.max((j4 - view.getMeasuredWidth()) / 2, j);
        k1 = j;
          goto _L15
_L9:
        k1 = view.getMeasuredWidth();
        l = i1 + view.getMeasuredWidth();
        int l2 = j4 - i1 - k1;
        i1 = l;
        k1 = j;
        l = l2;
          goto _L15
_L12:
        int i3 = view.getMeasuredHeight();
        j = k;
        i3 += i;
        k = i;
        i = j;
        j = i3;
          goto _L16
_L11:
        int j3 = Math.max((i4 - view.getMeasuredHeight()) / 2, i);
        j = i;
        i = k;
        k = j3;
          goto _L16
_L13:
        int k3 = i4 - k - view.getMeasuredHeight();
        int i5 = view.getMeasuredHeight();
        j = i;
        i = k + i5;
        k = k3;
          goto _L16
_L2:
        i1 = j4 - j - i1;
        for (l = 0; l < l3; l++)
        {
            View view1 = getChildAt(l);
            if (view1.getVisibility() == 8)
            {
                continue;
            }
            LayoutParams layoutparams1 = (LayoutParams)view1.getLayoutParams();
            if (layoutparams1.isDecor)
            {
                continue;
            }
            ItemInfo iteminfo = infoForChild(view1);
            if (iteminfo == null)
            {
                continue;
            }
            float f = i1;
            int l1 = (int)(iteminfo.offset * f) + j;
            if (layoutparams1.needsMeasure)
            {
                layoutparams1.needsMeasure = false;
                float f1 = i1;
                view1.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)(layoutparams1.widthFactor * f1), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(i4 - i - k, 0x40000000));
            }
            view1.layout(l1, i, view1.getMeasuredWidth() + l1, view1.getMeasuredHeight() + i);
        }

        mTopPageBounds = i;
        mBottomPageBounds = i4 - k;
        mDecorChildCount = j1;
        if (mFirstLayout)
        {
            scrollToItem(mCurItem, false, 0, false);
        }
        mFirstLayout = false;
        return;
_L4:
        l = j1;
        j1 = i;
        int i2 = j;
        i = l;
        l = i1;
        i1 = k;
        j = j1;
        k = i2;
          goto _L17
    }

    public void onMeasure(int i, int j)
    {
label0:
        {
            setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, j));
            i = getMeasuredWidth();
            mGutterSize = Math.min(i / 10, mDefaultGutterSize);
            i = i - getPaddingLeft() - getPaddingRight();
            j = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
            int j2 = getChildCount();
            int i1 = 0;
            while (i1 < j2) 
            {
label1:
                {
                    {
                        View view = getChildAt(i1);
                        int k = i;
                        int l = j;
                        if (view.getVisibility() == 8)
                        {
                            break label1;
                        }
                        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                        k = i;
                        l = j;
                        if (layoutparams == null)
                        {
                            break label1;
                        }
                        k = i;
                        l = j;
                        if (!layoutparams.isDecor)
                        {
                            break label1;
                        }
                        k = layoutparams.gravity & 7;
                        int j1 = layoutparams.gravity & 0x70;
                        int k1 = 0x80000000;
                        l = 0x80000000;
                        boolean flag;
                        boolean flag1;
                        if (j1 == 48 || j1 == 80)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (k == 3 || k == 5)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (flag)
                        {
                            k = 0x40000000;
                        } else
                        {
                            k = k1;
                            if (flag1)
                            {
                                l = 0x40000000;
                                k = k1;
                            }
                        }
                        if (layoutparams.width != -2)
                        {
                            k1 = 0x40000000;
                            float f;
                            int l1;
                            int i2;
                            if (layoutparams.width != -1)
                            {
                                k = layoutparams.width;
                            } else
                            {
                                k = i;
                            }
                        } else
                        {
                            k1 = k;
                            k = i;
                        }
                        if (layoutparams.height == -2)
                        {
                            break label0;
                        }
                        l1 = 0x40000000;
                        l = l1;
                        if (layoutparams.height == -1)
                        {
                            break label0;
                        }
                        i2 = layoutparams.height;
                        l = l1;
                        l1 = i2;
                    }
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k, k1), android.view.View.MeasureSpec.makeMeasureSpec(l1, l));
                    if (flag)
                    {
                        l = j - view.getMeasuredHeight();
                        k = i;
                    } else
                    {
                        k = i;
                        l = j;
                        if (flag1)
                        {
                            k = i - view.getMeasuredWidth();
                            l = j;
                        }
                    }
                }
                i1++;
                i = k;
                j = l;
            }
            android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
            mChildHeightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000);
            mInLayout = true;
            populate(mCurItem);
            mInLayout = false;
            k = getChildCount();
            for (j = 0; j < k; j++)
            {
                view = getChildAt(j);
                if (view.getVisibility() == 8)
                {
                    continue;
                }
                layoutparams = (LayoutParams)view.getLayoutParams();
                if (layoutparams == null || !layoutparams.isDecor)
                {
                    f = i;
                    view.measure(android.view.View.MeasureSpec.makeMeasureSpec((int)(layoutparams.widthFactor * f), 0x40000000), mChildHeightMeasureSpec);
                }
            }

            return;
        }
        l1 = j;
        break MISSING_BLOCK_LABEL_282;
    }

    public void onPageScrolled(int i, float f, int j)
    {
        if (mDecorChildCount <= 0) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        int l1;
        int i2;
        int j2;
        int k2;
        i2 = getScrollX();
        k = getPaddingLeft();
        l = getPaddingRight();
        j2 = getWidth();
        k2 = getChildCount();
        l1 = 0;
_L8:
        if (l1 >= k2) goto _L2; else goto _L3
_L3:
        View view;
        LayoutParams layoutparams;
        view = getChildAt(l1);
        layoutparams = (LayoutParams)view.getLayoutParams();
        if (!layoutparams.isDecor)
        {
            break MISSING_BLOCK_LABEL_382;
        }
        layoutparams.gravity & 7;
        JVM INSTR tableswitch 1 5: default 116
    //                   1 219
    //                   2 116
    //                   3 190
    //                   4 116
    //                   5 251;
           goto _L4 _L5 _L4 _L6 _L4 _L7
_L4:
        int i1;
        i1 = k;
        int j1 = l;
        l = k;
        k = j1;
_L9:
        int k1;
        int l2 = (i1 + i2) - view.getLeft();
        i1 = k;
        k1 = l;
        if (l2 != 0)
        {
            view.offsetLeftAndRight(l2);
            k1 = l;
            i1 = k;
        }
_L10:
        l1++;
        k = k1;
        l = i1;
          goto _L8
_L6:
        i1 = view.getWidth();
        k1 = i1 + k;
        i1 = k;
        k = l;
        l = k1;
          goto _L9
_L5:
        i1 = Math.max((j2 - view.getMeasuredWidth()) / 2, k);
        k1 = k;
        k = l;
        l = k1;
          goto _L9
_L7:
        i1 = j2 - l - view.getMeasuredWidth();
        int i3 = view.getMeasuredWidth();
        k1 = k;
        k = l + i3;
        l = k1;
          goto _L9
_L2:
        if (mOnPageChangeListener != null)
        {
            mOnPageChangeListener.onPageScrolled(i, f, j);
        }
        if (mOnPageChangeListeners != null)
        {
            l = mOnPageChangeListeners.size();
            for (k = 0; k < l; k++)
            {
                OnPageChangeListener onpagechangelistener = (OnPageChangeListener)mOnPageChangeListeners.get(k);
                if (onpagechangelistener != null)
                {
                    onpagechangelistener.onPageScrolled(i, f, j);
                }
            }

        }
        mCalledSuper = true;
        return;
        i1 = l;
        k1 = k;
          goto _L10
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        byte byte0 = -1;
        int k = getChildCount();
        int j;
        if ((i & 2) != 0)
        {
            byte0 = 1;
            j = 0;
        } else
        {
            j = k - 1;
            k = -1;
        }
        for (; j != k; j += byte0)
        {
            View view = getChildAt(j);
            if (view.getVisibility() != 0)
            {
                continue;
            }
            ItemInfo iteminfo = infoForChild(view);
            if (iteminfo != null && iteminfo.position == mCurItem && view.requestFocus(i, rect))
            {
                return true;
            }
        }

        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(((AbsSavedState) (parcelable)).mSuperState);
        if (mAdapter != null)
        {
            Object obj = ((SavedState) (parcelable)).adapterState;
            obj = ((SavedState) (parcelable)).loader;
            setCurrentItemInternal(((SavedState) (parcelable)).position, false, true, 0);
            return;
        } else
        {
            mRestoredCurItem = ((SavedState) (parcelable)).position;
            mRestoredAdapterState = ((SavedState) (parcelable)).adapterState;
            mRestoredClassLoader = ((SavedState) (parcelable)).loader;
            return;
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.position = mCurItem;
        if (mAdapter != null)
        {
            savedstate.adapterState = mAdapter.saveState();
        }
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k)
        {
            recomputeScrollPosition(i, k, mPageMargin, mPageMargin);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        flag1 = false;
        if (mFakeDragging)
        {
            return true;
        }
        if (motionevent.getAction() == 0 && motionevent.getEdgeFlags() != 0)
        {
            return false;
        }
        if (mAdapter == null || mAdapter.getCount() == 0)
        {
            return false;
        }
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        flag = flag1;
        motionevent.getAction() & 0xff;
        JVM INSTR tableswitch 0 6: default 124
    //                   0 139
    //                   1 433
    //                   2 205
    //                   3 585
    //                   4 128
    //                   5 616
    //                   6 649;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        flag = flag1;
_L10:
        if (flag)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
_L2:
        mScroller.abortAnimation();
        mPopulatePending = false;
        populate(mCurItem);
        float f = motionevent.getX();
        mInitialMotionX = f;
        mLastMotionX = f;
        f = motionevent.getY();
        mInitialMotionY = f;
        mLastMotionY = f;
        mActivePointerId = motionevent.getPointerId(0);
        flag = flag1;
        continue; /* Loop/switch isn't completed */
_L4:
        if (!mIsBeingDragged)
        {
            int i = motionevent.findPointerIndex(mActivePointerId);
            if (i == -1)
            {
                flag = resetTouch();
                continue; /* Loop/switch isn't completed */
            }
            float f1 = motionevent.getX(i);
            float f4 = Math.abs(f1 - mLastMotionX);
            float f3 = motionevent.getY(i);
            float f5 = Math.abs(f3 - mLastMotionY);
            if (f4 > (float)mTouchSlop && f4 > f5)
            {
                mIsBeingDragged = true;
                ViewParent viewparent = getParent();
                if (viewparent != null)
                {
                    viewparent.requestDisallowInterceptTouchEvent(true);
                }
                if (f1 - mInitialMotionX > 0.0F)
                {
                    f1 = mInitialMotionX + (float)mTouchSlop;
                } else
                {
                    f1 = mInitialMotionX - (float)mTouchSlop;
                }
                mLastMotionX = f1;
                mLastMotionY = f3;
                setScrollState(1);
                if (!mScrollingCacheEnabled)
                {
                    mScrollingCacheEnabled = true;
                }
                viewparent = getParent();
                if (viewparent != null)
                {
                    viewparent.requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        flag = flag1;
        if (mIsBeingDragged)
        {
            flag = performDrag(motionevent.getX(motionevent.findPointerIndex(mActivePointerId))) | false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        flag = flag1;
        if (mIsBeingDragged)
        {
            Object obj = mVelocityTracker;
            ((VelocityTracker) (obj)).computeCurrentVelocity(1000, mMaximumVelocity);
            int j = (int)((VelocityTracker) (obj)).getXVelocity(mActivePointerId);
            mPopulatePending = true;
            int l = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            int i1 = getScrollX();
            obj = infoForCurrentScrollPosition();
            float f2 = (float)mPageMargin / (float)l;
            setCurrentItemInternal(determineTargetPage(((ItemInfo) (obj)).position, ((float)i1 / (float)l - ((ItemInfo) (obj)).offset) / (((ItemInfo) (obj)).widthFactor + f2), j, (int)(motionevent.getX(motionevent.findPointerIndex(mActivePointerId)) - mInitialMotionX)), true, true, j);
            flag = resetTouch();
        }
        continue; /* Loop/switch isn't completed */
_L5:
        flag = flag1;
        if (mIsBeingDragged)
        {
            scrollToItem(mCurItem, true, 0, false);
            flag = resetTouch();
        }
        continue; /* Loop/switch isn't completed */
_L7:
        int k = motionevent.getActionIndex();
        mLastMotionX = motionevent.getX(k);
        mActivePointerId = motionevent.getPointerId(k);
        flag = flag1;
        continue; /* Loop/switch isn't completed */
_L8:
        onSecondaryPointerUp(motionevent);
        mLastMotionX = motionevent.getX(motionevent.findPointerIndex(mActivePointerId));
        flag = flag1;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final boolean pageScrolled(int i)
    {
        if (mItems.size() != 0)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        if (!mFirstLayout) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        mCalledSuper = false;
        onPageScrolled(0, 0.0F, 0);
        if (mCalledSuper) goto _L1; else goto _L3
_L3:
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        ItemInfo iteminfo = infoForCurrentScrollPosition();
        int k = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int l = mPageMargin;
        float f = (float)mPageMargin / (float)k;
        int j = iteminfo.position;
        f = ((float)i / (float)k - iteminfo.offset) / (iteminfo.widthFactor + f);
        i = (int)((float)(l + k) * f);
        mCalledSuper = false;
        onPageScrolled(j, f, i);
        if (!mCalledSuper)
        {
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else
        {
            return true;
        }
    }

    final void populate(int i)
    {
        float f;
        float f1;
        float f2;
        Object obj;
        android.content.res.Resources.NotFoundException notfoundexception;
        Object obj1;
        ItemInfo iteminfo;
        Object obj2;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if (mCurItem != i)
        {
            iteminfo = infoForPosition(mCurItem);
            mCurItem = i;
        } else
        {
            iteminfo = null;
        }
        break MISSING_BLOCK_LABEL_23;
_L13:
        do
        {
            return;
        } while (mAdapter == null || mPopulatePending || getWindowToken() == null);
        mAdapter.startUpdate(this);
        i = mOffscreenPageLimit;
        l1 = Math.max(0, mCurItem - i);
        j1 = mAdapter.getCount();
        k1 = Math.min(j1 - 1, i + mCurItem);
        if (j1 != mExpectedAdapterCount)
        {
            try
            {
                obj = getResources().getResourceName(getId());
            }
            // Misplaced declaration of an exception variable
            catch (android.content.res.Resources.NotFoundException notfoundexception)
            {
                notfoundexception = Integer.toHexString(getId());
            }
            throw new IllegalStateException((new StringBuilder("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ")).append(mExpectedAdapterCount).append(", found: ").append(j1).append(" Pager id: ").append(((String) (obj))).append(" Pager class: ").append(getClass()).append(" Problematic adapter: ").append(mAdapter.getClass()).toString());
        }
        i = 0;
_L10:
        if (i >= mItems.size())
        {
            break MISSING_BLOCK_LABEL_1958;
        }
        notfoundexception = (ItemInfo)mItems.get(i);
        if (((ItemInfo) (notfoundexception)).position < mCurItem) goto _L2; else goto _L1
_L1:
        if (((ItemInfo) (notfoundexception)).position != mCurItem)
        {
            break MISSING_BLOCK_LABEL_1958;
        }
_L24:
        if (notfoundexception == null && j1 > 0)
        {
            obj1 = addNewItem(mCurItem, i);
        } else
        {
            obj1 = notfoundexception;
        }
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        i1 = i - 1;
        if (i1 >= 0)
        {
            notfoundexception = (ItemInfo)mItems.get(i1);
        } else
        {
            notfoundexception = null;
        }
        i2 = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        if (i2 <= 0)
        {
            f1 = 0.0F;
        } else
        {
            f1 = (2.0F - ((ItemInfo) (obj1)).widthFactor) + (float)getPaddingLeft() / (float)i2;
        }
        j = mCurItem;
        f2 = 0.0F;
        l = j - 1;
        k = i;
        obj2 = notfoundexception;
        if (l < 0) goto _L6; else goto _L5
_L5:
        if (f2 < f1 || l >= l1) goto _L8; else goto _L7
_L7:
        if (obj2 == null) goto _L6; else goto _L9
_L9:
        notfoundexception = ((android.content.res.Resources.NotFoundException) (obj2));
        i = i1;
        f = f2;
        j = k;
        if (l == ((ItemInfo) (obj2)).position)
        {
            notfoundexception = ((android.content.res.Resources.NotFoundException) (obj2));
            i = i1;
            f = f2;
            j = k;
            if (!((ItemInfo) (obj2)).scrolling)
            {
                mItems.remove(i1);
                mAdapter.destroyItem(this, l, ((ItemInfo) (obj2)).object);
                i = i1 - 1;
                j = k - 1;
                if (i >= 0)
                {
                    notfoundexception = (ItemInfo)mItems.get(i);
                    f = f2;
                } else
                {
                    notfoundexception = null;
                    f = f2;
                }
            }
        }
_L11:
        l--;
        obj2 = notfoundexception;
        i1 = i;
        f2 = f;
        k = j;
        break MISSING_BLOCK_LABEL_354;
_L2:
        i++;
          goto _L10
_L8:
        if (obj2 != null && l == ((ItemInfo) (obj2)).position)
        {
            f = f2 + ((ItemInfo) (obj2)).widthFactor;
            i = i1 - 1;
            if (i >= 0)
            {
                notfoundexception = (ItemInfo)mItems.get(i);
                j = k;
            } else
            {
                notfoundexception = null;
                j = k;
            }
        } else
        {
            f = f2 + addNewItem(l, i1 + 1).widthFactor;
            j = k + 1;
            if (i1 >= 0)
            {
                notfoundexception = (ItemInfo)mItems.get(i1);
                i = i1;
            } else
            {
                notfoundexception = null;
                i = i1;
            }
        }
          goto _L11
_L6:
label0:
        {
            f = ((ItemInfo) (obj1)).widthFactor;
            i = k + 1;
            if (f >= 2.0F)
            {
                break label0;
            }
            if (i < mItems.size())
            {
                notfoundexception = (ItemInfo)mItems.get(i);
            } else
            {
                notfoundexception = null;
            }
            if (i2 <= 0)
            {
                f1 = 0.0F;
            } else
            {
                f1 = (float)getPaddingRight() / (float)i2 + 2.0F;
            }
            j = mCurItem;
            j++;
            do
            {
                if (j >= j1)
                {
                    break label0;
                }
                if (f >= f1 && j > k1)
                {
                    if (notfoundexception == null)
                    {
                        break label0;
                    }
                    if (j == ((ItemInfo) (notfoundexception)).position && !((ItemInfo) (notfoundexception)).scrolling)
                    {
                        mItems.remove(i);
                        mAdapter.destroyItem(this, j, ((ItemInfo) (notfoundexception)).object);
                        if (i < mItems.size())
                        {
                            notfoundexception = (ItemInfo)mItems.get(i);
                        } else
                        {
                            notfoundexception = null;
                        }
                    }
                } else
                if (notfoundexception != null && j == ((ItemInfo) (notfoundexception)).position)
                {
                    f2 = ((ItemInfo) (notfoundexception)).widthFactor;
                    i++;
                    if (i < mItems.size())
                    {
                        notfoundexception = (ItemInfo)mItems.get(i);
                    } else
                    {
                        notfoundexception = null;
                    }
                    f += f2;
                } else
                {
                    notfoundexception = addNewItem(j, i);
                    i++;
                    f2 = ((ItemInfo) (notfoundexception)).widthFactor;
                    if (i < mItems.size())
                    {
                        notfoundexception = (ItemInfo)mItems.get(i);
                    } else
                    {
                        notfoundexception = null;
                    }
                    f += f2;
                }
                j++;
            } while (true);
        }
        l = mAdapter.getCount();
        i = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        if (i > 0)
        {
            f1 = (float)mPageMargin / (float)i;
        } else
        {
            f1 = 0.0F;
        }
        if (iteminfo != null)
        {
            i = iteminfo.position;
            if (i < ((ItemInfo) (obj1)).position)
            {
                f = iteminfo.offset;
                f2 = iteminfo.widthFactor;
                j = 0;
                f = f2 + f + f1;
                for (i++; i <= ((ItemInfo) (obj1)).position && j < mItems.size(); i++)
                {
                    for (notfoundexception = (ItemInfo)mItems.get(j); i > ((ItemInfo) (notfoundexception)).position && j < mItems.size() - 1; notfoundexception = (ItemInfo)mItems.get(j))
                    {
                        j++;
                    }

                    while (i < ((ItemInfo) (notfoundexception)).position) 
                    {
                        f2 = mAdapter.getPageWidth(i);
                        i++;
                        f = f2 + f1 + f;
                    }
                    notfoundexception.offset = f;
                    f += ((ItemInfo) (notfoundexception)).widthFactor + f1;
                }

            } else
            if (i > ((ItemInfo) (obj1)).position)
            {
                j = mItems.size();
                f = iteminfo.offset;
                j--;
                for (i--; i >= ((ItemInfo) (obj1)).position && j >= 0; i--)
                {
                    for (notfoundexception = (ItemInfo)mItems.get(j); i < ((ItemInfo) (notfoundexception)).position && j > 0; notfoundexception = (ItemInfo)mItems.get(j))
                    {
                        j--;
                    }

                    while (i > ((ItemInfo) (notfoundexception)).position) 
                    {
                        f2 = mAdapter.getPageWidth(i);
                        i--;
                        f -= f2 + f1;
                    }
                    f -= ((ItemInfo) (notfoundexception)).widthFactor + f1;
                    notfoundexception.offset = f;
                }

            }
        }
        i1 = mItems.size();
        f2 = ((ItemInfo) (obj1)).offset;
        i = ((ItemInfo) (obj1)).position - 1;
        if (((ItemInfo) (obj1)).position == 0)
        {
            f = ((ItemInfo) (obj1)).offset;
        } else
        {
            f = -3.402823E+38F;
        }
        mFirstOffset = f;
        if (((ItemInfo) (obj1)).position == l - 1)
        {
            f = (((ItemInfo) (obj1)).offset + ((ItemInfo) (obj1)).widthFactor) - 1.0F;
        } else
        {
            f = 3.402823E+38F;
        }
        mLastOffset = f;
        j = k - 1;
        f = f2;
        for (; j >= 0; j--)
        {
            for (notfoundexception = (ItemInfo)mItems.get(j); i > ((ItemInfo) (notfoundexception)).position; i--)
            {
                f -= mAdapter.getPageWidth(i) + f1;
            }

            f -= ((ItemInfo) (notfoundexception)).widthFactor + f1;
            notfoundexception.offset = f;
            if (((ItemInfo) (notfoundexception)).position == 0)
            {
                mFirstOffset = f;
            }
            i--;
        }

        f = ((ItemInfo) (obj1)).offset + ((ItemInfo) (obj1)).widthFactor + f1;
        i = ((ItemInfo) (obj1)).position + 1;
        for (j = k + 1; j < i1; j++)
        {
            for (notfoundexception = (ItemInfo)mItems.get(j); i < ((ItemInfo) (notfoundexception)).position; i++)
            {
                f = mAdapter.getPageWidth(i) + f1 + f;
            }

            if (((ItemInfo) (notfoundexception)).position == l - 1)
            {
                mLastOffset = (((ItemInfo) (notfoundexception)).widthFactor + f) - 1.0F;
            }
            notfoundexception.offset = f;
            f += ((ItemInfo) (notfoundexception)).widthFactor + f1;
            i++;
        }

        mAdapter.setPrimaryItem(this, mCurItem, ((ItemInfo) (obj1)).object);
_L4:
        mAdapter.finishUpdate(this);
        j = getChildCount();
        for (i = 0; i < j; i++)
        {
            obj1 = getChildAt(i);
            notfoundexception = (LayoutParams)((View) (obj1)).getLayoutParams();
            notfoundexception.childIndex = i;
            if (((LayoutParams) (notfoundexception)).isDecor || ((LayoutParams) (notfoundexception)).widthFactor != 0.0F)
            {
                continue;
            }
            obj1 = infoForChild(((View) (obj1)));
            if (obj1 != null)
            {
                notfoundexception.widthFactor = ((ItemInfo) (obj1)).widthFactor;
                notfoundexception.position = ((ItemInfo) (obj1)).position;
            }
        }

        if (!hasFocus()) goto _L13; else goto _L12
_L12:
        notfoundexception = findFocus();
        if (notfoundexception == null)
        {
            break MISSING_BLOCK_LABEL_1936;
        }
_L22:
        obj1 = notfoundexception.getParent();
        if (obj1 == this) goto _L15; else goto _L14
_L14:
        if (obj1 != null && (obj1 instanceof View)) goto _L17; else goto _L16
_L16:
        notfoundexception = null;
_L23:
        if (notfoundexception != null && ((ItemInfo) (notfoundexception)).position == mCurItem) goto _L13; else goto _L18
_L18:
        i = 0;
_L21:
        if (i >= getChildCount()) goto _L13; else goto _L19
_L19:
        notfoundexception = getChildAt(i);
        obj1 = infoForChild(notfoundexception);
        if (obj1 != null && ((ItemInfo) (obj1)).position == mCurItem && notfoundexception.requestFocus(2)) goto _L13; else goto _L20
_L20:
        i++;
          goto _L21
_L17:
        notfoundexception = (View)obj1;
          goto _L22
_L15:
        notfoundexception = infoForChild(notfoundexception);
          goto _L23
        notfoundexception = null;
          goto _L23
        notfoundexception = null;
          goto _L24
    }

    public void removeView(View view)
    {
        if (mInLayout)
        {
            removeViewInLayout(view);
            return;
        } else
        {
            super.removeView(view);
            return;
        }
    }

    public void setAdapter(PagerAdapter pageradapter)
    {
        if (mAdapter == null)
        {
            break MISSING_BLOCK_LABEL_151;
        }
        synchronized (mAdapter)
        {
            obj.mViewPagerObserver = null;
        }
        mAdapter.startUpdate(this);
        for (int i = 0; i < mItems.size(); i++)
        {
            obj = (ItemInfo)mItems.get(i);
            mAdapter.destroyItem(this, ((ItemInfo) (obj)).position, ((ItemInfo) (obj)).object);
        }

        break MISSING_BLOCK_LABEL_82;
        pageradapter;
        obj;
        JVM INSTR monitorexit ;
        throw pageradapter;
        mAdapter.finishUpdate(this);
        mItems.clear();
        for (int j = 0; j < getChildCount(); j++)
        {
            if (!((LayoutParams)getChildAt(j).getLayoutParams()).isDecor)
            {
                removeViewAt(j);
                j--;
            }
        }

        mCurItem = 0;
        scrollTo(0, 0);
        mAdapter = pageradapter;
        mExpectedAdapterCount = 0;
        if (mAdapter == null)
        {
            break MISSING_BLOCK_LABEL_266;
        }
        if (mObserver == null)
        {
            mObserver = new PagerObserver();
        }
        pageradapter = mAdapter;
        PagerObserver pagerobserver = mObserver;
        pageradapter;
        JVM INSTR monitorenter ;
        pageradapter.mViewPagerObserver = pagerobserver;
        pageradapter;
        JVM INSTR monitorexit ;
        boolean flag;
        mPopulatePending = false;
        flag = mFirstLayout;
        mFirstLayout = true;
        mExpectedAdapterCount = mAdapter.getCount();
        if (mRestoredCurItem < 0)
        {
            break MISSING_BLOCK_LABEL_272;
        }
        setCurrentItemInternal(mRestoredCurItem, false, true, 0);
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
        return;
        Exception exception;
        exception;
        pageradapter;
        JVM INSTR monitorexit ;
        throw exception;
        if (!flag)
        {
            populate(mCurItem);
            return;
        } else
        {
            requestLayout();
            return;
        }
    }

    public void setCurrentItem(int i)
    {
        mPopulatePending = false;
        boolean flag;
        if (!mFirstLayout)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setCurrentItemInternal(i, flag, false, 0);
    }

    public void setCurrentItem(int i, boolean flag)
    {
        mPopulatePending = false;
        setCurrentItemInternal(i, flag, false, 0);
    }

    public final void setCurrentItemInternal(int i, boolean flag, boolean flag1, int j)
    {
        boolean flag2 = false;
        if (mAdapter != null && mAdapter.getCount() > 0) goto _L2; else goto _L1
_L1:
        if (mScrollingCacheEnabled)
        {
            mScrollingCacheEnabled = false;
        }
_L4:
        return;
_L2:
        if (flag1 || mCurItem != i || mItems.size() == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mScrollingCacheEnabled)
        {
            mScrollingCacheEnabled = false;
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i >= 0) goto _L6; else goto _L5
_L5:
        int k = 0;
_L8:
        i = mOffscreenPageLimit;
        if (k > mCurItem + i || k < mCurItem - i)
        {
            for (i = 0; i < mItems.size(); i++)
            {
                ((ItemInfo)mItems.get(i)).scrolling = true;
            }

        }
        break; /* Loop/switch isn't completed */
_L6:
        k = i;
        if (i >= mAdapter.getCount())
        {
            k = mAdapter.getCount() - 1;
        }
        if (true) goto _L8; else goto _L7
_L7:
        flag1 = flag2;
        if (mCurItem != k)
        {
            flag1 = true;
        }
        if (mFirstLayout)
        {
            mCurItem = k;
            if (flag1)
            {
                dispatchOnPageSelected(k);
            }
            requestLayout();
            return;
        } else
        {
            populate(k);
            scrollToItem(k, flag, j, flag1);
            return;
        }
    }

    public void setOffscreenPageLimit(int i)
    {
        int j = i;
        if (i <= 0)
        {
            Log.w("ViewPager", (new StringBuilder("Requested offscreen page limit ")).append(i).append(" too small; defaulting to 1").toString());
            j = 1;
        }
        if (j != mOffscreenPageLimit)
        {
            mOffscreenPageLimit = j;
            populate(mCurItem);
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onpagechangelistener)
    {
        mOnPageChangeListener = onpagechangelistener;
    }

    public void setPageMargin(int i)
    {
        int j = mPageMargin;
        mPageMargin = i;
        int k = getWidth();
        recomputeScrollPosition(k, k, i, j);
        requestLayout();
    }

    public void setPageMarginDrawable(int i)
    {
        Drawable drawable = ContextCompat.getDrawable(getContext(), i);
        mMarginDrawable = drawable;
        if (drawable != null)
        {
            refreshDrawableState();
        }
        boolean flag;
        if (drawable == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setWillNotDraw(flag);
        invalidate();
    }

    public final void setScrollState(int i)
    {
        if (mScrollState != i) goto _L2; else goto _L1
_L1:
        return;
_L2:
        mScrollState = i;
        if (mOnPageChangeListener != null)
        {
            mOnPageChangeListener.onPageScrollStateChanged(i);
        }
        if (mOnPageChangeListeners != null)
        {
            int k = mOnPageChangeListeners.size();
            int j = 0;
            while (j < k) 
            {
                OnPageChangeListener onpagechangelistener = (OnPageChangeListener)mOnPageChangeListeners.get(j);
                if (onpagechangelistener != null)
                {
                    onpagechangelistener.onPageScrollStateChanged(i);
                }
                j++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mMarginDrawable;
    }


    private class _cls3
        implements Runnable
    {

        private final ViewPager this$0;

        public final void run()
        {
            setScrollState(0);
            ViewPager viewpager = ViewPager.this;
            viewpager.populate(viewpager.mCurItem);
        }

        _cls3()
        {
            this$0 = ViewPager.this;
            super();
        }
    }


    private class _cls4
        implements OnApplyWindowInsetsListener
    {

        private final Rect mTempRect = new Rect();
        private final ViewPager this$0;

        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            view = ViewCompat.onApplyWindowInsets(view, windowinsetscompat);
            if (((WindowInsets)((WindowInsetsCompat) (view)).mInsets).isConsumed())
            {
                return view;
            }
            windowinsetscompat = mTempRect;
            windowinsetscompat.left = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetLeft();
            windowinsetscompat.top = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetTop();
            windowinsetscompat.right = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetRight();
            windowinsetscompat.bottom = ((WindowInsets)((WindowInsetsCompat) (view)).mInsets).getSystemWindowInsetBottom();
            int k = getChildCount();
            for (int i = 0; i < k; i++)
            {
                WindowInsetsCompat windowinsetscompat1 = ViewCompat.dispatchApplyWindowInsets(getChildAt(i), view);
                windowinsetscompat.left = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetLeft(), ((Rect) (windowinsetscompat)).left);
                windowinsetscompat.top = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetTop(), ((Rect) (windowinsetscompat)).top);
                windowinsetscompat.right = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetRight(), ((Rect) (windowinsetscompat)).right);
                windowinsetscompat.bottom = Math.min(((WindowInsets)windowinsetscompat1.mInsets).getSystemWindowInsetBottom(), ((Rect) (windowinsetscompat)).bottom);
            }

            int j = ((Rect) (windowinsetscompat)).left;
            k = ((Rect) (windowinsetscompat)).top;
            int l = ((Rect) (windowinsetscompat)).right;
            int i1 = ((Rect) (windowinsetscompat)).bottom;
            return new WindowInsetsCompat(((WindowInsets)((WindowInsetsCompat) (view)).mInsets).replaceSystemWindowInsets(j, k, l, i1));
        }

        _cls4()
        {
            this$0 = ViewPager.this;
            super();
        }
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (ItemInfo)obj;
            obj1 = (ItemInfo)obj1;
            return ((ItemInfo) (obj)).position - ((ItemInfo) (obj1)).position;
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements Interpolator
    {

        public final float getInterpolation(float f)
        {
            f--;
            return f * (f * f * f * f) + 1.0F;
        }

        _cls2()
        {
        }
    }

}
