// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.alternate;

import android.content.Context;
import com.google.android.apps.calendar.timebox.EventItem;
import com.google.android.apps.calendar.timebox.GoalItem;
import com.google.android.apps.calendar.timebox.Item;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import com.google.android.apps.calendar.timebox.task.TaskItem;
import com.google.android.apps.calendar.timebox.task.TaskSet;
import com.google.android.apps.calendar.timeline.alternate.fragment.api.SwipeHandler;
import com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore;
import com.google.android.calendar.DeleteEventInteractiveHelper;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.timely.interaction.SwipeTaskUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public final class SwipeHandlerImpl
    implements SwipeHandler
{

    public final Context context;
    public final CalendarContentStore store;

    public SwipeHandlerImpl(Context context1, CalendarContentStore calendarcontentstore)
    {
        context = context1;
        store = calendarcontentstore;
    }

    final void logUserAction(String s)
    {
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(context, "event", s);
            return;
        }
    }

    public final ListenableFuture onSwipe(TimeRangeEntry timerangeentry)
    {
        Object obj = (Item)timerangeentry.getValue();
        if (obj instanceof GoalItem)
        {
            obj = (GoalItem)timerangeentry.getValue();
            class .Lambda._cls5
                implements Consumer
            {

                private final SwipeHandlerImpl arg$1;
                private final GoalItem arg$2;
                private final TimeRangeEntry arg$3;

                public final void accept(Object obj4)
                {
                    Object obj5 = arg$1;
                    GoalItem goalitem = arg$2;
                    TimeRangeEntry timerangeentry1 = arg$3;
                    obj4 = (com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction)obj4;
                    ((SwipeHandlerImpl) (obj5)).context.startService(HabitsIntentServiceHelper.createCompleteIntent(((SwipeHandlerImpl) (obj5)).context, goalitem.getHabitDescriptor(), goalitem.getEventDescriptor().getKey(), true, "swipe_from_timeline"));
                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj4)).removeItem(timerangeentry1);
                    obj5 = goalitem.setGoalDone(true);
                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj4)).addItem(new AutoValue_TimeRangeEntry(timerangeentry1.getKey(), obj5, timerangeentry1.getRange()));
                }

            .Lambda._cls5(GoalItem goalitem, TimeRangeEntry timerangeentry)
            {
                arg$1 = SwipeHandlerImpl.this;
                arg$2 = goalitem;
                arg$3 = timerangeentry;
            }
            }

            return store.updateStore(new .Lambda._cls5(((GoalItem) (obj)), timerangeentry));
        }
        if (obj instanceof EventItem)
        {
            obj = (EventItem)timerangeentry.getValue();
            class .Lambda._cls0
                implements AsyncFunction
            {

                private final SwipeHandlerImpl arg$1;
                private final TimeRangeEntry arg$2;

                public final ListenableFuture apply(Object obj4)
                {
                    SwipeHandlerImpl swipehandlerimpl = arg$1;
                    TimeRangeEntry timerangeentry1 = arg$2;
                    obj4 = (com.google.android.calendar.DeleteEventInteractiveHelper.DeleteOptions)obj4;
                    class .Lambda._cls7
                        implements Consumer
                    {

                        private final SwipeHandlerImpl arg$1;
                        private final TimeRangeEntry arg$2;
                        private final com.google.android.calendar.DeleteEventInteractiveHelper.DeleteOptions arg$3;

                        public final void accept(Object obj5)
                        {
                            SwipeHandlerImpl swipehandlerimpl1 = arg$1;
                            TimeRangeEntry timerangeentry2 = arg$2;
                            com.google.android.calendar.DeleteEventInteractiveHelper.DeleteOptions deleteoptions = arg$3;
                            obj5 = (com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction)obj5;
                            ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).removeItem(timerangeentry2);
                            ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).blockUpdates(DeleteEventInteractiveHelper.eventClient.delete(deleteoptions.descriptor(), deleteoptions.scope(), deleteoptions.guestNotification()));
                            swipehandlerimpl1.logUserAction("delete_swipe");
                        }

                            .Lambda._cls7(TimeRangeEntry timerangeentry, com.google.android.calendar.DeleteEventInteractiveHelper.DeleteOptions deleteoptions)
                            {
                                arg$1 = SwipeHandlerImpl.this;
                                arg$2 = timerangeentry;
                                arg$3 = deleteoptions;
                            }
                    }

                    return swipehandlerimpl.store.updateStore(swipehandlerimpl. new .Lambda._cls7(timerangeentry1, ((com.google.android.calendar.DeleteEventInteractiveHelper.DeleteOptions) (obj4))));
                }

            .Lambda._cls0(TimeRangeEntry timerangeentry)
            {
                arg$1 = SwipeHandlerImpl.this;
                arg$2 = timerangeentry;
            }
            }

            return AbstractTransformFuture.create(DeleteEventInteractiveHelper.showConfirmationDialog(context, ((EventItem) (obj)).getEventDescriptor().getKey()), new .Lambda._cls0(timerangeentry), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        if (obj instanceof TaskItem)
        {
            obj = (TaskItem)timerangeentry.getValue();
            class .Lambda._cls1
                implements Consumer
            {

                private final SwipeHandlerImpl arg$1;
                private final TimeRangeEntry arg$2;
                private final TaskItem arg$3;

                public final void accept(Object obj4)
                {
                    SwipeHandlerImpl swipehandlerimpl = arg$1;
                    TimeRangeEntry timerangeentry1 = arg$2;
                    TaskItem taskitem = arg$3;
                    obj4 = (com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction)obj4;
                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj4)).removeItem(timerangeentry1);
                    TaskItem taskitem1 = taskitem.setDone(true);
                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj4)).addItem(new AutoValue_TimeRangeEntry(timerangeentry1.getKey(), taskitem1, timerangeentry1.getRange()));
                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj4)).blockUpdates(SwipeTaskUtils.updateTaskDoneAsync(swipehandlerimpl.context, taskitem.getTaskData().getAccountName(), Collections.singleton(taskitem.getTaskData().getId())));
                    swipehandlerimpl.logUserAction("done_swipe");
                }

            .Lambda._cls1(TimeRangeEntry timerangeentry, TaskItem taskitem)
            {
                arg$1 = SwipeHandlerImpl.this;
                arg$2 = timerangeentry;
                arg$3 = taskitem;
            }
            }

            return store.updateStore(new .Lambda._cls1(timerangeentry, ((TaskItem) (obj))));
        }
        if (obj instanceof TaskSet)
        {
            TaskSet taskset = (TaskSet)timerangeentry.getValue();
            Object obj1 = taskset.getItems();
            class .Lambda._cls2
                implements Function
            {

                public static final Function $instance = new .Lambda._cls2();

                public final Object apply(Object obj4)
                {
                    return ((TaskItem)obj4).getTaskData().getId();
                }


            private .Lambda._cls2()
            {
            }
            }

            Object obj2 = .Lambda._cls2..instance;
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            obj1 = new com.google.common.collect.Iterables._cls5(((Iterable) (obj1)), ((Function) (obj2)));
            class .Lambda._cls3
                implements Function
            {

                public static final Function $instance = new .Lambda._cls3();

                public final Object apply(Object obj4)
                {
                    return ((TaskItem)obj4).getTaskData().getAccountName();
                }


            private .Lambda._cls3()
            {
            }
            }

            Object obj3;
            if (obj1 instanceof Collection)
            {
                obj1 = new HashSet((Collection)obj1);
            } else
            {
                obj2 = ((Iterable) (obj1)).iterator();
                obj1 = new HashSet();
                Iterators.addAll(((Collection) (obj1)), ((Iterator) (obj2)));
            }
            obj2 = taskset.getItems();
            obj3 = .Lambda._cls3..instance;
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            if (obj3 == null)
            {
                throw new NullPointerException();
            }
            obj2 = new com.google.common.collect.Iterables._cls5(((Iterable) (obj2)), ((Function) (obj3)));
            if (obj2 instanceof Collection)
            {
                obj2 = new HashSet((Collection)obj2);
            } else
            {
                obj3 = ((Iterable) (obj2)).iterator();
                obj2 = new HashSet();
                Iterators.addAll(((Collection) (obj2)), ((Iterator) (obj3)));
            }
            obj2 = ((Iterable) (obj2)).iterator();
            obj3 = ((Iterator) (obj2)).next();
            if (!((Iterator) (obj2)).hasNext())
            {
                obj2 = (String)obj3;
                class .Lambda._cls4
                    implements AsyncFunction
                {

                    private final SwipeHandlerImpl arg$1;
                    private final TimeRangeEntry arg$2;
                    private final TaskSet arg$3;
                    private final String arg$4;
                    private final Set arg$5;

                    public final ListenableFuture apply(Object obj4)
                    {
                        obj4 = arg$1;
                        TimeRangeEntry timerangeentry1 = arg$2;
                        TaskSet taskset1 = arg$3;
                        String s = arg$4;
                        Set set = arg$5;
                        class .Lambda._cls6
                            implements Consumer
                        {

                            private final SwipeHandlerImpl arg$1;
                            private final TimeRangeEntry arg$2;
                            private final TaskSet arg$3;
                            private final String arg$4;
                            private final Set arg$5;

                            public final void accept(Object obj5)
                            {
                                SwipeHandlerImpl swipehandlerimpl = arg$1;
                                TimeRangeEntry timerangeentry2 = arg$2;
                                Object obj7 = arg$3;
                                String s1 = arg$4;
                                Set set1 = arg$5;
                                obj5 = (com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction)obj5;
                                ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).removeItem(timerangeentry2);
                                Object obj6 = ((TaskSet) (obj7)).toBuilder();
                                obj7 = ((TaskSet) (obj7)).getItems();
                                com.google.android.apps.calendar.timebox.task.TaskSet..Lambda._cls0 _lcls0 = new com.google.android.apps.calendar.timebox.task.TaskSet..Lambda._cls0(true);
                                if (obj7 == null)
                                {
                                    throw new NullPointerException();
                                }
                                if (_lcls0 == null)
                                {
                                    throw new NullPointerException();
                                } else
                                {
                                    obj6 = ((com.google.android.apps.calendar.timebox.task.TaskSet.Builder) (obj6)).setItems(ImmutableList.copyOf(new com.google.common.collect.Iterables._cls5(((Iterable) (obj7)), _lcls0))).setDone(true).build();
                                    obj6 = ((TaskSet) (obj6)).toBuilder().setItems(ImmutableList.sortedCopyOf(TaskComparators.get(((TaskSet) (obj6)).isDone()), ((TaskSet) (obj6)).getItems())).build();
                                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).addItem(new AutoValue_TimeRangeEntry(timerangeentry2.getKey(), obj6, timerangeentry2.getRange()));
                                    ((com.google.android.apps.calendar.timeline.alternate.store.CalendarContentStore.StoreTransaction) (obj5)).blockUpdates(SwipeTaskUtils.updateTaskDoneAsync(swipehandlerimpl.context, s1, set1));
                                    swipehandlerimpl.logUserAction("done_all_swipe");
                                    return;
                                }
                            }

                                .Lambda._cls6(TimeRangeEntry timerangeentry, TaskSet taskset, String s, Set set)
                                {
                                    arg$1 = SwipeHandlerImpl.this;
                                    arg$2 = timerangeentry;
                                    arg$3 = taskset;
                                    arg$4 = s;
                                    arg$5 = set;
                                }
                        }

                        return ((SwipeHandlerImpl) (obj4)).store.updateStore(((.Lambda._cls6) (obj4)). new .Lambda._cls6(timerangeentry1, taskset1, s, set));
                    }

            .Lambda._cls4(TimeRangeEntry timerangeentry, TaskSet taskset, String s, Set set)
            {
                arg$1 = SwipeHandlerImpl.this;
                arg$2 = timerangeentry;
                arg$3 = taskset;
                arg$4 = s;
                arg$5 = set;
            }
                }

                return AbstractTransformFuture.create(SwipeTaskUtils.showMarkTaskBundleDoneDialog(context, taskset.getItems().size()), new .Lambda._cls4(timerangeentry, taskset, ((String) (obj2)), ((Set) (obj1))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }
            timerangeentry = (new StringBuilder("expected one element but was: <")).append(obj3);
            for (int i = 0; i < 4 && ((Iterator) (obj2)).hasNext(); i++)
            {
                timerangeentry.append(", ").append(((Iterator) (obj2)).next());
            }

            if (((Iterator) (obj2)).hasNext())
            {
                timerangeentry.append(", ...");
            }
            timerangeentry.append('>');
            throw new IllegalArgumentException(timerangeentry.toString());
        }
        timerangeentry = new RuntimeException("Unhandled");
        if (timerangeentry == null)
        {
            throw new NullPointerException();
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateFailedFuture(timerangeentry);
        }
    }
}
