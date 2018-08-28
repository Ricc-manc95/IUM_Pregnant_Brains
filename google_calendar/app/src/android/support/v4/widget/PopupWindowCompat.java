// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat
{

    private static Field sOverlapAnchorField;
    private static boolean sOverlapAnchorFieldAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    public static void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            popupwindow.setOverlapAnchor(flag);
        } else
        {
            if (!sOverlapAnchorFieldAttempted)
            {
                try
                {
                    Field field = android/widget/PopupWindow.getDeclaredField("mOverlapAnchor");
                    sOverlapAnchorField = field;
                    field.setAccessible(true);
                }
                catch (NoSuchFieldException nosuchfieldexception) { }
                sOverlapAnchorFieldAttempted = true;
            }
            if (sOverlapAnchorField != null)
            {
                try
                {
                    sOverlapAnchorField.set(popupwindow, Boolean.valueOf(flag));
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (PopupWindow popupwindow)
                {
                    return;
                }
            }
        }
    }

    public static void setWindowLayoutType(PopupWindow popupwindow, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            popupwindow.setWindowLayoutType(i);
        } else
        {
            if (!sSetWindowLayoutTypeMethodAttempted)
            {
                try
                {
                    Method method = android/widget/PopupWindow.getDeclaredMethod("setWindowLayoutType", new Class[] {
                        Integer.TYPE
                    });
                    sSetWindowLayoutTypeMethod = method;
                    method.setAccessible(true);
                }
                catch (Exception exception) { }
                sSetWindowLayoutTypeMethodAttempted = true;
            }
            if (sSetWindowLayoutTypeMethod != null)
            {
                try
                {
                    sSetWindowLayoutTypeMethod.invoke(popupwindow, new Object[] {
                        Integer.valueOf(i)
                    });
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (PopupWindow popupwindow)
                {
                    return;
                }
            }
        }
    }
}
