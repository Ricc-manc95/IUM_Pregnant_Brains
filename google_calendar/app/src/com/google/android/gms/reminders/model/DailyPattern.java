// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Time

public interface DailyPattern
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public Time zzchR;
        public Integer zzchS;
        public Boolean zzchT;

        public Builder()
        {
        }
    }


    public abstract Boolean getAllDay();

    public abstract Integer getDayPeriod();

    public abstract Time getTimeOfDay();
}
