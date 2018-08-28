// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.calendar.time.clock.Clock;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskDataLoader

public final class TasksApi
{

    private final TaskDataLoader dataLoader;
    private final ObservableReference timeZone;

    public TasksApi(TaskDataLoader taskdataloader, ObservableReference observablereference)
    {
        dataLoader = taskdataloader;
        timeZone = observablereference;
    }

    public final FluentFuture getAsync(int i, int j, boolean flag)
    {
        boolean flag1;
        if (i <= j)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException();
        }
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj1)
            {
                obj1 = (List)obj1;
                class .Lambda._cls6
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls6();

                    public final Object apply(Object obj2)
                    {
                        obj2 = (TaskData)obj2;
                        return new AutoValue_TimeRangeEntry(((TaskData) (obj2)).getId(), (new AutoValue_TaskImpl.Builder()).setTaskData(((TaskData) (obj2))).build(), ((TaskData) (obj2)).getTimeRange());
                    }


                        private .Lambda._cls6()
                        {
                        }
                }

                Function function = .Lambda._cls6..instance;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (function == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new com.google.common.collect.Iterables._cls5(((Iterable) (obj1)), function);
                }
            }


            private .Lambda._cls0()
            {
            }
        }

        class .Lambda._cls1
            implements Function
        {

            private final int arg$1;
            private final int arg$2;

            public final Object apply(Object obj1)
            {
                int k = arg$1;
                int i1 = arg$2;
                obj1 = (Iterable)obj1;
                class .Lambda._cls5
                    implements Predicate
                {

                    private final int arg$1;
                    private final int arg$2;

                    public final boolean apply(Object obj2)
                    {
                        int j1 = arg$1;
                        int k1 = arg$2;
                        obj2 = (TimeRangeEntry)obj2;
                        return ((TimeRangeEntry) (obj2)).getRange().getEndDay() >= j1 || ((TimeRangeEntry) (obj2)).getRange().getStartDay() <= k1;
                    }

                        .Lambda._cls5(int i, int j)
                        {
                            arg$1 = i;
                            arg$2 = j;
                        }
                }

                .Lambda._cls5 _lcls5 = new .Lambda._cls5(k, i1);
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                if (_lcls5 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new com.google.common.collect.Iterables._cls4(((Iterable) (obj1)), _lcls5);
                }
            }

            .Lambda._cls1(int i, int j)
            {
                arg$1 = i;
                arg$2 = j;
            }
        }

        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj1)
            {
                return Lists.newArrayList((Iterable)obj1);
            }


            private .Lambda._cls2()
            {
            }
        }

        FluentFuture fluentfuture = (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(dataLoader.loadAsync(i, j), .Lambda._cls0..instance, new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND)), new .Lambda._cls1(i, j), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND)), .Lambda._cls2..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        Object obj = fluentfuture;
        if (flag)
        {
            obj = (TimeZone)timeZone.get();
            class .Lambda._cls3
                implements Function
            {

                private final int arg$1;

                public final Object apply(Object obj1)
                {
                    Object obj2;
                    List list;
                    int l1;
                    l1 = arg$1;
                    obj1 = (List)obj1;
                    list = Bucketer.bucket(new TaskBucketerConfig(), ((List) (obj1)));
                    obj1 = new TaskFilter..Lambda._cls0(l1);
                    obj2 = list.iterator();
                    if (obj2 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (obj1 == null)
                    {
                        throw new NullPointerException();
                    }
                    Object obj3;
                    do
                    {
                        if (!((Iterator) (obj2)).hasNext())
                        {
                            break MISSING_BLOCK_LABEL_166;
                        }
                        obj3 = ((Iterator) (obj2)).next();
                    } while (!((Predicate) (obj1)).apply(obj3));
                    if (obj3 == null)
                    {
                        throw new NullPointerException();
                    }
                    obj2 = new Present(obj3);
_L1:
                    if (!((Optional) (obj2)).isPresent())
                    {
                        obj1 = list;
                    } else
                    {
                        obj1 = (TimeRangeEntry)((Optional) (obj2)).get();
                        Object obj4 = new HashSet();
                        for (Iterator iterator = list.iterator(); iterator.hasNext();)
                        {
                            Object obj5 = (TimeRangeEntry)iterator.next();
                            if (obj5 != obj1)
                            {
                                int k;
                                if (((TimeRangeEntry) (obj5)).getRange().getStartDay() == l1)
                                {
                                    k = 1;
                                } else
                                {
                                    k = 0;
                                }
                                if (k != 0)
                                {
                                    obj5 = (ImmutableList)((TaskSet)((TimeRangeEntry) (obj5)).getValue()).getItems();
                                    int j2 = ((ImmutableList) (obj5)).size();
                                    UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
                                    k = 0;
                                    while (k < j2) 
                                    {
                                        Object obj7 = ((ImmutableList) (obj5)).get(k);
                                        int j1 = k + 1;
                                        obj7 = ((TaskItem)obj7).getTaskData().getRecurrenceId();
                                        k = j1;
                                        if (obj7 != null)
                                        {
                                            ((Set) (obj4)).add(obj7);
                                            k = j1;
                                        }
                                    }
                                }
                            }
                        }

                        ArrayList arraylist = Lists.newArrayList(list);
                        obj1 = (TimeRangeEntry)((Optional) (obj2)).get();
                        if (!((Set) (obj4)).isEmpty())
                        {
                            obj6 = ((TaskSet)((TimeRangeEntry) (obj1)).getValue()).getItems();
                            obj4 = new TaskFilter..Lambda._cls1(((Set) (obj4)));
                            if (obj6 == null)
                            {
                                throw new NullPointerException();
                            }
                            if (obj4 == null)
                            {
                                throw new NullPointerException();
                            }
                            obj4 = new com.google.common.collect.Iterables._cls4(((Iterable) (obj6)), ((Predicate) (obj4)));
                            if (!((Iterable) (obj4)).iterator().hasNext())
                            {
                                obj1 = null;
                            } else
                            {
                                obj1 = TaskFilter.newBundleWith(((TimeRangeEntry) (obj1)), ((Iterable) (obj4)));
                            }
                        }
                        if (obj1 != null)
                        {
                            obj4 = (TaskSet)((TimeRangeEntry) (obj1)).getValue();
                            obj4 = ((TaskSet) (obj4)).toBuilder().setItems(ImmutableList.sortedCopyOf(TaskComparators.get(((TaskSet) (obj4)).isDone()), ((TaskSet) (obj4)).getItems())).build().getItems();
                            Object obj6 = new HashSet();
                            com.google.common.collect.ImmutableSet.Builder builder = new com.google.common.collect.ImmutableSet.Builder();
                            ImmutableList immutablelist = (ImmutableList)obj4;
                            int i2 = immutablelist.size();
                            UnmodifiableIterator unmodifiableiterator1 = (UnmodifiableIterator)null;
                            int i1 = 0;
                            do
                            {
                                if (i1 >= i2)
                                {
                                    break;
                                }
                                Object obj8 = immutablelist.get(i1);
                                int k1 = i1 + 1;
                                obj8 = (TaskItem)obj8;
                                if (((TaskItem) (obj8)).getTaskData().getRecurrenceId() != null)
                                {
                                    i1 = k1;
                                    if (!((Set) (obj6)).add(((TaskItem) (obj8)).getTaskData().getRecurrenceId()))
                                    {
                                        continue;
                                    }
                                }
                                obj8 = (com.google.common.collect.ImmutableSet.Builder)builder.add(obj8);
                                i1 = k1;
                            } while (true);
                            obj6 = builder.build();
                            if (((ImmutableList) (obj4)).size() != ((ImmutableSet) (obj6)).size())
                            {
                                obj1 = TaskFilter.newBundleWith(((TimeRangeEntry) (obj1)), ((Iterable) (obj6)));
                            }
                            arraylist.set(list.indexOf(((Optional) (obj2)).get()), obj1);
                        } else
                        {
                            arraylist.remove(((Optional) (obj2)).get());
                        }
                        obj1 = arraylist;
                    }
                    obj2 = TaskExtractor..Lambda._cls0.$instance;
                    if (obj1 instanceof RandomAccess)
                    {
                        obj1 = new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj1)), ((Function) (obj2)));
                    } else
                    {
                        obj1 = new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj1)), ((Function) (obj2)));
                    }
                    obj2 = TaskSorter..Lambda._cls0.$instance;
                    if (obj1 instanceof RandomAccess)
                    {
                        return new com.google.common.collect.Lists.TransformingRandomAccessList(((List) (obj1)), ((Function) (obj2)));
                    } else
                    {
                        return new com.google.common.collect.Lists.TransformingSequentialList(((List) (obj1)), ((Function) (obj2)));
                    }
                    obj2 = Absent.INSTANCE;
                      goto _L1
                }

            .Lambda._cls3(int i)
            {
                arg$1 = i;
            }
            }

            class .Lambda._cls4
                implements Function
            {

                public static final Function $instance = new .Lambda._cls4();

                public final Object apply(Object obj1)
                {
                    return ImmutableList.copyOf((Collection)(List)obj1);
                }


            private .Lambda._cls4()
            {
            }
            }

            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            obj = (FluentFuture)AbstractTransformFuture.create(fluentfuture, new .Lambda._cls3(TimeBoxUtil.msToJulianDay(((TimeZone) (obj)), l)), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.BACKGROUND));
        }
        return (FluentFuture)AbstractTransformFuture.create(((com.google.common.util.concurrent.ListenableFuture) (obj)), .Lambda._cls4..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }
}
