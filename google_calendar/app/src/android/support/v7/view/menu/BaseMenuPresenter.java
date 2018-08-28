// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPresenter, MenuView, MenuBuilder, MenuItemImpl, 
//            SubMenuBuilder

public abstract class BaseMenuPresenter
    implements MenuPresenter
{

    public MenuPresenter.Callback mCallback;
    public Context mContext;
    private int mItemLayoutRes;
    public MenuBuilder mMenu;
    private int mMenuLayoutRes;
    public MenuView mMenuView;
    public Context mSystemContext;
    private LayoutInflater mSystemInflater;

    public BaseMenuPresenter(Context context, int i, int j)
    {
        mSystemContext = context;
        mSystemInflater = LayoutInflater.from(context);
        mMenuLayoutRes = i;
        mItemLayoutRes = j;
    }

    public abstract void bindItemView(MenuItemImpl menuitemimpl, MenuView.ItemView itemview);

    public final boolean collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public final boolean expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean filterLeftoverView(ViewGroup viewgroup, int i)
    {
        viewgroup.removeViewAt(i);
        return true;
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public View getItemView(MenuItemImpl menuitemimpl, View view, ViewGroup viewgroup)
    {
        if (view instanceof MenuView.ItemView)
        {
            view = (MenuView.ItemView)view;
        } else
        {
            view = (MenuView.ItemView)mSystemInflater.inflate(mItemLayoutRes, viewgroup, false);
        }
        bindItemView(menuitemimpl, view);
        return (View)view;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        if (mMenuView == null)
        {
            mMenuView = (MenuView)mSystemInflater.inflate(mMenuLayoutRes, viewgroup, false);
            mMenuView.initialize(mMenu);
            updateMenuView(true);
        }
        return mMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        mContext = context;
        LayoutInflater.from(mContext);
        mMenu = menubuilder;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mCallback != null)
        {
            mCallback.onCloseMenu(menubuilder, flag);
        }
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        if (mCallback != null)
        {
            return mCallback.onOpenSubMenu(submenubuilder);
        } else
        {
            return false;
        }
    }

    public final void setCallback(MenuPresenter.Callback callback)
    {
        mCallback = callback;
    }

    public boolean shouldIncludeItem$514KOOBECHP6UQB45TPNAS3GDTP78BRM6SNNCQB5ESNMQPBEEKNKQPBEEL4N8PBD95MN0R1R55D0____0(MenuItemImpl menuitemimpl)
    {
        return true;
    }

    public void updateMenuView(boolean flag)
    {
        ViewGroup viewgroup = (ViewGroup)mMenuView;
        if (viewgroup != null)
        {
            int j;
            if (mMenu != null)
            {
                mMenu.flagActionItems();
                ArrayList arraylist = mMenu.getVisibleItems();
                int l = arraylist.size();
                int k = 0;
                int i = 0;
                do
                {
                    j = i;
                    if (k >= l)
                    {
                        break;
                    }
                    MenuItemImpl menuitemimpl = (MenuItemImpl)arraylist.get(k);
                    if (shouldIncludeItem$514KOOBECHP6UQB45TPNAS3GDTP78BRM6SNNCQB5ESNMQPBEEKNKQPBEEL4N8PBD95MN0R1R55D0____0(menuitemimpl))
                    {
                        View view = viewgroup.getChildAt(i);
                        Object obj;
                        View view1;
                        if (view instanceof MenuView.ItemView)
                        {
                            obj = ((MenuView.ItemView)view).getItemData();
                        } else
                        {
                            obj = null;
                        }
                        view1 = getItemView(menuitemimpl, view, viewgroup);
                        if (menuitemimpl != obj)
                        {
                            view1.setPressed(false);
                            view1.jumpDrawablesToCurrentState();
                        }
                        if (view1 != view)
                        {
                            obj = (ViewGroup)view1.getParent();
                            if (obj != null)
                            {
                                ((ViewGroup) (obj)).removeView(view1);
                            }
                            ((ViewGroup)mMenuView).addView(view1, i);
                        }
                        i++;
                    }
                    k++;
                } while (true);
            } else
            {
                j = 0;
            }
            while (j < viewgroup.getChildCount()) 
            {
                if (!filterLeftoverView(viewgroup, j))
                {
                    j++;
                }
            }
        }
    }
}
