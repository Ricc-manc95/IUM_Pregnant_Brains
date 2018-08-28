// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImage

abstract class ImmediateEventImageResolver
    implements EventImage.Resolver
{

    ImmediateEventImageResolver()
    {
    }

    abstract EventImage getEventImage();

    abstract int getHeight();

    abstract int getWidth();

    public final ListenableFuture resolveAsync(Context context, int i, int j)
    {
        boolean flag = true;
        if (i == getWidth())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalStateException();
        }
        if (j == getHeight())
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalStateException();
        }
        context = getEventImage();
        if (context == null)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(context);
        }
    }
}
