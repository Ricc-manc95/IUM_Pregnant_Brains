// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.timely.MonthData;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskDataManager, TaskAccount

final class arg._cls2
    implements Consumer
{

    private final ArpTaskDataManager arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        List list;
        ArpTaskDataManager arptaskdatamanager;
        List list1;
        ImmutableMap immutablemap;
        Object obj1;
        int i;
        int k;
        long l;
        long l1;
        boolean flag1;
        arptaskdatamanager = arg$1;
        flag1 = arg$2;
        immutablemap = (ImmutableMap)obj;
        arptaskdatamanager.startJulianDay = arptaskdatamanager.todayMonthData.startDay - 366;
        obj = arptaskdatamanager.todayMonthData;
        i = ((MonthData) (obj)).startDay;
        arptaskdatamanager.endJulianDay = ((((MonthData) (obj)).numDays + i) - 1) + 366;
        l = Utils.getMillisFromJulianDay(arptaskdatamanager.startJulianDay);
        l1 = Utils.getMillisFromJulianDay(arptaskdatamanager.endJulianDay);
        list = ArpTaskDataManager.generateTaskMonthDataList(arptaskdatamanager.todayMonthData, arptaskdatamanager.startJulianDay, arptaskdatamanager.endJulianDay);
        list1 = arptaskdatamanager.initAccountsAndDetermineIfShouldLoad(immutablemap, l, l1, flag1);
        obj = new ArrayList();
        obj1 = arptaskdatamanager.taskAccounts.values().iterator();
        do
        {
            if (!((Iterator) (obj1)).hasNext())
            {
                break;
            }
            TaskAccount taskaccount = (TaskAccount)((Iterator) (obj1)).next();
            if (!list1.contains(taskaccount))
            {
                ((List) (obj)).add(taskaccount);
            }
        } while (true);
        arptaskdatamanager.markTaskAccountsInitialLoadStart(list1);
        obj1 = (ArrayList)obj;
        k = ((ArrayList) (obj1)).size();
        i = 0;
_L4:
        TaskAccount taskaccount1;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_310;
        }
        taskaccount1 = (TaskAccount)((ArrayList) (obj1)).get(i);
        obj = AccountUtil.newGoogleAccount(taskaccount1.getAccountName());
        if (immutablemap == null) goto _L2; else goto _L1
_L1:
        obj = (Settings)immutablemap.get(obj);
        if (!(obj instanceof GoogleSettings)) goto _L2; else goto _L3
_L3:
        obj = (GoogleSettings)obj;
_L5:
        boolean flag;
        if (obj != null && ((GoogleSettings) (obj)).areTasksVisible())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        taskaccount1.loadTasks(l, l1, list, flag, flag1, 0);
        i++;
        if (true) goto _L4; else goto _L2
_L2:
        obj = null;
          goto _L5
        obj = arptaskdatamanager.todayMonthData;
        int j = ((MonthData) (obj)).startDay;
        j = ((((MonthData) (obj)).numDays + j) - 1) + 31;
        long l2 = Utils.getMillisFromJulianDay(j);
        obj = ArpTaskDataManager.generateTaskMonthDataList(arptaskdatamanager.todayMonthData, arptaskdatamanager.startJulianDay, j);
        for (Iterator iterator = list1.iterator(); iterator.hasNext(); ((TaskAccount)iterator.next()).loadTasks(l, l2, ((List) (obj)), true, true, 0)) { }
        for (obj = list1.iterator(); ((Iterator) (obj)).hasNext(); ((TaskAccount)((Iterator) (obj)).next()).loadTasks(l, l1, list, true, true, 1)) { }
        return;
    }

    (ArpTaskDataManager arptaskdatamanager, boolean flag)
    {
        arg$1 = arptaskdatamanager;
        arg$2 = flag;
    }
}
