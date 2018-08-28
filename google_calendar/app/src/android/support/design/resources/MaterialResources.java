// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;

public final class MaterialResources
{

    public static ColorStateList getColorStateList(Context context, TypedArray typedarray, int i)
    {
        if (typedarray.hasValue(i))
        {
            int j = typedarray.getResourceId(i, 0);
            if (j != 0)
            {
                context = AppCompatResources.getColorStateList(context, j);
                if (context != null)
                {
                    return context;
                }
            }
        }
        return typedarray.getColorStateList(i);
    }

    public static Drawable getDrawable(Context context, TypedArray typedarray, int i)
    {
        if (typedarray.hasValue(i))
        {
            int j = typedarray.getResourceId(i, 0);
            if (j != 0)
            {
                context = AppCompatResources.getDrawable(context, j);
                if (context != null)
                {
                    return context;
                }
            }
        }
        return typedarray.getDrawable(i);
    }
}
