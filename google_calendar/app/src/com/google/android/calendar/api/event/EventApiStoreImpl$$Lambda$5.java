// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.common.ContentProviderOperator;
import com.google.android.calendar.api.event.time.RecurrenceSplitter;
import com.google.calendar.v2a.android.util.provider.Batch;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.api.event:
//            EventApiStoreImpl, CpEventDescriptor, CpEventKey, ContentProviderRead, 
//            EventPermissionsFactory, EventPermissions, EventModificationsImpl, ContentProviderDelete, 
//            Event, EventDescriptor, EventStoreUtils, EventModifications, 
//            ContentProviderUpdate

final class arg._cls4
    implements Callable
{

    private final EventApiStoreImpl arg$1;
    private final CpEventDescriptor arg$2;
    private final int arg$3;
    private final ication arg$4;

    public final Object call()
    {
        Object obj;
        Object obj2;
        Object obj3;
        int i;
        boolean flag;
        boolean flag1;
        int j;
        boolean flag2;
        i = 1;
        flag1 = true;
        flag = false;
        flag2 = false;
        EventApiStoreImpl eventapistoreimpl = arg$1;
        obj2 = arg$2;
        j = arg$3;
        obj = arg$4;
        obj3 = eventapistoreimpl.read.readEvent((CpEventKey)((CpEventDescriptor) (obj2)).key);
        if (obj3 != null) goto _L2; else goto _L1
_L1:
        if (flag2)
        {
            EventApiStoreImpl.notifyWidgetAndForceUpsync();
        }
        return Boolean.valueOf(flag2);
_L2:
        if (!CalendarApi.EventPermissionsFactory.create(((Event) (obj3))).getAllowedModificationScopes().contains(Integer.valueOf(j)))
        {
            throw new IllegalArgumentException();
        }
        Object obj1 = new EventModificationsImpl(((Event) (obj3)));
        switch (j)
        {
        default:
            throw new UnsupportedOperationException("Unknown option");

        case 0: // '\0'
            if (((CpEventDescriptor) (obj2)).originalKey == null && !((CpEventDescriptor) (obj2)).key.hasStartMillis())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                flag2 = ContentProviderDelete.deleteStemEvent(((Event) (obj3)), ((ication) (obj)));
            } else
            {
                if (((CpEventDescriptor) (obj2)).originalKey == null && ((CpEventDescriptor) (obj2)).key.hasStartMillis())
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    obj1 = ((Event) (obj3)).getTimeZoneId();
                    if (((CpEventDescriptor) (obj2)).originalKey == null && ((CpEventDescriptor) (obj2)).key.hasStartMillis())
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        throw new IllegalArgumentException();
                    }
                    obj3 = new ContentValues();
                    ((ContentValues) (obj3)).put("originalInstanceTime", Long.valueOf(((EventDescriptor) (obj2)).getOriginalStart()));
                    obj = obj1;
                    if (obj1 == null)
                    {
                        obj = "UTC";
                    }
                    ((ContentValues) (obj3)).put("eventTimezone", ((String) (obj)));
                    ((ContentValues) (obj3)).put("eventStatus", Integer.valueOf(2));
                    i = ((flag) ? 1 : 0);
                    if (((CpEventDescriptor) (obj2)).originalKey == null)
                    {
                        i = ((flag) ? 1 : 0);
                        if (((CpEventDescriptor) (obj2)).key.hasStartMillis())
                        {
                            i = 1;
                        }
                    }
                    if (i == 0)
                    {
                        throw new IllegalStateException();
                    }
                    obj1 = (CpEventKey)((CpEventDescriptor) (obj2)).key;
                    if (!((CpEventKey) (obj1)).hasStartMillis())
                    {
                        throw new IllegalStateException();
                    }
                    obj = new ContentProviderOperator();
                    long l = ((CpEventKey) (obj1)).localId();
                    obj1 = ContentProviderOperation.newInsert(ContentUris.withAppendedId(android.provider.ENT_EXCEPTION_URI, l)).withValues(((ContentValues) (obj3))).build();
                    obj2 = ((ContentProviderOperator) (obj)).batch;
                    ((Batch) (obj2)).operations.add(obj1);
                    i = ((Batch) (obj2)).operations.size();
                    obj = ((ContentProviderOperator) (obj)).batch.apply(CalendarApi.getApiContentResolver(), "com.android.calendar").t(i - 1);
                    if (obj == null)
                    {
                        throw new IOException("Exception creation failed.");
                    }
                    ((Long) (obj)).longValue();
                    flag2 = true;
                } else
                {
                    if (((CpEventDescriptor) (obj2)).originalKey != null)
                    {
                        i = ((flag1) ? 1 : 0);
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        if (!((Event) (obj3)).isRecurring())
                        {
                            flag2 = ContentProviderDelete.deleteStemEvent(((Event) (obj3)), ((ication) (obj)));
                        } else
                        {
                            if (!((EventDescriptor) (obj2)).isRecurringException())
                            {
                                throw new IllegalArgumentException();
                            }
                            obj = new ContentProviderOperator();
                            long l1 = EventStoreUtils.localId(((EventDescriptor) (obj2)));
                            obj1 = new ContentValues();
                            ((ContentValues) (obj1)).put("eventStatus", Integer.valueOf(2));
                            obj1 = ContentProviderOperation.newUpdate(ContentUris.withAppendedId(android.provider.ENT_URI, l1)).withValues(((ContentValues) (obj1))).build();
                            obj2 = ((ContentProviderOperator) (obj)).batch;
                            ((Batch) (obj2)).operations.add(obj1);
                            ((Batch) (obj2)).operations.size();
                            flag2 = ((ContentProviderOperator) (obj)).batch.apply(CalendarApi.getApiContentResolver(), "com.android.calendar").d();
                        }
                    } else
                    {
                        throw new IllegalStateException("Event must be committed.");
                    }
                }
            }
            break;

        case 1: // '\001'
            if (!((CpEventDescriptor) (obj2)).isRecurring())
            {
                throw new IllegalStateException();
            }
            long l2 = ((CpEventDescriptor) (obj2)).getOriginalStart();
            EventApiStoreImpl.adjustTimesToFirstPhantom(((EventModifications) (obj1)));
            long l3 = ((EventModifications) (obj1)).getStartMillis();
            if (l3 == l2)
            {
                flag2 = ContentProviderDelete.deleteStemEvent(((Event) (obj3)), ((ication) (obj)));
            } else
            {
                obj2 = ((EventModifications) (obj1)).getTimeZoneId();
                obj2 = RecurrenceSplitter.splitRecurrence(((Event) (obj3)).getRecurrence(), l3, ((String) (obj2)), l2, ((Event) (obj3)).isAllDayEvent());
                if (((com.google.android.calendar.api.event.time.eLegacySplit) (obj2)).originalSeries != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalStateException(String.valueOf("No instances before the deleted instance"));
                }
                ((EventModifications) (obj1)).setRecurrence(((com.google.android.calendar.api.event.time.eLegacySplit) (obj2)).originalSeries);
                flag2 = ContentProviderUpdate.updateStemEvent(((EventModifications) (obj1)), ((ication) (obj))).changed;
            }
            break;

        case 2: // '\002'
            if (((CpEventDescriptor) (obj2)).originalKey != null || !((CpEventDescriptor) (obj2)).key.hasStartMillis())
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalArgumentException();
            }
            flag2 = ContentProviderDelete.deleteStemEvent(((Event) (obj3)), ((ication) (obj)));
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    ication(EventApiStoreImpl eventapistoreimpl, CpEventDescriptor cpeventdescriptor, int i, ication ication)
    {
        arg$1 = eventapistoreimpl;
        arg$2 = cpeventdescriptor;
        arg$3 = i;
        arg$4 = ication;
    }
}
