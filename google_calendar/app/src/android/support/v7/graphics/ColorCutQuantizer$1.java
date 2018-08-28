// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;

import java.util.Comparator;

final class ox
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (ox)obj;
        return ((ox)obj1).getVolume() - ((ox) (obj)).getVolume();
    }

    ox()
    {
    }
}
