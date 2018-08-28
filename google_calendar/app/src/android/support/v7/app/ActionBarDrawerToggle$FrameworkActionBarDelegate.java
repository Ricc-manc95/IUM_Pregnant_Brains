// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

final class mActivity
    implements mActivity
{

    private final Activity mActivity;

    public final Context getActionBarThemedContext()
    {
        ActionBar actionbar = mActivity.getActionBar();
        if (actionbar != null)
        {
            return actionbar.getThemedContext();
        } else
        {
            return mActivity;
        }
    }

    public final Drawable getThemeUpIndicator()
    {
        Object obj = mActivity.getActionBar();
        Drawable drawable;
        if (obj != null)
        {
            obj = ((ActionBar) (obj)).getThemedContext();
        } else
        {
            obj = mActivity;
        }
        obj = ((Context) (obj)).obtainStyledAttributes(null, new int[] {
            0x101030b
        }, 0x10102ce, 0);
        drawable = ((TypedArray) (obj)).getDrawable(0);
        ((TypedArray) (obj)).recycle();
        return drawable;
    }

    public final boolean isNavigationVisible()
    {
        ActionBar actionbar = mActivity.getActionBar();
        return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
    }

    public final void setActionBarDescription(int i)
    {
        ActionBar actionbar = mActivity.getActionBar();
        if (actionbar != null)
        {
            actionbar.setHomeActionContentDescription(i);
        }
    }

    public final void setActionBarUpIndicator(Drawable drawable, int i)
    {
        ActionBar actionbar = mActivity.getActionBar();
        if (actionbar != null)
        {
            actionbar.setHomeAsUpIndicator(drawable);
            actionbar.setHomeActionContentDescription(i);
        }
    }

    (Activity activity)
    {
        mActivity = activity;
    }
}
