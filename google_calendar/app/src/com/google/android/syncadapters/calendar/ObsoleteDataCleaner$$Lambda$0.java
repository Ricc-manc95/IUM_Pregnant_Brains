// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.Context;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            ObsoleteDataCleaner

final class arg._cls1
    implements Callable
{

    private final Context arg$1;

    public final Object call()
    {
        return ObsoleteDataCleaner.lambda$maybeRemoveAllObsoleteData$0$ObsoleteDataCleaner(arg$1);
    }

    (Context context)
    {
        arg$1 = context;
    }
}