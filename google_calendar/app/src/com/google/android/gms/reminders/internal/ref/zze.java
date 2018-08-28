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
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzl;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zzq

public class zze extends zzo
    implements DateTime
{

    private boolean zzcgL;
    private zzq zzcgM;

    public zze(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzcgL = false;
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "year");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            String s2 = zzaw(s, "month");
            dataholder.zzj(s2, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s2)))
            {
                String s3 = zzaw(s, "day");
                dataholder.zzj(s3, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s3)) && zzq.zza(dataholder, i, j, s))
                {
                    String s4 = zzaw(s, "period");
                    dataholder.zzj(s4, i);
                    if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s4)))
                    {
                        String s5 = zzaw(s, "date_range");
                        dataholder.zzj(s5, i);
                        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s5)))
                        {
                            String s6 = zzaw(s, "absolute_time_ms");
                            dataholder.zzj(s6, i);
                            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s6)))
                            {
                                String s7 = zzaw(s, "unspecified_future_time");
                                dataholder.zzj(s7, i);
                                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s7)))
                                {
                                    s = zzaw(s, "all_day");
                                    dataholder.zzj(s, i);
                                    if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
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
        if (!(obj instanceof DateTime))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzl.zza(this, (DateTime)obj);
        }
    }

    public final Object freeze()
    {
        return new zzl(this);
    }

    public final Long getAbsoluteTimeMs()
    {
        String s = zziU("absolute_time_ms");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final Boolean getAllDay()
    {
        return Boolean.valueOf(getBoolean(zziU("all_day")));
    }

    public final Integer getDateRange()
    {
        String s = zziU("date_range");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getDay()
    {
        String s = zziU("day");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getMonth()
    {
        String s = zziU("month");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getPeriod()
    {
        String s = zziU("period");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Time getTime()
    {
        if (!zzcgL)
        {
            zzcgL = true;
            if (zzq.zza(zzaKT, zzaNQ, zzaNR, zzchp))
            {
                zzcgM = null;
            } else
            {
                zzcgM = new zzq(zzaKT, zzaNQ, zzchp);
            }
        }
        return zzcgM;
    }

    public final Boolean getUnspecifiedFutureTime()
    {
        return Boolean.valueOf(getBoolean(zziU("unspecified_future_time")));
    }

    public final Integer getYear()
    {
        String s = zziU("year");
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
        return zzl.zzb(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzl(this)).writeToParcel(parcel, i);
    }
}
