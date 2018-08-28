// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.model:
//            MonthlyPattern

public static final class 
{

    public List zzcix;
    public Integer zzciy;
    public Integer zzciz;

    public final transient  addMonthDay(Integer ainteger[])
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

    public ()
    {
    }
}
