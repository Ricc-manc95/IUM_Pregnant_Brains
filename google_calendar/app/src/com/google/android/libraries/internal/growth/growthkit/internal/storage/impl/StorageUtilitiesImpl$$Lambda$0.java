// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            StorageUtilitiesImpl

final class arg._cls1
    implements Receiver
{

    private final String arg$1;

    public final void accept(Object obj)
    {
        String s = arg$1;
        obj = (Integer)obj;
        obj = StorageUtilitiesImpl.logger;
    }

    q(String s)
    {
        arg$1 = s;
    }
}
