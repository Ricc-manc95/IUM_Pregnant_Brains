// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.android.calendarcommon2.LogUtils;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventListController

final class this._cls0
    implements FutureCallback
{

    private final ICalEventListController this$0;

    public final void onFailure(Throwable throwable)
    {
        LogUtils.w(ICalEventListController.TAG, throwable, "Error importing calendar", new Object[0]);
        importAllCallback.onFailure();
        importAllView.setEnabled(true);
    }

    public final void onSuccess(Object obj)
    {
        importAllCallback.onSuccess();
        importAllView.setEnabled(true);
    }

    portAllView()
    {
        this$0 = ICalEventListController.this;
        super();
    }
}
