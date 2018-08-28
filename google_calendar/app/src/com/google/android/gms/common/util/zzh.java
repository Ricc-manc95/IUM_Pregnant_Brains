// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;

public final class zzh
{

    private static Boolean zzaTi;
    public static Boolean zzaTk;

    public static boolean zzaL(Context context)
    {
        if (zzaTi == null)
        {
            boolean flag;
            if (context.getPackageManager().hasSystemFeature("android.hardware.type.watch"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            zzaTi = Boolean.valueOf(flag);
        }
        return zzaTi.booleanValue();
    }
}
