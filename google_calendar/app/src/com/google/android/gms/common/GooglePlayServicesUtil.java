// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

// Referenced classes of package com.google.android.gms.common:
//            GooglePlayServicesUtilLight, GoogleApiAvailability

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight
{

    public static Dialog getErrorDialog(int i, Activity activity, int j)
    {
        boolean flag = true;
        if (i != 18)
        {
            if (i == 1)
            {
                flag = GooglePlayServicesUtilLight.zzt(activity, "com.google.android.gms");
            } else
            {
                flag = false;
            }
        }
        if (flag)
        {
            i = 18;
        }
        return GoogleApiAvailability.zzaIm.getErrorDialog(activity, i, 0, null);
    }

    public static Resources getRemoteResource(Context context)
    {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    public static int isGooglePlayServicesAvailable(Context context)
    {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }

    public static boolean isUserRecoverableError(int i)
    {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i);
    }

    public static boolean showErrorDialogFragment(int i, Activity activity, int j)
    {
        boolean flag = true;
        if (i != 18)
        {
            if (i == 1)
            {
                flag = GooglePlayServicesUtilLight.zzt(activity, "com.google.android.gms");
            } else
            {
                flag = false;
            }
        }
        if (flag)
        {
            i = 18;
        }
        return GoogleApiAvailability.zzaIm.showErrorDialogFragment(activity, i, 0, null);
    }

    public static void showErrorNotification(int i, Context context)
    {
        GoogleApiAvailability googleapiavailability;
label0:
        {
            boolean flag1 = false;
            googleapiavailability = GoogleApiAvailability.zzaIm;
            boolean flag;
            if (i == 18)
            {
                flag = true;
            } else
            if (i == 1)
            {
                flag = GooglePlayServicesUtilLight.zzt(context, "com.google.android.gms");
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = flag1;
                if (i == 9)
                {
                    flag = GooglePlayServicesUtilLight.zzt(context, "com.android.vending");
                }
                if (!flag)
                {
                    break label0;
                }
            }
            (new GoogleApiAvailability.zza(googleapiavailability, context)).sendEmptyMessageDelayed(1, 0x1d4c0L);
            return;
        }
        googleapiavailability.showErrorNotification(context, i);
    }
}
