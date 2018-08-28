// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPresenter, ShowableListMenu, MenuBuilder, MenuAdapter, 
//            MenuItemImpl

public abstract class MenuPopup
    implements MenuPresenter, ShowableListMenu, android.widget.AdapterView.OnItemClickListener
{

    public Rect mEpicenterBounds;

    MenuPopup()
    {
    }

    protected static int measureIndividualMenuWidth(ListAdapter listadapter, ViewGroup viewgroup, Context context, int i)
    {
        int k1 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        int l1 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = listadapter.getCount();
        int k = 0;
        int i1 = 0;
        View view = null;
        int j = 0;
        while (k < i2) 
        {
            int j1 = listadapter.getItemViewType(k);
            int l = i1;
            if (j1 != i1)
            {
                l = j1;
                view = null;
            }
            if (viewgroup == null)
            {
                viewgroup = new FrameLayout(context);
            }
            view = listadapter.getView(k, view, viewgroup);
            view.measure(k1, l1);
            i1 = view.getMeasuredWidth();
            if (i1 >= i)
            {
                return i;
            }
            if (i1 > j)
            {
                j = i1;
            }
            k++;
            i1 = l;
        }
        return j;
    }

    protected static boolean shouldPreserveIconSpacing(MenuBuilder menubuilder)
    {
        boolean flag1 = false;
        int j = menubuilder.size();
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < j)
                {
                    MenuItem menuitem = menubuilder.getItem(i);
                    if (!menuitem.isVisible() || menuitem.getIcon() == null)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            i++;
        } while (true);
    }

    public abstract void addMenu(MenuBuilder menubuilder);

    protected boolean closeMenuOnSubMenuOpened()
    {
        return true;
    }

    public final boolean collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public final boolean expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public final void initForMenu(Context context, MenuBuilder menubuilder)
    {
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        view = (ListAdapter)adapterview.getAdapter();
        if (view instanceof HeaderViewListAdapter)
        {
            adapterview = (MenuAdapter)((HeaderViewListAdapter)view).getWrappedAdapter();
        } else
        {
            adapterview = (MenuAdapter)view;
        }
        adapterview = ((MenuAdapter) (adapterview)).mAdapterMenu;
        view = (MenuItem)view.getItem(i);
        if (closeMenuOnSubMenuOpened())
        {
            i = 0;
        } else
        {
            i = 4;
        }
        adapterview.performItemAction(view, this, i);
    }

    public abstract void setAnchorView(View view);

    public abstract void setForceShowIcon(boolean flag);

    public abstract void setGravity(int i);

    public abstract void setHorizontalOffset(int i);

    public abstract void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener);

    public abstract void setShowTitle(boolean flag);

    public abstract void setVerticalOffset(int i);
}
