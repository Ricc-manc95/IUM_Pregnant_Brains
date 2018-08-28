// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.calendarlist.CalendarListFilterOptions;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.api.common.Feature;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attendee.Attendee;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.habit.HabitInstance;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.loader.CalendarListLoader;
import com.google.android.calendar.newapi.model.CalendarList;
import com.google.android.calendar.search.SearchResultsAdapter;
import com.google.android.calendar.timely.TimelineEvent;
import com.google.android.calendar.timely.TimelineGroove;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation, ICalTimelineEvent, MultipleEventImporter

public class ICalEventListController
{
    public static interface ImportAllCallback
    {

        public abstract void onFailure();

        public abstract void onSuccess();
    }

    public static interface ImportAllView
    {

        public abstract void setEnabled(boolean flag);

        public abstract void showCalendarChooser(CalendarList calendarlist);
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalEventListController);
    private final Context context;
    private final EventClient eventClient;
    public final ImportAllCallback importAllCallback;
    public final ImportAllView importAllView;
    private final FutureCallback importCompleteCallback;
    private final SearchResultsAdapter listAdapter;
    public final List operations;
    private final com.google.android.calendar.newapi.common.Loader.Callback writableCalendarCallback;
    public final CalendarListLoader writableCalendars;

    public ICalEventListController(Context context1, List list, SearchResultsAdapter searchresultsadapter, EventClient eventclient, ImportAllView importallview, ImportAllCallback importallcallback)
    {
label0:
        {
            super();
            writableCalendarCallback = new _cls1();
            importCompleteCallback = new _cls2();
            if (context1 == null)
            {
                throw new NullPointerException();
            }
            context = (Context)context1;
            if (list == null)
            {
                throw new NullPointerException();
            }
            operations = (List)list;
            if (searchresultsadapter == null)
            {
                throw new NullPointerException();
            }
            listAdapter = (SearchResultsAdapter)searchresultsadapter;
            if (eventclient == null)
            {
                throw new NullPointerException();
            }
            eventClient = (EventClient)eventclient;
            if (importallview == null)
            {
                throw new NullPointerException();
            }
            importAllView = (ImportAllView)importallview;
            if (importallcallback == null)
            {
                throw new NullPointerException();
            }
            importAllCallback = (ImportAllCallback)importallcallback;
            eventclient = new ArrayList();
            importallview = operations.iterator();
            while (importallview.hasNext()) 
            {
                importallcallback = (ICalEventOperation)importallview.next();
                Context context2 = context;
                context1 = importallcallback.events();
                if (context1 == null)
                {
                    throw new NullPointerException();
                }
                Event event;
                if (context1 instanceof List)
                {
                    context1 = ((Context) (((List)context1).get(0)));
                } else
                {
                    context1 = context1.iterator();
                    if (context1 == null)
                    {
                        throw new NullPointerException();
                    }
                    if (!context1.hasNext())
                    {
                        throw new IndexOutOfBoundsException((new StringBuilder(91)).append("position (").append(0).append(") must be less than the number of elements that remained (").append(0).append(")").toString());
                    }
                    context1 = ((Context) (context1.next()));
                }
                event = (Event)context1;
                if (event.getHabitInstance().isSupported() && event.getHabitInstance().getValue() != null)
                {
                    list = (HabitInstance)event.getHabitInstance().getValue();
                    context1 = new TimelineGroove(list.getHabitParentDescriptor());
                    searchresultsadapter = (TimelineGroove)context1;
                    searchresultsadapter.type = Integer.valueOf(list.getParentType());
                    boolean flag1;
                    if (list.getStatus() == 3)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    searchresultsadapter.completed = flag1;
                    searchresultsadapter.calendarAccessLevel = CalendarAccessLevel.OWNER;
                } else
                {
                    context1 = new TimelineEvent();
                }
                if (event.getDescriptor().isCommitted())
                {
                    context1.eventKey = event.getDescriptor().getKey();
                }
                context1.calendarId = event.getCalendar().calendarKey;
                if (TextUtils.isEmpty(event.getSummary()))
                {
                    list = context2.getString(0x7f130358);
                } else
                {
                    list = event.getSummary();
                }
                context1.title = list;
                context1.organizer = event.getOrganizer().email;
                searchresultsadapter = event.getTimeZoneId();
                list = searchresultsadapter;
                if (searchresultsadapter == null)
                {
                    list = Utils.getTimeZoneId(context2);
                }
                context1.timeRange = TimeRange.newInstance(TimeZone.getTimeZone(list), event.isAllDayEvent(), event.getStartMillis(), event.getEndMillis());
                context1.color = event.getColor().getValue();
                if (!event.getLocation().getEventLocations().isEmpty())
                {
                    context1.location = ((EventLocation)event.getLocation().getEventLocations().iterator().next()).name;
                }
                list = AttendeeUtils.getCurrentAttendee(event);
                if (list != null)
                {
                    context1.selfAttendeeStatus = ((Attendee) (list)).response.status;
                }
                context1.hasSmartMail = event.isSmartMailEvent();
                eventclient.add(new ICalTimelineEvent(context1, importallcallback));
            }
            listAdapter.swapItemList(eventclient);
            ((android.support.v7.widget.RecyclerView.Adapter) (listAdapter)).mObservable.notifyChanged();
            context1 = context;
            list = new CalendarListFilterOptions();
            list.writable = Boolean.valueOf(true);
            writableCalendars = new CalendarListLoader(context1, list, null, false);
            writableCalendars.callback = writableCalendarCallback;
            context1 = writableCalendars;
            if (!((AsyncTaskLoader) (context1)).finished.booleanValue())
            {
                boolean flag;
                if (context1.getStatus() == android.os.AsyncTask.Status.RUNNING)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label0;
                }
            }
            return;
        }
        context1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    static final boolean lambda$getNewEvents$0$ICalEventListController(ICalEventOperation icaleventoperation)
    {
        return icaleventoperation.getImportType() == 0;
    }

    static final void lambda$showConfirmationDialog$1$ICalEventListController$5166KOBMC4NMOOBECSNL4TBEDPGM4R357D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(Runnable runnable)
    {
        runnable.run();
    }

    public final void onCalendarChosen(CalendarListEntry calendarlistentry)
    {
        if (calendarlistentry != null)
        {
            Object obj = operations;
            class .Lambda._cls1
                implements Predicate
            {

                public static final Predicate $instance = new .Lambda._cls1();

                public final boolean apply(Object obj2)
                {
                    return ICalEventListController.lambda$getNewEvents$0$ICalEventListController((ICalEventOperation)obj2);
                }


            private .Lambda._cls1()
            {
            }
            }

            Predicate predicate = .Lambda._cls1..instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            if (predicate == null)
            {
                throw new NullPointerException();
            }
            for (obj = (new com.google.common.collect.Iterables._cls4(((Iterable) (obj)), predicate)).iterator(); ((Iterator) (obj)).hasNext();)
            {
                ImmutableList immutablelist = (ImmutableList)((ICalEventOperation)((Iterator) (obj)).next()).events();
                int k = immutablelist.size();
                UnmodifiableIterator unmodifiableiterator = (UnmodifiableIterator)null;
                int i = 0;
                while (i < k) 
                {
                    Object obj1 = immutablelist.get(i);
                    i++;
                    ((EventModifications)obj1).switchCalendar(calendarlistentry);
                }
            }

        }
        if (operations.size() > 6)
        {
            int j = operations.size();
            class .Lambda._cls0
                implements Runnable
            {

                private final ICalEventListController arg$1;

                public final void run()
                {
                    arg$1.performImportAll();
                }

            .Lambda._cls0()
            {
                arg$1 = ICalEventListController.this;
            }
            }

            calendarlistentry = new .Lambda._cls0();
            String s = context.getResources().getQuantityString(0x7f120024, j, new Object[] {
                Integer.valueOf(j)
            });
            class .Lambda._cls2
                implements android.content.DialogInterface.OnClickListener
            {

                private final Runnable arg$1;

                public final void onClick(DialogInterface dialoginterface, int l)
                {
                    ICalEventListController.lambda$showConfirmationDialog$1$ICalEventListController$5166KOBMC4NMOOBECSNL4TBEDPGM4R357D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(arg$1);
                }

            .Lambda._cls2(Runnable runnable)
            {
                arg$1 = runnable;
            }
            }

            (new android.app.AlertDialog.Builder(context)).setMessage(s).setPositiveButton(0x7f1302fb, new .Lambda._cls2(calendarlistentry)).setNegativeButton(0x1040000, null).show();
            return;
        } else
        {
            performImportAll();
            return;
        }
    }

    final void performImportAll()
    {
        if (importAllView != null)
        {
            importAllView.setEnabled(false);
        }
        Object obj1 = new MultipleEventImporter(eventClient);
        Object obj = operations;
        obj1 = new MultipleEventImporter..Lambda._cls0(((MultipleEventImporter) (obj1)));
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((com.google.common.base.Function) (obj1)))), true);
        obj1 = importCompleteCallback;
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), calendarexecutor);
            return;
        }
    }


    private class _cls1
        implements com.google.android.calendar.newapi.common.Loader.Callback
    {

        private final ICalEventListController this$0;

        public final void onLoadingFailure(Loader loader, String s)
        {
            LogUtils.e(ICalEventListController.TAG, "Failed to load writable calendar list: %s", new Object[] {
                s
            });
        }

        public final void onLoadingSuccess(Loader loader)
        {
            if (((CalendarList)loader.getResult()).calendars.isEmpty())
            {
                importAllView.setEnabled(false);
            }
        }

        _cls1()
        {
            this$0 = ICalEventListController.this;
            super();
        }
    }


    private class _cls2
        implements FutureCallback
    {

        private final ICalEventListController this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.w(ICalEventListController.TAG, throwable, "Error importing calendar", new Object[0]);
            importAllCallback.onFailure();
            importAllView.setEnabled(true);
        }

        public final void onSuccess(Object obj)
        {
            importAllCallback.onSuccess();
            importAllView.setEnabled(true);
        }

        _cls2()
        {
            this$0 = ICalEventListController.this;
            super();
        }
    }

}
