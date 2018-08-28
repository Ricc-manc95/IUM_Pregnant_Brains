// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

// Referenced classes of package com.android.volley:
//            ResponseDelivery, Request, Response, VolleyError

public final class ExecutorDelivery
    implements ResponseDelivery
{

    private final Executor mResponsePoster;

    public ExecutorDelivery(final Handler handler)
    {
        mResponsePoster = new _cls1();
    }

    public final void postError(Request request, VolleyError volleyerror)
    {
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("post-error", Thread.currentThread().getId());
        }
        volleyerror = new Response(volleyerror);
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, volleyerror, null));
    }

    public final void postResponse(Request request, Response response)
    {
        request.markDelivered();
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("post-response", Thread.currentThread().getId());
        }
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, null));
    }

    public final void postResponse(Request request, Response response, Runnable runnable)
    {
        synchronized (request.mLock)
        {
            request.mResponseDelivered = true;
        }
        if (VolleyLog.MarkerLog.ENABLED)
        {
            request.mEventLog.add("post-response", Thread.currentThread().getId());
        }
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
        return;
        request;
        obj;
        JVM INSTR monitorexit ;
        throw request;
    }

    private class _cls1
        implements Executor
    {

        private final Handler val$handler;

        public final void execute(Runnable runnable)
        {
            handler.post(runnable);
        }

        _cls1()
        {
            handler = handler1;
            super();
        }
    }


    private class ResponseDeliveryRunnable
        implements Runnable
    {

        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public final void run()
        {
            if (mRequest.isCanceled())
            {
                mRequest.finish("canceled-at-delivery");
            } else
            {
                boolean flag;
                if (mResponse.error == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    mRequest.deliverResponse(mResponse.result);
                } else
                {
                    mRequest.deliverError(mResponse.error);
                }
                if (mResponse.intermediate)
                {
                    Request request = mRequest;
                    if (VolleyLog.MarkerLog.ENABLED)
                    {
                        request.mEventLog.add("intermediate-response", Thread.currentThread().getId());
                    }
                } else
                {
                    mRequest.finish("done");
                }
                if (mRunnable != null)
                {
                    mRunnable.run();
                    return;
                }
            }
        }

        public ResponseDeliveryRunnable(Request request, Response response, Runnable runnable)
        {
            mRequest = request;
            mResponse = response;
            mRunnable = runnable;
        }
    }

}
