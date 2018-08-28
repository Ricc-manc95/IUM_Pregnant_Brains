// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;
import java.io.IOException;

// Referenced classes of package com.google.android.libraries.gcoreclient.auth:
//            GcoreGoogleAuthException, GcoreGooglePlayServicesAvailabilityException, GcoreUserRecoverableAuthException

public interface GcoreGoogleAuthUtil
{

    public abstract String getAccountId(String s)
        throws GcoreGoogleAuthException, IOException;

    public abstract Account[] getAccounts(String s)
        throws RemoteException, GcoreGooglePlayServicesNotAvailableException, GcoreGooglePlayServicesRepairableException;

    public abstract String getToken(String s, String s1, Bundle bundle)
        throws GcoreGooglePlayServicesAvailabilityException, GcoreUserRecoverableAuthException, GcoreGoogleAuthException, IOException;
}
