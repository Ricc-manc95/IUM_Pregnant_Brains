// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.loader.SettingsMapLoader;
import com.google.android.calendar.newapi.common.loader.TaskLoader;
import com.google.android.calendar.newapi.model.SettingsMap;
import com.google.android.calendar.task.ArpTaskDateTimeInCalendar;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.TimeZoneImpl;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.Task;
import com.google.common.base.Platform;
import com.google.common.collect.Iterators;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderEditScreenModel

final class ReminderEditScreenLoader extends CompositeLoader
{

    private final Account account;
    private final List accounts;
    private ReminderEditScreenModel.Factory modelFactory;
    private Task originalTask;
    private SettingsMapLoader settingsLoader;
    private SettingsMap settingsMap;
    private Task task;
    private TaskLoader taskLoader;

    ReminderEditScreenLoader(Context context, Account account1, String s, ReminderEditScreenModel remindereditscreenmodel, Bundle bundle)
    {
        modelFactory = new ReminderEditScreenModel.Factory();
        Task task1;
        if (remindereditscreenmodel == null)
        {
            task1 = null;
        } else
        {
            task1 = remindereditscreenmodel.task;
        }
        task = task1;
        if (remindereditscreenmodel == null)
        {
            task1 = null;
        } else
        {
            task1 = remindereditscreenmodel.original;
        }
        originalTask = task1;
        if (remindereditscreenmodel == null)
        {
            remindereditscreenmodel = null;
        } else
        {
            remindereditscreenmodel = remindereditscreenmodel.settingsMap;
        }
        settingsMap = remindereditscreenmodel;
        accounts = Arrays.asList(AccountManager.get(context).getAccountsByType("com.google"));
        remindereditscreenmodel = accounts;
        if (account1 == null)
        {
            if (!remindereditscreenmodel.isEmpty())
            {
                account1 = (Account)remindereditscreenmodel.get(0);
            } else
            {
                account1 = null;
            }
        }
        if (account1 == null)
        {
            throw new NullPointerException();
        }
        account = (Account)account1;
        if (task != null || !Platform.stringIsNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        account1 = new com.google.android.gms.reminders.model.Task.Builder();
        account1.zzavt = "";
        boolean flag;
        long l;
        long l1;
        boolean flag1;
        if (bundle != null)
        {
            l = bundle.getLong("beginTime");
        } else
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        if (Utils.getJulianDay(context, l) > Utils.getTodayJulianDay(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l1 = l;
        if (flag)
        {
            s = Calendar.getInstance(Utils.getTimeZone(context));
            s.setTimeInMillis(l);
            s.set(11, 8);
            s.set(12, 0);
            s.set(13, 0);
            s.set(14, 0);
            l1 = s.getTimeInMillis();
        }
        context = new DateTimeImpl(l1, new TimeZoneImpl(Utils.getTimeZoneId(context, null)));
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        context = ArpTaskDateTimeInCalendar.fromCalendarDateTime(context, flag1, false);
        if (context != null)
        {
            context = (DateTime)context.freeze();
        } else
        {
            context = null;
        }
        account1.zzcjl = context;
        account1.zzcjg = Boolean.valueOf(false);
        account1.zzcjh = Boolean.valueOf(false);
        task = account1.build();
_L4:
        if (settingsMap == null)
        {
            context = new SettingsMapLoader();
            settingsLoader = context;
            super.loaders.add(context);
        }
        return;
_L2:
        if (task == null)
        {
            if (TaskDataFactory.instance == null)
            {
                TaskDataFactory.instance = new TaskDataFactory();
            }
            account1 = TaskDataFactory.instance.getTaskConnection();
            context = new TaskLoader(context, account.name, s, account1);
            taskLoader = context;
            super.loaders.add(context);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final Object getResult()
    {
        Account account1 = account;
        Object obj;
        Task task1;
        Object obj1;
        List list;
        if (taskLoader != null)
        {
            obj = (Task)taskLoader.getResult();
        } else
        {
            obj = task;
        }
        if (originalTask != null)
        {
            task1 = originalTask;
        } else
        {
            task1 = task;
        }
        list = accounts;
        Object obj2;
        long l;
        if (settingsLoader != null)
        {
            obj1 = ((SettingsMap)settingsLoader.getResult()).settingsMap.values();
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            if (com/google/android/calendar/api/settings/GoogleSettings == null)
            {
                throw new NullPointerException();
            }
            obj2 = new com.google.common.base.Predicates.InstanceOfPredicate(com/google/android/calendar/api/settings/GoogleSettings);
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            obj1 = new com.google.common.collect.Iterables._cls4(((Iterable) (obj1)), ((com.google.common.base.Predicate) (obj2)));
            Object aobj[] = (Object[])Array.newInstance(com/google/android/calendar/api/settings/GoogleSettings, 0);
            if (obj1 instanceof Collection)
            {
                obj1 = (Collection)obj1;
            } else
            {
                java.util.Iterator iterator = ((Iterable) (obj1)).iterator();
                obj1 = new ArrayList();
                Iterators.addAll(((Collection) (obj1)), iterator);
            }
            obj1 = SettingsMap.create((GoogleSettings[])((Collection) (obj1)).toArray(aobj));
        } else
        {
            obj1 = settingsMap;
        }
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        aobj = ((Object []) (obj));
        if (ReminderEditScreenModel.isOverdue(((Task) (obj)).getDueDate(), l))
        {
            aobj = ReminderEditScreenModel.setAllDayToday(((Task) (obj)), l);
        }
        obj = new ReminderEditScreenModel();
        obj.account = account1;
        obj.original = task1;
        if (((ReminderEditScreenModel) (obj)).account == null)
        {
            throw new NullPointerException();
        } else
        {
            obj.accounts = list;
            obj.settingsMap = ((SettingsMap) (obj1));
            ((ReminderEditScreenModel) (obj)).setTask(((Task) (aobj)));
            return obj;
        }
    }
}
