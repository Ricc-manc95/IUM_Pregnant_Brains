// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.phenotype:
//            zzg, FlagOverride

public class FlagOverrides extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzg();
    public final int mVersionCode;
    public final List overrides;

    FlagOverrides(int i, List list)
    {
        mVersionCode = i;
        overrides = list;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof FlagOverrides))
        {
            return false;
        } else
        {
            obj = (FlagOverrides)obj;
            return overrides.equals(((FlagOverrides) (obj)).overrides);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("FlagOverrides(");
        Iterator iterator = overrides.iterator();
        boolean flag = true;
        FlagOverride flagoverride;
        for (; iterator.hasNext(); flagoverride.toString(stringbuilder))
        {
            flagoverride = (FlagOverride)iterator.next();
            if (!flag)
            {
                stringbuilder.append(", ");
            }
            flag = false;
        }

        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zzc(parcel, 2, overrides, false);
        zzc.zzJ(parcel, i);
    }

}
