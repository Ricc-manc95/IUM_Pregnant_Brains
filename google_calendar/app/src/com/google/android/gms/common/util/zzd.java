// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;

public final class zzd
{

    public static boolean zzy(Context context, String s)
    {
        boolean flag = false;
        "com.google.android.gms".equals(s);
        int i;
        try
        {
            i = zzaby.zzaTJ.zzaS(context).mContext.getPackageManager().getApplicationInfo(s, 0).flags;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return false;
        }
        if ((i & 0x200000) != 0)
        {
            flag = true;
        }
        return flag;
    }
}
