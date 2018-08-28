// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Map;

// Referenced classes of package com.google.android.gms.internal:
//            zzyl, zzys, zzyy

public final class zzaJA extends zzyl
{

    private final  zzaJA;

    public final void zzK(Status status)
    {
        boolean flag1 = true;
        zzaJA zzaja = zzaJA;
        boolean flag;
        if (status.zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
        } else
        {
            zzaja.zzb(zzaja.zzb(status));
            return;
        }
    }

    public final void zza(zzyy zzyy1, boolean flag)
    {
        a a = zzaJA;
        zzyy1.zzaKN.put(a, Boolean.valueOf(flag));
        a.zza(new nit>(zzyy1, a));
    }

    public final void zza(nit> nit>)
        throws DeadObjectException
    {
        boolean flag3 = true;
        boolean flag2 = true;
          = zzaJA;
        nit> = nit>.zzaKB;
        try
        {
            .zza(nit>);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (nit> nit>)
        {
            Status status = new Status(8, nit>.getLocalizedMessage(), null);
            boolean flag;
            if (status.zzaEP <= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                flag = flag2;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
            } else
            {
                .zzb(.zzb(status));
                throw nit>;
            }
        }
        // Misplaced declaration of an exception variable
        catch (nit> nit>)
        {
            nit> = new Status(8, nit>.getLocalizedMessage(), null);
        }
        boolean flag1;
        if (((Status) (nit>)).zzaEP <= 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            flag1 = flag3;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
        } else
        {
            .zzb(.zzb(nit>));
            return;
        }
    }

    public Result(int i, Result result)
    {
        super(i);
        zzaJA = result;
    }
}
