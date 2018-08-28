// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import com.android.bitmap.RequestKey;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.util.concurrent.ListenableFuture;

public class BitmapCallbacks
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/event/image/BitmapCallbacks);

    public BitmapCallbacks()
    {
    }

    static final void lambda$listen$0$BitmapCallbacks(com.android.bitmap.RequestKey.Callback callback, RequestKey requestkey, ListenableFuture listenablefuture)
    {
        try
        {
            callback.byteArrayCreated(requestkey, (byte[])listenablefuture.get());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ListenableFuture listenablefuture)
        {
            LogUtils.e(TAG, listenablefuture, "Unable to load bitmap bytes", new Object[0]);
        }
        callback.byteArrayCreated(requestkey, null);
    }

    static final void lambda$listen$1$BitmapCallbacks(ListenableFuture listenablefuture)
    {
        listenablefuture.cancel(true);
    }

    public static com.android.bitmap.RequestKey.Cancelable listen(RequestKey requestkey, com.android.bitmap.RequestKey.Callback callback, ListenableFuture listenablefuture)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final com.android.bitmap.RequestKey.Callback arg$1;
            private final RequestKey arg$2;
            private final ListenableFuture arg$3;

            public final void run()
            {
                BitmapCallbacks.lambda$listen$0$BitmapCallbacks(arg$1, arg$2, arg$3);
            }

            .Lambda._cls0(com.android.bitmap.RequestKey.Callback callback, RequestKey requestkey, ListenableFuture listenablefuture)
            {
                arg$1 = callback;
                arg$2 = requestkey;
                arg$3 = listenablefuture;
            }
        }

        listenablefuture.addListener(new .Lambda._cls0(callback, requestkey, listenablefuture), CalendarExecutor.MAIN);
        class .Lambda._cls1
            implements com.android.bitmap.RequestKey.Cancelable
        {

            private final ListenableFuture arg$1;

            public final void cancel()
            {
                BitmapCallbacks.lambda$listen$1$BitmapCallbacks(arg$1);
            }

            .Lambda._cls1(ListenableFuture listenablefuture)
            {
                arg$1 = listenablefuture;
            }
        }

        return new .Lambda._cls1(listenablefuture);
    }

}
