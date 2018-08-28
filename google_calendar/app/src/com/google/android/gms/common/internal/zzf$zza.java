// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzf

abstract class zzaPG extends zzaPG
{

    private final int statusCode;
    private final Bundle zzaPG;
    private final zzf zzaPH;

    protected final void zzA(Object obj)
    {
        Object obj1 = null;
        if ((Boolean)obj != null) goto _L2; else goto _L1
_L1:
        zzf.zza(zzaPH, 1, null);
_L4:
        return;
_L2:
        switch (statusCode)
        {
        default:
            zzf.zza(zzaPH, 1, null);
            obj = obj1;
            if (zzaPG != null)
            {
                obj = (PendingIntent)zzaPG.getParcelable("pendingIntent");
            }
            zzn(new ConnectionResult(statusCode, ((PendingIntent) (obj))));
            return;

        case 0: // '\0'
            if (!zzyS())
            {
                zzf.zza(zzaPH, 1, null);
                zzn(new ConnectionResult(8, null));
                return;
            }
            break;

        case 10: // '\n'
            zzf.zza(zzaPH, 1, null);
            throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected abstract void zzn(ConnectionResult connectionresult);

    protected abstract boolean zzyS();

    protected final void zzyT()
    {
    }

    protected (zzf zzf1, int i, Bundle bundle)
    {
        zzaPH = zzf1;
        super(zzf1, Boolean.valueOf(true));
        statusCode = i;
        zzaPG = bundle;
    }
}
