// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.accounts.Account;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.collect.ImmutableCollection;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            PreferredNotification

public final class isExchange
{

    public static final PreferredNotification EXCHANGE_DEFAULT_ALL_DAY_NOTIFICATION = new PreferredNotification(1, 900, 1);
    public static final PreferredNotification EXCHANGE_DEFAULT_TIMED_NOTIFICATION = new PreferredNotification(0, 10, 1);
    public final List allDayNotifications = new ArrayList();
    public final boolean isExchange;
    public boolean noAllDayNotifications;
    public boolean noTimedNotifications;
    public final List timedNotifications = new ArrayList();

    public static PreferredNotification getExchangeInitialDefaultNotification(boolean flag)
    {
        if (flag)
        {
            return EXCHANGE_DEFAULT_ALL_DAY_NOTIFICATION;
        } else
        {
            return EXCHANGE_DEFAULT_TIMED_NOTIFICATION;
        }
    }


    public (Account account)
    {
        noTimedNotifications = false;
        noAllDayNotifications = false;
        account = account.type;
        isExchange = AccountUtil.EXCHANGE_TYPES.contains(account);
    }
}
