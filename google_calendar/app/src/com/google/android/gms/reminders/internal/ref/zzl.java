// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.zzab;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzm

public class zzl extends zzo
    implements RecurrenceInfo
{

    private boolean zzcgZ;
    private zzm zzcha;

    public zzl(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgZ = false;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof RecurrenceInfo))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzab.zza(this, (RecurrenceInfo)obj);
        }
    }

    public final Object freeze()
    {
        return new zzab(this);
    }

    public final Boolean getExceptional()
    {
        return Boolean.valueOf(getBoolean(zziU("recurrence_exceptional")));
    }

    public final Boolean getMaster()
    {
        return Boolean.valueOf(getBoolean(zziU("recurrence_master")));
    }

    public final Recurrence getRecurrence()
    {
        if (!zzcgZ)
        {
            zzcgZ = true;
            if (zzm.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzcha = null;
            } else
            {
                zzcha = new zzm(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzcha;
    }

    public final String getRecurrenceId()
    {
        return getString(zziU("recurrence_id"));
    }

    public int hashCode()
    {
        return zzab.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzab(this)).writeToParcel(parcel, i);
    }
}
