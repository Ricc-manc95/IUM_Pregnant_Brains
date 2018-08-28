// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.FeatureIdProto;
import com.google.android.gms.reminders.model.zzp;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzg extends zzo
    implements FeatureIdProto
{

    public zzg(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "cell_id");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            s = zzaw(s, "fprint");
            dataholder.zzj(s, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
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
        if (!(obj instanceof FeatureIdProto))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzp.zza(this, (FeatureIdProto)obj);
        }
    }

    public final Object freeze()
    {
        return new zzp(this);
    }

    public final Long getCellId()
    {
        String s = zziU("cell_id");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final Long getFprint()
    {
        String s = zziU("fprint");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public int hashCode()
    {
        return zzp.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzp(this)).writeToParcel(parcel, i);
    }
}
