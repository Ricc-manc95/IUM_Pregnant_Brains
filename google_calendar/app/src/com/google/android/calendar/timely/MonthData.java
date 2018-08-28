// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Trace;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.SubscriptionInstance;
import com.google.android.apps.calendar.util.concurrent.SubscriptionManager;
import com.google.android.calendar.time.Time;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar.timely:
//            RangedData, TimelineItem, TimelineTaskType

public class MonthData
    implements RangedData
{
    static final class MonthEventResults
        implements RangedData.EventResults
    {

        public final ArrayList events;

        public final void addTimelineItem(TimelineItem timelineitem)
        {
            events.add(timelineitem);
        }

        MonthEventResults(int i)
        {
            events = new ArrayList(i);
        }
    }

    public static class TaskResults
        implements RangedData.EventResults
    {

        public final int endDay;
        public final List mList = new ArrayList();
        public final int startDay;

        public void addTimelineItem(TimelineItem timelineitem)
        {
            if (timelineitem instanceof TimelineTaskType)
            {
                timelineitem = (TimelineTaskType)timelineitem;
                mList.add(timelineitem);
            }
        }

        public boolean equals(Object obj)
        {
            return (obj instanceof TaskResults) && mList.equals(((TaskResults)obj).mList);
        }

        public int hashCode()
        {
            int l = mList.size();
            int i = 0;
            int j = 1;
            while (i < l) 
            {
                TimelineTaskType timelinetasktype = (TimelineTaskType)mList.get(i);
                int k;
                if (timelinetasktype == null || timelinetasktype.getId() == null)
                {
                    k = 0;
                } else
                {
                    k = timelinetasktype.getId().hashCode();
                }
                j = j * 31 + k;
                i++;
            }
            return j;
        }

        public TaskResults(int i, int j)
        {
            endDay = j;
            startDay = i;
        }
    }

    final class UpdateFinishedListenerImpl
        implements RangedData.UpdateFinishedListener
    {

        private AtomicInteger counter;
        private final MonthData this$0;

        private final void viewUpdateFinished()
        {
            if (eventsReady && tasksReady && viewUpdatePerformedListener != null)
            {
                viewUpdatePerformedListener.postOnViewUpdatePerformed();
            }
        }

        public final void notifyUpdateFinished()
        {
            if (counter.decrementAndGet() == 0)
            {
                viewUpdateFinished();
            }
        }

        UpdateFinishedListenerImpl(int i)
        {
            this$0 = MonthData.this;
            super();
            counter = new AtomicInteger(i);
            if (i == 0)
            {
                viewUpdateFinished();
            }
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/MonthData);
    private static int static_tag = 0;
    public boolean allTasksReady;
    public final boolean currentMonth;
    private int dateInfo[];
    private SparseArray daysToItems;
    private int eventToken;
    private List events;
    public boolean eventsReady;
    private final Map listeners = new HashMap();
    public int numDays;
    public int startDay;
    public final SubscriptionManager subscriptions = new SubscriptionManager();
    private int tag;
    private Map tasks;
    public boolean tasksReady;
    private Time timeRecycle;
    public RangedData.ViewUpdatePerformedListener viewUpdatePerformedListener;

    public MonthData(boolean flag)
    {
        timeRecycle = new Time();
        int i = static_tag;
        static_tag = i + 1;
        tag = i;
        dateInfo = new int[3];
        daysToItems = new SparseArray();
        currentMonth = flag;
    }

    private final void addItemsToDayBuckets(List list, SparseArray sparsearray)
    {
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            TimelineItem timelineitem = (TimelineItem)iterator.next();
            int j = timelineitem.getStartDay() - startDay;
            int i = j;
            if (j < 0)
            {
                i = 0;
            }
            int k = timelineitem.getEndDay() - startDay;
            j = k;
            if (k > numDays - 1)
            {
                j = numDays - 1;
            }
            while (i <= j) 
            {
                List list1 = (List)sparsearray.get(i);
                list = list1;
                if (list1 == null)
                {
                    list = new ArrayList();
                    sparsearray.put(i, list);
                }
                list.add(timelineitem);
                i++;
            }
        }

    }

    private final void checkDataReady()
    {
        boolean flag1 = false;
        if (!eventsReady) goto _L2; else goto _L1
_L1:
        Object obj;
        boolean flag;
        if (currentMonth && !tasksReady)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L3
_L3:
        flag = true;
_L5:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_573;
        }
        obj = new SparseArray();
        if (tasks != null)
        {
            Iterator iterator = tasks.values().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                TaskResults taskresults = (TaskResults)iterator.next();
                if (taskresults != null)
                {
                    addItemsToDayBuckets(taskresults.mList, ((SparseArray) (obj)));
                }
            } while (true);
        }
        break; /* Loop/switch isn't completed */
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        if (events != null)
        {
            addItemsToDayBuckets(events, ((SparseArray) (obj)));
        }
        int i = 0;
        while (i < numDays) 
        {
            List list = (List)((SparseArray) (obj)).get(i);
            if (list != null)
            {
                if (list.size() == 1)
                {
                    ((SparseArray) (obj)).put(i, Collections.singletonList((TimelineItem)list.get(0)));
                } else
                {
                    Collections.sort(list, TimelineItem.ItemComparator);
                    if (list instanceof ArrayList)
                    {
                        ((ArrayList)list).trimToSize();
                    }
                }
            }
            i++;
        }
        daysToItems = ((SparseArray) (obj));
        obj = listeners;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        obj1 = new HashMap();
        for (Iterator iterator2 = listeners.keySet().iterator(); iterator2.hasNext();)
        {
            int j = ((Integer)iterator2.next()).intValue();
            Iterator iterator3 = ((Map)listeners.get(Integer.valueOf(j))).values().iterator();
            while (iterator3.hasNext()) 
            {
                ((Map) (obj1)).put((RangedData.OnUpdateListener)iterator3.next(), Integer.valueOf(j));
            }
        }

        break MISSING_BLOCK_LABEL_348;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj;
        JVM INSTR monitorexit ;
        Object obj2 = (Collection)subscriptions.apply(this);
        if (!((Map) (obj1)).isEmpty() && !((Collection) (obj2)).isEmpty())
        {
            LogUtils.d(TAG, "Notifying %d listener(s) for %s", new Object[] {
                Integer.valueOf(((Map) (obj1)).size() + ((Collection) (obj2)).size()), this
            });
        }
        int k = ((flag1) ? 1 : 0);
        if (currentMonth)
        {
            k = ((Map) (obj1)).size();
            k = ((Collection) (obj2)).size() + k;
        }
        UpdateFinishedListenerImpl updatefinishedlistenerimpl = new UpdateFinishedListenerImpl(k);
        java.util.Map.Entry entry;
        for (obj1 = ((Map) (obj1)).entrySet().iterator(); ((Iterator) (obj1)).hasNext(); ((RangedData.OnUpdateListener)entry.getKey()).postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(this, ((Integer)entry.getValue()).intValue(), updatefinishedlistenerimpl))
        {
            entry = (java.util.Map.Entry)((Iterator) (obj1)).next();
        }

        class .Lambda._cls0
            implements Runnable
        {

            private final RangedData.UpdateFinishedListener arg$1;

            public final void run()
            {
                arg$1.notifyUpdateFinished();
            }

            .Lambda._cls0(RangedData.UpdateFinishedListener updatefinishedlistener)
            {
                arg$1 = updatefinishedlistener;
            }
        }

        for (Iterator iterator1 = ((Collection) (obj2)).iterator(); iterator1.hasNext(); ((ListenableFuture) (obj2)).addListener(new .Lambda._cls0(updatefinishedlistenerimpl), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE))
        {
            obj2 = (ListenableFuture)iterator1.next();
            updatefinishedlistenerimpl.getClass();
        }

    }

    private final void notifyListeners()
    {
        int i = 0;
        Map map = listeners;
        map;
        JVM INSTR monitorenter ;
        Object obj;
        obj = new HashMap();
        for (Iterator iterator1 = listeners.keySet().iterator(); iterator1.hasNext();)
        {
            int j = ((Integer)iterator1.next()).intValue();
            Iterator iterator2 = ((Map)listeners.get(Integer.valueOf(j))).values().iterator();
            while (iterator2.hasNext()) 
            {
                ((Map) (obj)).put((RangedData.OnUpdateListener)iterator2.next(), Integer.valueOf(j));
            }
        }

        break MISSING_BLOCK_LABEL_125;
        obj;
        map;
        JVM INSTR monitorexit ;
        throw obj;
        map;
        JVM INSTR monitorexit ;
        Object obj1 = (Collection)subscriptions.apply(this);
        if (!((Map) (obj)).isEmpty() && !((Collection) (obj1)).isEmpty())
        {
            LogUtils.d(TAG, "Notifying %d listener(s) for %s", new Object[] {
                Integer.valueOf(((Map) (obj)).size() + ((Collection) (obj1)).size()), this
            });
        }
        if (currentMonth)
        {
            i = ((Map) (obj)).size() + ((Collection) (obj1)).size();
        }
        UpdateFinishedListenerImpl updatefinishedlistenerimpl = new UpdateFinishedListenerImpl(i);
        java.util.Map.Entry entry;
        for (obj = ((Map) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext(); ((RangedData.OnUpdateListener)entry.getKey()).postUpdate$51666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFEHKMQPBCF4NKQRREEHK48OBKC4TKIMICCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UT39DLIMOU9FA9GMSPR5CH262T314HAN0P31EHIKCQBED5PMGPB49HKN6T35DPIN4EP9AO______0(this, ((Integer)entry.getValue()).intValue(), updatefinishedlistenerimpl))
        {
            entry = (java.util.Map.Entry)((Iterator) (obj)).next();
        }

        for (Iterator iterator = ((Collection) (obj1)).iterator(); iterator.hasNext(); ((ListenableFuture) (obj1)).addListener(new .Lambda._cls0(updatefinishedlistenerimpl), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE))
        {
            obj1 = (ListenableFuture)iterator.next();
            updatefinishedlistenerimpl.getClass();
        }

        return;
    }

    final void addEvents(RangedData.EventResults eventresults)
    {
        this;
        JVM INSTR monitorenter ;
        events = ((MonthEventResults)eventresults).events;
        setEventsReady();
        this;
        JVM INSTR monitorexit ;
        return;
        eventresults;
        throw eventresults;
    }

    public final boolean containsDay(int i)
    {
        return i >= startDay && i <= (startDay + numDays) - 1;
    }

    final int[] getDateInfo(int i)
    {
        int j = 1;
        if (i != 0)
        {
            j = (i - startDay) + 1;
        }
        dateInfo[2] = j;
        return (int[])dateInfo.clone();
    }

    public final String getDebugTag()
    {
        int i = tag;
        int j = dateInfo[0];
        int k = dateInfo[1];
        return (new StringBuilder(41)).append("[").append(i).append("]: [").append(j).append(", ").append(k + 1).append("]").toString();
    }

    public final int getEndDay()
    {
        return (startDay + numDays) - 1;
    }

    public final List getItems(int i)
    {
        this;
        JVM INSTR monitorenter ;
        List list;
        int j = startDay;
        list = (List)daysToItems.get(i - j);
        this;
        JVM INSTR monitorexit ;
        return list;
        Exception exception;
        exception;
        throw exception;
    }

    public final int getStartDay()
    {
        return startDay;
    }

    public final int getToken()
    {
        return eventToken;
    }

    public final boolean isDataReady()
    {
        if (eventsReady)
        {
            boolean flag;
            if (currentMonth && !tasksReady)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }

    public final void onTasksPopulated()
    {
        this;
        JVM INSTR monitorenter ;
        checkDataReady();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean populateTasks(String s, TaskResults taskresults)
    {
        this;
        JVM INSTR monitorenter ;
        if (tasks == null)
        {
            tasks = new HashMap();
        }
        if (!tasks.containsKey(s)) goto _L2; else goto _L1
_L1:
        boolean flag = ((TaskResults)tasks.get(s)).equals(taskresults);
        if (!flag) goto _L2; else goto _L3
_L3:
        flag = false;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        TaskResults taskresults1 = (TaskResults)tasks.get(s);
        if (taskresults1 == null)
        {
            break MISSING_BLOCK_LABEL_95;
        }
        if (!taskresults1.mList.isEmpty())
        {
            break MISSING_BLOCK_LABEL_140;
        }
        if (taskresults == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!taskresults.mList.isEmpty())
        {
            break MISSING_BLOCK_LABEL_140;
        }
        break; /* Loop/switch isn't completed */
_L6:
        tasks.put(s, taskresults);
        if (true) goto _L5; else goto _L4
        s;
        throw s;
_L4:
        flag = false;
          goto _L6
        flag = true;
          goto _L6
    }

    public final void recycle(int i)
    {
        int j = 1;
        this;
        JVM INSTR monitorenter ;
        Object obj;
        Object obj2;
        ArrayList arraylist;
        Object obj3;
        int l;
        if (i < startDay || i > (startDay + numDays) - 1)
        {
            j = 0;
        }
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_270;
        }
        obj2 = subscriptions;
        obj = ((SubscriptionManager) (obj2)).subscriptions;
        obj;
        JVM INSTR monitorenter ;
        arraylist = (ArrayList)new ArrayList(((SubscriptionManager) (obj2)).subscriptions);
        l = arraylist.size();
        j = 0;
_L2:
        if (j >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        obj3 = arraylist.get(j);
        j++;
        ((SubscriptionInstance)obj3).cancel(false);
        if (true) goto _L2; else goto _L1
        obj2;
        obj;
        JVM INSTR monitorexit ;
        throw obj2;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
_L1:
        if (!((SubscriptionManager) (obj2)).subscriptions.isEmpty())
        {
            throw new IllegalStateException();
        }
        obj;
        JVM INSTR monitorexit ;
        timeRecycle.setJulianDaySafe(i);
        Object obj1 = timeRecycle;
        ((Time) (obj1)).writeFieldsToImpl();
        long l1 = ((Time) (obj1)).impl.toMillis(false);
        obj1 = Calendar.getInstance();
        ((Calendar) (obj1)).setTimeInMillis(l1);
        int k = ((Calendar) (obj1)).get(1);
        dateInfo[0] = k;
        k = ((Calendar) (obj1)).get(2);
        dateInfo[1] = k;
        k = ((Calendar) (obj1)).get(5);
        dateInfo[2] = k;
        startDay = i - (k - 1);
        numDays = ((Calendar) (obj1)).getActualMaximum(5);
        daysToItems.clear();
        events = null;
        tasks = null;
        listeners.clear();
        eventsReady = false;
        eventToken = -1;
        this;
        JVM INSTR monitorexit ;
    }

    public final void registerListener(int i, RangedData.OnUpdateListener onupdatelistener)
    {
        this;
        JVM INSTR monitorenter ;
        if (!containsDay(i)) goto _L2; else goto _L1
_L1:
        Map map = listeners;
        map;
        JVM INSTR monitorenter ;
        Object obj;
        if (!listeners.containsKey(Integer.valueOf(i)))
        {
            break MISSING_BLOCK_LABEL_82;
        }
        obj = (Map)listeners.get(Integer.valueOf(i));
_L3:
        onupdatelistener.setListenerTag(i);
        ((Map) (obj)).put(Integer.valueOf(onupdatelistener.getListenerTagType()), onupdatelistener);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        obj = new HashMap();
        listeners.put(Integer.valueOf(i), obj);
          goto _L3
        onupdatelistener;
        map;
        JVM INSTR monitorexit ;
        throw onupdatelistener;
        onupdatelistener;
        this;
        JVM INSTR monitorexit ;
        throw onupdatelistener;
    }

    public final void setEventsReady()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        Trace.beginSection("MonthData dataReady");
        eventsReady = true;
        if (!eventsReady) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        TaskResults taskresults;
        int i;
        if (currentMonth && !tasksReady)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L3
_L11:
        if (i == 0) goto _L5; else goto _L4
_L4:
        obj = new SparseArray();
        if (tasks == null) goto _L7; else goto _L6
_L6:
        obj1 = tasks.values().iterator();
_L10:
        if (!((Iterator) (obj1)).hasNext()) goto _L7; else goto _L8
_L8:
        taskresults = (TaskResults)((Iterator) (obj1)).next();
        if (taskresults == null) goto _L10; else goto _L9
_L9:
        addItemsToDayBuckets(taskresults.mList, ((SparseArray) (obj)));
          goto _L10
        obj;
        throw obj;
_L2:
        i = 0;
          goto _L11
_L7:
        i = ((flag) ? 1 : 0);
        if (events == null)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        addItemsToDayBuckets(events, ((SparseArray) (obj)));
        i = ((flag) ? 1 : 0);
_L13:
        if (i >= numDays)
        {
            break MISSING_BLOCK_LABEL_233;
        }
        obj1 = (List)((SparseArray) (obj)).get(i);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_259;
        }
        if (((List) (obj1)).size() == 1)
        {
            ((SparseArray) (obj)).put(i, Collections.singletonList((TimelineItem)((List) (obj1)).get(0)));
            break MISSING_BLOCK_LABEL_259;
        }
        Collections.sort(((List) (obj1)), TimelineItem.ItemComparator);
        if (obj1 instanceof ArrayList)
        {
            ((ArrayList)obj1).trimToSize();
        }
        break MISSING_BLOCK_LABEL_259;
        daysToItems = ((SparseArray) (obj));
        notifyListeners();
_L5:
        Trace.endSection();
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        if (i != 0) goto _L2; else goto _L12
_L12:
        i = 1;
          goto _L11
        i++;
          goto _L13
    }

    public final void setTasksReady()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        tasksReady = true;
        if (!eventsReady) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        TaskResults taskresults;
        int i;
        if (currentMonth && !tasksReady)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L3
_L11:
        if (i == 0) goto _L5; else goto _L4
_L4:
        obj = new SparseArray();
        if (tasks == null) goto _L7; else goto _L6
_L6:
        obj1 = tasks.values().iterator();
_L10:
        if (!((Iterator) (obj1)).hasNext()) goto _L7; else goto _L8
_L8:
        taskresults = (TaskResults)((Iterator) (obj1)).next();
        if (taskresults == null) goto _L10; else goto _L9
_L9:
        addItemsToDayBuckets(taskresults.mList, ((SparseArray) (obj)));
          goto _L10
        obj;
        throw obj;
_L2:
        i = 0;
          goto _L11
_L7:
        i = ((flag) ? 1 : 0);
        if (events == null)
        {
            break MISSING_BLOCK_LABEL_148;
        }
        addItemsToDayBuckets(events, ((SparseArray) (obj)));
        i = ((flag) ? 1 : 0);
_L13:
        if (i >= numDays)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        obj1 = (List)((SparseArray) (obj)).get(i);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_250;
        }
        if (((List) (obj1)).size() == 1)
        {
            ((SparseArray) (obj)).put(i, Collections.singletonList((TimelineItem)((List) (obj1)).get(0)));
            break MISSING_BLOCK_LABEL_250;
        }
        Collections.sort(((List) (obj1)), TimelineItem.ItemComparator);
        if (obj1 instanceof ArrayList)
        {
            ((ArrayList)obj1).trimToSize();
        }
        break MISSING_BLOCK_LABEL_250;
        daysToItems = ((SparseArray) (obj));
        notifyListeners();
_L5:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        if (i != 0) goto _L2; else goto _L12
_L12:
        i = 1;
          goto _L11
        i++;
          goto _L13
    }

    public final void setToken(int i)
    {
        eventToken = i;
    }

    public String toString()
    {
        int ai[] = dateInfo;
        int i = ai[0];
        int j = ai[1];
        int k = ai[2];
        return (new StringBuilder(35)).append(i).append("/").append(j + 1).append("/").append(k).toString();
    }

    public final boolean unregisterListener(int i, int j)
    {
        Map map = listeners;
        map;
        JVM INSTR monitorenter ;
        Map map1;
        if (!listeners.containsKey(Integer.valueOf(i)))
        {
            break MISSING_BLOCK_LABEL_130;
        }
        map1 = (Map)listeners.get(Integer.valueOf(i));
        if (map1 == null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        Iterator iterator = map1.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            if (((Integer)((java.util.Map.Entry)iterator.next()).getKey()).intValue() != j)
            {
                continue;
            }
            iterator.remove();
            break;
        } while (true);
        if (map1.isEmpty())
        {
            listeners.remove(Integer.valueOf(i));
        }
        map;
        JVM INSTR monitorexit ;
        return true;
        map;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
