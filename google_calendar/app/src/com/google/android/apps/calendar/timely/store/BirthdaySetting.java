// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;


public final class BirthdaySetting
{

    public boolean includeGplusBirthday;
    public boolean visibility;

    public BirthdaySetting(int i)
    {
        if (i == 2)
        {
            visibility = false;
            return;
        }
        if (i == 0)
        {
            visibility = true;
            includeGplusBirthday = true;
            return;
        }
        if (i == 1)
        {
            visibility = true;
            includeGplusBirthday = false;
            return;
        } else
        {
            throw new IllegalArgumentException("illegal BirthdaySetting mode");
        }
    }

    public BirthdaySetting(boolean flag, boolean flag1)
    {
        visibility = flag;
        includeGplusBirthday = flag1;
    }

    public final boolean equals(Object obj)
    {
        byte byte0 = 2;
        boolean flag1 = false;
        boolean flag = flag1;
        if (obj instanceof BirthdaySetting)
        {
            obj = (BirthdaySetting)obj;
            int i;
            if (((BirthdaySetting) (obj)).visibility)
            {
                if (((BirthdaySetting) (obj)).includeGplusBirthday)
                {
                    i = 0;
                } else
                {
                    i = 1;
                }
            } else
            {
                i = 2;
            }
            if (visibility)
            {
                if (includeGplusBirthday)
                {
                    byte0 = 0;
                } else
                {
                    byte0 = 1;
                }
            }
            flag = flag1;
            if (i == byte0)
            {
                flag = true;
            }
        }
        return flag;
    }

    public final int hashCode()
    {
        if (visibility)
        {
            return !includeGplusBirthday ? 1 : 0;
        } else
        {
            return 2;
        }
    }
}
