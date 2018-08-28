// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.Dialog;

// Referenced classes of package com.google.android.gms.internal:
//            zzyr

final class nit> extends nit>
{

    private final Dialog zzaJW;
    private final zzaJW zzaJX;

    public final void zzwN()
    {
        zzyr zzyr1 = zzaJX.aJV;
        zzyr1.zzaJT = -1;
        zzyr1.zzaJR = false;
        zzyr1.zzaJS = null;
        zzyr1.zzwI();
        if (zzaJW.isShowing())
        {
            zzaJW.dismiss();
        }
    }

    ( , Dialog dialog)
    {
        zzaJX = ;
        zzaJW = dialog;
        super();
    }
}
