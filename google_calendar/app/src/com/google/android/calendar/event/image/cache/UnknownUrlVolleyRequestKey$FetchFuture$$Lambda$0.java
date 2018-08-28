// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.cache;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.volley.VolleyRequests;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar.event.image.cache:
//            UnknownUrlVolleyRequestKey

final class arg._cls2
    implements Runnable
{

    private final <init> arg$1;
    private final ListenableFuture arg$2;

    public final void run()
    {
        arg._cls2 _lcls2;
label0:
        {
            _lcls2 = arg$1;
            ListenableFuture listenablefuture = arg$2;
            try
            {
                _lcls2._fld2.mUrlString = (String)listenablefuture.get();
            }
            catch (Throwable throwable)
            {
                LogUtils.e(UnknownUrlVolleyRequestKey.TAG, throwable, "Unable to resolve URL", new Object[0]);
            }
            if (!_lcls2.isCancelled())
            {
                if (_lcls2._fld2.mUrlString != null)
                {
                    break label0;
                }
                _lcls2.setFuture(_lcls2._fld2.getFallbackBytesAsync());
            }
            return;
        }
        ListenableFuture listenablefuture1 = VolleyRequests.loadBytesAsync(_lcls2._fld2.mUrlString);
        _lcls2.ure.set(listenablefuture1);
        listenablefuture1.addListener(new <init>(_lcls2, listenablefuture1), com.google.common.util.concurrent.e);
    }

    ( , ListenableFuture listenablefuture)
    {
        arg$1 = ;
        arg$2 = listenablefuture;
    }
}
