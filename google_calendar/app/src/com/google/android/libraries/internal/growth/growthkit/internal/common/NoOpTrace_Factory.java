// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common:
//            NoOpTrace

public final class NoOpTrace_Factory
    implements Factory
{

    public static final NoOpTrace_Factory INSTANCE = new NoOpTrace_Factory();

    public NoOpTrace_Factory()
    {
    }

    public final Object get()
    {
        return new NoOpTrace();
    }

}
