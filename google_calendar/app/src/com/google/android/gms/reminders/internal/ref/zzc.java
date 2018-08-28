// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.ChainInfo;
import com.google.android.gms.reminders.model.FeatureIdProto;
import com.google.android.gms.reminders.model.zzf;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzg

public class zzc extends zzo
    implements ChainInfo
{

    private boolean zzcgH;
    private zzg zzcgI;

    public zzc(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgH = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "chain_name");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            s = String.valueOf(s);
            String s2 = String.valueOf("chain_id_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zzg.zza(dataholder, i, j, s))
            {
                return true;
            }
        }
        return false;
    }

    public int describeContents()
    {
        return 0;
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
            return zzf.zza(this, (ChainInfo)obj);
        }
    }

    public final Object freeze()
    {
        return new zzf(this);
    }

    public final FeatureIdProto getChainId()
    {
        if (!zzcgH)
        {
            zzcgH = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("chain_id_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zzg.zza(dataholder, i, k, s))
            {
                zzcgI = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("chain_id_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcgI = new zzg(dataholder1, j, s1);
            }
        }
        return zzcgI;
    }

    public final String getChainName()
    {
        return getString(zziU("chain_name"));
    }

    public int hashCode()
    {
        return zzf.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzf(this)).writeToParcel(parcel, i);
    }
}
