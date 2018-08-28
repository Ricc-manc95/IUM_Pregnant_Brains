// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Scope;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf, zzn, zzg

public abstract class zzl extends zzf
    implements com.google.android.gms.common.api.Api.zze, zzm.zza
{

    public final zzg zzaKD;
    private final Account zzafe;
    private final Set zzalx;

    public zzl(Context context, Looper looper, int i, zzg zzg1, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        zzn zzn1 = zzn.zzaF(context);
        GoogleApiAvailability googleapiavailability = GoogleApiAvailability.zzaIm;
        if (connectioncallbacks == null)
        {
            throw new NullPointerException("null reference");
        }
        connectioncallbacks = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)connectioncallbacks;
        if (onconnectionfailedlistener == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            this(context, looper, zzn1, googleapiavailability, i, zzg1, connectioncallbacks, (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener)onconnectionfailedlistener);
            return;
        }
    }

    private zzl(Context context, Looper looper, zzn zzn1, GoogleApiAvailability googleapiavailability, int i, zzg zzg1, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, 
            com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        if (connectioncallbacks == null)
        {
            connectioncallbacks = null;
        } else
        {
            connectioncallbacks = new _cls1(connectioncallbacks);
        }
        if (onconnectionfailedlistener == null)
        {
            onconnectionfailedlistener = null;
        } else
        {
            onconnectionfailedlistener = new _cls2(onconnectionfailedlistener);
        }
        super(context, looper, zzn1, googleapiavailability, i, connectioncallbacks, onconnectionfailedlistener, zzg1.zzaJd);
        zzaKD = zzg1;
        zzafe = zzg1.zzafe;
        context = zzg1.zzaPM;
        looper = zzc(context);
        for (zzn1 = looper.iterator(); zzn1.hasNext();)
        {
            if (!context.contains((Scope)zzn1.next()))
            {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }

        zzalx = looper;
    }

    public final Account getAccount()
    {
        return zzafe;
    }

    public Set zzc(Set set)
    {
        return set;
    }

    protected final Set zzyR()
    {
        return zzalx;
    }

    private class _cls1
        implements zzf.zzb
    {

        private final com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks zzaQf;

        public final void onConnected(Bundle bundle)
        {
            zzaQf.onConnected(bundle);
        }

        public final void onConnectionSuspended(int i)
        {
            zzaQf.onConnectionSuspended(i);
        }

        _cls1(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
        {
            zzaQf = connectioncallbacks;
            super();
        }
    }


    private class _cls2
        implements zzf.zzc
    {

        private final com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener zzaQg;

        public final void onConnectionFailed(ConnectionResult connectionresult)
        {
            zzaQg.onConnectionFailed(connectionresult);
        }

        _cls2(com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            zzaQg = onconnectionfailedlistener;
            super();
        }
    }

}
