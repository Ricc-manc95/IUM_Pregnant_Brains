// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.util.zzh;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class zzaon extends zzl
{

    protected zzaon(Context context, Looper looper, int i, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, zzg zzg)
    {
        super(context, looper, i, zzg, connectioncallbacks, onconnectionfailedlistener);
    }

    protected final Set zzc(Set set)
    {
        HashSet hashset = new HashSet(set.size());
        Iterator iterator = set.iterator();
        do
        {
            if (iterator.hasNext())
            {
                Scope scope1 = (Scope)iterator.next();
                Scope scope;
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.activity.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.location.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.location.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.body.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.body.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.nutrition.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.blood_pressure.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.blood_pressure.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.blood_glucose.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.blood_glucose.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.oxygen_saturation.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.oxygen_saturation.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.body_temperature.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.body_temperature.write");
                } else
                if (scope1.equals(new Scope("https://www.googleapis.com/auth/fitness.reproductive_health.read")))
                {
                    scope = new Scope("https://www.googleapis.com/auth/fitness.reproductive_health.write");
                } else
                {
                    scope = scope1;
                }
                if (scope.equals(scope1) || !set.contains(scope))
                {
                    hashset.add(scope1);
                }
            } else
            {
                return hashset;
            }
        } while (true);
    }

    public final boolean zzpZ()
    {
        return !zzh.zzaL(super.mContext);
    }

    public final boolean zzyQ()
    {
        return true;
    }
}
