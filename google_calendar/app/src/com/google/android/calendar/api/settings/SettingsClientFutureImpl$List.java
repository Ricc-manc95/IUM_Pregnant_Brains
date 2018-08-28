// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsClientBase, SettingsApiStore, SettingsClientFutureImpl

final class te extends FuturePendingResult
{

    private final SettingsClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        int j = 0;
        Settings asettings[] = api.list();
        boolean flag;
        int i;
        if (asettings != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i = 0;
        } else
        {
            i = 13;
        }
        if (flag)
        {
            j = asettings.length;
        }
        return new t>(i, j, null, asettings);
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new t>(throwable);
    }

    ()
    {
        this$0 = SettingsClientFutureImpl.this;
        super();
        CalendarExecutor.API.execute(super.future);
    }
}
