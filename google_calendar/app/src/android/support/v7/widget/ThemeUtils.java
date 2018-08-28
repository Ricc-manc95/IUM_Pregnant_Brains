// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.TypedValue;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray

final class ThemeUtils
{

    public static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    public static final int DISABLED_STATE_SET[] = {
        0xfefeff62
    };
    public static final int EMPTY_STATE_SET[] = new int[0];
    public static final int FOCUSED_STATE_SET[] = {
        0x101009c
    };
    public static final int PRESSED_STATE_SET[] = {
        0x10100a7
    };
    private static final int TEMP_ARRAY[] = new int[1];
    private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();

    public static int getDisabledThemeAttrColor(Context context, int i)
    {
        Object obj = getThemeAttrColorStateList(context, i);
        if (obj != null && ((ColorStateList) (obj)).isStateful())
        {
            return ((ColorStateList) (obj)).getColorForState(DISABLED_STATE_SET, ((ColorStateList) (obj)).getDefaultColor());
        }
        TypedValue typedvalue = (TypedValue)TL_TYPED_VALUE.get();
        obj = typedvalue;
        if (typedvalue == null)
        {
            obj = new TypedValue();
            TL_TYPED_VALUE.set(obj);
        }
        context.getTheme().resolveAttribute(0x1010033, ((TypedValue) (obj)), true);
        float f = ((TypedValue) (obj)).getFloat();
        i = getThemeAttrColor(context, i);
        return ColorUtils.setAlphaComponent(i, Math.round(f * (float)Color.alpha(i)));
    }

    public static int getThemeAttrColor(Context context, int i)
    {
        TEMP_ARRAY[0] = i;
        context = new TintTypedArray(context, context.obtainStyledAttributes(null, TEMP_ARRAY));
        i = ((TintTypedArray) (context)).mWrapped.getColor(0, 0);
        ((TintTypedArray) (context)).mWrapped.recycle();
        return i;
        Exception exception;
        exception;
        ((TintTypedArray) (context)).mWrapped.recycle();
        throw exception;
    }

    public static ColorStateList getThemeAttrColorStateList(Context context, int i)
    {
        TEMP_ARRAY[0] = i;
        context = new TintTypedArray(context, context.obtainStyledAttributes(null, TEMP_ARRAY));
        ColorStateList colorstatelist = context.getColorStateList(0);
        ((TintTypedArray) (context)).mWrapped.recycle();
        return colorstatelist;
        Exception exception;
        exception;
        ((TintTypedArray) (context)).mWrapped.recycle();
        throw exception;
    }

}
