// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;

// Referenced classes of package com.google.android.gms.internal:
//            zzyu

public final class zzyt
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    public final Api zzaGo;
    private final int zzaKm;
    public zzyu zzaKn;

    public zzyt(Api api, int i)
    {
        zzaGo = api;
        zzaKm = i;
    }

    public final void onConnected(Bundle bundle)
    {
        if (zzaKn == null)
        {
            throw new NullPointerException(String.valueOf("Callbacks must be attached to a ClientConnectionHelper instance before connecting the client."));
        } else
        {
            zzaKn.onConnected(bundle);
            return;
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        if (zzaKn == null)
        {
            throw new NullPointerException(String.valueOf("Callbacks must be attached to a ClientConnectionHelper instance before connecting the client."));
        } else
        {
            zzaKn.zza(connectionresult, zzaGo, zzaKm);
            return;
        }
    }

    public final void onConnectionSuspended(int i)
    {
        if (zzaKn == null)
        {
            throw new NullPointerException(String.valueOf("Callbacks must be attached to a ClientConnectionHelper instance before connecting the client."));
        } else
        {
            zzaKn.onConnectionSuspended(i);
            return;
        }
    }
}
