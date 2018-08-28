// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.pagination;

import com.google.android.calendar.timely.net.BaseClient;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely.net.pagination:
//            PageableResult, PageableRequest

public final class PaginatingClient
    implements BaseClient
{

    public PageableResult extendableResult;
    public PageableRequest lastRequest;
    public final BaseClient underlying;

    public PaginatingClient(BaseClient baseclient)
    {
        underlying = baseclient;
    }

    public final ListenableFuture getNextPage()
    {
        boolean flag1 = true;
        boolean flag;
        if (lastRequest != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (extendableResult != null && extendableResult.hasNextPage())
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            lastRequest = (PageableRequest)lastRequest.withTokenFrom(extendableResult);
            PageableRequest pageablerequest = lastRequest;
            class .Lambda._cls0
                implements Function
            {

                private final PaginatingClient arg$1;

                public final Object apply(Object obj)
                {
                    PaginatingClient paginatingclient = arg$1;
                    obj = (PageableResult)obj;
                    boolean flag2;
                    if (paginatingclient.extendableResult != null && paginatingclient.extendableResult.hasNextPage())
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                    if (flag2)
                    {
                        obj = (PageableResult)paginatingclient.extendableResult.merge(obj);
                    }
                    paginatingclient.extendableResult = ((PageableResult) (obj));
                    return paginatingclient.extendableResult;
                }

            .Lambda._cls0()
            {
                arg$1 = PaginatingClient.this;
            }
            }

            return AbstractTransformFuture.create(underlying.sendRequest(pageablerequest), new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }

    public final void injectResponse(Object obj, Object obj1)
    {
        obj = (PageableRequest)obj;
        obj1 = (PageableResult)obj1;
        underlying.injectResponse(obj, obj1);
        lastRequest = ((PageableRequest) (obj));
        extendableResult = ((PageableResult) (obj1));
    }

    public final ListenableFuture sendRequest(PageableRequest pageablerequest)
    {
        lastRequest = pageablerequest;
        extendableResult = null;
        return AbstractTransformFuture.create(underlying.sendRequest(pageablerequest), new .Lambda._cls0(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final volatile ListenableFuture sendRequest(Object obj)
    {
        return sendRequest((PageableRequest)obj);
    }

    public final void wipeCache()
    {
        underlying.wipeCache();
        lastRequest = null;
        extendableResult = null;
    }
}
