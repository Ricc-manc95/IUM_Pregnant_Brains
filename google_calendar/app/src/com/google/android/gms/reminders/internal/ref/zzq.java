// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzaj;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzq extends zzo
    implements Time
{

    public zzq(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "hour");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            String s2 = zzaw(s, "minute");
            dataholder.zzj(s2, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s2)))
            {
                s = zzaw(s, "second");
                dataholder.zzj(s, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
                {
                    return true;
                }
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
        if (!(obj instanceof Time))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzaj.zza(this, (Time)obj);
        }
    }

    public final Object freeze()
    {
        return new zzaj(this);
    }

    public final Integer getHour()
    {
        String s = zziU("hour");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getMinute()
    {
        String s = zziU("minute");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getSecond()
    {
        String s = zziU("second");
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
        return zzaj.zzc(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzaj(this)).writeToParcel(parcel, i);
    }
}
