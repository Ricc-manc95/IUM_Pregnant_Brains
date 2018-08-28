// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.common.loader;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class SettingsMapLoader extends AsyncTaskLoader
{

    public SettingsMapLoader()
    {
    }

    protected final Object runInBackground(Object aobj[])
    {
        aobj = (com.google.android.calendar.api.settings.SettingsClient.ListResult)CalendarApi.Settings.list().await();
        boolean flag;
        if (((com.google.android.calendar.api.settings.SettingsClient.ListResult) (aobj)).getStatus().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            aobj = ((com.google.android.calendar.api.settings.SettingsClient.ListResult) (aobj)).getStatus().zzaIk;
            super.success = false;
            super.failureMessage = ((String) (aobj));
            return null;
        } else
        {
            return SettingsMap.create(((com.google.android.calendar.api.settings.SettingsClient.ListResult) (aobj)).getSettingsList());
        }
    }
}
