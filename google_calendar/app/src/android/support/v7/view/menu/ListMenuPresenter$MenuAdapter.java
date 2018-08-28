// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            ListMenuPresenter, MenuBuilder, MenuItemImpl

public final class findExpandedIndex extends BaseAdapter
{

    private int mExpandedIndex;
    private final ListMenuPresenter this$0;

    private final void findExpandedIndex()
    {
        MenuItemImpl menuitemimpl = mMenu.mExpandedItem;
        if (menuitemimpl != null)
        {
            Object obj = mMenu;
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
        MenuBuilder menubuilder = mMenu;
        menubuilder.flagActionItems();
        int i = menubuilder.mNonActionItems.size();
        if (mExpandedIndex < 0)
        {
            return i;
        } else
        {
            return i - 1;
        }
    }

    public final Object getItem(int i)
    {
        Object obj = mMenu;
        ((MenuBuilder) (obj)).flagActionItems();
        obj = ((MenuBuilder) (obj)).mNonActionItems;
        int j = i;
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
        ((es)view).es((MenuItemImpl)getItem(i), 0);
        return view;
    }

    public final void notifyDataSetChanged()
    {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public ()
    {
        this$0 = ListMenuPresenter.this;
        super();
        mExpandedIndex = -1;
        findExpandedIndex();
    }
}
