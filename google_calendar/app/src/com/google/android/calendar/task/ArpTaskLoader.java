// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.accounts.Account;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.api.settings.Settings;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.TimelineTaskType;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.task:
//            TaskLoader, TaskConnection, ArpRemindersBufferIterator, BundleTaskResults

public class ArpTaskLoader
    implements TaskLoader
{
    final class TaskResults extends BundleTaskResults
    {

        private final com.google.android.calendar.timely.RangedData.EventResults finalStorage;
        private final ArpTaskLoader this$0;

        public final void finalizeStorage()
        {
            super.finalizeStorage();
            TimelineTaskType timelinetasktype;
            for (Iterator iterator = mList.iterator(); iterator.hasNext(); finalStorage.addTimelineItem(timelinetasktype))
            {
                timelinetasktype = (TimelineTaskType)iterator.next();
                if (timelinetasktype instanceof TimelineTaskBundle)
                {
                    ((TimelineTaskBundle)timelinetasktype).updateTitles(context);
                }
            }

        }

        TaskResults(com.google.android.calendar.timely.RangedData.EventResults eventresults, int i, int j)
        {
            this$0 = ArpTaskLoader.this;
            super(i, j);
            finalStorage = eventresults;
        }
    }


    public final Context context;
    private final TaskConnection taskConnection;

    public ArpTaskLoader(Context context1, TaskConnection taskconnection)
    {
        context = context1;
        taskConnection = taskconnection;
    }

    public final void loadTasks(TaskLoader.TaskProcessor taskprocessor, boolean flag)
    {
        ImmutableMap immutablemap;
        Account aaccount[];
        int i;
        int j;
        int k;
        int l;
        long l1;
        long l2;
        ListenableFutureCache listenablefuturecache;
        if (flag)
        {
            i = taskprocessor.getStartDay() - 366;
        } else
        {
            i = taskprocessor.getStartDay();
        }
        k = taskprocessor.getEndDay();
        l1 = Utils.getMillisFromJulianDay(i);
        l2 = Utils.getMillisFromJulianDay(k);
        listenablefuturecache = SettingsCache.instance;
        if (listenablefuturecache == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        immutablemap = (ImmutableMap)Futures.getUnchecked(((ListenableFutureCache)listenablefuturecache).getValueAsync());
        aaccount = AccountsUtil.getGoogleAccounts(context);
        l = aaccount.length;
        j = 0;
_L4:
        if (j >= l) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        Object obj2;
        obj2 = aaccount[j];
        obj1 = ((Account) (obj2)).name;
        if (immutablemap == null)
        {
            break MISSING_BLOCK_LABEL_281;
        }
        obj = (Settings)immutablemap.get(obj2);
        if (!(obj instanceof GoogleSettings))
        {
            break MISSING_BLOCK_LABEL_281;
        }
        obj = (GoogleSettings)obj;
_L3:
        boolean flag1;
        if (obj != null && ((GoogleSettings) (obj)).areTasksVisible())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!AccountUtil.isGoogleAccount(((Account) (obj2))) || !flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = taskConnection.loadTasksSynchronous(context, ((String) (obj1)), l1, l2);
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj1 = new ArpRemindersBufferIterator(context, ((com.google.android.gms.reminders.model.RemindersBuffer) (obj)), ((String) (obj1)));
        obj2 = new TaskResults(taskprocessor.getStorage(), i, k);
        for (; ((ArpRemindersBufferIterator) (obj1)).hasNext(); ((com.google.android.calendar.timely.MonthData.TaskResults) (obj2)).addTimelineItem((TimelineTask)((ArpRemindersBufferIterator) (obj1)).next())) { }
        break MISSING_BLOCK_LABEL_292;
        taskprocessor;
        if (((AbstractDataBuffer) (obj)).zzaKT != null)
        {
            ((AbstractDataBuffer) (obj)).zzaKT.close();
        }
        throw taskprocessor;
        obj = null;
          goto _L3
        ((BundleTaskResults) (obj2)).finalizeStorage();
        if (((AbstractDataBuffer) (obj)).zzaKT != null)
        {
            ((AbstractDataBuffer) (obj)).zzaKT.close();
        }
        j++;
          goto _L4
_L2:
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/task/ArpTaskLoader);
    }
}
