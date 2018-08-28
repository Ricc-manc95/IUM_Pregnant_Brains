// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.zzg;
import com.google.android.gms.internal.zzye;
import com.google.android.gms.internal.zzyj;
import java.util.ArrayList;

// Referenced classes of package com.google.android.gms.clearcut:
//            ClearcutLoggerApi

public final class ClearcutLogger
{

    public static final Api API;
    private static final com.google.android.gms.common.api.Api.zzf zzahs;
    private static final com.google.android.gms.common.api.Api.zza zzaht;
    private final String mPackageName;
    private final int zzaHi;
    private String zzaHj;
    private int zzaHk;
    private String zzaHl;
    private String zzaHm;
    private final boolean zzaHn;
    private int zzaHo;
    private final ClearcutLoggerApi zzaHp;
    private TimeZoneOffsetProvider zzaHq;
    private final LogSampler zzaHr;
    private final Clock zzvb;

    private ClearcutLogger(Context context, int i, String s, String s1, String s2, boolean flag, ClearcutLoggerApi clearcutloggerapi, 
            Clock clock, TimeZoneOffsetProvider timezoneoffsetprovider, LogSampler logsampler)
    {
        i = 0;
        super();
        zzaHk = -1;
        zzaHo = 0;
        mPackageName = context.getPackageName();
        zzaHi = zzan(context);
        zzaHk = -1;
        zzaHj = s;
        zzaHl = s1;
        zzaHm = s2;
        zzaHn = false;
        zzaHp = clearcutloggerapi;
        zzvb = clock;
        zzaHq = new TimeZoneOffsetProvider();
        zzaHo = 0;
        zzaHr = logsampler;
        if (zzaHn)
        {
            if (zzaHl == null)
            {
                i = 1;
            }
            if (i == 0)
            {
                throw new IllegalArgumentException(String.valueOf("can't be anonymous with an upload account"));
            }
        }
    }

    public ClearcutLogger(Context context, String s, String s1)
    {
        this(context, -1, s, s1, null, false, ((ClearcutLoggerApi) (new zzye(context))), ((Clock) (zzg.zzaTf)), null, ((LogSampler) (new zzyj(context))));
    }

    public ClearcutLogger(Context context, String s, String s1, String s2)
    {
        this(context, -1, s, s1, null, false, ((ClearcutLoggerApi) (new zzye(context))), ((Clock) (zzg.zzaTf)), null, ((LogSampler) (new zzyj(context))));
    }

    static int zza(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHk;
    }

    private static int zzan(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return 0;
        }
        return i;
    }

    static String zzb(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHj;
    }

    static String zzc(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHl;
    }

    static String zzd(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHm;
    }

    static int zze(ClearcutLogger clearcutlogger)
    {
        return 0;
    }

    static int[] zze(ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return null;
        }
        int ai[] = new int[arraylist.size()];
        arraylist = (ArrayList)arraylist;
        int k = arraylist.size();
        int j = 0;
        for (int i = 0; j < k; i++)
        {
            Object obj = arraylist.get(j);
            j++;
            ai[i] = ((Integer)obj).intValue();
        }

        return ai;
    }

    static Clock zzf(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzvb;
    }

    static String[] zzf(ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return null;
        } else
        {
            return (String[])arraylist.toArray(new String[0]);
        }
    }

    static TimeZoneOffsetProvider zzg(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHq;
    }

    static byte[][] zzg(ArrayList arraylist)
    {
        if (arraylist == null)
        {
            return null;
        } else
        {
            return (byte[][])arraylist.toArray(new byte[0][]);
        }
    }

    public static boolean zzh(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHn;
    }

    static String zzi(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.mPackageName;
    }

    static int zzj(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHi;
    }

    static LogSampler zzk(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHr;
    }

    static ClearcutLoggerApi zzl(ClearcutLogger clearcutlogger)
    {
        return clearcutlogger.zzaHp;
    }

    static 
    {
        zzahs = new com.google.android.gms.common.api.Api.zzf();
        zzaht = new _cls1();
        API = new Api("ClearcutLogger.API", zzaht, zzahs);
    }

    private class TimeZoneOffsetProvider
    {

        public TimeZoneOffsetProvider()
        {
        }
    }


    private class _cls1 extends com.google.android.gms.common.api.Api.zza
    {

        public final com.google.android.gms.common.api.Api.zze zza(Context context, Looper looper, com.google.android.gms.common.internal.zzg zzg1, Object obj, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
        {
            return new zzyf(context, looper, zzg1, connectioncallbacks, onconnectionfailedlistener);
        }

        _cls1()
        {
        }
    }

}
