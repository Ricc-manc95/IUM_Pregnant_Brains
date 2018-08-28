// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import java.util.Comparator;

final class emInfo
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (emInfo)obj;
        obj1 = (emInfo)obj1;
        return ((emInfo) (obj)).position - ((emInfo) (obj1)).position;
    }

    emInfo()
    {
    }
}
