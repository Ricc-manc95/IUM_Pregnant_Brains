// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            EditScreenController

final class arg._cls2
    implements Consumer
{

    private final boolean arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        EditScreenController.lambda$notifyCalendarChanged$0$EditScreenController(arg$1, arg$2, (EditSegmentController)obj);
    }

    (boolean flag, boolean flag1)
    {
        arg$1 = flag;
        arg$2 = flag1;
    }
}
