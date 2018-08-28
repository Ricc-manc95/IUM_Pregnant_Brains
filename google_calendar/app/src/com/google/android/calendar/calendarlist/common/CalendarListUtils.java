// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist.common;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.calendar.syncadapters.timely.type.CalendarType;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class CalendarListUtils
{

    public static void postProcessItems(Context context, List list, String s)
    {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0)
        {
            context = SettingsCache.instance;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Not initialized"));
            }
            context = (ImmutableMap)((ListenableFutureCache)context).result;
            if (context != null)
            {
                ArrayList arraylist = new ArrayList();
                Iterator iterator = list.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    Object obj = (CalendarListItemInfo)iterator.next();
                    if (((CalendarListItemInfo) (obj)).getType() == 0)
                    {
                        obj = (Settings)context.get(((AccountItem)obj).account);
                        if (obj instanceof GoogleSettings)
                        {
                            arraylist.add(new TaskCalendarItem((GoogleSettings)obj, s));
                        }
                    }
                } while (true);
                list.addAll(arraylist);
                return;
            }
        }
    }

    public static boolean processCalendars(CalendarListEntry acalendarlistentry[], List list, Map map, boolean flag)
    {
        list.clear();
        if (acalendarlistentry == null || acalendarlistentry.length <= 0) goto _L2; else goto _L1
_L1:
        HashSet hashset;
        BirthdayGroupItem birthdaygroupitem;
        HolidayGroupItem holidaygroupitem;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int i;
        int k;
        hashset = new HashSet();
        birthdaygroupitem = new BirthdayGroupItem();
        holidaygroupitem = new HolidayGroupItem();
        flag1 = false;
        flag2 = false;
        flag3 = false;
        k = acalendarlistentry.length;
        i = 0;
_L4:
        if (i >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = acalendarlistentry[i];
        if (((CalendarListEntry) (obj)).isDeleted())
        {
            break MISSING_BLOCK_LABEL_609;
        }
        Account account = ((CalendarListEntry) (obj)).getDescriptor().account;
        if (!AccountUtil.isAccountInfoValid(account.name, account.type))
        {
            break MISSING_BLOCK_LABEL_609;
        }
        obj = new CalendarItem(((CalendarListEntry) (obj)));
        int j;
        boolean flag9;
        if (map != null && map.containsKey(((CalendarItem) (obj)).calendarDescriptor))
        {
            flag9 = ((Boolean)map.get(((CalendarItem) (obj)).calendarDescriptor)).booleanValue();
        } else
        {
            flag9 = ((CalendarItem) (obj)).isVisible;
        }
        if (((CalendarItem) (obj)).isPrimary)
        {
            obj.priority = 1;
        } else
        if (flag9 && ((CalendarItem) (obj)).isSynced)
        {
            obj.priority = 3;
        } else
        {
            obj.priority = 4;
        }
        j = CalendarType.calculateType(((CalendarItem) (obj)).ownerAccount);
        if (3 == j)
        {
            break MISSING_BLOCK_LABEL_609;
        }
        if (1 == j)
        {
            ((GroupOfCalendarsItem) (birthdaygroupitem)).calendars.add(obj);
            if (((GroupOfCalendarsItem) (birthdaygroupitem)).areVisible || ((CalendarItem) (obj)).isVisible)
            {
                flag9 = true;
            } else
            {
                flag9 = false;
            }
            birthdaygroupitem.areVisible = flag9;
            flag1 = flag3;
            flag3 = true;
        } else
        if (2 == j)
        {
            ((GroupOfCalendarsItem) (holidaygroupitem)).calendars.add(obj);
            boolean flag5;
            boolean flag10;
            if (((GroupOfCalendarsItem) (holidaygroupitem)).areVisible || ((CalendarItem) (obj)).isVisible)
            {
                flag10 = true;
            } else
            {
                flag10 = false;
            }
            holidaygroupitem.areVisible = flag10;
            flag2 = flag3;
            flag5 = true;
            flag3 = flag1;
            flag1 = flag2;
            flag2 = flag5;
        } else
        {
            String s = ((CalendarItem) (obj)).account.type;
            boolean flag6;
            boolean flag11;
            if ("com.htc.pcsc".equals(s))
            {
                flag11 = true;
            } else
            {
                flag11 = "LOCAL".equals(s);
            }
            if (!flag11 && !((CalendarItem) (obj)).isPrimary && !flag && !((CalendarItem) (obj)).isSynced)
            {
                break MISSING_BLOCK_LABEL_609;
            }
            list.add(obj);
            if (hashset.contains(((CalendarItem) (obj)).account))
            {
                break MISSING_BLOCK_LABEL_609;
            }
            hashset.add(((CalendarItem) (obj)).account);
            list.add(new AccountItem(((CalendarItem) (obj)).account));
            if (!"com.google".equals(((CalendarItem) (obj)).account.type))
            {
                break MISSING_BLOCK_LABEL_609;
            }
            flag6 = true;
            flag3 = flag1;
            flag1 = flag6;
        }
_L5:
        j = i + 1;
        i = ((flag3) ? 1 : 0);
        flag3 = flag1;
        flag1 = i;
        i = j;
        if (true) goto _L4; else goto _L3
_L3:
        boolean flag7 = flag2;
        boolean flag4 = flag1;
        if (flag)
        {
            flag7 = flag2;
            flag4 = flag1;
            if (flag3)
            {
                flag4 = true;
                flag7 = true;
            }
        }
        if (flag4)
        {
            list.add(birthdaygroupitem);
        }
        if (flag7)
        {
            list.add(holidaygroupitem);
        }
        if (flag4 || flag7)
        {
            list.add(new GroupHeaderItem());
        }
        return true;
_L2:
        return false;
        boolean flag8 = flag1;
        flag1 = flag3;
        flag3 = flag8;
          goto _L5
    }

    public static void processHiddenCalendars(List list, List list1, Map map)
    {
        ArrayList arraylist = new ArrayList(list.size());
        HashMap hashmap = new HashMap();
        Iterator iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj1 = (CalendarListItemInfo)iterator.next();
            boolean flag;
            if ((obj1 instanceof CalendarItem) && ((CalendarItem)obj1).priority == 4)
            {
                if (map != null && map.containsKey(((CalendarItem)obj1).calendarDescriptor))
                {
                    flag = ((Boolean)map.get(((CalendarItem)obj1).calendarDescriptor)).booleanValue();
                } else
                {
                    flag = ((CalendarItem)obj1).isVisible;
                }
            } else
            {
                flag = true;
            }
            if (!flag)
            {
                Object obj = (CalendarItem)obj1;
                if (((AccountItem) (obj)).account == null)
                {
                    obj = null;
                } else
                {
                    obj = ((AccountItem) (obj)).account.name;
                }
                if (!list1.contains(obj))
                {
                    obj1 = (CalendarItem)obj1;
                    if (!hashmap.containsKey(((CalendarItem) (obj1)).account))
                    {
                        obj = new GroupOfHiddenCalendarsItem(((CalendarItem) (obj1)).account);
                        hashmap.put(((CalendarItem) (obj1)).account, obj);
                        arraylist.add(obj);
                    } else
                    {
                        obj = (GroupOfHiddenCalendarsItem)hashmap.get(((CalendarItem) (obj1)).account);
                    }
                    ((GroupOfHiddenCalendarsItem) (obj)).calendars.add(obj1);
                    continue;
                }
            }
            arraylist.add(obj1);
        } while (true);
        list.clear();
        list.addAll(arraylist);
    }

    private class CalendarListItemInfo
    {

        public abstract int getOrder();

        public abstract int getType();

        public abstract int getViewType();
    }


    private class AccountItem
        implements CalendarListItemInfo
    {

        public Account account;
        public int priority;

        public final int getOrder()
        {
            return 0;
        }

        public int getType()
        {
            return 0;
        }

        public int getViewType()
        {
            return 1;
        }

        public AccountItem()
        {
        }

        public AccountItem(Account account1)
        {
            priority = 0;
            account = account1;
        }
    }


    private class TaskCalendarItem extends CalendarItem
    {
        private class CalendarItem extends AccountItem
        {

            public CalendarDescriptor calendarDescriptor;
            public CalendarListEntry calendarListEntry;
            public int color;
            public String displayName;
            public boolean isPrimary;
            public boolean isSynced;
            public boolean isVisible;
            public String ownerAccount;
            public Uri uri;

            public final int getType()
            {
                return 1;
            }

            public final int getViewType()
            {
                return 2;
            }

            public CalendarItem()
            {
            }

            public CalendarItem(CalendarListEntry calendarlistentry)
            {
                calendarListEntry = calendarlistentry;
                account = calendarlistentry.getDescriptor().account;
                uri = ContentUris.withAppendedId(android.provider.CalendarContract.Calendars.CONTENT_URI, calendarlistentry.getDescriptor().calendarKey.getLocalId());
                isVisible = calendarlistentry.isVisible();
                isSynced = calendarlistentry.isSyncEnabled();
                isPrimary = calendarlistentry.isPrimary();
                color = calendarlistentry.getColor().getValue();
                displayName = calendarlistentry.getDisplayName();
                ownerAccount = calendarlistentry.getDescriptor().calendarId;
                calendarDescriptor = calendarlistentry.getDescriptor();
                if (isPrimary)
                {
                    priority = 1;
                    return;
                } else
                {
                    priority = 3;
                    return;
                }
            }
        }


        public final GoogleSettings settings;

        TaskCalendarItem(GoogleSettings googlesettings, String s)
        {
            settings = googlesettings;
            Account account = googlesettings.getDescriptor();
            String s1 = account.name;
            uri = TimelyContract.TASKS_CALENDAR_URI.buildUpon().appendPath(s1).build();
            displayName = s;
            ownerAccount = s1;
            this.account = account;
            color = googlesettings.getTaskColor().getValue();
            isVisible = googlesettings.areTasksVisible();
            isSynced = true;
            priority = 2;
        }
    }


    private class BirthdayGroupItem extends GroupOfCalendarsItem
    {
        private class GroupOfCalendarsItem
            implements CalendarListItemInfo
        {

            public boolean areVisible;
            public final ArrayList calendars = new ArrayList();
            private final int order;
            private final int type;

            public final int getOrder()
            {
                return order;
            }

            public final int getType()
            {
                return type;
            }

            public final int getViewType()
            {
                return 2;
            }

            GroupOfCalendarsItem(int i, int j)
            {
                type = j;
                order = i;
                areVisible = false;
            }
        }


        public BirthdayGroupItem()
        {
            super(2, 3);
        }
    }


    private class HolidayGroupItem extends GroupOfCalendarsItem
    {

        public HolidayGroupItem()
        {
            super(3, 4);
        }
    }


    private class GroupHeaderItem
        implements CalendarListItemInfo
    {

        public final int getOrder()
        {
            return 1;
        }

        public final int getType()
        {
            return 2;
        }

        public final int getViewType()
        {
            return 0;
        }

        public GroupHeaderItem()
        {
        }
    }


    private class GroupOfHiddenCalendarsItem extends AccountItem
    {

        public final ArrayList calendars = new ArrayList();

        public final int getType()
        {
            return 5;
        }

        public final int getViewType()
        {
            return 2;
        }

        public GroupOfHiddenCalendarsItem(Account account)
        {
            this.account = account;
            priority = 4;
        }
    }

}
