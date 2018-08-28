// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.internal.zzzm;
import com.google.android.gms.internal.zzzs;
import java.util.concurrent.atomic.AtomicBoolean;

// Referenced classes of package com.google.android.gms.common:
//            GoogleApiAvailabilityLight, SupportErrorDialogFragment, ErrorDialogFragment, GooglePlayServicesUtilLight

public final class GoogleApiAvailability extends GoogleApiAvailabilityLight
{

    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final GoogleApiAvailability zzaIm = new GoogleApiAvailability();

    GoogleApiAvailability()
    {
    }

    public static Dialog zza(Context context, int i, zzi zzi, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        android.app.AlertDialog.Builder builder = null;
        if (i == 0)
        {
            return null;
        }
        Object obj = new TypedValue();
        context.getTheme().resolveAttribute(0x1010309, ((TypedValue) (obj)), true);
        if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(((TypedValue) (obj)).resourceId)))
        {
            builder = new android.app.AlertDialog.Builder(context, 5);
        }
        obj = builder;
        if (builder == null)
        {
            obj = new android.app.AlertDialog.Builder(context);
        }
        ((android.app.AlertDialog.Builder) (obj)).setMessage(zzh.zzh(context, i));
        if (oncancellistener != null)
        {
            ((android.app.AlertDialog.Builder) (obj)).setOnCancelListener(oncancellistener);
        }
        oncancellistener = zzh.zzj(context, i);
        if (oncancellistener != null)
        {
            ((android.app.AlertDialog.Builder) (obj)).setPositiveButton(oncancellistener, zzi);
        }
        context = zzh.zzf(context, i);
        if (context != null)
        {
            ((android.app.AlertDialog.Builder) (obj)).setTitle(context);
        }
        return ((android.app.AlertDialog.Builder) (obj)).create();
    }

    static void zza(Activity activity, Dialog dialog, String s, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        if (activity instanceof FragmentActivity)
        {
            activity = ((FragmentActivity)activity).mFragments.mHost.mFragmentManager;
            SupportErrorDialogFragment supporterrordialogfragment = new SupportErrorDialogFragment();
            if (dialog == null)
            {
                throw new NullPointerException(String.valueOf("Cannot display null dialog"));
            }
            dialog = (Dialog)dialog;
            dialog.setOnCancelListener(null);
            dialog.setOnDismissListener(null);
            supporterrordialogfragment.mDialog = dialog;
            if (oncancellistener != null)
            {
                supporterrordialogfragment.zzaIl = oncancellistener;
            }
            supporterrordialogfragment.show(activity, s);
            return;
        }
        activity = activity.getFragmentManager();
        ErrorDialogFragment errordialogfragment = new ErrorDialogFragment();
        if (dialog == null)
        {
            throw new NullPointerException(String.valueOf("Cannot display null dialog"));
        }
        dialog = (Dialog)dialog;
        dialog.setOnCancelListener(null);
        dialog.setOnDismissListener(null);
        errordialogfragment.mDialog = dialog;
        if (oncancellistener != null)
        {
            errordialogfragment.zzaIl = oncancellistener;
        }
        errordialogfragment.show(activity, s);
    }

    public final Dialog getErrorDialog(Activity activity, int i, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        return zza(activity, i, new com.google.android.gms.common.internal.zzi._cls1(getErrorResolutionIntent(activity, i, "d"), activity, j), oncancellistener);
    }

    public final boolean showErrorDialogFragment(Activity activity, int i, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        Dialog dialog = getErrorDialog(activity, i, j, oncancellistener);
        if (dialog == null)
        {
            return false;
        } else
        {
            zza(activity, dialog, "GooglePlayServicesErrorDialog", oncancellistener);
            return true;
        }
    }

    public final void showErrorNotification(Context context, int i)
    {
        zza(context, i, ((String) (null)), getErrorResolutionPendingIntent(context, i, 0, "n"));
    }

    public final Dialog zza(Activity activity, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        Object obj = new ProgressBar(activity, null, 0x101007a);
        ((ProgressBar) (obj)).setIndeterminate(true);
        ((ProgressBar) (obj)).setVisibility(0);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setView(((android.view.View) (obj)));
        builder.setMessage(zzh.zzh(activity, 18));
        builder.setPositiveButton("", null);
        obj = builder.create();
        zza(activity, ((Dialog) (obj)), "GooglePlayServicesUpdatingDialog", oncancellistener);
        return ((Dialog) (obj));
    }

    public final zzzm zza(Context context, com.google.android.gms.internal.zzzm.zza zza1)
    {
        Object obj = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        ((IntentFilter) (obj)).addDataScheme("package");
        zzzm zzzm1 = new zzzm(zza1);
        context.registerReceiver(zzzm1, ((IntentFilter) (obj)));
        zzzm1.mContext = context;
        obj = zzzm1;
        if (!GooglePlayServicesUtilLight.zzt(context, "com.google.android.gms"))
        {
            zza1.zzwN();
            zzzm1.unregister();
            obj = null;
        }
        return ((zzzm) (obj));
    }

    public final void zza(Context context, int i, String s, PendingIntent pendingintent)
    {
        if (i != 18) goto _L2; else goto _L1
_L1:
        (new zza(context)).sendEmptyMessageDelayed(1, 0x1d4c0L);
_L4:
        return;
_L2:
        if (pendingintent != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i == 6)
        {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        String s2 = zzh.zzg(context, i);
        String s1 = zzh.zzi(context, i);
        Object obj = context.getResources();
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && com.google.android.gms.common.util.zzh.zzaL(context))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            pendingintent = (new android.app.Notification.Builder(context)).setSmallIcon(context.getApplicationInfo().icon).setPriority(2).setAutoCancel(true).setContentTitle(s2).setStyle((new android.app.Notification.BigTextStyle()).bigText(s1)).addAction(0x7f0200dd, ((Resources) (obj)).getString(0x7f13011e), pendingintent).build();
        } else
        {
            android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context);
            builder.mNotification.icon = 0x108008a;
            obj = builder.setTicker(((Resources) (obj)).getString(0x7f130116));
            long l = System.currentTimeMillis();
            ((android.support.v4.app.NotificationCompat.Builder) (obj)).mNotification.when = l;
            obj = ((android.support.v4.app.NotificationCompat.Builder) (obj)).setAutoCancel(true);
            obj.mContentIntent = pendingintent;
            pendingintent = ((android.support.v4.app.NotificationCompat.Builder) (obj)).setContentTitle(s2).setContentText(s1);
            pendingintent.mLocalOnly = true;
            pendingintent = pendingintent.setStyle((new android.support.v4.app.NotificationCompat.BigTextStyle()).bigText(s1)).build();
        }
        switch (i)
        {
        default:
            i = 39789;
            break;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            break MISSING_BLOCK_LABEL_342;
        }
_L5:
        context = (NotificationManager)context.getSystemService("notification");
        if (s == null)
        {
            context.notify(i, pendingintent);
            return;
        } else
        {
            context.notify(s, i, pendingintent);
            return;
        }
        GooglePlayServicesUtilLight.zzaIB.set(false);
        i = 10436;
          goto _L5
    }

    public final boolean zza(Activity activity, zzzs zzzs, int i, int j, android.content.DialogInterface.OnCancelListener oncancellistener)
    {
        zzzs = zza(((Context) (activity)), i, ((zzi) (new com.google.android.gms.common.internal.zzi._cls3(getErrorResolutionIntent(activity, i, "d"), zzzs, 2))), oncancellistener);
        if (zzzs == null)
        {
            return false;
        } else
        {
            zza(activity, ((Dialog) (zzzs)), "GooglePlayServicesErrorDialog", oncancellistener);
            return true;
        }
    }

    static 
    {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    private class zza extends Handler
    {

        private final GoogleApiAvailability zzaIn;
        private final Context zzws;

        public final void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 57;
               goto _L1 _L2
_L1:
            int i = message.what;
            Log.w("GoogleApiAvailability", (new StringBuilder(50)).append("Don't know how to handle this message: ").append(i).toString());
_L4:
            return;
_L2:
            int j = zzaIn.isGooglePlayServicesAvailable(zzws);
            if (zzaIn.isUserResolvableError(j))
            {
                zzaIn.showErrorNotification(zzws, j);
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public zza(Context context)
        {
            zzaIn = GoogleApiAvailability.this;
            if (Looper.myLooper() == null)
            {
                googleapiavailability = Looper.getMainLooper();
            } else
            {
                googleapiavailability = Looper.myLooper();
            }
            super(GoogleApiAvailability.this);
            zzws = context.getApplicationContext();
        }
    }

}
