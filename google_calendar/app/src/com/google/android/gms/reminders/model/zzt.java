// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            LocationGroup, zzs, zzf, zzd, 
//            ChainInfo, CategoryInfo

public class zzt extends zza
    implements LocationGroup
{

    public static final android.os.Parcelable.Creator CREATOR = new zzs();
    public final int mVersionCode;
    public final String zzcir;
    public final Integer zzcis;
    public final zzf zzciv;
    public final zzd zzciw;

    zzt(int i, String s, Integer integer, zzf zzf1, zzd zzd1)
    {
        zzcir = s;
        zzcis = integer;
        zzciv = zzf1;
        zzciw = zzd1;
        mVersionCode = i;
    }

    public zzt(LocationGroup locationgroup)
    {
        this(locationgroup.getLocationQuery(), locationgroup.getLocationQueryType(), locationgroup.getChainInfo(), locationgroup.getCategoryInfo(), false);
    }

    private zzt(String s, Integer integer, ChainInfo chaininfo, CategoryInfo categoryinfo, boolean flag)
    {
        Object obj = null;
        super();
        mVersionCode = 2;
        zzcir = s;
        zzcis = integer;
        if (chaininfo == null)
        {
            s = null;
        } else
        {
            s = new zzf(chaininfo);
        }
        zzciv = s;
        if (categoryinfo == null)
        {
            s = obj;
        } else
        {
            s = new zzd(categoryinfo);
        }
        zzciw = s;
    }

    public static int zza(LocationGroup locationgroup)
    {
        return Arrays.hashCode(new Object[] {
            locationgroup.getLocationQuery(), locationgroup.getLocationQueryType(), locationgroup.getChainInfo(), locationgroup.getCategoryInfo()
        });
    }

    public static boolean zza(LocationGroup locationgroup, LocationGroup locationgroup1)
    {
        String s = locationgroup.getLocationQuery();
        String s1 = locationgroup1.getLocationQuery();
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer = locationgroup.getLocationQueryType();
            Integer integer1 = locationgroup1.getLocationQueryType();
            if (integer == integer1 || integer != null && integer.equals(integer1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ChainInfo chaininfo = locationgroup.getChainInfo();
                ChainInfo chaininfo1 = locationgroup1.getChainInfo();
                if (chaininfo == chaininfo1 || chaininfo != null && chaininfo.equals(chaininfo1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    locationgroup = locationgroup.getCategoryInfo();
                    locationgroup1 = locationgroup1.getCategoryInfo();
                    if (locationgroup == locationgroup1 || locationgroup != null && locationgroup.equals(locationgroup1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof LocationGroup))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (LocationGroup)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final CategoryInfo getCategoryInfo()
    {
        return zzciw;
    }

    public final ChainInfo getChainInfo()
    {
        return zzciv;
    }

    public final String getLocationQuery()
    {
        return zzcir;
    }

    public final Integer getLocationQueryType()
    {
        return zzcis;
    }

    public int hashCode()
    {
        return zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, zzcir, false);
        Integer integer = zzcis;
        if (integer != null)
        {
            zzc.zzb(parcel, 3, 4);
            parcel.writeInt(integer.intValue());
        }
        zzc.zza(parcel, 5, zzciv, i, false);
        zzc.zza(parcel, 6, zzciw, i, false);
        zzc.zzJ(parcel, j);
    }

}
