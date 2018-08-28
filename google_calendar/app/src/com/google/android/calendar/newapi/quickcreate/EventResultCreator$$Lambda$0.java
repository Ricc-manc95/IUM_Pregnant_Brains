// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.location.EventLocation;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            EventResultCreator

final class arg._cls2
    implements Consumer
{

    private final EventResultCreator arg$1;
    private final String arg$2;

    public final void accept(Object obj)
    {
        EventResultCreator eventresultcreator = arg$1;
        String s = arg$2;
        obj = (EventLocation)obj;
        if (eventresultcreator.targetLocationReference.equals(s))
        {
            eventresultcreator.resolvedEventLocation = ((EventLocation) (obj));
        }
    }

    Q(EventResultCreator eventresultcreator, String s)
    {
        arg$1 = eventresultcreator;
        arg$2 = s;
    }
}
