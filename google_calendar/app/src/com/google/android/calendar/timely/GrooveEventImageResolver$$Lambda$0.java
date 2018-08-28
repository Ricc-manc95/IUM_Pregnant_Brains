// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.calendar.api.habit.Habit;
import com.google.android.calendar.event.image.EventImage;
import com.google.android.gms.common.api.Status;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely:
//            GrooveEventImageResolver, TimelineGroove

final class arg._cls2
    implements AsyncFunction
{

    private final GrooveEventImageResolver arg$1;
    private final Context arg$2;

    public final ListenableFuture apply(Object obj)
    {
        GrooveEventImageResolver grooveeventimageresolver = arg$1;
        Context context = arg$2;
        obj = (com.google.android.calendar.api.habit.._cls2)obj;
        boolean flag;
        if (((com.google.android.calendar.api.habit.._cls2) (obj))._mth2().zzaEP <= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (true)
            {
                return com.google.common.util.concurrent.Future.NULL;
            } else
            {
                return new com.google.common.util.concurrent.Future(null);
            }
        } else
        {
            grooveeventimageresolver.item.type = Integer.valueOf(((com.google.android.calendar.api.habit.re) (obj)).re().getType());
            return EventImage.newUrlInstanceAsync(context, FlairAllocatorFactory.getGrooveFlairUrlString(((com.google.android.calendar.api.habit.irUrlString) (obj)).irUrlString().getType()));
        }
    }

    uture(GrooveEventImageResolver grooveeventimageresolver, Context context)
    {
        arg$1 = grooveeventimageresolver;
        arg$2 = context;
    }
}
