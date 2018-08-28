// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzr

public class MapValue extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzr();
    public final int mVersionCode;
    public final int zzbiw;
    public final float zzbix;

    MapValue(int i, int j, float f)
    {
        mVersionCode = i;
        zzbiw = j;
        zzbix = f;
    }

    private final float asFloat()
    {
        boolean flag;
        if (zzbiw == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Value is not in float format"));
        } else
        {
            return zzbix;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof MapValue)) goto _L4; else goto _L3
_L3:
        obj = (MapValue)obj;
        if (zzbiw != ((MapValue) (obj)).zzbiw) goto _L6; else goto _L5
_L5:
        zzbiw;
        JVM INSTR tableswitch 2 2: default 56
    //                   2 81;
           goto _L7 _L8
_L7:
        boolean flag;
        if (zzbix == ((MapValue) (obj)).zzbix)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L10:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L8:
        if (asFloat() == ((MapValue) (obj)).asFloat())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        flag = false;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public int hashCode()
    {
        return (int)zzbix;
    }

    public String toString()
    {
        switch (zzbiw)
        {
        default:
            return "unknown";

        case 2: // '\002'
            return Float.toString(asFloat());
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = zzbiw;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        float f = zzbix;
        zzc.zzb(parcel, 2, 4);
        parcel.writeFloat(f);
        j = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
