// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common.impl:
//            AndroidClock

public final class AndroidClock_Factory
    implements Factory
{

    public static final AndroidClock_Factory INSTANCE = new AndroidClock_Factory();

    public AndroidClock_Factory()
    {
    }

    public final Object get()
    {
        return new AndroidClock();
    }

}
