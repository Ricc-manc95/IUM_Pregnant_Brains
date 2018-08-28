// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import android.util.SparseArray;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.TimelineTaskBundle;
import com.google.android.calendar.timely.TimelineTaskType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class BundleTaskResults extends com.google.android.calendar.timely.MonthData.TaskResults
{

    private SparseArray doneBundles;
    private List futureTasks;
    public List tasks;
    private TimelineTaskBundle unscheduled;

    BundleTaskResults(int i, int j)
    {
        super(i, j);
        doneBundles = new SparseArray();
        futureTasks = new ArrayList();
        tasks = new ArrayList();
    }

    private final void addBundleToTimeline(TimelineTaskBundle timelinetaskbundle)
    {
label0:
        {
            if (timelinetaskbundle.timelineTaskList.size() > 0)
            {
                if (timelinetaskbundle.timelineTaskList.size() != 1)
                {
                    break label0;
                }
                super.addTimelineItem((TimelineItem)timelinetaskbundle.timelineTaskList.get(0));
            }
            return;
        }
        super.addTimelineItem(timelinetaskbundle);
    }

    public final void addTimelineItem(TimelineItem timelineitem)
    {
        timelineitem = (TimelineTask)timelineitem;
        if (((TimelineTask) (timelineitem)).unscheduled && !((TimelineTask) (timelineitem)).isDone)
        {
            if (unscheduled == null)
            {
                unscheduled = new TimelineTaskBundle(timelineitem);
                return;
            } else
            {
                unscheduled.addTimelineTask(timelineitem);
                return;
            }
        }
        if (((TimelineTask) (timelineitem)).isDone)
        {
            int i = ((TimelineTask) (timelineitem)).timeRange.getStartDay();
            TimelineTaskBundle timelinetaskbundle = (TimelineTaskBundle)doneBundles.get(i);
            if (timelinetaskbundle == null)
            {
                doneBundles.put(i, new TimelineTaskBundle(timelineitem));
                return;
            } else
            {
                timelinetaskbundle.addTimelineTask(timelineitem);
                return;
            }
        } else
        {
            futureTasks.add(timelineitem);
            return;
        }
    }

    public void finalizeStorage()
    {
        int l = 0;
        Collections.sort(futureTasks, TimelineTaskType.DATE_COMPARATOR);
        TimelineTaskBundle timelinetaskbundle;
        int j;
        if (futureTasks.size() > 1)
        {
            Object obj = (TimelineTaskType)futureTasks.get(0);
            int i = 1;
            while (i < futureTasks.size()) 
            {
                TimelineTaskType timelinetasktype = (TimelineTaskType)futureTasks.get(i);
                if (TimelineTaskType.DATE_COMPARATOR.compare(obj, timelinetasktype) == 0)
                {
                    if (!(obj instanceof TimelineTaskBundle))
                    {
                        obj = new TimelineTaskBundle((TimelineTask)obj);
                    }
                    ((TimelineTaskBundle)obj).addTimelineTask((TimelineTask)timelinetasktype);
                } else
                {
                    super.addTimelineItem(((TimelineItem) (obj)));
                    if (obj instanceof TimelineTaskBundle)
                    {
                        ((TimelineTaskBundle)obj).sort();
                    }
                    obj = timelinetasktype;
                }
                i++;
            }
            super.addTimelineItem(((TimelineItem) (obj)));
            if (obj instanceof TimelineTaskBundle)
            {
                ((TimelineTaskBundle)obj).sort();
            }
            break MISSING_BLOCK_LABEL_167;
        } else
        {
            if (futureTasks.size() == 1)
            {
                super.addTimelineItem((TimelineItem)futureTasks.get(0));
            }
            continue;
        }
        do
        {
            j = l;
            if (unscheduled != null)
            {
                unscheduled.sort();
                if (unscheduled != null && unscheduled.timelineTaskList.size() != 0)
                {
                    HashSet hashset = new HashSet();
                    Object obj1 = futureTasks.iterator();
                    do
                    {
                        if (!((Iterator) (obj1)).hasNext())
                        {
                            break;
                        }
                        TimelineTask timelinetask = (TimelineTask)(TimelineTaskType)((Iterator) (obj1)).next();
                        if (timelinetask.recurringSometimeToday)
                        {
                            hashset.add(timelinetask.recurrenceId);
                        }
                    } while (true);
                    long l1 = unscheduled.timeRange.getUtcStartMillis();
label0:
                    for (int k = 0; k < doneBundles.size(); k++)
                    {
                        obj1 = (TimelineTaskBundle)doneBundles.valueAt(k);
                        if (((TimelineTaskBundle) (obj1)).timeRange.getUtcStartMillis() != l1)
                        {
                            continue;
                        }
                        obj1 = ((TimelineTaskBundle) (obj1)).timelineTaskList.iterator();
                        do
                        {
                            TimelineTask timelinetask1;
                            do
                            {
                                if (!((Iterator) (obj1)).hasNext())
                                {
                                    continue label0;
                                }
                                timelinetask1 = (TimelineTask)(TimelineItem)((Iterator) (obj1)).next();
                            } while (!timelinetask1.recurringSometimeToday);
                            hashset.add(timelinetask1.recurrenceId);
                        } while (true);
                    }

                    Object obj2 = unscheduled.timelineTaskList;
                    obj1 = new HashSet();
                    obj2 = ((List) (obj2)).iterator();
                    while (((Iterator) (obj2)).hasNext()) 
                    {
                        TimelineTask timelinetask2 = (TimelineTask)((Iterator) (obj2)).next();
                        if (timelinetask2.recurrenceId != null)
                        {
                            if (!((Set) (obj1)).add(timelinetask2.recurrenceId))
                            {
                                ((Iterator) (obj2)).remove();
                            } else
                            if (hashset.contains(timelinetask2.recurrenceId))
                            {
                                ((Iterator) (obj2)).remove();
                            }
                        }
                    }
                }
                addBundleToTimeline(unscheduled);
            }
            for (j = l; j < doneBundles.size(); j++)
            {
                l = doneBundles.keyAt(j);
                timelinetaskbundle = (TimelineTaskBundle)doneBundles.get(l);
                timelinetaskbundle.sort();
                addBundleToTimeline(timelinetaskbundle);
            }

            for (Iterator iterator = mList.iterator(); iterator.hasNext();)
            {
                TimelineItem timelineitem = (TimelineItem)iterator.next();
                if (timelineitem instanceof TimelineTaskBundle)
                {
                    tasks.addAll(((TimelineTaskBundle)timelineitem).timelineTaskList);
                } else
                {
                    tasks.add(timelineitem);
                }
            }

            futureTasks = null;
            unscheduled = null;
            doneBundles = null;
            return;
        } while (true);
    }
}
