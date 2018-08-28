// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuBuilder, SubMenuBuilder

public final class MenuItemImpl
    implements SupportMenuItem
{

    public ActionProvider mActionProvider;
    private View mActionView;
    private final int mCategoryOrder;
    private android.view.MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    public int mFlags;
    private final int mGroup;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private int mIconResId;
    private ColorStateList mIconTintList;
    private android.graphics.PorterDuff.Mode mIconTintMode;
    private final int mId;
    private Intent mIntent;
    public boolean mIsActionViewExpanded;
    public MenuBuilder mMenu;
    private android.view.ContextMenu.ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint;
    private android.view.MenuItem.OnActionExpandListener mOnActionExpandListener;
    public final int mOrdering;
    public char mShortcutAlphabeticChar;
    public int mShortcutAlphabeticModifiers;
    public char mShortcutNumericChar;
    public int mShortcutNumericModifiers;
    public int mShowAsAction;
    public SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    MenuItemImpl(MenuBuilder menubuilder, int i, int j, int k, int l, CharSequence charsequence, int i1)
    {
        mShortcutNumericModifiers = 4096;
        mShortcutAlphabeticModifiers = 4096;
        mIconResId = 0;
        mIconTintList = null;
        mIconTintMode = null;
        mHasIconTint = false;
        mHasIconTintMode = false;
        mNeedToApplyIconTint = false;
        mFlags = 16;
        mShowAsAction = 0;
        mIsActionViewExpanded = false;
        mMenu = menubuilder;
        mId = j;
        mGroup = i;
        mCategoryOrder = k;
        mOrdering = l;
        mTitle = charsequence;
        mShowAsAction = i1;
    }

    private final Drawable applyIconTintIfNecessary(Drawable drawable)
    {
        Object obj;
label0:
        {
            obj = drawable;
            if (drawable == null)
            {
                break label0;
            }
            obj = drawable;
            if (!mNeedToApplyIconTint)
            {
                break label0;
            }
            if (!mHasIconTint)
            {
                obj = drawable;
                if (!mHasIconTintMode)
                {
                    break label0;
                }
            }
            obj = drawable;
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                obj = drawable;
                if (!(drawable instanceof TintAwareDrawable))
                {
                    obj = new WrappedDrawableApi21(drawable);
                }
            }
            obj = ((Drawable) (obj)).mutate();
            if (mHasIconTint)
            {
                ((Drawable) (obj)).setTintList(mIconTintList);
            }
            if (mHasIconTintMode)
            {
                ((Drawable) (obj)).setTintMode(mIconTintMode);
            }
            mNeedToApplyIconTint = false;
        }
        return ((Drawable) (obj));
    }

    public final boolean collapseActionView()
    {
        if ((mShowAsAction & 8) != 0)
        {
            if (mActionView == null)
            {
                return true;
            }
            if (mOnActionExpandListener == null || mOnActionExpandListener.onMenuItemActionCollapse(this))
            {
                return mMenu.collapseItemActionView(this);
            }
        }
        return false;
    }

    public final boolean expandActionView()
    {
        while (!hasCollapsibleActionView() || mOnActionExpandListener != null && !mOnActionExpandListener.onMenuItemActionExpand(this)) 
        {
            return false;
        }
        return mMenu.expandItemActionView(this);
    }

    public final android.view.ActionProvider getActionProvider()
    {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public final View getActionView()
    {
        if (mActionView != null)
        {
            return mActionView;
        }
        if (mActionProvider != null)
        {
            mActionView = mActionProvider.onCreateActionView(this);
            return mActionView;
        } else
        {
            return null;
        }
    }

    public final int getAlphabeticModifiers()
    {
        return mShortcutAlphabeticModifiers;
    }

    public final char getAlphabeticShortcut()
    {
        return mShortcutAlphabeticChar;
    }

    public final CharSequence getContentDescription()
    {
        return mContentDescription;
    }

    public final int getGroupId()
    {
        return mGroup;
    }

    public final Drawable getIcon()
    {
        if (mIconDrawable != null)
        {
            return applyIconTintIfNecessary(mIconDrawable);
        }
        if (mIconResId != 0)
        {
            Drawable drawable = AppCompatResources.getDrawable(mMenu.mContext, mIconResId);
            mIconResId = 0;
            mIconDrawable = drawable;
            return applyIconTintIfNecessary(drawable);
        } else
        {
            return null;
        }
    }

    public final ColorStateList getIconTintList()
    {
        return mIconTintList;
    }

    public final android.graphics.PorterDuff.Mode getIconTintMode()
    {
        return mIconTintMode;
    }

    public final Intent getIntent()
    {
        return mIntent;
    }

    public final int getItemId()
    {
        return mId;
    }

    public final android.view.ContextMenu.ContextMenuInfo getMenuInfo()
    {
        return mMenuInfo;
    }

    public final int getNumericModifiers()
    {
        return mShortcutNumericModifiers;
    }

    public final char getNumericShortcut()
    {
        return mShortcutNumericChar;
    }

    public final int getOrder()
    {
        return mCategoryOrder;
    }

    public final SubMenu getSubMenu()
    {
        return mSubMenu;
    }

    public final ActionProvider getSupportActionProvider()
    {
        return mActionProvider;
    }

    public final CharSequence getTitle()
    {
        return mTitle;
    }

    public final CharSequence getTitleCondensed()
    {
        if (mTitleCondensed != null)
        {
            return mTitleCondensed;
        } else
        {
            return mTitle;
        }
    }

    public final CharSequence getTooltipText()
    {
        return mTooltipText;
    }

    public final boolean hasCollapsibleActionView()
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if ((mShowAsAction & 8) != 0)
        {
            if (mActionView == null && mActionProvider != null)
            {
                mActionView = mActionProvider.onCreateActionView(this);
            }
            flag = flag1;
            if (mActionView != null)
            {
                flag = true;
            }
        }
        return flag;
    }

    public final boolean hasSubMenu()
    {
        return mSubMenu != null;
    }

    public final boolean invoke()
    {
_L2:
        return true;
        if (mClickListener != null && mClickListener.onMenuItemClick(this) || mMenu.dispatchMenuItemSelected(mMenu, this)) goto _L2; else goto _L1
_L1:
        if (mIntent == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mMenu.mContext.startActivity(mIntent);
        return true;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", activitynotfoundexception);
        if (mActionProvider != null && mActionProvider.onPerformDefaultAction()) goto _L2; else goto _L3
_L3:
        return false;
    }

    public final boolean isActionViewExpanded()
    {
        return mIsActionViewExpanded;
    }

    public final boolean isCheckable()
    {
        return (mFlags & 1) == 1;
    }

    public final boolean isChecked()
    {
        return (mFlags & 2) == 2;
    }

    public final boolean isEnabled()
    {
        return (mFlags & 0x10) != 0;
    }

    public final boolean isVisible()
    {
        if (mActionProvider == null || !mActionProvider.overridesItemVisibility()) goto _L2; else goto _L1
_L1:
        if ((mFlags & 8) != 0 || !mActionProvider.isVisible()) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if ((mFlags & 8) != 0)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final MenuItem setActionProvider(android.view.ActionProvider actionprovider)
    {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public final MenuItem setActionView(int i)
    {
        Object obj = mMenu.mContext;
        obj = (SupportMenuItem)setActionView(LayoutInflater.from(((Context) (obj))).inflate(i, new LinearLayout(((Context) (obj))), false));
        return this;
    }

    public final MenuItem setActionView(View view)
    {
        mActionView = view;
        mActionProvider = null;
        if (view != null && view.getId() == -1 && mId > 0)
        {
            view.setId(mId);
        }
        view = mMenu;
        view.mIsActionItemsStale = true;
        view.onItemsChanged(true);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c)
    {
        if (mShortcutAlphabeticChar == c)
        {
            return this;
        } else
        {
            mShortcutAlphabeticChar = Character.toLowerCase(c);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public final MenuItem setAlphabeticShortcut(char c, int i)
    {
        if (mShortcutAlphabeticChar == c && mShortcutAlphabeticModifiers == i)
        {
            return this;
        } else
        {
            mShortcutAlphabeticChar = Character.toLowerCase(c);
            mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public final MenuItem setCheckable(boolean flag)
    {
        int i = mFlags;
        int j = mFlags;
        boolean flag1;
        if (flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mFlags = flag1 | j & -2;
        if (i != mFlags)
        {
            mMenu.onItemsChanged(false);
        }
        return this;
    }

    public final MenuItem setChecked(boolean flag)
    {
        if ((mFlags & 4) != 0)
        {
            MenuBuilder menubuilder = mMenu;
            int j = getGroupId();
            int k = menubuilder.mItems.size();
            if (!menubuilder.mPreventDispatchingItemsChanged)
            {
                menubuilder.mPreventDispatchingItemsChanged = true;
                menubuilder.mItemsChangedWhileDispatchPrevented = false;
                menubuilder.mStructureChangedWhileDispatchPrevented = false;
            }
            int i = 0;
            while (i < k) 
            {
                MenuItemImpl menuitemimpl = (MenuItemImpl)menubuilder.mItems.get(i);
                if (menuitemimpl.getGroupId() != j)
                {
                    continue;
                }
                boolean flag1;
                if ((menuitemimpl.mFlags & 4) != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1 && menuitemimpl.isCheckable())
                {
                    if (menuitemimpl == this)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    menuitemimpl.setCheckedInt(flag);
                }
                i++;
            }
            menubuilder.mPreventDispatchingItemsChanged = false;
            if (menubuilder.mItemsChangedWhileDispatchPrevented)
            {
                menubuilder.mItemsChangedWhileDispatchPrevented = false;
                menubuilder.onItemsChanged(menubuilder.mStructureChangedWhileDispatchPrevented);
            }
            return this;
        } else
        {
            setCheckedInt(flag);
            return this;
        }
    }

    final void setCheckedInt(boolean flag)
    {
        int i = mFlags;
        int j = mFlags;
        byte byte0;
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        mFlags = byte0 | j & -3;
        if (i != mFlags)
        {
            mMenu.onItemsChanged(false);
        }
    }

    public final SupportMenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setEnabled(boolean flag)
    {
        if (flag)
        {
            mFlags = mFlags | 0x10;
        } else
        {
            mFlags = mFlags & 0xffffffef;
        }
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setIcon(int i)
    {
        mIconDrawable = null;
        mIconResId = i;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setIcon(Drawable drawable)
    {
        mIconResId = 0;
        mIconDrawable = drawable;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        mHasIconTint = true;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setIconTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mIconTintMode = mode;
        mHasIconTintMode = true;
        mNeedToApplyIconTint = true;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setIntent(Intent intent)
    {
        mIntent = intent;
        return this;
    }

    public final MenuItem setNumericShortcut(char c)
    {
        if (mShortcutNumericChar == c)
        {
            return this;
        } else
        {
            mShortcutNumericChar = c;
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public final MenuItem setNumericShortcut(char c, int i)
    {
        if (mShortcutNumericChar == c && mShortcutNumericModifiers == i)
        {
            return this;
        } else
        {
            mShortcutNumericChar = c;
            mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
            mMenu.onItemsChanged(false);
            return this;
        }
    }

    public final MenuItem setOnActionExpandListener(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
    {
        mOnActionExpandListener = onactionexpandlistener;
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
    {
        mClickListener = onmenuitemclicklistener;
        return this;
    }

    public final MenuItem setShortcut(char c, char c1)
    {
        mShortcutNumericChar = c;
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setShortcut(char c, char c1, int i, int j)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(j);
        mMenu.onItemsChanged(false);
        return this;
    }

    public final void setShowAsAction(int i)
    {
        switch (i & 3)
        {
        default:
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            mShowAsAction = i;
            break;
        }
        MenuBuilder menubuilder = mMenu;
        menubuilder.mIsActionItemsStale = true;
        menubuilder.onItemsChanged(true);
    }

    public final MenuItem setShowAsActionFlags(int i)
    {
        setShowAsAction(i);
        return this;
    }

    public final SupportMenuItem setSupportActionProvider(ActionProvider actionprovider)
    {
        if (mActionProvider != null)
        {
            ActionProvider actionprovider1 = mActionProvider;
            actionprovider1.mVisibilityListener = null;
            actionprovider1.mSubUiVisibilityListener = null;
        }
        mActionView = null;
        mActionProvider = actionprovider;
        mMenu.onItemsChanged(true);
        if (mActionProvider != null)
        {
            mActionProvider.setVisibilityListener(new _cls1());
        }
        return this;
    }

    public final MenuItem setTitle(int i)
    {
        return setTitle(((CharSequence) (mMenu.mContext.getString(i))));
    }

    public final MenuItem setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        mMenu.onItemsChanged(false);
        if (mSubMenu != null)
        {
            mSubMenu.setHeaderTitle(charsequence);
        }
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence charsequence)
    {
        mTitleCondensed = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final SupportMenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        mMenu.onItemsChanged(false);
        return this;
    }

    public final MenuItem setVisible(boolean flag)
    {
        if (setVisibleInt(flag))
        {
            MenuBuilder menubuilder = mMenu;
            menubuilder.mIsVisibleItemsStale = true;
            menubuilder.onItemsChanged(true);
        }
        return this;
    }

    final boolean setVisibleInt(boolean flag)
    {
        boolean flag1 = false;
        int i = mFlags;
        int j = mFlags;
        byte byte0;
        if (flag)
        {
            byte0 = 0;
        } else
        {
            byte0 = 8;
        }
        mFlags = byte0 | j & -9;
        flag = flag1;
        if (i != mFlags)
        {
            flag = true;
        }
        return flag;
    }

    public final String toString()
    {
        if (mTitle != null)
        {
            return mTitle.toString();
        } else
        {
            return null;
        }
    }

    private class _cls1
        implements android.support.v4.view.ActionProvider.VisibilityListener
    {

        private final MenuItemImpl this$0;

        public final void onActionProviderVisibilityChanged$51D2ILG_0()
        {
            MenuBuilder menubuilder = mMenu;
            menubuilder.mIsVisibleItemsStale = true;
            menubuilder.onItemsChanged(true);
        }

        _cls1()
        {
            this$0 = MenuItemImpl.this;
            super();
        }
    }

}
