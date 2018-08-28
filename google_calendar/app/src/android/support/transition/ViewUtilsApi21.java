// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Matrix;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.transition:
//            ViewUtilsApi19

class ViewUtilsApi21 extends ViewUtilsApi19
{

    private static Method sTransformMatrixToGlobalMethod;
    private static boolean sTransformMatrixToGlobalMethodFetched;
    private static Method sTransformMatrixToLocalMethod;
    private static boolean sTransformMatrixToLocalMethodFetched;

    ViewUtilsApi21()
    {
    }

    public final void transformMatrixToGlobal(View view, Matrix matrix)
    {
        if (!sTransformMatrixToGlobalMethodFetched)
        {
            try
            {
                Method method = android/view/View.getDeclaredMethod("transformMatrixToGlobal", new Class[] {
                    android/graphics/Matrix
                });
                sTransformMatrixToGlobalMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sTransformMatrixToGlobalMethodFetched = true;
        }
        if (sTransformMatrixToGlobalMethod == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        sTransformMatrixToGlobalMethod.invoke(view, new Object[] {
            matrix
        });
        return;
        view;
        throw new RuntimeException(view.getCause());
        view;
    }

    public final void transformMatrixToLocal(View view, Matrix matrix)
    {
        if (!sTransformMatrixToLocalMethodFetched)
        {
            try
            {
                Method method = android/view/View.getDeclaredMethod("transformMatrixToLocal", new Class[] {
                    android/graphics/Matrix
                });
                sTransformMatrixToLocalMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sTransformMatrixToLocalMethodFetched = true;
        }
        if (sTransformMatrixToLocalMethod == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        sTransformMatrixToLocalMethod.invoke(view, new Object[] {
            matrix
        });
        return;
        view;
        throw new RuntimeException(view.getCause());
        view;
    }
}
