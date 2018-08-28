// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            Toolbar, AppCompatImageButton

final class this._cls0
    implements MenuPresenter
{

    public MenuItemImpl mCurrentExpandedItem;
    private MenuBuilder mMenu;
    private final Toolbar this$0;

    public final boolean collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        if (mExpandedActionView instanceof CollapsibleActionView)
        {
            ((CollapsibleActionView)mExpandedActionView).onActionViewCollapsed();
        }
        removeView(mExpandedActionView);
        removeView(mCollapseButtonView);
        mExpandedActionView = null;
        Toolbar toolbar = Toolbar.this;
        for (int i = toolbar.mHiddenViews.size() - 1; i >= 0; i--)
        {
            toolbar.addView((View)toolbar.mHiddenViews.get(i));
        }

        toolbar.mHiddenViews.clear();
        mCurrentExpandedItem = null;
        requestLayout();
        menuitemimpl.mIsActionViewExpanded = false;
        menuitemimpl.mMenu.onItemsChanged(false);
        return true;
    }

    public final boolean expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        Object obj = Toolbar.this;
        if (((Toolbar) (obj)).mCollapseButtonView == null)
        {
            obj.mCollapseButtonView = new AppCompatImageButton(((Toolbar) (obj)).getContext(), null, 0x7f0100a9);
            ((Toolbar) (obj)).mCollapseButtonView.setImageDrawable(((Toolbar) (obj)).mCollapseIcon);
            ((Toolbar) (obj)).mCollapseButtonView.setContentDescription(((Toolbar) (obj)).mCollapseDescription);
            mCurrentExpandedItem mcurrentexpandeditem = new this._cls0(-2, -2);
            mcurrentexpandeditem._fld0 = ((Toolbar) (obj)).mButtonGravity & 0x70 | 0x800003;
            mcurrentexpandeditem._fld0 = 2;
            ((Toolbar) (obj)).mCollapseButtonView.setLayoutParams(mcurrentexpandeditem);
            ((Toolbar) (obj)).mCollapseButtonView.setOnClickListener(new this._cls0(((Toolbar) (obj))));
        }
        obj = mCollapseButtonView.getParent();
        if (obj != Toolbar.this)
        {
            if (obj instanceof ViewGroup)
            {
                ((ViewGroup)obj).removeView(mCollapseButtonView);
            }
            addView(mCollapseButtonView);
        }
        mExpandedActionView = menuitemimpl.getActionView();
        mCurrentExpandedItem = menuitemimpl;
        obj = mExpandedActionView.getParent();
        if (obj != Toolbar.this)
        {
            if (obj instanceof ViewGroup)
            {
                ((ViewGroup)obj).removeView(mExpandedActionView);
            }
            obj = new this._cls0(-2, -2);
            obj._fld0 = mButtonGravity & 0x70 | 0x800003;
            obj._fld0 = 2;
            mExpandedActionView.setLayoutParams(((android.view.) (obj)));
            addView(mExpandedActionView);
        }
        obj = Toolbar.this;
        for (int i = ((Toolbar) (obj)).getChildCount() - 1; i >= 0; i--)
        {
            View view = ((Toolbar) (obj)).getChildAt(i);
            if (((this._cls0)view.getLayoutParams())._fld0 != 2 && view != ((Toolbar) (obj)).mMenuView)
            {
                ((Toolbar) (obj)).removeViewAt(i);
                ((Toolbar) (obj)).mHiddenViews.add(view);
            }
        }

        requestLayout();
        menuitemimpl.mIsActionViewExpanded = true;
        menuitemimpl.mMenu.onItemsChanged(false);
        if (mExpandedActionView instanceof CollapsibleActionView)
        {
            ((CollapsibleActionView)mExpandedActionView).onActionViewExpanded();
        }
        return true;
    }

    public final boolean flagActionItems()
    {
        return false;
    }

    public final void initForMenu(Context context, MenuBuilder menubuilder)
    {
        if (mMenu != null && mCurrentExpandedItem != null)
        {
            mMenu.collapseItemActionView(mCurrentExpandedItem);
        }
        mMenu = menubuilder;
    }

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
    }

    public final boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        return false;
    }

    public final void setCallback(android.support.v7.view.menu.nu nu)
    {
    }

    public final void updateMenuView(boolean flag)
    {
        boolean flag2 = false;
        if (mCurrentExpandedItem == null) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (mMenu == null) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        j = mMenu.size();
        i = 0;
_L9:
        flag1 = flag2;
        if (i >= j) goto _L4; else goto _L5
_L5:
        if (mMenu.getItem(i) != mCurrentExpandedItem) goto _L7; else goto _L6
_L6:
        flag1 = true;
_L4:
        if (!flag1)
        {
            collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(mCurrentExpandedItem);
        }
_L2:
        return;
_L7:
        i++;
        if (true) goto _L9; else goto _L8
_L8:
    }

    ()
    {
        this$0 = Toolbar.this;
        super();
    }
}
