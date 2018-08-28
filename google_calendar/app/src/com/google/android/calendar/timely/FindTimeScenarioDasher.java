// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeScenario, FindTimeUtil

final class FindTimeScenarioDasher
    implements FindTimeScenario
{

    FindTimeScenarioDasher()
    {
    }

    public final boolean isEnabled(Context context, CalendarListEntry calendarlistentry)
    {
        context = calendarlistentry.getDescriptor().account;
        boolean flag;
        if (!AccountUtil.isGoogleAccount(context))
        {
            flag = false;
        } else
        {
            Object obj = SettingsCache.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            obj = (ImmutableMap)Futures.getUnchecked(((ListenableFutureCache)obj).getValueAsync());
            if (obj == null || !((ImmutableMap) (obj)).containsKey(context))
            {
                flag = false;
            } else
            {
                flag = AccountUtils.isDasher((Settings)((ImmutableMap) (obj)).get(context));
            }
        }
        return flag && FindTimeUtil.isCalendarTypeSupported(calendarlistentry);
    }
}
