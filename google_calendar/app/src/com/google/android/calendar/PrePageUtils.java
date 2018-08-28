// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarListClient;
import com.google.android.calendar.launch.oobe.DemoUserUtil;
import com.google.android.calendar.launch.oobe.WhatsNewFactory;
import com.google.android.calendar.timely.ConfidentialityDialog;
import com.google.android.calendar.utils.account.AccountsUtil;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

public final class PrePageUtils
{

    public static void showPrePages(Activity activity)
    {
        if (WhatsNewFactory.instance == null)
        {
            WhatsNewFactory.instance = new WhatsNewFactory();
        }
        Object obj = WhatsNewFactory.instance;
        if (!(new DemoUserUtil(activity)).isDemoUser())
        {
            android.accounts.Account aaccount[] = AccountsUtil.getGoogleAccounts(activity);
            if (aaccount.length > 0)
            {
                ((WhatsNewFactory) (obj)).processAccountChanges(activity, aaccount);
            } else
            {
                ListenableFuture listenablefuture = AbstractTransformFuture.create(CalendarApi.CalendarList.list(null), com.google.android.apps.calendar.api.util.account.CalendarAccountsUtil..Lambda._cls0.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                activity = LogUtils.newFailureLoggingCallback(new com.google.android.calendar.launch.oobe.WhatsNewFactory..Lambda._cls0(((WhatsNewFactory) (obj)), activity), WhatsNewFactory.TAG, "Unable to list calendars", new Object[0]);
                obj = CalendarExecutor.MAIN;
                if (activity == null)
                {
                    throw new NullPointerException();
                }
                listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, activity), ((java.util.concurrent.Executor) (obj)));
            }
        }
        new ConfidentialityDialog();
    }
}
