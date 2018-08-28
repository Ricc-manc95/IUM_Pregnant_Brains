// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api.impl;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient;
import com.google.android.libraries.gcoreclient.common.api.support.GcoreWrapper;
import com.google.android.libraries.gcoreclient.common.api.support.GoogleApiClientWrapper;

public class BaseGcoreGoogleApiClientImpl
    implements GcoreGoogleApiClient, GoogleApiClientWrapper
{

    private GoogleApiClient client;
    private GcoreWrapper wrapper;

    protected BaseGcoreGoogleApiClientImpl(Context context, GoogleApiClient googleapiclient, GcoreWrapper gcorewrapper)
    {
        client = googleapiclient;
        wrapper = gcorewrapper;
    }

    public final void connect()
    {
        client.connect();
    }

    public final void disconnect()
    {
        client.disconnect();
    }

    public final void registerConnectionCallbacks(com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreConnectionCallbacks gcoreconnectioncallbacks)
    {
        client.registerConnectionCallbacks(wrapper.unwrap(gcoreconnectioncallbacks));
    }

    public final void registerConnectionFailedListener(com.google.android.libraries.gcoreclient.common.api.GcoreGoogleApiClient.GcoreOnConnectionFailedListener gcoreonconnectionfailedlistener)
    {
        client.registerConnectionFailedListener(wrapper.unwrap(gcoreonconnectionfailedlistener));
    }

    public final GoogleApiClient unwrap()
    {
        return client;
    }
}
