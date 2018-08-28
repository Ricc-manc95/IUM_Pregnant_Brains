// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.google.android.apps.calendar.graphics.RtlMirroring;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImage

final class AutoValue_EventImage extends EventImage
{

    private final int flair;
    private final RtlMirroring rtlMirroring;
    private final String url;

    AutoValue_EventImage(String s, int i, RtlMirroring rtlmirroring)
    {
        url = s;
        flair = i;
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
            if (obj instanceof EventImage)
            {
                obj = (EventImage)obj;
                if ((url != null ? !url.equals(((EventImage) (obj)).url()) : ((EventImage) (obj)).url() != null) || (flair != ((EventImage) (obj)).flair() || !rtlMirroring.equals(((EventImage) (obj)).rtlMirroring())))
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

    public final int flair()
    {
        return flair;
    }

    public final int hashCode()
    {
        int i;
        if (url == null)
        {
            i = 0;
        } else
        {
            i = url.hashCode();
        }
        return ((i ^ 0xf4243) * 0xf4243 ^ flair) * 0xf4243 ^ rtlMirroring.hashCode();
    }

    public final RtlMirroring rtlMirroring()
    {
        return rtlMirroring;
    }

    public final String toString()
    {
        String s = url;
        int i = flair;
        String s1 = String.valueOf(rtlMirroring);
        return (new StringBuilder(String.valueOf(s).length() + 50 + String.valueOf(s1).length())).append("EventImage{url=").append(s).append(", flair=").append(i).append(", rtlMirroring=").append(s1).append("}").toString();
    }

    public final String url()
    {
        return url;
    }
}
