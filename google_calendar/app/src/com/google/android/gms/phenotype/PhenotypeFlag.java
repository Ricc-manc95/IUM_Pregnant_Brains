// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import com.google.android.gms.internal.zzaau;
import com.google.android.gms.internal.zzbqq;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.google.android.gms.phenotype:
//            zzh

public abstract class PhenotypeFlag
{

    private static Context zzaIr = null;
    private static boolean zzcaK = false;
    private static zzaau zzcaL = zzaau.zzm("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false);
    private static final Object zzun = new Object();
    private final Object zzBa;
    private Object zzadW;
    private final Uri zzagh;
    private final String zzcaM;
    private final String zzcaN;
    private final String zzcaO;

    public PhenotypeFlag(String s, String s1, String s2, Uri uri, Object obj)
    {
        zzadW = null;
        if (s2 == null && uri == null)
        {
            throw new IllegalArgumentException("Must pass a valid Shared Preferences file name or ContentProvider Uri");
        } else
        {
            zzcaM = s;
            zzcaN = s1;
            zzcaO = s2;
            zzagh = uri;
            zzBa = obj;
            return;
        }
    }

    public static void init(Context context)
    {
        Object obj = zzun;
        obj;
        JVM INSTR monitorenter ;
        Context context1;
        if (zzaIr != null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        context1 = context.getApplicationContext();
        zzaIr = context1;
        if (context1 != null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        zzaIr = context;
        obj;
        JVM INSTR monitorexit ;
        obj = zzaau.zzun;
        obj;
        JVM INSTR monitorenter ;
        int i;
        if (zzaau.zzaNH == null)
        {
            zzaau.zzaNH = new com.google.android.gms.internal.zzaau.zzd(context.getContentResolver());
        }
        i = zzaau.zzaNI;
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        zzaau.zzaNI = context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).uid;
_L1:
        obj;
        JVM INSTR monitorexit ;
        zzcaK = false;
        return;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        context;
        Log.e("GservicesValue", context.toString());
          goto _L1
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static PhenotypeFlag zzb(String s, String s1, String s2, Uri uri, double d)
    {
        return new _cls4(s, s1, s2, uri, Double.valueOf(d), s1);
    }

    public static PhenotypeFlag zzb(String s, String s1, String s2, Uri uri, int i)
    {
        return new _cls2(s, s1, s2, uri, Integer.valueOf(i), s1);
    }

    public static PhenotypeFlag zzb(String s, String s1, String s2, Uri uri, long l)
    {
        return new _cls1(s, s1, s2, uri, Long.valueOf(l), s1);
    }

    public static PhenotypeFlag zzb(String s, String s1, String s2, Uri uri, String s3)
    {
        return new _cls5(s, s1, s2, uri, s3, s1);
    }

    public static PhenotypeFlag zzb(String s, String s1, String s2, Uri uri, boolean flag)
    {
        return new _cls3(s, s1, s2, uri, Boolean.valueOf(flag), s1);
    }

    public abstract Object fromSharedPreferences(SharedPreferences sharedpreferences);

    public abstract Object fromString(String s);

    public final Object get()
    {
        if (zzaIr == null)
        {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        if (((Boolean)zzcaL.get()).booleanValue()) goto _L2; else goto _L1
_L1:
        if (zzagh == null) goto _L4; else goto _L3
_L3:
        Object obj;
        ContentResolver contentresolver = zzaIr.getContentResolver();
        Object obj3 = zzagh;
        zzh zzh1 = (zzh)zzh.zzcaE.get(obj3);
        obj = zzh1;
        if (zzh1 == null)
        {
            zzh zzh2 = new zzh(contentresolver, ((Uri) (obj3)));
            obj3 = (zzh)zzh.zzcaE.putIfAbsent(obj3, zzh2);
            obj = obj3;
            if (obj3 == null)
            {
                zzh2.zzcaF.registerContentObserver(zzh2.mUri, false, zzh2.zzcaG);
                obj = zzh2;
            }
        }
        obj = zzb(((zzh) (obj)));
        if (obj == null) goto _L2; else goto _L5
_L5:
        return obj;
_L4:
        obj = zzaIr.getSharedPreferences(zzcaO, 0);
        if (((SharedPreferences) (obj)).contains(zzcaN))
        {
            return fromSharedPreferences(((SharedPreferences) (obj)));
        }
_L2:
        obj = zzaIr.getContentResolver();
        obj = zzbqq.getString(((ContentResolver) (obj)), zzcaM);
_L7:
        Object obj2;
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        obj2 = fromString(((String) (obj)));
        obj = obj2;
        if (obj2 != null) goto _L5; else goto _L6
_L6:
        return zzBa;
        Object obj1;
        obj1;
        long l = Binder.clearCallingIdentity();
        obj1 = zzbqq.getString(zzaIr.getContentResolver(), zzcaM);
        Binder.restoreCallingIdentity(l);
          goto _L7
        obj1;
        Binder.restoreCallingIdentity(l);
        throw obj1;
    }

    public abstract Object zzb(zzh zzh1);


    private class _cls4 extends PhenotypeFlag
    {

        private final String zzcaP;

        private static Double zziK(String s)
        {
            double d;
            try
            {
                d = Double.parseDouble(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return null;
            }
            return Double.valueOf(d);
        }

        public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return Double.valueOf(sharedpreferences.getFloat(zzcaP, 0.0F));
        }

        public final Object fromString(String s)
        {
            return zziK(s);
        }

        public final Object zzb(zzh zzh1)
        {
            return zzh1.zza(zzcaP, null);
        }

        _cls4(String s, String s1, String s2, Uri uri, Double double1, String s3)
        {
            zzcaP = s3;
            super(s, s1, s2, uri, double1);
        }
    }


    private class _cls2 extends PhenotypeFlag
    {

        private final String zzcaP;

        private static Integer zziI(String s)
        {
            int i;
            try
            {
                i = Integer.parseInt(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return null;
            }
            return Integer.valueOf(i);
        }

        public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return Integer.valueOf((int)sharedpreferences.getLong(zzcaP, 0L));
        }

        public final Object fromString(String s)
        {
            return zziI(s);
        }

        public final Object zzb(zzh zzh1)
        {
            return zzh1.getInteger(zzcaP, null);
        }

        _cls2(String s, String s1, String s2, Uri uri, Integer integer, String s3)
        {
            zzcaP = s3;
            super(s, s1, s2, uri, integer);
        }
    }


    private class _cls1 extends PhenotypeFlag
    {

        private final String zzcaP;

        private static Long zziH(String s)
        {
            long l;
            try
            {
                l = Long.parseLong(s);
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return null;
            }
            return Long.valueOf(l);
        }

        public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return Long.valueOf(sharedpreferences.getLong(zzcaP, 0L));
        }

        public final Object fromString(String s)
        {
            return zziH(s);
        }

        public final Object zzb(zzh zzh1)
        {
            return zzh1.getLong(zzcaP, null);
        }

        _cls1(String s, String s1, String s2, Uri uri, Long long1, String s3)
        {
            zzcaP = s3;
            super(s, s1, s2, uri, long1);
        }
    }


    private class _cls5 extends PhenotypeFlag
    {

        private final String zzcaP;

        public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return sharedpreferences.getString(zzcaP, "");
        }

        public final Object fromString(String s)
        {
            return s;
        }

        public final Object zzb(zzh zzh1)
        {
            return zzh1.getString(zzcaP, null);
        }

        _cls5(String s, String s1, String s2, Uri uri, String s3, String s4)
        {
            zzcaP = s4;
            super(s, s1, s2, uri, s3);
        }
    }


    private class _cls3 extends PhenotypeFlag
    {

        private final String zzcaP;

        public final Object fromSharedPreferences(SharedPreferences sharedpreferences)
        {
            return Boolean.valueOf(sharedpreferences.getBoolean(zzcaP, false));
        }

        public final Object fromString(String s)
        {
            if (zzbqq.bu.matcher(s).matches())
            {
                return Boolean.valueOf(true);
            }
            if (zzbqq.bv.matcher(s).matches())
            {
                return Boolean.valueOf(false);
            } else
            {
                return null;
            }
        }

        public final Object zzb(zzh zzh1)
        {
            Object obj = null;
            String s = zzh1.getString(zzcaP, null);
            zzh1 = obj;
            if (s != null)
            {
                if (zzbqq.bu.matcher(s).matches())
                {
                    zzh1 = Boolean.valueOf(true);
                } else
                {
                    zzh1 = obj;
                    if (zzbqq.bv.matcher(s).matches())
                    {
                        return Boolean.valueOf(false);
                    }
                }
            }
            return zzh1;
        }

        _cls3(String s, String s1, String s2, Uri uri, Boolean boolean1, String s3)
        {
            zzcaP = s3;
            super(s, s1, s2, uri, boolean1);
        }
    }

}
