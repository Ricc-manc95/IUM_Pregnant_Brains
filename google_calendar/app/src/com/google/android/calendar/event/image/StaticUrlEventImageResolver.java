// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImage

public abstract class StaticUrlEventImageResolver
    implements EventImage.Resolver
{

    public StaticUrlEventImageResolver()
    {
    }

    public final ListenableFuture resolveAsync(Context context, int i, int j)
    {
        return EventImage.newUrlInstanceAsync(context, url());
    }

    abstract String url();
}
