// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;


// Referenced classes of package com.google.android.calendar.event.image:
//            ImmediateEventImageResolver, EventImage

final class AutoValue_ImmediateEventImageResolver extends ImmediateEventImageResolver
{

    private final EventImage eventImage;
    private final int height;
    private final int width;

    AutoValue_ImmediateEventImageResolver(EventImage eventimage, int i, int j)
    {
        if (eventimage == null)
        {
            throw new NullPointerException("Null eventImage");
        } else
        {
            eventImage = eventimage;
            width = i;
            height = j;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ImmediateEventImageResolver)
            {
                if (!eventImage.equals(((ImmediateEventImageResolver) (obj = (ImmediateEventImageResolver)obj)).getEventImage()) || width != ((ImmediateEventImageResolver) (obj)).getWidth() || height != ((ImmediateEventImageResolver) (obj)).getHeight())
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

    final EventImage getEventImage()
    {
        return eventImage;
    }

    final int getHeight()
    {
        return height;
    }

    final int getWidth()
    {
        return width;
    }

    public final int hashCode()
    {
        return ((eventImage.hashCode() ^ 0xf4243) * 0xf4243 ^ width) * 0xf4243 ^ height;
    }

    public final String toString()
    {
        String s = String.valueOf(eventImage);
        int i = width;
        int j = height;
        return (new StringBuilder(String.valueOf(s).length() + 79)).append("ImmediateEventImageResolver{eventImage=").append(s).append(", width=").append(i).append(", height=").append(j).append("}").toString();
    }
}
