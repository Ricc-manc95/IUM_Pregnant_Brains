// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.common.FuturePendingResult;
import com.google.android.gms.common.api.Result;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsModifications, SettingsClientBase, SettingsApiStore, SettingsClientFutureImpl

final class  extends FuturePendingResult
{

    private final SettingsModifications settings;
    private final SettingsClientFutureImpl this$0;

    protected final Result calculateResult()
        throws Exception
    {
        boolean flag = true;
        SettingsClientFutureImpl settingsclientfutureimpl = SettingsClientFutureImpl.this;
        SettingsModifications settingsmodifications = settings;
        if (settingsmodifications.getOriginal() == null)
        {
            return new (13, 0, null, null);
        }
        int i;
        if (((SettingsClientBase) (settingsclientfutureimpl)).api.update(settingsmodifications) != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        return new (0, i, null, null);
    }

    protected final Result createFailedResult(Throwable throwable)
    {
        return new (throwable);
    }

    (SettingsModifications settingsmodifications)
    {
        this$0 = SettingsClientFutureImpl.this;
        super();
        settings = settingsmodifications;
        CalendarExecutor.API.execute(super.future);
    }
}
