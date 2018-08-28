// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApiActivity;

// Referenced classes of package com.google.android.gms.internal:
//            zzyr, zzzr, zzzs

final class zzaJV
    implements Runnable
{

    public final zzyr zzaJV;

    public final void run()
    {
        if (!zzaJV.mStarted)
        {
            return;
        }
        ConnectionResult connectionresult = zzaJV.zzaJS;
        boolean flag;
        if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            zzaJV.zzaMD.startActivityForResult(GoogleApiActivity.zzb(((zzzr) (zzaJV)).zzaMD.zzyb(), zzaJV.zzaJS.mPendingIntent, zzaJV.zzaJT, false), 1);
            return;
        }
        if (zzaJV.zzaJj.isUserResolvableError(zzaJV.zzaJS.zzaEP))
        {
            zzaJV.zzaJj.zza(((zzzr) (zzaJV)).zzaMD.zzyb(), zzaJV.zzaMD, zzaJV.zzaJS.zzaEP, 2, zzaJV);
            return;
        }
        if (zzaJV.zzaJS.zzaEP == 18)
        {
            Dialog dialog = zzaJV.zzaJj.zza(((zzzr) (zzaJV)).zzaMD.zzyb(), zzaJV);
            class _cls1 extends zzzm.zza
            {

                private final Dialog zzaJW;
                private final zzyr.zza zzaJX;

                public final void zzwN()
                {
                    zzyr zzyr1 = zzaJX.zzaJV;
                    zzyr1.zzaJT = -1;
                    zzyr1.zzaJR = false;
                    zzyr1.zzaJS = null;
                    zzyr1.zzwI();
                    if (zzaJW.isShowing())
                    {
                        zzaJW.dismiss();
                    }
                }

            _cls1(Dialog dialog)
            {
                zzaJX = zzyr.zza.this;
                zzaJW = dialog;
                super();
            }
            }

            zzaJV.zzaJj.zza(((zzzr) (zzaJV)).zzaMD.zzyb().getApplicationContext(), new _cls1(dialog));
            return;
        } else
        {
            zzaJV.zza(zzaJV.zzaJS, zzaJV.zzaJT);
            return;
        }
    }

    _cls1(zzyr zzyr1)
    {
        zzaJV = zzyr1;
        super();
    }
}
