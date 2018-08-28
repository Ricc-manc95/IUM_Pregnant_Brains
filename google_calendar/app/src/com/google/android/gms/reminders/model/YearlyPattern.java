// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.model:
//            MonthlyPattern

public interface YearlyPattern
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public MonthlyPattern zzciG;
        public List zzcjK;

        public Builder()
        {
        }
    }


    public abstract MonthlyPattern getMonthlyPattern();

    public abstract List getYearMonth();
}
