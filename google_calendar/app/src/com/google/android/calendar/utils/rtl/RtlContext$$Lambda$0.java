// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.rtl;

import android.content.res.Configuration;

// Referenced classes of package com.google.android.calendar.utils.rtl:
//            RtlContext, RtlUtils

public final class arg._cls1
    implements RtlContext
{

    private final Configuration arg$1;

    public final boolean isRtl()
    {
        return RtlUtils.isLayoutDirectionRtl(arg$1);
    }

    public (Configuration configuration)
    {
        arg$1 = configuration;
    }
}
