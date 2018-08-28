// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventSaveFlow

final class arg._cls1
    implements Consumer
{

    private final EventSaveFlow arg$1;

    public final void accept(Object obj)
    {
        EventSaveFlow eventsaveflow = arg$1;
        ((arg._cls1)obj).nSaveCompleted(false, null, eventsaveflow.scope);
    }

    (EventSaveFlow eventsaveflow)
    {
        arg$1 = eventsaveflow;
    }
}
