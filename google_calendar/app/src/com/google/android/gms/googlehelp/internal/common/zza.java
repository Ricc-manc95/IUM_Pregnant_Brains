// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.googlehelp.internal.common;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zza
    implements com.google.android.gms.googlehelp.zza
{

    public static final Status zzbwN = new Status(13);

    public zza()
    {
    }

    public final PendingResult zza(GoogleApiClient googleapiclient, Activity activity, Intent intent)
    {
        return googleapiclient.zza(new _cls1(googleapiclient, intent, null, activity));
    }


    private class _cls1 extends zzb
    {
        private class zzb extends zza
        {
            private class zza extends com.google.android.gms.internal.zzyq.zza
            {

                protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
                    throws RemoteException
                {
                    zzb1 = (com.google.android.gms.googlehelp.internal.common.zzb)zzb1;
                    android.content.Context context = ((zzf) (zzb1)).mContext;
                    zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCTNMUPRCCLK6AR3G5TKMST35E9N62R1FCDNMQRBFDONNKUJ47CKLC___0((zzd)zzb1.zzyP());
                }

                protected abstract void zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCTNMUPRCCLK6AR3G5TKMST35E9N62R1FCDNMQRBFDONNKUJ47CKLC___0(zzd zzd1)
                    throws RemoteException;

                public zza(GoogleApiClient googleapiclient)
                {
                    super(zzc.API, googleapiclient);
                }
            }


            protected final Result zzb(Status status)
            {
                return status;
            }

            public zzb(GoogleApiClient googleapiclient)
            {
                super(googleapiclient);
            }
        }


        public final Intent zzbwC;
        private final Bitmap zzbwO;
        public final Activity zzbwP;

        protected final void zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCTNMUPRCCLK6AR3G5TKMST35E9N62R1FCDNMQRBFDONNKUJ47CKLC___0(zzd zzd1)
            throws RemoteException
        {
            try
            {
                class _cls1 extends SimpleGoogleHelpCallbacks
                {

                    private final _cls1 zzbwQ;

                    public final void onGoogleHelpProcessed(GoogleHelp googlehelp)
                    {
                        if (googlehelp.zzbww == null) goto _L2; else goto _L1
_L1:
                        String s;
                        TogglingData togglingdata;
                        Object obj;
                        int i;
                        togglingdata = googlehelp.zzbww;
                        obj = zzbwQ.zzbwP;
                        s = ((Activity) (obj)).getTitle().toString();
                        i = ((Activity) (obj)).getResources().getIdentifier("action_bar", "id", ((Activity) (obj)).getPackageName());
                        if (i == 0) goto _L4; else goto _L3
_L3:
                        obj = (ViewGroup)((Activity) (obj)).findViewById(i);
                        if (obj == null) goto _L4; else goto _L5
_L5:
                        i = 0;
_L10:
                        if (i >= ((ViewGroup) (obj)).getChildCount()) goto _L4; else goto _L6
_L6:
                        android.view.View view = ((ViewGroup) (obj)).getChildAt(i);
                        if (!(view instanceof TextView)) goto _L8; else goto _L7
_L7:
                        s = ((TextView)view).getText().toString();
_L4:
                        togglingdata.zzbxb = s;
_L2:
                        zzbwQ.zzbwC.putExtra("EXTRA_GOOGLE_HELP", googlehelp).putExtra("EXTRA_START_TICK", System.nanoTime());
                        zzbwQ.zzbwP.startActivityForResult(zzbwQ.zzbwC, 123);
                        zzbwQ.zzb(Status.zzaJt);
                        return;
_L8:
                        i++;
                        if (true) goto _L10; else goto _L9
_L9:
                    }

                _cls1()
                {
                    zzbwQ = _cls1.this;
                    super();
                }
                }

                zzd1.zza((GoogleHelp)zzbwC.getParcelableExtra("EXTRA_GOOGLE_HELP"), zzbwO, new _cls1());
                return;
            }
            // Misplaced declaration of an exception variable
            catch (zzd zzd1)
            {
                Log.e("gH_GoogleHelpApiImpl", "Starting help failed!", zzd1);
            }
            zzN(com.google.android.gms.googlehelp.internal.common.zza.zzbwN);
        }

        _cls1(GoogleApiClient googleapiclient, Intent intent, Bitmap bitmap, Activity activity)
        {
            zzbwC = intent;
            zzbwO = bitmap;
            zzbwP = activity;
            super(googleapiclient);
        }
    }

}
