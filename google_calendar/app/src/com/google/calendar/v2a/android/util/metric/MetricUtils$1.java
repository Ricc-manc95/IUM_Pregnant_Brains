// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.google.common.base.Function;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            MetricUtils

final class val.resultMapping
    implements FutureCallback
{

    private final tricContext val$metricContext;
    private final Function val$resultMapping;

    public final void onFailure(Throwable throwable)
    {
        tricContext triccontext = val$metricContext;
        if (MetricUtils.isCancellationException(throwable))
        {
            throwable = sult.CANCEL;
        } else
        {
            throwable = sult.FAILURE;
        }
        triccontext.finish(throwable);
    }

    public final void onSuccess(Object obj)
    {
        val$metricContext.finish((sult)val$resultMapping.apply(obj));
    }

    tricContext()
    {
        val$metricContext = triccontext;
        val$resultMapping = function;
        super();
    }
}
