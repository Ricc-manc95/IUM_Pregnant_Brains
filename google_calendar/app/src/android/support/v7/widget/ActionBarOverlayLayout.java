// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.OverScroller;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.widget:
//            DecorContentParent, ContentFrameLayout, ActionBarContainer, DecorToolbar, 
//            Toolbar, ToolbarWidgetWrapper, ViewUtils

public class ActionBarOverlayLayout extends ViewGroup
    implements DecorContentParent
{
    public static interface ActionBarVisibilityCallback
    {

        public abstract void enableContentAnimations(boolean flag);

        public abstract void hideForSystem();

        public abstract void onContentScrollStarted();

        public abstract void onContentScrollStopped();

        public abstract void onWindowVisibilityChanged(int i);

        public abstract void showForSystem();
    }

    public static final class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public LayoutParams(int i, int j)
        {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }
    }


    private static final int ATTRS[] = {
        0x7f010082, 0x1010059
    };
    private int mActionBarHeight;
    public ActionBarContainer mActionBarTop;
    public ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    public boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    public ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    public boolean mHasNonEmbeddedTabs;
    public boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastBaseInnerInsets;
    private final Rect mLastInnerInsets;
    public int mLastSystemUiVisibility;
    public boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    public final AnimatorListenerAdapter mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    public int mWindowVisibility;

    public ActionBarOverlayLayout(Context context)
    {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mWindowVisibility = 0;
        mBaseContentInsets = new Rect();
        mLastBaseContentInsets = new Rect();
        mContentInsets = new Rect();
        mBaseInnerInsets = new Rect();
        mLastBaseInnerInsets = new Rect();
        mInnerInsets = new Rect();
        mLastInnerInsets = new Rect();
        mTopAnimatorListener = new _cls1();
        mRemoveActionBarHideOffset = new _cls2();
        mAddActionBarHideOffset = new _cls3();
        init(context);
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    private static boolean applyInsets(View view, Rect rect, boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        flag = false;
        view = (LayoutParams)view.getLayoutParams();
        if (((LayoutParams) (view)).leftMargin != rect.left)
        {
            view.leftMargin = rect.left;
            flag = true;
        }
        if (((LayoutParams) (view)).topMargin != rect.top)
        {
            view.topMargin = rect.top;
            flag = true;
        }
        if (((LayoutParams) (view)).rightMargin != rect.right)
        {
            view.rightMargin = rect.right;
            flag = true;
        }
        if (flag2 && ((LayoutParams) (view)).bottomMargin != rect.bottom)
        {
            view.bottomMargin = rect.bottom;
            return true;
        } else
        {
            return flag;
        }
    }

    private final void init(Context context)
    {
        boolean flag1 = true;
        TypedArray typedarray = getContext().getTheme().obtainStyledAttributes(ATTRS);
        mActionBarHeight = typedarray.getDimensionPixelSize(0, 0);
        mWindowContentOverlay = typedarray.getDrawable(1);
        boolean flag;
        if (mWindowContentOverlay == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setWillNotDraw(flag);
        typedarray.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        mIgnoreWindowContentOverlay = flag;
        mFlingEstimator = new OverScroller(context);
    }

    private final void pullChildren()
    {
        if (mContent == null)
        {
            mContent = (ContentFrameLayout)findViewById(0x7f100000);
            mActionBarTop = (ActionBarContainer)findViewById(0x7f1000ef);
            Object obj = findViewById(0x7f1000f0);
            if (obj instanceof DecorToolbar)
            {
                obj = (DecorToolbar)obj;
            } else
            if (obj instanceof Toolbar)
            {
                obj = (Toolbar)obj;
                if (((Toolbar) (obj)).mWrapper == null)
                {
                    obj.mWrapper = new ToolbarWidgetWrapper(((Toolbar) (obj)), true);
                }
                obj = ((Toolbar) (obj)).mWrapper;
            } else
            {
                throw new IllegalStateException((new StringBuilder("Can't make a decor toolbar out of ")).append(obj.getClass().getSimpleName()).toString());
            }
            mDecorToolbar = ((DecorToolbar) (obj));
        }
    }

    public static void setShowingForActionMode$51D2ILG_0()
    {
    }

    public final boolean canShowOverflowMenu()
    {
        pullChildren();
        return mDecorToolbar.canShowOverflowMenu();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public final void dismissPopups()
    {
        pullChildren();
        mDecorToolbar.dismissPopupMenus();
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if (mWindowContentOverlay != null && !mIgnoreWindowContentOverlay)
        {
            int i;
            if (mActionBarTop.getVisibility() == 0)
            {
                i = (int)((float)mActionBarTop.getBottom() + mActionBarTop.getTranslationY() + 0.5F);
            } else
            {
                i = 0;
            }
            mWindowContentOverlay.setBounds(0, i, getWidth(), mWindowContentOverlay.getIntrinsicHeight() + i);
            mWindowContentOverlay.draw(canvas);
        }
    }

    protected boolean fitSystemWindows(Rect rect)
    {
        pullChildren();
        ViewCompat.getWindowSystemUiVisibility(this);
        boolean flag = applyInsets(mActionBarTop, rect, true, true, false, true);
        mBaseInnerInsets.set(rect);
        rect = mBaseInnerInsets;
        Rect rect1 = mBaseContentInsets;
        if (ViewUtils.sComputeFitSystemWindowsMethod != null)
        {
            try
            {
                ViewUtils.sComputeFitSystemWindowsMethod.invoke(this, new Object[] {
                    rect, rect1
                });
            }
            // Misplaced declaration of an exception variable
            catch (Rect rect) { }
        }
        if (!mLastBaseInnerInsets.equals(mBaseInnerInsets))
        {
            mLastBaseInnerInsets.set(mBaseInnerInsets);
            flag = true;
        }
        if (!mLastBaseContentInsets.equals(mBaseContentInsets))
        {
            mLastBaseContentInsets.set(mBaseContentInsets);
            flag = true;
        }
        if (flag)
        {
            requestLayout();
        }
        return true;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-1, -1);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public int getNestedScrollAxes()
    {
        return mParentHelper.mNestedScrollAxes;
    }

    public final void haltActionBarHideOffsetAnimations()
    {
        removeCallbacks(mRemoveActionBarHideOffset);
        removeCallbacks(mAddActionBarHideOffset);
        if (mCurrentActionBarTopAnimator != null)
        {
            mCurrentActionBarTopAnimator.cancel();
        }
    }

    public final boolean hideOverflowMenu()
    {
        pullChildren();
        return mDecorToolbar.hideOverflowMenu();
    }

    public final void initFeature(int i)
    {
        boolean flag = true;
        pullChildren();
        switch (i)
        {
        default:
            return;

        case 2: // '\002'
            mDecorToolbar.initProgress();
            return;

        case 5: // '\005'
            mDecorToolbar.initIndeterminateProgress();
            return;

        case 109: // 'm'
            mOverlayMode = true;
            break;
        }
        if (getContext().getApplicationInfo().targetSdkVersion >= 19)
        {
            flag = false;
        }
        mIgnoreWindowContentOverlay = flag;
    }

    public final boolean isOverflowMenuShowPending()
    {
        pullChildren();
        return mDecorToolbar.isOverflowMenuShowPending();
    }

    public final boolean isOverflowMenuShowing()
    {
        pullChildren();
        return mDecorToolbar.isOverflowMenuShowing();
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        init(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        j = getChildCount();
        k = getPaddingLeft();
        getPaddingRight();
        l = getPaddingTop();
        getPaddingBottom();
        for (i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if (view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                int i1 = view.getMeasuredWidth();
                int j1 = view.getMeasuredHeight();
                int k1 = layoutparams.leftMargin + k;
                int l1 = layoutparams.topMargin + l;
                view.layout(k1, l1, i1 + k1, j1 + l1);
            }
        }

    }

    protected void onMeasure(int i, int j)
    {
        pullChildren();
        measureChildWithMargins(mActionBarTop, i, 0, j, 0);
        Object obj = (LayoutParams)mActionBarTop.getLayoutParams();
        int i2 = Math.max(0, mActionBarTop.getMeasuredWidth() + ((LayoutParams) (obj)).leftMargin + ((LayoutParams) (obj)).rightMargin);
        int k = mActionBarTop.getMeasuredHeight();
        int l = ((LayoutParams) (obj)).topMargin;
        int l1 = Math.max(0, ((LayoutParams) (obj)).bottomMargin + (k + l));
        int k1 = View.combineMeasuredStates(0, mActionBarTop.getMeasuredState());
        int j1;
        if ((ViewCompat.getWindowSystemUiVisibility(this) & 0x100) != 0)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (l != 0)
        {
            int i1 = mActionBarHeight;
            k = i1;
            if (mHasNonEmbeddedTabs)
            {
                k = i1;
                if (mActionBarTop.mTabContainer != null)
                {
                    k = i1 + mActionBarHeight;
                }
            }
        } else
        if (mActionBarTop.getVisibility() != 8)
        {
            k = mActionBarTop.getMeasuredHeight();
        } else
        {
            k = 0;
        }
        mContentInsets.set(mBaseContentInsets);
        mInnerInsets.set(mBaseInnerInsets);
        if (!mOverlayMode && l == 0)
        {
            obj = mContentInsets;
            obj.top = k + ((Rect) (obj)).top;
            obj = mContentInsets;
            obj.bottom = ((Rect) (obj)).bottom;
        } else
        {
            Rect rect = mInnerInsets;
            rect.top = k + rect.top;
            rect = mInnerInsets;
            rect.bottom = rect.bottom;
        }
        applyInsets(mContent, mContentInsets, true, true, true, true);
        if (!mLastInnerInsets.equals(mInnerInsets))
        {
            mLastInnerInsets.set(mInnerInsets);
            mContent.dispatchFitSystemWindows(mInnerInsets);
        }
        measureChildWithMargins(mContent, i, 0, j, 0);
        obj = (LayoutParams)mContent.getLayoutParams();
        k = Math.max(i2, mContent.getMeasuredWidth() + ((LayoutParams) (obj)).leftMargin + ((LayoutParams) (obj)).rightMargin);
        l = mContent.getMeasuredHeight();
        j1 = ((LayoutParams) (obj)).topMargin;
        l = Math.max(l1, ((LayoutParams) (obj)).bottomMargin + (l + j1));
        j1 = View.combineMeasuredStates(k1, mContent.getMeasuredState());
        k1 = getPaddingLeft();
        l1 = getPaddingRight();
        l = Math.max(l + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(k + (k1 + l1), getSuggestedMinimumWidth()), i, j1), View.resolveSizeAndState(l, j, j1 << 16));
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        if (!mHideOnContentScroll || !flag)
        {
            return false;
        }
        mFlingEstimator.fling(0, 0, 0, (int)f1, 0, 0, 0x80000000, 0x7fffffff);
        if (mFlingEstimator.getFinalY() > mActionBarTop.getHeight())
        {
            haltActionBarHideOffsetAnimations();
            mAddActionBarHideOffset.run();
        } else
        {
            haltActionBarHideOffsetAnimations();
            mRemoveActionBarHideOffset.run();
        }
        mAnimatingForFling = true;
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        return false;
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        mHideOnContentScrollReference = mHideOnContentScrollReference + j;
        setActionBarHideOffset(mHideOnContentScrollReference);
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        mParentHelper.mNestedScrollAxes = i;
        if (mActionBarTop != null)
        {
            i = -(int)mActionBarTop.getTranslationY();
        } else
        {
            i = 0;
        }
        mHideOnContentScrollReference = i;
        haltActionBarHideOffsetAnimations();
        if (mActionBarVisibilityCallback != null)
        {
            mActionBarVisibilityCallback.onContentScrollStarted();
        }
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        if ((i & 2) == 0 || mActionBarTop.getVisibility() != 0)
        {
            return false;
        } else
        {
            return mHideOnContentScroll;
        }
    }

    public void onStopNestedScroll(View view)
    {
        if (mHideOnContentScroll && !mAnimatingForFling)
        {
            if (mHideOnContentScrollReference <= mActionBarTop.getHeight())
            {
                haltActionBarHideOffsetAnimations();
                postDelayed(mRemoveActionBarHideOffset, 600L);
            } else
            {
                haltActionBarHideOffsetAnimations();
                postDelayed(mAddActionBarHideOffset, 600L);
            }
        }
        if (mActionBarVisibilityCallback != null)
        {
            mActionBarVisibilityCallback.onContentScrollStopped();
        }
    }

    public void onWindowSystemUiVisibilityChanged(int i)
    {
        boolean flag2 = true;
        super.onWindowSystemUiVisibilityChanged(i);
        pullChildren();
        int j = mLastSystemUiVisibility;
        mLastSystemUiVisibility = i;
        boolean flag;
        boolean flag1;
        if ((i & 4) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if ((i & 0x100) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (mActionBarVisibilityCallback != null)
        {
            ActionBarVisibilityCallback actionbarvisibilitycallback = mActionBarVisibilityCallback;
            if (flag1)
            {
                flag2 = false;
            }
            actionbarvisibilitycallback.enableContentAnimations(flag2);
            if (flag || !flag1)
            {
                mActionBarVisibilityCallback.showForSystem();
            } else
            {
                mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if (((j ^ i) & 0x100) != 0 && mActionBarVisibilityCallback != null)
        {
            ViewCompat.requestApplyInsets(this);
        }
    }

    protected void onWindowVisibilityChanged(int i)
    {
        super.onWindowVisibilityChanged(i);
        mWindowVisibility = i;
        if (mActionBarVisibilityCallback != null)
        {
            mActionBarVisibilityCallback.onWindowVisibilityChanged(i);
        }
    }

    public void setActionBarHideOffset(int i)
    {
        haltActionBarHideOffsetAnimations();
        i = Math.max(0, Math.min(i, mActionBarTop.getHeight()));
        mActionBarTop.setTranslationY(-i);
    }

    public void setIcon(int i)
    {
        pullChildren();
        mDecorToolbar.setIcon(i);
    }

    public void setLogo(int i)
    {
        pullChildren();
        mDecorToolbar.setLogo(i);
    }

    public final void setMenu(Menu menu, android.support.v7.view.menu.MenuPresenter.Callback callback)
    {
        pullChildren();
        mDecorToolbar.setMenu(menu, callback);
    }

    public final void setMenuPrepared()
    {
        pullChildren();
        mDecorToolbar.setMenuPrepared();
    }

    public void setUiOptions(int i)
    {
    }

    public final void setWindowCallback(android.view.Window.Callback callback)
    {
        pullChildren();
        mDecorToolbar.setWindowCallback(callback);
    }

    public final void setWindowTitle(CharSequence charsequence)
    {
        pullChildren();
        mDecorToolbar.setWindowTitle(charsequence);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    public final boolean showOverflowMenu()
    {
        pullChildren();
        return mDecorToolbar.showOverflowMenu();
    }


    private class _cls1 extends AnimatorListenerAdapter
    {

        private final ActionBarOverlayLayout this$0;

        public final void onAnimationCancel(Animator animator)
        {
            mCurrentActionBarTopAnimator = null;
            mAnimatingForFling = false;
        }

        public final void onAnimationEnd(Animator animator)
        {
            mCurrentActionBarTopAnimator = null;
            mAnimatingForFling = false;
        }

        _cls1()
        {
            this$0 = ActionBarOverlayLayout.this;
            super();
        }
    }


    private class _cls2
        implements Runnable
    {

        private final ActionBarOverlayLayout this$0;

        public final void run()
        {
            haltActionBarHideOffsetAnimations();
            mCurrentActionBarTopAnimator = mActionBarTop.animate().translationY(0.0F).setListener(mTopAnimatorListener);
        }

        _cls2()
        {
            this$0 = ActionBarOverlayLayout.this;
            super();
        }
    }


    private class _cls3
        implements Runnable
    {

        private final ActionBarOverlayLayout this$0;

        public final void run()
        {
            haltActionBarHideOffsetAnimations();
            mCurrentActionBarTopAnimator = mActionBarTop.animate().translationY(-mActionBarTop.getHeight()).setListener(mTopAnimatorListener);
        }

        _cls3()
        {
            this$0 = ActionBarOverlayLayout.this;
            super();
        }
    }

}
