// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import com.google.android.apps.calendar.util.database.Cursors;
import com.google.android.calendar.api.event.CpEventKey;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.apps.calendar.timebox.cp:
//            CpEventsApi

public final class arg._cls2
    implements Callable
{

    private final CpEventsApi arg$1;
    private final CpEventKey arg$2;

    public final Object call()
    {
        CpEventsApi cpeventsapi = arg$1;
        CpEventKey cpeventkey = arg$2;
        return (Long)Cursors.extractSingleEntry(cpeventsapi.context.getContentResolver().query(ContentUris.withAppendedId(android.provider.s.CONTENT_URI, cpeventkey.localId()), new String[] {
            "dtstart"
        }, null, null, null), .instance, "Event start time");
    }

    public (CpEventsApi cpeventsapi, CpEventKey cpeventkey)
    {
        arg$1 = cpeventsapi;
        arg$2 = cpeventkey;
    }
}
