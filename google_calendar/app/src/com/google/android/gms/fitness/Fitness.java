// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzaos;
import com.google.android.gms.internal.zzaou;
import com.google.android.gms.internal.zzapv;
import com.google.android.gms.internal.zzapw;
import com.google.android.gms.internal.zzapx;
import com.google.android.gms.internal.zzapy;
import com.google.android.gms.internal.zzaqa;
import com.google.android.gms.internal.zzaqb;
import com.google.android.gms.internal.zzaqc;
import com.google.android.gms.internal.zzaqd;

// Referenced classes of package com.google.android.gms.fitness:
//            HistoryApi, RecordingApi

public final class Fitness
{

    public static final Api HISTORY_API;
    public static final HistoryApi HistoryApi = new zzapy();
    public static final Api RECORDING_API;
    public static final RecordingApi RecordingApi = new zzaqb();
    public static final Scope SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");

    static 
    {
        new zzaqc();
        RECORDING_API = zzaou.API;
        new zzaqd();
        HISTORY_API = zzaos.API;
        new zzapx();
        new zzapw();
        new zzapv();
        new zzaqa();
        new Scope("https://www.googleapis.com/auth/fitness.activity.write");
        new Scope("https://www.googleapis.com/auth/fitness.location.read");
        new Scope("https://www.googleapis.com/auth/fitness.location.write");
        new Scope("https://www.googleapis.com/auth/fitness.body.read");
        new Scope("https://www.googleapis.com/auth/fitness.body.write");
        new Scope("https://www.googleapis.com/auth/fitness.nutrition.read");
        new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
    }
}
