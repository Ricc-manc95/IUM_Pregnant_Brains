// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.analytics.internal.IAnalyticsService;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.analytics.tracking.android:
//            AnalyticsClient

final class AnalyticsGmsCoreClient
    implements AnalyticsClient
{

    public ServiceConnection mConnection;
    public Context mContext;
    public OnConnectedListener mOnConnectedListener;
    public OnConnectionFailedListener mOnConnectionFailedListener;
    public IAnalyticsService mService;

    public AnalyticsGmsCoreClient(Context context, OnConnectedListener onconnectedlistener, OnConnectionFailedListener onconnectionfailedlistener)
    {
        mContext = context;
        if (onconnectedlistener == null)
        {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        mOnConnectedListener = onconnectedlistener;
        if (onconnectionfailedlistener == null)
        {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        } else
        {
            mOnConnectionFailedListener = onconnectionfailedlistener;
            return;
        }
    }

    public final void clearHits()
    {
        boolean flag;
        if (mService != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            String s;
            try
            {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            }
            catch (RemoteException remoteexception)
            {
                s = (new StringBuilder("clear hits failed: ")).append(remoteexception).toString();
            }
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s).toString());
            return;
        }
        mService.clearHits();
        return;
    }

    public final void connect()
    {
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", mContext.getPackageName());
        if (mConnection != null)
        {
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Calling connect() while still connected, missing disconnect().").toString());
        } else
        {
            mConnection = new AnalyticsServiceConnection();
            boolean flag = mContext.bindService(intent, mConnection, 129);
            (new StringBuilder("connect: bindService returned ")).append(flag).append(" for ").append(intent).toString();
            if (!flag)
            {
                mConnection = null;
                mOnConnectionFailedListener._mth514KOOBECHP6UQB45THMURJKCLN78BQ9DPQ6ARJK7CKLC___0(1);
                return;
            }
        }
    }

    public final void disconnect()
    {
        mService = null;
        if (mConnection != null)
        {
            try
            {
                mContext.unbindService(mConnection);
            }
            catch (IllegalStateException illegalstateexception) { }
            catch (IllegalArgumentException illegalargumentexception) { }
            mConnection = null;
            mOnConnectedListener.onDisconnected();
        }
    }

    public final void sendHit(Map map, long l, String s, List list)
    {
        boolean flag;
        if (mService != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            try
            {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            }
            // Misplaced declaration of an exception variable
            catch (Map map)
            {
                map = (new StringBuilder("sendHit failed: ")).append(map).toString();
            }
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(map).toString());
            return;
        }
        mService.sendHit(map, l, s, list);
        return;
    }

    private class AnalyticsServiceConnection
        implements ServiceConnection
    {

        private final AnalyticsGmsCoreClient this$0;

        public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            (new StringBuilder("service connected, binder: ")).append(ibinder).toString();
            AnalyticsGmsCoreClient analyticsgmscoreclient;
            if (!"com.google.android.gms.analytics.internal.IAnalyticsService".equals(ibinder.getInterfaceDescriptor()))
            {
                break MISSING_BLOCK_LABEL_101;
            }
            analyticsgmscoreclient = AnalyticsGmsCoreClient.this;
            if (ibinder != null) goto _L2; else goto _L1
_L1:
            componentname = null;
_L3:
            try
            {
                analyticsgmscoreclient.mService = componentname;
                mOnConnectedListener.onConnected();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (ComponentName componentname) { }
            break MISSING_BLOCK_LABEL_101;
_L2:
            componentname = ibinder.queryLocalInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
            if (componentname == null)
            {
                break MISSING_BLOCK_LABEL_88;
            }
            if (!(componentname instanceof IAnalyticsService))
            {
                break MISSING_BLOCK_LABEL_88;
            }
            componentname = (IAnalyticsService)componentname;
              goto _L3
            componentname = new com.google.android.gms.analytics.internal.IAnalyticsService.Stub.Proxy(ibinder);
              goto _L3
            mContext.unbindService(this);
            mConnection = null;
            mOnConnectionFailedListener._mth514KOOBECHP6UQB45THMURJKCLN78BQ9DPQ6ARJK7CKLC___0(2);
            return;
        }

        public final void onServiceDisconnected(ComponentName componentname)
        {
            (new StringBuilder("service disconnected: ")).append(componentname).toString();
            mConnection = null;
            mOnConnectedListener.onDisconnected();
        }

        AnalyticsServiceConnection()
        {
            this$0 = AnalyticsGmsCoreClient.this;
            super();
        }
    }


    private class OnConnectionFailedListener
    {

        public abstract void onConnectionFailed$514KOOBECHP6UQB45THMURJKCLN78BQ9DPQ6ARJK7CKLC___0(int i);
    }


    private class OnConnectedListener
    {

        public abstract void onConnected();

        public abstract void onDisconnected();
    }

}
