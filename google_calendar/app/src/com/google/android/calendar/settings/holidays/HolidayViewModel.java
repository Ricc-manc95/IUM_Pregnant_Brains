// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.holidays;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.settings.holidays:
//            AutoValue_HolidayViewModel_HolidayCalendar

public final class HolidayViewModel
{

    public final Context context;
    public List countryCalendars;
    public Map countryHolidaysById;
    public final Map countrySubscriptions = new HashMap();
    public List religiousCalendars;
    public Map religiousHolidaysById;
    public final Map religiousSubscriptions = new HashMap();

    public HolidayViewModel(Context context1, ImmutableMap immutablemap, com.google.calendar.v2.libs.proto.DirectoryProto.Directory directory, List list)
    {
        countryCalendars = new ArrayList();
        religiousCalendars = new ArrayList();
        countryHolidaysById = new HashMap();
        religiousHolidaysById = new HashMap();
        context = context1;
        if (directory != null)
        {
            com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry directoryentry;
            AutoValue_HolidayViewModel_HolidayCalendar autovalue_holidayviewmodel_holidaycalendar1;
            for (context1 = directory.countryHoliday_.iterator(); context1.hasNext(); countryHolidaysById.put(directoryentry.id_, autovalue_holidayviewmodel_holidaycalendar1))
            {
                directoryentry = (com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry)context1.next();
                autovalue_holidayviewmodel_holidaycalendar1 = new AutoValue_HolidayViewModel_HolidayCalendar(directoryentry.id_, directoryentry.name_);
                countryCalendars.add(autovalue_holidayviewmodel_holidaycalendar1);
            }

            AutoValue_HolidayViewModel_HolidayCalendar autovalue_holidayviewmodel_holidaycalendar;
            for (context1 = directory.religionHoliday_.iterator(); context1.hasNext(); religiousHolidaysById.put(((com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry) (directory)).id_, autovalue_holidayviewmodel_holidaycalendar))
            {
                directory = (com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry)context1.next();
                autovalue_holidayviewmodel_holidaycalendar = new AutoValue_HolidayViewModel_HolidayCalendar(((com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry) (directory)).id_, ((com.google.calendar.v2.libs.proto.DirectoryProto.DirectoryEntry) (directory)).name_);
                religiousCalendars.add(autovalue_holidayviewmodel_holidaycalendar);
            }

        }
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
                countrySubscriptions.put(immutablemap.getDescriptor(), new HashSet());
                religiousSubscriptions.put(immutablemap.getDescriptor(), new HashSet());
            }
        } while (true);
        context1 = list.iterator();
        do
        {
            if (!context1.hasNext())
            {
                break;
            }
            list = (CalendarListEntry)context1.next();
            immutablemap = list.getDescriptor().account;
            if (countrySubscriptions.containsKey(immutablemap))
            {
                directory = list.getDescriptor().calendarId;
                if (countryHolidaysById.containsKey(directory))
                {
                    ((Set)countrySubscriptions.get(immutablemap)).add((HolidayCalendar)countryHolidaysById.get(directory));
                } else
                if (religiousHolidaysById.containsKey(directory))
                {
                    ((Set)religiousSubscriptions.get(immutablemap)).add((HolidayCalendar)religiousHolidaysById.get(directory));
                } else
                if (CalendarType.isHolidayCalendar(directory))
                {
                    list = new AutoValue_HolidayViewModel_HolidayCalendar(list.getDescriptor().calendarId, list.getDisplayName());
                    ((Set)countrySubscriptions.get(immutablemap)).add(list);
                    countryCalendars.add(list);
                    countryHolidaysById.put(directory, list);
                }
            }
        } while (true);
        class .Lambda._cls0
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls0();

            public final int compare(Object obj, Object obj1)
            {
                obj = (HolidayCalendar)obj;
                obj1 = (HolidayCalendar)obj1;
                return ((HolidayCalendar) (obj)).getDisplayName().compareTo(((HolidayCalendar) (obj1)).getDisplayName());
            }


            private .Lambda._cls0()
            {
            }
        }

        Collections.sort(countryCalendars, .Lambda._cls0..instance);
        class .Lambda._cls1
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls1();

            public final int compare(Object obj, Object obj1)
            {
                obj = (HolidayCalendar)obj;
                obj1 = (HolidayCalendar)obj1;
                return ((HolidayCalendar) (obj)).getDisplayName().compareTo(((HolidayCalendar) (obj1)).getDisplayName());
            }


            private .Lambda._cls1()
            {
            }
        }

        Collections.sort(religiousCalendars, .Lambda._cls1..instance);
    }

    static void applySubscriptions(Account account, Set set, Set set1)
    {
        Object obj = set1.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            HolidayCalendar holidaycalendar = (HolidayCalendar)((Iterator) (obj)).next();
            if (!set.contains(holidaycalendar))
            {
                CalendarApi.CalendarList.subscribe(account, holidaycalendar.getId());
                set.add(holidaycalendar);
            }
        } while (true);
        obj = new ArrayList();
        Iterator iterator = set.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            HolidayCalendar holidaycalendar1 = (HolidayCalendar)iterator.next();
            if (!set1.contains(holidaycalendar1))
            {
                ((List) (obj)).add(holidaycalendar1);
            }
        } while (true);
        set1 = (ArrayList)obj;
        int j = set1.size();
        for (int i = 0; i < j;)
        {
            Object obj1 = set1.get(i);
            i++;
            obj1 = (HolidayCalendar)obj1;
            CalendarApi.CalendarList.unsubscribe(account, ((HolidayCalendar) (obj1)).getId());
            set.remove(obj1);
        }

    }

    private class HolidayCalendar
    {

        abstract String getDisplayName();

        abstract String getId();

        HolidayCalendar()
        {
        }
    }

}
