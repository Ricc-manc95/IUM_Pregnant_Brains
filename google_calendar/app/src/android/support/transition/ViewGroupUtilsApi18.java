// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class ViewGroupUtilsApi18
{

    public static Method sSuppressLayoutMethod;
    private static boolean sSuppressLayoutMethodFetched;

    static void fetchSuppressLayoutMethod()
    {
        if (!sSuppressLayoutMethodFetched)
        {
            try
            {
                Method method = android/view/ViewGroup.getDeclaredMethod("suppressLayout", new Class[] {
                    Boolean.TYPE
                });
                sSuppressLayoutMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sSuppressLayoutMethodFetched = true;
        }
    }

    static void suppressLayout(ViewGroup viewgroup, boolean flag)
    {
        if (!sSuppressLayoutMethodFetched)
        {
            try
            {
                Method method = android/view/ViewGroup.getDeclaredMethod("suppressLayout", new Class[] {
                    Boolean.TYPE
                });
                sSuppressLayoutMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            sSuppressLayoutMethodFetched = true;
        }
        if (sSuppressLayoutMethod == null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        sSuppressLayoutMethod.invoke(viewgroup, new Object[] {
            Boolean.valueOf(flag)
        });
        return;
        viewgroup;
        return;
        viewgroup;
    }
}
