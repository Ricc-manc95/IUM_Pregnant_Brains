// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.RecurrenceEnd;
import com.google.android.gms.reminders.model.zzy;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zze

public class zzk extends zzo
    implements RecurrenceEnd
{

    private boolean zzcgV;
    private zze zzcgW;
    private boolean zzcgX;
    private zze zzcgY;

    public zzk(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgV = false;
        zzcgX = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = String.valueOf(s);
        String s2 = String.valueOf("recurrence_end_");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        if (zze.zza(dataholder, i, j, s1))
        {
            s1 = zzaw(s, "recurrence_end_num_occurrences");
            dataholder.zzj(s1, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
            {
                s1 = zzaw(s, "recurrence_end_auto_renew");
                dataholder.zzj(s1, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
                {
                    s = String.valueOf(s);
                    s1 = String.valueOf("recurrence_end_auto_renew_until_");
                    if (s1.length() != 0)
                    {
                        s = s.concat(s1);
                    } else
                    {
                        s = new String(s);
                    }
                    if (zze.zza(dataholder, i, j, s))
                    {
                        return true;
                    }
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
        if (!(obj instanceof RecurrenceEnd))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzy.zza(this, (RecurrenceEnd)obj);
        }
    }

    public final Object freeze()
    {
        return new zzy(this);
    }

    public final Boolean getAutoRenew()
    {
        return Boolean.valueOf(getBoolean(zziU("recurrence_end_auto_renew")));
    }

    public final DateTime getAutoRenewUntil()
    {
        if (!zzcgX)
        {
            zzcgX = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("recurrence_end_auto_renew_until_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zze.zza(dataholder, i, k, s))
            {
                zzcgY = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("recurrence_end_auto_renew_until_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcgY = new zze(dataholder1, j, s1);
            }
        }
        return zzcgY;
    }

    public final DateTime getEndDateTime()
    {
        if (!zzcgV)
        {
            zzcgV = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("recurrence_end_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zze.zza(dataholder, i, k, s))
            {
                zzcgW = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("recurrence_end_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzcgW = new zze(dataholder1, j, s1);
            }
        }
        return zzcgW;
    }

    public final Integer getNumOccurrences()
    {
        String s = zziU("recurrence_end_num_occurrences");
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
        return zzy.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzy(this)).writeToParcel(parcel, i);
    }
}
