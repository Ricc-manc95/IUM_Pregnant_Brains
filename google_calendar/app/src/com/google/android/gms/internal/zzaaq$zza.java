// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultStore;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzys

final class zzaNu
    implements android.os.athRecipient, zzaNu
{

    private final WeakReference zzaNs;
    private final WeakReference zzaNt;
    private final WeakReference zzaNu;

    private final void zzym()
    {
        Object obj = (zzys)zzaNs.get();
        ResultStore resultstore = (ResultStore)zzaNt.get();
        if (resultstore != null && obj != null)
        {
            ((PendingResult) (obj)).zzwD().intValue();
            resultstore.remove$514IILG_0();
        }
        obj = (IBinder)zzaNu.get();
        if (obj != null)
        {
            ((IBinder) (obj)).unlinkToDeath(this, 0);
        }
    }

    public final void binderDied()
    {
        zzym();
    }

    public final void zzc(zzys zzys1)
    {
        zzym();
    }

    ore(zzys zzys1, ResultStore resultstore, IBinder ibinder)
    {
        zzaNt = new WeakReference(resultstore);
        zzaNs = new WeakReference(zzys1);
        zzaNu = new WeakReference(ibinder);
    }
}
