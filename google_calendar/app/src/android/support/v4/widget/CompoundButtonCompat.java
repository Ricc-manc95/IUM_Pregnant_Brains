// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat
{

    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;

    public static Drawable getButtonDrawable(CompoundButton compoundbutton)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return compoundbutton.getButtonDrawable();
        }
        if (!sButtonDrawableFieldFetched)
        {
            try
            {
                Field field = android/widget/CompoundButton.getDeclaredField("mButtonDrawable");
                sButtonDrawableField = field;
                field.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception) { }
            sButtonDrawableFieldFetched = true;
        }
        if (sButtonDrawableField == null)
        {
            break MISSING_BLOCK_LABEL_64;
        }
        compoundbutton = (Drawable)sButtonDrawableField.get(compoundbutton);
        return compoundbutton;
        compoundbutton;
        sButtonDrawableField = null;
        return null;
    }
}
