// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;


// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, HprofClassInstance, HprofClass

public final class e
    implements sCallback
{

    public final void edgeExplored(HprofObject hprofobject, HprofObject hprofobject1)
    {
        if (hprofobject1.parent == null)
        {
            boolean flag;
            if ((hprofobject1.flags & 1) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if ((hprofobject1 instanceof HprofClassInstance) && (((HprofClassInstance)hprofobject1).clazz.flags & 2) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    hprofobject1.parent = hprofobject;
                }
            }
        }
    }

    public e()
    {
    }
}
