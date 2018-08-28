// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.security;

import android.content.Context;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesNotAvailableException;
import com.google.android.libraries.gcoreclient.common.GcoreGooglePlayServicesRepairableException;

public interface GcoreProviderInstaller
{

    public abstract void installIfNeeded(Context context)
        throws GcoreGooglePlayServicesNotAvailableException, GcoreGooglePlayServicesRepairableException;
}
