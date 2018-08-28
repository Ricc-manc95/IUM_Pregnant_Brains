// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventDeleteFlow

final class arg._cls2
    implements Consumer
{

    private final EventDeleteFlow arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        EventDeleteFlow eventdeleteflow = arg$1;
        boolean flag = arg$2;
        ((arg._cls2)obj).nEventDeleted(flag, eventdeleteflow.scope);
    }

    (EventDeleteFlow eventdeleteflow, boolean flag)
    {
        arg$1 = eventdeleteflow;
        arg$2 = flag;
    }
}
