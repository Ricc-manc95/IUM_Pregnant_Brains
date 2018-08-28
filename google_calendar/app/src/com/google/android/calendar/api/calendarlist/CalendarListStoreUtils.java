// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.calendarlist;

import android.database.Cursor;
import android.text.TextUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.color.AutoValue_CustomCalendarColor;
import com.google.android.calendar.api.color.ColorFactory;
import com.google.android.calendar.api.common.TimeZoneHelper;
import com.google.android.calendar.api.converters.CalendarAccessLevelConverter;
import com.google.android.calendar.api.event.EnumConverter;
import com.google.android.calendar.api.event.conference.ConferenceTypeStoreUtils;
import com.google.android.calendar.api.event.notification.NotificationsStoreUtils;
import com.google.android.calendar.api.event.notification.NotificationsTimelyStoreUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.CharMatcher;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.calendarlist:
//            CalendarDescriptor, CalendarListEntryImpl, CalendarCategory, CalendarListEntry

public final class CalendarListStoreUtils
{

    static Optional cursorToDescriptor(Cursor cursor)
    {
        long l = cursor.getLong(0);
        String s = cursor.getString(14);
        String s1 = cursor.getString(15);
        cursor = Platform.nullToEmpty(cursor.getString(3));
        class .Lambda._cls0
            implements Function
        {

            private final String arg$1;
            private final long arg$2;

            public final Object apply(Object obj)
            {
                String s2 = arg$1;
                long l1 = arg$2;
                return new CalendarDescriptor((Account)obj, s2, CalendarKey.newInstance(l1));
            }

            .Lambda._cls0(String s, long l)
            {
                arg$1 = s;
                arg$2 = l;
            }
        }

        return AccountUtil.newAccount(s, s1).transform(new .Lambda._cls0(cursor, l));
    }

    static CalendarListEntry cursorToEntity(CalendarDescriptor calendardescriptor, Cursor cursor, com.google.android.apps.calendar.timely.store.TimelyStore.Notifications notifications, Map map)
    {
        Object obj1;
        Object obj = cursorToDescriptor(cursor);
        if (((Optional) (obj)).isPresent())
        {
            calendardescriptor = (CalendarDescriptor)((Optional) (obj)).get();
        }
        String s;
        String s1;
        com.google.android.calendar.api.event.notification.Notification anotification[];
        com.google.android.calendar.api.event.notification.Notification anotification1[];
        CalendarAccessLevel calendaraccesslevel;
        int i;
        int j;
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        if (cursor.getInt(18) == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        s1 = cursor.getString(2);
        obj = cursor.getString(5);
        i = cursor.getInt(4);
        if (AccountUtil.isGoogleAccount(calendardescriptor.account) && obj != null && !((String) (obj)).isEmpty())
        {
            obj = CalendarApi.getColorFactory().createCalendarColor(((String) (obj)), i);
        } else
        {
            obj = new AutoValue_CustomCalendarColor(0xff000000 | i, "");
        }
        if (cursor.getInt(8) == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (cursor.getInt(9) == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (!AccountUtil.isGoogleAccount(calendardescriptor.account))
        {
            flag = true;
        } else
        if (cursor.isNull(19))
        {
            flag = true;
        } else
        if (cursor.getInt(19) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        s = Platform.emptyToNull(cursor.getString(17));
        if (s == null || TimeZoneHelper.isValidTimeZoneId(s))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            s = null;
        }
        anotification = NotificationsTimelyStoreUtils.loadDefaultNotifications(calendardescriptor, false, notifications);
        anotification1 = NotificationsTimelyStoreUtils.loadDefaultNotifications(calendardescriptor, true, notifications);
        if (cursor.getInt(6) != 0)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        calendaraccesslevel = CalendarAccessLevelConverter.providerToApi(Integer.valueOf(cursor.getInt(7)));
        j = cursor.getInt(10);
        i = j;
        if (j < 0)
        {
            i = 0;
        }
        notifications = calendardescriptor.account;
        obj1 = cursor.getString(20);
        if (!TextUtils.isEmpty(((CharSequence) (obj1))) && AccountUtil.isGoogleAccount(notifications)) goto _L2; else goto _L1
_L1:
        notifications = Collections.emptyList();
_L4:
        String as[];
        int k;
        int l;
        boolean flag5;
        if (cursor.getInt(21) != 0)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        return new CalendarListEntryImpl(calendardescriptor, flag1, s1, ((com.google.android.calendar.api.color.CalendarColor) (obj)), flag2, flag3, flag, flag5, s, anotification, anotification1, flag4, calendaraccesslevel, i, NotificationsStoreUtils.storeMethodsToApiMethods(parseIntegerArrayFromString(cursor.getString(11))), EnumConverter.convertProviderToApiAvailabilities(parseIntegerArrayFromString(cursor.getString(13))), Arrays.asList(new Integer[] {
            Integer.valueOf(ConferenceTypeStoreUtils.getDefaultConferenceType(calendardescriptor, map))
        }), notifications);
_L2:
        as = TextUtils.split(((String) (obj1)), ",");
        obj1 = new ArrayList(as.length);
        l = as.length;
        k = 0;
_L5:
        notifications = ((com.google.android.apps.calendar.timely.store.TimelyStore.Notifications) (obj1));
        if (k >= l) goto _L4; else goto _L3
_L3:
        notifications = as[k];
        if ("family".equals(notifications))
        {
            ((List) (obj1)).add(CalendarCategory.FAMILY);
        } else
        if ("migrated".equals(notifications))
        {
            ((List) (obj1)).add(CalendarCategory.MIGRATED);
        }
        k++;
          goto _L5
    }

    private static int[] parseIntegerArrayFromString(String s)
    {
        if (s == null)
        {
            return new int[0];
        }
        CharMatcher charmatcher = CharMatcher.is(',');
        if (charmatcher == null)
        {
            throw new NullPointerException();
        }
        s = (new Splitter(new com.google.common.base.Splitter._cls1(charmatcher))).splitToList(s);
        int ai[] = new int[s.size()];
        int i = 0;
        while (i < s.size()) 
        {
            try
            {
                ai[i] = Integer.parseInt((String)s.get(i));
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return new int[0];
            }
            i++;
        }
        return ai;
    }
}
