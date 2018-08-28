// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.internal.zzr;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdm, zzbdn, zzbdu, zzbdy, 
//            zzbea, zzbdt

public final class zzbdx extends zzl
    implements zzbdm
{

    private final zzg zzaKD;
    private Integer zzaPP;
    private final Bundle zzcmg;
    private final boolean zzcmr;

    public zzbdx(Context context, Looper looper, boolean flag, zzg zzg1, Bundle bundle, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        super(context, looper, 44, zzg1, connectioncallbacks, onconnectionfailedlistener);
        zzcmr = flag;
        zzaKD = zzg1;
        zzcmg = bundle;
        zzaPP = zzg1.zzaPP;
    }

    public zzbdx(Context context, Looper looper, boolean flag, zzg zzg1, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        zzbdn zzbdn1 = zzg1.zzaPO;
        Integer integer = zzg1.zzaPP;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzg1.zzafe);
        if (integer != null)
        {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", integer.intValue());
        }
        if (zzbdn1 != null)
        {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzbdn1.zzcmi);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzbdn1.zzals);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzbdn1.zzalv);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzbdn1.zzalu);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzbdn1.zzalw);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzbdn1.zzcmj);
            if (zzbdn1.zzcmk != null)
            {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzbdn1.zzcmk.longValue());
            }
            if (zzbdn1.zzcml != null)
            {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzbdn1.zzcml.longValue());
            }
        }
        this(context, looper, true, zzg1, bundle, connectioncallbacks, onconnectionfailedlistener);
    }

    public final void connect()
    {
        zza(new com.google.android.gms.common.internal.zzf.zzi(this));
    }

    public final void zzUk()
    {
        try
        {
            ((zzbdu)zzyP()).zzvb(zzaPP.intValue());
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public final void zza(zzr zzr, boolean flag)
    {
        try
        {
            ((zzbdu)zzyP()).zza(zzr, zzaPP.intValue(), flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (zzr zzr)
        {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public final void zza(zzbdt zzbdt1)
    {
        if (zzbdt1 == null)
        {
            throw new NullPointerException(String.valueOf("Expecting a valid ISignInCallbacks"));
        }
        Object obj = zzaKD;
        if (((zzg) (obj)).zzafe == null) goto _L2; else goto _L1
_L1:
        obj = ((zzg) (obj)).zzafe;
_L4:
        Object obj1 = null;
        try
        {
            if ("<<default account>>".equals(((Account) (obj)).name))
            {
                obj1 = com.google.android.gms.auth.api.signin.internal.zzl.zzae(super.mContext);
                obj1 = ((com.google.android.gms.auth.api.signin.internal.zzl) (obj1)).zzbY(((com.google.android.gms.auth.api.signin.internal.zzl) (obj1)).zzca("defaultGoogleSignInAccount"));
            }
            obj = new zzad(((Account) (obj)), zzaPP.intValue(), ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) (obj1)));
            ((zzbdu)zzyP()).zza(new zzbdy(((zzad) (obj))), zzbdt1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try
            {
                zzbdt1.zzb(new zzbea(8));
                return;
            }
            // Misplaced declaration of an exception variable
            catch (zzbdt zzbdt1)
            {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", remoteexception);
            }
            return;
        }
_L2:
        obj = new Account("<<default account>>", "com.google");
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected final String zzeD()
    {
        return "com.google.android.gms.signin.service.START";
    }

    protected final String zzeE()
    {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    protected final IInterface zzi(IBinder ibinder)
    {
        if (ibinder == null)
        {
            return null;
        }
        IInterface iinterface = ibinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (iinterface != null && (iinterface instanceof zzbdu))
        {
            return (zzbdu)iinterface;
        } else
        {
            return new zzbdu.zza.zza(ibinder);
        }
    }

    protected final Bundle zzpF()
    {
        String s = zzaKD.zzahF;
        if (!super.mContext.getPackageName().equals(s))
        {
            zzcmg.putString("com.google.android.gms.signin.internal.realClientPackageName", zzaKD.zzahF);
        }
        return zzcmg;
    }

    public final boolean zzpZ()
    {
        return zzcmr;
    }
}
