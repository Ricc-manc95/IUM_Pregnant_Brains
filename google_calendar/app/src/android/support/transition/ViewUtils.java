// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

// Referenced classes of package android.support.transition:
//            ViewUtilsApi22, ViewUtilsApi21, WindowIdApi18, ViewUtilsBase, 
//            WindowIdImpl

final class ViewUtils
{

    public static final ViewUtilsBase IMPL;
    public static final Property TRANSITION_ALPHA = new _cls1(java/lang/Float, "translationAlpha");
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    static void clearNonTransitionAlpha(View view)
    {
    }

    static WindowIdImpl getWindowId(View view)
    {
        return new WindowIdApi18(view);
    }

    static void saveNonTransitionAlpha(View view)
    {
    }

    static void setTransitionVisibility(View view, int i)
    {
        if (!sViewFlagsFieldFetched)
        {
            int j;
            try
            {
                Field field = android/view/View.getDeclaredField("mViewFlags");
                sViewFlagsField = field;
                field.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception) { }
            sViewFlagsFieldFetched = true;
        }
        if (sViewFlagsField == null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        j = sViewFlagsField.getInt(view);
        sViewFlagsField.setInt(view, j & 0xfffffff3 | i);
        return;
        view;
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 22)
        {
            IMPL = new ViewUtilsApi22();
        } else
        {
            IMPL = new ViewUtilsApi21();
        }
        new _cls2(android/graphics/Rect, "clipBounds");
    }

    private class _cls1 extends Property
    {

        public final Object get(Object obj)
        {
            obj = (View)obj;
            return Float.valueOf(ViewUtils.IMPL.getTransitionAlpha(((View) (obj))));
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (View)obj;
            float f = ((Float)obj1).floatValue();
            ViewUtils.IMPL.setTransitionAlpha(((View) (obj)), f);
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls2 extends Property
    {

        public final Object get(Object obj)
        {
            return ViewCompat.getClipBounds((View)obj);
        }

        public final void set(Object obj, Object obj1)
        {
            ViewCompat.setClipBounds((View)obj, (Rect)obj1);
        }

        _cls2(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
