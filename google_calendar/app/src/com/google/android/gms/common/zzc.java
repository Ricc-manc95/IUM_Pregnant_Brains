// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzc
{

    private static zzw zzaIp;
    private static final Object zzaIq = new Object();
    private static Context zzaIr;

    static void init(Context context)
    {
        com/google/android/gms/common/zzc;
        JVM INSTR monitorenter ;
        if (zzaIr != null) goto _L2; else goto _L1
_L1:
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        zzaIr = context.getApplicationContext();
_L4:
        com/google/android/gms/common/zzc;
        JVM INSTR monitorexit ;
        return;
_L2:
        Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        if (true) goto _L4; else goto _L3
_L3:
        context;
        throw context;
    }

    static boolean zza(String s, zza zza1)
    {
        if (!zzwl())
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = zzaIp.zzd(s, new zze(zza1.getBytes()));
        }
        // Misplaced declaration of an exception variable
        catch (zza zza1)
        {
            Log.e("GoogleCertificates", (new StringBuilder(String.valueOf(s).length() + 44)).append("Error checking if ").append(s).append(" is Google release signed.").toString());
            return false;
        }
        return flag;
    }

    static boolean zzb(String s, zza zza1)
    {
        if (!zzwl())
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = zzaIp.zze(s, new zze(zza1.getBytes()));
        }
        // Misplaced declaration of an exception variable
        catch (zza zza1)
        {
            Log.e("GoogleCertificates", (new StringBuilder(String.valueOf(s).length() + 36)).append("Error checking if ").append(s).append(" is Google signed.").toString());
            return false;
        }
        return flag;
    }

    private static boolean zzwl()
    {
        if (zzaIp != null)
        {
            return true;
        }
        if (zzaIr == null)
        {
            throw new NullPointerException("null reference");
        }
        Object obj2 = zzaIq;
        obj2;
        JVM INSTR monitorenter ;
        Object obj = zzaIp;
        if (obj != null) goto _L2; else goto _L1
_L1:
        obj = DynamiteModule.zza(zzaIr, DynamiteModule.zzbgM, "com.google.android.gms.googlecertificates").zzdJ("com.google.android.gms.common.GoogleCertificatesImpl");
        if (obj != null) goto _L4; else goto _L3
_L3:
        obj = null;
_L5:
        zzaIp = ((zzw) (obj));
_L2:
        obj2;
        JVM INSTR monitorexit ;
        return true;
_L4:
        android.os.IInterface iinterface = ((IBinder) (obj)).queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        if (iinterface == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        if (!(iinterface instanceof zzw))
        {
            break MISSING_BLOCK_LABEL_97;
        }
        obj = (zzw)iinterface;
          goto _L5
        obj = new com.google.android.gms.common.internal.zzw.zza.zza(((IBinder) (obj)));
          goto _L5
        com.google.android.gms.dynamite.DynamiteModule.zza zza1;
        zza1;
        Object obj1;
        obj1 = String.valueOf("Failed to load com.google.android.gms.googlecertificates: ");
        String s = String.valueOf(zza1.getMessage());
        if (s.length() == 0)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        obj1 = ((String) (obj1)).concat(s);
_L6:
        Log.e("GoogleCertificates", ((String) (obj1)));
        obj2;
        JVM INSTR monitorexit ;
        return false;
        obj1;
        obj2;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1 = new String(((String) (obj1)));
          goto _L6
    }


    private class zza extends com.google.android.gms.common.internal.zzt.zza
    {

        private int zzaIs;

        protected static byte[] zzcF(String s)
        {
            try
            {
                s = s.getBytes("ISO-8859-1");
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                throw new AssertionError(s);
            }
            return s;
        }

        public boolean equals(Object obj)
        {
            if (obj == null || !(obj instanceof zzt))
            {
                return false;
            }
            obj = (zzt)obj;
            if (((zzt) (obj)).zzwn() != hashCode())
            {
                return false;
            }
            boolean flag;
            try
            {
                obj = ((zzt) (obj)).zzwm();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                Log.e("GoogleCertificates", "iCertData failed to retrive data from remote");
                return false;
            }
            if (obj == null)
            {
                return false;
            }
            obj = (byte[])zze.zzH(((zzd) (obj)));
            flag = Arrays.equals(getBytes(), ((byte []) (obj)));
            return flag;
        }

        abstract byte[] getBytes();

        public int hashCode()
        {
            return zzaIs;
        }

        public final zzd zzwm()
        {
            return new zze(getBytes());
        }

        public final int zzwn()
        {
            return hashCode();
        }

        protected zza(byte abyte0[])
        {
            boolean flag = false;
            super();
            byte abyte1[] = abyte0;
            if (abyte0.length != 25)
            {
                int i = abyte0.length;
                abyte1 = String.valueOf(zzm.zza(abyte0, 0, abyte0.length, false));
                Log.wtf("GoogleCertificates", (new StringBuilder(String.valueOf(abyte1).length() + 51)).append("Cert hash data has incorrect length (").append(i).append("):\n").append(abyte1).toString(), new Exception());
                abyte1 = Arrays.copyOfRange(abyte0, 0, 25);
                if (abyte1.length == 25)
                {
                    flag = true;
                }
                i = abyte1.length;
                abyte0 = (new StringBuilder(55)).append("cert hash data has incorrect length. length=").append(i).toString();
                if (!flag)
                {
                    throw new IllegalArgumentException(String.valueOf(abyte0));
                }
            }
            zzaIs = Arrays.hashCode(abyte1);
        }
    }

}
