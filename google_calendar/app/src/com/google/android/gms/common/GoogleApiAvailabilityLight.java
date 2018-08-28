// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;

// Referenced classes of package com.google.android.gms.common:
//            GooglePlayServicesUtilLight, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException

public class GoogleApiAvailabilityLight
{

    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final GoogleApiAvailabilityLight zzaIo = new GoogleApiAvailabilityLight();

    GoogleApiAvailabilityLight()
    {
    }

    public static void zzar(Context context)
        throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
        GooglePlayServicesUtilLight.zzad(context);
    }

    public static void zzas(Context context)
    {
        GooglePlayServicesUtilLight.zzas(context);
    }

    private static String zzu(Context context, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("gcore_");
        stringbuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        stringbuilder.append("-");
        if (!TextUtils.isEmpty(s))
        {
            stringbuilder.append(s);
        }
        stringbuilder.append("-");
        if (context != null)
        {
            stringbuilder.append(context.getPackageName());
        }
        stringbuilder.append("-");
        if (context != null)
        {
            try
            {
                s = zzaby.zzaTJ.zzaS(context);
                context = context.getPackageName();
                stringbuilder.append(((zzabx) (s)).mContext.getPackageManager().getPackageInfo(context, 0).versionCode);
            }
            // Misplaced declaration of an exception variable
            catch (Context context) { }
        }
        return stringbuilder.toString();
    }

    public Intent getErrorResolutionIntent(int i)
    {
        return getErrorResolutionIntent(null, i, null);
    }

    public Intent getErrorResolutionIntent(Context context, int i, String s)
    {
        boolean flag = true;
        switch (i)
        {
        default:
            return null;

        case 1: // '\001'
        case 2: // '\002'
            if (context != null)
            {
                if (android.os.Build.VERSION.SDK_INT >= 24)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0 && zzh.zzaL(context))
                {
                    i = ((flag) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    return zzp.zzzo();
                }
            }
            return zzp.zzJ("com.google.android.gms", zzu(context, s));

        case 3: // '\003'
            return zzp.zzcX("com.google.android.gms");
        }
    }

    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int j)
    {
        return getErrorResolutionPendingIntent(context, i, j, null);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int j, String s)
    {
        s = getErrorResolutionIntent(context, i, s);
        if (s == null)
        {
            return null;
        } else
        {
            return PendingIntent.getActivity(context, j, s, 0x10000000);
        }
    }

    public String getErrorString(int i)
    {
        return GooglePlayServicesUtilLight.getErrorString(i);
    }

    public int isGooglePlayServicesAvailable(Context context)
    {
        int j = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
        int i = j;
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, j))
        {
            i = 18;
        }
        return i;
    }

    public boolean isPlayServicesPossiblyUpdating(Context context, int i)
    {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i);
    }

    public boolean isUserResolvableError(int i)
    {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i);
    }

    static 
    {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
}
