// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuBuilder, MenuItemImpl, ListMenuItemView

public final class MenuAdapter extends BaseAdapter
{

    public MenuBuilder mAdapterMenu;
    private int mExpandedIndex;
    public boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    public MenuAdapter(MenuBuilder menubuilder, LayoutInflater layoutinflater, boolean flag, int i)
    {
        mExpandedIndex = -1;
        mOverflowOnly = flag;
        mInflater = layoutinflater;
        mAdapterMenu = menubuilder;
        mItemLayoutRes = i;
        findExpandedIndex();
    }

    private final void findExpandedIndex()
    {
        MenuItemImpl menuitemimpl = mAdapterMenu.mExpandedItem;
        if (menuitemimpl != null)
        {
            Object obj = mAdapterMenu;
            ((MenuBuilder) (obj)).flagActionItems();
            obj = ((MenuBuilder) (obj)).mNonActionItems;
            int j = ((ArrayList) (obj)).size();
            for (int i = 0; i < j; i++)
            {
                if ((MenuItemImpl)((ArrayList) (obj)).get(i) == menuitemimpl)
                {
                    mExpandedIndex = i;
                    return;
                }
            }

        }
        mExpandedIndex = -1;
    }

    public final int getCount()
    {
        Object obj;
        if (mOverflowOnly)
        {
            obj = mAdapterMenu;
            ((MenuBuilder) (obj)).flagActionItems();
            obj = ((MenuBuilder) (obj)).mNonActionItems;
        } else
        {
            obj = mAdapterMenu.getVisibleItems();
        }
        if (mExpandedIndex < 0)
        {
            return ((ArrayList) (obj)).size();
        } else
        {
            return ((ArrayList) (obj)).size() - 1;
        }
    }

    public final Object getItem(int i)
    {
        Object obj;
        int j;
        if (mOverflowOnly)
        {
            obj = mAdapterMenu;
            ((MenuBuilder) (obj)).flagActionItems();
            obj = ((MenuBuilder) (obj)).mNonActionItems;
        } else
        {
            obj = mAdapterMenu.getVisibleItems();
        }
        j = i;
        if (mExpandedIndex >= 0)
        {
            j = i;
            if (i >= mExpandedIndex)
            {
                j = i + 1;
            }
        }
        return (MenuItemImpl)((ArrayList) (obj)).get(j);
    }

    public final long getItemId(int i)
    {
        return (long)i;
    }

    public final View getView(int i, View view, ViewGroup viewgroup)
    {
        if (view == null)
        {
            view = mInflater.inflate(mItemLayoutRes, viewgroup, false);
        }
        int k = ((MenuItemImpl)getItem(i)).getGroupId();
        int j;
        if (i - 1 >= 0)
        {
            j = ((MenuItemImpl)getItem(i - 1)).getGroupId();
        } else
        {
            j = k;
        }
        viewgroup = (ListMenuItemView)view;
        if (mAdapterMenu.isGroupDividerEnabled() && k != j)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (((ListMenuItemView) (viewgroup)).mGroupDivider != null)
        {
            Object obj = ((ListMenuItemView) (viewgroup)).mGroupDivider;
            if (!((ListMenuItemView) (viewgroup)).mHasListDivider && j != 0)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            ((ImageView) (obj)).setVisibility(j);
        }
        viewgroup = (MenuView.ItemView)view;
        if (mForceShowIcon)
        {
            obj = (ListMenuItemView)view;
            obj.mForceShowIcon = true;
            obj.mPreserveIconSpacing = true;
        }
        viewgroup.initialize((MenuItemImpl)getItem(i), 0);
        return view;
    }

    public final void notifyDataSetChanged()
    {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }
}
