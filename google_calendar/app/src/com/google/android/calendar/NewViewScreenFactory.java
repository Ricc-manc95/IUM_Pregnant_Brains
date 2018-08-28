// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.os.Bundle;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.ical.ICalTimelineEvent;
import com.google.android.calendar.newapi.screen.GrooveViewScreenController;
import com.google.android.calendar.newapi.screen.birthday.BirthdayViewScreenController;
import com.google.android.calendar.newapi.screen.ics.IcsViewScreenController;
import com.google.android.calendar.newapi.screen.reminder.ReminderViewScreenController;
import com.google.android.calendar.timebox.adapter.TimeBoxToTimelineAdapter;
import com.google.android.calendar.timely.TimelineBirthday;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineExternalEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;

public final class NewViewScreenFactory
{

    public static FluentFuture createViewScreen(Context context, TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
    {
        if (timelineitem instanceof TimelineExternalEvent)
        {
            EventKey eventkey = (EventKey)((TimelineEvent) ((TimelineExternalEvent)timelineitem)).eventKey;
            timelineitem = new TimeBoxToTimelineAdapter(context);
            class .Lambda._cls0
                implements Supplier
            {

                private final Context arg$1;

                public final Object get()
                {
                    return Utils.getTimeZone(arg$1);
                }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
            }

            context = (new EventsApiImpl(context, new .Lambda._cls0(context))).getAsync(eventkey);
            timelineitem.getClass();
            class .Lambda._cls1
                implements Function
            {

                private final TimeBoxToTimelineAdapter arg$1;

                public final Object apply(Object obj)
                {
                    TimeBoxToTimelineAdapter timeboxtotimelineadapter = arg$1;
                    obj = (TimeRangeEntry)obj;
                    return timeboxtotimelineadapter.createEvent((Item)((TimeRangeEntry) (obj)).getValue(), ((TimeRangeEntry) (obj)).getRange());
                }

            .Lambda._cls1(TimeBoxToTimelineAdapter timeboxtotimelineadapter)
            {
                arg$1 = timeboxtotimelineadapter;
            }
            }

            class .Lambda._cls2
                implements AsyncFunction
            {

                private final EventInfoAnimationData arg$1;
                private final Bundle arg$2;

                public final ListenableFuture apply(Object obj)
                {
                    EventInfoAnimationData eventinfoanimationdata1 = arg$1;
                    Bundle bundle1 = arg$2;
                    return NewViewScreenFactory.onTimelineItem((TimelineEvent)obj, eventinfoanimationdata1, bundle1);
                }

            .Lambda._cls2(EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
            {
                arg$1 = eventinfoanimationdata;
                arg$2 = bundle;
            }
            }

            return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create(context, new .Lambda._cls1(timelineitem), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls2(eventinfoanimationdata, bundle), CalendarExecutor.MAIN);
        } else
        {
            return onTimelineItem(timelineitem, eventinfoanimationdata, bundle);
        }
    }

    static FluentFuture onTimelineItem(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
    {
        if (timelineitem instanceof TimelineGroove)
        {
            timelineitem = (TimelineGroove)timelineitem;
            timelineitem = GrooveViewScreenController.prepare(new GrooveViewScreenController(), timelineitem, eventinfoanimationdata, bundle);
            if (timelineitem == null)
            {
                timelineitem = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                timelineitem = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
            }
            if (timelineitem instanceof FluentFuture)
            {
                return (FluentFuture)timelineitem;
            } else
            {
                return new ForwardingFluentFuture(timelineitem);
            }
        }
        if (timelineitem instanceof TimelineTask)
        {
            timelineitem = (TimelineTask)timelineitem;
            timelineitem = ReminderViewScreenController.prepare(new ReminderViewScreenController(), timelineitem, eventinfoanimationdata, bundle);
            if (timelineitem == null)
            {
                timelineitem = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                timelineitem = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
            }
            if (timelineitem instanceof FluentFuture)
            {
                return (FluentFuture)timelineitem;
            } else
            {
                return new ForwardingFluentFuture(timelineitem);
            }
        }
        if (timelineitem instanceof TimelineBirthday)
        {
            timelineitem = (TimelineBirthday)timelineitem;
            timelineitem = BirthdayViewScreenController.prepare(new BirthdayViewScreenController(), timelineitem, eventinfoanimationdata, bundle);
            if (timelineitem == null)
            {
                timelineitem = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                timelineitem = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
            }
            if (timelineitem instanceof FluentFuture)
            {
                return (FluentFuture)timelineitem;
            } else
            {
                return new ForwardingFluentFuture(timelineitem);
            }
        }
        if (timelineitem instanceof ICalTimelineEvent)
        {
            timelineitem = IcsViewScreenController.forEvent((ICalTimelineEvent)timelineitem, eventinfoanimationdata);
            if (timelineitem == null)
            {
                timelineitem = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                timelineitem = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(timelineitem);
            }
            if (timelineitem instanceof FluentFuture)
            {
                return (FluentFuture)timelineitem;
            } else
            {
                return new ForwardingFluentFuture(timelineitem);
            }
        }
        if (timelineitem instanceof TimelineEvent)
        {
            boolean flag;
            if (!(timelineitem instanceof TimelineExternalEvent))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException();
            }
            TimelineEvent timelineevent = (TimelineEvent)timelineitem;
            timelineitem = CalendarApi.Events.readDescriptor((EventKey)timelineevent.eventKey);
            class .Lambda._cls3
                implements Function
            {

                private final TimelineEvent arg$1;
                private final EventInfoAnimationData arg$2;
                private final Bundle arg$3;

                public final Object apply(Object obj)
                {
                    Object obj1 = arg$1;
                    EventInfoAnimationData eventinfoanimationdata1 = arg$2;
                    Bundle bundle1 = arg$3;
                    obj = (EventDescriptor)obj;
                    if (((TimelineEvent) (obj1)).hasSmartMail)
                    {
                        obj1 = SmartMailViewScreenController.prepare(new SmartMailViewScreenController(), ((TimelineItem) (obj1)), eventinfoanimationdata1, bundle1);
                        ((Fragment) (obj1)).getArguments().putParcelable("EventDescriptorKey", ((android.os.Parcelable) (obj)));
                        return obj1;
                    } else
                    {
                        obj1 = EventViewScreenController.prepare(new EventViewScreenController(), ((TimelineItem) (obj1)), eventinfoanimationdata1, bundle1);
                        ((Fragment) (obj1)).getArguments().putParcelable("EventDescriptorKey", ((android.os.Parcelable) (obj)));
                        return obj1;
                    }
                }

            .Lambda._cls3(TimelineEvent timelineevent, EventInfoAnimationData eventinfoanimationdata, Bundle bundle)
            {
                arg$1 = timelineevent;
                arg$2 = eventinfoanimationdata;
                arg$3 = bundle;
            }
            }

            if (timelineitem instanceof FluentFuture)
            {
                timelineitem = (FluentFuture)timelineitem;
            } else
            {
                timelineitem = new ForwardingFluentFuture(timelineitem);
            }
            return (FluentFuture)AbstractTransformFuture.create(timelineitem, new .Lambda._cls3(timelineevent, eventinfoanimationdata, bundle), CalendarExecutor.MAIN);
        } else
        {
            timelineitem = String.valueOf(timelineitem.getClass());
            throw new RuntimeException((new StringBuilder(String.valueOf(timelineitem).length() + 20)).append("Unhandled item type ").append(timelineitem).toString());
        }
    }
}
