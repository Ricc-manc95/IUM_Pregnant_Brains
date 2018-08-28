// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import java.util.ArrayList;
import java.util.List;

public interface WeeklyPattern
    extends Parcelable, Freezable
{
    public static final class Builder
    {

        public List zzcjJ;

        public final transient Builder addWeekDay(Integer ainteger[])
        {
            if (zzcjJ == null)
            {
                zzcjJ = new ArrayList();
            }
            int j = ainteger.length;
            for (int i = 0; i < j; i++)
            {
                Integer integer = ainteger[i];
                boolean flag;
                if (integer == null || integer.intValue() == 1 || integer.intValue() == 2 || integer.intValue() == 3 || integer.intValue() == 4 || integer.intValue() == 5 || integer.intValue() == 6 || integer.intValue() == 7)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException(String.valueOf("Invalid constant for Weekday. Use value in ModelConstants"));
                }
                zzcjJ.add(integer);
            }

            return this;
        }

        public Builder()
        {
        }
    }


    public abstract List getWeekDay();
}
