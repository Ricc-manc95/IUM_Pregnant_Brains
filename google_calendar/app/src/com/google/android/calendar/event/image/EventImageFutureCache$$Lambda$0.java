// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImage, EventImageFutureCache

final class arg._cls3
    implements Function
{

    private final Context arg$1;
    private final int arg$2;
    private final int arg$3;

    public final Object apply(Object obj)
    {
        return EventImageFutureCache.lambda$getFuture$0$EventImageFutureCache(arg$1, arg$2, arg$3, (EventImage)obj);
    }

    (Context context, int i, int j)
    {
        arg$1 = context;
        arg$2 = i;
        arg$3 = j;
    }
}
