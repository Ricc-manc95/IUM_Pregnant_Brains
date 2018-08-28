// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import android.accounts.Account;
import com.google.android.apps.calendar.util.concurrent.CalendarFutures;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.calendar.v2a.shared.storage.AsyncAccountService;
import com.google.common.base.Function;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            V2AEventsApi

final class arg._cls2
    implements Function
{

    private final V2AEventsApi arg$1;
    private final Map arg$2;

    public final Object apply(Object obj)
    {
        V2AEventsApi v2aeventsapi = arg$1;
        Map map = arg$2;
        obj = (CalendarListEntry)obj;
        Account account = ((CalendarListEntry) (obj)).getDescriptor().account;
        if (((CalendarListEntry) (obj)).isVisible() && AccountUtil.isGoogleAccount(account))
        {
            ListenableFuture listenablefuture = (ListenableFuture)map.get(account);
            obj = listenablefuture;
            if (listenablefuture == null)
            {
                obj = v2aeventsapi.asyncAccountService.getAccountKey(account.name);
                map.put(account, obj);
            }
            return obj;
        } else
        {
            return CalendarFutures.IMMEDIATE_ABSENT_FUTURE;
        }
    }

    (V2AEventsApi v2aeventsapi, Map map)
    {
        arg$1 = v2aeventsapi;
        arg$2 = map;
    }
}
