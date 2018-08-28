// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;


// Referenced classes of package com.android.volley:
//            Request, Response

final class mRunnable
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
                if (mRequest)
                {
                    request.mEventLog.mRequest("intermediate-response", Thread.currentThread().getId());
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

    public (Request request, Response response, Runnable runnable)
    {
        mRequest = request;
        mResponse = response;
        mRunnable = runnable;
    }
}
