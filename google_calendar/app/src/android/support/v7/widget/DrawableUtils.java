// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.widget:
//            ThemeUtils

public final class DrawableUtils
{

    public static final Rect INSETS_NONE = new Rect();
    private static Class sInsetsClazz;

    public static boolean canSafelyMutateDrawable(Drawable drawable)
    {
        do
        {
            if (drawable instanceof DrawableContainer)
            {
                drawable = drawable.getConstantState();
                if (drawable instanceof android.graphics.drawable.DrawableContainer.DrawableContainerState)
                {
                    drawable = ((android.graphics.drawable.DrawableContainer.DrawableContainerState)drawable).getChildren();
                    int j = drawable.length;
                    for (int i = 0; i < j; i++)
                    {
                        if (!canSafelyMutateDrawable(drawable[i]))
                        {
                            return false;
                        }
                    }

                }
                break;
            }
            if (drawable instanceof WrappedDrawable)
            {
                drawable = ((WrappedDrawable)drawable).getWrappedDrawable();
                continue;
            }
            if (drawable instanceof DrawableWrapper)
            {
                drawable = ((DrawableWrapper)drawable).mDrawable;
                continue;
            }
            if (!(drawable instanceof ScaleDrawable))
            {
                break;
            }
            drawable = ((ScaleDrawable)drawable).getDrawable();
        } while (true);
        return true;
    }

    static void fixDrawable(Drawable drawable)
    {
        if (android.os.Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName()))
        {
            int ai[] = drawable.getState();
            if (ai == null || ai.length == 0)
            {
                drawable.setState(ThemeUtils.CHECKED_STATE_SET);
            } else
            {
                drawable.setState(ThemeUtils.EMPTY_STATE_SET);
            }
            drawable.setState(ai);
        }
    }

    public static Rect getOpticalBounds(Drawable drawable)
    {
        if (sInsetsClazz == null) goto _L2; else goto _L1
_L1:
        Object obj = drawable;
        Object obj1;
        if (drawable instanceof WrappedDrawable)
        {
            obj = ((WrappedDrawable)drawable).getWrappedDrawable();
        }
        obj1 = obj.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(obj, new Object[0]);
        if (obj1 == null) goto _L2; else goto _L3
_L3:
        Field afield[];
        int j;
        obj = new Rect();
        afield = sInsetsClazz.getFields();
        j = afield.length;
        int i = 0;
_L17:
        drawable = ((Drawable) (obj));
        if (i >= j) goto _L5; else goto _L4
_L4:
        drawable = afield[i];
        String s = drawable.getName();
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 4: default 271
    //                   -1383228885: 192
    //                   115029: 160
    //                   3317767: 144
    //                   108511772: 176;
           goto _L6 _L7 _L8 _L9 _L10
_L9:
        if (s.equals("left"))
        {
            byte0 = 0;
        }
          goto _L6
_L8:
        if (s.equals("top"))
        {
            byte0 = 1;
        }
          goto _L6
_L10:
        if (s.equals("right"))
        {
            byte0 = 2;
        }
          goto _L6
_L7:
        if (s.equals("bottom"))
        {
            byte0 = 3;
        }
          goto _L6
_L12:
        obj.left = drawable.getInt(obj1);
          goto _L11
        drawable;
        Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
_L2:
        drawable = INSETS_NONE;
_L5:
        return drawable;
_L13:
        obj.top = drawable.getInt(obj1);
          goto _L11
_L14:
        obj.right = drawable.getInt(obj1);
          goto _L11
_L15:
        obj.bottom = drawable.getInt(obj1);
          goto _L11
_L6:
        byte0;
        JVM INSTR tableswitch 0 3: default 304
    //                   0 208
    //                   1 235
    //                   2 247
    //                   3 259;
           goto _L11 _L12 _L13 _L14 _L15
_L11:
        i++;
        if (true) goto _L17; else goto _L16
_L16:
    }

    public static android.graphics.PorterDuff.Mode parseTintMode(int i, android.graphics.PorterDuff.Mode mode)
    {
        switch (i)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        default:
            return mode;

        case 3: // '\003'
            return android.graphics.PorterDuff.Mode.SRC_OVER;

        case 5: // '\005'
            return android.graphics.PorterDuff.Mode.SRC_IN;

        case 9: // '\t'
            return android.graphics.PorterDuff.Mode.SRC_ATOP;

        case 14: // '\016'
            return android.graphics.PorterDuff.Mode.MULTIPLY;

        case 15: // '\017'
            return android.graphics.PorterDuff.Mode.SCREEN;

        case 16: // '\020'
            return android.graphics.PorterDuff.Mode.ADD;
        }
    }

    static 
    {
        try
        {
            sInsetsClazz = Class.forName("android.graphics.Insets");
        }
        catch (ClassNotFoundException classnotfoundexception) { }
    }
}
