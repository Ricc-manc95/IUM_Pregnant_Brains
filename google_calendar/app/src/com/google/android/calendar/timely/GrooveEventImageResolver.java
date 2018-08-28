// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.util.gms.GmsFutures;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.habit.HabitClient;
import com.google.android.calendar.api.habit.HabitDescriptor;
import com.google.android.calendar.event.image.EventImage;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent, TimelineGroove

public final class GrooveEventImageResolver
    implements com.google.android.calendar.event.image.EventImage.Resolver
{

    public final TimelineGroove item;

    public GrooveEventImageResolver(TimelineGroove timelinegroove)
    {
        item = timelinegroove;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (GrooveEventImageResolver)obj;
        Object obj1 = ((TimelineEvent) (item)).title;
        String s = ((TimelineEvent) (((GrooveEventImageResolver) (obj)).item)).title;
        boolean flag;
        if (obj1 == s || obj1 != null && obj1.equals(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = item.descriptor;
        obj = ((GrooveEventImageResolver) (obj)).item.descriptor;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        int j = 0;
        int i;
        if (((TimelineEvent) (item)).title != null)
        {
            i = ((TimelineEvent) (item)).title.hashCode();
        } else
        {
            i = 0;
        }
        if (item.descriptor != null)
        {
            j = item.descriptor.hashCode();
        }
        return (i + 527) * 31 + j;
    }

    public final ListenableFuture resolveAsync(Context context, int i, int j)
    {
        Object obj = FlairAllocatorFactory.getFlairUrlString(((TimelineEvent) (item)).title);
        if (obj != null)
        {
            return EventImage.newUrlInstanceAsync(context, ((String) (obj)));
        }
        obj = item.type;
        class .Lambda._cls0
            implements AsyncFunction
        {

            private final GrooveEventImageResolver arg$1;
            private final Context arg$2;

            public final ListenableFuture apply(Object obj1)
            {
                GrooveEventImageResolver grooveeventimageresolver = arg$1;
                Context context1 = arg$2;
                obj1 = (com.google.android.calendar.api.habit.HabitClient.ReadResult)obj1;
                boolean flag;
                if (((com.google.android.calendar.api.habit.HabitClient.ReadResult) (obj1)).getStatus().zzaEP <= 0)
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
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                    }
                } else
                {
                    grooveeventimageresolver.item.type = Integer.valueOf(((com.google.android.calendar.api.habit.HabitClient.ReadResult) (obj1)).getHabit().getType());
                    return EventImage.newUrlInstanceAsync(context1, FlairAllocatorFactory.getGrooveFlairUrlString(((com.google.android.calendar.api.habit.HabitClient.ReadResult) (obj1)).getHabit().getType()));
                }
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = GrooveEventImageResolver.this;
                arg$2 = context;
            }
        }

        if (obj != null)
        {
            return EventImage.newUrlInstanceAsync(context, FlairAllocatorFactory.getGrooveFlairUrlString(((Integer) (obj)).intValue()));
        } else
        {
            return AbstractTransformFuture.create(GmsFutures.asFuture(CalendarApi.Habits.read(item.descriptor)), new .Lambda._cls0(context), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }
}
