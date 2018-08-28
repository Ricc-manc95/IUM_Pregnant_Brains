// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;

import android.accounts.Account;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

// Referenced classes of package com.google.android.calendar.api.settings:
//            SettingsClient, SettingsFactory, GoogleSettingsModifications

public final class SettingsUtils
{

    public static void updateTimezoneSettings(Account account, String s)
    {
        account = (SettingsClient.ReadResult)CalendarApi.Settings.read(account).await();
        boolean flag;
        if (account.getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if ((account = CalendarApi.SettingsFactory.modifySettings(account.getSettings())) instanceof GoogleSettingsModifications)
            {
                ((GoogleSettingsModifications)account).setTimezoneId(s);
                CalendarApi.Settings.update(account).await();
                return;
            }
        }
    }
}
