// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.common;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.gms.ListenableFuturePendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.util.concurrent.TimeUnit;

public abstract class FuturePendingResult extends PendingResult
    implements ListenableFuturePendingResult
{

    public final ListenableFutureTask future = new ListenableFutureTask(new .Lambda._cls0());

    public FuturePendingResult()
    {
        class .Lambda._cls0
            implements Callable
        {

            private final FuturePendingResult arg$1;

            public final Object call()
            {
                return arg$1.calculateResult();
            }

            .Lambda._cls0()
            {
                arg$1 = FuturePendingResult.this;
            }
        }

    }

    public final Result await()
    {
        Result result;
        try
        {
            result = (Result)future.get();
        }
        catch (Exception exception)
        {
            return createFailedResult(exception);
        }
        return result;
    }

    public final Result await(long l, TimeUnit timeunit)
    {
        try
        {
            timeunit = (Result)future.get(l, timeunit);
        }
        // Misplaced declaration of an exception variable
        catch (TimeUnit timeunit)
        {
            return createFailedResult(timeunit);
        }
        return timeunit;
    }

    public abstract Result calculateResult()
        throws Exception;

    public final void cancel()
    {
        future.cancel(false);
    }

    public abstract Result createFailedResult(Throwable throwable);

    public final ListenableFuture getFuture()
    {
        return future;
    }

    public final boolean isCanceled()
    {
        return future.isCancelled();
    }

    public final void setResultCallback(final ResultCallback resultCallback)
    {
        ListenableFutureTask listenablefuturetask = future;
        resultCallback = new _cls2();
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (resultCallback == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuturetask.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuturetask, resultCallback), calendarexecutor);
            return;
        }
    }

    private class _cls2
        implements FutureCallback
    {

        private final FuturePendingResult this$0;
        private final ResultCallback val$resultCallback;

        public final void onFailure(Throwable throwable)
        {
            resultCallback.onResult(createFailedResult(throwable));
        }

        public final void onSuccess(Object obj)
        {
            obj = (Result)obj;
            resultCallback.onResult(((Result) (obj)));
        }

        _cls2()
        {
            this$0 = FuturePendingResult.this;
            resultCallback = resultcallback;
            super();
        }
    }

}
