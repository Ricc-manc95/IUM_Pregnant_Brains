// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.Subscription;
import com.google.android.calendar.CalendarController;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.latency.LatencyLoggerHolder;
import com.google.android.calendar.time.DateTimeImpl;
import com.google.android.calendar.time.DateTimeService;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.ActivitySingletonCache;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.google.android.calendar.task:
//            BaseTaskDataManager, ArpLoadTester, TaskAccount, ArpTaskAccount, 
//            TaskConnection

public class ArpTaskDataManager
    implements BaseTaskDataManager, TaskAccount.TaskAccountListener, OnPropertyChangedListener
{
    public static final class TaskMonthData
    {

        public final int numDays;
        public final int startJulianDay;

        public TaskMonthData(int i)
        {
            Object obj = new Time();
            ((Time) (obj)).writeFieldsToImpl();
            ((Time) (obj)).impl.setJulianDay(i);
            ((Time) (obj)).copyFieldsFromImpl();
            ((Time) (obj)).writeFieldsToImpl();
            long l = ((Time) (obj)).impl.toMillis(false);
            obj = Calendar.getInstance();
            ((Calendar) (obj)).setTimeInMillis(l);
            startJulianDay = i - (((Calendar) (obj)).get(5) - 1);
            numDays = ((Calendar) (obj)).getActualMaximum(5);
        }

        public TaskMonthData(int i, int j)
        {
            startJulianDay = i;
            numDays = (j - i) + 1;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/task/ArpTaskDataManager);
    public final Map allTasks = new HashMap();
    private final ArpLoadTester arpLoadTester;
    private final Context context;
    private final CalendarController controller;
    public int endJulianDay;
    private Set haveLoggedAccountAlready;
    private boolean isInitialLoad;
    private final MonthData monthDatas[];
    private final Subscription settingsSubscription;
    public int startJulianDay;
    public final Map taskAccounts = new ConcurrentHashMap();
    private final TaskConnection taskConnection;
    private final Set tasksAccountsFullLoading;
    private final Set tasksAccountsShortLoading;
    public boolean tasksReady;
    private int todayJulianDay;
    public final MonthData todayMonthData;

    public ArpTaskDataManager(Activity activity, TaskConnection taskconnection, MonthData monthdata, MonthData amonthdata[])
    {
        isInitialLoad = true;
        context = activity.getApplicationContext();
        taskConnection = taskconnection;
        todayMonthData = monthdata;
        monthDatas = amonthdata;
        controller = (CalendarController)CalendarController.instances.getInstance(activity);
        activity = CalendarProperties.instance;
        if (activity == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        }
        ((CalendarProperties)activity).registerListener(0, this);
        activity = SettingsCache.instance;
        if (activity == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        } else
        {
            class .Lambda._cls0
                implements Consumer
            {

                private final ArpTaskDataManager arg$1;

                public final void accept(Object obj)
                {
                    arg$1.loadAccounts(false);
                }

            .Lambda._cls0()
            {
                arg$1 = ArpTaskDataManager.this;
            }
            }

            settingsSubscription = ((ListenableFutureCache)activity).subscribe(new .Lambda._cls0(), CalendarExecutor.MAIN, false);
            arpLoadTester = new ArpLoadTester(context, taskConnection, todayMonthData, this);
            activity = arpLoadTester;
            activity = context;
            tasksAccountsShortLoading = new HashSet();
            tasksAccountsFullLoading = new HashSet();
            (new Handler()).postDelayed(new _cls1(), 2000L);
            return;
        }
    }

    protected static List generateTaskMonthDataList(MonthData monthdata, int i, int j)
    {
        TaskMonthData taskmonthdata = new TaskMonthData(monthdata.startDay, (monthdata.startDay + monthdata.numDays) - 1);
        monthdata = new ArrayList();
        monthdata.add(taskmonthdata);
        for (int k = (taskmonthdata.startJulianDay + taskmonthdata.numDays) - 1; k < j;)
        {
            TaskMonthData taskmonthdata2 = new TaskMonthData(k + 1);
            k = (taskmonthdata2.startJulianDay + taskmonthdata2.numDays) - 1;
            monthdata.add(taskmonthdata2);
        }

        for (j = taskmonthdata.startJulianDay; j > i;)
        {
            TaskMonthData taskmonthdata1 = new TaskMonthData(j - 1);
            j = taskmonthdata1.startJulianDay;
            monthdata.add(taskmonthdata1);
        }

        return Collections.unmodifiableList(monthdata);
    }

    private final void markTaskAccountInitialLoadEnd(String s, Integer integer)
    {
        this;
        JVM INSTR monitorenter ;
        if (integer == null) goto _L2; else goto _L1
_L1:
        if (integer.intValue() != 0) goto _L2; else goto _L3
_L3:
        tasksAccountsShortLoading.remove(s);
_L5:
        if (!tasksAccountsShortLoading.isEmpty())
        {
            break MISSING_BLOCK_LABEL_129;
        }
        if (tasksReady)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        s = AnalyticsLoggerHolder.instance;
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
_L2:
        if (integer == null) goto _L5; else goto _L4
_L4:
        if (integer.intValue() != 1) goto _L5; else goto _L6
_L6:
        tasksAccountsFullLoading.remove(s);
          goto _L5
        ((AnalyticsLogger)s).trackEvent(context, "loading", "ui_revealed", "loaded", null);
        setTasksReady();
        LatencyLoggerHolder.get().markAt(17);
        int j;
        if (!tasksAccountsFullLoading.isEmpty())
        {
            break MISSING_BLOCK_LABEL_188;
        }
        LatencyLoggerHolder.get().markAt(16);
        s = monthDatas;
        j = s.length;
        int i = 0;
_L9:
        if (i >= j) goto _L8; else goto _L7
_L7:
        integer = s[i];
        if (integer == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        integer.allTasksReady = true;
        i++;
          goto _L9
_L8:
    }

    private static void updateMonthData(MonthData monthdata, String s, NavigableMap navigablemap)
    {
        navigablemap = navigablemap.floorEntry(Integer.valueOf(monthdata.startDay));
        if (navigablemap != null)
        {
            navigablemap = (com.google.android.calendar.timely.MonthData.TaskResults)navigablemap.getValue();
            if (monthdata.startDay == ((com.google.android.calendar.timely.MonthData.TaskResults) (navigablemap)).startDay && monthdata.populateTasks(s, navigablemap))
            {
                monthdata.onTasksPopulated();
            }
        }
    }

    public final void includeData(int i)
    {
        boolean flag;
        if (i >= startJulianDay && i <= endJulianDay)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Object obj = new TreeMap();
            ((NavigableMap) (obj)).put(Integer.valueOf(todayMonthData.startDay), todayMonthData);
            MonthData amonthdata[] = monthDatas;
            int k = amonthdata.length;
            int j = 0;
            while (j < k) 
            {
                MonthData monthdata1 = amonthdata[j];
                if (monthdata1 == null)
                {
                    continue;
                }
                boolean flag1;
                if (i >= monthdata1.startDay && i <= (monthdata1.startDay + monthdata1.numDays) - 1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    ((NavigableMap) (obj)).put(Integer.valueOf(monthdata1.startDay), monthdata1);
                }
                j++;
            }
            HashMap hashmap = new HashMap();
            for (Iterator iterator = ((NavigableMap) (obj)).values().iterator(); iterator.hasNext();)
            {
                MonthData monthdata2 = (MonthData)iterator.next();
                HashMap hashmap1 = new HashMap();
                hashmap.put(monthdata2, hashmap1);
                Iterator iterator2 = taskAccounts.keySet().iterator();
                while (iterator2.hasNext()) 
                {
                    hashmap1.put((String)iterator2.next(), new com.google.android.calendar.timely.MonthData.TaskResults(monthdata2.startDay, (monthdata2.startDay + monthdata2.numDays) - 1));
                }
            }

            for (Iterator iterator1 = taskAccounts.values().iterator(); iterator1.hasNext();)
            {
                Object obj2 = (TaskAccount)iterator1.next();
                String s = ((TaskAccount) (obj2)).getAccountName();
                obj2 = ((TaskAccount) (obj2)).getTasks();
                if (obj2 != null)
                {
                    obj2 = ((TaskAccount.Tasks) (obj2)).monthsTasksMap.values().iterator();
                    while (((Iterator) (obj2)).hasNext()) 
                    {
                        com.google.android.calendar.timely.MonthData.TaskResults taskresults = (com.google.android.calendar.timely.MonthData.TaskResults)((Iterator) (obj2)).next();
                        i = taskresults.startDay;
                        Object obj3 = ((NavigableMap) (obj)).floorEntry(Integer.valueOf(i));
                        if (obj3 != null)
                        {
                            obj3 = (MonthData)((java.util.Map.Entry) (obj3)).getValue();
                            if (i >= ((MonthData) (obj3)).startDay && i <= (((MonthData) (obj3)).startDay + ((MonthData) (obj3)).numDays) - 1)
                            {
                                i = 1;
                            } else
                            {
                                i = 0;
                            }
                            if (i != 0)
                            {
                                ((Map)hashmap.get(obj3)).put(s, taskresults);
                            }
                        }
                    }
                }
            }

            obj = hashmap.entrySet().iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
                MonthData monthdata = (MonthData)((java.util.Map.Entry) (obj1)).getKey();
                obj1 = ((Map)((java.util.Map.Entry) (obj1)).getValue()).entrySet().iterator();
                java.util.Map.Entry entry;
                for (i = 0; ((Iterator) (obj1)).hasNext(); i = monthdata.populateTasks((String)entry.getKey(), (com.google.android.calendar.timely.MonthData.TaskResults)entry.getValue()) | i)
                {
                    entry = (java.util.Map.Entry)((Iterator) (obj1)).next();
                }

                if (i != 0)
                {
                    monthdata.onTasksPopulated();
                }
                if (tasksAccountsFullLoading.isEmpty())
                {
                    monthdata.allTasksReady = true;
                }
            }
        }
    }

    final List initAccountsAndDetermineIfShouldLoad(ImmutableMap immutablemap, long l, long l1, boolean flag)
    {
        Account aaccount[];
        ArrayList arraylist;
        int i;
        int j;
        aaccount = AccountsUtil.getGoogleAccounts(context);
        arraylist = new ArrayList();
        j = aaccount.length;
        i = 0;
_L5:
        Object obj1;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_230;
        }
        obj1 = aaccount[i];
        if (!AccountUtil.isGoogleAccount(((Account) (obj1)))) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        Object obj = (TaskAccount)taskAccounts.get(((Account) (obj1)).name);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_127;
        }
        obj = new ArpTaskAccount(context, ((Account) (obj1)), taskConnection, this);
        taskAccounts.put(((Account) (obj1)).name, new ArpTaskAccount(context, ((Account) (obj1)), taskConnection, this));
        this;
        JVM INSTR monitorexit ;
        if (immutablemap == null)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        obj1 = (Settings)immutablemap.get(obj1);
        if (!(obj1 instanceof GoogleSettings))
        {
            break MISSING_BLOCK_LABEL_218;
        }
        obj1 = (GoogleSettings)obj1;
_L3:
        boolean flag1;
        if (obj1 != null && ((GoogleSettings) (obj1)).areTasksVisible())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (((TaskAccount) (obj)).shouldLoad(l, l1, flag1, flag))
        {
            arraylist.add(obj);
        }
_L2:
        i++;
        continue; /* Loop/switch isn't completed */
        immutablemap;
        this;
        JVM INSTR monitorexit ;
        throw immutablemap;
        obj1 = null;
          goto _L3
        return arraylist;
        if (true) goto _L5; else goto _L4
_L4:
    }

    final void loadAccounts(boolean flag)
    {
        Object obj = SettingsCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = ((ListenableFutureCache)obj).getValueAsync();
        class .Lambda._cls1
            implements Consumer
        {

            private final ArpTaskDataManager arg$1;
            private final boolean arg$2;

            public final void accept(Object obj1)
            {
                List list;
                ArpTaskDataManager arptaskdatamanager;
                List list1;
                ImmutableMap immutablemap;
                Object obj2;
                int i;
                int k;
                long l;
                long l1;
                boolean flag2;
                arptaskdatamanager = arg$1;
                flag2 = arg$2;
                immutablemap = (ImmutableMap)obj1;
                arptaskdatamanager.startJulianDay = arptaskdatamanager.todayMonthData.startDay - 366;
                obj1 = arptaskdatamanager.todayMonthData;
                i = ((MonthData) (obj1)).startDay;
                arptaskdatamanager.endJulianDay = ((((MonthData) (obj1)).numDays + i) - 1) + 366;
                l = Utils.getMillisFromJulianDay(arptaskdatamanager.startJulianDay);
                l1 = Utils.getMillisFromJulianDay(arptaskdatamanager.endJulianDay);
                list = ArpTaskDataManager.generateTaskMonthDataList(arptaskdatamanager.todayMonthData, arptaskdatamanager.startJulianDay, arptaskdatamanager.endJulianDay);
                list1 = arptaskdatamanager.initAccountsAndDetermineIfShouldLoad(immutablemap, l, l1, flag2);
                obj1 = new ArrayList();
                obj2 = arptaskdatamanager.taskAccounts.values().iterator();
                do
                {
                    if (!((Iterator) (obj2)).hasNext())
                    {
                        break;
                    }
                    TaskAccount taskaccount = (TaskAccount)((Iterator) (obj2)).next();
                    if (!list1.contains(taskaccount))
                    {
                        ((List) (obj1)).add(taskaccount);
                    }
                } while (true);
                arptaskdatamanager.markTaskAccountsInitialLoadStart(list1);
                obj2 = (ArrayList)obj1;
                k = ((ArrayList) (obj2)).size();
                i = 0;
_L4:
                TaskAccount taskaccount1;
                if (i >= k)
                {
                    break MISSING_BLOCK_LABEL_310;
                }
                taskaccount1 = (TaskAccount)((ArrayList) (obj2)).get(i);
                obj1 = AccountUtil.newGoogleAccount(taskaccount1.getAccountName());
                if (immutablemap == null) goto _L2; else goto _L1
_L1:
                obj1 = (Settings)immutablemap.get(obj1);
                if (!(obj1 instanceof GoogleSettings)) goto _L2; else goto _L3
_L3:
                obj1 = (GoogleSettings)obj1;
_L5:
                boolean flag1;
                if (obj1 != null && ((GoogleSettings) (obj1)).areTasksVisible())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                taskaccount1.loadTasks(l, l1, list, flag1, flag2, 0);
                i++;
                if (true) goto _L4; else goto _L2
_L2:
                obj1 = null;
                  goto _L5
                obj1 = arptaskdatamanager.todayMonthData;
                int j = ((MonthData) (obj1)).startDay;
                j = ((((MonthData) (obj1)).numDays + j) - 1) + 31;
                long l2 = Utils.getMillisFromJulianDay(j);
                obj1 = ArpTaskDataManager.generateTaskMonthDataList(arptaskdatamanager.todayMonthData, arptaskdatamanager.startJulianDay, j);
                for (Iterator iterator = list1.iterator(); iterator.hasNext(); ((TaskAccount)iterator.next()).loadTasks(l, l2, ((List) (obj1)), true, true, 0)) { }
                for (obj1 = list1.iterator(); ((Iterator) (obj1)).hasNext(); ((TaskAccount)((Iterator) (obj1)).next()).loadTasks(l, l1, list, true, true, 1)) { }
                return;
            }

            .Lambda._cls1(boolean flag)
            {
                arg$1 = ArpTaskDataManager.this;
                arg$2 = flag;
            }
        }

        com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newFailureLoggingCallback(new .Lambda._cls1(flag), TAG, "Unable to load account settings.", new Object[0]);
        CalendarExecutor calendarexecutor = CalendarExecutor.BACKGROUND;
        if (futurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), futurecallback), calendarexecutor);
            return;
        }
    }

    final void logRevealReason(String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "loading", "ui_revealed", s, null);
            return;
        }
    }

    final void markTaskAccountsInitialLoadStart(List list)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isInitialLoad;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        isInitialLoad = false;
        if (!list.isEmpty() || !tasksAccountsShortLoading.isEmpty())
        {
            break MISSING_BLOCK_LABEL_123;
        }
        if (tasksReady) goto _L1; else goto _L3
_L3:
        setTasksReady();
        list = AnalyticsLoggerHolder.instance;
        if (list != null)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        list;
        this;
        JVM INSTR monitorexit ;
        throw list;
        ((AnalyticsLogger)list).trackEvent(context, "loading", "ui_revealed", "no_tasks", null);
        LatencyLoggerHolder.get().markAt(17);
        LatencyLoggerHolder.get().markAt(16);
          goto _L1
        TaskAccount taskaccount1;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); tasksAccountsShortLoading.add(taskaccount1.getAccountName()))
        {
            taskaccount1 = (TaskAccount)iterator.next();
        }

        list = list.iterator();
        while (list.hasNext()) 
        {
            TaskAccount taskaccount = (TaskAccount)list.next();
            tasksAccountsFullLoading.add(taskaccount.getAccountName());
        }
          goto _L1
    }

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            todayJulianDay = -1;
            break;
        }
        updateToday();
    }

    public final void onDestroy()
    {
        CalendarProperties calendarproperties = CalendarProperties.instance;
        if (calendarproperties == null)
        {
            throw new NullPointerException(String.valueOf("CalendarProperties#initialize(...) must be called first"));
        } else
        {
            ((CalendarProperties)calendarproperties).unregisterListener(0, this);
            settingsSubscription.cancel(false);
            return;
        }
    }

    public final void onTaskAccountChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHGN6QPFAHGN6QQ1CDHMUTBEEGTIILG_0()
    {
        controller.executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new com.google.android.calendar.CalendarController.Command(128L));
    }

    public final void onTaskAccountLoaded(TaskAccount taskaccount, Integer integer)
    {
        TaskAccount.Tasks tasks;
        String s;
        Object obj;
        s = taskaccount.getAccountName();
        tasks = taskaccount.getTasks();
        updateMonthData(todayMonthData, s, tasks.monthsTasksMap);
        MonthData amonthdata[] = monthDatas;
        int l = amonthdata.length;
        int i = 0;
        while (i < l) 
        {
            MonthData monthdata = amonthdata[i];
            if (monthdata == null)
            {
                continue;
            }
            boolean flag;
            if ((monthdata.startDay + monthdata.numDays) - 1 >= startJulianDay && monthdata.startDay <= endJulianDay)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                updateMonthData(monthdata, s, tasks.monthsTasksMap);
            }
            i++;
        }
        markTaskAccountInitialLoadEnd(s, integer);
        obj = arpLoadTester;
        if (((ArpLoadTester) (obj)).shouldProfile)
        {
            ((ArpLoadTester) (obj)).latencyLogger.markAt(42);
        }
        obj = new ArrayListMultimap();
        Iterator iterator = tasks.tasksList.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            TimelineItem timelineitem = (TimelineItem)iterator.next();
            String s1 = timelineitem.getTitle();
            if (!TextUtils.isEmpty(s1))
            {
                ((Multimap) (obj)).put(s1.toLowerCase(), timelineitem);
            }
        } while (true);
        this;
        JVM INSTR monitorenter ;
        allTasks.put(s, obj);
        this;
        JVM INSTR monitorexit ;
        int j;
        int k;
        int i1;
        ArpLoadTester arploadtester = arpLoadTester;
        if (arploadtester.shouldProfile)
        {
            arploadtester.latencyLogger.markAt(43);
        }
        if (integer == null || integer.intValue() != 1)
        {
            break MISSING_BLOCK_LABEL_548;
        }
        if (haveLoggedAccountAlready == null)
        {
            haveLoggedAccountAlready = new HashSet();
        }
        if (haveLoggedAccountAlready.contains(taskaccount.getAccountName()))
        {
            break MISSING_BLOCK_LABEL_548;
        }
        haveLoggedAccountAlready.add(taskaccount.getAccountName());
        taskaccount = tasks.tasksList.iterator();
        j = 0;
        i1 = 0;
        k = 0;
        do
        {
            if (!taskaccount.hasNext())
            {
                break;
            }
            integer = (TimelineTask)(TimelineItem)taskaccount.next();
            if (!((TimelineTask) (integer)).isDone)
            {
                boolean flag1;
                if (((TimelineTask) (integer)).unscheduled)
                {
                    k++;
                } else
                {
                    i1++;
                }
                if (((TimelineTask) (integer)).taskAssistHolder != null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    j++;
                }
            }
        } while (true);
        break MISSING_BLOCK_LABEL_450;
        taskaccount;
        this;
        JVM INSTR monitorexit ;
        throw taskaccount;
        taskaccount = AnalyticsLoggerHolder.instance;
        if (taskaccount == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        taskaccount = (AnalyticsLogger)taskaccount;
        taskaccount.trackTiming(context, "reminders_count", k * 1000, "num_carried_over_reminders", null);
        taskaccount.trackTiming(context, "reminders_count", i1 * 1000, "num_future_reminders", null);
        taskaccount.trackTiming(context, "reminders_count", j * 1000, "num_reminders_with_assist", null);
        taskaccount = arpLoadTester;
        if (((ArpLoadTester) (taskaccount)).shouldProfile)
        {
            ((ArpLoadTester) (taskaccount)).latencyLogger.markAt(44);
        }
        return;
    }

    public final void refreshComplete()
    {
        loadAccounts(false);
    }

    public final void refreshStart()
    {
    }

    final void setTasksReady()
    {
        this;
        JVM INSTR monitorenter ;
        tasksReady = true;
        todayMonthData.setTasksReady();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void updateToday()
    {
        Object obj = DateTimeService.getInstance();
        Time time;
        int i;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        obj = DateTimeService.toDateTimeImpl((new DateTimeImpl(l, ((DateTimeService) (obj)).calendarTimeZone)).withTime(0, 0, 0));
        time = ((DateTimeImpl) (obj)).time;
        time.writeFieldsToImpl();
        i = android.text.format.Time.getJulianDay(time.impl.toMillis(false), ((DateTimeImpl) (obj)).time.gmtoff);
        if (todayJulianDay != i)
        {
            boolean flag;
            if (todayJulianDay != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            loadAccounts(flag);
        }
        todayJulianDay = i;
    }


    private class _cls1
        implements Runnable
    {

        private final ArpTaskDataManager this$0;

        public final void run()
        {
            synchronized (ArpTaskDataManager.this)
            {
                if (!tasksReady)
                {
                    todayMonthData.setEventsReady();
                    setTasksReady();
                    logRevealReason("timeout");
                }
            }
            return;
            exception;
            arptaskdatamanager;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls1()
        {
            this$0 = ArpTaskDataManager.this;
            super();
        }
    }

}
