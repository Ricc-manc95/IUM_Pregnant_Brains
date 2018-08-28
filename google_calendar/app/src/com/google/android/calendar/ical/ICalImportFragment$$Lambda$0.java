// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalImportFragment

final class 
    implements AsyncFunction
{

    public static final AsyncFunction $instance = new <init>();

    public final ListenableFuture apply(Object obj)
    {
        return ICalImportFragment.lambda$scheduleRefresh$0$ICalImportFragment((List)obj);
    }


    private ()
    {
    }
}
