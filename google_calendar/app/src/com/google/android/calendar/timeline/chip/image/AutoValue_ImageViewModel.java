// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;


// Referenced classes of package com.google.android.calendar.timeline.chip.image:
//            ImageViewModel, ImageResolver

public final class AutoValue_ImageViewModel extends ImageViewModel
{

    private final Object id;
    private final ImageResolver imageResolver;

    public AutoValue_ImageViewModel(Object obj, ImageResolver imageresolver)
    {
        if (obj == null)
        {
            throw new NullPointerException("Null id");
        }
        id = obj;
        if (imageresolver == null)
        {
            throw new NullPointerException("Null imageResolver");
        } else
        {
            imageResolver = imageresolver;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ImageViewModel)
            {
                if (!id.equals(((ImageViewModel) (obj = (ImageViewModel)obj)).id()) || !imageResolver.equals(((ImageViewModel) (obj)).imageResolver()))
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
        return (id.hashCode() ^ 0xf4243) * 0xf4243 ^ imageResolver.hashCode();
    }

    final Object id()
    {
        return id;
    }

    final ImageResolver imageResolver()
    {
        return imageResolver;
    }

    public final String toString()
    {
        String s = String.valueOf(id);
        String s1 = String.valueOf(imageResolver);
        return (new StringBuilder(String.valueOf(s).length() + 35 + String.valueOf(s1).length())).append("ImageViewModel{id=").append(s).append(", imageResolver=").append(s1).append("}").toString();
    }
}
