// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.pagination;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.timely.net.pagination:
//            PageableResult, PaginatingClient

final class arg._cls1
    implements Function
{

    private final PaginatingClient arg$1;

    public final Object apply(Object obj)
    {
        PaginatingClient paginatingclient = arg$1;
        obj = (PageableResult)obj;
        boolean flag;
        if (paginatingclient.extendableResult != null && paginatingclient.extendableResult.hasNextPage())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj = (PageableResult)paginatingclient.extendableResult.merge(obj);
        }
        paginatingclient.extendableResult = ((PageableResult) (obj));
        return paginatingclient.extendableResult;
    }

    (PaginatingClient paginatingclient)
    {
        arg$1 = paginatingclient;
    }
}
