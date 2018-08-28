// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

public final class zzaPL extends zzaPL
{

    private final zzf zzaPH;
    private final IBinder zzaPL;

    protected final void zzn(ConnectionResult connectionresult)
    {
        if (zzf.zzd(zzaPH) != null)
        {
            zzf.zzd(zzaPH).onConnectionFailed(connectionresult);
        }
        zzf zzf1 = zzaPH;
        zzf1.zzaPr = connectionresult.zzaEP;
        zzf1.zzaPs = System.currentTimeMillis();
    }

    protected final boolean zzyS()
    {
        String s;
        try
        {
            s = zzaPL.getInterfaceDescriptor();
        }
        catch (RemoteException remoteexception)
        {
            Log.w("GmsClient", "service probably died");
            return false;
        }
        if (!zzaPH.zzeE().equals(s))
        {
            String s1 = String.valueOf(zzaPH.zzeE());
            Log.e("GmsClient", (new StringBuilder(String.valueOf(s1).length() + 34 + String.valueOf(s).length())).append("service descriptor mismatch: ").append(s1).append(" vs. ").append(s).toString());
        } else
        {
            android.os.IInterface iinterface = zzaPH.zzi(zzaPL);
            if (iinterface != null && zzf.zza(zzaPH, 2, 3, iinterface))
            {
                zzf zzf1 = zzaPH;
                if (zzf.zzb(zzaPH) != null)
                {
                    zzf.zzb(zzaPH).onConnected(null);
                }
                return true;
            }
        }
        return false;
    }

    public (zzf zzf1, int i, IBinder ibinder, Bundle bundle)
    {
        zzaPH = zzf1;
        super(zzf1, i, bundle);
        zzaPL = ibinder;
    }
}
