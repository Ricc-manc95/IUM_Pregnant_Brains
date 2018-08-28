// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.smartmail;

import android.accounts.Account;
import android.content.Context;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.GoogleSettingsModifications;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.api.settings.SettingsClient;
import com.google.android.calendar.api.settings.SettingsFactory;
import com.google.android.calendar.settings.SettingsShims;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SmartMailViewModel
{

    public final Map settings = new HashMap();
    public final Set shared = new HashSet();
    public final Map smartMailModes = new HashMap();

    public SmartMailViewModel(Context context, ImmutableMap immutablemap, List list)
    {
        immutablemap = (UnmodifiableIterator)((ImmutableCollection)immutablemap.values()).iterator();
        do
        {
            if (!immutablemap.hasNext())
            {
                break;
            }
            Object obj = (Settings)immutablemap.next();
            if (obj instanceof GoogleSettings)
            {
                GoogleSettings googlesettings = (GoogleSettings)obj;
                com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode smartmailmode = googlesettings.getSmartMailMode();
                if (smartmailmode != null)
                {
                    obj = ((Settings) (obj)).getDescriptor();
                    if (SettingsShims.instance.isSmartMailAccount(context, ((Account) (obj))))
                    {
                        settings.put(obj, googlesettings);
                        smartMailModes.put(obj, smartmailmode);
                    }
                }
            }
        } while (true);
        context = list.iterator();
        do
        {
            if (!context.hasNext())
            {
                break;
            }
            immutablemap = (CalendarListEntry)context.next();
            if (immutablemap.isPrimary() && immutablemap.isPotentiallyShared())
            {
                shared.add(immutablemap.getDescriptor().account);
            }
        } while (true);
    }

    final void setMode(Account account, com.google.android.calendar.api.settings.GoogleSettings.SmartMailMode smartmailmode, boolean flag)
    {
        smartMailModes.put(account, smartmailmode);
        SettingsClient settingsclient = CalendarApi.Settings;
        GoogleSettingsModifications googlesettingsmodifications = CalendarApi.SettingsFactory.modifyGoogleSettings((GoogleSettings)settings.get(account));
        if (flag)
        {
            account = com.google.android.calendar.api.settings.GoogleSettings.SmartMailUpdateMode.ALL;
        } else
        {
            account = com.google.android.calendar.api.settings.GoogleSettings.SmartMailUpdateMode.NEW;
        }
        settingsclient.update(googlesettingsmodifications.setSmartMailMode(smartmailmode, account));
    }
}
