// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            StorageUtilitiesImpl

final class q
    implements Receiver
{

    public static final Receiver $instance = new <init>();

    public final void accept(Object obj)
    {
        obj = (Integer)obj;
        obj = StorageUtilitiesImpl.logger;
    }


    private q()
    {
    }
}
