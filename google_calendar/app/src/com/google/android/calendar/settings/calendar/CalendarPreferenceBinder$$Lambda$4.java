// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.calendar;

import android.accounts.Account;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.notification.Notification;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.home.CalendarViewModel;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableCollection;
import java.util.List;

// Referenced classes of package com.google.android.calendar.settings.calendar:
//            CalendarPreferenceBinder

final class arg._cls3
    implements android.support.v7.preference.er
{

    private final CalendarPreferenceBinder arg$1;
    private final int arg$2;
    private final boolean arg$3;

    public final boolean onPreferenceChange(Preference preference, Object obj)
    {
        CalendarPreferenceBinder calendarpreferencebinder = arg$1;
        int i = arg$2;
        boolean flag = arg$3;
        if ("no".equals(obj))
        {
            if (i >= 0)
            {
                obj = calendarpreferencebinder.viewModel;
                if (flag)
                {
                    preference = ((CalendarViewModel) (obj)).allDayNotifications;
                } else
                {
                    preference = ((CalendarViewModel) (obj)).timedNotifications;
                }
                preference.remove(i);
                ((CalendarViewModel) (obj)).updateStore(flag);
                calendarpreferencebinder.bind(calendarpreferencebinder.viewModel);
            }
            return true;
        }
        if ("custom".equals(obj))
        {
            preference = SettingsShims.instance;
            obj = calendarpreferencebinder.context;
            arg._cls3 _lcls3 = new <init>(calendarpreferencebinder, flag, i);
            String s = calendarpreferencebinder.viewModel.calendar.getDescriptor().account.type;
            preference.createCustomNotificationDialog(((android.content.Context) (obj)), _lcls3, flag, AccountUtil.EXCHANGE_TYPES.contains(s)).show(calendarpreferencebinder.fragment.mFragmentManager, null);
            return true;
        } else
        {
            preference = ((String)obj).split(",");
            calendarpreferencebinder.setOrAddNotification(flag, i, new Notification(Notification.checkMethod(Integer.parseInt(preference[0])), Integer.parseInt(preference[1])));
            return true;
        }
    }

    (CalendarPreferenceBinder calendarpreferencebinder, int i, boolean flag)
    {
        arg$1 = calendarpreferencebinder;
        arg$2 = i;
        arg$3 = flag;
    }
}
