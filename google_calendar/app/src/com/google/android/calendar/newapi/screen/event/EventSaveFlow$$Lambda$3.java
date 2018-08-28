// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.Event;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventSaveFlow

final class arg._cls2
    implements Consumer
{

    private final EventSaveFlow arg$1;
    private final Event arg$2;

    public final void accept(Object obj)
    {
        EventSaveFlow eventsaveflow = arg$1;
        Event event = arg$2;
        ((arg._cls2)obj).nSaveCompleted(true, event, eventsaveflow.scope);
    }

    (EventSaveFlow eventsaveflow, Event event)
    {
        arg$1 = eventsaveflow;
        arg$2 = event;
    }
}
