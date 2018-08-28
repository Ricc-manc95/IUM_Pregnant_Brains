// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.transition:
//            ViewGroupUtilsApi18

final class ViewGroupUtils
{

    static void suppressLayout(ViewGroup viewgroup, boolean flag)
    {
        ViewGroupUtilsApi18.fetchSuppressLayoutMethod();
        if (ViewGroupUtilsApi18.sSuppressLayoutMethod == null)
        {
            break MISSING_BLOCK_LABEL_28;
        }
        ViewGroupUtilsApi18.sSuppressLayoutMethod.invoke(viewgroup, new Object[] {
            Boolean.valueOf(flag)
        });
        return;
        viewgroup;
        return;
        viewgroup;
    }
}
