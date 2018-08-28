// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;

// Referenced classes of package android.support.v7.widget:
//            DropDownListView, MenuItemHoverListener

public final class mRetreatKey extends DropDownListView
{

    private final int mAdvanceKey;
    public MenuItemHoverListener mHoverListener;
    private MenuItem mHoveredMenuItem;
    private final int mRetreatKey;

    public final volatile boolean hasFocus()
    {
        return super.hasFocus();
    }

    public final volatile boolean hasWindowFocus()
    {
        return super.hasWindowFocus();
    }

    public final volatile boolean isFocused()
    {
        return super.isFocused();
    }

    public final volatile boolean isInTouchMode()
    {
        return super.isInTouchMode();
    }

    public final volatile int measureHeightOfChildrenCompat(int i, int j, int k, int l, int i1)
    {
        return super.measureHeightOfChildrenCompat(i, j, k, l, i1);
    }

    public final volatile boolean onForwardedEvent(MotionEvent motionevent, int i)
    {
        return super.onForwardedEvent(motionevent, i);
    }

    public final boolean onHoverEvent(MotionEvent motionevent)
    {
        if (mHoverListener == null) goto _L2; else goto _L1
_L1:
        MenuItemImpl menuitemimpl;
        Object obj = getAdapter();
        MenuItem menuitem;
        int i;
        int j;
        if (obj instanceof HeaderViewListAdapter)
        {
            obj = (HeaderViewListAdapter)obj;
            i = ((HeaderViewListAdapter) (obj)).getHeadersCount();
            obj = (MenuAdapter)((HeaderViewListAdapter) (obj)).getWrappedAdapter();
        } else
        {
            obj = (MenuAdapter)obj;
            i = 0;
        }
        if (motionevent.getAction() == 10) goto _L4; else goto _L3
_L3:
        j = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
        if (j == -1) goto _L4; else goto _L5
_L5:
        i = j - i;
        if (i < 0 || i >= ((MenuAdapter) (obj)).getCount()) goto _L4; else goto _L6
_L6:
        menuitemimpl = (MenuItemImpl)((MenuAdapter) (obj)).getItem(i);
_L8:
        menuitem = mHoveredMenuItem;
        if (menuitem != menuitemimpl)
        {
            obj = ((MenuAdapter) (obj)).mAdapterMenu;
            if (menuitem != null)
            {
                mHoverListener.onItemHoverExit(((MenuBuilder) (obj)), menuitem);
            }
            mHoveredMenuItem = menuitemimpl;
            if (menuitemimpl != null)
            {
                mHoverListener.onItemHoverEnter(((MenuBuilder) (obj)), menuitemimpl);
            }
        }
_L2:
        return super.onHoverEvent(motionevent);
_L4:
        menuitemimpl = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final boolean onKeyDown(int i, KeyEvent keyevent)
    {
        ListMenuItemView listmenuitemview = (ListMenuItemView)getSelectedView();
        if (listmenuitemview != null && i == mAdvanceKey)
        {
            if (listmenuitemview.isEnabled() && listmenuitemview.mItemData.hasSubMenu())
            {
                performItemClick(listmenuitemview, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listmenuitemview != null && i == mRetreatKey)
        {
            setSelection(-1);
            ((MenuAdapter)getAdapter()).mAdapterMenu.close(false);
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public final volatile boolean onTouchEvent(MotionEvent motionevent)
    {
        return super.onTouchEvent(motionevent);
    }

    public final volatile void setSelector(Drawable drawable)
    {
        super.setSelector(drawable);
    }

    public (Context context, boolean flag)
    {
        super(context, flag);
        if (1 == context.getResources().getConfiguration().getLayoutDirection())
        {
            mAdvanceKey = 21;
            mRetreatKey = 22;
            return;
        } else
        {
            mAdvanceKey = 22;
            mRetreatKey = 21;
            return;
        }
    }
}
