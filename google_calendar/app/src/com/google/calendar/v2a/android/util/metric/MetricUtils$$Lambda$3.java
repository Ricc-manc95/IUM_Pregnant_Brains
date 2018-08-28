// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.metric;

import java.util.List;

// Referenced classes of package com.google.calendar.v2a.android.util.metric:
//            MetricUtils

public final class arg._cls1
    implements ext
{

    private final List arg$1;

    public final void finish(ext ext)
    {
        MetricUtils.lambda$startMeasurement$6$MetricUtils(arg$1, ext);
    }

    public final void finish(boolean flag)
    {
        arg._cls1 _lcls1;
        if (flag)
        {
            _lcls1 = CESS;
        } else
        {
            _lcls1 = LURE;
        }
        finish(_lcls1);
    }

    public ext(List list)
    {
        arg$1 = list;
    }
}
