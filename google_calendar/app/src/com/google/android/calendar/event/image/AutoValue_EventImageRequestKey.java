// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;


// Referenced classes of package com.google.android.calendar.event.image:
//            EventImageRequestKey

final class AutoValue_EventImageRequestKey extends EventImageRequestKey
{

    private final int height;
    private final EventImage.Resolver resolver;
    private final int width;

    AutoValue_EventImageRequestKey(EventImage.Resolver resolver1, int i, int j)
    {
        if (resolver1 == null)
        {
            throw new NullPointerException("Null resolver");
        } else
        {
            resolver = resolver1;
            width = i;
            height = j;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof EventImageRequestKey)
            {
                if (!resolver.equals(((EventImageRequestKey) (obj = (EventImageRequestKey)obj)).getResolver()) || width != ((EventImageRequestKey) (obj)).getWidth() || height != ((EventImageRequestKey) (obj)).getHeight())
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

    public final int getHeight()
    {
        return height;
    }

    final EventImage.Resolver getResolver()
    {
        return resolver;
    }

    public final int getWidth()
    {
        return width;
    }

    public final int hashCode()
    {
        return ((resolver.hashCode() ^ 0xf4243) * 0xf4243 ^ width) * 0xf4243 ^ height;
    }

    public final String toString()
    {
        String s = String.valueOf(resolver);
        int i = width;
        int j = height;
        return (new StringBuilder(String.valueOf(s).length() + 70)).append("EventImageRequestKey{resolver=").append(s).append(", width=").append(i).append(", height=").append(j).append("}").toString();
    }
}
