// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event.image.cache:
//            UnknownUrlVolleyRequestKey

final class arg._cls2
    implements Runnable
{

    private final arg._cls2 arg$1;
    private final ListenableFuture arg$2;

    public final void run()
    {
        arg._cls2 _lcls2 = arg$1;
        ListenableFuture listenablefuture = arg$2;
        try
        {
            _lcls2.set((byte[])listenablefuture.get());
            return;
        }
        catch (Throwable throwable)
        {
            LogUtils.e(UnknownUrlVolleyRequestKey.TAG, throwable, "Unable to perform Volley request", new Object[0]);
        }
        _lcls2.setFuture(_lcls2._fld2.getFallbackBytesAsync());
    }

    ( , ListenableFuture listenablefuture)
    {
        arg$1 = ;
        arg$2 = listenablefuture;
    }
}
