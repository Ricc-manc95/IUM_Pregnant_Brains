// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzaau;

// Referenced classes of package com.google.android.gms.common.stats:
//            zzc

public final class 
{

    public static zzaau zzaSA = zzaau.zzH("gms:common:stats:connections:ignored_calling_services", "");
    public static zzaau zzaSB = zzaau.zzH("gms:common:stats:connections:ignored_target_processes", "");
    public static zzaau zzaSC = zzaau.zzH("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
    public static zzaau zzaSD = zzaau.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(0x927c0L));
    public static zzaau zzaSz = zzaau.zzH("gms:common:stats:connections:ignored_calling_processes", "");

    static 
    {
        zzaau.zza("gms:common:stats:connections:level", Integer.valueOf(zzc.LOG_LEVEL_OFF));
    }
}
