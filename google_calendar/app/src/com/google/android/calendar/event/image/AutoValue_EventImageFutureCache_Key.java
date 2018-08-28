// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;


final class AutoValue_EventImageFutureCache_Key extends EventImageFutureCache.Key
{

    private final int height;
    private final EventImage.Resolver resolver;
    private final int width;

    AutoValue_EventImageFutureCache_Key(EventImage.Resolver resolver1, int i, int j)
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
            if (obj instanceof EventImageFutureCache.Key)
            {
                if (!resolver.equals(((EventImageFutureCache.Key) (obj = (EventImageFutureCache.Key)obj)).resolver()) || width != ((EventImageFutureCache.Key) (obj)).width() || height != ((EventImageFutureCache.Key) (obj)).height())
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
        return ((resolver.hashCode() ^ 0xf4243) * 0xf4243 ^ width) * 0xf4243 ^ height;
    }

    final int height()
    {
        return height;
    }

    final EventImage.Resolver resolver()
    {
        return resolver;
    }

    public final String toString()
    {
        String s = String.valueOf(resolver);
        int i = width;
        int j = height;
        return (new StringBuilder(String.valueOf(s).length() + 53)).append("Key{resolver=").append(s).append(", width=").append(i).append(", height=").append(j).append("}").toString();
    }

    final int width()
    {
        return width;
    }
}
