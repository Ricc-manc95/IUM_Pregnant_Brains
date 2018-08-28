// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.util.zzm;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class zzaIs extends com.google.android.gms.common.internal.<init>
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

    protected .zzt(byte abyte0[])
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
