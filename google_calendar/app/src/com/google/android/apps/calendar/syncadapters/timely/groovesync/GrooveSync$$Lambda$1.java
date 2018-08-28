// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.syncadapters.timely.groovesync;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.SyncResult;
import android.os.Bundle;
import com.google.api.services.calendar.Calendar;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.apps.calendar.syncadapters.timely.groovesync:
//            GrooveSync

final class arg._cls6
    implements Supplier
{

    private final GrooveSync arg$1;
    private final Account arg$2;
    private final Bundle arg$3;
    private final ContentProviderClient arg$4;
    private final Calendar arg$5;
    private final SyncResult arg$6;

    public final Object get()
    {
        return arg$1.syncInternal(arg$2, arg$3, arg$4, arg$5, arg$6);
    }

    (GrooveSync groovesync, Account account, Bundle bundle, ContentProviderClient contentproviderclient, Calendar calendar, SyncResult syncresult)
    {
        arg$1 = groovesync;
        arg$2 = account;
        arg$3 = bundle;
        arg$4 = contentproviderclient;
        arg$5 = calendar;
        arg$6 = syncresult;
    }
}
