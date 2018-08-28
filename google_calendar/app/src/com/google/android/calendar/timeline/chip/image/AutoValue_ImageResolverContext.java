// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;

import android.content.Context;

// Referenced classes of package com.google.android.calendar.timeline.chip.image:
//            ImageResolverContext

public final class AutoValue_ImageResolverContext extends ImageResolverContext
{

    private final Context context;
    private final int preferredHeight;
    private final int preferredWidth;

    public AutoValue_ImageResolverContext(Context context1, int i, int j)
    {
        if (context1 == null)
        {
            throw new NullPointerException("Null context");
        } else
        {
            context = context1;
            preferredWidth = i;
            preferredHeight = j;
            return;
        }
    }

    public final Context context()
    {
        return context;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ImageResolverContext)
            {
                if (!context.equals(((ImageResolverContext) (obj = (ImageResolverContext)obj)).context()) || preferredWidth != ((ImageResolverContext) (obj)).preferredWidth() || preferredHeight != ((ImageResolverContext) (obj)).preferredHeight())
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
        return ((context.hashCode() ^ 0xf4243) * 0xf4243 ^ preferredWidth) * 0xf4243 ^ preferredHeight;
    }

    public final int preferredHeight()
    {
        return preferredHeight;
    }

    public final int preferredWidth()
    {
        return preferredWidth;
    }

    public final String toString()
    {
        String s = String.valueOf(context);
        int i = preferredWidth;
        int j = preferredHeight;
        return (new StringBuilder(String.valueOf(s).length() + 87)).append("ImageResolverContext{context=").append(s).append(", preferredWidth=").append(i).append(", preferredHeight=").append(j).append("}").toString();
    }
}
