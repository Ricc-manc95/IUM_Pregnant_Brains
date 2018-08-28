// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.view.ActionMode;
import android.view.KeyEvent;
import android.view.View;

public abstract class ActionBar
{

    public ActionBar()
    {
    }

    public boolean closeOptionsMenu()
    {
        return false;
    }

    public boolean collapseActionView()
    {
        return false;
    }

    public void dispatchMenuVisibilityChanged(boolean flag)
    {
    }

    public abstract int getDisplayOptions();

    public abstract int getHeight();

    public Context getThemedContext()
    {
        return null;
    }

    public abstract void hide();

    public boolean invalidateOptionsMenu()
    {
        return false;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
    }

    void onDestroy()
    {
    }

    public boolean onKeyShortcut(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean onMenuKeyEvent(KeyEvent keyevent)
    {
        return false;
    }

    public boolean openOptionsMenu()
    {
        return false;
    }

    public abstract void setBackgroundDrawable(Drawable drawable);

    public abstract void setCustomView(View view, LayoutParams layoutparams);

    public void setDefaultDisplayHomeAsUpEnabled(boolean flag)
    {
    }

    public abstract void setDisplayHomeAsUpEnabled(boolean flag);

    public abstract void setDisplayOptions(int i);

    public abstract void setDisplayShowHomeEnabled(boolean flag);

    public abstract void setDisplayShowTitleEnabled(boolean flag);

    public abstract void setDisplayUseLogoEnabled(boolean flag);

    public void setElevation(float f)
    {
        if (f != 0.0F)
        {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        } else
        {
            return;
        }
    }

    public void setHideOnContentScrollEnabled(boolean flag)
    {
        throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
    }

    public void setHomeActionContentDescription(int i)
    {
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
    }

    public void setHomeButtonEnabled(boolean flag)
    {
    }

    public abstract void setIcon(int i);

    public abstract void setIcon(Drawable drawable);

    public abstract void setLogo(Drawable drawable);

    public void setShowHideAnimationEnabled(boolean flag)
    {
    }

    public abstract void setTitle(int i);

    public abstract void setTitle(CharSequence charsequence);

    public void setWindowTitle(CharSequence charsequence)
    {
    }

    public abstract void show();

    public ActionMode startActionMode(android.support.v7.view.ActionMode.Callback callback)
    {
        return null;
    }
}
