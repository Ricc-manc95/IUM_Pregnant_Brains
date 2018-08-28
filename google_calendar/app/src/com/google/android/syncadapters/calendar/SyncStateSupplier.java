// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import android.content.ContentProviderClient;
import com.google.common.base.Supplier;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            CalendarSyncStateUtils

final class SyncStateSupplier
    implements Supplier
{

    private final Account account;
    private final ContentProviderClient provider;

    SyncStateSupplier(ContentProviderClient contentproviderclient, Account account1)
    {
        provider = contentproviderclient;
        account = account1;
    }

    public final Object get()
    {
        return CalendarSyncStateUtils.getIfAvailable(provider, account);
    }
}
