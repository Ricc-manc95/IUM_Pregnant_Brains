// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.MonthlyPattern;
import com.google.android.gms.reminders.model.zzv;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzj extends zzo
    implements MonthlyPattern
{

    public zzj(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "monthly_pattern_month_day");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            String s2 = zzaw(s, "monthly_pattern_week_day");
            dataholder.zzj(s2, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s2)))
            {
                s = zzaw(s, "monthly_pattern_week_day_number");
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
        if (!(obj instanceof MonthlyPattern))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzv.zza(this, (MonthlyPattern)obj);
        }
    }

    public final Object freeze()
    {
        return new zzv(this);
    }

    public final List getMonthDay()
    {
        Object obj;
        int i;
        i = 0;
        obj = zziU("monthly_pattern_month_day");
        if (!zzcO(((String) (obj)))) goto _L2; else goto _L1
_L1:
        obj = new ArrayList(0);
_L4:
        return ((List) (obj));
_L2:
        String s = getString(((String) (obj)));
        ArrayList arraylist = new ArrayList();
        obj = arraylist;
        if (TextUtils.isEmpty(s))
        {
            continue;
        }
        String as[] = TextUtils.split(s, ",");
        int j = as.length;
        do
        {
            obj = arraylist;
            if (i >= j)
            {
                continue;
            }
            arraylist.add(Integer.valueOf(as[i]));
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final Integer getWeekDay()
    {
        String s = zziU("monthly_pattern_week_day");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final Integer getWeekDayNumber()
    {
        String s = zziU("monthly_pattern_week_day_number");
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
        return zzv.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzv(this)).writeToParcel(parcel, i);
    }
}
