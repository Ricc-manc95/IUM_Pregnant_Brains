// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.accounts.Account;
import android.app.backup.BackupManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.color.CalendarColor;
import com.google.android.calendar.calendarlist.common.CalendarListUtils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.sync.SyncUtils;
import com.google.android.calendar.timely.BirthdayManager;
import com.google.android.calendar.timely.BottomSheet;
import com.google.android.calendar.timely.SyncOffNotificationsManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.calendarlist:
//            DrawerFragment, SelectCalendarsAdapter

final class arg._cls1
    implements Consumer
{

    private final DrawerFragment arg$1;

    public final void accept(Object obj)
    {
        DrawerFragment drawerfragment = arg$1;
        CalendarListEntry acalendarlistentry[] = (CalendarListEntry[])obj;
        obj = drawerfragment.adapter;
        CalendarListEntry calendarlistentry = BirthdayManager.getPrimaryBirthdayCalendar(((SelectCalendarsAdapter) (obj)).mContext, acalendarlistentry);
        if (calendarlistentry != null)
        {
            Context context = ((SelectCalendarsAdapter) (obj)).mContext;
            int i = calendarlistentry.getColor().getValue();
            context.getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("preferences_birthdays_color", i).apply();
            (new BackupManager(context)).dataChanged();
        } else
        if (!((SelectCalendarsAdapter) (obj)).mContext.getSharedPreferences("com.google.android.calendar_preferences", 0).contains("preferences_birthdays_color"))
        {
            obj2 = ((SelectCalendarsAdapter) (obj)).mContext;
            ((Context) (obj2)).getSharedPreferences("com.google.android.calendar_preferences", 0).edit().putInt("preferences_birthdays_color", 0xff92e1c0).apply();
            (new BackupManager(((Context) (obj2)))).dataChanged();
        }
        boolean flag2;
        if (CalendarListUtils.processCalendars(acalendarlistentry, ((SelectCalendarsAdapter) (obj)).mLastItems, ((SelectCalendarsAdapter) (obj)).originalVisibilities, false))
        {
            for (Iterator iterator = ((SelectCalendarsAdapter) (obj)).mLastItems.iterator(); iterator.hasNext();)
            {
                Object obj2 = (com.google.android.calendar.calendarlist.common.rListItemInfo)iterator.next();
                if (((com.google.android.calendar.calendarlist.common.rListItemInfo) (obj2)).getType() == 3)
                {
                    obj2 = (com.google.android.calendar.calendarlist.common.CalendarsItem)obj2;
                    obj2.areVisible = ((SelectCalendarsAdapter) (obj)).mContext.getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("preferences_birthdays_master_visibility", true);
                    ((SelectCalendarsAdapter) (obj)).saveBirthdayVisibility(((com.google.android.calendar.calendarlist.common.CalendarsItem) (obj2)));
                } else
                if (obj2 instanceof com.google.android.calendar.calendarlist.common.CalendarsItem)
                {
                    obj2 = (com.google.android.calendar.calendarlist.common.CalendarsItem)obj2;
                    if (((com.google.android.calendar.calendarlist.common.CalendarsItem) (obj2)).calendars.size() > 0)
                    {
                        ArrayList arraylist = (ArrayList)((com.google.android.calendar.calendarlist.common.CalendarsItem) (obj2)).calendars;
                        int k = arraylist.size();
                        int j = 0;
                        boolean flag = false;
                        do
                        {
                            if (j >= k)
                            {
                                break;
                            }
                            Object obj5 = arraylist.get(j);
                            j++;
                            obj5 = (com.google.android.calendar.calendarlist.common.rItem)obj5;
                            if (((com.google.android.calendar.calendarlist.common.rItem) (obj5)).isVisible != ((com.google.android.calendar.calendarlist.common.CalendarsItem) (obj2)).areVisible)
                            {
                                flag = ((SelectCalendarsAdapter) (obj)).saveCalendarVisibility(((com.google.android.calendar.calendarlist.common.rItem) (obj5)), ((com.google.android.calendar.calendarlist.common.CalendarsItem) (obj2)).areVisible) | flag;
                            }
                        } while (true);
                        if (flag)
                        {
                            LatencyLoggerHolder.get().markAt(5);
                        }
                    }
                }
            }

            Object obj1 = ((SelectCalendarsAdapter) (obj)).mContext;
            if (SyncOffNotificationsManager.instance == null)
            {
                SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj1)));
            }
            obj1 = SyncOffNotificationsManager.instance;
            Object obj4 = ((SelectCalendarsAdapter) (obj)).mLastItems;
            Object obj3 = new HashSet();
            obj4 = ((List) (obj4)).iterator();
            do
            {
                if (!((Iterator) (obj4)).hasNext())
                {
                    break;
                }
                com.google.android.calendar.calendarlist.common.rListItemInfo rlistiteminfo = (com.google.android.calendar.calendarlist.common.rListItemInfo)((Iterator) (obj4)).next();
                if (rlistiteminfo instanceof com.google.android.calendar.calendarlist.common.Item)
                {
                    ((Set) (obj3)).add(((com.google.android.calendar.calendarlist.common.Item)rlistiteminfo).account);
                }
            } while (true);
            ((SyncOffNotificationsManager) (obj1)).allAccounts.clear();
            ((SyncOffNotificationsManager) (obj1)).allAccounts.addAll(((java.util.Collection) (obj3)));
            ((SyncOffNotificationsManager) (obj1)).syncOffAccounts.clear();
            obj3 = ((Set) (obj3)).iterator();
            do
            {
                if (!((Iterator) (obj3)).hasNext())
                {
                    break;
                }
                Account account = (Account)((Iterator) (obj3)).next();
                boolean flag1;
                if (SyncUtils.isSyncable(account) && !SyncUtils.getSyncAutomatically(account))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    ((SyncOffNotificationsManager) (obj1)).syncOffAccounts.add(account);
                }
            } while (true);
            ((SyncOffNotificationsManager) (obj1)).checkDismissCycle();
            if (((SyncOffNotificationsManager) (obj1)).needsRefresh)
            {
                ((SyncOffNotificationsManager) (obj1)).onAppOpen();
            }
            if (((SyncOffNotificationsManager) (obj1)).isShowing)
            {
                if (!ContentResolver.getMasterSyncAutomatically())
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                if (!flag2 && (((SyncOffNotificationsManager) (obj1)).account != null || ((SyncOffNotificationsManager) (obj1)).syncOffAccounts.size() <= 0) && (((SyncOffNotificationsManager) (obj1)).account == null || !((SyncOffNotificationsManager) (obj1)).syncOffAccounts.contains(((SyncOffNotificationsManager) (obj1)).account)))
                {
                    ((SyncOffNotificationsManager) (obj1)).notification.hide(false, true);
                }
            }
            ((SelectCalendarsAdapter) (obj)).updateItemList();
        } else
        {
            ((SelectCalendarsAdapter) (obj)).notifyDataSetChanged();
        }
        drawerfragment.calendarsLoaded = true;
        if (drawerfragment.settingsLoaded && drawerfragment.calendarsLoaded)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (flag2)
        {
            drawerfragment.adapter.settings = drawerfragment.settings;
        }
    }

    AccountItem(DrawerFragment drawerfragment)
    {
        arg$1 = drawerfragment;
    }
}
