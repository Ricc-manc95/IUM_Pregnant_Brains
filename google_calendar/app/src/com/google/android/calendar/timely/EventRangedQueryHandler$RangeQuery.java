// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;


// Referenced classes of package com.google.android.calendar.timely:
//            RangedData

final class config
{

    public  config;
    public RangedData data;
    public int token;

    (RangedData rangeddata, int i)
    {
        rangeddata.setToken(i);
        data = rangeddata;
        token = i;
        config = null;
    }
}
