// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.transition:
//            ViewUtilsBase

class ViewUtilsApi19 extends ViewUtilsBase
{

    private static Method sGetTransitionAlphaMethod;
    private static boolean sGetTransitionAlphaMethodFetched;
    private static Method sSetTransitionAlphaMethod;
    private static boolean sSetTransitionAlphaMethodFetched;

    ViewUtilsApi19()
    {
    }

    public final float getTransitionAlpha(View view)
    {
        if (!sGetTransitionAlphaMethodFetched)
        {
            float f;
            IllegalAccessException illegalaccessexception;
            try
            {
                Method method = android/view/View.getDeclaredMethod("getTransitionAlpha", new Class[0]);
                sGetTransitionAlphaMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sGetTransitionAlphaMethodFetched = true;
        }
        if (sGetTransitionAlphaMethod == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        f = ((Float)sGetTransitionAlphaMethod.invoke(view, new Object[0])).floatValue();
        return f;
        view;
        throw new RuntimeException(view.getCause());
        illegalaccessexception;
        return super.getTransitionAlpha(view);
    }

    public final void setTransitionAlpha(View view, float f)
    {
        if (!sSetTransitionAlphaMethodFetched)
        {
            try
            {
                Method method = android/view/View.getDeclaredMethod("setTransitionAlpha", new Class[] {
                    Float.TYPE
                });
                sSetTransitionAlphaMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sSetTransitionAlphaMethodFetched = true;
        }
        if (sSetTransitionAlphaMethod != null)
        {
            try
            {
                sSetTransitionAlphaMethod.invoke(view, new Object[] {
                    Float.valueOf(f)
                });
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
                throw new RuntimeException(view.getCause());
            }
        } else
        {
            view.setAlpha(f);
            return;
        }
    }
}
