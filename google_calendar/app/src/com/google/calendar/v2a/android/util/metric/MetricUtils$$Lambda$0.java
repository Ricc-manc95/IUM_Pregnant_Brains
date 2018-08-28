// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.google.common.base.Predicate;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            MetricUtils

final class arg._cls3
    implements Callable
{

    private final arg._cls3 arg$1;
    private final Callable arg$2;
    private final Predicate arg$3;

    public final Object call()
    {
        return MetricUtils.lambda$withMetrics$2$MetricUtils(arg$1, arg$2, arg$3);
    }

    ( , Callable callable, Predicate predicate)
    {
        arg$1 = ;
        arg$2 = callable;
        arg$3 = predicate;
    }
}
