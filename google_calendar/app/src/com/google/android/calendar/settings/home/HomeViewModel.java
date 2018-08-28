// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.home;

import android.content.Context;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.calendarlist.common.CalendarListUtils;
import com.google.android.calendar.settings.SettingsShims;
import com.google.android.calendar.settings.birthdays.BirthdayViewModel;
import com.google.android.calendar.settings.general.GeneralPreferenceViewModel;
import com.google.android.calendar.settings.holidays.HolidayViewModel;
import com.google.android.calendar.settings.smartmail.SmartMailViewModel;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.home:
//            ReminderViewModel, CalendarViewModel

public final class HomeViewModel
{

    public final BirthdayViewModel birthdayViewModel;
    public final Multimap calendarMap = new LinkedHashMultimap(16, 2);
    public final Map calendarViewModels = new HashMap();
    private final List calendars;
    private final Context context;
    public final Set expandable = new HashSet();
    public final List expanded = new ArrayList();
    public GeneralPreferenceViewModel generalPreferenceViewModel;
    public final HolidayViewModel holidayViewModel;
    public final Map reminderViewModels = new HashMap();
    public boolean showBirthdays;
    public boolean showHolidays;
    public final SmartMailViewModel smartMailViewModel;

    public HomeViewModel(Context context1, List list, ImmutableMap immutablemap, SettingsShims settingsshims, SmartMailViewModel smartmailviewmodel, BirthdayViewModel birthdayviewmodel, HolidayViewModel holidayviewmodel, 
            GeneralPreferenceViewModel generalpreferenceviewmodel)
    {
        context = context1;
        calendars = list;
        smartMailViewModel = smartmailviewmodel;
        birthdayViewModel = birthdayviewmodel;
        holidayViewModel = holidayviewmodel;
        generalPreferenceViewModel = generalpreferenceviewmodel;
        context1 = (UnmodifiableIterator)((ImmutableCollection)immutablemap.values()).iterator();
        do
        {
            if (!context1.hasNext())
            {
                break;
            }
            immutablemap = (Settings)context1.next();
            if (immutablemap instanceof GoogleSettings)
            {
                reminderViewModels.put(immutablemap.getDescriptor(), new ReminderViewModel((GoogleSettings)immutablemap));
            }
        } while (true);
        for (context1 = list.iterator(); context1.hasNext(); calendarViewModels.put(list.getDescriptor(), new CalendarViewModel(context, settingsshims, list)))
        {
            list = (CalendarListEntry)context1.next();
        }

        updateCalendarMap();
    }

    final void updateCalendarMap()
    {
        Object obj;
        ArrayList arraylist1;
        int i;
        int k;
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])calendars.toArray(new CalendarListEntry[calendars.size()]);
        expandable.clear();
        showBirthdays = false;
        showHolidays = false;
        ArrayList arraylist = new ArrayList();
        if (CalendarListUtils.processCalendars(acalendarlistentry, arraylist, null, true))
        {
            CalendarListUtils.postProcessItems(context, arraylist, context.getString(0x7f1303e2));
            CalendarListUtils.processHiddenCalendars(arraylist, expanded, null);
            Collections.sort(arraylist, new com.google.android.calendar.calendarlist.common.CalendarListUtils.ListItemComparator(AccountsUtil.getGoogleAccounts(context)));
        }
        calendarMap.clear();
        arraylist1 = (ArrayList)arraylist;
        k = arraylist1.size();
        i = 0;
        obj = null;
_L6:
        Object obj1;
        Object obj2;
        int j;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_485;
        }
        obj1 = arraylist1.get(i);
        j = i + 1;
        obj2 = (com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo)obj1;
        obj1 = obj;
        if (obj == null) goto _L2; else goto _L1
_L1:
        obj1 = obj;
        ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj2)).getType();
        JVM INSTR tableswitch 0 4: default 208
    //                   0 299
    //                   1 210
    //                   2 299
    //                   3 299
    //                   4 299;
           goto _L3 _L4 _L5 _L4 _L4 _L4
_L5:
        break; /* Loop/switch isn't completed */
_L3:
        obj1 = obj;
_L2:
        obj = obj1;
        i = j;
        switch (((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj2)).getType())
        {
        default:
            i = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarListItemInfo) (obj2)).getType();
            throw new IllegalStateException((new StringBuilder(27)).append("Unhandled type: ").append(i).toString());

        case 0: // '\0'
            obj = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.AccountItem)obj2).account;
            i = j;
            break;

        case 1: // '\001'
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            if (obj2 instanceof com.google.android.calendar.calendarlist.common.CalendarListUtils.TaskCalendarItem)
            {
                obj = calendarMap;
                obj2 = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.TaskCalendarItem)obj2).account;
                ((Multimap) (obj)).put(obj1, (ReminderViewModel)reminderViewModels.get(obj2));
                obj = obj1;
                i = j;
            } else
            {
                obj = calendarMap;
                obj2 = ((com.google.android.calendar.calendarlist.common.CalendarListUtils.CalendarItem)obj2).calendarListEntry.getDescriptor();
                ((Multimap) (obj)).put(obj1, (CalendarViewModel)calendarViewModels.get(obj2));
                obj = obj1;
                i = j;
            }
            break;

        case 5: // '\005'
            expandable.add(obj1);
            obj = obj1;
            i = j;
            break;

        case 4: // '\004'
            showHolidays = true;
            obj = obj1;
            i = j;
            break;

        case 3: // '\003'
            showBirthdays = true;
            obj = obj1;
            i = j;
            break;

        case 2: // '\002'
            break;
        }
        if (true) goto _L6; else goto _L4
_L4:
        obj1 = null;
          goto _L2
    }
}
