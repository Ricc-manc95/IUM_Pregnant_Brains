// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image:
//            BitmapCallbacks

final class arg._cls1
    implements com.android.bitmap.pCallbacks..Lambda._cls1
{

    private final ListenableFuture arg$1;

    public final void cancel()
    {
        BitmapCallbacks.lambda$listen$1$BitmapCallbacks(arg$1);
    }

    (ListenableFuture listenablefuture)
    {
        arg$1 = listenablefuture;
    }
}
