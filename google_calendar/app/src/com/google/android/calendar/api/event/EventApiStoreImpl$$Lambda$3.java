// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.time.RecurrenceSplitter;
import com.google.android.calendar.api.event.time.RecurrenceStartShifter;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventModifications, EventApiStoreImpl, EventDescriptor, CpEventKey, 
//            ContentProviderRead, EventPermissionsFactory, EventPermissions, ContentProviderInsert, 
//            CpEventDescriptor, ContentProviderUpdate, EventModificationsImpl, Event, 
//            ContentProviderDelete

final class arg._cls4
    implements Callable
{

    private final EventApiStoreImpl arg$1;
    private final EventModifications arg$2;
    private final int arg$3;
    private final ication arg$4;

    public final Object call()
    {
        Object obj1;
        EventApiStoreImpl eventapistoreimpl;
        ication ication;
        int k;
        eventapistoreimpl = arg$1;
        obj1 = arg$2;
        k = arg$3;
        ication = arg$4;
        if (!((EventModifications) (obj1)).isModified())
        {
            return eventapistoreimpl.read.readEvent((CpEventKey)((EventModifications) (obj1)).getDescriptor().getKey());
        }
        boolean flag;
        if (!((EventModifications) (obj1)).isNewEvent())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (((EventModifications) (obj1)).getCalendar() == null)
        {
            throw new NullPointerException();
        }
        if (!CalendarApi.EventPermissionsFactory.create(((Event) (obj1))).getAllowedModificationScopes().contains(Integer.valueOf(k)))
        {
            throw new IllegalArgumentException();
        }
        k;
        JVM INSTR tableswitch 0 2: default 168
    //                   0 178
    //                   1 353
    //                   2 879;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException("Invalid update scope");
_L2:
        Object obj;
        if (((EventModifications) (obj1)).getDescriptor().isRecurringPhantom())
        {
            if (!((EventModifications) (obj1)).getDescriptor().isRecurringPhantom())
            {
                throw new IllegalArgumentException();
            }
            obj = ContentProviderInsert.performInsertion(((EventModifications) (obj1)), ication, null, true);
            obj1 = ((CpEventDescriptor)((EventModifications) (obj1)).getDescriptor()).derivePhantomDescriptor(((EventModifications) (obj1)).getStartMillis());
            long l1 = (() (obj)).localId;
            boolean flag1;
            if (((CpEventDescriptor) (obj1)).originalKey == null && ((CpEventDescriptor) (obj1)).key.hasStartMillis())
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
            obj = new (new CpEventDescriptor(CpEventKey.newInstance(l1), ((CpEventDescriptor) (obj1)).key), true);
        } else
        {
            obj = ContentProviderUpdate.updateStemEvent(((EventModifications) (obj1)), ication);
        }
_L6:
        if ((() (obj)).changed)
        {
            EventApiStoreImpl.notifyWidgetAndForceUpsync();
        }
        return eventapistoreimpl.read.readEvent((CpEventKey)(() (obj)).updatedDescriptor.getKey());
_L3:
        if (!((EventModifications) (obj1)).getDescriptor().isRecurring())
        {
            throw new IllegalStateException();
        }
        EventModificationsImpl eventmodificationsimpl = new EventModificationsImpl(((EventModifications) (obj1)).getOriginal());
        long l2 = ((EventModifications) (obj1)).getDescriptor().getOriginalStart();
        if (!eventmodificationsimpl.isRecurring())
        {
            throw new IllegalArgumentException(String.valueOf("Event is not a recurring phantom."));
        }
        long l4 = eventmodificationsimpl.getRecurringFirstStartMillis();
        for (obj = eventmodificationsimpl; ((EventModifications) (obj)).getOriginal() instanceof EventModifications; obj = (EventModifications)((EventModifications) (obj)).getOriginal()) { }
        Event event1 = ((EventModifications) (obj)).getOriginal();
        if (event1 == null)
        {
            throw new NullPointerException();
        }
        long l6 = event1.getStartMillis();
        Object obj3 = event1.getTimeZoneId();
        obj = obj3;
        if (obj3 == null)
        {
            obj = "Etc/GMT";
        }
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
        calendar1.setTimeInMillis(l6);
        l6 = eventmodificationsimpl.getStartMillis();
        obj3 = eventmodificationsimpl.getTimeZoneId();
        obj = obj3;
        if (obj3 == null)
        {
            obj = "Etc/GMT";
        }
        Calendar calendar3 = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
        calendar3.setTimeInMillis(l6);
        obj3 = event1.getTimeZoneId();
        obj = obj3;
        if (obj3 == null)
        {
            obj = "Etc/GMT";
        }
        obj = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
        ((Calendar) (obj)).setTimeInMillis(l4);
        int i = RecurrenceStartShifter.getJulianDay(calendar3);
        int l = RecurrenceStartShifter.getJulianDay(calendar1);
        obj3 = (Calendar)calendar3.clone();
        ((Calendar) (obj3)).set(6, ((Calendar) (obj)).get(6));
        ((Calendar) (obj3)).set(1, ((Calendar) (obj)).get(1));
        ((Calendar) (obj3)).add(6, i - l);
        l4 = ((Calendar) (obj3)).getTimeInMillis();
        l6 = eventmodificationsimpl.getEndMillis();
        long l8 = eventmodificationsimpl.getStartMillis();
        eventmodificationsimpl.setStartMillis(l4);
        eventmodificationsimpl.setEndMillis((l6 + l4) - l8);
        l4 = eventmodificationsimpl.getStartMillis();
        if (l4 == l2)
        {
            obj = ContentProviderUpdate.updateStemEvent(((EventModifications) (obj1)), ication);
        } else
        {
            obj = eventmodificationsimpl.getTimeZoneId();
            obj = RecurrenceSplitter.splitRecurrence(eventmodificationsimpl.getRecurrence(), l4, ((String) (obj)), l2, eventmodificationsimpl.isAllDayEvent());
            boolean flag2;
            if (((com.google.android.calendar.api.event.time.eLegacySplit) (obj)).originalSeries != null)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                throw new IllegalStateException(String.valueOf("No instances before the changed instance"));
            }
            eventmodificationsimpl.setRecurrence(((com.google.android.calendar.api.event.time.eLegacySplit) (obj)).originalSeries);
            boolean flag3 = ContentProviderUpdate.updateStemEvent(eventmodificationsimpl, ication).changed;
            if (!((EventModifications) (obj1)).isRecurrenceModified())
            {
                ((EventModifications) (obj1)).setRecurrence(((com.google.android.calendar.api.event.time.eLegacySplit) (obj)).newSeries);
            }
            obj = ContentProviderInsert.insertEvent(((EventModifications) (obj1)), ication);
            if (flag3 || obj != null)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            obj = new (((EventDescriptor) (obj)), flag3);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (((EventModifications) (obj1)).isRecurrenceModified() && !((EventModifications) (obj1)).isRecurring())
        {
            ContentProviderDelete.deleteStemEvent(((Event) (obj1)), ication);
            obj = new (ContentProviderInsert.insertEvent(((EventModifications) (obj1)), ication), true);
        } else
        {
            if (!((EventModifications) (obj1)).isRecurring())
            {
                throw new IllegalArgumentException(String.valueOf("Event is not a recurring phantom."));
            }
            long l3 = ((EventModifications) (obj1)).getRecurringFirstStartMillis();
            for (obj = obj1; ((EventModifications) (obj)).getOriginal() instanceof EventModifications; obj = (EventModifications)((EventModifications) (obj)).getOriginal()) { }
            Event event = ((EventModifications) (obj)).getOriginal();
            if (event == null)
            {
                throw new NullPointerException();
            }
            long l5 = event.getStartMillis();
            Object obj2 = event.getTimeZoneId();
            obj = obj2;
            if (obj2 == null)
            {
                obj = "Etc/GMT";
            }
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
            calendar.setTimeInMillis(l5);
            l5 = ((Event) (obj1)).getStartMillis();
            obj2 = ((Event) (obj1)).getTimeZoneId();
            obj = obj2;
            if (obj2 == null)
            {
                obj = "Etc/GMT";
            }
            Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
            calendar2.setTimeInMillis(l5);
            obj2 = event.getTimeZoneId();
            obj = obj2;
            if (obj2 == null)
            {
                obj = "Etc/GMT";
            }
            obj = Calendar.getInstance(TimeZone.getTimeZone(((String) (obj))));
            ((Calendar) (obj)).setTimeInMillis(l3);
            int j = RecurrenceStartShifter.getJulianDay(calendar2);
            int i1 = RecurrenceStartShifter.getJulianDay(calendar);
            obj2 = (Calendar)calendar2.clone();
            ((Calendar) (obj2)).set(6, ((Calendar) (obj)).get(6));
            ((Calendar) (obj2)).set(1, ((Calendar) (obj)).get(1));
            ((Calendar) (obj2)).add(6, j - i1);
            l3 = ((Calendar) (obj2)).getTimeInMillis();
            l5 = ((EventModifications) (obj1)).getEndMillis();
            long l7 = ((EventModifications) (obj1)).getStartMillis();
            ((EventModifications) (obj1)).setStartMillis(l3);
            ((EventModifications) (obj1)).setEndMillis((l5 + l3) - l7);
            obj = ContentProviderUpdate.updateStemEvent(((EventModifications) (obj1)), ication);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    ication(EventApiStoreImpl eventapistoreimpl, EventModifications eventmodifications, int i, ication ication)
    {
        arg$1 = eventapistoreimpl;
        arg$2 = eventmodifications;
        arg$3 = i;
        arg$4 = ication;
    }
}
