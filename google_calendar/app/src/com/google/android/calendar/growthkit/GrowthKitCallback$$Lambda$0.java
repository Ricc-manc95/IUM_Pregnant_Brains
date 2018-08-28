// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.growthkit;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.growthkit:
//            GrowthKitCallback

final class arg._cls1
    implements Function
{

    private final String arg$1;

    public final Object apply(Object obj)
    {
        return GrowthKitCallback.lambda$onAppStateNeeded$0$GrowthKitCallback(arg$1, (Integer)obj);
    }

    (String s)
    {
        arg$1 = s;
    }
}
