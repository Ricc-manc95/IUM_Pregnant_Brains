// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.internal.zzzs;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzi

public final class nit> extends zzi
{

    private final Intent val$intent;
    private final int val$requestCode;
    private final zzzs zzaPS;

    public final void zzzg()
    {
        if (val$intent != null)
        {
            zzaPS.startActivityForResult(val$intent, val$requestCode);
        }
    }

    public (int i)
    {
        val$intent = intent1;
        zzaPS = final_zzzs1;
        val$requestCode = i;
        super();
    }
}
