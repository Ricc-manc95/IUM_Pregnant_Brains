// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.belong;

import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.belong:
//            BelongUtils

final class arg._cls1
    implements Callable
{

    private final List arg$1;

    public final Object call()
    {
        return BelongUtils.lambda$hasHabitsWithFitIntegrationAsync$1$BelongUtils(arg$1);
    }

    (List list)
    {
        arg$1 = list;
    }
}
