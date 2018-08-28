// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FutureCallback;

final class val.pendingResult
    implements FutureCallback
{

    private final android.content.ult val$pendingResult;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e("ObsoleteDataCleanerBR", throwable, "Obsolete data removal failed.", new Object[0]);
        val$pendingResult.pendingResult();
    }

    public final void onSuccess(Object obj)
    {
        LogUtils.d("ObsoleteDataCleanerBR", "Obsolete data removal succeeded.", new Object[0]);
        val$pendingResult.pendingResult();
    }

    ()
    {
        val$pendingResult = _pcls1;
        super();
    }
}
