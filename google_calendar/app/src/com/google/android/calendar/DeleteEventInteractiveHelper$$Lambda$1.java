// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import com.google.android.calendar.api.event.Event;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar:
//            DeleteEventInteractiveHelper

final class arg._cls1
    implements AsyncFunction
{

    private final Context arg$1;

    public final ListenableFuture apply(Object obj)
    {
        return DeleteEventInteractiveHelper.lambda$showConfirmationDialog$0$DeleteEventInteractiveHelper(arg$1, (Event)obj);
    }

    (Context context)
    {
        arg$1 = context;
    }
}
