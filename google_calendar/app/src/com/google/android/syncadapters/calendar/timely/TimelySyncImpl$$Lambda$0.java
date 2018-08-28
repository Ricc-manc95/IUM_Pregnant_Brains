// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely;

import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.syncadapters.calendar.timely:
//            TimelySyncImpl

final class arg._cls3
    implements Supplier
{

    private final TimelySyncImpl arg$1;
    private final long arg$2;
    private final String arg$3;

    public final Object get()
    {
        TimelySyncImpl timelysyncimpl = arg$1;
        long l = arg$2;
        String s = arg$3;
        return timelysyncimpl.timelyStore.getLastSyncedConferenceDataForEvent(l, s);
    }

    (TimelySyncImpl timelysyncimpl, long l, String s)
    {
        arg$1 = timelysyncimpl;
        arg$2 = l;
        arg$3 = s;
    }
}
