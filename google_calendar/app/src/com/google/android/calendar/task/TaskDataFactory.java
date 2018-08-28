// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.text.TextUtils;
import com.google.android.calendar.latency.LatencyLogger;
import com.google.android.calendar.search.OnSearchQueryFinishedListener;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.task:
//            ArpTaskConnection, ArpTaskDataManager, TaskAccount, ArpTaskAccount, 
//            TaskConnection

public final class TaskDataFactory
{

    public static TaskDataFactory instance;
    private TaskConnection currentTaskConnection;
    public ArpTaskDataManager currentTaskDataManager;
    public final List onTasksChangedListeners = new ArrayList();

    public TaskDataFactory()
    {
        currentTaskDataManager = null;
        currentTaskConnection = null;
    }

    public final TaskConnection getTaskConnection()
    {
        this;
        JVM INSTR monitorenter ;
        TaskConnection taskconnection;
        if (currentTaskConnection == null)
        {
            currentTaskConnection = new ArpTaskConnection();
        }
        taskconnection = currentTaskConnection;
        this;
        JVM INSTR monitorexit ;
        return taskconnection;
        Exception exception;
        exception;
        throw exception;
    }

    public final void onTasksChanged(List list)
    {
        if (currentTaskDataManager != null)
        {
            ArrayList arraylist = (ArrayList)new ArrayList(currentTaskDataManager.taskAccounts.values());
            int j = arraylist.size();
            for (int i = 0; i < j;)
            {
                Object obj = arraylist.get(i);
                i++;
                obj = (ArpTaskAccount)(TaskAccount)obj;
                if (((ArpTaskAccount) (obj)).isVisible)
                {
                    ((ArpTaskAccount) (obj)).listener.onTaskAccountChanged$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHGN6QPFAHGN6QQ1CDHMUTBEEGTIILG_0();
                }
                if (((ArpTaskAccount) (obj)).shouldProfile)
                {
                    ((ArpTaskAccount) (obj)).latencyLogger.markAt(36);
                }
                (new ArpTaskAccount.AsyncProcessChangedTasks(((ArpTaskAccount) (obj)), ((ArpTaskAccount) (obj)).taskMonthDataList)).execute(new List[] {
                    list
                });
            }

        }
        for (list = onTasksChangedListeners.iterator(); list.hasNext(); ((OnTasksChangedListener)list.next()).onTasksChanged()) { }
    }

    public final void searchTasks(String s, OnSearchQueryFinishedListener onsearchqueryfinishedlistener)
    {
        String s1;
        ArrayList arraylist;
        if (currentTaskDataManager == null || TextUtils.isEmpty(s))
        {
            onsearchqueryfinishedlistener.onSearchQueryFinished(null);
            return;
        }
        s1 = s.toLowerCase();
        arraylist = new ArrayList();
        s = currentTaskDataManager;
        s;
        JVM INSTR monitorenter ;
        for (Iterator iterator = currentTaskDataManager.allTasks.values().iterator(); iterator.hasNext();)
        {
            Multimap multimap = (Multimap)iterator.next();
            Iterator iterator1 = multimap.keySet().iterator();
            while (iterator1.hasNext()) 
            {
                String s2 = (String)iterator1.next();
                if (s2.contains(s1))
                {
                    arraylist.addAll(multimap.get(s2));
                }
            }
        }

        break MISSING_BLOCK_LABEL_154;
        onsearchqueryfinishedlistener;
        s;
        JVM INSTR monitorexit ;
        throw onsearchqueryfinishedlistener;
        s;
        JVM INSTR monitorexit ;
        onsearchqueryfinishedlistener.onSearchQueryFinished(new com.google.android.calendar.search.SearchQueryHandler.SearchResults(arraylist));
        return;
    }

    private class OnTasksChangedListener
    {

        public abstract void onTasksChanged();
    }

}
