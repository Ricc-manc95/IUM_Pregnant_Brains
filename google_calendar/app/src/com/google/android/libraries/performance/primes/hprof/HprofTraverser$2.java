// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import java.util.Set;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject

final class sCallback
    implements sCallback
{

    public final void edgeExplored(HprofObject hprofobject, HprofObject hprofobject1)
    {
        hprofobject1.parents.add(hprofobject);
    }

    sCallback()
    {
    }
}
