// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzakp;
import com.google.android.gms.internal.zzcdm;

// Referenced classes of package com.google.android.gms.drive:
//            zza

public class ChangeSequenceNumber extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.drive.zza();
    public final int mVersionCode;
    public final long zzaWB;
    public final long zzaWC;
    public final long zzaWD;
    private volatile String zzaWE;

    ChangeSequenceNumber(int i, long l, long l1, long l2)
    {
        boolean flag1 = true;
        super();
        zzaWE = null;
        boolean flag;
        if (l != -1L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (l1 != -1L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (l2 != -1L)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            mVersionCode = i;
            zzaWB = l;
            zzaWC = l1;
            zzaWD = l2;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof ChangeSequenceNumber)
        {
            if (((ChangeSequenceNumber) (obj = (ChangeSequenceNumber)obj)).zzaWC == zzaWC && ((ChangeSequenceNumber) (obj)).zzaWD == zzaWD && ((ChangeSequenceNumber) (obj)).zzaWB == zzaWB)
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        String s = String.valueOf(String.valueOf(zzaWB));
        String s1 = String.valueOf(String.valueOf(zzaWC));
        String s2 = String.valueOf(String.valueOf(zzaWD));
        return (new StringBuilder(String.valueOf(s).length() + 0 + String.valueOf(s1).length() + String.valueOf(s2).length())).append(s).append(s1).append(s2).toString().hashCode();
    }

    public String toString()
    {
        if (zzaWE == null)
        {
            Object obj = new zzakp();
            obj.versionCode = mVersionCode;
            obj.zzbbv = zzaWB;
            obj.zzbbw = zzaWC;
            obj.zzbbx = zzaWD;
            int i = ((zzcdm) (obj)).computeSerializedSize();
            obj.AL = i;
            byte abyte0[] = new byte[i];
            zzcdm.zza(((zzcdm) (obj)), abyte0, 0, abyte0.length);
            String s = Base64.encodeToString(abyte0, 10);
            obj = String.valueOf("ChangeSequenceNumber:");
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                obj = ((String) (obj)).concat(s);
            } else
            {
                obj = new String(((String) (obj)));
            }
            zzaWE = ((String) (obj));
        }
        return zzaWE;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        long l = zzaWB;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        l = zzaWC;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        l = zzaWD;
        zzc.zzb(parcel, 4, 8);
        parcel.writeLong(l);
        zzc.zzJ(parcel, i);
    }

}
