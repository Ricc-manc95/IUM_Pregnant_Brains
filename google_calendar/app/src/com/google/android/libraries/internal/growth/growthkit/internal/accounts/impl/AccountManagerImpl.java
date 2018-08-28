// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.accounts.impl;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthException;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthUtil;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Provider;

public final class AccountManagerImpl
    implements AccountManager
{

    private static final Logger logger = new Logger();
    private final GcoreGoogleAuthUtil authUtil;
    private final Context context;
    private final ListeningExecutorService executor;
    private final Provider syncGaiaAccounts;

    public AccountManagerImpl(Context context1, ListeningExecutorService listeningexecutorservice, GcoreGoogleAuthUtil gcoregoogleauthutil, Provider provider)
    {
        context = context1;
        executor = listeningexecutorservice;
        authUtil = gcoregoogleauthutil;
        syncGaiaAccounts = provider;
    }

    public final String getAccountId(String s)
    {
        s = authUtil.getAccountId(s);
        return s;
        s;
_L2:
        Object obj = logger;
        String s1 = "Failed to get account id";
        Object aobj[] = new Object[0];
        String s2 = ((Logger) (obj)).tag;
        obj = s1;
        if (aobj != null)
        {
            obj = s1;
            if (aobj.length > 0)
            {
                obj = String.format("Failed to get account id", aobj);
            }
        }
        Log.e(s2, ((String) (obj)), s);
        return null;
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final List getAccountsNames()
    {
        if (!((Boolean)syncGaiaAccounts.get()).booleanValue())
        {
            return Collections.emptyList();
        }
        ArrayList arraylist = new ArrayList();
        Object obj;
        Object obj1;
        try
        {
            obj = authUtil.getAccounts("com.google");
        }
        catch (Exception exception)
        {
            obj = logger;
            obj1 = "Failed to get accounts using GcoreGoogleAuthUtil";
            Object aobj[] = new Object[0];
            String s1 = ((Logger) (obj)).tag;
            obj = obj1;
            if (aobj != null)
            {
                obj = obj1;
                if (aobj.length > 0)
                {
                    obj = String.format("Failed to get accounts using GcoreGoogleAuthUtil", aobj);
                }
            }
            Log.e(s1, ((String) (obj)), exception);
            obj = null;
        }
        obj1 = obj;
        if (obj == null)
        {
            if (ContextCompat.checkSelfPermission(context, "android.permission.GET_ACCOUNTS") == 0)
            {
                obj1 = android.accounts.AccountManager.get(context).getAccountsByType("com.google");
            } else
            {
                obj1 = logger;
                String s = "Failed to get accounts using AccountManager, missing permission GET_ACCOUNTS";
                Object aobj1[] = new Object[0];
                String s2 = ((Logger) (obj1)).tag;
                obj1 = s;
                if (aobj1 != null)
                {
                    obj1 = s;
                    if (aobj1.length > 0)
                    {
                        obj1 = String.format("Failed to get accounts using AccountManager, missing permission GET_ACCOUNTS", aobj1);
                    }
                }
                Log.e(s2, ((String) (obj1)));
                obj1 = obj;
            }
        }
        if (obj1 != null)
        {
            int j = obj1.length;
            for (int i = 0; i < j; i++)
            {
                arraylist.add(obj1[i].name);
            }

        }
        return arraylist;
    }

    public final ListenableFuture getOAuthTokenAsync(String s, String s1)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final AccountManagerImpl arg$1;
            private final String arg$2;
            private final String arg$3;

            public final Object call()
            {
                return arg$1.lambda$getOAuthTokenAsync$0$AccountManagerImpl(arg$2, arg$3);
            }

            .Lambda._cls0(String s, String s1)
            {
                arg$1 = AccountManagerImpl.this;
                arg$2 = s;
                arg$3 = s1;
            }
        }

        return executor.submit(new .Lambda._cls0(s, s1));
    }

    final String lambda$getOAuthTokenAsync$0$AccountManagerImpl(String s, String s1)
        throws Exception
    {
        s = authUtil.getToken(s, s1, Bundle.EMPTY);
        return s;
        s;
_L2:
        s1 = logger;
        String s2 = "Failed to get OAuth token";
        Object aobj[] = new Object[0];
        String s3 = ((Logger) (s1)).tag;
        s1 = s2;
        if (aobj != null)
        {
            s1 = s2;
            if (aobj.length > 0)
            {
                s1 = String.format("Failed to get OAuth token", aobj);
            }
        }
        Log.w(s3, s1, s);
        return null;
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

}
