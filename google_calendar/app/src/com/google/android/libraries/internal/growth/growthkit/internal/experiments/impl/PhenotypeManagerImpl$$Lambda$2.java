// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.experiments.impl:
//            PhenotypeManagerImpl

final class 
    implements Receiver
{

    public static final Receiver $instance = new <init>();

    public final void accept(Object obj)
    {
        obj = (Throwable)obj;
        PhenotypeManagerImpl.logger.w(((Throwable) (obj)), "Failed to register to Phenotype", new Object[0]);
    }


    private ()
    {
    }
}
