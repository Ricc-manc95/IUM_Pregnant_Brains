// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.google.android.gms.phenotype:
//            zza, Flag

public class Configuration extends zza
    implements Comparable
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.phenotype.zza();
    public final String deleteFlags[];
    private final Map flagMap = new TreeMap();
    public final int flagType;
    public final Flag flags[];
    public final int mVersionCode;

    Configuration(int i, int j, Flag aflag[], String as[])
    {
        mVersionCode = i;
        flagType = j;
        flags = aflag;
        j = aflag.length;
        for (i = 0; i < j; i++)
        {
            Flag flag = aflag[i];
            flagMap.put(flag.name, flag);
        }

        deleteFlags = as;
        if (deleteFlags != null)
        {
            Arrays.sort(deleteFlags);
        }
    }

    public int compareTo(Object obj)
    {
        obj = (Configuration)obj;
        return flagType - ((Configuration) (obj)).flagType;
    }

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof Configuration))
        {
            obj = (Configuration)obj;
            if (mVersionCode == ((Configuration) (obj)).mVersionCode && flagType == ((Configuration) (obj)).flagType)
            {
                Map map = flagMap;
                Map map1 = ((Configuration) (obj)).flagMap;
                boolean flag;
                if (map == map1 || map != null && map.equals(map1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && Arrays.equals(deleteFlags, ((Configuration) (obj)).deleteFlags))
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("Configuration(");
        stringbuilder.append(mVersionCode);
        stringbuilder.append(", ");
        stringbuilder.append(flagType);
        stringbuilder.append(", ");
        stringbuilder.append("(");
        for (Iterator iterator = flagMap.values().iterator(); iterator.hasNext(); stringbuilder.append(", "))
        {
            stringbuilder.append((Flag)iterator.next());
        }

        stringbuilder.append(")");
        stringbuilder.append(", ");
        stringbuilder.append("(");
        if (deleteFlags != null)
        {
            String as[] = deleteFlags;
            int j = as.length;
            for (int i = 0; i < j; i++)
            {
                stringbuilder.append(as[i]);
                stringbuilder.append(", ");
            }

        } else
        {
            stringbuilder.append("null");
        }
        stringbuilder.append(")");
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = flagType;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 3, flags, i, false);
        zzc.zza(parcel, 4, deleteFlags, false);
        zzc.zzJ(parcel, j);
    }

}
