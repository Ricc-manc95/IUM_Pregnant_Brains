// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.location;

import android.os.Bundle;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class LocationClientListener
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    private static final boolean DEBUG;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/location/LocationClientListener);

    public LocationClientListener()
    {
    }

    public final void onConnected(Bundle bundle)
    {
        LogUtils.v(TAG, "LocationClient connected: %s", new Object[] {
            bundle
        });
    }

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        boolean flag = false;
        LogUtils.e(TAG, "LocationClient failed to connect: %s", new Object[] {
            connectionresult
        });
        int i = connectionresult.zzaEP;
        if (GooglePlayServicesUtil.isUserRecoverableError(i) || 8 == i)
        {
            flag = true;
        }
        if (!flag && DEBUG)
        {
            connectionresult = String.valueOf(connectionresult);
            throw new RuntimeException((new StringBuilder(String.valueOf(connectionresult).length() + 32)).append("Failed to add geofence. status: ").append(connectionresult).toString());
        } else
        {
            return;
        }
    }

    public final void onConnectionSuspended(int i)
    {
        LogUtils.v(TAG, "LocationClient disconnected", new Object[0]);
    }

    static 
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            DEBUG = ((FeatureConfig)featureconfig).dogfood_features();
        }
    }
}
