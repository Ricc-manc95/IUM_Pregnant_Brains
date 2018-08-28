// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;


// Referenced classes of package com.firebase.jobdispatcher:
//            JobTrigger

public final class windowEnd extends JobTrigger
{

    public final int windowEnd;
    public final int windowStart;

    (int i, int j)
    {
        windowStart = i;
        windowEnd = j;
    }
}
