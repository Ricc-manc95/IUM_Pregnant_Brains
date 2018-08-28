// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.reminders.model.WeeklyPattern;
import com.google.android.gms.reminders.model.zzal;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzr extends zzo
    implements WeeklyPattern
{

    public zzr(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof WeeklyPattern))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzal.zza(this, (WeeklyPattern)obj);
        }
    }

    public final Object freeze()
    {
        return new zzal(this);
    }

    public final List getWeekDay()
    {
        return zziV(zziU("weekly_pattern_weekday"));
    }

    public int hashCode()
    {
        return zzal.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzal(this)).writeToParcel(parcel, i);
    }
}
