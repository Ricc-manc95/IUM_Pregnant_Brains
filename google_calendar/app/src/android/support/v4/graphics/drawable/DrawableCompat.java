// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v4.graphics.drawable:
//            WrappedDrawable

public final class DrawableCompat
{

    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;

    public static void clearColorFilter(Drawable drawable)
    {
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L2; else goto _L1
_L1:
        drawable.clearColorFilter();
_L4:
        return;
_L2:
        drawable.clearColorFilter();
        if (drawable instanceof InsetDrawable)
        {
            clearColorFilter(((InsetDrawable)drawable).getDrawable());
            return;
        }
        if (drawable instanceof WrappedDrawable)
        {
            clearColorFilter(((WrappedDrawable)drawable).getWrappedDrawable());
            return;
        }
        if (drawable instanceof DrawableContainer)
        {
            drawable = (android.graphics.drawable.DrawableContainer.DrawableContainerState)((DrawableContainer)drawable).getConstantState();
            if (drawable != null)
            {
                int i = 0;
                int j = drawable.getChildCount();
                while (i < j) 
                {
                    Drawable drawable1 = drawable.getChild(i);
                    if (drawable1 != null)
                    {
                        clearColorFilter(drawable1);
                    }
                    i++;
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static int getLayoutDirection(Drawable drawable)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return drawable.getLayoutDirection();
        }
        if (!sGetLayoutDirectionMethodFetched)
        {
            int i;
            try
            {
                Method method = android/graphics/drawable/Drawable.getDeclaredMethod("getLayoutDirection", new Class[0]);
                sGetLayoutDirectionMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sGetLayoutDirectionMethodFetched = true;
        }
        if (sGetLayoutDirectionMethod == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        i = ((Integer)sGetLayoutDirectionMethod.invoke(drawable, new Object[0])).intValue();
        return i;
        drawable;
        sGetLayoutDirectionMethod = null;
        return 0;
    }

    public static boolean setLayoutDirection(Drawable drawable, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return drawable.setLayoutDirection(i);
        }
        if (!sSetLayoutDirectionMethodFetched)
        {
            try
            {
                Method method = android/graphics/drawable/Drawable.getDeclaredMethod("setLayoutDirection", new Class[] {
                    Integer.TYPE
                });
                sSetLayoutDirectionMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sSetLayoutDirectionMethodFetched = true;
        }
        if (sSetLayoutDirectionMethod == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        sSetLayoutDirectionMethod.invoke(drawable, new Object[] {
            Integer.valueOf(i)
        });
        return true;
        drawable;
        sSetLayoutDirectionMethod = null;
        return false;
    }
}
