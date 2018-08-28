// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.loggers;

import android.accounts.Account;
import android.content.Context;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApiClient;

public final class CalendarClearcutLogger
{

    public static CalendarClearcutLogger calendarClearcutLogger;
    private GoogleApiClient client;
    private com.google.android.gms.common.api.GoogleApiClient.Builder clientBuilder;
    private final Context context;

    CalendarClearcutLogger(Context context1)
    {
        if (GoogleApiAvailability.zzaIm.isGooglePlayServicesAvailable(context1) == 0)
        {
            clientBuilder = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context1)).addApi(ClearcutLogger.API);
        }
        context = context1;
    }

    public final void logEvent(com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder logeventbuilder)
    {
        if (clientBuilder == null)
        {
            return;
        }
        if (client == null)
        {
            client = clientBuilder.build();
        }
        if (!client.isConnecting() && !client.isConnected())
        {
            client.connect();
        }
        logeventbuilder.logAsync();
    }

    public final void logEvent(com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder logeventbuilder, Account account)
    {
        boolean flag;
        boolean flag1;
        flag1 = false;
        flag = flag1;
        if (account == null) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if (!"com.google".equals(account.type)) goto _L2; else goto _L3
_L3:
        Account aaccount[];
        int i;
        int j;
        aaccount = AccountsUtil.getGoogleAccounts(context);
        j = aaccount.length;
        i = 0;
_L8:
        flag = flag1;
        if (i >= j) goto _L2; else goto _L4
_L4:
        if (!account.equals(aaccount[i])) goto _L6; else goto _L5
_L5:
        flag = true;
_L2:
        if (!flag)
        {
            return;
        }
        break; /* Loop/switch isn't completed */
_L6:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
        account = account.name;
        if (ClearcutLogger.zzh(logeventbuilder.zzaHB))
        {
            throw new IllegalArgumentException("setUploadAccountName forbidden on anonymous logger");
        } else
        {
            logeventbuilder.zzaHl = account;
            logEvent(logeventbuilder);
            return;
        }
    }
}
