// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzabx;
import com.google.android.gms.internal.zzaby;

public final class zzz
{

    private static boolean zzPJ;
    private static int zzaQH;
    private static Object zzun = new Object();

    public static int zzaH(Context context)
    {
        Object obj = zzun;
        obj;
        JVM INSTR monitorenter ;
        if (!zzPJ) goto _L2; else goto _L1
_L1:
        return zzaQH;
_L2:
        String s;
        zzPJ = true;
        s = context.getPackageName();
        context = zzaby.zzaTJ.zzaS(context);
        context = ((zzabx) (context)).mContext.getPackageManager().getApplicationInfo(s, 128).metaData;
        if (context != null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        obj;
        JVM INSTR monitorexit ;
          goto _L1
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        context.getString("com.google.app.id");
        zzaQH = context.getInt("com.google.android.gms.version");
_L3:
        obj;
        JVM INSTR monitorexit ;
          goto _L1
        context;
        Log.wtf("MetadataValueReader", "This should never happen.", context);
          goto _L3
    }

}
