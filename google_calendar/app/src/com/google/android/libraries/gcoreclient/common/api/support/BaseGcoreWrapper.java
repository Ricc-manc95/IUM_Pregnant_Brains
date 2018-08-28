// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.support;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.libraries.gcoreclient.common.GcoreConnectionResult;
import com.google.android.libraries.gcoreclient.common.api.GcoreApi;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcorePendingResult;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.gcoreclient.common.api.support:
//            BaseGcoreApi, GoogleApiClientWrapper, BaseGcoreConnectionResult, GcorePendingResultImpl, 
//            GcoreStatusImpl

class BaseGcoreWrapper
{

    private final Map connectionCallbacksMap = new ArrayMap();
    private final Map failedListenerMap = new ArrayMap();
    private final Object mapLock = new Object();

    BaseGcoreWrapper()
    {
    }

    public Api unwrap(GcoreApi gcoreapi)
    {
        if (gcoreapi instanceof BaseGcoreApi)
        {
            return ((BaseGcoreApi)gcoreapi).getApi();
        } else
        {
            return null;
        }
    }

    public com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks unwrap(final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreConnectionCallbacks listener)
    {
label0:
        {
            synchronized (mapLock)
            {
                if (!connectionCallbacksMap.containsKey(listener))
                {
                    break label0;
                }
                listener = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)connectionCallbacksMap.get(listener);
            }
            return listener;
        }
        _cls1 _lcls1;
        _lcls1 = new _cls1();
        connectionCallbacksMap.put(listener, _lcls1);
        obj;
        JVM INSTR monitorexit ;
        return _lcls1;
        listener;
        obj;
        JVM INSTR monitorexit ;
        throw listener;
    }

    public com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener unwrap(final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreOnConnectionFailedListener listener)
    {
label0:
        {
            synchronized (mapLock)
            {
                if (!failedListenerMap.containsKey(listener))
                {
                    break label0;
                }
                listener = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener)failedListenerMap.get(listener);
            }
            return listener;
        }
        _cls2 _lcls2;
        _lcls2 = new _cls2();
        failedListenerMap.put(listener, _lcls2);
        obj;
        JVM INSTR monitorexit ;
        return _lcls2;
        listener;
        obj;
        JVM INSTR monitorexit ;
        throw listener;
    }

    public GoogleApiClient unwrap(GcoreGoogleApiClient gcoregoogleapiclient)
    {
        if (gcoregoogleapiclient instanceof GoogleApiClientWrapper)
        {
            return ((GoogleApiClientWrapper)gcoregoogleapiclient).unwrap();
        } else
        {
            return null;
        }
    }

    public GcoreConnectionResult wrap(ConnectionResult connectionresult)
    {
        return new BaseGcoreConnectionResult(connectionresult);
    }

    public GcorePendingResult wrap(PendingResult pendingresult)
    {
        return new GcorePendingResultImpl(pendingresult, GcoreStatusImpl.STATUS_RESULT_WRAPPER);
    }

    private class _cls1
        implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    {

        private final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreConnectionCallbacks val$listener;

        public final void onConnected(Bundle bundle)
        {
            listener.onConnected$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R55B0____0();
        }

        public final void onConnectionSuspended(int i)
        {
            listener.onConnectionSuspended$514IILG_0();
        }

        _cls1()
        {
            listener = gcoreconnectioncallbacks;
            super();
        }
    }


    private class _cls2
        implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    {

        private final BaseGcoreWrapper this$0;
        private final com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreOnConnectionFailedListener val$listener;

        public final void onConnectionFailed(ConnectionResult connectionresult)
        {
            listener.onConnectionFailed(wrap(connectionresult));
        }

        _cls2()
        {
            this$0 = BaseGcoreWrapper.this;
            listener = gcoreonconnectionfailedlistener;
            super();
        }
    }

}
