// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings.data;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.LoadDetailsConstants;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.Iterator;
import java.util.Set;

public final class DefaultCalendarHelper
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/settings/data/DefaultCalendarHelper);

    private DefaultCalendarHelper()
    {
    }

    private static Optional findCalendarDescriptorForLocalId(Set set, long l)
    {
        set = set.iterator();
_L4:
        if (!set.hasNext()) goto _L2; else goto _L1
_L1:
        Object obj = (CalendarDescriptor)set.next();
        if (((CalendarDescriptor) (obj)).calendarKey == CalendarKey.NONE || ((CalendarDescriptor) (obj)).calendarKey.getLocalId() != l) goto _L4; else goto _L3
_L3:
        if (obj == null)
        {
            throw new NullPointerException();
        }
        set = new Present(obj);
_L6:
        return set;
_L2:
        set = ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, l);
        set = CalendarApi.getApiContentResolver().query(set, LoadDetailsConstants.CALENDARS_PROJECTION, null, null, null);
        obj = (Optional)Cursors.extractSingleEntry(set, new com.google.android.calendar.api.calendarlist.CalendarListStoreUtils..Lambda._cls1(set), "CalendarListEntry");
        set = ((Set) (obj));
        if (obj != null) goto _L6; else goto _L5
_L5:
        set = Absent.INSTANCE;
        return set;
        set;
        LogUtils.w(TAG, set, "Failure to read the calendar descriptor from CP.", new Object[0]);
_L8:
        return Absent.INSTANCE;
        set;
        LogUtils.wtf(TAG, set, "Failure to read the calendar descriptor from CP. ", new Object[0]);
        if (true) goto _L8; else goto _L7
_L7:
    }

    static Optional readDefaultCalendarDescriptorToSharedPrefs(Set set, Context context)
    {
        String s = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultCalendarAccountId", "");
        String s1 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultCalendarAccountType", "");
        String s2 = context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preference_defaultCalendarGlobalId", "");
        Long long1 = Long.valueOf(context.getSharedPreferences("com.google.android.calendar_preferences", 0).getLong("preference_defaultCalendarId", -1L));
        if (s.isEmpty() || s1.isEmpty() || s2.isEmpty())
        {
            if (long1.longValue() != -1L)
            {
                set = findCalendarDescriptorForLocalId(set, long1.longValue());
                if (set.isPresent())
                {
                    writeDefaultCalendarDescriptorToSharedPrefs(context, (CalendarDescriptor)set.get());
                    return set;
                }
            }
            return Absent.INSTANCE;
        }
        if (long1.longValue() == -1L)
        {
            set = CalendarDescriptor.createWithoutLocalId(new Account(s, s1), s2);
            if (set == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(set);
            }
        }
        set = new CalendarDescriptor(new Account(s, s1), s2, CalendarKey.newInstance(long1.longValue()));
        if (set == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(set);
        }
    }

    static void writeDefaultCalendarDescriptorToSharedPrefs(Context context, CalendarDescriptor calendardescriptor)
    {
        if (calendardescriptor == null)
        {
            return;
        }
        SharedPrefs.setSharedPreference(context, "preference_defaultCalendarAccountId", calendardescriptor.account.name);
        SharedPrefs.setSharedPreference(context, "preference_defaultCalendarAccountType", calendardescriptor.account.type);
        SharedPrefs.setSharedPreference(context, "preference_defaultCalendarGlobalId", calendardescriptor.calendarId);
        if (calendardescriptor.calendarKey != CalendarKey.NONE)
        {
            SharedPrefs.setSharedPreference(context, "preference_defaultCalendarId", calendardescriptor.calendarKey.getLocalId());
            return;
        } else
        {
            SharedPrefs.removeSharedPreference(context, "preference_defaultCalendarId");
            return;
        }
    }

}
