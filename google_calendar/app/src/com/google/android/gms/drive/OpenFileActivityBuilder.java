// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzagr;
import com.google.android.gms.internal.zzahu;
import com.google.android.gms.internal.zzajj;

// Referenced classes of package com.google.android.gms.drive:
//            Drive

public final class OpenFileActivityBuilder
{

    private String zzaXi[];

    public OpenFileActivityBuilder()
    {
    }

    public final IntentSender build(GoogleApiClient googleapiclient)
    {
        if (!googleapiclient.isConnected())
        {
            throw new IllegalStateException(String.valueOf("Client must be connected"));
        }
        if (zzaXi == null)
        {
            zzaXi = new String[0];
        }
        try
        {
            googleapiclient = ((zzahu)((zzagr)googleapiclient.zza(Drive.zzahs)).zzyP()).zza(new zzajj(null, zzaXi, null, null));
        }
        // Misplaced declaration of an exception variable
        catch (GoogleApiClient googleapiclient)
        {
            throw new RuntimeException("Unable to connect Drive Play Service", googleapiclient);
        }
        return googleapiclient;
    }
}
