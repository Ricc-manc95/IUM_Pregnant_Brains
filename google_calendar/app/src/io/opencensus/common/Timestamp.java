// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.common;


// Referenced classes of package io.opencensus.common:
//            TimeUtils

public abstract class Timestamp
    implements Comparable
{

    Timestamp()
    {
    }

    public int compareTo(Object obj)
    {
        obj = (Timestamp)obj;
        int i = TimeUtils.compareLongs(getSeconds(), ((Timestamp) (obj)).getSeconds());
        if (i != 0)
        {
            return i;
        } else
        {
            return TimeUtils.compareLongs(getNanos(), ((Timestamp) (obj)).getNanos());
        }
    }

    public abstract int getNanos();

    public abstract long getSeconds();
}
