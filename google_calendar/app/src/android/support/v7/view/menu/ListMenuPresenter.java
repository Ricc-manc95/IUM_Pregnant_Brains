// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPresenter, MenuItemImpl, MenuBuilder, MenuDialogHelper, 
//            ExpandedMenuView, SubMenuBuilder

public final class ListMenuPresenter
    implements MenuPresenter, android.widget.AdapterView.OnItemClickListener
{

    public MenuAdapter mAdapter;
    public MenuPresenter.Callback mCallback;
    private Context mContext;
    public LayoutInflater mInflater;
    public int mItemLayoutRes;
    public MenuBuilder mMenu;
    public ExpandedMenuView mMenuView;
    private int mThemeRes;

    private ListMenuPresenter(int i, int j)
    {
        mItemLayoutRes = i;
        mThemeRes = 0;
    }

    public ListMenuPresenter(Context context, int i)
    {
        this(i, 0);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public final boolean collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public final boolean expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public final boolean flagActionItems()
    {
        return false;
    }

    public final void initForMenu(Context context, MenuBuilder menubuilder)
    {
        if (mThemeRes == 0) goto _L2; else goto _L1
_L1:
        mContext = new ContextThemeWrapper(context, mThemeRes);
        mInflater = LayoutInflater.from(mContext);
_L4:
        mMenu = menubuilder;
        if (mAdapter != null)
        {
            mAdapter.notifyDataSetChanged();
        }
        return;
_L2:
        if (mContext != null)
        {
            mContext = context;
            if (mInflater == null)
            {
                mInflater = LayoutInflater.from(mContext);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mCallback != null)
        {
            mCallback.onCloseMenu(menubuilder, flag);
        }
    }

    public final void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        mMenu.performItemAction((MenuItemImpl)mAdapter.getItem(i), this, 0);
    }

    public final boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        boolean flag = true;
        if (!submenubuilder.hasVisibleItems())
        {
            flag = false;
        } else
        {
            MenuDialogHelper menudialoghelper = new MenuDialogHelper(submenubuilder);
            Object obj1 = menudialoghelper.mMenu;
            Object obj = new android.support.v7.app.AlertDialog.Builder(((MenuBuilder) (obj1)).mContext);
            menudialoghelper.mPresenter = new ListMenuPresenter(((android.support.v7.app.AlertDialog.Builder) (obj)).P.mContext, 0x7f050010);
            menudialoghelper.mPresenter.mCallback = menudialoghelper;
            Object obj2 = menudialoghelper.mMenu;
            ListMenuPresenter listmenupresenter = menudialoghelper.mPresenter;
            Context context = ((MenuBuilder) (obj2)).mContext;
            ((MenuBuilder) (obj2)).mPresenters.add(new WeakReference(listmenupresenter));
            listmenupresenter.initForMenu(context, ((MenuBuilder) (obj2)));
            obj2.mIsActionItemsStale = true;
            obj2 = menudialoghelper.mPresenter;
            if (((ListMenuPresenter) (obj2)).mAdapter == null)
            {
                obj2.mAdapter = ((MenuAdapter) (obj2)). new MenuAdapter();
            }
            obj2 = ((ListMenuPresenter) (obj2)).mAdapter;
            ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mAdapter = ((android.widget.ListAdapter) (obj2));
            ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mOnClickListener = menudialoghelper;
            obj2 = ((MenuBuilder) (obj1)).mHeaderView;
            if (obj2 != null)
            {
                ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mCustomTitleView = ((View) (obj2));
            } else
            {
                android.graphics.drawable.Drawable drawable = ((MenuBuilder) (obj1)).mHeaderIcon;
                ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mIcon = drawable;
                obj1 = ((MenuBuilder) (obj1)).mHeaderTitle;
                ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mTitle = ((CharSequence) (obj1));
            }
            ((android.support.v7.app.AlertDialog.Builder) (obj)).P.mOnKeyListener = menudialoghelper;
            menudialoghelper.mDialog = ((android.support.v7.app.AlertDialog.Builder) (obj)).create();
            menudialoghelper.mDialog.setOnDismissListener(menudialoghelper);
            obj = menudialoghelper.mDialog.getWindow().getAttributes();
            obj.type = 1003;
            obj.flags = ((android.view.WindowManager.LayoutParams) (obj)).flags | 0x20000;
            menudialoghelper.mDialog.show();
            if (mCallback != null)
            {
                mCallback.onOpenSubMenu(submenubuilder);
                return true;
            }
        }
        return flag;
    }

    public final void setCallback(MenuPresenter.Callback callback)
    {
        mCallback = callback;
    }

    public final void updateMenuView(boolean flag)
    {
        if (mAdapter != null)
        {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MenuAdapter extends BaseAdapter
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
            ((MenuView.ItemView)view).initialize((MenuItemImpl)getItem(i), 0);
            return view;
        }

        public final void notifyDataSetChanged()
        {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }

        public MenuAdapter()
        {
            this$0 = ListMenuPresenter.this;
            super();
            mExpandedIndex = -1;
            findExpandedIndex();
        }
    }

}
