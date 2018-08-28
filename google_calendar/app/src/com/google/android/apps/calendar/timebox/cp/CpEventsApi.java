// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import android.content.Context;
import android.net.Uri;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

public final class CpEventsApi
{

    private final Supplier calendarListFutureSupplier;
    public final Context context;
    public final Supplier timeZoneSupplier;

    public CpEventsApi(Context context1, Supplier supplier, Supplier supplier1)
    {
        context = context1;
        timeZoneSupplier = supplier;
        calendarListFutureSupplier = supplier1;
    }

    public final FluentFuture queryAsync(Context context1, Uri uri, String s, String as[], String s1)
    {
        class .Lambda._cls2
            implements Callable
        {

            private final Context arg$1;
            private final Uri arg$2;
            private final String arg$3;
            private final String arg$4[];
            private final String arg$5;

            public final Object call()
            {
                Context context2 = arg$1;
                Uri uri1 = arg$2;
                String s2 = arg$3;
                String as1[] = arg$4;
                String s3 = arg$5;
                return context2.getContentResolver().query(uri1, EventsQueryInfo.PROJECTION, s2, as1, s3);
            }

            .Lambda._cls2(Context context1, Uri uri, String s, String as[], String s1)
            {
                arg$1 = context1;
                arg$2 = uri;
                arg$3 = s;
                arg$4 = as;
                arg$5 = s1;
            }
        }

        class .Lambda._cls3
            implements BiFunction
        {

            private final CpEventsApi arg$1;

            public final Object apply(Object obj, Object obj1)
            {
                CpEventsApi cpeventsapi = arg$1;
                obj = (com.google.android.calendar.api.calendarlist.CalendarListEntry[])obj;
                obj1 = (Cursor)obj1;
                if (obj1 == null)
                {
                    throw new NullPointerException("Cursor is null");
                } else
                {
                    return Cursors.apply(((Cursor) (obj1)), new CursorToEntryAdapter((TimeZone)cpeventsapi.timeZoneSupplier.get(), ((com.google.android.calendar.api.calendarlist.CalendarListEntry []) (obj))));
                }
            }

            .Lambda._cls3()
            {
                arg$1 = CpEventsApi.this;
            }
        }

        context1 = CalendarFutures.transform((ListenableFuture)calendarListFutureSupplier.get(), (FluentFuture)CalendarExecutor.EVENTS.submit(new .Lambda._cls2(context1, uri, s, as, s1)), new .Lambda._cls3(), CalendarExecutor.BACKGROUND);
        if (context1 instanceof FluentFuture)
        {
            return (FluentFuture)context1;
        } else
        {
            return new ForwardingFluentFuture(context1);
        }
    }
}
