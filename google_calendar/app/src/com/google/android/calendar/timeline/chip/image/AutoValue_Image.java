// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;

import com.android.bitmap.ReusableBitmap;
import com.google.android.apps.calendar.graphics.RtlMirroring;

// Referenced classes of package com.google.android.calendar.timeline.chip.image:
//            Image

public final class AutoValue_Image extends Image
{

    private final ReusableBitmap reusableBitmap;
    private final RtlMirroring rtlMirroring;

    public AutoValue_Image(ReusableBitmap reusablebitmap, RtlMirroring rtlmirroring)
    {
        if (reusablebitmap == null)
        {
            throw new NullPointerException("Null reusableBitmap");
        }
        reusableBitmap = reusablebitmap;
        if (rtlmirroring == null)
        {
            throw new NullPointerException("Null rtlMirroring");
        } else
        {
            rtlMirroring = rtlmirroring;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof Image)
            {
                if (!reusableBitmap.equals(((Image) (obj = (Image)obj)).reusableBitmap()) || !rtlMirroring.equals(((Image) (obj)).rtlMirroring()))
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
        return (reusableBitmap.hashCode() ^ 0xf4243) * 0xf4243 ^ rtlMirroring.hashCode();
    }

    public final ReusableBitmap reusableBitmap()
    {
        return reusableBitmap;
    }

    public final RtlMirroring rtlMirroring()
    {
        return rtlMirroring;
    }

    public final String toString()
    {
        String s = String.valueOf(reusableBitmap);
        String s1 = String.valueOf(rtlMirroring);
        return (new StringBuilder(String.valueOf(s).length() + 37 + String.valueOf(s1).length())).append("Image{reusableBitmap=").append(s).append(", rtlMirroring=").append(s1).append("}").toString();
    }
}
