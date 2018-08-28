// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.widget:
//            AbsActionBarView, TintTypedArray, ActionMenuPresenter, ActionMenuView, 
//            ViewUtils

public class ActionBarContextView extends AbsActionBarView
{

    public View mClose;
    private int mCloseItemLayout;
    public View mCustomView;
    public CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    public CharSequence mTitle;
    private LinearLayout mTitleLayout;
    public boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public ActionBarContextView(Context context)
    {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010087);
    }

    public ActionBarContextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        context = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ActionMode, i, 0));
        ViewCompat.setBackground(this, context.getDrawable(android.support.v7.appcompat.R.styleable.ActionMode_background));
        i = android.support.v7.appcompat.R.styleable.ActionMode_titleTextStyle;
        mTitleStyleRes = ((TintTypedArray) (context)).mWrapped.getResourceId(i, 0);
        i = android.support.v7.appcompat.R.styleable.ActionMode_subtitleTextStyle;
        mSubtitleStyleRes = ((TintTypedArray) (context)).mWrapped.getResourceId(i, 0);
        i = android.support.v7.appcompat.R.styleable.ActionMode_height;
        mContentHeight = ((TintTypedArray) (context)).mWrapped.getLayoutDimension(i, 0);
        i = android.support.v7.appcompat.R.styleable.ActionMode_closeItemLayout;
        mCloseItemLayout = ((TintTypedArray) (context)).mWrapped.getResourceId(i, 0x7f050005);
        ((TintTypedArray) (context)).mWrapped.recycle();
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.MarginLayoutParams(-1, -2);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new android.view.ViewGroup.MarginLayoutParams(getContext(), attributeset);
    }

    public final void initForMode(final ActionMode mode)
    {
        boolean flag = false;
        if (mClose != null) goto _L2; else goto _L1
_L1:
        mClose = LayoutInflater.from(getContext()).inflate(mCloseItemLayout, this, false);
        addView(mClose);
_L4:
        mClose.findViewById(0x7f1000ce).setOnClickListener(new _cls1());
        mode = (MenuBuilder)mode.getMenu();
        if (mActionMenuPresenter != null)
        {
            ActionMenuPresenter actionmenupresenter = mActionMenuPresenter;
            actionmenupresenter.hideOverflowMenu();
            if (actionmenupresenter.mActionButtonPopup != null)
            {
                actionmenupresenter.mActionButtonPopup.dismiss();
                boolean flag1 = true;
            }
        }
        mActionMenuPresenter = new ActionMenuPresenter(getContext());
        Object obj = mActionMenuPresenter;
        obj.mReserveOverflow = true;
        obj.mReserveOverflowSet = true;
        obj = new android.view.ViewGroup.LayoutParams(-2, -1);
        ActionMenuPresenter actionmenupresenter1 = mActionMenuPresenter;
        Context context = mPopupContext;
        ((MenuBuilder) (mode)).mPresenters.add(new WeakReference(actionmenupresenter1));
        actionmenupresenter1.initForMenu(context, mode);
        mode.mIsActionItemsStale = true;
        mMenuView = (ActionMenuView)mActionMenuPresenter.getMenuView(this);
        ViewCompat.setBackground(mMenuView, null);
        addView(mMenuView, ((android.view.ViewGroup.LayoutParams) (obj)));
        return;
_L2:
        if (mClose.getParent() == null)
        {
            addView(mClose);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void initTitle()
    {
label0:
        {
            byte byte1 = 8;
            boolean flag = true;
            if (mTitleLayout == null)
            {
                LayoutInflater.from(getContext()).inflate(0x7f050000, this);
                mTitleLayout = (LinearLayout)getChildAt(getChildCount() - 1);
                mTitleView = (TextView)mTitleLayout.findViewById(0x7f1000cc);
                mSubtitleView = (TextView)mTitleLayout.findViewById(0x7f1000cd);
                if (mTitleStyleRes != 0)
                {
                    mTitleView.setTextAppearance(getContext(), mTitleStyleRes);
                }
                if (mSubtitleStyleRes != 0)
                {
                    mSubtitleView.setTextAppearance(getContext(), mSubtitleStyleRes);
                }
            }
            mTitleView.setText(mTitle);
            mSubtitleView.setText(mSubtitle);
            Object obj;
            byte byte0;
            int i;
            if (!TextUtils.isEmpty(mTitle))
            {
                byte0 = 1;
            } else
            {
                byte0 = 0;
            }
            if (TextUtils.isEmpty(mSubtitle))
            {
                flag = false;
            }
            obj = mSubtitleView;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            ((TextView) (obj)).setVisibility(i);
            obj = mTitleLayout;
            if (byte0 == 0)
            {
                byte0 = byte1;
                if (!flag)
                {
                    break label0;
                }
            }
            byte0 = 0;
        }
        ((LinearLayout) (obj)).setVisibility(byte0);
        if (mTitleLayout.getParent() == null)
        {
            addView(mTitleLayout);
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mActionMenuPresenter != null)
        {
            mActionMenuPresenter.hideOverflowMenu();
            ActionMenuPresenter actionmenupresenter = mActionMenuPresenter;
            if (actionmenupresenter.mActionButtonPopup != null)
            {
                actionmenupresenter.mActionButtonPopup.dismiss();
            }
        }
    }

    public volatile boolean onHoverEvent(MotionEvent motionevent)
    {
        return super.onHoverEvent(motionevent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() == 32)
        {
            accessibilityevent.setSource(this);
            accessibilityevent.setClassName(getClass().getName());
            accessibilityevent.setPackageName(getContext().getPackageName());
            accessibilityevent.setContentDescription(mTitle);
            return;
        } else
        {
            super.onInitializeAccessibilityEvent(accessibilityevent);
            return;
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        flag = ViewUtils.isLayoutRtl(this);
        int i1;
        int j1;
        int k1;
        if (flag)
        {
            i1 = k - i - getPaddingRight();
        } else
        {
            i1 = getPaddingLeft();
        }
        j1 = getPaddingTop();
        k1 = l - j - getPaddingTop() - getPaddingBottom();
        j = i1;
        if (mClose != null)
        {
            j = i1;
            if (mClose.getVisibility() != 8)
            {
                Object obj = (android.view.ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
                if (flag)
                {
                    l = ((android.view.ViewGroup.MarginLayoutParams) (obj)).rightMargin;
                } else
                {
                    l = ((android.view.ViewGroup.MarginLayoutParams) (obj)).leftMargin;
                }
                if (flag)
                {
                    j = ((android.view.ViewGroup.MarginLayoutParams) (obj)).leftMargin;
                } else
                {
                    j = ((android.view.ViewGroup.MarginLayoutParams) (obj)).rightMargin;
                }
                if (flag)
                {
                    l = i1 - l;
                } else
                {
                    l = i1 + l;
                }
                l += positionChild(mClose, l, j1, k1, flag);
                if (flag)
                {
                    j = l - j;
                } else
                {
                    j = l + j;
                }
            }
        }
        l = j;
        if (mTitleLayout != null)
        {
            l = j;
            if (mCustomView == null)
            {
                l = j;
                if (mTitleLayout.getVisibility() != 8)
                {
                    l = j + positionChild(mTitleLayout, j, j1, k1, flag);
                }
            }
        }
        if (mCustomView != null)
        {
            positionChild(mCustomView, l, j1, k1, flag);
        }
        if (flag)
        {
            i = getPaddingLeft();
        } else
        {
            i = k - i - getPaddingRight();
        }
        if (mMenuView != null)
        {
            obj = mMenuView;
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            positionChild(((View) (obj)), i, j1, k1, flag);
        }
    }

    protected void onMeasure(int i, int j)
    {
        int i1 = 0x40000000;
        boolean flag = false;
        if (android.view.View.MeasureSpec.getMode(i) != 0x40000000)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)").toString());
        }
        if (android.view.View.MeasureSpec.getMode(j) == 0)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getSimpleName()).append(" can only be used with android:layout_height=\"wrap_content\"").toString());
        }
        int k1 = android.view.View.MeasureSpec.getSize(i);
        int k;
        int l;
        int j1;
        int l1;
        if (mContentHeight > 0)
        {
            k = mContentHeight;
        } else
        {
            k = android.view.View.MeasureSpec.getSize(j);
        }
        l1 = getPaddingTop() + getPaddingBottom();
        i = k1 - getPaddingLeft() - getPaddingRight();
        j1 = k - l1;
        l = android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x80000000);
        j = i;
        if (mClose != null)
        {
            Object obj = mClose;
            ((View) (obj)).measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x80000000), l);
            i = Math.max(0, i - ((View) (obj)).getMeasuredWidth());
            obj = (android.view.ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
            j = ((android.view.ViewGroup.MarginLayoutParams) (obj)).leftMargin;
            j = i - (((android.view.ViewGroup.MarginLayoutParams) (obj)).rightMargin + j);
        }
        i = j;
        if (mMenuView != null)
        {
            i = j;
            if (mMenuView.getParent() == this)
            {
                ActionMenuView actionmenuview = mMenuView;
                actionmenuview.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, 0x80000000), l);
                i = Math.max(0, j - actionmenuview.getMeasuredWidth());
            }
        }
        j = i;
        if (mTitleLayout != null)
        {
            j = i;
            if (mCustomView == null)
            {
                if (mTitleOptional)
                {
                    j = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                    mTitleLayout.measure(j, l);
                    int i2 = mTitleLayout.getMeasuredWidth();
                    Object obj1;
                    if (i2 <= i)
                    {
                        l = 1;
                    } else
                    {
                        l = 0;
                    }
                    j = i;
                    if (l != 0)
                    {
                        j = i - i2;
                    }
                    obj1 = mTitleLayout;
                    if (l != 0)
                    {
                        i = 0;
                    } else
                    {
                        i = 8;
                    }
                    ((LinearLayout) (obj1)).setVisibility(i);
                } else
                {
                    LinearLayout linearlayout = mTitleLayout;
                    linearlayout.measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x80000000), l);
                    j = Math.max(0, i - linearlayout.getMeasuredWidth());
                }
            }
        }
        if (mCustomView != null)
        {
            obj1 = mCustomView.getLayoutParams();
            if (((android.view.ViewGroup.LayoutParams) (obj1)).width != -2)
            {
                i = 0x40000000;
            } else
            {
                i = 0x80000000;
            }
            l = j;
            if (((android.view.ViewGroup.LayoutParams) (obj1)).width >= 0)
            {
                l = Math.min(((android.view.ViewGroup.LayoutParams) (obj1)).width, j);
            }
            if (((android.view.ViewGroup.LayoutParams) (obj1)).height != -2)
            {
                j = i1;
            } else
            {
                j = 0x80000000;
            }
            if (((android.view.ViewGroup.LayoutParams) (obj1)).height >= 0)
            {
                i1 = Math.min(((android.view.ViewGroup.LayoutParams) (obj1)).height, j1);
            } else
            {
                i1 = j1;
            }
            mCustomView.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, i), android.view.View.MeasureSpec.makeMeasureSpec(i1, j));
        }
        if (mContentHeight <= 0)
        {
            l = getChildCount();
            i = 0;
            for (j = ((flag) ? 1 : 0); j < l; j++)
            {
                k = getChildAt(j).getMeasuredHeight() + l1;
                if (k > i)
                {
                    i = k;
                }
            }

            setMeasuredDimension(k1, i);
            return;
        } else
        {
            setMeasuredDimension(k1, k);
            return;
        }
    }

    public volatile boolean onTouchEvent(MotionEvent motionevent)
    {
        return super.onTouchEvent(motionevent);
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
    }

    public final void setCustomView(View view)
    {
        if (mCustomView != null)
        {
            removeView(mCustomView);
        }
        mCustomView = view;
        if (view != null && mTitleLayout != null)
        {
            removeView(mTitleLayout);
            mTitleLayout = null;
        }
        if (view != null)
        {
            addView(view);
        }
        requestLayout();
    }

    public volatile void setVisibility(int i)
    {
        super.setVisibility(i);
    }

    public final volatile ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long l)
    {
        return super.setupAnimatorToVisibility(i, l);
    }

    public boolean shouldDelayChildPressedState()
    {
        return false;
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final ActionMode val$mode;

        public final void onClick(View view)
        {
            mode.finish();
        }

        _cls1()
        {
            mode = actionmode;
            super();
        }
    }

}
