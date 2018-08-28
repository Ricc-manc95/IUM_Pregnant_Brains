// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

// Referenced classes of package android.support.v7.widget:
//            ActionBarBackgroundDrawable, ScrollingTabContainerView

public class ActionBarContainer extends FrameLayout
{

    public View mActionBarView;
    public Drawable mBackground;
    private View mContextView;
    private int mHeight;
    public boolean mIsSplit;
    public boolean mIsStacked;
    public boolean mIsTransitioning;
    public Drawable mSplitBackground;
    public Drawable mStackedBackground;
    public View mTabContainer;

    public ActionBarContainer(Context context)
    {
        this(context, null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeset)
    {
        boolean flag;
        flag = true;
        super(context, attributeset);
        ViewCompat.setBackground(this, new ActionBarBackgroundDrawable(this));
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ActionBar);
        mBackground = context.getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_background);
        mStackedBackground = context.getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_backgroundStacked);
        mHeight = context.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.ActionBar_height, -1);
        if (getId() == 0x7f10003d)
        {
            mIsSplit = true;
            mSplitBackground = context.getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_backgroundSplit);
        }
        context.recycle();
        if (!mIsSplit) goto _L2; else goto _L1
_L1:
        if (mSplitBackground != null)
        {
            flag = false;
        }
_L4:
        setWillNotDraw(flag);
        return;
_L2:
        if (mBackground != null || mStackedBackground != null)
        {
            flag = false;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (mBackground != null && mBackground.isStateful())
        {
            mBackground.setState(getDrawableState());
        }
        if (mStackedBackground != null && mStackedBackground.isStateful())
        {
            mStackedBackground.setState(getDrawableState());
        }
        if (mSplitBackground != null && mSplitBackground.isStateful())
        {
            mSplitBackground.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if (mBackground != null)
        {
            mBackground.jumpToCurrentState();
        }
        if (mStackedBackground != null)
        {
            mStackedBackground.jumpToCurrentState();
        }
        if (mSplitBackground != null)
        {
            mSplitBackground.jumpToCurrentState();
        }
    }

    public void onFinishInflate()
    {
        super.onFinishInflate();
        mActionBarView = findViewById(0x7f1000f0);
        mContextView = findViewById(0x7f1000f1);
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        super.onHoverEvent(motionevent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        return mIsTransitioning || super.onInterceptTouchEvent(motionevent);
    }

    public void onLayout(boolean flag, int i, int j, int k, int l)
    {
label0:
        {
            {
                boolean flag1 = true;
                super.onLayout(flag, i, j, k, l);
                View view = mTabContainer;
                if (view != null && view.getVisibility() != 8)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (view != null && view.getVisibility() != 8)
                {
                    j = getMeasuredHeight();
                    android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                    view.layout(i, j - view.getMeasuredHeight() - layoutparams.bottomMargin, k, j - layoutparams.bottomMargin);
                }
                if (!mIsSplit)
                {
                    break label0;
                }
                if (mSplitBackground != null)
                {
                    mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
                    i = ((flag1) ? 1 : 0);
                } else
                {
                    i = 0;
                }
            }
            if (i != 0)
            {
                invalidate();
            }
            return;
        }
        if (mBackground != null)
        {
            if (mActionBarView.getVisibility() == 0)
            {
                mBackground.setBounds(mActionBarView.getLeft(), mActionBarView.getTop(), mActionBarView.getRight(), mActionBarView.getBottom());
            } else
            if (mContextView != null && mContextView.getVisibility() == 0)
            {
                mBackground.setBounds(mContextView.getLeft(), mContextView.getTop(), mContextView.getRight(), mContextView.getBottom());
            } else
            {
                mBackground.setBounds(0, 0, 0, 0);
            }
            i = 1;
        } else
        {
            i = 0;
        }
        mIsStacked = flag;
        if (flag && mStackedBackground != null)
        {
            mStackedBackground.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            i = ((flag1) ? 1 : 0);
        }
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_129;
        }
    }

    public void onMeasure(int i, int j)
    {
        int l = 1;
        int k = j;
        if (mActionBarView == null)
        {
            k = j;
            if (android.view.View.MeasureSpec.getMode(j) == 0x80000000)
            {
                k = j;
                if (mHeight >= 0)
                {
                    k = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(mHeight, android.view.View.MeasureSpec.getSize(j)), 0x80000000);
                }
            }
        }
        super.onMeasure(i, k);
        if (mActionBarView != null)
        {
            j = android.view.View.MeasureSpec.getMode(k);
            if (mTabContainer != null && mTabContainer.getVisibility() != 8 && j != 0x40000000)
            {
                View view = mActionBarView;
                android.widget.FrameLayout.LayoutParams layoutparams1;
                int i1;
                if (view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    view = mActionBarView;
                    android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                    i = view.getMeasuredHeight();
                    l = layoutparams.topMargin;
                    i = layoutparams.bottomMargin + (i + l);
                } else
                {
                    View view1 = mContextView;
                    i = l;
                    if (view1 != null)
                    {
                        i = l;
                        android.widget.FrameLayout.LayoutParams layoutparams2;
                        if (view1.getVisibility() != 8)
                        {
                            if (view1.getMeasuredHeight() == 0)
                            {
                                i = l;
                            } else
                            {
                                i = 0;
                            }
                        }
                    }
                    if (i == 0)
                    {
                        view1 = mContextView;
                        layoutparams2 = (android.widget.FrameLayout.LayoutParams)view1.getLayoutParams();
                        i = view1.getMeasuredHeight();
                        l = layoutparams2.topMargin;
                        i = layoutparams2.bottomMargin + (i + l);
                    } else
                    {
                        i = 0;
                    }
                }
                if (j == 0x80000000)
                {
                    j = android.view.View.MeasureSpec.getSize(k);
                } else
                {
                    j = 0x7fffffff;
                }
                k = getMeasuredWidth();
                view = mTabContainer;
                layoutparams1 = (android.widget.FrameLayout.LayoutParams)view.getLayoutParams();
                l = view.getMeasuredHeight();
                i1 = layoutparams1.topMargin;
                setMeasuredDimension(k, Math.min(layoutparams1.bottomMargin + (l + i1) + i, j));
                return;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        super.onTouchEvent(motionevent);
        return true;
    }

    public final void setTabContainer(ScrollingTabContainerView scrollingtabcontainerview)
    {
        if (mTabContainer != null)
        {
            removeView(mTabContainer);
        }
        mTabContainer = scrollingtabcontainerview;
        if (scrollingtabcontainerview != null)
        {
            addView(scrollingtabcontainerview);
            scrollingtabcontainerview = scrollingtabcontainerview.getLayoutParams();
            scrollingtabcontainerview.width = -1;
            scrollingtabcontainerview.height = -2;
            ScrollingTabContainerView.setAllowCollapse$51D2ILG_0();
        }
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mBackground != null)
        {
            mBackground.setVisible(flag, false);
        }
        if (mStackedBackground != null)
        {
            mStackedBackground.setVisible(flag, false);
        }
        if (mSplitBackground != null)
        {
            mSplitBackground.setVisible(flag, false);
        }
    }

    public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback)
    {
        return null;
    }

    public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback, int i)
    {
        if (i != 0)
        {
            return super.startActionModeForChild(view, callback, i);
        } else
        {
            return null;
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return drawable == mBackground && !mIsSplit || drawable == mStackedBackground && mIsStacked || drawable == mSplitBackground && mIsSplit || super.verifyDrawable(drawable);
    }
}
