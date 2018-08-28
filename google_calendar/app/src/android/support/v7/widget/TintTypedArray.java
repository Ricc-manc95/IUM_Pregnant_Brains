// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager

public final class TintTypedArray
{

    public final Context mContext;
    public TypedValue mTypedValue;
    public final TypedArray mWrapped;

    public TintTypedArray(Context context, TypedArray typedarray)
    {
        mContext = context;
        mWrapped = typedarray;
    }

    public final ColorStateList getColorStateList(int i)
    {
        if (mWrapped.hasValue(i))
        {
            int j = mWrapped.getResourceId(i, 0);
            if (j != 0)
            {
                ColorStateList colorstatelist = AppCompatResources.getColorStateList(mContext, j);
                if (colorstatelist != null)
                {
                    return colorstatelist;
                }
            }
        }
        return mWrapped.getColorStateList(i);
    }

    public final Drawable getDrawable(int i)
    {
        if (mWrapped.hasValue(i))
        {
            int j = mWrapped.getResourceId(i, 0);
            if (j != 0)
            {
                return AppCompatResources.getDrawable(mContext, j);
            }
        }
        return mWrapped.getDrawable(i);
    }

    public final Drawable getDrawableIfKnown(int i)
    {
        if (mWrapped.hasValue(i))
        {
            i = mWrapped.getResourceId(i, 0);
            if (i != 0)
            {
                return AppCompatDrawableManager.get().getDrawable(mContext, i, true);
            }
        }
        return null;
    }
}
