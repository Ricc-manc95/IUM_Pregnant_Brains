// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.ArrayList;
import java.util.List;

public interface MonthlyPattern
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public List zzcix;
        public Integer zzciy;
        public Integer zzciz;

        public final transient Builder addMonthDay(Integer ainteger[])
        {
            if (zzcix == null)
            {
                zzcix = new ArrayList();
            }
            int j = ainteger.length;
            for (int i = 0; i < j; i++)
            {
                Integer integer = ainteger[i];
                zzcix.add(integer);
            }

            return this;
        }

        public Builder()
        {
        }
    }


    public abstract List getMonthDay();

    public abstract Integer getWeekDay();

    public abstract Integer getWeekDayNumber();
}
