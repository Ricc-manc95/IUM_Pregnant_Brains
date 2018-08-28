// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.ActionProvider;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public final class ActionMenuItem
    implements SupportMenuItem
{

    private CharSequence mContentDescription;
    private Context mContext;
    private int mFlags;
    private final int mGroup = 0;
    private boolean mHasIconTint;
    private boolean mHasIconTintMode;
    private Drawable mIconDrawable;
    private ColorStateList mIconTintList;
    private android.graphics.PorterDuff.Mode mIconTintMode;
    private final int mId = 0x102002c;
    private Intent mIntent;
    private final int mOrdering = 0;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    public ActionMenuItem(Context context, int i, int j, int k, int l, CharSequence charsequence)
    {
        mShortcutNumericModifiers = 4096;
        mShortcutAlphabeticModifiers = 4096;
        mIconTintList = null;
        mIconTintMode = null;
        mHasIconTint = false;
        mHasIconTintMode = false;
        mFlags = 16;
        mContext = context;
        mTitle = charsequence;
    }

    private final void applyIconTint()
    {
        if (mIconDrawable != null && (mHasIconTint || mHasIconTintMode))
        {
            Object obj = mIconDrawable;
            if (android.os.Build.VERSION.SDK_INT < 23 && !(obj instanceof TintAwareDrawable))
            {
                obj = new WrappedDrawableApi21(((Drawable) (obj)));
            }
            mIconDrawable = ((Drawable) (obj));
            mIconDrawable = mIconDrawable.mutate();
            if (mHasIconTint)
            {
                mIconDrawable.setTintList(mIconTintList);
            }
            if (mHasIconTintMode)
            {
                mIconDrawable.setTintMode(mIconTintMode);
            }
        }
    }

    public final boolean collapseActionView()
    {
        return false;
    }

    public final boolean expandActionView()
    {
        return false;
    }

    public final ActionProvider getActionProvider()
    {
        throw new UnsupportedOperationException();
    }

    public final View getActionView()
    {
        return null;
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
        return mIconDrawable;
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
        return null;
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
        return mOrdering;
    }

    public final SubMenu getSubMenu()
    {
        return null;
    }

    public final android.support.v4.view.ActionProvider getSupportActionProvider()
    {
        return null;
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

    public final boolean hasSubMenu()
    {
        return false;
    }

    public final boolean isActionViewExpanded()
    {
        return false;
    }

    public final boolean isCheckable()
    {
        return (mFlags & 1) != 0;
    }

    public final boolean isChecked()
    {
        return (mFlags & 2) != 0;
    }

    public final boolean isEnabled()
    {
        return (mFlags & 0x10) != 0;
    }

    public final boolean isVisible()
    {
        return (mFlags & 8) == 0;
    }

    public final MenuItem setActionProvider(ActionProvider actionprovider)
    {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setActionView(int i)
    {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setActionView(View view)
    {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setAlphabeticShortcut(char c)
    {
        mShortcutAlphabeticChar = Character.toLowerCase(c);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c, int i)
    {
        mShortcutAlphabeticChar = Character.toLowerCase(c);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public final MenuItem setCheckable(boolean flag)
    {
        int i = mFlags;
        boolean flag1;
        if (flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mFlags = flag1 | i & -2;
        return this;
    }

    public final MenuItem setChecked(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        mFlags = byte0 | i & -3;
        return this;
    }

    public final SupportMenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        return this;
    }

    public final MenuItem setContentDescription(CharSequence charsequence)
    {
        mContentDescription = charsequence;
        return this;
    }

    public final MenuItem setEnabled(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if (flag)
        {
            byte0 = 16;
        } else
        {
            byte0 = 0;
        }
        mFlags = byte0 | i & 0xffffffef;
        return this;
    }

    public final MenuItem setIcon(int i)
    {
        mIconDrawable = ContextCompat.getDrawable(mContext, i);
        applyIconTint();
        return this;
    }

    public final MenuItem setIcon(Drawable drawable)
    {
        mIconDrawable = drawable;
        applyIconTint();
        return this;
    }

    public final MenuItem setIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        mHasIconTint = true;
        applyIconTint();
        return this;
    }

    public final MenuItem setIconTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mIconTintMode = mode;
        mHasIconTintMode = true;
        applyIconTint();
        return this;
    }

    public final MenuItem setIntent(Intent intent)
    {
        mIntent = intent;
        return this;
    }

    public final MenuItem setNumericShortcut(char c)
    {
        mShortcutNumericChar = c;
        return this;
    }

    public final MenuItem setNumericShortcut(char c, int i)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public final MenuItem setOnActionExpandListener(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
    {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setOnMenuItemClickListener(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
    {
        return this;
    }

    public final MenuItem setShortcut(char c, char c1)
    {
        mShortcutNumericChar = c;
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        return this;
    }

    public final MenuItem setShortcut(char c, char c1, int i, int j)
    {
        mShortcutNumericChar = c;
        mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        mShortcutAlphabeticChar = Character.toLowerCase(c1);
        mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(j);
        return this;
    }

    public final void setShowAsAction(int i)
    {
    }

    public final MenuItem setShowAsActionFlags(int i)
    {
        setShowAsAction(i);
        return this;
    }

    public final SupportMenuItem setSupportActionProvider(android.support.v4.view.ActionProvider actionprovider)
    {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setTitle(int i)
    {
        mTitle = mContext.getResources().getString(i);
        return this;
    }

    public final MenuItem setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence charsequence)
    {
        mTitleCondensed = charsequence;
        return this;
    }

    public final SupportMenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        return this;
    }

    public final MenuItem setTooltipText(CharSequence charsequence)
    {
        mTooltipText = charsequence;
        return this;
    }

    public final MenuItem setVisible(boolean flag)
    {
        int i = mFlags;
        byte byte0;
        if (flag)
        {
            byte0 = 0;
        } else
        {
            byte0 = 8;
        }
        mFlags = byte0 | i & 8;
        return this;
    }
}
