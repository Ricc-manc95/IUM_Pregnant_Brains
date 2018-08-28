// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.hats;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.common.api.PendingResult;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.concurrent.Callable;

public final class arg._cls1
    implements Callable
{

    private final Context arg$1;

    public final Object call()
    {
        Context context = arg$1;
        java.util.List list = Arrays.asList(((com.google.android.calendar.api.settings.)CalendarApi.Settings.list().await()).SettingsList());
        com.google.common.base.Predicate predicate = .instance;
        long l;
        boolean flag;
        if (Iterators.indexOf(list.iterator(), predicate) != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().oolean("dasher_status", flag).y();
        (new BackupManager(context)).dataChanged();
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().ong("dasher_status_expires", l + 0x5265c00L).y();
        (new BackupManager(context)).dataChanged();
        return Boolean.valueOf(flag);
    }

    public sult(Context context)
    {
        arg$1 = context;
    }
}
