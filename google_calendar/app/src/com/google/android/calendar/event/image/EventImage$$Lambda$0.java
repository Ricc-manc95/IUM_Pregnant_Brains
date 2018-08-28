// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.event.image:
//            EventImage

final class arg._cls2
    implements Callable
{

    private final Context arg$1;
    private final String arg$2;

    public final Object call()
    {
        return EventImage.lambda$newUrlInstanceAsync$0$EventImage(arg$1, arg$2);
    }

    (Context context, String s)
    {
        arg$1 = context;
        arg$2 = s;
    }
}
