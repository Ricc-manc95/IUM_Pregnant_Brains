// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;

public final class zzx
{

    public static boolean zze(Context context, int i)
    {
        if (zzaby.zzaTJ.zzaS(context).zzg(i, "com.google.android.gms"))
        {
            Object obj = context.getPackageManager();
            GoogleSignatureVerifier googlesignatureverifier;
            try
            {
                obj = ((PackageManager) (obj)).getPackageInfo("com.google.android.gms", 64);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                return false;
            }
            googlesignatureverifier = GoogleSignatureVerifier.getInstance(context);
            context.getPackageManager();
            if (obj != null)
            {
                if (googlesignatureverifier.zza(((android.content.pm.PackageInfo) (obj)), false))
                {
                    return true;
                }
                if (googlesignatureverifier.zza(((android.content.pm.PackageInfo) (obj)), true))
                {
                    if (GooglePlayServicesUtilLight.honorsDebugCertificates(googlesignatureverifier.mContext))
                    {
                        return true;
                    } else
                    {
                        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
