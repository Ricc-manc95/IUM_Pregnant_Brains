// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            TintResources, VectorEnabledTintResources

public final class TintContextWrapper extends ContextWrapper
{

    private static final Object CACHE_LOCK = new Object();
    private static ArrayList sCache;
    private final Resources mResources;
    private final android.content.res.Resources.Theme mTheme = null;

    private TintContextWrapper(Context context)
    {
        super(context);
        mResources = new TintResources(this, context.getResources());
    }

    public static Context wrap(Context context)
    {
        if (!(context instanceof TintContextWrapper) && !(context.getResources() instanceof TintResources))
        {
            if (!(context.getResources() instanceof VectorEnabledTintResources));
        }
        return context;
    }

    public final AssetManager getAssets()
    {
        return mResources.getAssets();
    }

    public final Resources getResources()
    {
        return mResources;
    }

    public final android.content.res.Resources.Theme getTheme()
    {
        if (mTheme == null)
        {
            return super.getTheme();
        } else
        {
            return mTheme;
        }
    }

    public final void setTheme(int i)
    {
        if (mTheme == null)
        {
            super.setTheme(i);
            return;
        } else
        {
            mTheme.applyStyle(i, true);
            return;
        }
    }

}
