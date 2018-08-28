// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public abstract class ApiClientAsyncTask extends AsyncTask
{

    public GoogleApiClient client;

    public ApiClientAsyncTask(Context context, String s)
    {
        com.google.android.gms.common.api.GoogleApiClient.Builder builder = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(Drive.API);
        context = Drive.SCOPE_FULL;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Scope must not be null"));
        }
        builder.zzaIZ.add(context);
        if (s != null)
        {
            if (s == null)
            {
                context = null;
            } else
            {
                context = new Account(s, "com.google");
            }
            builder.zzafe = context;
        }
        client = builder.build();
    }

    protected final transient Object doInBackground(Object aobj[])
    {
        final CountDownLatch latch = new CountDownLatch(1);
        client.registerConnectionCallbacks(new _cls1());
        client.registerConnectionFailedListener(new _cls2());
        client.connect();
        try
        {
            latch.await();
        }
        // Misplaced declaration of an exception variable
        catch (Object aobj[])
        {
            return null;
        }
        if (!client.isConnected())
        {
            return null;
        }
        aobj = ((Object []) (doInBackgroundConnected(aobj)));
        client.disconnect();
        return ((Object) (aobj));
        aobj;
        client.disconnect();
        throw aobj;
    }

    public transient abstract Object doInBackgroundConnected(Object aobj[]);

    private class _cls1
        implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    {

        private final CountDownLatch val$latch;

        public final void onConnected(Bundle bundle)
        {
            latch.countDown();
        }

        public final void onConnectionSuspended(int i)
        {
        }

        _cls1()
        {
            latch = countdownlatch;
            super();
        }
    }


    private class _cls2
        implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    {

        private final CountDownLatch val$latch;

        public final void onConnectionFailed(ConnectionResult connectionresult)
        {
            latch.countDown();
        }

        _cls2()
        {
            latch = countdownlatch;
            super();
        }
    }

}
