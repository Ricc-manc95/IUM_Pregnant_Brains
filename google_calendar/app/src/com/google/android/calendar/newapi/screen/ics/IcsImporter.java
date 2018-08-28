// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListApiStoreImpl;
import com.google.android.calendar.api.calendarlist.CalendarListClientFutureImpl;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.utils.collection.Iterables2;
import com.google.android.calendar.v2a.UnifiedSyncUtils;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

class IcsImporter
{
    static interface IcsImportCompleteListener
    {

        public abstract void onIcsImportComplete(ImportResult importresult);
    }

    public static final class ImportResult
    {

        public final Event event;
        public final boolean updateOperation;

        ImportResult(Event event1, boolean flag)
        {
            event = event1;
            updateOperation = flag;
        }
    }


    private static final String TAG = com/google/android/calendar/newapi/screen/ics/IcsImporter.getSimpleName();
    public final CalendarListClientFutureImpl calendarClient;
    private final EventClient eventClient;

    public IcsImporter(EventClient eventclient)
    {
        eventClient = eventclient;
        if (UnifiedSyncUtils.shouldUseCalendarsAndEvents())
        {
            eventclient = new CalendarListClientFutureImpl(new CalendarListApiStoreImpl());
        } else
        {
            eventclient = null;
        }
        calendarClient = eventclient;
    }

    private static Object getResultOrNull(ListenableFuture listenablefuture)
    {
        try
        {
            listenablefuture = ((ListenableFuture) (listenablefuture.get()));
        }
        // Misplaced declaration of an exception variable
        catch (ListenableFuture listenablefuture)
        {
            LogUtils.e(TAG, listenablefuture, "Unable to get result", new Object[0]);
            return null;
        }
        return listenablefuture;
    }

    static final boolean lambda$addImportListener$3$IcsImporter(ImportResult importresult)
    {
        return importresult == null;
    }

    static final void lambda$addImportListener$4$IcsImporter(ListenableFuture listenablefuture, IcsImportCompleteListener icsimportcompletelistener)
    {
        listenablefuture = (List)listenablefuture.get();
        if (Iterables2.isNullOrEmpty(listenablefuture))
        {
            return;
        }
        class .Lambda._cls2
            implements Predicate
        {

            public static final Predicate $instance = new .Lambda._cls2();

            public final boolean apply(Object obj)
            {
                return IcsImporter.lambda$addImportListener$3$IcsImporter((ImportResult)obj);
            }


            private .Lambda._cls2()
            {
            }
        }

        Predicate predicate = .Lambda._cls2..instance;
        boolean flag;
        if (Iterators.indexOf(listenablefuture.iterator(), predicate) != -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L1
_L5:
        try
        {
            icsimportcompletelistener.onIcsImportComplete(listenablefuture);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (ListenableFuture listenablefuture)
        {
            LogUtils.e(TAG, listenablefuture, "ICS Import: failed.", new Object[0]);
        }
        icsimportcompletelistener.onIcsImportComplete(new ImportResult(null, false));
        return;
_L3:
        listenablefuture = (ImportResult)listenablefuture.get(0);
        continue; /* Loop/switch isn't completed */
_L1:
        if (!flag) goto _L3; else goto _L2
_L2:
        listenablefuture = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    final ImportResult importOrUpdateSingleEvent(CalendarListEntry calendarlistentry, EventModifications eventmodifications)
    {
        if (eventmodifications.isNewEvent())
        {
            eventmodifications.switchCalendar(calendarlistentry);
            return new ImportResult((Event)getResultOrNull(eventClient.icsImport(eventmodifications)), false);
        }
        if (eventmodifications.isRecurring() && !eventmodifications.getOriginal().getDescriptor().isRecurringPhantom())
        {
            try
            {
                eventClient.delete(eventmodifications.getOriginal().getDescriptor(), 0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED).get();
                eventmodifications.setForceNewEvent(true);
                calendarlistentry = new ImportResult((Event)getResultOrNull(eventClient.icsImport(eventmodifications)), false);
            }
            // Misplaced declaration of an exception variable
            catch (CalendarListEntry calendarlistentry)
            {
                LogUtils.wtf(TAG, calendarlistentry, "Unable to delete original event", new Object[0]);
                return new ImportResult(null, false);
            }
            return calendarlistentry;
        }
        byte byte0;
        if (eventmodifications.isRecurring())
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        calendarlistentry = CalendarApi.EventPermissionsFactory.create(eventmodifications).getAllowedModificationScopes();
        if (!calendarlistentry.contains(Integer.valueOf(byte0)))
        {
            LogUtils.e(TAG, "Failed importing event. Cannot use scope %d. Allowed scopes are [%s].", new Object[] {
                Integer.valueOf(byte0), TextUtils.join(",", calendarlistentry)
            });
            return new ImportResult(null, true);
        } else
        {
            return new ImportResult((Event)getResultOrNull(eventClient.icsUpdate(eventmodifications, byte0)), true);
        }
    }

}
