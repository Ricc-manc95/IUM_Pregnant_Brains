// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.search;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FutureCallback;
import java.util.Collection;

// Referenced classes of package com.google.android.calendar.search:
//            TaskSearchHandler, OnSearchQueryFinishedListener

final class val.listener
    implements FutureCallback
{

    private final OnSearchQueryFinishedListener val$listener;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.e(TaskSearchHandler.TAG, throwable, "Tasks loading failed", new Object[0]);
        val$listener.onSearchQueryFinished(null);
    }

    public final void onSuccess(Object obj)
    {
        obj = (Collection)obj;
        val$listener.onSearchQueryFinished(new earchResults(((Collection) (obj))));
    }

    dListener()
    {
        val$listener = onsearchqueryfinishedlistener;
        super();
    }
}
