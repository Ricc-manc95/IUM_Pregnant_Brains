// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.drive.DriveId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzahu, zzagn

public final class zzagr extends zzl
{

    private final Bundle zzaZi;
    private volatile DriveId zzaZk;
    private volatile DriveId zzaZl;
    private volatile boolean zzaZm;
    private final Map zzaZn = new HashMap();
    private final Map zzaZo = new HashMap();
    private final Map zzaZp = new HashMap();
    private final Map zzaZq = new HashMap();
    private final String zzahF;

    public zzagr(Context context, Looper looper, zzg zzg1, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, Bundle bundle)
    {
        super(context, looper, 11, zzg1, connectioncallbacks, onconnectionfailedlistener);
        zzaZm = false;
        zzahF = zzg1.zzahF;
        zzaZi = bundle;
        looper = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT");
        looper.setPackage(context.getPackageName());
        context = context.getPackageManager().queryIntentServices(looper, 0);
        switch (context.size())
        {
        default:
            context = String.valueOf(looper.getAction());
            throw new IllegalStateException((new StringBuilder(String.valueOf(context).length() + 72)).append("AndroidManifest.xml can only define one service that handles the ").append(context).append(" action").toString());

        case 1: // '\001'
            context = ((ResolveInfo)context.get(0)).serviceInfo;
            if (!((ServiceInfo) (context)).exported)
            {
                context = String.valueOf(((ServiceInfo) (context)).name);
                throw new IllegalStateException((new StringBuilder(String.valueOf(context).length() + 60)).append("Drive event service ").append(context).append(" must be exported in AndroidManifest.xml").toString());
            }
            // fall through

        case 0: // '\0'
            return;
        }
    }

    public final void disconnect()
    {
        if (isConnected())
        {
            try
            {
                ((zzahu)zzyP()).zza(new zzagn());
            }
            catch (RemoteException remoteexception) { }
        }
        super.disconnect();
        synchronized (zzaZn)
        {
            zzaZn.clear();
        }
        synchronized (zzaZo)
        {
            zzaZo.clear();
        }
        synchronized (zzaZp)
        {
            zzaZp.clear();
        }
        synchronized (zzaZq)
        {
            zzaZq.clear();
        }
        return;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        map;
        JVM INSTR monitorexit ;
        throw exception1;
        exception2;
        map;
        JVM INSTR monitorexit ;
        throw exception2;
        exception3;
        map;
        JVM INSTR monitorexit ;
        throw exception3;
    }

    protected final void zza(int i, IBinder ibinder, Bundle bundle, int j)
    {
        if (bundle != null)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            zzaZk = (DriveId)bundle.getParcelable("com.google.android.gms.drive.root_id");
            zzaZl = (DriveId)bundle.getParcelable("com.google.android.gms.drive.appdata_id");
            zzaZm = true;
        }
        super.zza(i, ibinder, bundle, j);
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.drive.ApiService.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.drive.internal.IDriveService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
        if (iinterface != null && (iinterface instanceof zzahu))
        {
            return (zzahu)iinterface;
        } else
        {
            return new zzahu.zza.zza(ibinder);
        }
    }

    protected final Bundle zzpF()
    {
        String s = super.mContext.getPackageName();
        if (s == null)
        {
            throw new NullPointerException("null reference");
        }
        boolean flag;
        if (!super.zzaKD.zzaPM.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        Bundle bundle = new Bundle();
        if (!s.equals(zzahF))
        {
            bundle.putString("proxy_package_name", zzahF);
        }
        bundle.putAll(zzaZi);
        return bundle;
    }

    public final boolean zzpZ()
    {
        return !super.mContext.getPackageName().equals(zzahF) || !zzx.zze(super.mContext, Process.myUid());
    }

    public final boolean zzyQ()
    {
        return true;
    }
}
