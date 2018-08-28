// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.clearcut;

import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.zzaak;
import com.google.android.gms.internal.zzys;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.ArrayList;
import java.util.TimeZone;

// Referenced classes of package com.google.android.gms.clearcut:
//            ClearcutLogger, LogEventParcelable, ClearcutLoggerApi

public final class zzaHs
{

    private boolean zzaHA;
    public final ClearcutLogger zzaHB;
    public String zzaHj;
    private int zzaHk;
    public String zzaHl;
    private String zzaHm;
    private int zzaHo;
    private final zzaHB zzaHs;
    private ArrayList zzaHu;
    private ArrayList zzaHv;
    private ArrayList zzaHw;
    private ArrayList zzaHx;
    private boolean zzaHy;
    public final com.google.android.gms.internal.zzaHB zzaHz;

    public final PendingResult logAsync()
    {
        if (zzaHA)
        {
            throw new IllegalStateException("do not reuse LogEventBuilder");
        }
        zzaHA = true;
        Object obj = new LogEventParcelable(new PlayLoggerContext(ClearcutLogger.zzi(zzaHB), ClearcutLogger.zzj(zzaHB), zzaHk, zzaHj, zzaHl, zzaHm, ClearcutLogger.zzh(zzaHB), zzaHo), zzaHz, zzaHs, null, ClearcutLogger.zze(null), ClearcutLogger.zzf(null), ClearcutLogger.zze(null), ClearcutLogger.zzg(null), zzaHy);
        PlayLoggerContext playloggercontext = ((LogEventParcelable) (obj)).playLoggerContext;
        if (ClearcutLogger.zzk(zzaHB).dLog(playloggercontext.logSourceName, playloggercontext.logSource))
        {
            return ClearcutLogger.zzl(zzaHB).logEvent(((LogEventParcelable) (obj)));
        }
        obj = Status.zzaJt;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Result must not be null"));
        } else
        {
            zzaak zzaak1 = new zzaak(Looper.getMainLooper());
            zzaak1.zzb(((com.google.android.gms.common.api.Result) (obj)));
            return zzaak1;
        }
    }

    public (ClearcutLogger clearcutlogger,  )
    {
        this(clearcutlogger, null, );
    }

    public <init>(ClearcutLogger clearcutlogger, byte abyte0[])
    {
        this(clearcutlogger, abyte0, null);
    }

    private <init>(ClearcutLogger clearcutlogger, byte abyte0[], <init> <init>1)
    {
        zzaHB = clearcutlogger;
        super();
        zzaHk = ClearcutLogger.zza(zzaHB);
        zzaHj = ClearcutLogger.zzb(zzaHB);
        zzaHl = ClearcutLogger.zzc(zzaHB);
        zzaHm = ClearcutLogger.zzd(zzaHB);
        zzaHo = ClearcutLogger.zze(zzaHB);
        zzaHu = null;
        zzaHv = null;
        zzaHw = null;
        zzaHx = null;
        zzaHy = true;
        zzaHz = new com.google.android.gms.internal.zzaHz();
        zzaHA = false;
        zzaHl = ClearcutLogger.zzc(clearcutlogger);
        zzaHm = ClearcutLogger.zzd(clearcutlogger);
        zzaHz.zzaHz = ClearcutLogger.zzf(clearcutlogger).currentTimeMillis();
        zzaHz.zzaHz = ClearcutLogger.zzf(clearcutlogger).elapsedRealtime();
        com.google.android.gms.internal.<init> <init>2 = zzaHz;
        ClearcutLogger.zzg(clearcutlogger);
        long l = zzaHz.zzaHz;
        <init>2.zzaHz = TimeZone.getDefault().getOffset(l) / 1000;
        if (abyte0 != null)
        {
            zzaHz.zzaHz = abyte0;
        }
        zzaHs = <init>1;
    }
}
