// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.CategoryInfo;
import com.google.android.gms.reminders.model.ChainInfo;
import com.google.android.gms.reminders.model.LocationGroup;
import com.google.android.gms.reminders.model.zzt;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzb, zzc

public class zzh extends zzo
    implements LocationGroup
{

    private boolean zzcgN;
    private com.google.android.gms.reminders.internal.ref.zzc zzcgO;
    private boolean zzcgP;
    private zzb zzcgQ;

    public zzh(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgN = false;
        zzcgP = false;
    }

    public int describeContents()
    {
        return 0;
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
            return zzt.zza(this, (LocationGroup)obj);
        }
    }

    public final Object freeze()
    {
        return new zzt(this);
    }

    public final CategoryInfo getCategoryInfo()
    {
        if (!zzcgP)
        {
            zzcgP = true;
            if (zzb.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzcgQ = null;
            } else
            {
                zzcgQ = new zzb(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzcgQ;
    }

    public final ChainInfo getChainInfo()
    {
        if (!zzcgN)
        {
            zzcgN = true;
            if (zzc.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzcgO = null;
            } else
            {
                zzcgO = new com.google.android.gms.reminders.internal.ref.zzc(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzcgO;
    }

    public final String getLocationQuery()
    {
        return getString(zziU("location_query"));
    }

    public final Integer getLocationQueryType()
    {
        String s = zziU("location_query_type");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public int hashCode()
    {
        return zzt.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzt(this)).writeToParcel(parcel, i);
    }
}
