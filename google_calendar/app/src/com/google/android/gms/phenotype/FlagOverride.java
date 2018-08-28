// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.phenotype:
//            zzf, Flag

public class FlagOverride extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzf();
    public final boolean committed;
    public final String configurationName;
    public final Flag flag;
    public final int mVersionCode;
    public final String userName;

    FlagOverride(int i, String s, String s1, Flag flag1, boolean flag2)
    {
        mVersionCode = i;
        configurationName = s;
        userName = s1;
        flag = flag1;
        committed = flag2;
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof FlagOverride))
        {
            return false;
        }
        obj = (FlagOverride)obj;
        Object obj1 = configurationName;
        Object obj2 = ((FlagOverride) (obj)).configurationName;
        boolean flag1;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = userName;
        obj2 = ((FlagOverride) (obj)).userName;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = flag;
        obj2 = ((FlagOverride) (obj)).flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && committed == ((FlagOverride) (obj)).committed) goto _L1; else goto _L3
_L3:
        return false;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        toString(stringbuilder);
        return stringbuilder.toString();
    }

    public final String toString(StringBuilder stringbuilder)
    {
        stringbuilder.append("FlagOverride(");
        stringbuilder.append(configurationName);
        stringbuilder.append(", ");
        stringbuilder.append(userName);
        stringbuilder.append(", ");
        flag.toString(stringbuilder);
        stringbuilder.append(", ");
        stringbuilder.append(committed);
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag1 = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, configurationName, false);
        zzc.zza(parcel, 3, userName, false);
        zzc.zza(parcel, 4, flag, i, false);
        boolean flag2 = committed;
        zzc.zzb(parcel, 5, 4);
        if (flag2)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
