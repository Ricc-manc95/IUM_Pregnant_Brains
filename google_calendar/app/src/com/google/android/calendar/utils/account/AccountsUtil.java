// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.version.MncUtil;
import com.google.android.gsf.GoogleLoginServiceConstants;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public final class AccountsUtil
{

    public static final String GOOGLE_CALENDAR_SYNC_FEATURE[] = {
        GoogleLoginServiceConstants.featureForService("cl")
    };
    public static boolean cacheGoogleAccounts = false;
    public static Account cachedGoogleAccounts[] = null;
    public static boolean getAccountsIssuePresent = false;

    public static Account[] getGoogleAccounts(Context context)
    {
        Account aaccount[];
        Account aaccount1[];
        try
        {
            aaccount1 = cachedGoogleAccounts;
        }
        catch (SecurityException securityexception)
        {
            if (MncUtil.isGetAccountsIssuePresent(context))
            {
                getAccountsIssuePresent = true;
                LogUtils.e("CalendarAccounts", securityexception, "SecurityException consumed.", new Object[0]);
                return new Account[0];
            } else
            {
                throw securityexception;
            }
        }
        aaccount = aaccount1;
        if (aaccount1 != null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        aaccount = AccountManager.get(context).getAccountsByType("com.google");
        if (cacheGoogleAccounts)
        {
            cachedGoogleAccounts = aaccount;
        }
        return aaccount;
    }

    public static Account[] getGoogleAccounts(Context context, String as[])
    {
        HandlerThread handlerthread = new HandlerThread("AccountGetter", 1);
        handlerthread.start();
        SettableFuture settablefuture = new SettableFuture();
        class .Lambda._cls0
            implements AccountManagerCallback
        {

            private final SettableFuture arg$1;

            public final void run(AccountManagerFuture accountmanagerfuture)
            {
                AccountsUtil.lambda$getGoogleAccounts$0$AccountsUtil(arg$1, accountmanagerfuture);
            }

            .Lambda._cls0(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        AccountManager.get(context).getAccountsByTypeAndFeatures("com.google", as, new .Lambda._cls0(settablefuture), new Handler(handlerthread.getLooper()));
        as = (Account[])settablefuture.get();
        handlerthread.quit();
        return as;
        context;
_L4:
        LogUtils.e("CalendarAccounts", context, "Error getting Google accounts", new Object[0]);
        handlerthread.quit();
_L2:
        return new Account[0];
        as;
        if (!MncUtil.isGetAccountsIssuePresent(context))
        {
            break; /* Loop/switch isn't completed */
        }
        getAccountsIssuePresent = true;
        LogUtils.e("CalendarAccounts", as, "SecurityException consumed.", new Object[0]);
        handlerthread.quit();
        if (true) goto _L2; else goto _L1
_L1:
        throw as;
        context;
        handlerthread.quit();
        throw context;
        context;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void initialize(Context context)
    {
        context.registerReceiver(new AccountsChangedReceiver(), new IntentFilter("android.accounts.LOGIN_ACCOUNTS_CHANGED"));
        cacheGoogleAccounts = true;
    }

    static final void lambda$getGoogleAccounts$0$AccountsUtil(SettableFuture settablefuture, AccountManagerFuture accountmanagerfuture)
    {
        try
        {
            settablefuture.set((Account[])accountmanagerfuture.getResult());
            return;
        }
        // Misplaced declaration of an exception variable
        catch (AccountManagerFuture accountmanagerfuture) { }
        // Misplaced declaration of an exception variable
        catch (AccountManagerFuture accountmanagerfuture) { }
        // Misplaced declaration of an exception variable
        catch (AccountManagerFuture accountmanagerfuture) { }
        settablefuture.setException(accountmanagerfuture);
    }


    private class AccountsChangedReceiver extends BroadcastReceiver
    {

        public final void onReceive(Context context, Intent intent)
        {
            AccountsUtil.cachedGoogleAccounts = null;
        }

        AccountsChangedReceiver()
        {
        }
    }

}
