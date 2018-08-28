// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.prefs;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.utils.account.PrimaryAccountSelector;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Map;

public class PrefService
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/prefs/PrefService);
    public static PrefService instance;
    private final PrimaryAccountSelector accountSelector;
    public CalendarColor holidaysColor;
    public Subscription settingsSubscription;

    public PrefService(PrimaryAccountSelector primaryaccountselector)
    {
        holidaysColor = CalendarApi.getColorFactory().defaultHolidayColor();
        accountSelector = primaryaccountselector;
    }

    public static PrefService getInstance(Context context)
    {
        if (instance == null)
        {
            if (PrimaryAccountSelector.instance == null)
            {
                PrimaryAccountSelector.instance = new PrimaryAccountSelector(context);
            }
            instance = new PrefService(PrimaryAccountSelector.instance);
        }
        return instance;
    }

    final Settings getPrimarySettings(Map map)
    {
        if (map == null)
        {
            LogUtils.e(TAG, "Settings map is null", new Object[0]);
            return null;
        }
        android.accounts.Account account = accountSelector.account;
        if (account == null)
        {
            LogUtils.w(TAG, "Primary account is null", new Object[0]);
            return null;
        } else
        {
            return (Settings)map.get(account);
        }
    }

    public final void setHolidaysColor(CalendarColor calendarcolor)
    {
        if (calendarcolor == null)
        {
            LogUtils.e(TAG, "Won't set null color", new Object[0]);
            return;
        }
        Object obj = SettingsCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = ((ListenableFutureCache)obj).getValueAsync();
        class .Lambda._cls1
            implements Consumer
        {

            private final PrefService arg$1;
            private final CalendarColor arg$2;

            public final void accept(Object obj1)
            {
                PrefService prefservice = arg$1;
                CalendarColor calendarcolor1 = arg$2;
                obj1 = prefservice.getPrimarySettings((ImmutableMap)obj1);
                if (obj1 instanceof GoogleSettings)
                {
                    obj1 = CalendarApi.SettingsFactory.modifySettings(((Settings) (obj1)));
                    if (obj1 instanceof GoogleSettingsModifications)
                    {
                        ((GoogleSettingsModifications)obj1).setHolidayColor(calendarcolor1);
                        CalendarApi.Settings.update(((com.google.android.calendar.api.settings.SettingsModifications) (obj1)));
                    }
                }
            }

            .Lambda._cls1(CalendarColor calendarcolor)
            {
                arg$1 = PrefService.this;
                arg$2 = calendarcolor;
            }
        }

        com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newFailureLoggingCallback(new .Lambda._cls1(calendarcolor), TAG, "Failed to save holiday color", new Object[0]);
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (futurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), futurecallback), directexecutor);
            holidaysColor = calendarcolor;
            return;
        }
    }

}
