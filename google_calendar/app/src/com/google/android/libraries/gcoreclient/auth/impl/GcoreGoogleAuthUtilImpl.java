// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.auth.impl;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.UserRecoverableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;

// Referenced classes of package com.google.android.libraries.gcoreclient.auth.impl:
//            BaseGcoreGoogleAuthUtilImpl

public final class GcoreGoogleAuthUtilImpl extends BaseGcoreGoogleAuthUtilImpl
{

    public GcoreGoogleAuthUtilImpl(Context context)
    {
        super(context);
    }

    public final Account[] getAccounts(String s)
        throws RemoteException, GcoreGooglePlayServicesNotAvailableException, GcoreGooglePlayServicesRepairableException
    {
        try
        {
            s = GoogleAuthUtil.getAccounts(context, s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new GcoreGooglePlayServicesRepairableException(((GooglePlayServicesRepairableException) (s)).zzajN, s.getMessage(), new Intent(((UserRecoverableException) (s)).mIntent), s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new GcoreGooglePlayServicesNotAvailableException(((GooglePlayServicesNotAvailableException) (s)).errorCode, s);
        }
        return s;
    }
}
