// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ViewUtils
{

    public static Method sComputeFitSystemWindowsMethod;

    public static boolean isLayoutRtl(View view)
    {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static void makeOptionalFitsSystemWindows(View view)
    {
        try
        {
            Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method.isAccessible())
            {
                method.setAccessible(true);
            }
            method.invoke(view, new Object[0]);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            return;
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            return;
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            return;
        }
    }

    static 
    {
        try
        {
            Method method = android/view/View.getDeclaredMethod("computeFitSystemWindows", new Class[] {
                android/graphics/Rect, android/graphics/Rect
            });
            sComputeFitSystemWindowsMethod = method;
            if (!method.isAccessible())
            {
                sComputeFitSystemWindowsMethod.setAccessible(true);
            }
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
        }
    }
}
