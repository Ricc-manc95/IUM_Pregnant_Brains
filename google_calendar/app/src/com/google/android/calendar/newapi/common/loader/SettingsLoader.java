// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common.loader;

import android.accounts.Account;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class SettingsLoader extends AsyncTaskLoader
{

    private final Account account;

    public SettingsLoader(Account account1)
    {
        account = account1;
    }

    protected final Object runInBackground(Object aobj[])
    {
        aobj = (com.google.android.calendar.api.settings.SettingsClient.ReadResult)CalendarApi.Settings.read(account).await();
        boolean flag;
        if (((com.google.android.calendar.api.settings.SettingsClient.ReadResult) (aobj)).getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            aobj = ((com.google.android.calendar.api.settings.SettingsClient.ReadResult) (aobj)).getStatus().zzaIk;
            super.success = false;
            super.failureMessage = ((String) (aobj));
            return null;
        } else
        {
            return ((com.google.android.calendar.api.settings.SettingsClient.ReadResult) (aobj)).getSettings();
        }
    }
}
