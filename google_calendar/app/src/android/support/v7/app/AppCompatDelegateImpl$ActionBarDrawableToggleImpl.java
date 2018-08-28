// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegate, ActionBar, AppCompatDelegateImpl

final class this._cls0
    implements this._cls0
{

    private final AppCompatDelegateImpl this$0;

    public final Context getActionBarThemedContext()
    {
        AppCompatDelegateImpl appcompatdelegateimpl = AppCompatDelegateImpl.this;
        Context context = null;
        Object obj = appcompatdelegateimpl.getSupportActionBar();
        if (obj != null)
        {
            context = ((ActionBar) (obj)).getThemedContext();
        }
        obj = context;
        if (context == null)
        {
            obj = appcompatdelegateimpl.mContext;
        }
        return ((Context) (obj));
    }

    public final Drawable getThemeUpIndicator()
    {
        AppCompatDelegateImpl appcompatdelegateimpl = AppCompatDelegateImpl.this;
        Object obj = appcompatdelegateimpl.getSupportActionBar();
        Object obj1;
        if (obj != null)
        {
            obj = ((ActionBar) (obj)).getThemedContext();
        } else
        {
            obj = null;
        }
        obj1 = obj;
        if (obj == null)
        {
            obj1 = appcompatdelegateimpl.mContext;
        }
        obj = new TintTypedArray(((Context) (obj1)), ((Context) (obj1)).obtainStyledAttributes(null, new int[] {
            0x7f01009e
        }));
        obj1 = ((TintTypedArray) (obj)).getDrawable(0);
        ((TintTypedArray) (obj)).mWrapped.recycle();
        return ((Drawable) (obj1));
    }

    public final boolean isNavigationVisible()
    {
        ActionBar actionbar = getSupportActionBar();
        return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
    }

    public final void setActionBarDescription(int i)
    {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            actionbar.setHomeActionContentDescription(i);
        }
    }

    public final void setActionBarUpIndicator(Drawable drawable, int i)
    {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            actionbar.setHomeAsUpIndicator(drawable);
            actionbar.setHomeActionContentDescription(i);
        }
    }

    ()
    {
        this$0 = AppCompatDelegateImpl.this;
        super();
    }
}
