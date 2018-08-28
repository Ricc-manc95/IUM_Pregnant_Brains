// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.gcoreclient.common.api;


public interface GcoreGoogleApiClient
{

    public abstract void connect();

    public abstract void disconnect();

    public abstract void registerConnectionCallbacks(GcoreConnectionCallbacks gcoreconnectioncallbacks);

    public abstract void registerConnectionFailedListener(GcoreOnConnectionFailedListener gcoreonconnectionfailedlistener);
}
