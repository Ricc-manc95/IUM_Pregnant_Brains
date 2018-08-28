// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzaqh;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzl

public final class Device extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzl();
    public final String manufacturer;
    public final String model;
    public final int type;
    public final String version = "";
    public final int versionCode;
    public final String zzbia;
    public final int zzbib;

    Device(int i, String s, String s1, String s2, int j, int k)
    {
        versionCode = i;
        if (s == null)
        {
            throw new NullPointerException("null reference");
        }
        manufacturer = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException("null reference");
        }
        model = (String)s1;
        if (s2 != null)
        {
            zzbia = s2;
            type = j;
            zzbib = k;
            return;
        } else
        {
            throw new IllegalStateException("Device UID is null.");
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof Device)) goto _L4; else goto _L3
_L3:
        boolean flag;
        obj = (Device)obj;
        String s = manufacturer;
        String s1 = ((Device) (obj)).manufacturer;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L5
_L5:
        s = model;
        s1 = ((Device) (obj)).model;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        s = version;
        s1 = ((Device) (obj)).version;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L8
_L8:
        s = zzbia;
        s1 = ((Device) (obj)).zzbia;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || type != ((Device) (obj)).type || zzbib != ((Device) (obj)).zzbib) goto _L6; else goto _L9
_L9:
        flag = true;
_L11:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L6:
        flag = false;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            manufacturer, model, version, zzbia, Integer.valueOf(type)
        });
    }

    public final String toString()
    {
        return String.format("Device{%s:%s:%s:%s}", new Object[] {
            String.format("%s:%s:%s", new Object[] {
                manufacturer, model, zzbia
            }), version, Integer.valueOf(type), Integer.valueOf(zzbib)
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        int j;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        j = parcel.dataPosition();
        zzc.zza(parcel, 1, manufacturer, false);
        zzc.zza(parcel, 2, model, false);
        zzc.zza(parcel, 3, version, false);
        if (zzbib != 1) goto _L2; else goto _L1
_L1:
        String s = zzbia;
_L4:
        zzc.zza(parcel, 4, s, false);
        i = type;
        zzc.zzb(parcel, 5, 4);
        parcel.writeInt(i);
        i = zzbib;
        zzc.zzb(parcel, 6, 4);
        parcel.writeInt(i);
        i = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
        return;
_L2:
        s = zzbia;
        String s1 = (String)zzaqh.zzbjL.get();
        if (s1 == null || s1.startsWith("com.google"))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            s = zzaqh.zzR(s, (String)zzaqh.zzbjL.get());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
