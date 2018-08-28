// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.graphics.drawable.DrawableContainer;
import android.util.Log;
import java.lang.reflect.Method;

public final class DrawableUtils
{

    private static Method setConstantStateMethod;
    private static boolean setConstantStateMethodFetched;

    public static boolean setContainerConstantStateV9(DrawableContainer drawablecontainer, android.graphics.drawable.Drawable.ConstantState constantstate)
    {
        if (!setConstantStateMethodFetched)
        {
            try
            {
                Method method = android/graphics/drawable/DrawableContainer.getDeclaredMethod("setConstantState", new Class[] {
                    android/graphics/drawable/DrawableContainer$DrawableContainerState
                });
                setConstantStateMethod = method;
                method.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
            }
            setConstantStateMethodFetched = true;
        }
        if (setConstantStateMethod == null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        setConstantStateMethod.invoke(drawablecontainer, new Object[] {
            constantstate
        });
        return true;
        drawablecontainer;
        Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
        return false;
    }
}
