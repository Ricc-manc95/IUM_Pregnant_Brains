// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newapi.common.Loader;
import com.google.android.calendar.newapi.model.CalendarList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventListController

final class this._cls0
    implements com.google.android.calendar.newapi.common.ew
{

    private final ICalEventListController this$0;

    public final void onLoadingFailure(Loader loader, String s)
    {
        LogUtils.e(ICalEventListController.TAG, "Failed to load writable calendar list: %s", new Object[] {
            s
        });
    }

    public final void onLoadingSuccess(Loader loader)
    {
        if (((CalendarList)loader.getResult()).calendars.isEmpty())
        {
            importAllView.setEnabled(false);
        }
    }

    portAllView()
    {
        this$0 = ICalEventListController.this;
        super();
    }
}
