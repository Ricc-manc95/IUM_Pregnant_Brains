// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.button;

import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;

final class MaterialButtonBackgroundDrawable extends RippleDrawable
{

    MaterialButtonBackgroundDrawable(ColorStateList colorstatelist, InsetDrawable insetdrawable, Drawable drawable)
    {
        super(colorstatelist, insetdrawable, drawable);
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        if (getDrawable(0) != null)
        {
            ((GradientDrawable)((LayerDrawable)((InsetDrawable)getDrawable(0)).getDrawable()).getDrawable(0)).setColorFilter(colorfilter);
        }
    }
}
