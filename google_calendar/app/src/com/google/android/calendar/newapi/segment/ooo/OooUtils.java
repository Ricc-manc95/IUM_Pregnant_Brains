// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.ooo;

import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.AccountUtils;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import java.util.Arrays;
import java.util.Iterator;

public final class OooUtils
{

    public static CalendarListEntry getDefaultCalendar()
    {
        Object obj;
        obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = (CalendarListEntry[])((ListenableFutureCache)obj).result;
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = null;
_L4:
        return ((CalendarListEntry) (obj));
_L2:
        Object obj2;
        obj2 = Arrays.asList(((Object []) (obj)));
        class .Lambda._cls0
            implements Predicate
        {

            private final CalendarDescriptor arg$1;

            public final boolean apply(Object obj3)
            {
                CalendarDescriptor calendardescriptor = arg$1;
                return ((CalendarListEntry)obj3).getDescriptor().equals(calendardescriptor);
            }

            .Lambda._cls0(CalendarDescriptor calendardescriptor)
            {
                arg$1 = calendardescriptor;
            }
        }

        .Lambda._cls0 _lcls0 = new .Lambda._cls0(CalendarProperties.getDefaultCalendarId());
        Iterator iterator = ((Iterable) (obj2)).iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (_lcls0 == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_154;
            }
            obj = iterator.next();
        } while (!_lcls0.apply(obj));
_L5:
        Object obj1;
        obj1 = (CalendarListEntry)obj;
        if (obj1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = obj1;
        if (isOooSupportedCalendar(((CalendarListEntry) (obj1)))) goto _L4; else goto _L3
_L3:
        class .Lambda._cls1
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls1();

            public final boolean apply(Object obj3)
            {
                return OooUtils.isOooSupportedCalendar((CalendarListEntry)obj3);
            }


            private .Lambda._cls1()
            {
            }
        }

        obj1 = .Lambda._cls1..instance;
        obj2 = ((Iterable) (obj2)).iterator();
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        break MISSING_BLOCK_LABEL_159;
        obj = null;
          goto _L5
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
_L9:
        if (!((Iterator) (obj2)).hasNext()) goto _L7; else goto _L6
_L6:
        obj = ((Iterator) (obj2)).next();
        if (!((Predicate) (obj1)).apply(obj)) goto _L9; else goto _L8
_L8:
        return (CalendarListEntry)obj;
_L7:
        obj = null;
        if (true) goto _L8; else goto _L10
_L10:
    }

    public static boolean isOooSupportedCalendar(CalendarListEntry calendarlistentry)
    {
        android.accounts.Account account = calendarlistentry.getDescriptor().account;
        if (!AccountUtil.isGoogleAccount(account))
        {
            return false;
        }
        if (!calendarlistentry.isPrimary())
        {
            return false;
        }
        calendarlistentry = SettingsCache.instance;
        if (calendarlistentry == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        calendarlistentry = (ImmutableMap)Futures.getUnchecked(((ListenableFutureCache)calendarlistentry).getValueAsync());
        if (calendarlistentry == null)
        {
            return false;
        } else
        {
            return AccountUtils.isDasher((Settings)calendarlistentry.get(account));
        }
    }
}
