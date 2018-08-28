// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;


// Referenced classes of package com.google.android.gms.fitness.data:
//            DataSource, Application, DataType

public static final class zzbhP
{

    public String name;
    public int type;
    public Application zzbhO;
    public String zzbhP;
    public DataType zzbhk;

    public final DataSource build()
    {
        boolean flag1 = true;
        boolean flag;
        if (zzbhk != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Must set data type"));
        }
        if (type >= 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("Must set data source type"));
        } else
        {
            return new DataSource(this);
        }
    }

    public ()
    {
        type = -1;
        zzbhP = "";
    }
}
