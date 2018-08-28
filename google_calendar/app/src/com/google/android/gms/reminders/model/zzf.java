// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            ChainInfo, zze, zzp, FeatureIdProto

public class zzf extends zza
    implements ChainInfo
{

    public static final android.os.Parcelable.Creator CREATOR = new zze();
    public final int mVersionCode;
    public final String zzbCM;
    public final zzp zzchK;

    zzf(int i, String s, zzp zzp1)
    {
        zzbCM = s;
        zzchK = zzp1;
        mVersionCode = i;
    }

    public zzf(ChainInfo chaininfo)
    {
        this(chaininfo.getChainName(), chaininfo.getChainId(), false);
    }

    private zzf(String s, FeatureIdProto featureidproto, boolean flag)
    {
        mVersionCode = 1;
        zzbCM = s;
        if (featureidproto == null)
        {
            s = null;
        } else
        {
            s = new zzp(featureidproto);
        }
        zzchK = s;
    }

    public static int zza(ChainInfo chaininfo)
    {
        return Arrays.hashCode(new Object[] {
            chaininfo.getChainName(), chaininfo.getChainId()
        });
    }

    public static boolean zza(ChainInfo chaininfo, ChainInfo chaininfo1)
    {
        String s = chaininfo.getChainName();
        String s1 = chaininfo1.getChainName();
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
            chaininfo = chaininfo.getChainId();
            chaininfo1 = chaininfo1.getChainId();
            if (chaininfo == chaininfo1 || chaininfo != null && chaininfo.equals(chaininfo1))
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
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ChainInfo))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (ChainInfo)obj);
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

    public final FeatureIdProto getChainId()
    {
        return zzchK;
    }

    public final String getChainName()
    {
        return zzbCM;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getChainName(), getChainId()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 3, zzbCM, false);
        zzc.zza(parcel, 4, zzchK, i, false);
        zzc.zzJ(parcel, j);
    }

}
