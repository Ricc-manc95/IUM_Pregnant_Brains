// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsClientBase, SettingsApiStore, SettingsClientFutureImpl

final class te extends FuturePendingResult
{

    private final Account account;
    private final SettingsClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        int j = 0;
        Object obj = SettingsClientFutureImpl.this;
        Account account1 = account;
        obj = ((SettingsClientBase) (obj)).api.read(account1);
        int i;
        if (obj != null)
        {
            i = 0;
        } else
        {
            i = 13;
        }
        if (obj != null)
        {
            j = 1;
        }
        return new t>(i, j, ((Settings) (obj)), null);
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new t>(throwable);
    }

    (Account account1)
    {
        this$0 = SettingsClientFutureImpl.this;
        super();
        account = account1;
        CalendarExecutor.API.execute(super.future);
    }
}
