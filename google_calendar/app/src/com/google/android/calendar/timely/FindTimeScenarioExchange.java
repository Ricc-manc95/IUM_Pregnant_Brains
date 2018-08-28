// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.Context;
import android.os.RemoteException;
import com.android.calendarcommon2.LogUtils;
import com.android.emailcommon.service.EmailServiceProxy;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.calendar.utils.exchange.ExchangeUtil;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.timely:
//            FindTimeScenario

final class FindTimeScenarioExchange
    implements FindTimeScenario
{
    static interface ProxyProvider
    {

        public abstract EmailServiceProxy get(Context context);
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/FindTimeScenarioExchange);
    private static Map exchangeFindTimeSupportMap = new HashMap();
    private ProxyProvider proxyProvider;

    FindTimeScenarioExchange()
    {
        this(((ProxyProvider) (new _cls1())));
    }

    private FindTimeScenarioExchange(ProxyProvider proxyprovider)
    {
        proxyProvider = proxyprovider;
    }

    private final boolean isEnabled(Context context, Account account)
    {
        boolean flag1 = false;
        if (AccountUtil.isGoogleExchangeAccount(account) || AccountUtil.isGoogleExchangeGoAccount(account)) goto _L2; else goto _L1
_L1:
        EmailServiceProxy emailserviceproxy;
        return false;
_L2:
        if ((emailserviceproxy = proxyProvider.get(context)) == null || ExchangeUtil.getEasServiceSupportPackageName(context) == null) goto _L1; else goto _L3
_L3:
        if (exchangeFindTimeSupportMap.containsKey(account))
        {
            return ((Boolean)exchangeFindTimeSupportMap.get(account)).booleanValue();
        }
        context = emailserviceproxy.getProtocolVersion(account.name);
        boolean flag;
        flag = flag1;
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        double d = Double.parseDouble(context);
        flag = flag1;
        if (d >= 14D)
        {
            flag = true;
        }
_L5:
        exchangeFindTimeSupportMap.put(account, Boolean.valueOf(flag));
        return flag;
        context;
_L6:
        LogUtils.e(TAG, context, "getProtocolVersion failed", new Object[0]);
        flag = flag1;
        if (true) goto _L5; else goto _L4
_L4:
        context;
          goto _L6
    }

    public final boolean isEnabled(Context context, CalendarListEntry calendarlistentry)
    {
        return isEnabled(context, calendarlistentry.getDescriptor().account) && calendarlistentry.isPrimary();
    }


    private class _cls1
        implements ProxyProvider
    {

        public final EmailServiceProxy get(Context context)
        {
            return ExchangeUtil.getEasServiceProxy(context);
        }

        _cls1()
        {
        }
    }

}
