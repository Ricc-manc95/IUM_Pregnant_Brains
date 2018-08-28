// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location.internal;

import android.location.Location;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;

// Referenced classes of package com.google.android.gms.location.internal:
//            zzn, zzm

public final class zze
    implements FusedLocationProviderApi
{

    public zze()
    {
    }

    public final Location getLastLocation(GoogleApiClient googleapiclient)
    {
        googleapiclient = LocationServices.zzr(googleapiclient);
        try
        {
            googleapiclient = ((zzn) (googleapiclient)).zzbCo.getLastLocation();
        }
        // Misplaced declaration of an exception variable
        catch (GoogleApiClient googleapiclient)
        {
            return null;
        }
        return googleapiclient;
    }
}
