// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.auth.impl;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthException;
import com.google.android.libraries.gcoreclient.auth.GcoreGoogleAuthUtil;
import com.google.android.libraries.gcoreclient.auth.GcoreGooglePlayServicesAvailabilityException;
import com.google.android.libraries.gcoreclient.auth.GcoreUserRecoverableAuthException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class BaseGcoreGoogleAuthUtilImpl
    implements GcoreGoogleAuthUtil
{

    public final Context context;

    public BaseGcoreGoogleAuthUtilImpl(Context context1)
    {
        context = context1;
    }

    public final String getAccountId(String s)
        throws GcoreGoogleAuthException, IOException
    {
        try
        {
            s = GoogleAuthUtil.getAccountId(context, s);
        }
        catch (UserRecoverableAuthException userrecoverableauthexception)
        {
            String s1 = userrecoverableauthexception.getMessage();
            if (userrecoverableauthexception.mIntent == null)
            {
                s = null;
            } else
            {
                s = new Intent(userrecoverableauthexception.mIntent);
            }
            throw new GcoreUserRecoverableAuthException(s1, s, userrecoverableauthexception);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new GcoreGoogleAuthException(s);
        }
        return s;
    }

    public Account[] getAccounts(String s)
        throws RemoteException, GcoreGooglePlayServicesNotAvailableException, GcoreGooglePlayServicesRepairableException
    {
        return AccountManager.get(context).getAccountsByType(s);
    }

    public final String getToken(String s, String s1, Bundle bundle)
        throws GcoreGooglePlayServicesAvailabilityException, GcoreUserRecoverableAuthException, GcoreGoogleAuthException, IOException
    {
        Object obj1 = null;
        Object obj = null;
        try
        {
            s = GoogleAuthUtil.getToken(context, s, s1, bundle);
        }
        // Misplaced declaration of an exception variable
        catch (String s1)
        {
            int i = ((GooglePlayServicesAvailabilityException) (s1)).zzajN;
            bundle = s1.getMessage();
            if (((UserRecoverableAuthException) (s1)).mIntent == null)
            {
                s = obj;
            } else
            {
                s = new Intent(((UserRecoverableAuthException) (s1)).mIntent);
            }
            throw new GcoreGooglePlayServicesAvailabilityException(i, bundle, s, s1);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            s1 = s.getMessage();
            if (((UserRecoverableAuthException) (s)).mIntent == null)
            {
                s = obj1;
            } else
            {
                s = new Intent(((UserRecoverableAuthException) (s)).mIntent);
            }
            throw new GcoreUserRecoverableAuthException(s1, s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new GcoreGoogleAuthException(s);
        }
        return s;
    }

    static 
    {
        TimeUnit.SECONDS.toMillis(2L);
    }
}
