// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.libraries.gcoreclient.common.GcoreConnectionResult;
import com.google.android.libraries.gcoreclient.common.api.GcoreApi;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.support:
//            BaseGcoreWrapper

public final class GcoreWrapper extends BaseGcoreWrapper
{

    public GcoreWrapper()
    {
    }

    public final volatile Api unwrap(GcoreApi gcoreapi)
    {
        return super.unwrap(gcoreapi);
    }

    public final volatile com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks unwrap(com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreConnectionCallbacks gcoreconnectioncallbacks)
    {
        return super.unwrap(gcoreconnectioncallbacks);
    }

    public final volatile com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener unwrap(com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreOnConnectionFailedListener gcoreonconnectionfailedlistener)
    {
        return super.unwrap(gcoreonconnectionfailedlistener);
    }

    public final volatile GoogleApiClient unwrap(GcoreGoogleApiClient gcoregoogleapiclient)
    {
        return super.unwrap(gcoregoogleapiclient);
    }

    public final volatile GcoreConnectionResult wrap(ConnectionResult connectionresult)
    {
        return super.wrap(connectionresult);
    }

    public final volatile GcorePendingResult wrap(PendingResult pendingresult)
    {
        return super.wrap(pendingresult);
    }
}
