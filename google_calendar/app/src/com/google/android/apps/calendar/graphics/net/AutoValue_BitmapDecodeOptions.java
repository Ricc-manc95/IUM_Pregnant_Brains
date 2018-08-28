// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.net;


// Referenced classes of package com.google.android.apps.calendar.graphics.net:
//            BitmapDecodeOptions

public final class AutoValue_BitmapDecodeOptions extends BitmapDecodeOptions
{

    private final int maxHeightOrZero;
    private final int maxWidthOrZero;

    public AutoValue_BitmapDecodeOptions(int i, int j)
    {
        maxWidthOrZero = i;
        maxHeightOrZero = j;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof BitmapDecodeOptions)
            {
                if (maxWidthOrZero != ((BitmapDecodeOptions) (obj = (BitmapDecodeOptions)obj)).maxWidthOrZero() || maxHeightOrZero != ((BitmapDecodeOptions) (obj)).maxHeightOrZero())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return (maxWidthOrZero ^ 0xf4243) * 0xf4243 ^ maxHeightOrZero;
    }

    public final int maxHeightOrZero()
    {
        return maxHeightOrZero;
    }

    public final int maxWidthOrZero()
    {
        return maxWidthOrZero;
    }

    public final String toString()
    {
        int i = maxWidthOrZero;
        int j = maxHeightOrZero;
        return (new StringBuilder(76)).append("BitmapDecodeOptions{maxWidthOrZero=").append(i).append(", maxHeightOrZero=").append(j).append("}").toString();
    }
}
