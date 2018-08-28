// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.transition:
//            ViewUtilsApi21

final class ViewUtilsApi22 extends ViewUtilsApi21
{

    private static Method sSetLeftTopRightBottomMethod;
    private static boolean sSetLeftTopRightBottomMethodFetched;

    ViewUtilsApi22()
    {
    }

    public final void setLeftTopRightBottom(View view, int i, int j, int k, int l)
    {
        if (!sSetLeftTopRightBottomMethodFetched)
        {
            try
            {
                Method method = android/view/View.getDeclaredMethod("setLeftTopRightBottom", new Class[] {
                    Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE
                });
                sSetLeftTopRightBottomMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sSetLeftTopRightBottomMethodFetched = true;
        }
        if (sSetLeftTopRightBottomMethod == null)
        {
            break MISSING_BLOCK_LABEL_106;
        }
        sSetLeftTopRightBottomMethod.invoke(view, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(l)
        });
        return;
        view;
        throw new RuntimeException(view.getCause());
        view;
    }
}
