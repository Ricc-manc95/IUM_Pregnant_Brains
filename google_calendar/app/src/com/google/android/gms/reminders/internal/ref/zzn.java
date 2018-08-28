// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.RecurrenceStart;
import com.google.android.gms.reminders.model.zzad;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zze

public class zzn extends zzo
    implements RecurrenceStart
{

    private boolean zzchn;
    private zze zzcho;

    public zzn(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzchn = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        s = String.valueOf(s);
        String s1 = String.valueOf("recurrence_start_");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        return zze.zza(dataholder, i, j, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof RecurrenceStart))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzad.zza(this, (RecurrenceStart)obj);
        }
    }

    public final Object freeze()
    {
        return new zzad(this);
    }

    public final DateTime getStartDateTime()
    {
        if (!zzchn)
        {
            zzchn = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("recurrence_start_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zze.zza(dataholder, i, k, s))
            {
                zzcho = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("recurrence_start_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcho = new zze(dataholder1, j, s1);
            }
        }
        return zzcho;
    }

    public int hashCode()
    {
        return zzad.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzad(this)).writeToParcel(parcel, i);
    }
}
