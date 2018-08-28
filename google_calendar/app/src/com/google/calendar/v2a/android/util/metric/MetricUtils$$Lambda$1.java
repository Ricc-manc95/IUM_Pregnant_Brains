// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            MetricUtils

final class arg._cls3
    implements Supplier
{

    private final arg._cls3 arg$1;
    private final Supplier arg$2;
    private final Predicate arg$3;

    public final Object get()
    {
        return MetricUtils.lambda$withMetrics$3$MetricUtils(arg$1, arg$2, arg$3);
    }

    ( , Supplier supplier, Predicate predicate)
    {
        arg$1 = ;
        arg$2 = supplier;
        arg$3 = predicate;
    }
}
