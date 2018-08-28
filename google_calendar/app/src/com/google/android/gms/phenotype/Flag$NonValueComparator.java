// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.phenotype;

import java.util.Comparator;

// Referenced classes of package com.google.android.gms.phenotype:
//            Flag

public static final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (Flag)obj;
        obj1 = (Flag)obj1;
        if (((Flag) (obj)).flagStorageType == ((Flag) (obj1)).flagStorageType)
        {
            return ((Flag) (obj)).name.compareTo(((Flag) (obj1)).name);
        } else
        {
            return ((Flag) (obj)).flagStorageType - ((Flag) (obj1)).flagStorageType;
        }
    }

    public ()
    {
    }
}
