// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime.grid;

import android.text.TextUtils;
import android.view.LayoutInflater;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.proposenewtime.grid.views.ProposeNewTimeGridDayView;
import com.google.android.apps.calendar.proposenewtime.grid.views.ProposeNewTimeGridViewFrame;
import com.google.android.apps.calendar.proposenewtime.state.Attendee;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.StateHolder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.TimelineAttendeeEvent;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.gridviews.GridDayView;
import com.google.android.calendar.timely.gridviews.GridViewFrame;
import com.google.android.calendar.timely.gridviews.allday.AttendeeAllDayHeaderView;
import com.google.android.calendar.timely.gridviews.allday.ExpandableChipColumnView;
import com.google.android.calendar.timely.gridviews.attendees.AttendeeInfoLayout;
import com.google.android.calendar.utils.recycler.Recycler;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Present;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProposeNewTimeGridManager
{
    final class DisplayAttendeeEvents
        implements FutureCallback
    {

        private final int julianDay;
        private final ProposeNewTimeGridManager this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.d(ProposeNewTimeGridManager.TAG, throwable, "Failed to load attendee events", new Object[0]);
            List list = stateHolder.getState().getAttendees();
            throwable = new com.google.common.collect.Lists.OnePlusArrayList((Attendee)list.get(0), new Attendee[0]);
            class .Lambda._cls0
                implements Function
            {

                public static final Function $instance = new .Lambda._cls0();

                public final Object apply(Object obj1)
                {
                    return ((Attendee)obj1).toBuilder().setDisabled(true).build();
                }


                private .Lambda._cls0()
                {
                }
            }

            Object obj;
            Function function;
            if (throwable instanceof FluentIterable)
            {
                throwable = (FluentIterable)throwable;
            } else
            {
                throwable = new com.google.common.collect.FluentIterable._cls1(throwable, throwable);
            }
            obj = list.subList(1, list.size());
            if (obj instanceof FluentIterable)
            {
                obj = (FluentIterable)obj;
            } else
            {
                obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
            }
            function = .Lambda._cls0..instance;
            obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
            if (obj == null)
            {
                throw new NullPointerException();
            }
            if (function == null)
            {
                throw new NullPointerException();
            }
            obj = new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), function);
            if (obj instanceof FluentIterable)
            {
                obj = (FluentIterable)obj;
            } else
            {
                obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
            }
            throwable = FluentIterable.concatNoDefensiveCopy(new Iterable[] {
                throwable, obj
            });
            throwable = ImmutableList.copyOf((Iterable)((FluentIterable) (throwable)).iterableDelegate.or(throwable));
            attendeeInfo.onUpdate(throwable, 0, false);
            throwable = attendeeFrame;
            obj = attendeeFrame;
            throwable.removeView((GridDayView)((GridViewFrame) (obj)).getChildAt(((GridViewFrame) (obj)).getChildrenBeforeGridDayViews() + 1));
            for (int i = 0; i < list.size() - 1; i++)
            {
                throwable = ProposeNewTimeGridManager.this;
                obj = (ProposeNewTimeGridDayView)((ProposeNewTimeGridManager) (throwable)).layoutInflater.inflate(0x7f050130, null);
                ((GridDayView) (obj)).initialize(((ProposeNewTimeGridManager) (throwable)).chipRecycler, null, 1);
                obj.isDisabled = true;
                attendeeFrame.addView(((android.view.View) (obj)));
            }

            throwable = allDayGrid;
            obj = attendeeFrame;
            int j = ((GridViewFrame) (obj)).getChildCount() - ((GridViewFrame) (obj)).getChildrenBeforeGridDayViews();
            if (j > ((ExpandableChipColumnView) (throwable)).columnCount)
            {
                throwable.setColumnCount(j);
            }
        }

        public final void onSuccess(Object obj)
        {
            obj = (Map)obj;
            if (obj == null || julianDay != displayedJulianDay) goto _L2; else goto _L1
_L1:
            ArrayList arraylist;
            List list;
            Object obj1;
            Object obj2;
            Iterator iterator;
            list = stateHolder.getState().getAttendees();
            arraylist = new ArrayList();
            arraylist.add((Attendee)stateHolder.getState().getAttendees().get(0));
            obj1 = attendeeFrame;
            obj2 = attendeeFrame;
            ((ProposeNewTimeGridViewFrame) (obj1)).removeView((GridDayView)((GridViewFrame) (obj2)).getChildAt(((GridViewFrame) (obj2)).getChildrenBeforeGridDayViews() + 1));
            obj1 = new ArrayList();
            obj2 = new ArrayList();
            iterator = ((Map) (obj)).entrySet().iterator();
_L5:
            java.util.Map.Entry entry;
            String s;
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_574;
            }
            entry = (java.util.Map.Entry)iterator.next();
            s = (String)entry.getKey();
            class .Lambda._cls1
                implements Predicate
            {

                private final String arg$1;

                public final boolean apply(Object obj4)
                {
                    String s2 = arg$1;
                    obj4 = ((Attendee)obj4).getEmail();
                    return obj4 != null && ((String) (obj4)).equalsIgnoreCase(s2);
                }

                .Lambda._cls1(String s)
                {
                    arg$1 = s;
                }
            }

            .Lambda._cls1 _lcls1;
            if (list instanceof FluentIterable)
            {
                obj = (FluentIterable)list;
            } else
            {
                obj = new com.google.common.collect.FluentIterable._cls1(list, list);
            }
            _lcls1 = new .Lambda._cls1(s);
            obj = ((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj)).iterator();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            if (_lcls1 == null)
            {
                throw new NullPointerException();
            }
            Object obj3;
            do
            {
                if (!((Iterator) (obj)).hasNext())
                {
                    break MISSING_BLOCK_LABEL_501;
                }
                obj3 = ((Iterator) (obj)).next();
            } while (!_lcls1.apply(obj3));
            if (obj3 == null)
            {
                throw new NullPointerException();
            }
            obj = new Present(obj3);
_L3:
            class .Lambda._cls2
                implements Supplier
            {

                private final String arg$1;

                public final Object get()
                {
                    String s2 = arg$1;
                    return (new com.google.android.apps.calendar.proposenewtime.state..AutoValue_Attendee.Builder()).setDisabled(false).setEmail(s2).setSourceAccount("").setDisplayName("").build();
                }

                .Lambda._cls2(String s)
                {
                    arg$1 = s;
                }
            }

            obj = (Attendee)((Optional) (obj)).or(new .Lambda._cls2(s));
            s = ((Attendee) (obj)).getEmail();
            String s1 = ((Attendee)stateHolder.getState().getAttendees().get(0)).getEmail();
            boolean flag;
            if (s != null && s.equalsIgnoreCase(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                ProposeNewTimeGridManager proposenewtimegridmanager = ProposeNewTimeGridManager.this;
                ProposeNewTimeGridDayView proposenewtimegriddayview = (ProposeNewTimeGridDayView)proposenewtimegridmanager.layoutInflater.inflate(0x7f050130, null);
                proposenewtimegriddayview.initialize(proposenewtimegridmanager.chipRecycler, null, 1);
                if (!((Optional)entry.getValue()).isPresent())
                {
                    obj = ((Attendee) (obj)).toBuilder().setDisabled(true).build();
                    proposenewtimegriddayview.isDisabled = true;
                } else
                {
                    proposenewtimegriddayview.setChips(julianDay, (List)((Optional)entry.getValue()).get());
                    ((List) (obj1)).add((List)((Optional)entry.getValue()).get());
                }
                arraylist.add(obj);
                if (TextUtils.isEmpty(((Attendee) (obj)).getDisplayName()))
                {
                    obj = ((Attendee) (obj)).getEmail();
                } else
                {
                    obj = ((Attendee) (obj)).getDisplayName();
                }
                ((List) (obj2)).add(obj);
                attendeeFrame.addView(proposenewtimegriddayview);
            }
            continue; /* Loop/switch isn't completed */
            obj = Absent.INSTANCE;
              goto _L3
            obj = allDayGrid;
            ProposeNewTimeGridViewFrame proposenewtimegridviewframe = attendeeFrame;
            int i = proposenewtimegridviewframe.getChildCount() - proposenewtimegridviewframe.getChildrenBeforeGridDayViews();
            if (i > ((ExpandableChipColumnView) (obj)).columnCount)
            {
                ((ExpandableChipColumnView) (obj)).setColumnCount(i);
            }
            allDayGrid.onUpdate$5166KOBMC4NNAT39DGNKOQBJEGTKOQJ1EPGIUTBKD5M2UJ39EDQ3MIA994KLC___0(((List) (obj1)), ((List) (obj2)), julianDay, 1);
            attendeeInfo.onUpdate(arraylist, 0, false);
_L2:
            return;
            if (true) goto _L5; else goto _L4
_L4:
        }

        DisplayAttendeeEvents(int i)
        {
            this$0 = ProposeNewTimeGridManager.this;
            super();
            julianDay = i;
        }
    }

    final class DisplayUserEvents
        implements FutureCallback
    {

        private final int julianDay;
        public final ProposeNewTimeGridManager this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.wtf(ProposeNewTimeGridManager.TAG, throwable, "Error loading user events", new Object[0]);
        }

        public final void onSuccess(Object obj)
        {
            obj = (List)obj;
            if (obj != null && julianDay == displayedJulianDay)
            {
                class .Lambda._cls0
                    implements Predicate
                {

                    private final DisplayUserEvents arg$1;

                    public final boolean apply(Object obj2)
                    {
                        DisplayUserEvents displayuserevents = arg$1;
                        for (obj2 = (TimelineItem)obj2; !((TimelineItem) (obj2)).getSourceAccountName().equals(displayuserevents._fld0.stateHolder.getState().getAccount().name) || (obj2 instanceof TimelineEvent) && !((TimelineEvent)obj2).ownerAccount.equals(displayuserevents._fld0.stateHolder.getState().getCalendarId());)
                        {
                            return false;
                        }

                        return true;
                    }

                .Lambda._cls0()
                {
                    arg$1 = DisplayUserEvents.this;
                }
                }

                Object obj1;
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj1 = new .Lambda._cls0();
                obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), ((Predicate) (obj1)));
                class .Lambda._cls1
                    implements Predicate
                {

                    public static final Predicate $instance = new .Lambda._cls1();

                    public final boolean apply(Object obj2)
                    {
                        obj2 = (TimelineItem)obj2;
                        return !(obj2 instanceof TimelineBirthday) && !(obj2 instanceof TimelineHoliday);
                    }


                private .Lambda._cls1()
                {
                }
                }

                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj1 = .Lambda._cls1..instance;
                obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), ((Predicate) (obj1)));
                class .Lambda._cls2
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls2();

                    public final Object apply(Object obj2)
                    {
                        return ProposeNewTimeGridManager.convertToTimelineAttendee((TimelineItem)obj2);
                    }


                private .Lambda._cls2()
                {
                }
                }

                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj1 = .Lambda._cls2..instance;
                obj = (Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj);
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((Function) (obj1)));
                if (obj instanceof FluentIterable)
                {
                    obj = (FluentIterable)obj;
                } else
                {
                    obj = new com.google.common.collect.FluentIterable._cls1(((Iterable) (obj)), ((Iterable) (obj)));
                }
                obj = ImmutableList.copyOf((Iterable)((FluentIterable) (obj)).iterableDelegate.or(obj));
                obj1 = attendeeFrame;
                ((ProposeNewTimeGridDayView)(GridDayView)((GridViewFrame) (obj1)).getChildAt(((GridViewFrame) (obj1)).getChildrenBeforeGridDayViews() + 0)).setChips(displayedJulianDay, ((List) (obj)));
                allDayGrid.onUpdate$5166KOBMC4NNAT39DGNKOQBJEGTKOQJ1EPGIUTBKD5M2UJ39EDQ3MIA994KLC___0(ImmutableList.of(obj), ImmutableList.of(((Attendee)stateHolder.getState().getAttendees().get(0)).getDisplayName()), julianDay, 0);
            }
        }

        DisplayUserEvents(int i)
        {
            this$0 = ProposeNewTimeGridManager.this;
            super();
            julianDay = i;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/proposenewtime/grid/ProposeNewTimeGridManager);
    public final AttendeeAllDayHeaderView allDayGrid;
    private final Function attendeeEventsLoader;
    public final ProposeNewTimeGridViewFrame attendeeFrame;
    public final AttendeeInfoLayout attendeeInfo;
    public final Recycler chipRecycler;
    public int displayedJulianDay;
    public final LayoutInflater layoutInflater;
    public final StateHolder stateHolder;
    private final Function userEventsLoader;

    public ProposeNewTimeGridManager(ProposeNewTimeGridViewFrame proposenewtimegridviewframe, AttendeeAllDayHeaderView attendeealldayheaderview, AttendeeInfoLayout attendeeinfolayout, Recycler recycler, LayoutInflater layoutinflater, Function function, Function function1, 
            StateHolder stateholder)
    {
        attendeeFrame = proposenewtimegridviewframe;
        allDayGrid = attendeealldayheaderview;
        attendeeInfo = attendeeinfolayout;
        chipRecycler = recycler;
        layoutInflater = layoutinflater;
        userEventsLoader = function;
        attendeeEventsLoader = function1;
        stateHolder = stateholder;
        resetAttendeeGrids();
    }

    static TimelineAttendeeEvent convertToTimelineAttendee(TimelineItem timelineitem)
    {
        TimelineAttendeeEvent timelineattendeeevent = new TimelineAttendeeEvent();
        timelineattendeeevent.title = timelineitem.getTitle();
        timelineattendeeevent.timeRange = timelineitem.getTimeRange();
        if (timelineitem instanceof TimelineEvent)
        {
            timelineattendeeevent.endTimeUnspecified = ((TimelineEvent)timelineitem).endTimeUnspecified;
        }
        timelineattendeeevent.selfAttendeeStatus = timelineitem.getSelfAttendeeStatus();
        timelineattendeeevent.location = timelineitem.getLocation();
        return timelineattendeeevent;
    }

    private final void resetAttendeeGrids()
    {
        attendeeInfo.onUpdate(stateHolder.getState().getAttendees().subList(0, 1), 0, true);
        attendeeFrame.removeGridDayViews();
        Object obj = attendeeFrame;
        Object obj1 = (ProposeNewTimeGridDayView)layoutInflater.inflate(0x7f050130, null);
        ((GridDayView) (obj1)).initialize(chipRecycler, null, 1);
        ((ProposeNewTimeGridViewFrame) (obj)).addView(((android.view.View) (obj1)));
        obj = attendeeFrame;
        obj1 = (ProposeNewTimeGridDayView)layoutInflater.inflate(0x7f050130, null);
        ((GridDayView) (obj1)).initialize(chipRecycler, null, 1);
        ((ProposeNewTimeGridViewFrame) (obj)).addView(((android.view.View) (obj1)));
        allDayGrid.clear();
        obj = allDayGrid;
        obj1 = attendeeFrame;
        int i = ((GridViewFrame) (obj1)).getChildCount() - ((GridViewFrame) (obj1)).getChildrenBeforeGridDayViews();
        if (i > ((ExpandableChipColumnView) (obj)).columnCount)
        {
            ((ExpandableChipColumnView) (obj)).setColumnCount(i);
        }
    }

    public final void switchToDay(int i)
    {
        if (i == displayedJulianDay)
        {
            return;
        }
        displayedJulianDay = i;
        resetAttendeeGrids();
        ListenableFuture listenablefuture = (ListenableFuture)userEventsLoader.apply(Integer.valueOf(i));
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        }
        listenablefuture = (ListenableFuture)listenablefuture;
        Object obj = new DisplayUserEvents(i);
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, ((FutureCallback) (obj))), calendarexecutor);
        listenablefuture = (ListenableFuture)attendeeEventsLoader.apply(Integer.valueOf(i));
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        }
        listenablefuture = (ListenableFuture)listenablefuture;
        obj = new DisplayAttendeeEvents(i);
        calendarexecutor = CalendarExecutor.MAIN;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, ((FutureCallback) (obj))), calendarexecutor);
            return;
        }
    }

}
