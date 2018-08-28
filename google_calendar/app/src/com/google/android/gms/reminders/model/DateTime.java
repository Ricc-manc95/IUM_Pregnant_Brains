// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Time, zzl

public interface DateTime
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public Boolean zzchT;
        public Integer zzchV;
        public Integer zzchW;
        public Integer zzchX;
        public Time zzchY;
        public Long zzcib;
        public Boolean zzcic;

        public final DateTime build()
        {
            return new zzl(zzchV, zzchW, zzchX, zzchY, null, null, zzcib, zzcic, zzchT, true);
        }

        public Builder()
        {
        }
    }


    public abstract Long getAbsoluteTimeMs();

    public abstract Boolean getAllDay();

    public abstract Integer getDateRange();

    public abstract Integer getDay();

    public abstract Integer getMonth();

    public abstract Integer getPeriod();

    public abstract Time getTime();

    public abstract Boolean getUnspecifiedFutureTime();

    public abstract Integer getYear();
}
