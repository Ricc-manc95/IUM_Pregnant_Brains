// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.widget:
//            LinearLayoutCompat, ActionMenuPresenter, ViewUtils

public class ActionMenuView extends LinearLayoutCompat
    implements android.support.v7.view.menu.MenuBuilder.ItemInvoker, MenuView
{
    public static interface ActionMenuChildView
    {

        public abstract boolean needsDividerAfter();

        public abstract boolean needsDividerBefore();
    }

    static final class ActionMenuPresenterCallback
        implements android.support.v7.view.menu.MenuPresenter.Callback
    {

        public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
        }

        public final boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            return false;
        }

        ActionMenuPresenterCallback()
        {
        }
    }

    public static final class LayoutParams extends LinearLayoutCompat.LayoutParams
    {

        public int cellsUsed;
        public boolean expandable;
        public boolean expanded;
        public int extraPixels;
        public boolean isOverflowButton;
        public boolean preventEdgeOffset;

        public LayoutParams(int i, int j)
        {
            super(-2, -2);
            isOverflowButton = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            isOverflowButton = layoutparams.isOverflowButton;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }
    }

    final class MenuBuilderCallback
        implements android.support.v7.view.menu.MenuBuilder.Callback
    {

        private final ActionMenuView this$0;

        public final boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
        {
            return mOnMenuItemClickListener != null && mOnMenuItemClickListener.onMenuItemClick(menuitem);
        }

        public final void onMenuModeChange(MenuBuilder menubuilder)
        {
            if (mMenuBuilderCallback != null)
            {
                mMenuBuilderCallback.onMenuModeChange(menubuilder);
            }
        }

        MenuBuilderCallback()
        {
            this$0 = ActionMenuView.this;
            super();
        }
    }

    public static interface OnMenuItemClickListener
    {

        public abstract boolean onMenuItemClick(MenuItem menuitem);
    }


    public android.support.v7.view.menu.MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    public MenuBuilder mMenu;
    public android.support.v7.view.menu.MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    public OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    public ActionMenuPresenter mPresenter;
    public boolean mReserveOverflow;

    public ActionMenuView(Context context)
    {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        super.mBaselineAligned = false;
        float f = context.getResources().getDisplayMetrics().density;
        mMinCellSize = (int)(56F * f);
        mGeneratedItemPadding = (int)(f * 4F);
        mPopupContext = context;
        mPopupTheme = 0;
    }

    private final boolean hasSupportDividerBeforeChildAt(int i)
    {
        boolean flag1 = false;
        if (i == 0)
        {
            return false;
        }
        View view = getChildAt(i - 1);
        View view1 = getChildAt(i);
        boolean flag = flag1;
        if (i < getChildCount())
        {
            flag = flag1;
            if (view instanceof ActionMenuChildView)
            {
                flag = ((ActionMenuChildView)view).needsDividerAfter() | false;
            }
        }
        if (i > 0 && (view1 instanceof ActionMenuChildView))
        {
            return ((ActionMenuChildView)view1).needsDividerBefore() | flag;
        } else
        {
            return flag;
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams != null && (layoutparams instanceof LayoutParams);
    }

    public final void dismissPopupMenus()
    {
        if (mPresenter != null)
        {
            ActionMenuPresenter actionmenupresenter = mPresenter;
            actionmenupresenter.hideOverflowMenu();
            boolean flag1;
            if (actionmenupresenter.mActionButtonPopup != null)
            {
                actionmenupresenter.mActionButtonPopup.dismiss();
                boolean flag = true;
            } else
            {
                flag1 = false;
            }
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        return false;
    }

    protected final LinearLayoutCompat.LayoutParams generateDefaultLayoutParams()
    {
        LayoutParams layoutparams = new LayoutParams(-2, -2);
        layoutparams.gravity = 16;
        return layoutparams;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        LayoutParams layoutparams = new LayoutParams(-2, -2);
        layoutparams.gravity = 16;
        return layoutparams;
    }

    protected final LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams != null)
        {
            if (layoutparams instanceof LayoutParams)
            {
                layoutparams = new LayoutParams((LayoutParams)layoutparams);
            } else
            {
                layoutparams = new LayoutParams(layoutparams);
            }
            if (((LayoutParams) (layoutparams)).gravity <= 0)
            {
                layoutparams.gravity = 16;
            }
            return layoutparams;
        } else
        {
            layoutparams = new LayoutParams(-2, -2);
            layoutparams.gravity = 16;
            return layoutparams;
        }
    }

    public final volatile LinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return (LayoutParams)generateLayoutParams(attributeset);
    }

    protected final volatile LinearLayoutCompat.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public final Menu getMenu()
    {
        if (mMenu == null)
        {
            Object obj = getContext();
            mMenu = new MenuBuilder(((Context) (obj)));
            mMenu.setCallback(new MenuBuilderCallback());
            mPresenter = new ActionMenuPresenter(((Context) (obj)));
            obj = mPresenter;
            obj.mReserveOverflow = true;
            obj.mReserveOverflowSet = true;
            ActionMenuPresenter actionmenupresenter = mPresenter;
            Context context;
            if (mActionMenuPresenterCallback != null)
            {
                obj = mActionMenuPresenterCallback;
            } else
            {
                obj = new ActionMenuPresenterCallback();
            }
            actionmenupresenter.mCallback = ((android.support.v7.view.menu.MenuPresenter.Callback) (obj));
            obj = mMenu;
            actionmenupresenter = mPresenter;
            context = mPopupContext;
            ((MenuBuilder) (obj)).mPresenters.add(new WeakReference(actionmenupresenter));
            actionmenupresenter.initForMenu(context, ((MenuBuilder) (obj)));
            obj.mIsActionItemsStale = true;
            obj = mPresenter;
            obj.mMenuView = this;
            mMenu = ((ActionMenuPresenter) (obj)).mMenu;
        }
        return mMenu;
    }

    public final void initialize(MenuBuilder menubuilder)
    {
        mMenu = menubuilder;
    }

    public final boolean invokeItem(MenuItemImpl menuitemimpl)
    {
        return mMenu.performItemAction(menuitemimpl, null, 0);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if (mPresenter != null)
        {
            mPresenter.updateMenuView(false);
            if (mPresenter.isOverflowMenuShowing())
            {
                mPresenter.hideOverflowMenu();
                mPresenter.showOverflowMenu();
            }
        }
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mPresenter != null)
        {
            ActionMenuPresenter actionmenupresenter = mPresenter;
            actionmenupresenter.hideOverflowMenu();
            boolean flag1;
            if (actionmenupresenter.mActionButtonPopup != null)
            {
                actionmenupresenter.mActionButtonPopup.dismiss();
                boolean flag = true;
            } else
            {
                flag1 = false;
            }
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if (mFormatItems) goto _L2; else goto _L1
_L1:
        super.onLayout(flag, i, j, k, l);
_L4:
        return;
_L2:
        int i3 = getChildCount();
        int l2 = (l - j) / 2;
        int j3 = super.mDividerWidth;
        j = 0;
        int i1 = k - i - getPaddingRight() - getPaddingLeft();
        l = 0;
        flag = ViewUtils.isLayoutRtl(this);
        int j1 = 0;
        while (j1 < i3) 
        {
            View view = getChildAt(j1);
            LayoutParams layoutparams1;
            if (view.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
                if (layoutparams.isOverflowButton)
                {
                    int k1 = view.getMeasuredWidth();
                    l = k1;
                    if (hasSupportDividerBeforeChildAt(j1))
                    {
                        l = k1 + j3;
                    }
                    int k3 = view.getMeasuredHeight();
                    int j2;
                    int i4;
                    if (flag)
                    {
                        k1 = getPaddingLeft();
                        k1 = layoutparams.leftMargin + k1;
                        j2 = k1 + l;
                    } else
                    {
                        j2 = getWidth() - getPaddingRight() - layoutparams.rightMargin;
                        k1 = j2 - l;
                    }
                    i4 = l2 - k3 / 2;
                    view.layout(k1, i4, j2, k3 + i4);
                    l = i1 - l;
                    k1 = 1;
                    i1 = j;
                    j = k1;
                } else
                {
                    int k2 = view.getMeasuredWidth();
                    int l3 = layoutparams.leftMargin;
                    int j4 = layoutparams.rightMargin;
                    hasSupportDividerBeforeChildAt(j1);
                    int l1 = j + 1;
                    i1 -= j4 + (k2 + l3);
                    j = l;
                    l = i1;
                    i1 = l1;
                }
            } else
            {
                int i2 = j;
                j = l;
                l = i1;
                i1 = i2;
            }
            k1 = j1 + 1;
            j1 = i1;
            i1 = l;
            l = j;
            j = j1;
            j1 = k1;
        }
        if (i3 == 1 && l == 0)
        {
            view = getChildAt(0);
            j = view.getMeasuredWidth();
            l = view.getMeasuredHeight();
            i = (k - i) / 2 - j / 2;
            k = l2 - l / 2;
            view.layout(i, k, j + i, l + k);
            return;
        }
        if (l != 0)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        i = j - i;
        if (i > 0)
        {
            i = i1 / i;
        } else
        {
            i = 0;
        }
        k = Math.max(0, i);
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        i = getWidth() - getPaddingRight();
        j = 0;
        while (j < i3) 
        {
            view = getChildAt(j);
            layoutparams1 = (LayoutParams)view.getLayoutParams();
            if (view.getVisibility() != 8 && !layoutparams1.isOverflowButton)
            {
                i -= layoutparams1.rightMargin;
                l = view.getMeasuredWidth();
                i1 = view.getMeasuredHeight();
                j1 = l2 - i1 / 2;
                view.layout(i - l, j1, i, i1 + j1);
                i -= layoutparams1.leftMargin + l + k;
            }
            j++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        i = getPaddingLeft();
        j = 0;
        while (j < i3) 
        {
            view = getChildAt(j);
            layoutparams1 = (LayoutParams)view.getLayoutParams();
            if (view.getVisibility() != 8 && !layoutparams1.isOverflowButton)
            {
                i += layoutparams1.leftMargin;
                l = view.getMeasuredWidth();
                i1 = view.getMeasuredHeight();
                j1 = l2 - i1 / 2;
                view.layout(i, j1, i + l, i1 + j1);
                i = layoutparams1.rightMargin + l + k + i;
            }
            j++;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected void onMeasure(int i, int j)
    {
        int i1;
        int j3;
        int l3;
        int i4;
        int j4;
        int k4;
        boolean flag2 = mFormatItems;
        int k;
        boolean flag;
        if (android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mFormatItems = flag;
        if (flag2 != mFormatItems)
        {
            mFormatItemsWidth = 0;
        }
        k = android.view.View.MeasureSpec.getSize(i);
        if (mFormatItems && mMenu != null && k != mFormatItemsWidth)
        {
            mFormatItemsWidth = k;
            mMenu.onItemsChanged(true);
        }
        i1 = getChildCount();
        if (!mFormatItems || i1 <= 0) goto _L2; else goto _L1
_L1:
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int l4;
        int i5;
        long l5;
        i4 = android.view.View.MeasureSpec.getMode(j);
        i = android.view.View.MeasureSpec.getSize(i);
        l3 = android.view.View.MeasureSpec.getSize(j);
        k = getPaddingLeft();
        i1 = getPaddingRight();
        j3 = getPaddingTop() + getPaddingBottom();
        j4 = getChildMeasureSpec(j, j3, -2);
        k4 = i - (k + i1);
        i = k4 / mMinCellSize;
        j = mMinCellSize;
        if (i == 0)
        {
            setMeasuredDimension(k4, 0);
            return;
        }
        l4 = mMinCellSize + (k4 % j) / i;
        j = 0;
        l1 = 0;
        k1 = 0;
        i2 = 0;
        j1 = 0;
        l5 = 0L;
        i5 = getChildCount();
        j2 = 0;
_L11:
        if (j2 >= i5) goto _L4; else goto _L3
_L3:
        Object obj1;
        LayoutParams layoutparams;
        int k2;
        boolean flag1;
        obj1 = getChildAt(j2);
        if (((View) (obj1)).getVisibility() == 8)
        {
            break MISSING_BLOCK_LABEL_1652;
        }
        flag1 = obj1 instanceof ActionMenuItemView;
        k2 = i2 + 1;
        if (flag1)
        {
            ((View) (obj1)).setPadding(mGeneratedItemPadding, 0, mGeneratedItemPadding, 0);
        }
        layoutparams = (LayoutParams)((View) (obj1)).getLayoutParams();
        layoutparams.expanded = false;
        layoutparams.extraPixels = 0;
        layoutparams.cellsUsed = 0;
        layoutparams.expandable = false;
        layoutparams.leftMargin = 0;
        layoutparams.rightMargin = 0;
        if (!flag1) goto _L6; else goto _L5
_L5:
        int l;
        if (!TextUtils.isEmpty(((ActionMenuItemView)obj1).getText()))
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (l == 0) goto _L6; else goto _L7
_L7:
        flag1 = true;
_L12:
        layoutparams.preventEdgeOffset = flag1;
        ActionMenuItemView actionmenuitemview;
        LayoutParams layoutparams1;
        int l2;
        int j5;
        long l6;
        if (layoutparams.isOverflowButton)
        {
            l = 1;
        } else
        {
            l = i;
        }
        layoutparams1 = (LayoutParams)((View) (obj1)).getLayoutParams();
        j5 = android.view.View.MeasureSpec.makeMeasureSpec(android.view.View.MeasureSpec.getSize(j4) - j3, android.view.View.MeasureSpec.getMode(j4));
        if (obj1 instanceof ActionMenuItemView)
        {
            actionmenuitemview = (ActionMenuItemView)obj1;
        } else
        {
            actionmenuitemview = null;
        }
        if (actionmenuitemview == null) goto _L9; else goto _L8
_L8:
        if (!TextUtils.isEmpty(actionmenuitemview.getText()))
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 == 0) goto _L9; else goto _L10
_L10:
        i1 = 1;
_L13:
label0:
        {
            l2 = 0;
            i2 = l2;
            if (l <= 0)
            {
                break label0;
            }
            if (i1 != 0)
            {
                i2 = l2;
                if (l < 2)
                {
                    break label0;
                }
            }
            ((View) (obj1)).measure(android.view.View.MeasureSpec.makeMeasureSpec(l4 * l, 0x80000000), j5);
            l2 = ((View) (obj1)).getMeasuredWidth();
            i2 = l2 / l4;
            l = i2;
            if (l2 % l4 != 0)
            {
                l = i2 + 1;
            }
            i2 = l;
            if (i1 != 0)
            {
                i2 = l;
                if (l < 2)
                {
                    i2 = 2;
                }
            }
        }
        if (!layoutparams1.isOverflowButton && i1 != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        layoutparams1.expandable = flag1;
        layoutparams1.cellsUsed = i2;
        ((View) (obj1)).measure(android.view.View.MeasureSpec.makeMeasureSpec(i2 * l4, 0x40000000), j5);
        l1 = Math.max(l1, i2);
        float f;
        float f1;
        float f2;
        Object obj;
        int i3;
        int k3;
        long l7;
        long l8;
        if (layoutparams.expandable)
        {
            l = k1 + 1;
        } else
        {
            l = k1;
        }
        if (layoutparams.isOverflowButton)
        {
            i1 = 1;
        } else
        {
            i1 = j1;
        }
        i -= i2;
        k1 = Math.max(j, ((View) (obj1)).getMeasuredHeight());
        if (i2 == 1)
        {
            l6 = 1 << j2;
            j = k2;
            i2 = k1;
            l5 = l6 | l5;
            j1 = i1;
            k1 = l;
            i1 = i2;
            l = l1;
        } else
        {
            j1 = i1;
            j = k2;
            i1 = k1;
            k1 = l;
            l = l1;
        }
_L36:
        j2++;
        i2 = j;
        l1 = l;
        j = i1;
          goto _L11
_L6:
        flag1 = false;
          goto _L12
_L9:
        i1 = 0;
          goto _L13
_L4:
        if (j1 != 0 && i2 == 2)
        {
            k2 = 1;
        } else
        {
            k2 = 0;
        }
        l = 0;
        j2 = i;
        i = l;
_L27:
        l8 = l5;
        if (k1 <= 0) goto _L15; else goto _L14
_L14:
        l8 = l5;
        if (j2 <= 0) goto _L15; else goto _L16
_L16:
        l = 0x7fffffff;
        l7 = 0L;
        i1 = 0;
        i3 = 0;
_L23:
        if (i3 >= i5) goto _L18; else goto _L17
_L17:
        obj = (LayoutParams)getChildAt(i3).getLayoutParams();
        if (!((LayoutParams) (obj)).expandable) goto _L20; else goto _L19
_L19:
        if (((LayoutParams) (obj)).cellsUsed >= l) goto _L22; else goto _L21
_L21:
        i1 = ((LayoutParams) (obj)).cellsUsed;
        l7 = 1L << i3;
        l = 1;
_L25:
        k3 = i3 + 1;
        i3 = i1;
        i1 = l;
        l = i3;
        i3 = k3;
          goto _L23
_L22:
        if (((LayoutParams) (obj)).cellsUsed != l) goto _L20; else goto _L24
_L24:
        l7 |= 1L << i3;
        k3 = i1 + 1;
        i1 = l;
        l = k3;
          goto _L25
_L18:
        l5 |= l7;
        l8 = l5;
        if (i1 > j2) goto _L15; else goto _L26
_L26:
        i = j2;
        i1 = 0;
        while (i1 < i5) 
        {
            obj = getChildAt(i1);
            obj1 = (LayoutParams)((View) (obj)).getLayoutParams();
            if (((long)(1 << i1) & l7) == 0L)
            {
                if (((LayoutParams) (obj1)).cellsUsed == l + 1)
                {
                    l5 |= 1 << i1;
                }
            } else
            {
                if (k2 != 0 && ((LayoutParams) (obj1)).preventEdgeOffset && i == 1)
                {
                    ((View) (obj)).setPadding(mGeneratedItemPadding + l4, 0, mGeneratedItemPadding, 0);
                }
                obj1.cellsUsed = ((LayoutParams) (obj1)).cellsUsed + 1;
                obj1.expanded = true;
                i--;
            }
            i1++;
        }
        j2 = i;
        i = 1;
          goto _L27
_L15:
        if (j1 == 0 && i2 == 1)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        if (j2 <= 0 || l8 == 0L || j2 >= i2 - 1 && l == 0 && l1 <= 1) goto _L29; else goto _L28
_L28:
        f2 = Long.bitCount(l8);
        f1 = f2;
        if (l != 0) goto _L31; else goto _L30
_L30:
        f = f2;
        if ((1L & l8) != 0L)
        {
            f = f2;
            if (!((LayoutParams)getChildAt(0).getLayoutParams()).preventEdgeOffset)
            {
                f = f2 - 0.5F;
            }
        }
        f1 = f;
        if (((long)(1 << i5 - 1) & l8) == 0L) goto _L31; else goto _L32
_L32:
        f1 = f;
        if (((LayoutParams)getChildAt(i5 - 1).getLayoutParams()).preventEdgeOffset) goto _L31; else goto _L33
_L33:
        f -= 0.5F;
_L35:
        if (f > 0.0F)
        {
            l = (int)((float)(j2 * l4) / f);
        } else
        {
            l = 0;
        }
        i1 = 0;
        do
        {
            j1 = i;
            if (i1 >= i5)
            {
                break;
            }
            if (((long)(1 << i1) & l8) != 0L)
            {
                obj = getChildAt(i1);
                obj1 = (LayoutParams)((View) (obj)).getLayoutParams();
                if (obj instanceof ActionMenuItemView)
                {
                    obj1.extraPixels = l;
                    obj1.expanded = true;
                    if (i1 == 0 && !((LayoutParams) (obj1)).preventEdgeOffset)
                    {
                        obj1.leftMargin = -l / 2;
                    }
                    i = 1;
                } else
                if (((LayoutParams) (obj1)).isOverflowButton)
                {
                    obj1.extraPixels = l;
                    obj1.expanded = true;
                    obj1.rightMargin = -l / 2;
                    i = 1;
                } else
                {
                    if (i1 != 0)
                    {
                        obj1.leftMargin = l / 2;
                    }
                    if (i1 != i5 - 1)
                    {
                        obj1.rightMargin = l / 2;
                    }
                }
            }
            i1++;
        } while (true);
          goto _L34
_L29:
        j1 = i;
_L34:
        if (j1 != 0)
        {
            for (i = 0; i < i5; i++)
            {
                obj = getChildAt(i);
                obj1 = (LayoutParams)((View) (obj)).getLayoutParams();
                if (((LayoutParams) (obj1)).expanded)
                {
                    l = ((LayoutParams) (obj1)).cellsUsed;
                    ((View) (obj)).measure(android.view.View.MeasureSpec.makeMeasureSpec(((LayoutParams) (obj1)).extraPixels + l * l4, 0x40000000), j4);
                }
            }

        }
        if (i4 == 0x40000000)
        {
            j = l3;
        }
        setMeasuredDimension(k4, j);
        return;
_L2:
        for (l = 0; l < i1; l++)
        {
            obj = (LayoutParams)getChildAt(l).getLayoutParams();
            obj.rightMargin = 0;
            obj.leftMargin = 0;
        }

        super.onMeasure(i, j);
        return;
_L31:
        f = f1;
        if (true) goto _L35; else goto _L20
_L20:
        k3 = l;
        l = i1;
        i1 = k3;
          goto _L25
        l = l1;
        i1 = j;
        j = i2;
          goto _L36
    }

    public void setPopupTheme(int i)
    {
label0:
        {
            if (mPopupTheme != i)
            {
                mPopupTheme = i;
                if (i != 0)
                {
                    break label0;
                }
                mPopupContext = getContext();
            }
            return;
        }
        mPopupContext = new ContextThemeWrapper(getContext(), i);
    }

    public final void setPresenter(ActionMenuPresenter actionmenupresenter)
    {
        mPresenter = actionmenupresenter;
        actionmenupresenter = mPresenter;
        actionmenupresenter.mMenuView = this;
        mMenu = actionmenupresenter.mMenu;
    }
}
