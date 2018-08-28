// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.location:
//            zzp

public final class LocationRequest extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzp();
    public int mPriority;
    public final int mVersionCode;
    public long zzbAQ;
    public long zzbBq;
    public long zzbBr;
    public int zzbBs;
    public float zzbBt;
    public long zzbBu;
    public boolean zzbkT;

    public LocationRequest()
    {
        mVersionCode = 1;
        mPriority = 102;
        zzbBq = 0x36ee80L;
        zzbBr = 0x927c0L;
        zzbkT = false;
        zzbAQ = 0x7fffffffffffffffL;
        zzbBs = 0x7fffffff;
        zzbBt = 0.0F;
        zzbBu = 0L;
    }

    LocationRequest(int i, int j, long l, long l1, boolean flag, 
            long l2, int k, float f, long l3)
    {
        mVersionCode = i;
        mPriority = j;
        zzbBq = l;
        zzbBr = l1;
        zzbkT = flag;
        zzbAQ = l2;
        zzbBs = k;
        zzbBt = f;
        zzbBu = l3;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof LocationRequest))
            {
                return false;
            }
            obj = (LocationRequest)obj;
            if (mPriority != ((LocationRequest) (obj)).mPriority || zzbBq != ((LocationRequest) (obj)).zzbBq || zzbBr != ((LocationRequest) (obj)).zzbBr || zzbkT != ((LocationRequest) (obj)).zzbkT || zzbAQ != ((LocationRequest) (obj)).zzbAQ || zzbBs != ((LocationRequest) (obj)).zzbBs || zzbBt != ((LocationRequest) (obj)).zzbBt)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            Integer.valueOf(mPriority), Long.valueOf(zzbBq), Long.valueOf(zzbBr), Boolean.valueOf(zzbkT), Long.valueOf(zzbAQ), Integer.valueOf(zzbBs), Float.valueOf(zzbBt)
        });
    }

    public final String toString()
    {
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        stringbuilder = new StringBuilder();
        stringbuilder1 = stringbuilder.append("Request[");
        mPriority;
        JVM INSTR tableswitch 100 105: default 56
    //                   100 228
    //                   101 56
    //                   102 234
    //                   103 56
    //                   104 240
    //                   105 246;
           goto _L1 _L2 _L1 _L3 _L1 _L4 _L5
_L1:
        String s = "???";
_L7:
        stringbuilder1.append(s);
        if (mPriority != 105)
        {
            stringbuilder.append(" requested=");
            stringbuilder.append(zzbBq).append("ms");
        }
        stringbuilder.append(" fastest=");
        stringbuilder.append(zzbBr).append("ms");
        if (zzbBu > zzbBq)
        {
            stringbuilder.append(" maxWait=");
            stringbuilder.append(zzbBu).append("ms");
        }
        if (zzbAQ != 0x7fffffffffffffffL)
        {
            long l = zzbAQ;
            long l1 = SystemClock.elapsedRealtime();
            stringbuilder.append(" expireIn=");
            stringbuilder.append(l - l1).append("ms");
        }
        if (zzbBs != 0x7fffffff)
        {
            stringbuilder.append(" num=").append(zzbBs);
        }
        stringbuilder.append(']');
        return stringbuilder.toString();
_L2:
        s = "PRIORITY_HIGH_ACCURACY";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "PRIORITY_BALANCED_POWER_ACCURACY";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "PRIORITY_LOW_POWER";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "PRIORITY_NO_POWER";
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mPriority;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        long l = zzbBq;
        zzc.zzb(parcel, 2, 8);
        parcel.writeLong(l);
        l = zzbBr;
        zzc.zzb(parcel, 3, 8);
        parcel.writeLong(l);
        boolean flag = zzbkT;
        zzc.zzb(parcel, 4, 4);
        float f;
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        l = zzbAQ;
        zzc.zzb(parcel, 5, 8);
        parcel.writeLong(l);
        i = zzbBs;
        zzc.zzb(parcel, 6, 4);
        parcel.writeInt(i);
        f = zzbBt;
        zzc.zzb(parcel, 7, 4);
        parcel.writeFloat(f);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        l = zzbBu;
        zzc.zzb(parcel, 8, 8);
        parcel.writeLong(l);
        zzc.zzJ(parcel, j);
    }

}
