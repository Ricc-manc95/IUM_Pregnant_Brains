// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            zzzk

final class zzaMt
    implements Runnable
{

    private final ConnectionResult zzaMt;
    private final ult zzaMv;

    public final void run()
    {
        boolean flag;
        if (zzaMt.zzaEP == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            zzaMv.aMu = true;
            if (zzaMv.aKB.Z())
            {
                ult ult = zzaMv;
                if (ult.aMu && ult.aLh != null)
                {
                    ult.aKB.(ult.aLh, ult.alx);
                }
                return;
            } else
            {
                zzaMv.aKB.(null, Collections.emptySet());
                return;
            }
        } else
        {
            ((za)zzzk.zzj(zzaMv.aMr).get(zzaMv.aIT)).ConnectionFailed(zzaMt);
            return;
        }
    }

    ult(ult ult, ConnectionResult connectionresult)
    {
        zzaMv = ult;
        zzaMt = connectionresult;
        super();
    }
}
