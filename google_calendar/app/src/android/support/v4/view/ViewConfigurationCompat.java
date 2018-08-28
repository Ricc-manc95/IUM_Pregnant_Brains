// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat
{

    private static Method sGetScaledScrollFactorMethod = android/view/ViewConfiguration.getDeclaredMethod("getScaledScrollFactor", new Class[0]);

    private static float getLegacyScrollFactor(ViewConfiguration viewconfiguration, Context context)
    {
        if (android.os.Build.VERSION.SDK_INT < 25 || sGetScaledScrollFactorMethod == null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        int i = ((Integer)sGetScaledScrollFactorMethod.invoke(viewconfiguration, new Object[0])).intValue();
        return (float)i;
        viewconfiguration;
        viewconfiguration = new TypedValue();
        if (context.getTheme().resolveAttribute(0x101004d, viewconfiguration, true))
        {
            return viewconfiguration.getDimension(context.getResources().getDisplayMetrics());
        } else
        {
            return 0.0F;
        }
    }

    public static float getScaledHorizontalScrollFactor(ViewConfiguration viewconfiguration, Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            return viewconfiguration.getScaledHorizontalScrollFactor();
        } else
        {
            return getLegacyScrollFactor(viewconfiguration, context);
        }
    }

    public static int getScaledHoverSlop(ViewConfiguration viewconfiguration)
    {
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            return viewconfiguration.getScaledHoverSlop();
        } else
        {
            return viewconfiguration.getScaledTouchSlop() / 2;
        }
    }

    public static float getScaledVerticalScrollFactor(ViewConfiguration viewconfiguration, Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 26)
        {
            return viewconfiguration.getScaledVerticalScrollFactor();
        } else
        {
            return getLegacyScrollFactor(viewconfiguration, context);
        }
    }

    public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration viewconfiguration, Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            return viewconfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        viewconfiguration = context.getResources();
        int i = viewconfiguration.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        return i != 0 && viewconfiguration.getBoolean(i);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT != 25)
        {
            break MISSING_BLOCK_LABEL_22;
        }
        return;
        Exception exception;
        exception;
    }
}
