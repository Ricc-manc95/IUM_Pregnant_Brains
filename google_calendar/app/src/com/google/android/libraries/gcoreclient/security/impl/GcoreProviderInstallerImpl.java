// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.security.impl;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.UserRecoverableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;
import com.google.android.libraries.gcoreclient.security.GcoreProviderInstaller;

public final class GcoreProviderInstallerImpl
    implements GcoreProviderInstaller
{

    public GcoreProviderInstallerImpl()
    {
    }

    public final void installIfNeeded(Context context)
        throws GcoreGooglePlayServicesNotAvailableException, GcoreGooglePlayServicesRepairableException
    {
        try
        {
            ProviderInstaller.installIfNeeded(context);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new GcoreGooglePlayServicesNotAvailableException(((GooglePlayServicesNotAvailableException) (context)).errorCode, context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new GcoreGooglePlayServicesRepairableException(((GooglePlayServicesRepairableException) (context)).zzajN, context.getMessage(), new Intent(((UserRecoverableException) (context)).mIntent), context);
        }
    }
}
