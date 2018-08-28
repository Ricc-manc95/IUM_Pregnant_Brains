// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.settings;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import com.google.android.apps.calendar.timely.store.PreferredNotification;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.notification.NotificationsTimelyStoreUtils;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.event.ReminderEntry;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.FluentFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RecentAndDefaultNotificationsFactory
{
    public static class AccountRemovedBroadcastReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if (!AndroidPermissionUtils.hasMandatoryPermissions(context))
            {
                return;
            } else
            {
                intent = goAsync();
                class .Lambda._cls0
                    implements Runnable
                {

                    private final Context arg$1;

                    public final void run()
                    {
                        Object obj1 = arg$1;
                        Account aaccount[] = CalendarAccountsUtil.getSyncableAccounts(((Context) (obj1)));
                        Object obj;
                        String s;
                        if (aaccount.length > 0)
                        {
                            StringBuilder stringbuilder = new StringBuilder("(accountName!=?");
                            obj = new String[aaccount.length];
                            obj[0] = aaccount[0].name;
                            for (int i = 1; i < aaccount.length; i++)
                            {
                                stringbuilder.append(" AND accountName!=?");
                                obj[i] = aaccount[i].name;
                            }

                            stringbuilder.append(")");
                            s = stringbuilder.toString();
                        } else
                        {
                            obj = null;
                            s = null;
                        }
                        obj1 = TimelyStore.acquire(((Context) (obj1)).getApplicationContext());
                        ((TimelyStore) (obj1)).database.delete("preferrednotifications", s, ((String []) (obj)));
                        obj = (Collection)((TimelyStore) (obj1)).notificationSubscribers.apply(null);
                    }

                .Lambda._cls0(Context context)
                {
                    arg$1 = context;
                }
                }

                context = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new .Lambda._cls0(context));
                intent.getClass();
                class .Lambda._cls1
                    implements Runnable
                {

                    private final android.content.BroadcastReceiver.PendingResult arg$1;

                    public final void run()
                    {
                        arg$1.finish();
                    }

                .Lambda._cls1(android.content.BroadcastReceiver.PendingResult pendingresult)
                {
                    arg$1 = pendingresult;
                }
                }

                context.addListener(new .Lambda._cls1(intent), CalendarExecutor.BACKGROUND);
                return;
            }
        }

        public AccountRemovedBroadcastReceiver()
        {
        }
    }


    public static RecentAndDefaultNotificationsFactory instance;
    private int defaultDefaultMinutes;
    private TimelyStore timelyStore;

    public RecentAndDefaultNotificationsFactory()
    {
        defaultDefaultMinutes = -2;
    }

    private static List getGlobalDefaultNotifications(Context context, boolean flag, boolean flag1)
    {
        ArrayList arraylist = new ArrayList();
        context = context.getResources();
        int ai[];
        if (flag)
        {
            int i;
            if (flag1)
            {
                i = 0x7f0b0025;
            } else
            {
                i = 0x7f0b0027;
            }
            ai = context.getIntArray(i);
            if (flag1)
            {
                i = 0x7f0b0024;
            } else
            {
                i = 0x7f0b0026;
            }
            context = context.getIntArray(i);
        } else
        {
            int j;
            if (flag1)
            {
                j = 0x7f0b003b;
            } else
            {
                j = 0x7f0b003d;
            }
            ai = context.getIntArray(j);
            if (flag1)
            {
                j = 0x7f0b003a;
            } else
            {
                j = 0x7f0b003c;
            }
            context = context.getIntArray(j);
        }
        for (i = 0; i < ai.length; i++)
        {
            arraylist.add(new ReminderEntry(ai[i], context[i]));
        }

        Collections.sort(arraylist);
        return arraylist;
    }

    private final List getNotifications(Context context, CalendarKey calendarkey, Account account, boolean flag, int i)
    {
        Object obj;
        boolean flag1;
        byte byte0;
        boolean flag2;
        obj = null;
        byte0 = 2;
        flag2 = true;
        flag1 = false;
        if (timelyStore == null)
        {
            timelyStore = TimelyStore.acquire(context);
        }
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L1
_L1:
        if (account == null)
        {
            throw new NullPointerException();
        }
        calendarkey = SettingsCache.instance;
        if (calendarkey == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        ImmutableMap immutablemap = (ImmutableMap)((ListenableFutureCache)calendarkey).result;
        calendarkey = obj;
        if (immutablemap != null)
        {
            calendarkey = (Settings)immutablemap.get(account);
        }
        if (calendarkey != null)
        {
            if (flag)
            {
                i = 2;
            } else
            {
                i = 1;
            }
            calendarkey = calendarkey.getPreferredNotifications(i);
        } else
        {
            calendarkey = Collections.emptyList();
        }
        calendarkey = NotificationsTimelyStoreUtils.convertNotifications(calendarkey, flag);
        if (calendarkey.length != 0) goto _L4; else goto _L3
_L3:
        if (account == null) goto _L6; else goto _L5
_L5:
        calendarkey = account.type;
        if (!AccountUtil.EXCHANGE_TYPES.contains(calendarkey)) goto _L6; else goto _L7
_L7:
        calendarkey = getGlobalDefaultNotifications(context, flag2, flag);
_L11:
        return calendarkey;
_L6:
        flag2 = false;
          goto _L7
_L4:
        context = calendarkey;
_L9:
        if (context == null)
        {
            return Collections.EMPTY_LIST;
        }
          goto _L8
_L2:
        if (calendarkey != null)
        {
            context = CalendarListEntryCache.findByLocalId(calendarkey);
            if (context != null)
            {
                if (flag)
                {
                    i = byte0;
                } else
                {
                    i = 1;
                }
                context = NotificationsTimelyStoreUtils.convertNotifications(context.getDefaultNotifications(i), flag);
            } else
            {
                context = null;
            }
            if (context == null)
            {
                context = new PreferredNotification[0];
            }
        } else
        {
            context = null;
        }
        if (true) goto _L9; else goto _L8
_L8:
        account = new ArrayList(context.length);
        i = ((flag1) ? 1 : 0);
_L12:
        calendarkey = account;
        if (i >= context.length) goto _L11; else goto _L10
_L10:
        account.add(new ReminderEntry(((PreferredNotification) (context[i])).minutes, ((PreferredNotification) (context[i])).method));
        i++;
          goto _L12
    }

    public final List getDefaultNotifications(Context context, CalendarKey calendarkey, Account account, boolean flag)
    {
        if (calendarkey != null)
        {
            context = getNotifications(context, calendarkey, account, flag, 1);
        } else
        {
            if (flag)
            {
                return Collections.EMPTY_LIST;
            }
            calendarkey = new ArrayList(1);
            if (defaultDefaultMinutes == -2)
            {
                defaultDefaultMinutes = Integer.parseInt(context.getSharedPreferences("com.google.android.calendar_preferences", 0).getString("preferences_default_reminder", "-1"));
            }
            context = calendarkey;
            if (defaultDefaultMinutes != -1)
            {
                calendarkey.add(new ReminderEntry(defaultDefaultMinutes, 0));
                return calendarkey;
            }
        }
        return context;
    }

    public final List getRecentNotificationOptions(Context context, Account account, boolean flag)
    {
        if (account == null)
        {
            return getGlobalDefaultNotifications(context, false, flag);
        }
        context = getNotifications(context, null, account, flag, 0);
        int j = context.size();
        account = account.type;
        int i;
        int k;
        if (AccountUtil.EXCHANGE_TYPES.contains(account))
        {
            i = 5;
        } else
        {
            i = 3;
        }
        k = Math.min(i, j);
        account = new ArrayList(k);
        for (i = 0; i < k; i++)
        {
            account.add(0, (ReminderEntry)context.get(j - 1 - i));
        }

        return account;
    }
}
