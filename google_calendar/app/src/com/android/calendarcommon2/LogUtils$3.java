// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.android.calendarcommon2:
//            LogUtils

final class val.args
    implements FutureCallback
{

    private final Object val$args[];
    private final String val$format;
    private final String val$tag;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(val$tag, throwable, val$format, val$args);
    }

    public final void onSuccess(Object obj)
    {
    }

    eCallback()
    {
        val$tag = s;
        val$format = s1;
        val$args = aobj;
        super();
    }
}
