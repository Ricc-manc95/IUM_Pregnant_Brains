// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;

// Referenced classes of package com.google.android.gms.common:
//            zzc, GooglePlayServicesUtilLight

public class GoogleSignatureVerifier
{

    private static GoogleSignatureVerifier zzaID;
    public final Context mContext;

    private GoogleSignatureVerifier(Context context)
    {
        mContext = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException("null reference");
        }
        com/google/android/gms/common/GoogleSignatureVerifier;
        JVM INSTR monitorenter ;
        if (zzaID == null)
        {
            zzc.init(context);
            zzaID = new GoogleSignatureVerifier(context);
        }
        com/google/android/gms/common/GoogleSignatureVerifier;
        JVM INSTR monitorexit ;
        return zzaID;
        context;
        com/google/android/gms/common/GoogleSignatureVerifier;
        JVM INSTR monitorexit ;
        throw context;
    }

    static transient zzc.zza zza(PackageInfo packageinfo, zzc.zza azza[])
    {
        int i = 0;
        if (packageinfo.signatures == null)
        {
            return null;
        }
        if (packageinfo.signatures.length != 1)
        {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        packageinfo = new zzc.zzb(packageinfo.signatures[0].toByteArray());
        for (; i < azza.length; i++)
        {
            if (azza[i].equals(packageinfo))
            {
                return azza[i];
            }
        }

        return null;
    }

    private static boolean zzb(PackageInfo packageinfo, boolean flag)
    {
        if (packageinfo.signatures.length != 1)
        {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return false;
        }
        zzc.zzb zzb1 = new zzc.zzb(packageinfo.signatures[0].toByteArray());
        packageinfo = packageinfo.packageName;
        if (flag)
        {
            return zzc.zzb(packageinfo, zzb1);
        } else
        {
            return zzc.zza(packageinfo, zzb1);
        }
    }

    public final boolean isPackageGoogleSigned(PackageManager packagemanager, String s)
    {
        boolean flag = false;
        try
        {
            packagemanager = mContext;
            packagemanager = zzaby.zzaTJ.zzaS(packagemanager).mContext.getPackageManager().getPackageInfo(s, 64);
        }
        // Misplaced declaration of an exception variable
        catch (PackageManager packagemanager)
        {
            return false;
        }
        if (packagemanager != null)
        {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(mContext))
            {
                return zzb(packagemanager, true);
            }
            boolean flag1 = zzb(packagemanager, false);
            flag = flag1;
            if (!flag1)
            {
                flag = flag1;
                if (zzb(packagemanager, true))
                {
                    Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                    return flag1;
                }
            }
        }
        return flag;
    }

    public final boolean zza(PackageInfo packageinfo, boolean flag)
    {
        if (packageinfo != null && packageinfo.signatures != null)
        {
            if (flag)
            {
                packageinfo = zza(packageinfo, zzc.zzd.zzaIw);
            } else
            {
                packageinfo = zza(packageinfo, new zzc.zza[] {
                    zzc.zzd.zzaIw[0]
                });
            }
            if (packageinfo != null)
            {
                return true;
            }
        }
        return false;
    }
}
