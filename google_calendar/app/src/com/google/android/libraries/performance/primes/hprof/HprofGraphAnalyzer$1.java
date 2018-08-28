// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import java.util.Comparator;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (HprofObject)obj;
        obj1 = (HprofObject)obj1;
        return ((HprofObject) (obj)).retainedHeapSize - ((HprofObject) (obj1)).retainedHeapSize;
    }

    ()
    {
    }
}
