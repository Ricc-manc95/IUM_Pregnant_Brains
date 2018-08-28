// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.LayoutInflater;

public final class ContextThemeWrapper extends ContextWrapper
{

    private LayoutInflater mInflater;
    private Resources mResources;
    private android.content.res.Resources.Theme mTheme;
    public int mThemeResource;

    public ContextThemeWrapper()
    {
        super(null);
    }

    public ContextThemeWrapper(Context context, int i)
    {
        super(context);
        mThemeResource = i;
    }

    private final void initializeTheme()
    {
        boolean flag;
        if (mTheme == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            mTheme = getResources().newTheme();
            android.content.res.Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null)
            {
                mTheme.setTo(theme);
            }
        }
        mTheme.applyStyle(mThemeResource, true);
    }

    public final AssetManager getAssets()
    {
        return getResources().getAssets();
    }

    public final Resources getResources()
    {
        if (mResources == null)
        {
            mResources = super.getResources();
        }
        return mResources;
    }

    public final Object getSystemService(String s)
    {
        if ("layout_inflater".equals(s))
        {
            if (mInflater == null)
            {
                mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return mInflater;
        } else
        {
            return getBaseContext().getSystemService(s);
        }
    }

    public final android.content.res.Resources.Theme getTheme()
    {
        if (mTheme != null)
        {
            return mTheme;
        }
        if (mThemeResource == 0)
        {
            mThemeResource = 0x7f1402b0;
        }
        initializeTheme();
        return mTheme;
    }

    public final void setTheme(int i)
    {
        if (mThemeResource != i)
        {
            mThemeResource = i;
            initializeTheme();
        }
    }
}
