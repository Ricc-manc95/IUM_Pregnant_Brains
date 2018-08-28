// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.annotation;

import android.content.Context;
import android.location.Location;
import com.google.android.calendar.timely.location.LocationClientListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;

final class DeviceLocation
{

    static final Location lambda$getLastLocation$0$DeviceLocation(Context context)
        throws Exception
    {
        LocationClientListener locationclientlistener = new LocationClientListener();
        context = (new com.google.android.gms.common.api.GoogleApiClient.Builder(context)).addApi(LocationServices.API);
        if (locationclientlistener == null)
        {
            throw new NullPointerException(String.valueOf("Listener must not be null"));
        }
        ((com.google.android.gms.common.api.GoogleApiClient.Builder) (context)).zzaJl.add(locationclientlistener);
        if (locationclientlistener == null)
        {
            throw new NullPointerException(String.valueOf("Listener must not be null"));
        }
        ((com.google.android.gms.common.api.GoogleApiClient.Builder) (context)).zzaJm.add(locationclientlistener);
        context = context.build();
        Location location;
        context.blockingConnect();
        location = LocationServices.FusedLocationApi.getLastLocation(context);
        context.disconnect();
        return location;
        Exception exception;
        exception;
        context.disconnect();
        throw exception;
    }
}
