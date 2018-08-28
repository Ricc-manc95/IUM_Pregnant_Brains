// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager

public final class VectorEnabledTintResources extends Resources
{

    private static boolean sCompatVectorFromResourcesEnabled = false;
    private final WeakReference mContextRef;

    public final Drawable getDrawable(int i)
        throws android.content.res.Resources.NotFoundException
    {
        Context context = (Context)mContextRef.get();
        if (context != null)
        {
            AppCompatDrawableManager appcompatdrawablemanager = AppCompatDrawableManager.get();
            Drawable drawable1 = appcompatdrawablemanager.loadDrawableFromDelegates(context, i);
            Drawable drawable = drawable1;
            if (drawable1 == null)
            {
                drawable = super.getDrawable(i);
            }
            if (drawable != null)
            {
                return appcompatdrawablemanager.tintDrawable(context, i, false, drawable);
            } else
            {
                return null;
            }
        } else
        {
            return super.getDrawable(i);
        }
    }

}
