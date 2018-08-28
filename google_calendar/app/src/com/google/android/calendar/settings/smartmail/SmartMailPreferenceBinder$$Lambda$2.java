// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.smartmail;

import android.accounts.Account;
import android.content.DialogInterface;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.TwoStatePreference;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.settings.smartmail:
//            SmartMailPreferenceBinder, SmartMailViewModel

final class arg._cls6
    implements android.content.MailPreferenceBinder..Lambda._cls2
{

    private final SmartMailPreferenceBinder arg$1;
    private final Account arg$2;
    private final boolean arg$3;
    private final SwitchPreference arg$4;
    private final ListPreference arg$5;
    private final String arg$6[];

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        SmartMailPreferenceBinder smartmailpreferencebinder = arg$1;
        Account account = arg$2;
        boolean flag = arg$3;
        SwitchPreference switchpreference = arg$4;
        ListPreference listpreference = arg$5;
        String as[] = arg$6;
        SmartMailViewModel smartmailviewmodel = smartmailpreferencebinder.viewModel;
        Map map = smartmailviewmodel.smartMailModes;
        if (flag)
        {
            dialoginterface = com.google.android.calendar.api.settings.SECRET;
        } else
        {
            dialoginterface = com.google.android.calendar.api.settings.SECRET;
        }
        map.put(account, dialoginterface);
        dialoginterface = CalendarApi.SettingsFactory.modifyGoogleSettings((GoogleSettings)smartmailviewmodel.settings.get(account));
        if (flag)
        {
            dialoginterface.setSmartMailMode(com.google.android.calendar.api.settings.SECRET, com.google.android.calendar.api.settings.PCOMING);
        } else
        {
            dialoginterface.setSmartMailMode(com.google.android.calendar.api.settings.MailMode, com.google.android.calendar.api.settings.LL);
        }
        CalendarApi.Settings.update(dialoginterface);
        switchpreference.setChecked(flag);
        if (listpreference != null)
        {
            smartmailpreferencebinder.updateVisibilityMode(listpreference, as, (com.google.android.calendar.api.settings.teVisibilityMode)smartmailpreferencebinder.viewModel.smartMailModes.get(account));
        }
    }

    (SmartMailPreferenceBinder smartmailpreferencebinder, Account account, boolean flag, SwitchPreference switchpreference, ListPreference listpreference, String as[])
    {
        arg$1 = smartmailpreferencebinder;
        arg$2 = account;
        arg$3 = flag;
        arg$4 = switchpreference;
        arg$5 = listpreference;
        arg$6 = as;
    }
}
