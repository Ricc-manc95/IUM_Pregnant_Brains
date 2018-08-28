// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.gms.common:
//            ConnectionResult, GoogleSignatureVerifier, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException, 
//            GoogleApiAvailabilityLight

public class GooglePlayServicesUtilLight
{

    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0x99dea0;
    private static boolean zzaIA = false;
    public static final AtomicBoolean zzaIB = new AtomicBoolean();
    private static final AtomicBoolean zzaIC = new AtomicBoolean();
    public static boolean zzaIx = false;
    private static boolean zzaIz = false;

    public static String getErrorString(int i)
    {
        return ConnectionResult.getStatusString(i);
    }

    public static Context getRemoteContext(Context context)
    {
        try
        {
            context = context.createPackageContext("com.google.android.gms", 3);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }

    public static Resources getRemoteResource(Context context)
    {
        try
        {
            context = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }

    public static boolean honorsDebugCertificates(Context context)
    {
        if (!zzaIA)
        {
            zzax(context);
        }
        return zzaIz || !"user".equals(Build.TYPE);
    }

    public static int isGooglePlayServicesAvailable(Context context)
    {
        PackageManager packagemanager;
        PackageInfo packageinfo1;
        boolean flag;
        packagemanager = context.getPackageManager();
        try
        {
            context.getResources().getString(0x7f130117);
        }
        catch (Throwable throwable)
        {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName()) && !zzaIC.get())
        {
            int i = zzz.zzaH(context);
            if (i == 0)
            {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
            if (i != GOOGLE_PLAY_SERVICES_VERSION_CODE)
            {
                int l = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                context = String.valueOf("com.google.android.gms.version");
                throw new IllegalStateException((new StringBuilder(String.valueOf(context).length() + 290)).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(l).append(" but found ").append(i).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append(context).append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
        PackageInfo packageinfo;
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && zzh.zzaL(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_286;
        }
        if (zzh.zzaTk == null)
        {
            zzh.zzaTk = Boolean.valueOf(context.getPackageManager().hasSystemFeature("android.hardware.type.iot"));
        }
        if (zzh.zzaTk.booleanValue())
        {
            break MISSING_BLOCK_LABEL_286;
        }
        flag = true;
_L1:
        packageinfo = null;
        if (flag)
        {
            try
            {
                packageinfo = packagemanager.getPackageInfo("com.android.vending", 8256);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try
        {
            packageinfo1 = packagemanager.getPackageInfo("com.google.android.gms", 64);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
        GoogleSignatureVerifier.getInstance(context);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_341;
        }
        context = GoogleSignatureVerifier.zza(packageinfo, zzc.zzd.zzaIw);
        if (context == null)
        {
            Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
            return 9;
        }
        break MISSING_BLOCK_LABEL_315;
        flag = false;
          goto _L1
        if (GoogleSignatureVerifier.zza(packageinfo1, new zzc.zza[] {
    context
}) == null)
        {
            Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
            return 9;
        }
        break MISSING_BLOCK_LABEL_362;
        if (GoogleSignatureVerifier.zza(packageinfo1, zzc.zzd.zzaIw) == null)
        {
            Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
            return 9;
        }
        int j = GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000;
        if (packageinfo1.versionCode / 1000 < j)
        {
            int k = GOOGLE_PLAY_SERVICES_VERSION_CODE;
            int i1 = packageinfo1.versionCode;
            Log.w("GooglePlayServicesUtil", (new StringBuilder(77)).append("Google Play services out of date.  Requires ").append(k).append(" but found ").append(i1).toString());
            return 2;
        }
        ApplicationInfo applicationinfo = packageinfo1.applicationInfo;
        context = applicationinfo;
        if (applicationinfo == null)
        {
            try
            {
                context = packagemanager.getApplicationInfo("com.google.android.gms", 0);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", context);
                return 1;
            }
        }
        return ((ApplicationInfo) (context)).enabled ? 0 : 3;
    }

    public static boolean isPlayServicesPossiblyUpdating(Context context, int i)
    {
        if (i == 18)
        {
            return true;
        }
        if (i == 1)
        {
            return zzt(context, "com.google.android.gms");
        } else
        {
            return false;
        }
    }

    public static boolean isUserRecoverableError(int i)
    {
        switch (i)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 9: // '\t'
            return true;
        }
    }

    public static void zzad(Context context)
        throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
        int i = GoogleApiAvailabilityLight.zzaIo.isGooglePlayServicesAvailable(context);
        if (i != 0)
        {
            context = GoogleApiAvailabilityLight.zzaIo.getErrorResolutionIntent(context, i, "e");
            Log.e("GooglePlayServicesUtil", (new StringBuilder(57)).append("GooglePlayServices not available due to error ").append(i).toString());
            if (context == null)
            {
                throw new GooglePlayServicesNotAvailableException(i);
            } else
            {
                throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", context);
            }
        } else
        {
            return;
        }
    }

    public static void zzas(Context context)
    {
        if (!zzaIB.getAndSet(true)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        try
        {
            context = (NotificationManager)context.getSystemService("notification");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return;
        }
        if (context == null) goto _L1; else goto _L3
_L3:
        context.cancel(10436);
        return;
    }

    private static void zzax(Context context)
    {
        PackageInfo packageinfo = zzaby.zzaTJ.zzaS(context).mContext.getPackageManager().getPackageInfo("com.google.android.gms", 64);
        if (packageinfo == null) goto _L2; else goto _L1
_L1:
        GoogleSignatureVerifier.getInstance(context);
        if (GoogleSignatureVerifier.zza(packageinfo, new zzc.zza[] {
            zzc.zzd.zzaIw[1]
        }) == null) goto _L2; else goto _L3
_L3:
        zzaIz = true;
_L5:
        zzaIA = true;
        return;
_L2:
        zzaIz = false;
        if (true) goto _L5; else goto _L4
_L4:
        context;
        Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", context);
        zzaIA = true;
        return;
        context;
        zzaIA = true;
        throw context;
    }

    static boolean zzt(Context context, String s)
    {
        boolean flag1 = s.equals("com.google.android.gms");
        for (Iterator iterator = context.getPackageManager().getPackageInstaller().getAllSessions().iterator(); iterator.hasNext();)
        {
            if (s.equals(((android.content.pm.PackageInstaller.SessionInfo)iterator.next()).getAppPackageName()))
            {
                return true;
            }
        }

        PackageManager packagemanager = context.getPackageManager();
        boolean flag;
        try
        {
            s = packagemanager.getApplicationInfo(s, 8192);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return false;
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        return ((ApplicationInfo) (s)).enabled;
        if (!((ApplicationInfo) (s)).enabled) goto _L2; else goto _L1
_L1:
        context = ((UserManager)context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
        if (context == null) goto _L4; else goto _L3
_L3:
        flag1 = "true".equals(context.getString("restricted_profile"));
        if (!flag1) goto _L4; else goto _L5
_L5:
        flag = true;
_L6:
        if (!flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L6; else goto _L2
_L2:
        return false;
    }

}
