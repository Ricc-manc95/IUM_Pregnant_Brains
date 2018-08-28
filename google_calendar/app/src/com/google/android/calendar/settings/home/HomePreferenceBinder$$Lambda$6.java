// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.accounts.Account;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceGroup;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.home:
//            HomeViewModel, CalendarListItemViewModel, HomePreferenceBinder

final class arg._cls6
    implements android.support.v7.preference.tener
{

    private final HomePreferenceBinder arg$1;
    private final HomeViewModel arg$2;
    private final Account arg$3;
    private final PreferenceCategory arg$4;
    private final Preference arg$5;
    private final Collection arg$6;

    public final boolean onPreferenceClick(Preference preference)
    {
        preference = arg$1;
        Object obj = arg$2;
        Account account = arg$3;
        PreferenceCategory preferencecategory = arg$4;
        Preference preference1 = arg$5;
        Collection collection = arg$6;
        ((HomeViewModel) (obj)).expandable.remove(account);
        ((HomeViewModel) (obj)).expanded.add(account.name);
        ((HomeViewModel) (obj)).updateCalendarMap();
        preferencecategory.removePreferenceInt(preference1);
        if (((Preference) (preferencecategory)).mListener != null)
        {
            ((Preference) (preferencecategory)).mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TO74PB6CLP6ARJ3CKNL0SJ5CPIN4PBECDIJMAAM0();
        }
        obj = ((HomeViewModel) (obj)).calendarMap.get(account).iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            CalendarListItemViewModel calendarlistitemviewmodel = (CalendarListItemViewModel)((Iterator) (obj)).next();
            if (!collection.contains(calendarlistitemviewmodel))
            {
                preference.addPreferenceForCalendar(preferencecategory, calendarlistitemviewmodel);
            }
        } while (true);
        preference.updateColors();
        return true;
    }

    er(HomePreferenceBinder homepreferencebinder, HomeViewModel homeviewmodel, Account account, PreferenceCategory preferencecategory, Preference preference, Collection collection)
    {
        arg$1 = homepreferencebinder;
        arg$2 = homeviewmodel;
        arg$3 = account;
        arg$4 = preferencecategory;
        arg$5 = preference;
        arg$6 = collection;
    }
}
